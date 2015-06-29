package com.happy.exam.model;

public class SystemUserRole extends BaseModel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     * @备注:     
     * @字段:Id BIGINT(19)  
     */	
	private java.lang.Long id;

	/**
     * @备注:用户ID     
     * @字段:UserId BIGINT(19)  
     */	
	private java.lang.Long userId;

	/**
     * @备注:     
     * @字段:RoleId BIGINT(19)  
     */	
	private java.lang.Long roleId;
	public SystemUserRole(){
	}

	public SystemUserRole(
		java.lang.Long id
	){
		this.id = id;
	}

	
	/**
	 * @param id 
	 */
	public void setId(java.lang.Long id) {
		this.id = id;
	}
	
	/**
	 * @return 
	 */
	public java.lang.Long getId() {
		return this.id;
	}
	
	/**
	 * @param userId 用户ID
	 */
	public void setUserId(java.lang.Long userId) {
		this.userId = userId;
	}
	
	/**
	 * @return 用户ID
	 */
	public java.lang.Long getUserId() {
		return this.userId;
	}
	
	/**
	 * @param roleId 
	 */
	public void setRoleId(java.lang.Long roleId) {
		this.roleId = roleId;
	}
	
	/**
	 * @return 
	 */
	public java.lang.Long getRoleId() {
		return this.roleId;
	}
}
