package com.happy.exam.model;

public class SystemUserGroup extends BaseModel{

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
     * @备注:用户表主键ID     
     * @字段:UserId BIGINT(19)  
     */	
	private java.lang.Long userId;

	/**
     * @备注:组表主键ID     
     * @字段:GroupId BIGINT(19)  
     */	
	private java.lang.Long groupId;
	public SystemUserGroup(){
	}

	public SystemUserGroup(
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
	 * @param userId 用户表主键ID
	 */
	public void setUserId(java.lang.Long userId) {
		this.userId = userId;
	}
	
	/**
	 * @return 用户表主键ID
	 */
	public java.lang.Long getUserId() {
		return this.userId;
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
