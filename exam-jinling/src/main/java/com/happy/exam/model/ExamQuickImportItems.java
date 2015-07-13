package com.happy.exam.model;

public class ExamQuickImportItems extends BaseModel{

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
     * @备注:考试原题目     
     * @字段:PrototypeQuestion VARCHAR(4000)  
     */	
	private java.lang.String prototypeQuestion;

	/**
     * @备注:题型名     
     * @字段:TypeName VARCHAR(32)  
     */	
	private java.lang.String typeName;

	/**
     * @备注:题型: 1 单选,2多选,3填空,4简答,5操作题     
     * @字段:TypeFlag TINYINT(3)  
     */	
	private Integer typeFlag;

	/**
     * @备注:快速导入试卷基表主键ID     
     * @字段:QuickId BIGINT(19)  
     */	
	private java.lang.Long quickId;

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
     * @备注:排序     
     * @字段:Sort INT(10)  
     */	
	private java.lang.Integer sort;

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
	
	 
	public ExamQuickImportItems(){
	}

	public ExamQuickImportItems(
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
	 * @param prototypeQuestion 考试原题目
	 */
	public void setPrototypeQuestion(java.lang.String prototypeQuestion) {
		this.prototypeQuestion = prototypeQuestion;
	}
	
	/**
	 * @return 考试原题目
	 */
	public java.lang.String getPrototypeQuestion() {
		return this.prototypeQuestion;
	}
	
	/**
	 * @param typeName 题型名
	 */
	public void setTypeName(java.lang.String typeName) {
		this.typeName = typeName;
	}
	
	/**
	 * @return 题型名
	 */
	public java.lang.String getTypeName() {
		return this.typeName;
	}
	
	/**
	 * @param typeFlag 题型: 1 单选,2多选,3填空,4简答,5操作题
	 */
	public void setTypeFlag(Integer typeFlag) {
		this.typeFlag = typeFlag;
	}
	
	/**
	 * @return 题型: 1 单选,2多选,3填空,4简答,5操作题
	 */
	public Integer getTypeFlag() {
		return this.typeFlag;
	}
	
	/**
	 * @param quickId 快速导入试卷基表主键ID
	 */
	public void setQuickId(java.lang.Long quickId) {
		this.quickId = quickId;
	}
	
	/**
	 * @return 快速导入试卷基表主键ID
	 */
	public java.lang.Long getQuickId() {
		return this.quickId;
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
	 * @param sort 排序
	 */
	public void setSort(java.lang.Integer sort) {
		this.sort = sort;
	}
	
	/**
	 * @return 排序
	 */
	public java.lang.Integer getSort() {
		return this.sort;
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
