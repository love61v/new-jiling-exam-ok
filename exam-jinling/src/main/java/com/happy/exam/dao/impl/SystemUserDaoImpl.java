package com.happy.exam.dao.impl;

import java.util.List;
import java.util.Map;

import com.happy.exam.common.bean.UserGroupModel;
import com.happy.exam.common.bean.UserRolePermissionModel;
import com.happy.exam.dao.SystemUserDao;
import com.happy.exam.model.SystemRole;
import com.happy.exam.model.SystemUser;

import org.springframework.stereotype.Service;

/**
 * SystemUserDao
 *
 * @version : Ver 1.0
 * @author : <a href="mailto:hubo@95190.com">hubo</a>
 * @date : 2015年5月17日 下午9:01:26
 */
@Service
public class SystemUserDaoImpl extends
		MybatisBaseDaoImpl<SystemUser, java.lang.Long> implements SystemUserDao {

	private final static String CLZZ = SystemUser.class.getName();
	private final static String FIND_USER_BY_GROUPID = CLZZ + ".findUserByGroupId";
	private final static String FIND_ROLELIST_BY_USERID = CLZZ + ".findRoleListByUserId";
	private final static String FIND_PERMISSIONS_BY_USERID = CLZZ + ".findPermissionsByUserId";

	@Override
	public List<UserGroupModel> findUserByGroupId(Map<String, Object> params) {
		return this.getSqlSessionTemplate().selectList(FIND_USER_BY_GROUPID, params);
	}

	@Override
	public List<SystemRole> findRoleListByUserId(Long userId) {
		return this.getSqlSessionTemplate().selectList(FIND_ROLELIST_BY_USERID,userId);
	}

	@Override
	public List<UserRolePermissionModel> findPermissionsByUserId(Long userId) {
		return this.getSqlSessionTemplate().selectList(FIND_PERMISSIONS_BY_USERID,userId);
	}

}
