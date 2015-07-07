package com.happy.exam.dao;  
import java.util.List;

import com.happy.exam.model.ExamType;

/**
 *  ExamTypeDao
 *
 * @version : Ver 1.0
 * @author	: <a href="mailto:hubo@95190.com">hubo</a>
 * @date	: 2015年5月17日 下午9:01:26 
 */
public interface ExamTypeDao extends BaseDao<ExamType,java.lang.Long>{

	List<ExamType> findTreegrid(ExamType examType);

	int deleteUnion(ExamType examType);

}
