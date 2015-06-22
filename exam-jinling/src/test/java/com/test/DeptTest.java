/*
 * Beijing happy Information Technology Co,Ltd.
 * All rights reserved.
 * 
 * <p>DeptTest.java</p>
 */
package com.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.happy.exam.model.Department;
import com.happy.exam.service.DepartmentService;

/**
 *  
 *
 * @version : Ver 1.0
 * @author	: <a href="mailto:h358911056@qq.com">hubo</a>
 * @date	: 2015年6月15日 下午11:09:51 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"/test-applicationContext.xml"})
public class DeptTest {
	
	@Autowired
	private DepartmentService departmentService;

	@Test
	public void saveDepartment(){
		/*Department dept = new Department();
		dept.setDeptId(UUIDutils.getUUID());
		dept.setDeptName("所有部门");
		dept.setAreaCode(null);
		dept.setParentId(-1l);
		departmentService.save(dept);*/
		
		Department dept1 = new Department();
		dept1.setDeptName("科技中心");
		dept1.setAreaCode("15004");
		
		departmentService.save(dept1);
	}
}
