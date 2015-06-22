package com.happy.exam.common.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * treegrid传输层
 *
 * @version : Ver 1.0
 * @author : <a href="mailto:h358911056@qq.com">hubo</a>
 * @date : 2015年6月1日 下午11:54:41
 */
public class TreegridDto implements Serializable{
 
	private static final long serialVersionUID = 1L;

	private int total; //总数
	
	private String iconCls;  //图标
	
	private String state;  //图标open | closed

	@SuppressWarnings("rawtypes")
	private List<?> rows = new ArrayList(); //数据集合

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public String getIconCls() {
		return iconCls;
	}

	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public List<?> getRows() {
		return rows;
	}

	public void setRows(List<?> rows) {
		this.rows = rows;
	}
}
