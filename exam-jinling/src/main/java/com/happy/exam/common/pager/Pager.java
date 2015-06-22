package com.happy.exam.common.pager;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 分页实体bean
 *
 * @version : Ver 1.0
 * @author : <a href="mailto:hubo@95190.com">hubo</a>
 * @date : 2015年5月17日 下午8:36:32
 */
public class Pager implements Serializable {
 
	private static final long serialVersionUID = 1L;

	private Integer currentPage;

	private Integer pageSize = 15;

	private Integer totalPages;
	
	private Long  totalCount;

	@SuppressWarnings("rawtypes")
	private List<?> datas = new ArrayList();

	public Pager() {
		this.currentPage = 1;
		this.pageSize = 15;
	}
	
	/**
	 * 
	 * @param currentPage  当前页
	 * @param onePageSize  每页显示条数
	 * @param totalRows    库中总记录数
	 */
	public Pager(int currentPage,int onePageSize,Long totalRows){
		this.setPageSize(onePageSize);
		this.setTotalCount(Long.valueOf(totalRows));
		this.setCurrentPage(currentPage < 0 ? 1 :  currentPage);
		
		if(totalRows == 0){
			this.totalPages = 0;
			this.currentPage = 0;
		} else {
			int rows = totalRows.intValue() / onePageSize;
			if(totalRows % onePageSize == 0){
				this.totalPages =rows;
			}else{
				this.totalPages =rows + 1;
			}
		}
	}

	public Integer getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(Integer totalPages) {
		this.totalPages = totalPages;
	}

	public List<?> getDatas() {
		return datas;
	}

	public void setDatas(List<?> datas) {
		this.datas = datas;
	}

	public Long getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
	}

}
