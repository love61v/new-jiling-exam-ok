package com.happy.exam.service.impl;  
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.happy.exam.service.SystemUserService;
import com.happy.exam.service.impl.MybatisBaseServiceImpl;
import com.happy.exam.common.bean.UserGroupModel;
import com.happy.exam.common.bean.UserRolePermissionModel;
import com.happy.exam.dao.SystemUserDao;
import com.happy.exam.model.SystemRole;
import com.happy.exam.model.SystemUser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *  SystemUserServiceImpl
 *
 * @version : Ver 1.0
 * @author	: <a href="mailto:hubo@95190.com">hubo</a>
 * @date	: 2015年5月17日 下午9:01:26 
 */
@Service
public class SystemUserServiceImpl extends MybatisBaseServiceImpl<SystemUser, java.lang.Long> implements SystemUserService {

	@Autowired
	private SystemUserDao systemUserDao;
	
	@Override
	public List<UserGroupModel> findUserByGroupId(SystemUser user, Long groupId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("user", user);
		params.put("groupId", groupId);
		
		return systemUserDao.findUserByGroupId(params);
	}

	@Override
	public List<SystemRole> findRoleListByUserId(Long userId) {
		return systemUserDao.findRoleListByUserId(userId);
	}

	@Override
	public List<UserRolePermissionModel> findPermissionsByUserId(Long userId) {
		return systemUserDao.findPermissionsByUserId(userId);
	} 

}
