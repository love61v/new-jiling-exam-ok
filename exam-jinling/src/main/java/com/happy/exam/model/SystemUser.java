package com.happy.exam.model;

public class SystemUser extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Department department;

	/**
	 * @备注:
	 * @字段:UserId BIGINT(19)
	 */
	private java.lang.Long userId;

	/**
	 * @备注:部门表主键ID
	 * @字段:DeptId BIGINT(19)
	 */
	private java.lang.Long deptId;

	/**
	 * @备注:预报员编号
	 * @字段:ForcastNumber VARCHAR(32)
	 */
	private java.lang.String forcastNumber;

	/**
	 * @备注:登陆账号
	 * @字段:LoginName VARCHAR(64)
	 */
	private java.lang.String loginName;

	/**
	 * @备注:密码
	 * @字段:Password VARCHAR(64)
	 */
	private java.lang.String password;

	/**
	 * @备注:姓名
	 * @字段:UserName VARCHAR(64)
	 */
	private java.lang.String userName;

	/**
	 * @备注:英文名
	 * @字段:EngName VARCHAR(64)
	 */
	private java.lang.String engName;

	/**
	 * @备注:出生年月
	 * @字段:Birthday DATE(10)
	 */
	private String birthday;

	/**
	 * @备注:电话号码
	 * @字段:PhoneNumber VARCHAR(20)
	 */
	private java.lang.String phoneNumber;

	/**
	 * @备注:邮箱
	 * @字段:Email VARCHAR(32)
	 */
	private java.lang.String email;

	/**
	 * @备注:性别: 1男;2女
	 * @字段:Sex CHAR(1)
	 */
	private java.lang.String sex;

	/**
	 * @备注:地址
	 * @字段:Address VARCHAR(255)
	 */
	private java.lang.String address;

	/**
	 * @备注:身份证号码
	 * @字段:IdCard VARCHAR(64)
	 */
	private java.lang.String idCard;

	/**
	 * @备注:第一学历
	 * @字段:EducationFirst VARCHAR(64)
	 */
	private java.lang.String educationFirst;

	/**
	 * @备注:毕业院校(第一学历)
	 * @字段:SchoolFirst VARCHAR(64)
	 */
	private java.lang.String schoolFirst;

	/**
	 * @备注:专业(第一学历)
	 * @字段:MajorFirst VARCHAR(64)
	 */
	private java.lang.String majorFirst;

	/**
	 * @备注:毕业时间(第一学历)
	 * @字段:GraduateFirst DATETIME(19)
	 */
	private String graduateFirst;

	/**
	 * @备注:第二学历
	 * @字段:EducationSecond VARCHAR(64)
	 */
	private java.lang.String educationSecond;

	/**
	 * @备注:毕业院校(第二学历)
	 * @字段:SchoolSecond VARCHAR(64)
	 */
	private java.lang.String schoolSecond;

	/**
	 * @备注:专业(第二学历)
	 * @字段:MajorSecond VARCHAR(64)
	 */
	private java.lang.String majorSecond;

	/**
	 * @备注:毕业时间(第二学历)
	 * @字段:GraduateSecond DATETIME(19)
	 */
	private String graduateSecond;

	/**
	 * @备注:职称
	 * @字段:JobTitle VARCHAR(64)
	 */
	private java.lang.String jobTitle;

	/**
	 * @备注:任职时间
	 * @字段:JobDate DATETIME(19)
	 */
	private String jobDate;

	/**
	 * @备注:曾经历岗位及年限
	 * @字段:JobRemark VARCHAR(255)
	 */
	private java.lang.String jobRemark;

	/**
	 * @备注:开始从事预报年月
	 * @字段:ForcastBeginDate DATE(10)
	 */
	private String forcastBeginDate;

	/**
	 * @备注:培训经历
	 * @字段:Train VARCHAR(255)
	 */
	private java.lang.String train;

	/**
	 * @备注:状态: 1启用,2已禁用登陆，3已被删除
	 * @字段:Status TINYINT(3)
	 */
	private Integer status;

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

	public SystemUser() {
	}

	public SystemUser(java.lang.Long userId) {
		this.userId = userId;
	}

	/**
	 * @param userId
	 */
	public void setUserId(java.lang.Long userId) {
		this.userId = userId;
	}

	/**
	 * @return
	 */
	public java.lang.Long getUserId() {
		return this.userId;
	}

	/**
	 * @param deptId
	 *            部门表主键ID
	 */
	public void setDeptId(java.lang.Long deptId) {
		this.deptId = deptId;
	}

	/**
	 * @return 部门表主键ID
	 */
	public java.lang.Long getDeptId() {
		return this.deptId;
	}

	/**
	 * @param forcastNumber
	 *            预报员编号
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
	 * @param loginName
	 *            登陆账号
	 */
	public void setLoginName(java.lang.String loginName) {
		this.loginName = loginName;
	}

	/**
	 * @return 登陆账号
	 */
	public java.lang.String getLoginName() {
		return this.loginName;
	}

	/**
	 * @param password
	 *            密码
	 */
	public void setPassword(java.lang.String password) {
		this.password = password;
	}

	/**
	 * @return 密码
	 */
	public java.lang.String getPassword() {
		return this.password;
	}

	/**
	 * @param userName
	 *            姓名
	 */
	public void setUserName(java.lang.String userName) {
		this.userName = userName;
	}

	/**
	 * @return 姓名
	 */
	public java.lang.String getUserName() {
		return this.userName;
	}

	/**
	 * @param engName
	 *            英文名
	 */
	public void setEngName(java.lang.String engName) {
		this.engName = engName;
	}

	/**
	 * @return 英文名
	 */
	public java.lang.String getEngName() {
		return this.engName;
	}

	/**
	 * @param birthday
	 *            出生年月
	 */
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	/**
	 * @return 出生年月
	 */
	public String getBirthday() {
		return this.birthday;
	}

	/**
	 * @param phoneNumber
	 *            电话号码
	 */
	public void setPhoneNumber(java.lang.String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	/**
	 * @return 电话号码
	 */
	public java.lang.String getPhoneNumber() {
		return this.phoneNumber;
	}

	/**
	 * @param email
	 *            邮箱
	 */
	public void setEmail(java.lang.String email) {
		this.email = email;
	}

	/**
	 * @return 邮箱
	 */
	public java.lang.String getEmail() {
		return this.email;
	}

	/**
	 * @param sex
	 *            性别: 1男;2女
	 */
	public void setSex(java.lang.String sex) {
		this.sex = sex;
	}

	/**
	 * @return 性别: 1男;2女
	 */
	public java.lang.String getSex() {
		return this.sex;
	}

	/**
	 * @param address
	 *            地址
	 */
	public void setAddress(java.lang.String address) {
		this.address = address;
	}

	/**
	 * @return 地址
	 */
	public java.lang.String getAddress() {
		return this.address;
	}

	/**
	 * @param idCard
	 *            身份证号码
	 */
	public void setIdCard(java.lang.String idCard) {
		this.idCard = idCard;
	}

	/**
	 * @return 身份证号码
	 */
	public java.lang.String getIdCard() {
		return this.idCard;
	}

	/**
	 * @param educationFirst
	 *            第一学历
	 */
	public void setEducationFirst(java.lang.String educationFirst) {
		this.educationFirst = educationFirst;
	}

	/**
	 * @return 第一学历
	 */
	public java.lang.String getEducationFirst() {
		return this.educationFirst;
	}

	/**
	 * @param schoolFirst
	 *            毕业院校(第一学历)
	 */
	public void setSchoolFirst(java.lang.String schoolFirst) {
		this.schoolFirst = schoolFirst;
	}

	/**
	 * @return 毕业院校(第一学历)
	 */
	public java.lang.String getSchoolFirst() {
		return this.schoolFirst;
	}

	/**
	 * @param majorFirst
	 *            专业(第一学历)
	 */
	public void setMajorFirst(java.lang.String majorFirst) {
		this.majorFirst = majorFirst;
	}

	/**
	 * @return 专业(第一学历)
	 */
	public java.lang.String getMajorFirst() {
		return this.majorFirst;
	}

	/**
	 * @param graduateFirst
	 *            毕业时间(第一学历)
	 */
	public void setGraduateFirst(String graduateFirst) {
		this.graduateFirst = graduateFirst;
	}

	/**
	 * @return 毕业时间(第一学历)
	 */
	public String getGraduateFirst() {
		return this.graduateFirst;
	}

	/**
	 * @param educationSecond
	 *            第二学历
	 */
	public void setEducationSecond(java.lang.String educationSecond) {
		this.educationSecond = educationSecond;
	}

	/**
	 * @return 第二学历
	 */
	public java.lang.String getEducationSecond() {
		return this.educationSecond;
	}

	/**
	 * @param schoolSecond
	 *            毕业院校(第二学历)
	 */
	public void setSchoolSecond(java.lang.String schoolSecond) {
		this.schoolSecond = schoolSecond;
	}

	/**
	 * @return 毕业院校(第二学历)
	 */
	public java.lang.String getSchoolSecond() {
		return this.schoolSecond;
	}

	/**
	 * @param majorSecond
	 *            专业(第二学历)
	 */
	public void setMajorSecond(java.lang.String majorSecond) {
		this.majorSecond = majorSecond;
	}

	/**
	 * @return 专业(第二学历)
	 */
	public java.lang.String getMajorSecond() {
		return this.majorSecond;
	}

	/**
	 * @param graduateSecond
	 *            毕业时间(第二学历)
	 */
	public void setGraduateSecond(String graduateSecond) {
		this.graduateSecond = graduateSecond;
	}

	/**
	 * @return 毕业时间(第二学历)
	 */
	public String getGraduateSecond() {
		return this.graduateSecond;
	}

	/**
	 * @param jobTitle
	 *            职称
	 */
	public void setJobTitle(java.lang.String jobTitle) {
		this.jobTitle = jobTitle;
	}

	/**
	 * @return 职称
	 */
	public java.lang.String getJobTitle() {
		return this.jobTitle;
	}

	/**
	 * @param jobDate
	 *            任职时间
	 */
	public void setJobDate(String jobDate) {
		this.jobDate = jobDate;
	}

	/**
	 * @return 任职时间
	 */
	public String getJobDate() {
		return this.jobDate;
	}

	/**
	 * @param jobRemark
	 *            曾经历岗位及年限
	 */
	public void setJobRemark(java.lang.String jobRemark) {
		this.jobRemark = jobRemark;
	}

	/**
	 * @return 曾经历岗位及年限
	 */
	public java.lang.String getJobRemark() {
		return this.jobRemark;
	}

	/**
	 * @param forcastBeginDate
	 *            开始从事预报年月
	 */
	public void setForcastBeginDate(String forcastBeginDate) {
		this.forcastBeginDate = forcastBeginDate;
	}

	/**
	 * @return 开始从事预报年月
	 */
	public String getForcastBeginDate() {
		return this.forcastBeginDate;
	}

	/**
	 * @param train
	 *            培训经历
	 */
	public void setTrain(java.lang.String train) {
		this.train = train;
	}

	/**
	 * @return 培训经历
	 */
	public java.lang.String getTrain() {
		return this.train;
	}

	/**
	 * @param status
	 *            状态: 1启用,2已禁用登陆，3已被删除
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}

	/**
	 * @return 状态: 1启用,2已禁用登陆，3已被删除
	 */
	public Integer getStatus() {
		return this.status;
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

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

}
