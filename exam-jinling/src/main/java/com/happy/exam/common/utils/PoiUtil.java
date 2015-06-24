package com.happy.exam.common.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

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
	 * 解析各种考试excel数据到list中 从第2行开始
	 * 
	 * @param filepath
	 *            文件路径
	 * @return
	 */
	public static List<ExamQuestionModel> parseList(String filepath,
			ExamQuestionModel model, String type) {
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

			String value = "";
			HSSFCell cell = row.getCell(0); // 第1个单元格为问题
			value = cell.getStringCellValue();
			model = parseData(value, model);

			if (type.equals(ExamTypeEnum.SHORTS.getKey())
					|| type.equals(ExamTypeEnum.OPERATE.getKey())) {// 简答题或者操作题目
				HSSFCell cell2 = row.getCell(1); // 第2个单元格为答案
				model.setAnswer(cell2.getStringCellValue());
			}

			dataSet.add(model);

		}
		return dataSet;

	}

	/**
	 * 解析单元格行数据到实体中
	 * 
	 * @param value
	 *            内容
	 * @param tempModel
	 *            实体(已包含有当前登陆用户,typeid,操作时间的设值)
	 * @return
	 */
	private static ExamQuestionModel parseData(String value,
			ExamQuestionModel tempModel) {
		ExamQuestionModel model = new ExamQuestionModel();
		String type = tempModel.getType(); // 题型

		if (type.equals(ExamTypeEnum.SINGLE.getKey())) {// 单选题
			model.setAnswer(getSingleMultiAnswer(value)); // 设置答案
			model.setQuestion(getSingleMultiQuestion(value, type)); // 设置问题
		}

		if (type.equals(ExamTypeEnum.MULTI.getKey())) {// 多选题
			model.setAnswer(getSingleMultiAnswer(value));
			model.setQuestion(getSingleMultiQuestion(value, type));
		}

		if (type.equals(ExamTypeEnum.FILL.getKey())) {// 填空题
			String answer = getFillAnswer(value);
			model.setAnswer(answer);
			model.setQuestion(getFillQuestion(value, answer));
		}

		if (type.equals(ExamTypeEnum.SHORTS.getKey())) {// 简答题,处理问题与分值
			model.setScore(getShortsScore(value)); // 分值
			model.setQuestion(value); // 问题
		}

		if (type.equals(ExamTypeEnum.OPERATE.getKey())) {// 操作题
			model.setScore(getShortsScore(value)); // 分值
			model.setQuestion(value); // 问题
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
	private static String getShortsScore(String value) {
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
	private static String getSingleMultiQuestion(String content, String point) {
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
	private static String adjust(String content) {
		return StrUtil.replaceBracket(content).replaceAll(StrUtil.REG_S, "");
	}

	/**
	 * 单选题，多选题答案解析
	 * 
	 * @param value
	 * @return
	 */
	private static String getSingleMultiAnswer(String value) {
		value = adjust(value);
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
	 * 填空题答案解析 格式：散槽是(加深(加强))的解析为answer为"加深|加强"
	 * 
	 * @param value
	 * @return
	 */
	private static String getFillAnswer(String value) {
		StringBuffer sbf = new StringBuffer();
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
	 * 填空题答案解析 格式：散槽是(加深(加强))的解析为answer为"加深|加强"
	 * 
	 * @param value
	 * @return
	 */
	private static String getFillQuestion(String txt, String answer) {
		txt = adjust(txt);

		String[] arr = answer.split(";");
		for (int i = 0, len = arr.length; i < len; i++) {
			String temp = arr[i].replace("|", "(");
			txt = txt.replace(temp, ExamTypeEnum.FILL.getKey() + i);
		}

		return txt;

	}

}
