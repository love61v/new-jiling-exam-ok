package com.happy.exam.common.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

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

	// static/upload/singlechoice.xls

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
	 * 解析各种考试excel数据到list中 从第2行开始
	 * 
	 * @param filepath
	 *            文件路径
	 * @return
	 */
	public static List<ExamQuestionModel> parseList(String filepath,
			ExamQuestionModel model) {
		List<ExamQuestionModel> dataSet = new ArrayList<ExamQuestionModel>();

		HSSFWorkbook workbook = readFile(filepath);
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

			int cells = row.getPhysicalNumberOfCells();// 列
			for (int c = 0; c < cells; c++) {
				HSSFCell cell = row.getCell(c); // 单元格
				String value = "";

				switch (cell.getCellType()) {
				case HSSFCell.CELL_TYPE_FORMULA: // 公式
					value = cell.getCellFormula();
					break;
				case HSSFCell.CELL_TYPE_NUMERIC: // 数字
					value = cell.getNumericCellValue() + "";
					break;

				case HSSFCell.CELL_TYPE_STRING: // 字符
					value = cell.getStringCellValue();
					ExamQuestionModel data = parseData(value, model);

					dataSet.add(data);
					break;

				default:
					break;
				}

			}
		}
		return dataSet;

	}

	/**
	 * 解析单元格行数据到实体中
	 * @param value     内容
	 * @param tempModel 实体(已包含有当前登陆用户,typeid,操作时间的设值)
	 * @return
	 */
	private static ExamQuestionModel parseData(String value,ExamQuestionModel tempModel) {
		ExamQuestionModel model = new ExamQuestionModel();
		String type = tempModel.getType(); // 题型

		if (type.equals(ExamTypeEnum.SINGLE.getKey())) {// 单选题
			model.setAnswer(getSingleMultiAnswer(value)); // 设置答案
			model.setQuestion(getSingleMultiQuestion(value, type)); // 设置问题
		}

		if (type.equals(ExamTypeEnum.MULTI.getKey())) {//多选题
			model.setAnswer(getSingleMultiAnswer(value));
			model.setQuestion(getSingleMultiQuestion(value, type)); 
		}

		if (type.equals(ExamTypeEnum.FILL.getKey())) {//填空题

		}

		if (type.equals(ExamTypeEnum.SHORTS.getKey())) {//简答题

		}

		if (type.equals(ExamTypeEnum.OPERATE.getKey())) {//操作题

		}

		return model;
	}

	/**
	 * 问题解析，将正确答案替换占位体符，在前台可替换文本框
	 * 
	 * @param content
	 * @param point
	 *            占位符
	 * @return
	 */
	private static String getSingleMultiQuestion(String content, String point) {
		content = StrUtil.replaceBracket(content).replaceAll(StrUtil.REG_S, "");
		content = content.replaceAll(StrUtil.REG_AZ, "(  " + point + "  )");

		return content;
	}

	/**
	 * 单选题，多选题答案解析
	 * 
	 * @param value
	 * @return
	 */
	private static String getSingleMultiAnswer(String value) {
		value = StrUtil.replaceBracket(value).replaceAll(StrUtil.REG_S, "");;

		Pattern p = Pattern.compile(StrUtil.REG_AZ);
		Matcher matcher = p.matcher(value);
		if (matcher.find()) {
			value = StrUtil.replaceBracketNull(matcher.group());
			char[] charArray = value.toUpperCase().toCharArray();
			Arrays.sort(charArray);
			for (char c : charArray) {
				value.concat(String.valueOf(c));
			}

		}
		return value.toUpperCase();
	}
	
	/**
	 * 填空题答案解析
	 * 
	 * @param value
	 * @return
	 */
	private static String getFillAnswer(String value) {
		value = StrUtil.replaceBracket(value).replaceAll(StrUtil.REG_S, "");

		Pattern p = Pattern.compile(StrUtil.REG_More);
		Matcher matcher = p.matcher(value);
		StringBuffer sbf = new StringBuffer();
		while (matcher.find()) {
			value = StrUtil.replaceBracketNull(matcher.group());
			sbf.append(value);
			sbf.append(";");

		}
		return sbf.toString();
	}

	public static void main(String[] args) {
		/*
		 * String regex = "\\([A-Za-z]+\\)"; String str =
		 * "根据《中华人民共和国气象法》，\n在2007年6月12日中国气象局第16号令《突发气象灾害预警信号发布于传播办法》中，规定发布预警信号的气象灾害分为台风、暴雨等总共（  D  ）类。"
		 * ; str = StrUtil.replaceBracket(str).replaceAll("/\\s+|\\s|/g/", "");
		 * str = str.replaceAll(regex, "( @@ )");
		 * 
		 * System.out.println(str);
		 */

		/*String filepath = "d:/singlechoice.xls";
		ExamQuestionModel model = new ExamQuestionModel();
		model.setType(ExamTypeEnum.SINGLE.getKey());
		List<ExamQuestionModel> parseList = parseList(filepath, model);
		
		System.out.println(JSON.toJSONString(parseList));*/
		
		
		String str = "对称性的槽没有发展，疏散槽是（加深（加强））的，汇合槽是（填塞（减弱））的；槽前疏散，槽后汇合，则槽（移动迅速）；当高度槽落后于冷舌时，槽将（减弱）。";
		 
		str =getFillAnswer(str);
		
		System.out.println(str);
	}
}
