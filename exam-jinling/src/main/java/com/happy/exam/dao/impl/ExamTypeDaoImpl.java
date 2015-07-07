package com.happy.exam.dao.impl;  
import java.util.List;

import org.springframework.stereotype.Service;

import com.happy.exam.dao.ExamTypeDao;
import com.happy.exam.model.ExamType;

/**
 *  ExamTypeDao
 *
 * @version : Ver 1.0
 * @author	: <a href="mailto:hubo@95190.com">hubo</a>
 * @date	: 2015年5月17日 下午9:01:26 
 */
@Service
public class ExamTypeDaoImpl extends MybatisBaseDaoImpl<ExamType, java.lang.Long> implements ExamTypeDao { 
	private final String CLZZ_NAME = ExamType.class.getName();
	
	private final String FIND_TREE_GRID = CLZZ_NAME + ".findTreegrid";
	
	private final String DELETE_UNION = CLZZ_NAME + ".deleteUnion";
	
	@Override
	public List<ExamType> findTreegrid(ExamType examType) {
		
		return this.getSqlSessionTemplate().selectList(FIND_TREE_GRID, examType);
	}

	@Override
	public int deleteUnion(ExamType examType) {
		return  this.getSqlSessionTemplate().delete(DELETE_UNION, examType);
	} 
}
