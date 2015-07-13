package com.happy.exam.controller.user;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.happy.exam.common.bean.DataGridModel;
import com.happy.exam.common.bean.UserGroupModel;
import com.happy.exam.common.dto.DatagridDto;
import com.happy.exam.common.pager.Pager;
import com.happy.exam.common.utils.ConstantsUtil;
import com.happy.exam.common.utils.Md5;
import com.happy.exam.controller.BaseAction;
import com.happy.exam.model.Department;
import com.happy.exam.model.SystemRole;
import com.happy.exam.model.SystemUser;
import com.happy.exam.model.SystemUserRole;
import com.happy.exam.service.DepartmentService;
import com.happy.exam.service.SystemRoleService;
import com.happy.exam.service.SystemUserRoleService;
import com.happy.exam.service.SystemUserService;

/**
 * 用户管理action.
 *
 * @version : Ver 1.0
 * @author	: <a href="mailto:hubo@95190.com">hubo</a>
 * @date	: 2015-5-19 下午5:06:02 
 */
@Controller
@RequestMapping("/user")
public class UserAction extends BaseAction {
	private final static Logger logs = LoggerFactory.getLogger(UserAction.class);
	
	@Autowired 
	private SystemUserService systemUserService;
	
	@Autowired
	private DepartmentService departmentService;
	
	@Autowired
	private SystemRoleService systemRoleService;
	
	@Autowired
	private SystemUserRoleService systemUserRoleService;
	
	/**
	 * 返回数据到list页面
	 *
	 * @author 	: <a href="mailto:hubo@95190.com">hubo</a>  
	 * 2015年5月16日 下午11:48:57
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/list.html", method = RequestMethod.GET)
	public String showuserpage(Model model) {

		return "system/user/list";
	}
	
	
	/**
	 * 返回dataGrid用户数据
	 *
	 * @author 	: <a href="mailto:hubo@95190.com">hubo</a>  
	 * @date 2015年5月16日 下午11:56:08
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/list.json", method = RequestMethod.POST)
	@ResponseBody
	public DataGridModel showUserlist(DatagridDto dgDto,SystemUser user,String userQuery) {
		DataGridModel dataGridModel = new DataGridModel();
		
		user.setSortColumns("CREATETIME DESC");
		user.setUserName(user.getLoginName());
		
		if(StringUtils.isNotBlank(userQuery)){//搜索可按账号与用户名查
			user.setUserName(userQuery);
			user.setLoginName(userQuery);
		}
		
		if(super.hasRole("country_admin")){//县级管理员只能查询本机构的人员
			SystemUser currentUser = super.getCurrentSystemUser();
			user.setDeptId(currentUser.getDeptId()); //部门ID
		}
		
		Long total = systemUserService.getTotalCount(user);
		Pager pager = new Pager(dgDto.getPage(), dgDto.getRows(), total);
		pager = systemUserService.findPageList(user, pager);

		dataGridModel.setRows(pager.getDatas());
		dataGridModel.setTotal(total);

		return dataGridModel;
	}
	
	/**
	 * 根据groupId查此组下的用户
	 * 
	 * @author 	: <a href="mailto:358911056@qq.com">hubo</a>  2015-7-6 上午10:33:49
	 * @param user
	 * @param userGroupId 组ID
	 * @param findInGroup 搜索框内容
	 * @return
	 */
	@RequestMapping(value = "/findUserByGroupId.json", method = RequestMethod.POST)
	@ResponseBody
	public DataGridModel findUserByGroupId(SystemUser user,Long userGroupId,String findInGroup) {
		DataGridModel dataGridModel = new DataGridModel();
		
		if(StringUtils.isNotBlank(findInGroup)){
			user.setUserName(findInGroup);
			user.setLoginName(findInGroup);
		}
		
		List<UserGroupModel> datalist= systemUserService.findUserByGroupId(user,userGroupId);
		dataGridModel.setRows(datalist);

		return dataGridModel;
	}
	
