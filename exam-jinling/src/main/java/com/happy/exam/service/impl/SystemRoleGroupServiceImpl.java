package com.happy.exam.service.impl;  
import java.util.List;

import com.happy.exam.service.SystemRoleGroupService;
import com.happy.exam.service.impl.MybatisBaseServiceImpl;
import com.happy.exam.dao.SystemRoleGroupDao;
import com.happy.exam.model.SystemRole;
import com.happy.exam.model.SystemRoleGroup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *  SystemRoleGroupServiceImpl
 *
 * @version : Ver 1.0
 * @author	: <a href="mailto:hubo@95190.com">hubo</a>
 * @date	: 2015年5月17日 下午9:01:26 
 */
@Service
public class SystemRoleGroupServiceImpl extends MybatisBaseServiceImpl<SystemRoleGroup, java.lang.Long> implements SystemRoleGroupService {

	@Autowired
	private SystemRoleGroupDao systemRoleGroupDao;
	
	@Override
	public List<SystemRole> findRoleByGroupId(Long groupId) {
		return systemRoleGroupDao.findRoleByGroupId(groupId);
	} 
	
	@Override
	public long deleteGroupRole(SystemRoleGroup systemRoleGroup) {
		return systemRoleGroupDao.deleteGroupRole(systemRoleGroup);
	} 

}
