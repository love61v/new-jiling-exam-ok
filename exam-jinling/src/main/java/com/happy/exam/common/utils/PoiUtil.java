package com.happy.exam.common.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.alibaba.fastjson.JSON;
import com.happy.exam.common.bean.ExamQuestionModel;
import com.happy.exam.common.enums.ExamTypeEnum;
import com.happy.exam.model.ExamQuickImport;
import com.happy.exam.model.ExamQuickImportItems;
import com.happy.exam.model.SystemUser;

/**
 * poi的excel工具类.
 * 
 * @version : Ver 1.0
 * @author : <a href="mailto:hubo@95190.com">hubo</a>
 * @date : 2015年5月17日 下午5:37:55
 */
public class PoiUtil {
	
	/**
	 * 加载excel文件
	 * 
	 * @param filename
	 *            文件路径
	 * @return
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static HSSFWorkbook readFile(InputStream inputStream) {
		try {
			return new HSSFWorkbook(inputStream);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 5种题型导入
	 * 解析各种考试excel数据到list中 从第2行开始
	 * @param filepath 路径
	 * @param model    实体
	 * @param examTypeEnum 考试题目enum,传入值ExamTypeEnum.SINGLE等等
	 * @return
	 */
	public static List<ExamQuestionModel> parseList(InputStream inputStream,
			ExamQuestionModel model, ExamTypeEnum examTypeEnum) {
		
		List<ExamQuestionModel> dataSet = new ArrayList<ExamQuestionModel>();
		
		HSSFWorkbook workbook = readFile(inputStream);
		if (null == workbook) {
			return dataSet;
		}
		 
		HSSFSheet sheet = workbook.getSheetAt(0); // 只取第1个sheet
		readDataFromCell(sheet, model, examTypeEnum, dataSet);
		
		return dataSet;
	}
	
	/**
	 * 快速导入
	 * 解析各种考试excel数据到list中 从第2行开始
	 * @param filepath 路径
	 * @param model    实体
	 * @param examTypeEnum 考试题目enum,传入值ExamTypeEnum.SINGLE等等
	 * @return
	 */
	public static List<ExamQuickImportItems> parseQuickImportExcel(
			InputStream inputStream, ExamQuickImport baseQuickModel) {
		List<ExamQuickImportItems> dataSet = new ArrayList<ExamQuickImportItems>();

		HSSFWorkbook workbook = readFile(inputStream);
		if (null == workbook) {
			return dataSet;
		}

		HSSFSheet sheet1 = workbook.getSheetAt(0); // 第1个sheet是单选题目
		if (null != sheet1) {
			List<ExamQuickImportItems> singleList = readQuickImport(
					ExamTypeEnum.SINGLE, sheet1, baseQuickModel);
			if (null != singleList && singleList.size() > 0) {
				dataSet.addAll(singleList);
			}
		}

		HSSFSheet sheet2 = workbook.getSheetAt(1); // 多选题目sheet
		if (null != sheet2) {
			List<ExamQuickImportItems> multiList = readQuickImport(
					ExamTypeEnum.MULTI, sheet2, baseQuickModel);
			if (null != multiList && multiList.size() > 0) {
				dataSet.addAll(multiList);
			}
		}

		HSSFSheet sheet3 = workbook.getSheetAt(2); // 填空sheet
		if (null != sheet3) {
			List<ExamQuickImportItems> fillList = readQuickImport(
					ExamTypeEnum.FILL, sheet3, baseQuickModel);
			if (null != fillList && fillList.size() > 0) {
				dataSet.addAll(fillList);
			}
		}

		HSSFSheet sheet4 = workbook.getSheetAt(3); // 简答题目sheet
		if (null != sheet4) {
			List<ExamQuickImportItems> shortsList = readQuickImport(
					ExamTypeEnum.SHORTS, sheet4, baseQuickModel);
			if (null != shortsList && shortsList.size() > 0) {
				dataSet.addAll(shortsList);
			}
		}

		HSSFSheet sheet5 = workbook.getSheetAt(4); // 操作题目sheet
		if (null != sheet5) {
			List<ExamQuickImportItems> operateList = readQuickImport(
					ExamTypeEnum.OPERATE, sheet5, baseQuickModel);
			if (null != operateList && operateList.size() > 0) {
				dataSet.addAll(operateList);
			}
		}

		return dataSet;
	}
	
