/*
 * Beijing YESWAY Information Technology Co,Ltd.
 * All rights reserved.
 * 北京九五智驾信息技术股份有限公司
 * <p>SystemUserTest.java</p>
 */
package com.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import com.alibaba.fastjson.JSON;
import com.happy.exam.common.pager.Pager;
import com.happy.exam.common.utils.Md5;
import com.happy.exam.model.SystemUser;
import com.happy.exam.service.SystemUserService;
 

/**
 * 用户测试
 *
 * @version : Ver 1.0
 * @author	: <a href="mailto:hubo@95190.com">hubo</a>
 * @date	: 2015-5-19 上午11:27:50 
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"/test-applicationContext.xml"})
@TransactionConfiguration(transactionManager = "transactionManager")
public class UserTest {

	@Autowired private SystemUserService systemUserService;
	
 
	/**
	 * getUniqueObject()按条件返回实体对象方法测试
	 *
	 * @author 	: <a href="mailto:hubo@95190.com">hubo</a>  2015-5-19 下午1:51:52
	 */
	@Test public void findSystemUsertest() {
		SystemUser SystemUser = new SystemUser();
		SystemUser.setLoginName("admin");
		
		SystemUser = systemUserService.getUniqueObject(SystemUser);
		
		print(SystemUser);
	}
	
	/**
	 * 
	 * getById()按ＩＤ查找方法测试
	 * 按３２位长度的ＵＵＩＤ查找用户
	 *
	 * @author 	: <a href="mailto:hubo@95190.com">hubo</a>  2015-5-19 下午1:32:46
	 */
	@Test public void getByIdTest(){
		SystemUser systemUser = systemUserService.getById(1l, SystemUser.class);
		
		print(systemUser);
	}
	
	/**
	 * getTotalCount()取总数方法测试
	 *
	 * @author 	: <a href="mailto:hubo@95190.com">hubo</a>  2015-5-19 下午1:51:11
	 */
	@Test public void getTotalCountTest(){
		Long total = systemUserService.getTotalCount(new SystemUser());
		print(total,"总记录数");
		
		SystemUser SystemUser = new SystemUser();
		SystemUser.setLoginName("aaa");
		Long total2 = systemUserService.getTotalCount(SystemUser);
		print(total2,"名字是admin的条数");
	}
	
	/**
	 * findList()查询  (不带条件的)
	 *
	 * @author 	: <a href="mailto:hubo@95190.com">hubo</a>  2015-5-19 下午1:50:57
	 */
	@Test public void findListTest(){
		//无条件查询出所有用户
		List<SystemUser> list1 = systemUserService.findList(new SystemUser());
		
		print(list1,"无条件查询出所有用户结果: 条数:" + list1.size());
	}
	
	/**
	 * findList()查询 (带条件的)
	 *
	 * @author 	: <a href="mailto:hubo@95190.com">hubo</a>  2015-5-19 下午1:50:57
	 */
	@Test public void findListTest2(){
		//模糊查询
		SystemUser SystemUser = new SystemUser();
		SystemUser.setLoginName("aaa");
		
		List<SystemUser> list2 = systemUserService.findList(SystemUser);
		
		print(list2,"模糊查询结果: 条数:" + list2.size());
	}
	
	/**
	 * 分页 (带条件的)
	 *
	 * @author 	: <a href="mailto:hubo@95190.com">hubo</a>  2015-5-19 下午2:28:28
	 */
	@Test public void findPageListTest(){
		
		SystemUser SystemUser = new SystemUser();
		SystemUser.setLoginName("aaa");
		Long total = systemUserService.getTotalCount(SystemUser);
		print(total,"名字是like aaa的条数");
		
		Pager pager = new Pager(1,3,total);
		pager =systemUserService.findPageList(SystemUser, pager);
		
		print(pager,"分页");
	}
	
	/**
	 * 分页 (不带条件的)
	 *
	 * @author 	: <a href="mailto:hubo@95190.com">hubo</a>  2015-5-19 下午2:28:28
	 */
	@Test public void findPageListTest2(){
		
		SystemUser SystemUser = new SystemUser();
		Long total = systemUserService.getTotalCount(SystemUser);
		print(total,"不带条件的的条数");
		
		Pager pager = new Pager(1,3,total);
		pager =systemUserService.findPageList(SystemUser, pager);
		
		print(pager,"分页");
	}
	
	
	/**
	 * update()修改方法测试
	 *
	 * @author 	: <a href="mailto:hubo@95190.com">hubo</a>  2015-5-19 下午1:50:35
	 */
	@Test public void updateTest(){
		SystemUser SystemUser = new SystemUser();
		SystemUser.setPassword(Md5.md5("123"));
		
		int count = systemUserService.update(SystemUser);
		print(SystemUser,"修改" + count+"条数据");
	}
	
	/**
	 * save()保存方法
	 *
	 * @author 	: <a href="mailto:hubo@95190.com">hubo</a>  2015-5-19 下午1:55:57
	 */
	@Test public void saveTest(){
		SystemUser systemUser = new SystemUser();
		systemUser.setUserName("李四");
		systemUser.setLoginName("admin22");
		systemUser.setPassword(Md5.md5("123"));
		systemUser.setStatus(1);
		systemUser.setDeptId(1l);
		systemUser.setPhoneNumber("158213612");
		systemUser.setPassword(Md5.md5("123"));
		systemUser.setCreateTime(new Date());
		
		int count = systemUserService.save(systemUser); //条数
		print(systemUser,"添加" + count +"条数据");
	}
	
	/**
	 * 批量添加
	 *
	 * @author 	: <a href="mailto:hubo@95190.com">hubo</a>  2015-5-19 下午2:19:22
	 */
	@Test public void saveBatchTest(){
		List<SystemUser> list = new ArrayList<SystemUser>();
		for(int i = 10; i < 40; i++){
			SystemUser systemUser = new SystemUser();
			systemUser.setLoginName("jhson" + i);
			systemUser.setUserName("张三" + i);
			systemUser.setStatus(1);
			systemUser.setPhoneNumber("135213612" + i);
			systemUser.setPassword(Md5.md5("123"));
			systemUser.setCreateTime(new Date());
			if(i %2 == 0){
				systemUser.setAddress("北京");
				systemUser.setSex("1");
			}else{
				systemUser.setSex("2");
				systemUser.setAddress("上海");
			}
			systemUser.setEmail("abc" + i + "@163.com");
			
			
			list.add(systemUser);
		}
		
		int count = systemUserService.saveBatch(list, SystemUser.class);
		print("批量添加了" + count +"条数据");
	}
	
	/**
	 * 测试delete()方法
	 *
	 * @author 	: <a href="mailto:hubo@95190.com">hubo</a>  2015-5-19 下午2:02:04
	 */
	@Test public void deleteTest(){
		int count = systemUserService.delete(6l, SystemUser.class);
		print("删除" + count +"条数据");
	}
	
	
	/**
	 * delete()批量方法
	 *
	 * @author 	: <a href="mailto:hubo@95190.com">hubo</a>  2015-5-19 下午2:02:04
	 */
	@Test public void deleteBatchTest(){
		List<String> list = Arrays.asList("8","9","10");//删除id in (8,9,10)
		
		int count = systemUserService.deleteBatch(list, SystemUser.class);
		print("删除" + count +"条数据");
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
