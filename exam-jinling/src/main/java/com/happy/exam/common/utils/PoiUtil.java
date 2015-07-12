package com.happy.exam.common.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellRangeAddress;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import com.alibaba.fastjson.JSON;
import com.happy.exam.common.bean.ExamQuestionModel;
import com.happy.exam.common.enums.ExamTypeEnum;

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
	public static HSSFWorkbook readFile(String filepath) {
		try {
			return new HSSFWorkbook(new FileInputStream(filepath));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
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
	 * 解析各种考试excel数据到list中 从第2行开始
	 * @param filepath 路径
	 * @param model    实体
	 * @param examTypeEnum 考试题目enum,传入值ExamTypeEnum.SINGLE等等
	 * @return
	 */
	public static List<ExamQuestionModel> parseList(InputStream inputStream,ExamQuestionModel model, ExamTypeEnum examTypeEnum) {
		List<ExamQuestionModel> dataSet = new ArrayList<ExamQuestionModel>();
		ExamQuestionModel tempModel = null;
		
		HSSFWorkbook workbook = readFile(inputStream);
		if (null == workbook) {
			return dataSet;
		}

		HSSFSheet sheet = workbook.getSheetAt(0); // 只取第1个sheet
		int rows = sheet.getPhysicalNumberOfRows();

		for (int r = 1; r < rows; r++) {// 从第1行开始解析
			HSSFRow row = sheet.getRow(r); // 行
			if (null == row) {
				continue;
			}
			
			tempModel = setBean(model);

			String value = "";
			HSSFCell cell = row.getCell(0); // 第1个单元格为问题
			value = cell.getStringCellValue();
			
			String sencond  = "";
			if(null != row.getCell(1)){
				HSSFCell cellAnswer = row.getCell(1); // 第2个单元格为问题
				sencond = cellAnswer.getStringCellValue();  //第二列是单选与多选的答案
			}
			tempModel = parseData(value,sencond, tempModel, examTypeEnum);

			// 简答题或者操作题目第2个单元格为答案
			String type = examTypeEnum.getKey(); 
			boolean boor = type.equals(ExamTypeEnum.SHORTS.getKey())
					|| type.equals(ExamTypeEnum.OPERATE.getKey());
			if (boor) {
				HSSFCell cell2 = row.getCell(1);
				tempModel.setAnswer(cell2.getStringCellValue());
			}

			tempModel.setPrototypeQuestion(value); //源题内容
			dataSet.add(tempModel);
		}
		
		return dataSet;
	}

	public static ExamQuestionModel setBean(ExamQuestionModel model) {
		ExamQuestionModel tempModel;
		if(null == model){
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
	public static ExamQuestionModel parseData(String value,String sencond,ExamQuestionModel model, ExamTypeEnum examTypeEnum) {
		String flag = examTypeEnum.getKey(); // 题型

		switch (examTypeEnum) {
		case SINGLE: // 单选题
//			model.setAnswer(getSingleMultiAnswer(value)); // 设置答案
//			model.setQuestion(getSingleMultiQuestion(value, flag)); // 设置问题
			model.setAnswer(sencond); // 设置答案
			model.setQuestion(value); // 设置问题
			model.setScore("1");
			model.setStatus(1);
			break;
		case MULTI: // 多选题
			model.setAnswer(getSingleMultiAnswer(value));
			model.setQuestion(getSingleMultiQuestion(value, flag));
			model.setScore("2");
			model.setStatus(1);
			break;
		case FILL: // 填空题
			String answer = getFillAnswer(value);
			model.setAnswer(answer);
			model.setScore(answer.split(";").length * 0.5 + "");
			model.setQuestion(getFillQuestion(value, answer));
			model.setStatus(1);
			break;
		case SHORTS: // 简答题
			model.setScore(getShortsScore(value));
			model.setQuestion(value); // 问题
			model.setStatus(1);
			break;
		case OPERATE: // 操作题
			model.setScore(getShortsScore(value));
			model.setQuestion(value);
			model.setStatus(1);
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
				value = "0";
			}
		}

		return value;
	}

	/**
	 * 问题解析，将正确答案替换占位体符，在前台可替换文本框
	 * 
	 * @param content
	 * @param point
	 *            占位符
	 * @return
	 */
	public static String getSingleMultiQuestion(String content, String point) {
		content = adjust(content);
		content = content.replaceAll(StrUtil.REG_AZ, "(  " + point + "  )");

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

	/**
	 * 加载模板，写入数据
	 * 
	 * @param templatePath
	 *            模板路径
	 */
	@SuppressWarnings("resource")
	public static void writeDataFromTemplate(String templatePath) {
		FileInputStream fileIn = null;
		FileOutputStream fileOut = null;
		try {
			fileIn = new FileInputStream(templatePath);
			POIFSFileSystem filesystem = new POIFSFileSystem(fileIn);
			HSSFWorkbook workBook = new HSSFWorkbook(filesystem);
			HSSFSheet sheet = workBook.getSheetAt(0);

			HSSFRow row = sheet.createRow(1);
			row.createCell(6).setCellValue("你好，测试");

			fileOut = new FileOutputStream("d:/呵呵.xls");
			workBook.write(fileOut);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				fileIn.close();
				fileOut.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 测试,写数据到excel中
	 * 
	 * @param outputFilename
	 * @throws IOException
	 */
	public static void writeDataXls(String outputFilename) throws IOException {
		int rownum;
		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFSheet s = wb.createSheet();
		HSSFCellStyle cs = wb.createCellStyle();
		HSSFCellStyle cs2 = wb.createCellStyle();
		HSSFCellStyle cs3 = wb.createCellStyle();
		HSSFFont f = wb.createFont();
		HSSFFont f2 = wb.createFont();

		f.setFontHeightInPoints((short) 12);
		f.setColor((short) 0xA);
		f.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		f2.setFontHeightInPoints((short) 10);
		f2.setColor((short) 0xf);
		f2.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		cs.setFont(f);
		cs.setDataFormat(HSSFDataFormat
				.getBuiltinFormat("($#,##0_);[Red]($#,##0)"));
		cs2.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		cs2.setFillPattern((short) 1); // fill w fg
		cs2.setFillForegroundColor((short) 0xA);
		cs2.setFont(f2);
		wb.setSheetName(0, "HSSF Test");
		for (rownum = 0; rownum < 300; rownum++) {
			HSSFRow r = s.createRow(rownum);
			if ((rownum % 2) == 0) {
				r.setHeight((short) 0x249);
			}

			for (int cellnum = 0; cellnum < 50; cellnum += 2) {
				HSSFCell c = r.createCell(cellnum);
				c.setCellValue(rownum
						* 10000
						+ cellnum
						+ (((double) rownum / 1000) + ((double) cellnum / 10000)));
				if ((rownum % 2) == 0) {
					c.setCellStyle(cs);
				}
				c = r.createCell(cellnum + 1);
				c.setCellValue(new HSSFRichTextString("TEST"));
				// 50 characters divided by 1/20th of a point
				s.setColumnWidth(cellnum + 1, (int) (50 * 8 / 0.05));
				if ((rownum % 2) == 0) {
					c.setCellStyle(cs2);
				}
			}
		}

		// draw a thick black border on the row at the bottom using BLANKS
		rownum++;
		rownum++;
		HSSFRow r = s.createRow(rownum);
		cs3.setBorderBottom(HSSFCellStyle.BORDER_THICK);
		for (int cellnum = 0; cellnum < 50; cellnum++) {
			HSSFCell c = r.createCell(cellnum);
			c.setCellStyle(cs3);
		}
		s.addMergedRegion(new CellRangeAddress(0, 3, 0, 3));
		s.addMergedRegion(new CellRangeAddress(100, 110, 100, 110));

		// end draw thick black border
		// create a sheet, set its title then delete it
		s = wb.createSheet();
		wb.setSheetName(1, "DeletedSheet");
		wb.removeSheetAt(1);

		// end deleted sheet
		FileOutputStream out = new FileOutputStream(outputFilename);
		wb.write(out);
		out.close();
	}

	public static void main(String[] args) throws FileNotFoundException {
		InputStream fileIn = new FileInputStream("d:/fill.xls");
		ExamQuestionModel model = new ExamQuestionModel();
		model.setCreateTime(new Date());
		List<ExamQuestionModel> parseList = parseList(fileIn,model , ExamTypeEnum.FILL);
		System.out.println(JSON.toJSONString(parseList));
		/*String content = "就精细天气预报而言,短期预报要(明确灾害性天气落区),短时和临近预报";
		String result = getFillAnswer(content);
		
		System.out.println(result);*/
	}

}
