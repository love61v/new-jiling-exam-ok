package com.happy.exam.controller;

import java.util.HashMap;
import java.util.Map;

import org.apache.shiro.SecurityUtils;

import com.happy.exam.common.enums.ResponseCodeEnum;
import com.happy.exam.model.BaseModel;
import com.happy.exam.model.SystemUser;

/**
 * 控制器基类.
 *
 * @version : Ver 1.0
 * @author : <a href="mailto:hubo@95190.com">hubo</a>
 * @date : 2015年5月17日 下午5:41:39
 */
public class BaseAction {

	private final static ThreadLocal<SystemUser> userThreadLocal = new ThreadLocal<SystemUser>();

	/**
	 * 从本地线程中取当前用户
	 *
	 * @author : <a href="mailto:hubo@95190.com">hubo</a> 2015年5月19日 上午12:03:04
	 * @return 系统用户
	 */
	public SystemUser getCurrentSystemUser() {
		SystemUser user = userThreadLocal.get();
		if (null == user) {
			user = (SystemUser) SecurityUtils.getSubject().getPrincipal();
		}
		
		return user;
	}

	/**
	 * 设置当前用户
	 *
	 * @author : <a href="mailto:hubo@95190.com">hubo</a> 2015年5月19日 上午12:03:29
	 * @param user
	 */
	public void setCurrentSystemUser(SystemUser user) {
		userThreadLocal.set(user);
	}
	
	/**
	 * 获取状态码的map
	 *
	 * @author 	: <a href="mailto:h358911056@qq.com">hubo</a>  2015年6月7日 下午3:37:52
	 * @return
	 */
	public static Map<String, Object> getStatusMap(){
		Map<String, Object> map = new HashMap<String, Object>();
		int code = ResponseCodeEnum.CODE_0.getKey(); //默认状态码
		map.put("status", code);
		
		return map;
	}
	
	/**
	 * 设置创建用户
	 *
	 * @author 	: <a href="mailto:358911056@qq.com">hubo</a>  2015-7-6 上午10:07:54
	 * @param model 当前操作的model
	 */
	public void setCreateUser(BaseModel model){
		model.setCreateUser(getCurrentSystemUser().getUserId());
	}
	
	/**
	 * 验证当前用户是否有此角色
	 *
	 * @author 	: <a href="mailto:358911056@qq.com">hubo</a>  2015-7-6 下午1:22:52
	 * @param roleIdentifier  角色标识字符串
	 * @return
	 */
	public boolean hasRole(String roleIdentifier){
		return SecurityUtils.getSubject().hasRole(roleIdentifier);
	}
}
