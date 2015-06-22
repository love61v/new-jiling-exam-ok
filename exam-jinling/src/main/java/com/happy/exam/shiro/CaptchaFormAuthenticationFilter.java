package com.happy.exam.shiro;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;

/**
 * 验证码过滤器
 * 
 * @version : Ver 1.0
 * @author : <a href="mailto:hubo@95190.com">hubo</a>
 * @date : 2015-5-20 下午6:16:14
 */
public class CaptchaFormAuthenticationFilter extends FormAuthenticationFilter {

	public static final String DEFAULT_CAPTCHA_PARAM = "captcha";
	private String captchaParam = DEFAULT_CAPTCHA_PARAM;

	public String getCaptchaParam() {
		return captchaParam;
	}

	public void setCaptchaParam(String captchaParam) {
		this.captchaParam = captchaParam;
	}

	@Override
	protected CaptchaUsernamePasswordToken createToken(ServletRequest request,
			ServletResponse response) {

		return new CaptchaUsernamePasswordToken(getUsername(request),
				getPassword(request).toCharArray(), isRememberMe(request),
				getHost(request), getCaptcha(request));
	}

	/*@Override
	protected boolean executeLogin(ServletRequest request,
			ServletResponse response) throws Exception {
		CaptchaUsernamePasswordToken token = this
				.createToken(request, response);

		try {
			 图形验证码验证 
			doCaptchaValidate((HttpServletRequest) request, token);

			Subject subject = getSubject(request, response);
			subject.login(token);// 正常验证

			return onLoginSuccess(token, subject, request, response);
		} catch (AuthenticationException e) {
			return onLoginFailure(token, e, request, response);
		}
	}*/

	/**
	 * 对比验证码
	 * 
	 * @author : <a href="mailto:hubo@95190.com">hubo</a> 2015-5-20 下午6:32:00
	 * @param request
	 * @param token
	 *//*
	private void doCaptchaValidate(HttpServletRequest request,
			CaptchaUsernamePasswordToken token) {
		// session中的图形码字符串
		String captcha = (String) request.getSession().getAttribute(
				com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);
		// 比对
		if (captcha != null && !captcha.equalsIgnoreCase(token.getCaptcha())) {
			throw new IncorrectCaptchaException("验证码错误！");
		}
	}
*/
	/*@Override
	protected void setFailureAttribute(ServletRequest request,
			AuthenticationException ae) {
		request.setAttribute(getFailureKeyAttribute(), ae);
	}*/

	protected String getCaptcha(ServletRequest request) {
		return WebUtils.getCleanParam(request, getCaptchaParam());
	}

}
