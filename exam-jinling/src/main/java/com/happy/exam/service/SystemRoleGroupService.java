package com.happy.exam.service;   
import java.util.List;

import com.happy.exam.model.SystemRole;
import com.happy.exam.model.SystemRoleGroup;

/**
 *  SystemRoleGroupService
 *
 * @version : Ver 1.0
 * @author	: <a href="mailto:hubo@95190.com">hubo</a>
 * @date	: 2015年5月17日 下午9:01:26 
 */
public interface SystemRoleGroupService extends BaseService<SystemRoleGroup,java.lang.Long>{

	/**
	 * 根据groupId查组的所有角色
	 *
	 * @author 	: <a href="mailto:h358911056@qq.com">hubo</a>  2015年7月1日 上午1:04:28
	 * @param groupId 组ID
	 * @return 角色集合
	 */
	List<SystemRole> findRoleByGroupId(Long groupId);
	
	/**
	 * 删除组下的角色
	 *
	 * @author 	: <a href="mailto:h358911056@qq.com">hubo</a>  2015年7月1日 上午1:22:26
	 * @param systemRoleGroup
	 * @return
	 */
	long deleteGroupRole(SystemRoleGroup systemRoleGroup);

}
