package com.happy.exam.model;

public class ExamUserTestPaperScore extends BaseModel{

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
     * @备注:测试答案表ID     
     * @字段:TestAnswerId CHAR(10)  
     */	
	private java.lang.String testAnswerId;

	/**
     * @备注:用户ID     
     * @字段:UserId BIGINT(19)  
     */	
	private java.lang.Long userId;

	/**
     * @备注:题目的ID，是选择题则为选择题的ID，填空题目则为填空填空ID     
     * @字段:ExamId BIGINT(19)  
     */	
	private java.lang.Long examId;

	/**
     * @备注:系统标准答案： 填空题答案逗号分隔; 选择题完全正确才得分     
     * @字段:SysAnswer TEXT(65535)  
     */	
	private java.lang.String sysAnswer;

	/**
     * @备注:考生答案： 填空题答案逗号分隔; 选择题完全正确才得分     
     * @字段:UserAnswer TEXT(65535)  
     */	
	private java.lang.String userAnswer;

	/**
     * @备注:操作题上传的图片路径，逗号分隔     
     * @字段:ImgSrc VARCHAR(500)  
     */	
	private java.lang.String imgSrc;

	/**
     * @备注:各小题目得分     
     * @字段:Score DECIMAL(10)  
     */	
	private Long score;
	public ExamUserTestPaperScore(){
	}

	public ExamUserTestPaperScore(
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
	 * @param testAnswerId 测试答案表ID
	 */
	public void setTestAnswerId(java.lang.String testAnswerId) {
		this.testAnswerId = testAnswerId;
	}
	
	/**
	 * @return 测试答案表ID
	 */
	public java.lang.String getTestAnswerId() {
		return this.testAnswerId;
	}
	
	/**
	 * @param userId 用户ID
	 */
	public void setUserId(java.lang.Long userId) {
		this.userId = userId;
	}
	
	/**
	 * @return 用户ID
	 */
	public java.lang.Long getUserId() {
		return this.userId;
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
	 * @param sysAnswer 系统标准答案： 填空题答案逗号分隔; 选择题完全正确才得分
	 */
	public void setSysAnswer(java.lang.String sysAnswer) {
		this.sysAnswer = sysAnswer;
	}
	
	/**
	 * @return 系统标准答案： 填空题答案逗号分隔; 选择题完全正确才得分
	 */
	public java.lang.String getSysAnswer() {
		return this.sysAnswer;
	}
	
	/**
	 * @param userAnswer 考生答案： 填空题答案逗号分隔; 选择题完全正确才得分
	 */
	public void setUserAnswer(java.lang.String userAnswer) {
		this.userAnswer = userAnswer;
	}
	
	/**
	 * @return 考生答案： 填空题答案逗号分隔; 选择题完全正确才得分
	 */
	public java.lang.String getUserAnswer() {
		return this.userAnswer;
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
	 * @param score 各小题目得分
	 */
	public void setScore(Long score) {
		this.score = score;
	}
	
	/**
	 * @return 各小题目得分
	 */
	public Long getScore() {
		return this.score;
	}
}
