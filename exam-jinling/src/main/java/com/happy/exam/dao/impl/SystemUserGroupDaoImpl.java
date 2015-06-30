package com.happy.exam.dao.impl;  
import java.util.Map;

import org.springframework.stereotype.Service;

import com.happy.exam.dao.SystemUserGroupDao;
import com.happy.exam.model.SystemUserGroup;

/**
 *  SystemUserGroupDao
 *
 * @version : Ver 1.0
 * @author	: <a href="mailto:hubo@95190.com">hubo</a>
 * @date	: 2015年5月17日 下午9:01:26 
 */
@Service
public class SystemUserGroupDaoImpl extends MybatisBaseDaoImpl<SystemUserGroup, java.lang.Long> implements SystemUserGroupDao {

	private final static String CLZZ = SystemUserGroup.class.getName();
	private final static String DELETE_UNION = CLZZ + ".deleteUnion";
	
	
	@Override
	public long deleteUnion(Map<String, Object> params) {
		return this.getSqlSessionTemplate().delete(DELETE_UNION, params);
	}

}
