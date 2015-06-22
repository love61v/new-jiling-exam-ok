package com.happy.exam.dao.impl;  
import java.util.List;

import com.happy.exam.dao.SystemGroupDao; 
import com.happy.exam.model.SystemGroup;

import org.springframework.stereotype.Service;

/**
 *  SystemGroupDao
 *
 * @version : Ver 1.0
 * @author	: <a href="mailto:hubo@95190.com">hubo</a>
 * @date	: 2015年5月17日 下午9:01:26 
 */
@Service
public class SystemGroupDaoImpl extends MybatisBaseDaoImpl<SystemGroup, java.lang.Long> implements SystemGroupDao {

	@Override
	public List<SystemGroup> findTreegrid(SystemGroup group) {
		// TODO Auto-generated method stub
		return null;
	} 

}
