package com.happy.exam.common.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * ComboTree下拉菜单树传输层
 *
 * @version : Ver 1.0
 * @author : <a href="mailto:h358911056@qq.com">hubo</a>
 * @date : 2015年6月1日 下午11:54:41
 */
public class ComboTreeDto implements Serializable{
 
	private static final long serialVersionUID = 1L;
	
	private String id;
	
	private String text;
	
	@SuppressWarnings("rawtypes")
	private List<?> children = new ArrayList();

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public List<?> getChildren() {
		return children;
	}

	public void setChildren(List<?> children) {
		this.children = children;
	}
}
