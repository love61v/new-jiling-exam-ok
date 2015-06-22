package com.happy.exam.dao;  
import java.util.List;

import com.happy.exam.dao.BaseDao;
import com.happy.exam.model.Department;

/**
 *  DepartmentDao
 *
 * @version : Ver 1.0
 * @author	: <a href="mailto:hubo@95190.com">hubo</a>
 * @date	: 2015年5月17日 下午9:01:26 
 */
public interface DepartmentDao extends BaseDao<Department,java.lang.Long>{

	List<Department> findTreegrid(Department department);

}
