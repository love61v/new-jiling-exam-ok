package com.happy.exam.model;

public class Department extends BaseModel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     * @备注:部门主键ID     
     * @字段:DeptId BIGINT(19)  
     */	
	private java.lang.Long deptId;

	/**
     * @备注:名称     
     * @字段:DeptName VARCHAR(64)  
     */	
	private java.lang.String deptName;

	/**
     * @备注:父ID     
     * @字段:ParentId BIGINT(19)  
     */	
	private java.lang.Long parentId;
	
	/**
     * @备注:非数据库字段，查询时使用     
     */	
	private java.lang.Long _parentId;

	/**
     * @备注:区域编码     
     * @字段:AreaCode VARCHAR(64)  
     */	
	private java.lang.String areaCode;
	public Department(){
	}

	public Department(
		java.lang.Long deptId
	){
		this.deptId = deptId;
	}

	
	/**
	 * @param deptId 部门主键ID
	 */
	public void setDeptId(java.lang.Long deptId) {
		this.deptId = deptId;
	}
	
	/**
	 * @return 部门主键ID
	 */
	public java.lang.Long getDeptId() {
		return this.deptId;
	}
	
	/**
	 * @param deptName 名称
	 */
	public void setDeptName(java.lang.String deptName) {
		this.deptName = deptName;
	}
	
	/**
	 * @return 名称
	 */
	public java.lang.String getDeptName() {
		return this.deptName;
	}
	
	/**
	 * @param parentId 父ID
	 */
	public void setParentId(java.lang.Long parentId) {
		this.parentId = parentId;
	}
	
	/**
	 * @return 父ID
	 */
	public java.lang.Long getParentId() {
		return this.parentId;
	}
	
	/**
	 * @param areaCode 区域编码
	 */
	public void setAreaCode(java.lang.String areaCode) {
		this.areaCode = areaCode;
	}
	
	/**
	 * @return 区域编码
	 */
	public java.lang.String getAreaCode() {
		return this.areaCode;
	}

	public java.lang.Long get_parentId() {
		return _parentId;
	}

	public void set_parentId(java.lang.Long _parentId) {
		this._parentId = _parentId;
	}
	
	
}
