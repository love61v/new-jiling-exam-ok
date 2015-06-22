/*
 * Beijing happy Information Technology Co,Ltd.
 * All rights reserved.
 * 
 * <p>DatagridDto.java</p>
 */
package com.happy.exam.common.dto;

import java.io.Serializable;

/**
 * datagrid分页时post参数
 *
 * @version : Ver 1.0
 * @author : <a href="mailto:h358911056@qq.com">hubo</a>
 * @date : 2015年6月1日 下午11:54:41
 */
public class DatagridDto implements Serializable{

	private int page; //当前页

	private int rows; //每页显示条数

	public DatagridDto() {
		this.page = 1;
		this.rows = 15;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}
}
