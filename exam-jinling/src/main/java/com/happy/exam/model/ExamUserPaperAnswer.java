package com.happy.exam.model;

public class ExamUserPaperAnswer extends BaseModel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     * @备注:主键ID     
     * @字段:AnswerId BIGINT(19)  
     */	
	private java.lang.Long answerId;

	/**
     * @备注:正式题ID     
     * @字段:LibId CHAR(10)  
     */	
	private java.lang.String libId;

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
     * @字段:Answer TEXT(65535)  
     */	
	private java.lang.String answer;

	/**
     * @备注:操作题上传的图片路径，逗号分隔     
     * @字段:ImgSrc VARCHAR(500)  
     */	
	private java.lang.String imgSrc;
	public ExamUserPaperAnswer(){
	}

	public ExamUserPaperAnswer(
		java.lang.Long answerId
	){
		this.answerId = answerId;
	}

	
	/**
	 * @param answerId 主键ID
	 */
	public void setAnswerId(java.lang.Long answerId) {
		this.answerId = answerId;
	}
	
	/**
	 * @return 主键ID
	 */
	public java.lang.Long getAnswerId() {
		return this.answerId;
	}
	
	/**
	 * @param libId 正式题ID
	 */
	public void setLibId(java.lang.String libId) {
		this.libId = libId;
	}
	
	/**
	 * @return 正式题ID
	 */
	public java.lang.String getLibId() {
		return this.libId;
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
