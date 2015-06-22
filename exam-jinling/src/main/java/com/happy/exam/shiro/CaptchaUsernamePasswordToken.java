package com.happy.exam.shiro;

import org.apache.shiro.authc.UsernamePasswordToken;

/**
 * 登陆令牌
 *
 * @version : Ver 1.0
 * @author	: <a href="mailto:hubo@95190.com">hubo</a>
 * @date	: 2015-5-20 下午6:22:41 
 */
public class CaptchaUsernamePasswordToken extends UsernamePasswordToken{
	 
	private static final long serialVersionUID = 1L;
	
	//验证码字符串  
    private String captcha;
    
    public CaptchaUsernamePasswordToken(String username, char[] password,  
            boolean rememberMe, String host, String captcha) {  
        super(username, password, rememberMe, host);  
        this.captcha = captcha;  
    }  
  
    public String getCaptcha() {  
        return captcha;  
    }  
  
    public void setCaptcha(String captcha) {  
        this.captcha = captcha;  
    }  
}
