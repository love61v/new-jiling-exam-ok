package com.happy.exam.model;

public class SystemRole extends BaseModel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/*构造字段： 用户角色查询是用或者映射其它表的主键别名*/
	private java.lang.Long id;

	/**
     * @备注:角色主键ID     
     * @字段:RoleId BIGINT(19)  
     */	
	private java.lang.Long roleId;

	/**
     * @备注:角色名称     
     * @字段:RoleName VARCHAR(64)  
     */	
	private java.lang.String roleName;

	/**
     * @备注:英文名称     
     * @字段:EngName VARCHAR(64)  
     */	
	private java.lang.String engName;

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
	
	 
	public SystemRole(){
	}

	public SystemRole(
		java.lang.Long roleId
	){
		this.roleId = roleId;
	}

	
	/**
	 * @param roleId 角色主键ID
	 */
	public void setRoleId(java.lang.Long roleId) {
		this.roleId = roleId;
	}
	
	/**
	 * @return 角色主键ID
	 */
	public java.lang.Long getRoleId() {
		return this.roleId;
	}
	
	/**
	 * @param roleName 角色名称
	 */
	public void setRoleName(java.lang.String roleName) {
		this.roleName = roleName;
	}
	
	/**
	 * @return 角色名称
	 */
	public java.lang.String getRoleName() {
		return this.roleName;
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

	public java.lang.Long getId() {
		return id;
	}

	public void setId(java.lang.Long id) {
		this.id = id;
	}
	 
}
