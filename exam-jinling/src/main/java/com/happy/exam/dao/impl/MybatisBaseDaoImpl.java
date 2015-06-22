/*
 * Beijing happy Information Technology Co,Ltd.
 * All rights reserved.
 * 
 * <p>MybatisBaseDaoImpl.java</p>
 */
package com.happy.exam.dao.impl;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.happy.exam.common.exception.BizzException;
import com.happy.exam.common.pager.Pager;
import com.happy.exam.dao.BaseDao;

/**
 * 
 *
 * @version : Ver 1.0
 * @author : <a href="mailto:hubo@95190.com">hubo</a>
 * @date : 2015年5月17日 下午9:37:03
 */
@Repository
public class MybatisBaseDaoImpl<T, PK extends Serializable> implements BaseDao<T, PK> {
	
	/**
	 * 插入
	 */
	public String INSERT = ".insert";

	/**
	 * 批量插入
	 */
	public String INSERT_BATCH = ".insertBatch";

	/**
	 * 更新
	 */
	public String UPDATE = ".update";

	/**
	 * 根据ID 删除
	 */
	public String DELETE = ".delete";
	
	/**
	 * 根据ID串批量 删除
	 */
	public String DELETE_BATCH = ".delete_batch";

	/**
	 * 根据ID 查询
	 */
	public String GETBYID = ".getById";

	/**
	 * 根据条件 分页查询
	 */
	public String COUNT = ".findPage_count";
	/**
	 * 根据条件 分页查询
	 */
	public String PAGESELECT = ".findPage";

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;


	public SqlSessionTemplate getSqlSessionTemplate() {
		return sqlSessionTemplate;
	}

	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		this.sqlSessionTemplate = sqlSessionTemplate;
	}

	@SuppressWarnings("unchecked")
	@Override
	public T getById(PK pk, Class<T> cls) { 
		return (T) sqlSessionTemplate.selectOne(cls.getName() + GETBYID, pk);
	}

	@Override
	public T getUniqueObject(T obj) {
		if (null == obj) {
			throw new BizzException(" T can not be null");
		}
		List<T> list = this.findList(obj);

		return (null != list && list.size() > 0 ? (T) list.get(0) : null);
	}

	@Override
	public Long getTotalCount(T obj) {
		if (null == obj) {
			throw new BizzException(" T can not be null");
		}
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("model", obj);
		
		return sqlSessionTemplate.selectOne(obj.getClass().getName() + COUNT, params);
	}

	@Override
	public List<T> findList(T obj) {
		if (null == obj) {
			throw new BizzException(" T can not be null");
		}

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("model", obj);
		
		List<T> list = sqlSessionTemplate.selectList(
				obj.getClass().getName() + PAGESELECT, params);
		return list;
	}

	@Override
	public Pager findPageList(T obj, Pager pager) {
		if (null == obj || null == pager) {
			throw new BizzException(" T or pager can not be null");
		}

		if(pager.getTotalCount() > 0){
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("pageSize", pager.getPageSize());
			params.put("offset", (pager.getCurrentPage()-1)* pager.getPageSize());
			params.put("model", obj);
			
			List<T> list = sqlSessionTemplate.selectList(
					obj.getClass().getName() + PAGESELECT, params);
			
			pager.setDatas(list); //数据库集合
		}
		
		return pager;
	}

	@Override
	public int save(T obj) {
		if (null == obj) {
			throw new BizzException(" T can not be null");
		}

		return sqlSessionTemplate.insert(obj.getClass().getName() + INSERT, obj);
	}

	@Override
	public int update(T obj) {
		if (null == obj) {
			throw new BizzException(" T can not be null");
		}

		return sqlSessionTemplate.update(obj.getClass().getName() + UPDATE, obj);
	}

	@Override
	public int delete(PK pk, Class<T> cls) {
		return sqlSessionTemplate.delete(cls.getName() + DELETE, pk);
	}

	@Override
	public int deleteBatch(List<String> list, Class<T> cls) {
		return sqlSessionTemplate.delete(cls.getName() + DELETE_BATCH, list);
	}

	@Override
	public int saveBatch(List<T> list, Class<T> clzz) {
		return sqlSessionTemplate.insert(clzz.getName() + INSERT_BATCH, list);
	}

}
