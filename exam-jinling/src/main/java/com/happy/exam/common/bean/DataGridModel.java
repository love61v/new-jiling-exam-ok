/*
 * Beijing happy Information Technology Co,Ltd.
 * All rights reserved.
 * 
 * <p>DataGridModel.java</p>
 */
package com.happy.exam.common.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * datagrid实体bean
 *
 * @version : Ver 1.0
 * @author : <a href="mailto:h358911056@qq.com">hubo</a>
 * @date : 2015年5月31日 下午8:54:11
 */
public class DataGridModel {

	private Integer pages; // 当前页

	private Integer pageSize; // 每页显示条数

	private String sort; // 排序的字段名

	private String order; // 排序类型 desc , asc

	private Long total; // 总数

	private List<?> rows = new ArrayList(); // 数据

	public DataGridModel() {
		this.pages = 1;
		this.pageSize = 10;
	}

	public Integer getPages() {
		return pages;
	}

	public void setPages(Integer pages) {
		this.pages = pages;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public List<?> getRows() {
		return rows;
	}

	public void setRows(List<?> rows) {
		this.rows = rows;
	}
}
