package com.happy.exam.service.impl;  
import java.util.List;

import com.happy.exam.service.SystemGroupService;
import com.happy.exam.service.impl.MybatisBaseServiceImpl;
import com.happy.exam.dao.SystemGroupDao;
import com.happy.exam.model.SystemGroup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *  SystemGroupServiceImpl
 *
 * @version : Ver 1.0
 * @author	: <a href="mailto:hubo@95190.com">hubo</a>
 * @date	: 2015年5月17日 下午9:01:26 
 */
@Service
public class SystemGroupServiceImpl extends MybatisBaseServiceImpl<SystemGroup, java.lang.Long> implements SystemGroupService {

	@Autowired
	private SystemGroupDao systemGroupDao;
	
	@Override
	public List<SystemGroup> findTreegrid(SystemGroup group) {
		return systemGroupDao.findTreegrid(group);
	}

	@Override
	public int deleteUnion(SystemGroup group) {
		return systemGroupDao.deleteUnion(group);
	} 

}
