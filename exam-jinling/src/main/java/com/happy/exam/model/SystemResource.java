package com.happy.exam.model;

public class SystemResource extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * @备注:主键ID
	 * @字段:ResourceId BIGINT(19)
	 */
	private java.lang.Long resourceId;

	/**
	 * @备注:角色名称
	 * @字段:ResourceName VARCHAR(64)
	 */
	private java.lang.String resourceName;

	/**
	 * @备注:英文名称
	 * @字段:EngName VARCHAR(64)
	 */
	private java.lang.String engName;

	/**
	 * @备注:父ID
	 * @字段:ParentId BIGINT(19)
	 */
	private java.lang.Long parentId;

	/**
	 * @备注:非数据库字段，仅查询使用
	 */
	private java.lang.Long _parentId;

	/**
	 * @备注:URL路径
	 * @字段:Path CHAR(10)
	 */
	private java.lang.String path;

	/**
	 * @备注:图标地址
	 * @字段:SourceIcon CHAR(10)
	 */
	private java.lang.String sourceIcon;

	/**
	 * @备注:类型
	 * @字段:SourceType CHAR(10)
	 */
	private java.lang.String sourceType;

	/**
	 * @备注:状态: 1应用;2禁用;3已删除
	 * @字段:Status TINYINT(3)
	 */
	private Integer status;

	/**
	 * @备注:排序
	 * @字段:Sort INT(10)
	 */
	private java.lang.Integer sort;

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

	public SystemResource() {
	}

	public SystemResource(java.lang.Long resourceId) {
		this.resourceId = resourceId;
	}

	/**
	 * @param resourceId
	 *            主键ID
	 */
	public void setResourceId(java.lang.Long resourceId) {
		this.resourceId = resourceId;
	}

	/**
	 * @return 主键ID
	 */
	public java.lang.Long getResourceId() {
		return this.resourceId;
	}

	/**
	 * @param resourceName
	 *            角色名称
	 */
	public void setResourceName(java.lang.String resourceName) {
		this.resourceName = resourceName;
	}

	/**
	 * @return 角色名称
	 */
	public java.lang.String getResourceName() {
		return this.resourceName;
	}

	/**
	 * @param engName
	 *            英文名称
	 */
	public void setEngName(java.lang.String engName) {
		this.engName = engName;
	}

	/**
	 * @return 英文名称
	 */
	public java.lang.String getEngName() {
		return this.engName;
	}

	/**
	 * @param parentId
	 *            父ID
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
	 * @param path
	 *            URL路径
	 */
	public void setPath(java.lang.String path) {
		this.path = path;
	}

	/**
	 * @return URL路径
	 */
	public java.lang.String getPath() {
		return this.path;
	}

	/**
	 * @param sourceIcon
	 *            图标地址
	 */
	public void setSourceIcon(java.lang.String sourceIcon) {
		this.sourceIcon = sourceIcon;
	}

	/**
	 * @return 图标地址
	 */
	public java.lang.String getSourceIcon() {
		return this.sourceIcon;
	}

	/**
	 * @param sourceType
	 *            类型
	 */
	public void setSourceType(java.lang.String sourceType) {
		this.sourceType = sourceType;
	}

	/**
	 * @return 类型
	 */
	public java.lang.String getSourceType() {
		return this.sourceType;
	}

	/**
	 * @param status
	 *            状态: 1应用;2禁用;3已删除
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
	 * @param sort
	 *            排序
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
	 * @param remark
	 *            备注
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
	 * @param createUser
	 *            创建人
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
	 * @param createTime
	 *            入库时间
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
	 * @param updateTime
	 *            变更时间
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

	public java.lang.Long get_parentId() {
		return _parentId;
	}

	public void set_parentId(java.lang.Long _parentId) {
		this._parentId = _parentId;
	}
}
