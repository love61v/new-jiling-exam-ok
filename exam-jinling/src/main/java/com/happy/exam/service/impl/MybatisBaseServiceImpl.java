/*
 * Beijing YESWAY Information Technology Co,Ltd.
 * All rights reserved.
 * 北京九五智驾信息技术股份有限公司
 * <p>MybatisBaseServiceImpl.java</p>
 */
package com.happy.exam.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.happy.exam.common.pager.Pager;
import com.happy.exam.dao.BaseDao;
import com.happy.exam.service.BaseService;

/**
 * service基础实现类
 *
 * @version : Ver 1.0
 * @author	: <a href="mailto:hubo@95190.com">hubo</a>
 * @date	: 2015-5-19 上午11:05:12 
 */

@Service
public class MybatisBaseServiceImpl<T, PK extends Serializable> implements BaseService<T,PK> {
	
	@Autowired
	private BaseDao<T, PK> baseDao;

	@Override
	public T getById(PK pk, Class<T> clzz) {
		
		return baseDao.getById(pk, clzz);
	}

	@Override
	public T getUniqueObject(T obj) {
		
		return baseDao.getUniqueObject(obj);
	}

	@Override
	public Long getTotalCount(T obj) {
		
		return baseDao.getTotalCount(obj);
	}

	@Override
	public List<T> findList(T obj) {
		
		return baseDao.findList(obj);
	}

	@Override
	public Pager findPageList(T obj, Pager pager) {
		
		return baseDao.findPageList(obj, pager);
	}

	@Override
	public int save(T obj) {
		
		return baseDao.save(obj);
	}

	@Override
	public int saveBatch(List<T> list, Class<T> clzz) {
		
		return baseDao.saveBatch(list, clzz);
	}

	@Override
	public int update(T obj) {
		
		return baseDao.update(obj);
	}

	@Override
	public int delete(PK pk, Class<T> clzz) {
		
		return baseDao.delete(pk, clzz);
	}

	@Override
	public int deleteBatch(List<String> list, Class<T> clzz) {
		
		return baseDao.deleteBatch(list, clzz);
	}
}
