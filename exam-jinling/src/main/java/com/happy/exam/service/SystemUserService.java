package com.happy.exam.service;   
import java.util.List;

import com.happy.exam.common.bean.UserGroupModel;
import com.happy.exam.common.bean.UserRolePermissionModel;
import com.happy.exam.model.SystemRole;
import com.happy.exam.model.SystemUser;

/**
 *  SystemUserService
 *
 * @version : Ver 1.0
 * @author	: <a href="mailto:hubo@95190.com">hubo</a>
 * @date	: 2015年5月17日 下午9:01:26 
 */
public interface SystemUserService extends BaseService<SystemUser,java.lang.Long>{

	/**
	 * 根据groupId查询用户列表
	 *
	 * @author 	: <a href="mailto:h358911056@qq.com">hubo</a>  2015年6月28日 下午10:43:15
	 * @param user 
	 * @param groupId 组ID 
	 * @return
	 */
	List<UserGroupModel> findUserByGroupId(SystemUser user,Long groupId);
	
	/**
	 * 根据UserId查找其拥有的角色
	 *
	 * @author 	: <a href="mailto:358911056@qq.com">hubo</a>  2015-7-1 下午1:45:21
	 * @param userId
	 * @return 角色英文标识名
	 */
	List<SystemRole> findRoleListByUserId(Long userId);
	
	/**
	 * 根据userId查其所有的资源与操作
	 *
	 * @author 	: <a href="mailto:358911056@qq.com">hubo</a>  2015-7-1 下午3:12:40
	 * @param userId
	 * @return
	 */
	List<UserRolePermissionModel> findPermissionsByUserId(Long userId);
	
 
}
