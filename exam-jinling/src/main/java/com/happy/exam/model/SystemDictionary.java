package com.happy.exam.model;

public class SystemDictionary extends BaseModel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     * @备注: 字典主键ID     
     * @字段:DictId BIGINT(19)  
     */	
	private java.lang.Long dictId;

	/**
     * @备注:名称     
     * @字段:DictName VARCHAR(64)  
     */	
	private java.lang.String dictName;

	/**
     * @备注:值     
     * @字段:DictValue VARCHAR(100)  
     */	
	private java.lang.String dictValue;

	/**
     * @备注:父ID     
     * @字段:ParentId BIGINT(19)  
     */	
	private java.lang.Long parentId;

	/**
     * @备注:分类标识     
     * @字段:TypeFlag VARCHAR(32)  
     */	
	private java.lang.String typeFlag;

	/**
     * @备注:备注     
     * @字段:Remark VARCHAR(500)  
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
	
	 
	public SystemDictionary(){
	}

	public SystemDictionary(
		java.lang.Long dictId
	){
		this.dictId = dictId;
	}

	
	/**
	 * @param dictId  字典主键ID
	 */
	public void setDictId(java.lang.Long dictId) {
		this.dictId = dictId;
	}
	
	/**
	 * @return  字典主键ID
	 */
	public java.lang.Long getDictId() {
		return this.dictId;
	}
	
	/**
	 * @param dictName 名称
	 */
	public void setDictName(java.lang.String dictName) {
		this.dictName = dictName;
	}
	
	/**
	 * @return 名称
	 */
	public java.lang.String getDictName() {
		return this.dictName;
	}
	
	/**
	 * @param dictValue 值
	 */
	public void setDictValue(java.lang.String dictValue) {
		this.dictValue = dictValue;
	}
	
	/**
	 * @return 值
	 */
	public java.lang.String getDictValue() {
		return this.dictValue;
	}
	
	/**
	 * @param parentId 父ID
	 */
	public void setParentId(java.lang.Long parentId) {
		this.parentId = parentId;
	}
	
	/**
	 * @return 父ID
	 */
	public java.lang.Long getParentId() {
		return this.parentId;
	}
	
	/**
	 * @param typeFlag 分类标识
	 */
	public void setTypeFlag(java.lang.String typeFlag) {
		this.typeFlag = typeFlag;
	}
	
	/**
	 * @return 分类标识
	 */
	public java.lang.String getTypeFlag() {
		return this.typeFlag;
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
