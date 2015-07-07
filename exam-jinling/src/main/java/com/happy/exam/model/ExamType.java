package com.happy.exam.model;

public class ExamType extends BaseModel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     * @备注:考试分类id     
     * @字段:id BIGINT(19)  
     */	
	private java.lang.Long id;

	/**
     * @备注:考试类型名称 --市级考试--县级考试     
     * @字段:TypeName VARCHAR(64)  
     */	
	private java.lang.String typeName;

	/**
     * @备注:英文名称     
     * @字段:EngName VARCHAR(64)  
     */	
	private java.lang.String engName;

	/**
     * @备注:父类ID     
     * @字段:PId VARCHAR(32)  
     */	
	private java.lang.Long parentId;
	
	/**
	 * 构造树父类ID     
	 */	
	private java.lang.Long _parentId;

	/**
     * @备注:-状态，启用不启用     
     * @字段:Status TINYINT(3)  
     */	
	private Integer status;
	/**
	 * @备注:备注     
	 * @字段:Status TINYINT(3)  
	 */	
	private String remark;
	/**
	 * @备注:排序     
	 * @字段:Status TINYINT(3)  
	 */	
	private Integer sort;

	/**
     * @备注:操作人，创建人     
     * @字段:CreateUser VARCHAR(32)  
     */	
	private java.lang.Long createUser;

	/**
     * @备注:创建时间     
     * @字段:CreateTime DATETIME(19)  
     */	
	private java.util.Date createTime;
	
	 

	/**
     * @备注:最后修改时间     
     * @字段:UpdateTime DATETIME(19)  
     */	
	private java.util.Date updateTime;
	
	 
	public ExamType(){
	}

	public ExamType(
		java.lang.Long id
	){
		this.id = id;
	}

	
	/**
	 * @param id 考试分类id
	 */
	public void setId(java.lang.Long id) {
		this.id = id;
	}
	
	/**
	 * @return 考试分类id
	 */
	public java.lang.Long getId() {
		return this.id;
	}
	
	/**
	 * @param typeName 考试类型名称 --市级考试--县级考试
	 */
	public void setTypeName(java.lang.String typeName) {
		this.typeName = typeName;
	}
	
	/**
	 * @return 考试类型名称 --市级考试--县级考试
	 */
	public java.lang.String getTypeName() {
		return this.typeName;
	}
	
	/**
	 * @param engName 英文名称
	 */
	public void setEngName(java.lang.String engName) {
		this.engName = engName;
	}
	
	/**
	 * @return 英文名称
	 */
	public java.lang.String getEngName() {
		return this.engName;
	}
	
	/**
	 * @param pid 父类ID
	 */
	public void setParentId(java.lang.Long parentId) {
		this.parentId = parentId;
	}
	
	/**
	 * @return 父类ID
	 */
	public java.lang.Long getParentId() {
		return this.parentId;
	}
	
	/**
	 * @param status -状态，启用不启用
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	/**
	 * @return -状态，启用不启用
	 */
	public Integer getStatus() {
		return this.status;
	}
	
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	/**
	 * @param createUser 操作人，创建人
	 */
	public void setCreateUser(java.lang.Long createUser) {
		this.createUser = createUser;
	}
	
	/**
	 * @return 操作人，创建人
	 */
	public java.lang.Long getCreateUser() {
		return this.createUser;
	}
	
	/**
	 * @param createTime 创建时间
	 */
	public void setCreateTime(java.util.Date createTime) {
		this.createTime = createTime;
	}
	
	/**
	 * @return 创建时间
	 */
	public java.util.Date getCreateTime() {
		return this.createTime;
	}
	 
	
	/**
	 * @param updateTime 最后修改时间
	 */
	public void setUpdateTime(java.util.Date updateTime) {
		this.updateTime = updateTime;
	}
	
	/**
	 * @return 最后修改时间
	 */
	public java.util.Date getUpdateTime() {
		return this.updateTime;
	}

	public java.lang.Long get_parentId() {
		return _parentId;
	}

	public void set_parentId(java.lang.Long _parentId) {
		this._parentId = _parentId;
	}
	 
}
