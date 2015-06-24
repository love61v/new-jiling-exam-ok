package com.happy.exam.model;

public class ExamOperationQuestion extends BaseModel{

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
     * @备注:问题     
     * @字段:Question TEXT(65535)  
     */	
	private java.lang.String question;

	/**
     * @备注:答案     
     * @字段:Answer VARCHAR(255)  
     */	
	private java.lang.String answer;

	/**
     * @备注:分值(存在0.5的值)     
     * @字段:Score DECIMAL(10)  
     */	
	private String score;

	/**
     * @备注:图片路径     
     * @字段:ImgSrc VARCHAR(500)  
     */	
	private java.lang.String imgSrc;

	/**
     * @备注:考试分类表ID     
     * @字段:TypeId BIGINT(19)  
     */	
	private java.lang.Long typeId;

	/**
     * @备注:状态: 1应用;2禁用;3已删除     
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
	
	 
	public ExamOperationQuestion(){
	}

	public ExamOperationQuestion(
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
	 * @param question 问题
	 */
	public void setQuestion(java.lang.String question) {
		this.question = question;
	}
	
	/**
	 * @return 问题
	 */
	public java.lang.String getQuestion() {
		return this.question;
	}
	
	/**
	 * @param answer 答案
	 */
	public void setAnswer(java.lang.String answer) {
		this.answer = answer;
	}
	
	/**
	 * @return 答案
	 */
	public java.lang.String getAnswer() {
		return this.answer;
	}
	
	/**
	 * @param score 分值(存在0.5的值)
	 */
	public void setScore(String score) {
		this.score = score;
	}
	
	/**
	 * @return 分值(存在0.5的值)
	 */
	public String getScore() {
		return this.score;
	}
	
	/**
	 * @param imgSrc 图片路径
	 */
	public void setImgSrc(java.lang.String imgSrc) {
		this.imgSrc = imgSrc;
	}
	
	/**
	 * @return 图片路径
	 */
	public java.lang.String getImgSrc() {
		return this.imgSrc;
	}
	
	/**
	 * @param typeId 考试分类表ID
	 */
	public void setTypeId(java.lang.Long typeId) {
		this.typeId = typeId;
	}
	
	/**
	 * @return 考试分类表ID
	 */
	public java.lang.Long getTypeId() {
		return this.typeId;
	}
	
	/**
	 * @param status 状态: 1应用;2禁用;3已删除
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	/**
	 * @return 状态: 1应用;2禁用;3已删除
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
