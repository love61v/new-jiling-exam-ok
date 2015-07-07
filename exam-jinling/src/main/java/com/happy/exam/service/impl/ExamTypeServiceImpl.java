package com.happy.exam.service.impl;  
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.happy.exam.dao.ExamTypeDao;
import com.happy.exam.model.ExamType;
import com.happy.exam.service.ExamTypeService;

/**
 *  ExamTypeServiceImpl
 *
 * @version : Ver 1.0
 * @author	: <a href="mailto:hubo@95190.com">hubo</a>
 * @date	: 2015年5月17日 下午9:01:26 
 */
@Service
public class ExamTypeServiceImpl extends MybatisBaseServiceImpl<ExamType, java.lang.Long> implements ExamTypeService {

	@Autowired
	private ExamTypeDao examTypeDao;
	
	@Override
	public List<ExamType> findTreegrid(ExamType examType) {
		
		return examTypeDao.findTreegrid(examType);
	}

	@Override
	public int deleteUnion(ExamType examType) {
		return examTypeDao.deleteUnion(examType);
	} 

}
