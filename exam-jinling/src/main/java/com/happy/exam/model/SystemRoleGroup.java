package com.happy.exam.model;

public class SystemRoleGroup extends BaseModel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     * @备注:主键ID     
     * @字段:Id BIGINT(19)  
     */	
	private java.lang.Long id;

	/**
     * @备注:角色表主键ID     
     * @字段:RoleId BIGINT(19)  
     */	
	private java.lang.Long roleId;

	/**
     * @备注:组表主键ID     
     * @字段:GroupId BIGINT(19)  
     */	
	private java.lang.Long groupId;
	public SystemRoleGroup(){
	}

	public SystemRoleGroup(
		java.lang.Long id
	){
		this.id = id;
	}

	
	/**
	 * @param id 主键ID
	 */
	public void setId(java.lang.Long id) {
		this.id = id;
	}
	
	/**
	 * @return 主键ID
	 */
	public java.lang.Long getId() {
		return this.id;
	}
	
	/**
	 * @param roleId 角色表主键ID
	 */
	public void setRoleId(java.lang.Long roleId) {
		this.roleId = roleId;
	}
	
	/**
	 * @return 角色表主键ID
	 */
	public java.lang.Long getRoleId() {
		return this.roleId;
	}
	
	/**
	 * @param groupId 组表主键ID
	 */
	public void setGroupId(java.lang.Long groupId) {
		this.groupId = groupId;
	}
	
	/**
	 * @return 组表主键ID
	 */
	public java.lang.Long getGroupId() {
		return this.groupId;
	}
}
