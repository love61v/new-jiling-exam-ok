package com.happy.exam.service.impl;  
import java.util.List;

import com.happy.exam.service.DepartmentService;
import com.happy.exam.service.impl.MybatisBaseServiceImpl;
import com.happy.exam.dao.DepartmentDao;
import com.happy.exam.model.Department;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *  DepartmentServiceImpl
 *
 * @version : Ver 1.0
 * @author	: <a href="mailto:hubo@95190.com">hubo</a>
 * @date	: 2015年5月17日 下午9:01:26 
 */
@Service
public class DepartmentServiceImpl extends MybatisBaseServiceImpl<Department, java.lang.Long> implements DepartmentService {

	@Autowired
	private DepartmentDao departmentDao;
	
	@Override
	public List<Department> findTreegrid(Department department) {
		return departmentDao.findTreegrid(department);
	} 

}
