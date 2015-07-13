package com.happy.exam.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.happy.exam.common.bean.ExamQuestionModel;
import com.happy.exam.common.enums.ExamTypeEnum;
import com.happy.exam.common.enums.ResponseCodeEnum;
import com.happy.exam.common.utils.NetUtil;
import com.happy.exam.common.utils.PoiUtil;
import com.happy.exam.model.BaseModel;
import com.happy.exam.model.SystemLog;
import com.happy.exam.model.SystemUser;
import com.happy.exam.service.SystemLogService;

/**
 * 控制器基类.
 * 
 * @version : Ver 1.0
 * @author : <a href="mailto:hubo@95190.com">hubo</a>
 * @date : 2015年5月17日 下午5:41:39
 */
public class BaseAction {

	private final static ThreadLocal<SystemUser> userThreadLocal = new ThreadLocal<SystemUser>();
	
	@Autowired
	private SystemLogService systemLogService;

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
	 * 获取当前用户的ID
	 * 
	 * @author : <a href="mailto:358911056@qq.com">hubo</a> 2015-7-8 下午4:05:51
	 * @return
	 */
	public Long getCurrentUserId() {
		return getCurrentSystemUser().getUserId();
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
	 * @author : <a href="mailto:h358911056@qq.com">hubo</a> 2015年6月7日 下午3:37:52
	 * @return
	 */
	public static Map<String, Object> getStatusMap() {
		Map<String, Object> map = new HashMap<String, Object>();
		int code = ResponseCodeEnum.CODE_0.getKey(); // 默认状态码
		map.put("status", code);

		return map;
	}

	/**
	 * 设置创建用户
	 * 
	 * @author : <a href="mailto:358911056@qq.com">hubo</a> 2015-7-6 上午10:07:54
	 * @param model
	 *            当前操作的model
	 */
	public void setCreateUser(BaseModel model) {
		model.setCreateUser(getCurrentSystemUser().getUserId());
	}

	/**
	 * 验证当前用户是否有此角色
	 * 
	 * @author : <a href="mailto:358911056@qq.com">hubo</a> 2015-7-6 下午1:22:52
	 * @param roleIdentifier
	 *            角色标识字符串
	 * @return
	 */
	public boolean hasRole(String roleIdentifier) {
		return SecurityUtils.getSubject().hasRole(roleIdentifier);
	}

	/**
	 * 解析excel
	 *
	 * @author 	: <a href="mailto:954643309@qq.com">hgx</a>  2015-7-8 下午10:33:13
	 * @param file
	 * @param typeId
	 * @return
	 */
	public List<?> parseExcel(MultipartFile file,ExamTypeEnum examTypeEnum, Long typeId) {
		List<ExamQuestionModel> list = null;
		if (!file.isEmpty()) {
			String filename = file.getOriginalFilename();
			if (filename.endsWith("xls") || filename.endsWith("xlsx")) {
				InputStream fileIn;
				try {
					fileIn = file.getInputStream();
					ExamQuestionModel model = new ExamQuestionModel();
					model.setCreateUser(this.getCurrentUserId());
					model.setCreateTime(new Date());
					model.setTypeId(typeId); // 类型

					list = PoiUtil.parseList(fileIn, model, examTypeEnum);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return list;
	}
	
	/**
	 * 日志记录到数据库中
	 */
	public void log(HttpServletRequest request,String content){
		SystemUser user = getCurrentSystemUser();
		SystemLog log = new SystemLog();
		
		log.setContent(content);
		log.setCreateTime(new Date());
		log.setIp(NetUtil.getIpAddr(request));
		log.setCurrentUser(user.getUserName());
		log.setCreateUser(user.getUserId());
		
		systemLogService.save(log); //保存
	}
	
	/**
	 * 转换json字符
	 * @param obj 任意对象
	 * @return
	 */
	public String toJson(Object obj){
		return JSON.toJSONStringWithDateFormat(obj, "yyyy-MM-dd HH:mm:ss");
	}

}