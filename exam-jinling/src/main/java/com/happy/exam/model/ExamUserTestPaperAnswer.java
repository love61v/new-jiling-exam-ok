package com.happy.exam.model;

public class ExamUserTestPaperAnswer extends BaseModel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     * @备注:主键ID     
     * @字段:TestAnswerId BIGINT(19)  
     */	
	private java.lang.Long testAnswerId;

	/**
     * @备注:测试题ID     
     * @字段:TestLibId CHAR(10)  
     */	
	private java.lang.String testLibId;

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
     * @备注:答案： 填空题答案逗号分隔; 选择题完全正确才得分     
     * @字段:UserAnswer TEXT(65535)  
     */	
	private java.lang.String userAnswer;

	/**
     * @备注:操作题上传的图片路径，逗号分隔     
     * @字段:ImgSrc VARCHAR(500)  
     */	
	private java.lang.String imgSrc;
	public ExamUserTestPaperAnswer(){
	}

	public ExamUserTestPaperAnswer(
		java.lang.Long testAnswerId
	){
		this.testAnswerId = testAnswerId;
	}

	
	/**
	 * @param testAnswerId 主键ID
	 */
	public void setTestAnswerId(java.lang.Long testAnswerId) {
		this.testAnswerId = testAnswerId;
	}
	
	/**
	 * @return 主键ID
	 */
	public java.lang.Long getTestAnswerId() {
		return this.testAnswerId;
	}
	
	/**
	 * @param testLibId 测试题ID
	 */
	public void setTestLibId(java.lang.String testLibId) {
		this.testLibId = testLibId;
	}
	
	/**
	 * @return 测试题ID
	 */
	public java.lang.String getTestLibId() {
		return this.testLibId;
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
	 * @param userAnswer 答案： 填空题答案逗号分隔; 选择题完全正确才得分
	 */
	public void setUserAnswer(java.lang.String userAnswer) {
		this.userAnswer = userAnswer;
	}
	
	/**
	 * @return 答案： 填空题答案逗号分隔; 选择题完全正确才得分
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
}
