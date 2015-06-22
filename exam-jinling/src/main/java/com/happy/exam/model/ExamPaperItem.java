package com.happy.exam.model;

public class ExamPaperItem extends BaseModel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     * @备注:试题主键ID     
     * @字段:ItemId BIGINT(19)  
     */	
	private java.lang.Long itemId;

	/**
     * @备注:标题     
     * @字段:PaperId BIGINT(19)  
     */	
	private java.lang.Long paperId;

	/**
     * @备注:考试分类表ID     
     * @字段:TypeId BIGINT(19)  
     */	
	private java.lang.Long typeId;

	/**
     * @备注:题目数量     
     * @字段:Numbers INT(10)  
     */	
	private java.lang.Integer numbers;

	/**
     * @备注:占总分百分比     
     * @字段:Percent INT(10)  
     */	
	private java.lang.Integer percent;

	/**
     * @备注:题目类型: 1单选题目、2多选题目、3填空题、4简答题、5操作题     
     * @字段:TopicType TINYINT(3)  
     */	
	private Integer topicType;
	public ExamPaperItem(){
	}

	public ExamPaperItem(
		java.lang.Long itemId
	){
		this.itemId = itemId;
	}

	
	/**
	 * @param itemId 试题主键ID
	 */
	public void setItemId(java.lang.Long itemId) {
		this.itemId = itemId;
	}
	
	/**
	 * @return 试题主键ID
	 */
	public java.lang.Long getItemId() {
		return this.itemId;
	}
	
	/**
	 * @param paperId 标题
	 */
	public void setPaperId(java.lang.Long paperId) {
		this.paperId = paperId;
	}
	
	/**
	 * @return 标题
	 */
	public java.lang.Long getPaperId() {
		return this.paperId;
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
	 * @param numbers 题目数量
	 */
	public void setNumbers(java.lang.Integer numbers) {
		this.numbers = numbers;
	}
	
	/**
	 * @return 题目数量
	 */
	public java.lang.Integer getNumbers() {
		return this.numbers;
	}
	
	/**
	 * @param percent 占总分百分比
	 */
	public void setPercent(java.lang.Integer percent) {
		this.percent = percent;
	}
	
	/**
	 * @return 占总分百分比
	 */
	public java.lang.Integer getPercent() {
		return this.percent;
	}
	
	/**
	 * @param topicType 题目类型: 1单选题目、2多选题目、3填空题、4简答题、5操作题
	 */
	public void setTopicType(Integer topicType) {
		this.topicType = topicType;
	}
	
	/**
	 * @return 题目类型: 1单选题目、2多选题目、3填空题、4简答题、5操作题
	 */
	public Integer getTopicType() {
		return this.topicType;
	}
}
