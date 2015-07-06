package com.happy.exam.shiro;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.happy.exam.common.bean.UserRolePermissionModel;
import com.happy.exam.model.SystemRole;
import com.happy.exam.model.SystemUser;
import com.happy.exam.service.SystemUserService;

/**
 * shiro登陆认证与授权realm.
 *
 * @version : Ver 1.0
 * @author	: <a href="mailto:hubo@95190.com">hubo</a>
 * @date	: 2015-5-18 下午4:55:59 
 */
public class AuthenticationRealm extends AuthorizingRealm {
	
	private final static Logger logs = LoggerFactory.getLogger(AuthenticationRealm.class);
	
	@Autowired
	private SystemUserService systemUserService;
	

	/**
	 * 认证回调函数,登录时调用.
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken)
			throws AuthenticationException {
		logs.debug("登陆认证回调函数");
		
		//SystemUsernamePasswordToken token = (SystemUsernamePasswordToken) authcToken;
		CaptchaUsernamePasswordToken token = (CaptchaUsernamePasswordToken) authcToken;
		String userName = token.getUsername(); // 用户名
		String password = new String(token.getPassword()); // 密码
		
		// 增加判断验证码逻辑
		String captcha = token.getCaptcha();
		String exitCode = (String) SecurityUtils.getSubject().getSession()
				.getAttribute(CaptchaServlet.KEY_CAPTCHA);
		
		if (null == captcha || !captcha.equalsIgnoreCase(exitCode)) {
			throw new IncorrectCaptchaException("验证码错误");
		}

		if (StringUtils.isNotBlank(userName) && StringUtils.isNotBlank(password)) {
			SystemUser loginSystemUser = new SystemUser();
			loginSystemUser.setLoginName(userName);
			loginSystemUser.setPassword(password);
			
			SystemUser user = systemUserService.getUniqueObject(loginSystemUser);
			if (user == null) {
				throw new UnknownAccountException();
			}

			SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
					user, user.getPassword(), getName());

			return authenticationInfo;
		} else {
			return null;
		}
	}

	/**
	 *授权查询回调函数, 进行鉴权但缓存中无用户的授权信息时调用
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		logs.debug("授权查询角色与操作权...");
		SystemUser user = (SystemUser) principals.getPrimaryPrincipal();
		
		if (user != null && StringUtils.isNotBlank(user.getLoginName())) {
			SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
			
			Long userId = user.getUserId(); //用户ID
			List<SystemRole> rolesList = systemUserService.findRoleListByUserId(userId);
			List<String> roles = new ArrayList<String>();
			for(SystemRole role : rolesList){
				roles.add(role.getEngName());
			}
			info.addRoles(roles); //添加角色
			
			List<UserRolePermissionModel> permissions = systemUserService.findPermissionsByUserId(userId);
			if(null != permissions){
				List<String> tempList = new ArrayList<String>();
				String resource = ""; //模块英文名
				String opers = ""; //操作id串
				for(UserRolePermissionModel permission : permissions){
					resource = permission.getModuleEngName();
					opers = permission.getOperateId();
					if(StringUtils.isNotBlank(opers)){
						for(String id : opers.split(",")){
							tempList.add(resource.concat(":").concat(id));
						}
					}
				}
				info.addStringPermissions(tempList);//添加权限
			}
			
			return info;
		}
		return null;
	}
	 

	@Override
	protected void clearCachedAuthorizationInfo(PrincipalCollection principals) {
		logs.debug("退出清除缓存中的授权信息....");
		super.clearCachedAuthorizationInfo(principals);
	}
	
	/**
	 * 更新用户授权信息缓存.
	 */
	public void clearCachedAuthorizationInfo(String principal) {
		SimplePrincipalCollection principals = new SimplePrincipalCollection(
				principal, getName());
		clearCachedAuthorizationInfo(principals);
	}
 
	/**
	 * 清除所有用户授权信息缓存.
	 */
	public void clearAllCachedAuthorizationInfo() {
		Cache<Object, AuthorizationInfo> cache = getAuthorizationCache();
		if (cache != null) {
			for (Object key : cache.keys()) {
				cache.remove(key);
			}
		}
	}
 
	 
	 
}