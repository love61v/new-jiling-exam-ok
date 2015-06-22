package com.happy.exam.model;

public class SystemLog extends BaseModel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     * @备注: 主键ID     
     * @字段:LogId BIGINT(19)  
     */	
	private java.lang.Long logId;

	/**
     * @备注:日志内容     
     * @字段:Content VARCHAR(500)  
     */	
	private java.lang.String content;

	/**
     * @备注:操作人     
     * @字段:CurrentUser VARCHAR(32)  
     */	
	private java.lang.String currentUser;

	/**
     * @备注:ip地址     
     * @字段:Ip VARCHAR(64)  
     */	
	private java.lang.String ip;

	/**
     * @备注:操作     
     * @字段:Operate VARCHAR(64)  
     */	
	private java.lang.String operate;

	/**
     * @备注:备注     
     * @字段:Remark VARCHAR(500)  
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
	
	 
	public SystemLog(){
	}

	public SystemLog(
		java.lang.Long logId
	){
		this.logId = logId;
	}

	
	/**
	 * @param logId  主键ID
	 */
	public void setLogId(java.lang.Long logId) {
		this.logId = logId;
	}
	
	/**
	 * @return  主键ID
	 */
	public java.lang.Long getLogId() {
		return this.logId;
	}
	
	/**
	 * @param content 日志内容
	 */
	public void setContent(java.lang.String content) {
		this.content = content;
	}
	
	/**
	 * @return 日志内容
	 */
	public java.lang.String getContent() {
		return this.content;
	}
	
	/**
	 * @param currentUser 操作人
	 */
	public void setCurrentUser(java.lang.String currentUser) {
		this.currentUser = currentUser;
	}
	
	/**
	 * @return 操作人
	 */
	public java.lang.String getCurrentUser() {
		return this.currentUser;
	}
	
	/**
	 * @param ip ip地址
	 */
	public void setIp(java.lang.String ip) {
		this.ip = ip;
	}
	
	/**
	 * @return ip地址
	 */
	public java.lang.String getIp() {
		return this.ip;
	}
	
	/**
	 * @param operate 操作
	 */
	public void setOperate(java.lang.String operate) {
		this.operate = operate;
	}
	
	/**
	 * @return 操作
	 */
	public java.lang.String getOperate() {
		return this.operate;
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
