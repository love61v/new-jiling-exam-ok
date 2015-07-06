package com.happy.exam.dao;  
import java.util.List;
import java.util.Map;

import com.happy.exam.common.bean.UserGroupModel;
import com.happy.exam.common.bean.UserRolePermissionModel;
import com.happy.exam.dao.BaseDao;
import com.happy.exam.model.SystemRole;
import com.happy.exam.model.SystemUser;

/**
 *  SystemUserDao
 *
 * @version : Ver 1.0
 * @author	: <a href="mailto:hubo@95190.com">hubo</a>
 * @date	: 2015年5月17日 下午9:01:26 
 */
public interface SystemUserDao extends BaseDao<SystemUser,java.lang.Long>{

	List<UserGroupModel> findUserByGroupId(Map<String, Object> params);

	List<SystemRole> findRoleListByUserId(Long userId);

	List<UserRolePermissionModel> findPermissionsByUserId(Long userId);

}
