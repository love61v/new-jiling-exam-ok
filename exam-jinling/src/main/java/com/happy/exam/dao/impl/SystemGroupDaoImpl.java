package com.happy.exam.dao.impl;  
import java.util.List;

import org.springframework.stereotype.Service;

import com.happy.exam.dao.SystemGroupDao;
import com.happy.exam.model.SystemGroup;

/**
 *  SystemGroupDao
 *
 * @version : Ver 1.0
 * @author	: <a href="mailto:hubo@95190.com">hubo</a>
 * @date	: 2015年5月17日 下午9:01:26 
 */
@Service
public class SystemGroupDaoImpl extends MybatisBaseDaoImpl<SystemGroup, java.lang.Long> implements SystemGroupDao {

	private final String CLZZ_NAME = SystemGroup.class.getName();
	
	private final String FIND_TREE_GRID = CLZZ_NAME + ".findTreegrid";
	private final String DELETEUNION = CLZZ_NAME + ".deleteUnion";
	 
	@Override
	public List<SystemGroup> findTreegrid(SystemGroup group) {
		return this.getSqlSessionTemplate().selectList(FIND_TREE_GRID, group);
	}

	@Override
	public int deleteUnion(SystemGroup group) {
		return this.getSqlSessionTemplate().delete(DELETEUNION, group);
	} 

}