	/**
	 * 授权页面
	 *
	 * @author 	: <a href="mailto:hubo@95190.com">hubo</a>  
	 * 2015年5月16日 下午11:48:57
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/authz.html", method = RequestMethod.GET)
	public String authz(Model model) {
		List<SystemRole> rolelist = systemRoleService.findList(new SystemRole());
		model.addAttribute("rolelist", rolelist);
		
		return "/system/user/authz";
	}
	
	/**
	 * 跳转到编辑用户页面
	 *
	 * @author 	: <a href="mailto:h358911056@qq.com">hubo</a>  2015年6月7日 下午5:11:03
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/beforeEditUser.html", method = RequestMethod.GET)
	public String beforeEditUser(Model model, String userId,String flag) {
		List<Department> deptList = departmentService.findList(new Department());
		model.addAttribute("deptList", deptList); //部门
		
		if(StringUtils.isNotBlank(flag) && flag.equals("2")){//修改用户信息回选
			SystemUser user = systemUserService.getById(Long.valueOf(userId), SystemUser.class);
			model.addAttribute("user", user);
		}
		
		model.addAttribute("educationMap", ConstantsUtil.educationMap);//学历
		
		return "system/user/edit";
	}
	
	/**
	 * 编辑用户
	 * 存在ID则修改，否则添加
	 *
	 * @author 	: <a href="mailto:h358911056@qq.com">hubo</a>  2015年6月7日 下午11:45:43
	 * @param model
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/editUser.json", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> editUser(HttpServletRequest request,Model model, SystemUser user) {
		Map<String, Object> map = getStatusMap();
		
		List<Department> deptList = departmentService.findList(new Department());
		model.addAttribute("deptList", deptList); //部门
		 
		super.setCreateUser(user);//设置创建用户
		int count = 0;
		if(null != user && null != user.getUserId()){//修改用户
			logs.info("修改用户");
			user.setUpdateTime(new Date());
			count = systemUserService.update(user);
			map.put("flag", user.getUserId());
			
			super.log(request, "修改用户:" + toJson(user));
		}else{//添加
			logs.info("添加用户");
			//dataHandler(user);
			
			user.setStatus(1);
			user.setPassword(Md5.md5("123456"));//初始密码
			user.setCreateTime(new Date());
			count =  systemUserService.save(user); //保存用户
			
			//默认每个用户都是考生的角色
			SystemUserRole userRole = new SystemUserRole();
			userRole.setUserId(user.getUserId());
			userRole.setRoleId(3L); //考生角色ID
			systemUserRoleService.save(userRole);
			
			map.put("flag", null);
			super.log(request, "添加用户:" + toJson(user));
		}
		map.put("status", count);
		
		return map;
	}
	
	/**
	 * 日期处理
	 * @param user
	 * @return
	 */
	private SystemUser dataHandler(SystemUser user) {
		if(StringUtils.isBlank(user.getBirthday())){
			user.setBirthday(null);
		}
		if(StringUtils.isBlank(user.getJobDate())){
			user.setJobDate(null);
		}
		if(StringUtils.isBlank(user.getForcastBeginDate())){
			user.setForcastBeginDate(null);
		}
		if(StringUtils.isBlank(user.getGraduateFirst())){
			user.setGraduateFirst(null);
		}
		if(StringUtils.isBlank(user.getGraduateSecond())){
			user.setGraduateSecond(null);
		}
		
		return user;
	}


	/**
	 * 删除用户
	 *
	 * @author 	: <a href="mailto:h358911056@qq.com">hubo</a>  2015年6月7日 下午3:30:41
	 * @param ids id串
	 * @return
	 */
	@RequestMapping(value="/deleteUser.json",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> deleteUser(HttpServletRequest request,String ids) {
		Map<String, Object> map = getStatusMap();

		if (StringUtils.isNotBlank(ids)) {
			String[] idArr = ids.split(",");
			int count = systemUserService.deleteBatch(Arrays.asList(idArr),
					SystemUser.class);

			map.put("status", count);
			super.log(request, "删除用户编号:" + toJson(ids));
		}

		return map;
	}
	

	/**
	 * 修改密码
	 *
	 * @author 	: <a href="mailto:hubo@95190.com">hubo</a>  2015-5-19 下午4:23:58
	 * @return
	 */
	@RequestMapping(value="/updatePwd.json",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> updatePwd(HttpServletRequest request,String newpwd, String oldpwd) {
		Map<String, Object> map = getStatusMap();
		map.put("status", 1);

		return map;
	}

}
