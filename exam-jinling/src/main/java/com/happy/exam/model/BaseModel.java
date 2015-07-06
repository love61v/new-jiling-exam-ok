package com.happy.exam.model;

import java.io.Serializable;

/**
 * model基类.
 *
 * @version : Ver 1.0
 * @author : <a href="mailto:hubo@95190.com">hubo</a>
 * @date : 2015年5月17日 下午5:57:04
 */
public class BaseModel implements Serializable {

	private static final long serialVersionUID = 1L;

	/** 排序字段 */
	private String sortColumns;

	/* 非数据库字段: 方便更新时间区间查询用 */
	private String updateTimeBegin;

	private String updateTimeEnd;

	/* 非数据库字段: 方便创建时间区间查询用 */
	private String createTimeBegin;

	private String createTimeEnd;
	
	private Long createUser;

	public String getSortColumns() {
		return sortColumns;
	}

	public void setSortColumns(String sortColumns) {
		this.sortColumns = sortColumns;
	}

	public String getUpdateTimeBegin() {
		return updateTimeBegin;
	}

	public void setUpdateTimeBegin(String updateTimeBegin) {
		this.updateTimeBegin = updateTimeBegin;
	}

	public String getUpdateTimeEnd() {
		return updateTimeEnd;
	}

	public void setUpdateTimeEnd(String updateTimeEnd) {
		this.updateTimeEnd = updateTimeEnd;
	}

	public String getCreateTimeBegin() {
		return createTimeBegin;
	}

	public void setCreateTimeBegin(String createTimeBegin) {
		this.createTimeBegin = createTimeBegin;
	}

	public String getCreateTimeEnd() {
		return createTimeEnd;
	}

	public void setCreateTimeEnd(String createTimeEnd) {
		this.createTimeEnd = createTimeEnd;
	}

	public Long getCreateUser() {
		return createUser;
	}

	public void setCreateUser(Long createUserId) {
		createUser = createUserId;
	}

}
