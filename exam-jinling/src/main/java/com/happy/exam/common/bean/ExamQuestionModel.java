package com.happy.exam.common.bean;

import java.io.Serializable;

/**
 * 考试题目model
 *
 */
public class ExamQuestionModel implements Serializable {

	private static final long serialVersionUID = 1L;

	private java.lang.Long id;

	private java.lang.String question;

	private java.lang.String answer;

 
	private String score;

	private java.lang.Long typeId;
	
	private String type;

	private Integer status;
	
	private java.lang.String remark;

	private java.lang.Long createUser;

	private java.util.Date createTime;
	 
	private java.util.Date updateTime;

	public java.lang.Long getId() {
		return id;
	}

	public void setId(java.lang.Long id) {
		this.id = id;
	}

	public java.lang.String getQuestion() {
		return question;
	}

	public void setQuestion(java.lang.String question) {
		this.question = question;
	}

	public java.lang.String getAnswer() {
		return answer;
	}

	public void setAnswer(java.lang.String answer) {
		this.answer = answer;
	}

 
	public String getScore() {
		return score;
	}

	public void setScore(String score) {
		this.score = score;
	}

	public java.lang.Long getTypeId() {
		return typeId;
	}

	public void setTypeId(java.lang.Long typeId) {
		this.typeId = typeId;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public java.lang.String getRemark() {
		return remark;
	}

	public void setRemark(java.lang.String remark) {
		this.remark = remark;
	}

	public java.lang.Long getCreateUser() {
		return createUser;
	}

	public void setCreateUser(java.lang.Long createUser) {
		this.createUser = createUser;
	}

	public java.util.Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(java.util.Date createTime) {
		this.createTime = createTime;
	}

	public java.util.Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(java.util.Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
