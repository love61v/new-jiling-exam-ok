package com.happy.exam.dao;  
import java.util.Map;

import com.happy.exam.dao.BaseDao;
import com.happy.exam.model.SystemRoleGroup;
import com.happy.exam.model.SystemUserGroup;

/**
 *  SystemUserGroupDao
 *
 * @version : Ver 1.0
 * @author	: <a href="mailto:hubo@95190.com">hubo</a>
 * @date	: 2015年5月17日 下午9:01:26 
 */
public interface SystemUserGroupDao extends BaseDao<SystemUserGroup,java.lang.Long>{

	/**
	 * 根据groupId,与userID删除
	 *
	 * @author 	: <a href="mailto:h358911056@qq.com">hubo</a>  2015年6月29日 上午12:04:33
	 * @param params
	 * @return
	 */
	long deleteUnion(Map<String, Object> params);
}
