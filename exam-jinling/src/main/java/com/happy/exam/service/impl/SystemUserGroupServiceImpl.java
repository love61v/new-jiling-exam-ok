package com.happy.exam.service.impl;  
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.happy.exam.dao.SystemUserGroupDao;
import com.happy.exam.model.SystemRoleGroup;
import com.happy.exam.model.SystemUserGroup;
import com.happy.exam.service.SystemUserGroupService;

/**
 *  SystemUserGroupServiceImpl
 *
 * @version : Ver 1.0
 * @author	: <a href="mailto:hubo@95190.com">hubo</a>
 * @date	: 2015年5月17日 下午9:01:26 
 */
@Service
public class SystemUserGroupServiceImpl extends MybatisBaseServiceImpl<SystemUserGroup, java.lang.Long> implements SystemUserGroupService {

	@Autowired
	private SystemUserGroupDao systemUserGroupDao;
	@Override
	public long deleteUnion(String groupId, List<String> userIdList) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("groupId", groupId);
		params.put("userIdList", userIdList);
		
		return systemUserGroupDao.deleteUnion(params);
	}

}