	/**
	 * 解析快速导入试卷到集合
	 * @param examTypeEnum  题型枚举
	 * @param sheet  
	 * @param baseQuickModel
	 * @param dataSet
	 * @return
	 */
	private static List<ExamQuickImportItems> readQuickImport(
			ExamTypeEnum examTypeEnum, HSSFSheet sheet,
			ExamQuickImport baseQuickModel) {
		
		List<ExamQuickImportItems> dataSet = new ArrayList<ExamQuickImportItems>();
		ExamQuickImportItems model = null;
		ExamQuestionModel tempModel = null;
		
		int rows = sheet.getPhysicalNumberOfRows();
		for (int r = 1; r < rows; r++) {// 从第1行开始解析
			HSSFRow row = sheet.getRow(r); // 行
			if (null == row) {
				continue;
			}
			
			tempModel = new ExamQuestionModel(); //问题答案分数解析临时存储
			model = new ExamQuickImportItems();  //单元格内容解析到实体后入库的bean
			model.setQuickId(baseQuickModel.getQuickId());
			
			setExamTypeName(examTypeEnum, model); //设置题型名
			
			String value = "";
			HSSFCell cell = row.getCell(0); // 第1个单元格为问题
			if(null == cell || StringUtils.isBlank(cell.getStringCellValue())){
				continue;
			}
			value = cell.getStringCellValue();

			String sencond = ""; // 第二列值
			if (null != row.getCell(1)) {
				HSSFCell cellAnswer = row.getCell(1);
				sencond = cellAnswer.getStringCellValue();
			}
			
			//解析数据
			tempModel = parseData(value, sencond, tempModel, examTypeEnum);

			if (checkNullCell(row.getCell(1))) {// 处理第二个单元格
				if (examTypeEnum == ExamTypeEnum.SINGLE
						|| examTypeEnum == ExamTypeEnum.MULTI) {
					value = value.concat("\r\n").concat(sencond); // 单选多选题第二列是选项
				}
				if (examTypeEnum == ExamTypeEnum.SHORTS
						|| examTypeEnum == ExamTypeEnum.OPERATE) {
					tempModel.setAnswer(sencond); // 简答题或者操作题第二列为答案
				}
			}

			tempModel.setPrototypeQuestion(value); //源题内容
			tempModel.setStatus(1);
			
			try {
				PropertyUtils.copyProperties(model, tempModel);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			model.setSort(r); //顺序
			dataSet.add(model);
		}
		
		return dataSet;
	}

	

	/**
	 * 从当前excel的sheet中读取单元格数据设置到集合模型中
	 * @param sheet 当前excel的sheet
	 * @param model  问题dto
	 * @param examTypeEnum 题型
	 * @param dataSet  集合
	 */
	private static void readDataFromCell(HSSFSheet sheet,
			ExamQuestionModel model, ExamTypeEnum examTypeEnum,
			List<ExamQuestionModel> dataSet) {
		ExamQuestionModel tempModel = null;

		int rows = sheet.getPhysicalNumberOfRows();
		for (int r = 1; r < rows; r++) {// 从第1行开始解析
			HSSFRow row = sheet.getRow(r); // 行
			if (null == row) {
				continue;
			}
			tempModel = setBean(model);

			String value = "";
			HSSFCell cell = row.getCell(0); // 第1个单元格为问题
			if(null == cell || StringUtils.isBlank(cell.getStringCellValue())){
				continue;
			}
			value = cell.getStringCellValue();

			String sencond = ""; // 第二列值
			if (null != row.getCell(1)) {
				HSSFCell cellAnswer = row.getCell(1);
				sencond = cellAnswer.getStringCellValue();
			}
			tempModel = parseData(value, sencond, tempModel, examTypeEnum);

			if (checkNullCell(row.getCell(1))) {// 处理第二个单元格
				if (examTypeEnum == ExamTypeEnum.SINGLE
						|| examTypeEnum == ExamTypeEnum.MULTI) {
					value = value.concat("\r").concat(sencond); // 单选多选题第二列是选项
				}
				if (examTypeEnum == ExamTypeEnum.SHORTS
						|| examTypeEnum == ExamTypeEnum.OPERATE) {
					tempModel.setAnswer(sencond); // 简答题或者操作题第二列为答案
				}

			}

			tempModel.setPrototypeQuestion(value); //源题内容
			tempModel.setStatus(1);

			dataSet.add(tempModel);
		}
	}

	/**
	 * 解析单元格行数据到实体中
	 * 
	 * @param value
	 *            内容
	 * @param sencond
	 *            单选多选答案
	 * @param tempModel
	 *            实体(已包含有当前登陆用户,typeid,操作时间的设值)
	 * 
	 * @param examTypeEnum
	 *            传入ExamTypeEnum.SINGLE等等
	 * @return
	 */
	public static ExamQuestionModel parseData(String value, String sencond,
			ExamQuestionModel model, ExamTypeEnum examTypeEnum) {
		String key = examTypeEnum.getKey(); // 题型

		switch (examTypeEnum) {
		case SINGLE: // 单选题
			model.setAnswer(getSingleMultiAnswer(value)); // 设置答案
			model.setQuestion(getSingleMultiQuestion(value,sencond,key)); // 设置问题
			model.setScore("0.5");
			break;
		case MULTI: // 多选题
			model.setAnswer(getSingleMultiAnswer(value));
			model.setQuestion(getSingleMultiQuestion(value,sencond,key));
			model.setScore("1");
			break;
		case FILL: // 填空题
			String answer = getFillAnswer(value);
			model.setAnswer(answer);
			model.setScore(answer.split(";").length * 0.5 + "");
			model.setQuestion(getFillQuestion(value, answer));
			break;
		case SHORTS: // 简答题
			model.setScore(getShortsScore(value));
			model.setQuestion(value); // 问题
			model.setAnswer(sencond);
			break;
		case OPERATE: // 操作题
			model.setScore(getShortsScore(value));
			model.setQuestion(value);
			model.setAnswer(sencond);
			break;
		default:
			break;
		}

		return model;
	}

	/**
	 * 获取简答题的分值
	 * 
	 * @author : <a href="mailto:h358911056@qq.com">hubo</a> 2015年6月24日
	 *         下午10:55:08
	 * @param value
	 * @return
	 */
	public static String getShortsScore(String value) {
		value = adjust(value);
		Pattern p = Pattern.compile(StrUtil.REG_ALL);
		Matcher matcher = p.matcher(value);
		if (matcher.find()) {
			String temp = StrUtil.replaceBracketNull(matcher.group(0));
			if (temp.matches(StrUtil.REG_NUMBER)) {
				value = temp;
			} else if (temp.indexOf("分") > 0) {
				value = temp.substring(0, temp.indexOf("分"));
			} else {
				value = "4"; //默认4分
			}
		}

		return value;
	}

	/**
	 * 问题解析，将正确答案替换占位体符，在前台可替换文本框
	 *
	 * @author 	: <a href="mailto:954643309@qq.com">hgx</a>  2015-7-13 下午7:56:12
	 * @param content  原题干
	 * @param options  选项
	 * @param key  类型key
	 * @return
	 */
	public static String getSingleMultiQuestion(String content,String options, String key) {
		content = adjust(content).replace("\n", "").replace("\r","");
		content = content.replaceAll(StrUtil.REG_AZ, "(  " + key + "  )");
		content = content.concat("\r\n").concat(options);
		
		return content;
	}

	/**
	 * 将内容中的全角括号替换，且去除所有空格
	 * 
	 * @author : <a href="mailto:h358911056@qq.com">hubo</a> 2015年6月24日
	 *         下午10:50:11
	 * @param content
	 * @return
	 */
	public static String adjust(String content) {
		return StrUtil.toSemiangle(content).replaceAll(StrUtil.REG_S, "");
	}

	/**
	 * 单选题，多选题答案解析
	 * 
	 * @param value
	 * @return
	 */
	public static String getSingleMultiAnswer(String value) {
		value = adjust(value);
		Pattern p = Pattern.compile(StrUtil.REG_AZ);
		Matcher matcher = p.matcher(value);
		if (matcher.find()) {
			value = StrUtil.replaceBracketNull(matcher.group());
			value = StrUtil.sortCharater(value);
		}else{
			value = "";
		}
		return value;
	}
	

	/**
	 * 填空题答案解析 格式：散槽是(加深(加强))的解析为answer为"加深|加强"
	 * 
	 * @param value
	 * @return
	 */
	public static String getFillAnswer(String value) {
		StringBuffer sbf = new StringBuffer("");
		value = adjust(value);

		String result = "";
		Pattern pattern = Pattern.compile(StrUtil.REG_MORE);
		Matcher matcher = pattern.matcher(value);
		int i = 0;
		while (matcher.find()) {
			result = matcher.group(0);
			result = result.replaceAll("\\)", "");
			result = result.replaceFirst("\\(", "");
			result = result.replaceFirst("\\(", "|");
			if (i > 0) {
				sbf.append(";");
			}
			sbf.append(result);
			i++;
		}

		return sbf.toString();
	}

	/**
	 * 填空题答案解析 格式：散槽是(加深(加强))的解析为answer为"加深|加强" 表示加深或者加强都是正确答案
	 * 
	 * @param value
	 * @return
	 */
	public static String getFillQuestion(String txt, String answer) {
		txt = adjust(txt);

		String[] arr = answer.split(";");
		for (int i = 0, len = arr.length; i < len; i++) {
			String temp = arr[i].replace("|", "(");
			txt = txt.replace(temp, ExamTypeEnum.FILL.getKey() + i);
		}

		return txt;
	}
	
	private static void setExamTypeName(ExamTypeEnum examTypeEnum, ExamQuickImportItems model) {
		switch (examTypeEnum) {
		case SINGLE:
			model.setTypeFlag(1);
			model.setTypeName("单选题目");
			break;
		case MULTI:
			model.setTypeFlag(2);
			model.setTypeName("多选题目");
			break;
		case FILL:
			model.setTypeFlag(3);
			model.setTypeName("填空题目");
			break;
		case SHORTS:
			model.setTypeFlag(4);
			model.setTypeName("简答题目");
			break;
		case OPERATE:
			model.setTypeFlag(5);
			model.setTypeName("操作题目");
			break;
		default:
			break;
		}
	}
	
	/**
	 * 实体属性复制
	 * @param model
	 * @return
	 */
	public static ExamQuestionModel setBean(ExamQuestionModel model) {
		ExamQuestionModel tempModel;
		if (null == model) {
			tempModel = new ExamQuestionModel();
		} else {
			tempModel = new ExamQuestionModel();
			try {
				PropertyUtils.copyProperties(tempModel, model);
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			}
		}
		return tempModel;
	}
	 
	/**
	 * 解析导入的用户信息excel
	 *
	 * @author 	: <a href="mailto:358911056@qq.com">hubo</a>  2015-7-9 下午2:59:41
	 * @return 
	 */
	public static List<SystemUser> parseUserFromExcel(InputStream inputStream){
		return null;
		/*
		List<SystemUser> userList = new ArrayList<SystemUser>();
		
		HSSFWorkbook workbook = readFile(inputStream);
		if (null == workbook) {
			return userList;
		}
		
		HSSFSheet sheet = workbook.getSheetAt(0); // 只取第1个sheet
		int rows = sheet.getPhysicalNumberOfRows();
		for (int r = 2; r < rows; r++) {// 从第3行开始解析
			HSSFRow row = sheet.getRow(r); // 行
			if (null == row) {
				continue;
			}
			
			SystemUser user = new SystemUser();
			user.setPassword(Md5.md5("123"));
			user.setCreateTime(new Date());
			user.setStatus(1);
			user.setDeptId(1L);
			
			if(checkNullCell(row.getCell(0))){
				user.setForcastNumber((int)row.getCell(0).getNumericCellValue() + "");//预报员编号
			}
			if(checkNullCell(row.getCell(1))){
				user.setLoginName(row.getCell(1).getStringCellValue());  		//登陆账号
			}
			if(checkNullCell(row.getCell(2))){
				user.setUserName(row.getCell(2).getStringCellValue());   		//姓名
			}
			if(checkNullCell(row.getCell(3))){
				String sex = row.getCell(3).getStringCellValue();  				//性别
				if(StringUtils.isNotBlank(sex)){
					user.setSex(sex.equals("男") ? "1" : "2");
				}
			}
			if(checkNullCell(row.getCell(4))){
				user.setIdCard(getCellValue(row.getCell(4)) +""); 				//身份证号码
			}
			if(checkNullCell(row.getCell(5))){
				user.setAddress(row.getCell(5).getStringCellValue()); 			//工作单位
			}
			if(checkNullCell(row.getCell(6))){
				user.setMajorFirst(row.getCell(6).getStringCellValue());		// 第一学历专业
			}
			if(checkNullCell(row.getCell(7))){
				user.setSchoolFirst(row.getCell(7).getStringCellValue()); 		//毕业院校
			}
			if(checkNullCell(row.getCell(8))){
				user.setGraduateFirst(row.getCell(8).getDateCellValue());   	//毕业时间
			}
			if(checkNullCell(row.getCell(9))){
				user.setMajorSecond(row.getCell(9).getStringCellValue()); 		//第二学历专业
			}
			if(checkNullCell(row.getCell(10))){
				user.setSchoolSecond(row.getCell(10).getStringCellValue()); 	//第二毕业院校
			}
			if(checkNullCell(row.getCell(11))){
				user.setGraduateSecond(row.getCell(11).getDateCellValue());   	//第二毕业时间
			}
			if(checkNullCell(row.getCell(12))){
				user.setJobTitle(row.getCell(12).getStringCellValue());			//职称
			}
			if(checkNullCell(row.getCell(13))){
				user.setJobDate(row.getCell(13).getDateCellValue());			//任职时间
			}
			if(checkNullCell(row.getCell(14))){
				user.setJobRemark(row.getCell(14).getStringCellValue());		//曾经历岗位及年限
			}
			if(checkNullCell(row.getCell(15))){
				user.setBirthday(row.getCell(15).getDateCellValue());			//出生年月	
			}
			if(checkNullCell(row.getCell(16))){
				user.setForcastBeginDate(row.getCell(16).getDateCellValue());	//开始从事预报年月	
			}
			if(checkNullCell(row.getCell(17))){
				user.setTrain(row.getCell(17).getStringCellValue());			//培训经历
			}

			userList.add(user);
		}
		
		return userList;
	*/}
	
	/**
	 * 单元格是否有数据
	 *
	 * @author 	: <a href="mailto:358911056@qq.com">hubo</a>  2015-7-9 下午3:57:53
	 * @param cell
	 * @return
	 */
	public static boolean checkNullCell(HSSFCell cell){
		return (null != cell ? true : false);
	}
		
	/**
	 * 根据单元数据格式类型取值
	 *
	 * @author 	: <a href="mailto:358911056@qq.com">hubo</a>  2015-7-9 下午3:58:10
	 * @param cell
	 * @return
	 */
	public static Object getCellValue(HSSFCell cell){
		Object value = "";
		switch (cell.getCellType()) {
		case HSSFCell.CELL_TYPE_NUMERIC: //数字
			value = cell.getNumericCellValue();
			break;
		case HSSFCell.CELL_TYPE_STRING:  //字符
			value = cell.getStringCellValue();
		break;

		default:
			break;
		}
		return value;
	}

	public static void main(String[] args) throws FileNotFoundException {
		InputStream inputStream = new FileInputStream("d:/quickImport.xls");
		ExamQuickImport baseQuickModel = new ExamQuickImport();
		baseQuickModel.setQuickId(1L);
		
		List<ExamQuickImportItems> parseList = parseQuickImportExcel(inputStream, baseQuickModel);
		System.out.println(JSON.toJSONString(parseList));
	}

}
