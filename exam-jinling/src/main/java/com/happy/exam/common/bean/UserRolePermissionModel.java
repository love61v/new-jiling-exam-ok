 
package com.happy.exam.common.bean;

import java.io.Serializable;

/**
 *  
 *
 * @version : Ver 1.0
 * @author	: <a href="mailto:358911056@qq.com">hubo</a>
 * @date	: 2015-7-1 下午3:19:27 
 */
public class UserRolePermissionModel implements Serializable {
	 
	private static final long serialVersionUID = 1L;
	
	private Long userid;
	
	private String userName;
	
	private Long groupId;
	
	private Long resourceID;
	
	private String resourceName;
	
	private String moduleEngName;
	
	private String operateId;

	public Long getUserid() {
		return userid;
	}

	public void setUserid(Long userid) {
		this.userid = userid;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Long getGroupId() {
		return groupId;
	}

	public void setGroupId(Long groupId) {
		this.groupId = groupId;
	}

	public Long getResourceID() {
		return resourceID;
	}

	public void setResourceID(Long resourceID) {
		this.resourceID = resourceID;
	}

	public String getResourceName() {
		return resourceName;
	}

	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}

	public String getOperateId() {
		return operateId;
	}

	public void setOperateId(String operateId) {
		this.operateId = operateId;
	}

	public String getModuleEngName() {
		return moduleEngName;
	}

	public void setModuleEngName(String moduleEngName) {
		this.moduleEngName = moduleEngName;
	}
	
}
