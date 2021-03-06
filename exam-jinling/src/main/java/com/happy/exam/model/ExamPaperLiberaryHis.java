package com.happy.exam.model;

public class ExamPaperLiberaryHis extends BaseModel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     * @备注:主键ID     
     * @字段:LibHisId BIGINT(19)  
     */	
	private java.lang.Long libHisId;

	/**
     * @备注:题库表ID     
     * @字段:LibId BIGINT(19)  
     */	
	private java.lang.Long libId;

	/**
     * @备注:试题表ID     
     * @字段:PaperId BIGINT(19)  
     */	
	private java.lang.Long paperId;

	/**
     * @备注:题目的ID，是选择题则为选择题的ID，填空题目则为填空填空ID     
     * @字段:ExamId BIGINT(19)  
     */	
	private java.lang.Long examId;

	/**
     * @备注:问题     
     * @字段:Question TEXT(65535)  
     */	
	private java.lang.String question;

	/**
     * @备注:答案： 填空题答案逗号分隔; 选择题完全正确才得分     
     * @字段:Answer TEXT(65535)  
     */	
	private java.lang.String answer;

	/**
     * @备注:分值(存在0.5的值)     
     * @字段:Score DECIMAL(10)  
     */	
	private Long score;

	/**
     * @备注:操作题上传的图片路径，逗号分隔     
     * @字段:ImgSrc VARCHAR(500)  
     */	
	private java.lang.String imgSrc;

	/**
     * @备注:备注     
     * @字段:Remark VARCHAR(255)  
     */	
	private java.lang.String remark;

	/**
     * @备注:创建人ID     
     * @字段:CreatetUser BIGINT(19)  
     */	
	private java.lang.Long createtUser;

	/**
     * @备注:入库时间     
     * @字段:CreateTime DATETIME(19)  
     */	
	private java.util.Date createTime;
	
	 
	public ExamPaperLiberaryHis(){
	}

	public ExamPaperLiberaryHis(
		java.lang.Long libHisId
	){
		this.libHisId = libHisId;
	}

	
	/**
	 * @param libHisId 主键ID
	 */
	public void setLibHisId(java.lang.Long libHisId) {
		this.libHisId = libHisId;
	}
	
	/**
	 * @return 主键ID
	 */
	public java.lang.Long getLibHisId() {
		return this.libHisId;
	}
	
	/**
	 * @param libId 题库表ID
	 */
	public void setLibId(java.lang.Long libId) {
		this.libId = libId;
	}
	
	/**
	 * @return 题库表ID
	 */
	public java.lang.Long getLibId() {
		return this.libId;
	}
	
	/**
	 * @param paperId 试题表ID
	 */
	public void setPaperId(java.lang.Long paperId) {
		this.paperId = paperId;
	}
	
	/**
	 * @return 试题表ID
	 */
	public java.lang.Long getPaperId() {
		return this.paperId;
	}
	
	/**
	 * @param examId 题目的ID，是选择题则为选择题的ID，填空题目则为填空填空ID
	 */
	public void setExamId(java.lang.Long examId) {
		this.examId = examId;
	}
	
	/**
	 * @return 题目的ID，是选择题则为选择题的ID，填空题目则为填空填空ID
	 */
	public java.lang.Long getExamId() {
		return this.examId;
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
	 * @param answer 答案： 填空题答案逗号分隔; 选择题完全正确才得分
	 */
	public void setAnswer(java.lang.String answer) {
		this.answer = answer;
	}
	
	/**
	 * @return 答案： 填空题答案逗号分隔; 选择题完全正确才得分
	 */
	public java.lang.String getAnswer() {
		return this.answer;
	}
	
	/**
	 * @param score 分值(存在0.5的值)
	 */
	public void setScore(Long score) {
		this.score = score;
	}
	
	/**
	 * @return 分值(存在0.5的值)
	 */
	public Long getScore() {
		return this.score;
	}
	
	/**
	 * @param imgSrc 操作题上传的图片路径，逗号分隔
	 */
	public void setImgSrc(java.lang.String imgSrc) {
		this.imgSrc = imgSrc;
	}
	
	/**
	 * @return 操作题上传的图片路径，逗号分隔
	 */
	public java.lang.String getImgSrc() {
		return this.imgSrc;
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
	 * @param createtUser 创建人ID
	 */
	public void setCreatetUser(java.lang.Long createtUser) {
		this.createtUser = createtUser;
	}
	
	/**
	 * @return 创建人ID
	 */
	public java.lang.Long getCreatetUser() {
		return this.createtUser;
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
	 
}
