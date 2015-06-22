package com.happy.exam.model;

public class SystemMessage extends BaseModel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     * @备注: 字典主键ID     
     * @字段:MsgId BIGINT(19)  
     */	
	private java.lang.Long msgId;

	/**
     * @备注:名称     
     * @字段:Message TEXT(65535)  
     */	
	private java.lang.String message;

	/**
     * @备注:消息接收者ID,逗号分隔， 默认 -1表示所有用户都可接收到     
     * @字段:ReciveUsers VARCHAR(2000)  
     */	
	private java.lang.String reciveUsers;

	/**
     * @备注:备注     
     * @字段:Remark VARCHAR(500)  
     */	
	private java.lang.String remark;

	/**
     * @备注:发布人     
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
	
	 
	public SystemMessage(){
	}

	public SystemMessage(
		java.lang.Long msgId
	){
		this.msgId = msgId;
	}

	
	/**
	 * @param msgId  字典主键ID
	 */
	public void setMsgId(java.lang.Long msgId) {
		this.msgId = msgId;
	}
	
	/**
	 * @return  字典主键ID
	 */
	public java.lang.Long getMsgId() {
		return this.msgId;
	}
	
	/**
	 * @param message 名称
	 */
	public void setMessage(java.lang.String message) {
		this.message = message;
	}
	
	/**
	 * @return 名称
	 */
	public java.lang.String getMessage() {
		return this.message;
	}
	
	/**
	 * @param reciveUsers 消息接收者ID,逗号分隔， 默认 -1表示所有用户都可接收到
	 */
	public void setReciveUsers(java.lang.String reciveUsers) {
		this.reciveUsers = reciveUsers;
	}
	
	/**
	 * @return 消息接收者ID,逗号分隔， 默认 -1表示所有用户都可接收到
	 */
	public java.lang.String getReciveUsers() {
		return this.reciveUsers;
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
	 * @param createUser 发布人
	 */
	public void setCreateUser(java.lang.Long createUser) {
		this.createUser = createUser;
	}
	
	/**
	 * @return 发布人
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
