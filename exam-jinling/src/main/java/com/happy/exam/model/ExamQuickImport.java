package com.happy.exam.model;

public class ExamQuickImport extends BaseModel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     * @备注:主键ID     
     * @字段:QuickId BIGINT(19)  
     */	
	private java.lang.Long quickId;

	/**
     * @备注:问题     
     * @字段:ExamName VARCHAR(255)  
     */	
	private java.lang.String examName;

	/**
     * @备注:状态: 默认1应用;2禁用;3已删除,4此试装已考试过     
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
	
	 
	public ExamQuickImport(){
	}

	public ExamQuickImport(
		java.lang.Long quickId
	){
		this.quickId = quickId;
	}

	
	/**
	 * @param quickId 主键ID
	 */
	public void setQuickId(java.lang.Long quickId) {
		this.quickId = quickId;
	}
	
	/**
	 * @return 主键ID
	 */
	public java.lang.Long getQuickId() {
		return this.quickId;
	}
	
	/**
	 * @param examName 问题
	 */
	public void setExamName(java.lang.String examName) {
		this.examName = examName;
	}
	
	/**
	 * @return 问题
	 */
	public java.lang.String getExamName() {
		return this.examName;
	}
	
	/**
	 * @param status 状态: 默认1应用;2禁用;3已删除,4此试装已考试过
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	/**
	 * @return 状态: 默认1应用;2禁用;3已删除,4此试装已考试过
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
