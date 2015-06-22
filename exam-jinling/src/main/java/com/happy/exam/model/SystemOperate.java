package com.happy.exam.model;

public class SystemOperate extends BaseModel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     * @备注:操作主键ID     
     * @字段:OperateId BIGINT(19)  
     */	
	private java.lang.Long operateId;

	/**
     * @备注:操作名称     
     * @字段:OperateName VARCHAR(64)  
     */	
	private java.lang.String operateName;

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
	
	 
	public SystemOperate(){
	}

	public SystemOperate(
		java.lang.Long operateId
	){
		this.operateId = operateId;
	}

	
	/**
	 * @param operateId 操作主键ID
	 */
	public void setOperateId(java.lang.Long operateId) {
		this.operateId = operateId;
	}
	
	/**
	 * @return 操作主键ID
	 */
	public java.lang.Long getOperateId() {
		return this.operateId;
	}
	
	/**
	 * @param operateName 操作名称
	 */
	public void setOperateName(java.lang.String operateName) {
		this.operateName = operateName;
	}
	
	/**
	 * @return 操作名称
	 */
	public java.lang.String getOperateName() {
		return this.operateName;
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
	 
}
