package com.happy.exam.model;

public class ExamScore extends BaseModel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     * @备注:角色主键ID     
     * @字段:Id BIGINT(19)  
     */	
	private java.lang.Long id;

	/**
     * @备注:用户ID     
     * @字段:UserId BIGINT(19)  
     */	
	private java.lang.Long userId;

	/**
     * @备注:用户姓名     
     * @字段:UserName VARCHAR(64)  
     */	
	private java.lang.String userName;

	/**
     * @备注:预报员编号     
     * @字段:ForcastNumber VARCHAR(32)  
     */	
	private java.lang.String forcastNumber;

	/**
     * @备注:成绩     
     * @字段:TotalScore DECIMAL(10)  
     */	
	private Long totalScore;
	public ExamScore(){
	}

	public ExamScore(
		java.lang.Long id
	){
		this.id = id;
	}

	
	/**
	 * @param id 角色主键ID
	 */
	public void setId(java.lang.Long id) {
		this.id = id;
	}
	
	/**
	 * @return 角色主键ID
	 */
	public java.lang.Long getId() {
		return this.id;
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
	 * @param userName 用户姓名
	 */
	public void setUserName(java.lang.String userName) {
		this.userName = userName;
	}
	
	/**
	 * @return 用户姓名
	 */
	public java.lang.String getUserName() {
		return this.userName;
	}
	
	/**
	 * @param forcastNumber 预报员编号
	 */
	public void setForcastNumber(java.lang.String forcastNumber) {
		this.forcastNumber = forcastNumber;
	}
	
	/**
	 * @return 预报员编号
	 */
	public java.lang.String getForcastNumber() {
		return this.forcastNumber;
	}
	
	/**
	 * @param totalScore 成绩
	 */
	public void setTotalScore(Long totalScore) {
		this.totalScore = totalScore;
	}
	
	/**
	 * @return 成绩
	 */
	public Long getTotalScore() {
		return this.totalScore;
	}
}
