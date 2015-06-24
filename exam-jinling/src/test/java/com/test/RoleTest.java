/*
 * Beijing YESWAY Information Technology Co,Ltd.
 * All rights reserved.
 * 北京九五智驾信息技术股份有限公司
 * <p>SystemUserTest.java</p>
 */
package com.test;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import com.alibaba.fastjson.JSON;
import com.happy.exam.model.SystemResource;
import com.happy.exam.model.SystemRole;
import com.happy.exam.service.SystemResourceService;
import com.happy.exam.service.SystemRoleService;
 

/**
 * 用户测试
 *
 * @version : Ver 1.0
 * @author	: <a href="mailto:hubo@95190.com">hubo</a>
 * @date	: 2015-5-19 上午11:27:50 
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"/spring/test-applicationContext.xml"})
@TransactionConfiguration(transactionManager = "transactionManager")
public class RoleTest {

	@Autowired 
	private SystemRoleService systemRoleService;
	
	@Autowired
	private SystemResourceService systemResourceService;
	
	@Test public void saveModuleTest(){
		SystemResource m = new SystemResource();
		m.setCreateTime(new java.util.Date());
		m.setResourceName("所有模块");
		m.setSort(1);
		m.setStatus(1);
		m.setEngName("user_change_password".toUpperCase());
//		m.setPath("/user/changePassword.html");
//		m.setParentId("18676996EAE342E5BBFEB22829FE1177");
		
		systemResourceService.save(m);
	}
 
	
	/**
	 * save()保存方法
	 *
	 * @author 	: <a href="mailto:hubo@95190.com">hubo</a>  2015-5-19 下午1:55:57
	 */
	@Test public void saveTest(){
		SystemRole role = new SystemRole();
		role.setCreateTime(new Date());
		role.setEngName("student");
		role.setRoleName("考生");
		role.setRemark("考生");
		role.setStatus(1);
		
		
		int count = systemRoleService.save(role); //条数
		print(role,"添加" + count +"条数据");
	}
	
	 
	
	
	
	private void print(Object obj){
		System.out.println("************结果****************");
		System.out.println("****************************");
		
		System.out.println(JSON.toJSONString(obj));
		
		System.out.println("****************************");
		System.out.println("****************************");
	}
	
	private void print(Object obj,String tip){
		System.out.println("************"+tip+"结果****************");
		System.out.println("****************************");
		
		System.out.println(JSON.toJSONString(obj));
		
		System.out.println("****************************");
		System.out.println("****************************");
	}
	

}
