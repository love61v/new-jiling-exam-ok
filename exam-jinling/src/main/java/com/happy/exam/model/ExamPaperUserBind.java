package com.happy.exam.model;

public class ExamPaperUserBind extends BaseModel{

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
     * @备注:试卷与考试绑定表主键ID     
     * @字段:TemplateId BIGINT(19)  
     */	
	private java.lang.Long templateId;

	/**
     * @备注:考生登录ID     
     * @字段:UserId BIGINT(19)  
     */	
	private java.lang.Long userId;

	/**
     * @备注:考生用户名     
     * @字段:UserName VARCHAR(64)  
     */	
	private java.lang.String userName;

	/**
     * @备注:考试时间区间: 正式考试则为空，自测考试如果前台设置以此为开始时间为准     
     * @字段:beginTime DATETIME(19)  
     */	
	private java.util.Date beginTime;
	
	 

	/**
     * @备注:考试时间区间: 正式考试则为空，自测考试如果前台设置以此为结束时间     
     * @字段:endTime DATETIME(19)  
     */	
	private java.util.Date endTime;
	
	 

	/**
     * @备注:考试次数: 默认1次, 正式考试为1次， 自测考试为N（根据设置）次     
     * @字段:ExamNumbers INT(10)  
     */	
	private java.lang.Integer examNumbers;

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
	
	 
	public ExamPaperUserBind(){
	}

	public ExamPaperUserBind(
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
	 * @param templateId 试卷与考试绑定表主键ID
	 */
	public void setTemplateId(java.lang.Long templateId) {
		this.templateId = templateId;
	}
	
	/**
	 * @return 试卷与考试绑定表主键ID
	 */
	public java.lang.Long getTemplateId() {
		return this.templateId;
	}
	
	/**
	 * @param userId 考生登录ID
	 */
	public void setUserId(java.lang.Long userId) {
		this.userId = userId;
	}
	
	/**
	 * @return 考生登录ID
	 */
	public java.lang.Long getUserId() {
		return this.userId;
	}
	
	/**
	 * @param userName 考生用户名
	 */
	public void setUserName(java.lang.String userName) {
		this.userName = userName;
	}
	
	/**
	 * @return 考生用户名
	 */
	public java.lang.String getUserName() {
		return this.userName;
	}
	
	/**
	 * @param beginTime 考试时间区间: 正式考试则为空，自测考试如果前台设置以此为开始时间为准
	 */
	public void setBeginTime(java.util.Date beginTime) {
		this.beginTime = beginTime;
	}
	
	/**
	 * @return 考试时间区间: 正式考试则为空，自测考试如果前台设置以此为开始时间为准
	 */
	public java.util.Date getBeginTime() {
		return this.beginTime;
	}
	 
	
	/**
	 * @param endTime 考试时间区间: 正式考试则为空，自测考试如果前台设置以此为结束时间
	 */
	public void setEndTime(java.util.Date endTime) {
		this.endTime = endTime;
	}
	
	/**
	 * @return 考试时间区间: 正式考试则为空，自测考试如果前台设置以此为结束时间
	 */
	public java.util.Date getEndTime() {
		return this.endTime;
	}
	 
	
	/**
	 * @param examNumbers 考试次数: 默认1次, 正式考试为1次， 自测考试为N（根据设置）次
	 */
	public void setExamNumbers(java.lang.Integer examNumbers) {
		this.examNumbers = examNumbers;
	}
	
	/**
	 * @return 考试次数: 默认1次, 正式考试为1次， 自测考试为N（根据设置）次
	 */
	public java.lang.Integer getExamNumbers() {
		return this.examNumbers;
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
