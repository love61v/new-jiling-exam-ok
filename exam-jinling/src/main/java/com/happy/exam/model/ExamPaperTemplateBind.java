package com.happy.exam.model;

public class ExamPaperTemplateBind extends BaseModel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     * @备注:模板主键ID     
     * @字段:TemplateId BIGINT(19)  
     */	
	private java.lang.Long templateId;

	/**
     * @备注:试卷ID     
     * @字段:PaperId BIGINT(19)  
     */	
	private java.lang.Long paperId;

	/**
     * @备注:状态: 默认0 没有参加考试，1 试卷已作废      
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
	
	 
	public ExamPaperTemplateBind(){
	}

	public ExamPaperTemplateBind(
		java.lang.Long templateId
	){
		this.templateId = templateId;
	}

	
	/**
	 * @param templateId 模板主键ID
	 */
	public void setTemplateId(java.lang.Long templateId) {
		this.templateId = templateId;
	}
	
	/**
	 * @return 模板主键ID
	 */
	public java.lang.Long getTemplateId() {
		return this.templateId;
	}
	
	/**
	 * @param paperId 试卷ID
	 */
	public void setPaperId(java.lang.Long paperId) {
		this.paperId = paperId;
	}
	
	/**
	 * @return 试卷ID
	 */
	public java.lang.Long getPaperId() {
		return this.paperId;
	}
	
	/**
	 * @param status 状态: 默认0 没有参加考试，1 试卷已作废 
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	/**
	 * @return 状态: 默认0 没有参加考试，1 试卷已作废 
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
