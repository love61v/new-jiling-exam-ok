package com.happy.exam.service;   
import java.util.List;

import com.happy.exam.model.SystemRoleGroup;
import com.happy.exam.model.SystemUserGroup;

/**
 *  SystemUserGroupService
 *
 * @version : Ver 1.0
 * @author	: <a href="mailto:hubo@95190.com">hubo</a>
 * @date	: 2015年5月17日 下午9:01:26 
 */
public interface SystemUserGroupService extends BaseService<SystemUserGroup,java.lang.Long>{

	/**
	 * 根据groupId与userID删除
	 *
	 * @author 	: <a href="mailto:h358911056@qq.com">hubo</a>  2015年6月29日 上午12:01:38
	 * @param groupId
	 * @param asList
	 * @return
	 */
	long deleteUnion(String groupId, List<String> asList);
}
