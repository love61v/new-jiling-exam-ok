package com.happy.exam.common.enums;

/**
 * 考试题型枚举类
 *
 * @version : Ver 1.0
 * @author : <a href="mailto:hubo@95190.com">hubo</a>
 * @date : 2015年5月17日 下午5:23:15
 */
public enum ExamTypeEnum {

	SINGLE("@SINGLE@","单选题"),
	
	MULTI("@MULTI@","多选题"),
	
	FILL("@FILL@","填空题"),
	
	SHORTS("@SHORTS@","简答题"),
	
	OPERATE("@OPERATE@","操作题");

	private String key; //标识占位符，前台用它替换为input框
	 
	private String remark; //备注

	private ExamTypeEnum(String key,String remark) {
		this.key = key;
		this.remark = remark;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
}
