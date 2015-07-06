package com.happy.exam.service.impl;  
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.happy.exam.dao.SystemResourceDao;
import com.happy.exam.model.SystemResource;
import com.happy.exam.service.SystemResourceService;

/**
 *  SystemResourceServiceImpl
 *
 * @version : Ver 1.0
 * @author	: <a href="mailto:hubo@95190.com">hubo</a>
 * @date	: 2015年5月17日 下午9:01:26 
 */
@Service
public class SystemResourceServiceImpl extends MybatisBaseServiceImpl<SystemResource, java.lang.Long> implements SystemResourceService {

	@Autowired
	private SystemResourceDao systemResourceDao;
	
	@Override
	public List<SystemResource> findTreegrid(SystemResource systemResource) {
		
		return systemResourceDao.findTreegrid(systemResource);
	}

	@Override
	public int deleteUnion(SystemResource systemResource) {
		return systemResourceDao.deleteUnion(systemResource);
	}

	@Override
	public List<SystemResource> findUserModuleTree(Long userId) {
		return systemResourceDao.findUserModuleTree(userId);
	} 

}
