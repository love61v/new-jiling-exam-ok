package com.happy.exam.model;

public class ExamType extends BaseModel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     * @备注:角色主键ID     
     * @字段:Id BIGINT(19)  
     */	
	private java.lang.Long id;

	/**
     * @备注:考试类型名称： 市级考试; 县级考试     
     * @字段:TypeName VARCHAR(64)  
     */	
	private java.lang.String typeName;

	/**
     * @备注:英文名称     
     * @字段:EngName VARCHAR(64)  
     */	
	private java.lang.String engName;

	/**
     * @备注:父ID     
     * @字段:ParentId BIGINT(19)  
     */	
	private java.lang.Long parentId;

	/**
     * @备注:状态: 1应用;2禁用;3已删除     
     * @字段:Status TINYINT(3)  
     */	
	private Integer status;

	/**
     * @备注:排序     
     * @字段:Sort INT(10)  
     */	
	private java.lang.Integer sort;

	/**
     * @备注:备注     
     * @字段:Remark VARCHAR(255)  
     */	
	private java.lang.String remark;

	/**
     * @备注:创建人     
     * @字段:CreateUser BIGINT(19)  
     */	
	private java.lang.Long createUser;

	/**
     * @备注:入库时间     
     * @字段:CreateTime DATETIME(19)  
     */	
	private java.util.Date createTime;
	
	 

	/**
     * @备注:变更时间     
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
	 * @param id 角色主键ID
	 */
	public void setId(java.lang.Long id) {
		this.id = id;
	}
	
	/**
	 * @return 角色主键ID
	 */
	public java.lang.Long getId() {
		return this.id;
	}
	
	/**
	 * @param typeName 考试类型名称： 市级考试; 县级考试
	 */
	public void setTypeName(java.lang.String typeName) {
		this.typeName = typeName;
	}
	
	/**
	 * @return 考试类型名称： 市级考试; 县级考试
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
	 * @param status 状态: 1应用;2禁用;3已删除
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	/**
	 * @return 状态: 1应用;2禁用;3已删除
	 */
	public Integer getStatus() {
		return this.status;
	}
	
	/**
	 * @param sort 排序
	 */
	public void setSort(java.lang.Integer sort) {
		this.sort = sort;
	}
	
	/**
	 * @return 排序
	 */
	public java.lang.Integer getSort() {
		return this.sort;
	}
	
	/**
	 * @param remark 备注
	 */
	public void setRemark(java.lang.String remark) {
		this.remark = remark;
	}
	
	/**
	 * @return 备注
	 */
	public java.lang.String getRemark() {
		return this.remark;
	}
	
	/**
	 * @param createUser 创建人
	 */
	public void setCreateUser(java.lang.Long createUser) {
		this.createUser = createUser;
	}
	
	/**
	 * @return 创建人
	 */
	public java.lang.Long getCreateUser() {
		return this.createUser;
	}
	
	/**
	 * @param createTime 入库时间
	 */
	public void setCreateTime(java.util.Date createTime) {
		this.createTime = createTime;
	}
	
	/**
	 * @return 入库时间
	 */
	public java.util.Date getCreateTime() {
		return this.createTime;
	}
	 
	
	/**
	 * @param updateTime 变更时间
	 */
	public void setUpdateTime(java.util.Date updateTime) {
		this.updateTime = updateTime;
	}
	
	/**
	 * @return 变更时间
	 */
	public java.util.Date getUpdateTime() {
		return this.updateTime;
	}
	 
}
