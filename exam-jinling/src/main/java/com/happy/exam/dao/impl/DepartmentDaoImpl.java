package com.happy.exam.dao.impl;  
import java.util.List;

import com.happy.exam.dao.DepartmentDao; 
import com.happy.exam.model.Department;

import org.springframework.stereotype.Service;

/**
 *  DepartmentDao
 *
 * @version : Ver 1.0
 * @author	: <a href="mailto:hubo@95190.com">hubo</a>
 * @date	: 2015年5月17日 下午9:01:26 
 */
@Service
public class DepartmentDaoImpl extends MybatisBaseDaoImpl<Department, java.lang.Long> implements DepartmentDao {
	
	private final String CLZZ_NAME = Department.class.getName();
	private final String FIND_TREE_GRID = CLZZ_NAME + ".findTreegrid";

	@Override
	public List<Department> findTreegrid(Department department) {
		return this.getSqlSessionTemplate().selectList(FIND_TREE_GRID, department);
	} 

}
