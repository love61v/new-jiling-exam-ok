package com.happy.exam.dao.impl;  
import java.util.List;

import com.happy.exam.dao.SystemRoleGroupDao; 
import com.happy.exam.model.SystemRole;
import com.happy.exam.model.SystemRoleGroup;

import org.springframework.stereotype.Service;

/**
 *  SystemRoleGroupDao
 *
 * @version : Ver 1.0
 * @author	: <a href="mailto:hubo@95190.com">hubo</a>
 * @date	: 2015年5月17日 下午9:01:26 
 */
@Service
public class SystemRoleGroupDaoImpl extends MybatisBaseDaoImpl<SystemRoleGroup, java.lang.Long> implements SystemRoleGroupDao { 

	private final static String CLZZ = SystemRoleGroup.class.getName();
	private final static String FIND_ROLE_BY_GROUPID = CLZZ + ".findRoleByGroupId";
	private final static String DELETE_GROUP_ROLE = CLZZ + ".deleteGroupRole";
	
	@Override
	public List<SystemRole> findRoleByGroupId(Long groupId) {
		return this.getSqlSessionTemplate().selectList(FIND_ROLE_BY_GROUPID, groupId);
	}

	@Override
	public long deleteGroupRole(SystemRoleGroup systemRoleGroup) {
		return this.getSqlSessionTemplate().delete(DELETE_GROUP_ROLE, systemRoleGroup);
	} 
	
}
