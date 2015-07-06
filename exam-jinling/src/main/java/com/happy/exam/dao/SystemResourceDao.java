package com.happy.exam.dao;  
import java.util.List;

import com.happy.exam.dao.BaseDao;
import com.happy.exam.model.SystemResource;

/**
 *  SystemResourceDao
 *
 * @version : Ver 1.0
 * @author	: <a href="mailto:hubo@95190.com">hubo</a>
 * @date	: 2015年5月17日 下午9:01:26 
 */
public interface SystemResourceDao extends BaseDao<SystemResource,java.lang.Long>{

	List<SystemResource> findTreegrid(SystemResource systemResource);

	int deleteUnion(SystemResource systemResource);

	List<SystemResource> findUserModuleTree(Long userId);

}
