package com.happy.exam.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.happy.exam.common.utils.Md5;
import com.happy.exam.model.SystemUser;
import com.happy.exam.service.SystemUserService;
import com.happy.exam.shiro.CaptchaUsernamePasswordToken;
import com.happy.exam.shiro.IncorrectCaptchaException;

/**
 * 登陆action
 *
 * @version : Ver 1.0
 * @author : <a href="mailto:hubo@95190.com">hubo</a>
 * @date : 2015年5月17日 上午2:24:46
 */
@Controller
public class LoginAction extends BaseAction{
	
	@Autowired
	private SystemUserService systemUserService;

	/**
	 * 跳转到登陆页面o
	 *
	 * @author : <a href="mailto:hubo@95190.com">hubo</a> 2015年5月16日 下午11:48:57
	 * @return
	 */
	@RequestMapping(value = "/login.html", method = RequestMethod.GET)
	public String loginPage() {
		return "login";
	}

	/**
	 *  登陆
	 * 当前用户是管理员则进入后台管理页面，否则进入考生页面
	 *
	 * @author 	: <a href="mailto:hubo@95190.com">hubo</a>  2015年5月17日 上午2:30:05
	 * @param model
	 * @param username
	 * @param password
	 * @return
	 */
	@RequestMapping(value = "/login.html", method = RequestMethod.POST)
	public String dologin(Model model, String username, String password,
			String captcha,HttpServletRequest request) {
		String errorMessage = ""; //登陆提示信息
		boolean isAuth = false; //标识认证用户
		
		CaptchaUsernamePasswordToken token = new CaptchaUsernamePasswordToken(username, 
				Md5.md5(password).toCharArray(), false, "", captcha);
		Subject currentSystemUser = SecurityUtils.getSubject();
		
		try {
			currentSystemUser.login(token); // 登陆
			isAuth = currentSystemUser.isAuthenticated();
			if(currentSystemUser.isAuthenticated()){//身份认证成功
				SystemUser user = (SystemUser) currentSystemUser.getPrincipal();
				super.setCurrentSystemUser(user);
				
				Session session = currentSystemUser.getSession(false);
				session.setTimeout(3600000);
				
				return "system/admin8";
			}
		} catch (UnknownAccountException e) {
			errorMessage = "用户名/密码错误";
		} catch (IncorrectCredentialsException e) {
			errorMessage = "密码错误";
		} catch (LockedAccountException e) {
			errorMessage = "用户锁定";
		} catch (DisabledAccountException e) {
			errorMessage = "用户被禁止";
		} catch (IncorrectCaptchaException e) {
			errorMessage = "验证码错误";
		}
		
		if(!isAuth){
			model.addAttribute("userName", username);
			model.addAttribute("captcha", captcha);
			model.addAttribute("errorMessage", errorMessage);
			return "login";
		}

		return null;
	}

	/**
	 * 退出
	 *
	 * @author 	: <a href="mailto:hubo@95190.com">hubo</a>  2015年5月17日 下午5:40:24
	 */
	@RequestMapping(value="/logout.html",method=RequestMethod.GET)
	public String logout(){
		Subject currentSystemUser = SecurityUtils.getSubject();
		currentSystemUser.logout(); //退出
		
		return "redirect: /login.html";
	}
	
}
