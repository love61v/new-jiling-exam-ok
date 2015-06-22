package com.happy.exam.model;

public class ExamPaperSetting extends BaseModel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     * @备注:试题主键ID     
     * @字段:paperId BIGINT(19)  
     */	
	private java.lang.Long paperId;

	/**
     * @备注:标题     
     * @字段:Title VARCHAR(255)  
     */	
	private java.lang.String title;

	/**
     * @备注:副标题     
     * @字段:SubTitle VARCHAR(255)  
     */	
	private java.lang.String subTitle;

	/**
     * @备注:时长:  单位秒     
     * @字段:duration INT(10)  
     */	
	private java.lang.Integer duration;

	/**
     * @备注:总分     
     * @字段:TotalScore DECIMAL(10)  
     */	
	private Long totalScore;

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
	
	 

	/**
     * @备注:考试类型： 1正式考试，2自测考试     
     * @字段:Type TINYINT(3)  
     */	
	private Integer type;
	public ExamPaperSetting(){
	}

	public ExamPaperSetting(
		java.lang.Long paperId
	){
		this.paperId = paperId;
	}

	
	/**
	 * @param paperId 试题主键ID
	 */
	public void setPaperId(java.lang.Long paperId) {
		this.paperId = paperId;
	}
	
	/**
	 * @return 试题主键ID
	 */
	public java.lang.Long getPaperId() {
		return this.paperId;
	}
	
	/**
	 * @param title 标题
	 */
	public void setTitle(java.lang.String title) {
		this.title = title;
	}
	
	/**
	 * @return 标题
	 */
	public java.lang.String getTitle() {
		return this.title;
	}
	
	/**
	 * @param subTitle 副标题
	 */
	public void setSubTitle(java.lang.String subTitle) {
		this.subTitle = subTitle;
	}
	
	/**
	 * @return 副标题
	 */
	public java.lang.String getSubTitle() {
		return this.subTitle;
	}
	
	/**
	 * @param duration 时长:  单位秒
	 */
	public void setDuration(java.lang.Integer duration) {
		this.duration = duration;
	}
	
	/**
	 * @return 时长:  单位秒
	 */
	public java.lang.Integer getDuration() {
		return this.duration;
	}
	
	/**
	 * @param totalScore 总分
	 */
	public void setTotalScore(Long totalScore) {
		this.totalScore = totalScore;
	}
	
	/**
	 * @return 总分
	 */
	public Long getTotalScore() {
		return this.totalScore;
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
	 
	
	/**
	 * @param type 考试类型： 1正式考试，2自测考试
	 */
	public void setType(Integer type) {
		this.type = type;
	}
	
	/**
	 * @return 考试类型： 1正式考试，2自测考试
	 */
	public Integer getType() {
		return this.type;
	}
}
