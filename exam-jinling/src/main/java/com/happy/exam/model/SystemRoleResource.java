package com.happy.exam.model;

public class SystemRoleResource extends BaseModel{

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
     * @备注:角色表主键ID     
     * @字段:RoleId BIGINT(19)  
     */	
	private java.lang.Long roleId;

	/**
     * @备注:资源表主键ID     
     * @字段:ResourceId BIGINT(19)  
     */	
	private java.lang.Long resourceId;

	/**
     * @备注:操作ID串,逗号分隔     
     * @字段:OperateId VARCHAR(500)  
     */	
	private java.lang.String operateId;
	public SystemRoleResource(){
	}

	public SystemRoleResource(
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
	 * @param resourceId 资源表主键ID
	 */
	public void setResourceId(java.lang.Long resourceId) {
		this.resourceId = resourceId;
	}
	
	/**
	 * @return 资源表主键ID
	 */
	public java.lang.Long getResourceId() {
		return this.resourceId;
	}
	
	/**
	 * @param operateId 操作ID串,逗号分隔
	 */
	public void setOperateId(java.lang.String operateId) {
		this.operateId = operateId;
	}
	
	/**
	 * @return 操作ID串,逗号分隔
	 */
	public java.lang.String getOperateId() {
		return this.operateId;
	}
}
