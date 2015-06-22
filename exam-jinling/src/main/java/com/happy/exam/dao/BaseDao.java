/*
 * Beijing happy Information Technology Co,Ltd.
 * All rights reserved.
 * 
 * <p>BaseDao.java</p>
 */
package com.happy.exam.dao;

import java.io.Serializable;
import java.util.List;

import com.happy.exam.common.pager.Pager;

/**
 * dao基础类
 *
 * @version : Ver 1.0
 * @author : <a href="mailto:hubo@95190.com">hubo</a>
 * @date : 2015年5月17日 下午8:55:47
 */
public interface BaseDao<T, PK extends Serializable> {
	

	/**
	 * 按主键查找对象
	 *
	 * @author 	: <a href="mailto:hubo@95190.com">hubo</a>  2015年5月17日 下午10:47:11
	 * @param pk 主键
	 * @param clzz 实体bean
	 * @return
	 */
	T getById(PK pk,Class<T> clzz);

	/**
	 * 按条件查询返回单个对象实体
	 *
	 * @author : <a href="mailto:hubo@95190.com">hubo</a> 2015年5月17日 下午8:59:30
	 * @param obj
	 * @return
	 */
	T getUniqueObject(T obj);

	/**
	 * 获取总记录数
	 * 
	 * @author : <a href="mailto:hubo@95190.com">hubo</a> 2015-4-21 上午8:27:48
	 * @param obj
	 *            实体对象
	 * @return
	 */
	Long getTotalCount(T obj);

	/**
	 * 按实体对象属性动态查找列表
	 * 
	 * @author : <a href="mailto:hubo@95190.com">hubo</a> 2015-4-21 上午8:27:13
	 * @param obj
	 *            实体对象
	 * @return
	 */
	List<T> findList(T obj);

	/**
	 * 分页
	 * 
	 * @author : <a href="mailto:hubo@95190.com">hubo</a> 2015-4-21 上午8:28:58
	 * @param pageResult
	 *            分页实体bean
	 * @param obj
	 *            实体对象
	 * @return 分页实体对象
	 */
	Pager findPageList(T obj,Pager pager);

	/**
	 * 保存
	 * 
	 * @author : <a href="mailto:hubo@95190.com">hubo</a> 2015-4-21 上午8:29:35
	 * @param obj
	 * @return
	 */
	int save(T obj);
	
	/**
	 * 批量保存
	 *
	 * @author 	: <a href="mailto:hubo@95190.com">hubo</a>  2015年5月17日 下午10:49:48
	 * @param list 实体bean集合
	 * @param clzz 实体类
	 * @return
	 */
	int saveBatch(List<T> list,Class<T> clzz);

	/**
	 * 更新
	 * 
	 * @author : <a href="mailto:hubo@95190.com">hubo</a> 2015-4-21 上午8:29:41
	 * @param obj
	 * @return
	 */
	int update(T obj);

	/**
	 * 删除
	 * 
	 * @author : <a href="mailto:hubo@95190.com">hubo</a> 2015-4-21 上午8:29:49
	 * @param pk
	 * @param clzz 实体bean
	 * @return
	 */
	int delete(PK pk,Class<T> clzz);

	/**
	 * 批量删除
	 * 
	 * @author : <a href="mailto:hubo@95190.com">hubo</a> 2015-4-21 上午8:29:57
	 * @param list
	 * @param clzz 实体bean
	 * @return
	 */
	int deleteBatch(List<String> list,Class<T> clzz);

}
