package com.happy.exam.model;

public class SystemGroup extends BaseModel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     * @备注: 主键ID     
     * @字段:GroupId BIGINT(19)  
     */	
	private java.lang.Long groupId;

	/**
     * @备注:组名称     
     * @字段:GroupName VARCHAR(64)  
     */	
	private java.lang.String groupName;

	/**
     * @备注:英文名称     
     * @字段:EngName VARCHAR(64)  
     */	
	private java.lang.String engName;

	/**
     * @备注:父组ID     
     * @字段:ParentId BIGINT(19)  
     */	
	private java.lang.Long parentId;
	
	private java.lang.Long _parentId;

	/**
     * @备注:状态:  1应用, 2禁用     
     * @字段:Status TINYINT(3)  
     */	
	private Integer status;

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
	
	 
	public SystemGroup(){
	}

	public SystemGroup(
		java.lang.Long groupId
	){
		this.groupId = groupId;
	}

	
	/**
	 * @param groupId  主键ID
	 */
	public void setGroupId(java.lang.Long groupId) {
		this.groupId = groupId;
	}
	
	/**
	 * @return  主键ID
	 */
	public java.lang.Long getGroupId() {
		return this.groupId;
	}
	
	/**
	 * @param groupName 组名称
	 */
	public void setGroupName(java.lang.String groupName) {
		this.groupName = groupName;
	}
	
	/**
	 * @return 组名称
	 */
	public java.lang.String getGroupName() {
		return this.groupName;
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
	 * @param parentId 父组ID
	 */
	public void setParentId(java.lang.Long parentId) {
		this.parentId = parentId;
	}
	
	/**
	 * @return 父组ID
	 */
	public java.lang.Long getParentId() {
		return this.parentId;
	}
	
	/**
	 * @param status 状态:  1应用, 2禁用
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	/**
	 * @return 状态:  1应用, 2禁用
	 */
	public Integer getStatus() {
		return this.status;
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

	public java.lang.Long get_parentId() {
		return _parentId;
	}

	public void set_parentId(java.lang.Long _parentId) {
		this._parentId = _parentId;
	}
}
