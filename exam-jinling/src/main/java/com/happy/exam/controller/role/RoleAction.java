/*
 * Beijing happy Information Technology Co,Ltd.
 * All rights reserved.
 * 
 * <p>RoleAction.java</p>
 */
package com.happy.exam.controller.role;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.happy.exam.common.bean.DataGridModel;
import com.happy.exam.common.dto.DatagridDto;
import com.happy.exam.common.pager.Pager;
import com.happy.exam.controller.BaseAction;
import com.happy.exam.model.SystemOperate;
import com.happy.exam.model.SystemRole;
import com.happy.exam.model.SystemRoleResource;
import com.happy.exam.service.SystemOperateService;
import com.happy.exam.service.SystemResourceService;
import com.happy.exam.service.SystemRoleResourceService;
import com.happy.exam.service.SystemRoleService;

/**
 *  角色action
 *
 * @version : Ver 1.0
 * @author	: <a href="mailto:h358911056@qq.com">hubo</a>
 * @date	: 2015年6月1日 下午9:41:32 
 */
@Controller
@RequestMapping("/role")
public class RoleAction extends BaseAction {

	@Autowired
	private SystemRoleService systemRoleService;
	
	@Autowired
	private SystemResourceService systemResourceService;
	
	@Autowired
	private SystemOperateService systemOperateService;
	
	@Autowired
	private SystemRoleResourceService systemRoleResourceService;
	
	/**
	 * 角色表表页面
	 *
	 * @author 	: <a href="mailto:hubo@95190.com">hubo</a>  
	 * 2015年5月16日 下午11:48:57
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/list.html", method = RequestMethod.GET)
	public String showRoles(HttpServletResponse res,HttpServletRequest req) {
		return "/system/role/list";
	}
	
	/**
	 * 返回dataGrid角色数据
	 *
	 * @author 	: <a href="mailto:hubo@95190.com">hubo</a>  
	 * @date 2015年5月16日 下午11:56:08
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/list.json", method = RequestMethod.POST,produces="application/json")
	@ResponseBody
	public DataGridModel showRolelist(DatagridDto dgDto,SystemRole role) {
		DataGridModel dataGridModel = new DataGridModel();
		
		role.setSortColumns("CREATETIME DESC");
		Long total = systemRoleService.getTotalCount(role);
		
		Pager pager = new Pager(dgDto.getPage(), dgDto.getRows(), total);
		pager = systemRoleService.findPageList(role, pager);

		dataGridModel.setRows(pager.getDatas());
		dataGridModel.setTotal(total);

		return dataGridModel;
	}
	
	/**
	 * 跳转到编辑用户页面
	 *
	 * @author 	: <a href="mailto:h358911056@qq.com">hubo</a>  2015年6月7日 下午5:11:03
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/beforeEditRole.html", method = RequestMethod.GET)
	public String beforeEditRole(Model model, String roleId,String flag) {
		if(StringUtils.isNotBlank(flag) && flag.equals("2")){//修改用户信息回选
			SystemRole role = systemRoleService.getById(Long.valueOf(roleId), SystemRole.class);
			model.addAttribute("role", role);
		}
		
		return "/system/role/edit";
	}
	
	/**
	 * 编辑用户
	 * 存在ID则修改，否则添加
	 *
	 * @author 	: <a href="mailto:h358911056@qq.com">hubo</a>  2015年6月7日 下午11:45:43
	 * @param model
	 * @param role
	 * @return
	 */
	@RequestMapping(value = "/editRole.json", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> editRole(Model model, SystemRole role) {
		int count = 0;
		Map<String, Object> map = getStatusMap();

		if (null != role && null != role.getRoleId()) {// 修改
			role.setUpdateTime(new Date());
			count = systemRoleService.update(role);
			map.put("flag", role.getRoleId());
		} else {// 添加
			role.setStatus(1);
			role.setCreateTime(new Date());
			count = systemRoleService.save(role);
			map.put("flag", null);
		}
		map.put("status", count);

		return map;
	}
	
	/**
	 * 删除用户
	 *
	 * @author 	: <a href="mailto:h358911056@qq.com">hubo</a>  2015年6月7日 下午3:30:41
	 * @param ids id串
	 * @return
	 */
	@RequestMapping(value="/deleteRole.json",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> deleteRole(String ids) {
		Map<String, Object> map = getStatusMap();

		if (StringUtils.isNotBlank(ids)) {
			String[] idArr = ids.split(",");
			int count = systemRoleService.deleteBatch(Arrays.asList(idArr),SystemRole.class);

			map.put("status", count);
		}

		return map;
	}
	
	
	/**
	 * 分配资源页面
	 *
	 * @author 	: <a href="mailto:hubo@95190.com">hubo</a>  
	 * 2015年5月16日 下午11:48:57
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/authzRole.html", method = RequestMethod.GET)
	public String authzRolePage(Model model,String roleId) {
		
		if (StringUtils.isNotBlank(roleId)) {
			SystemOperate operate = new SystemOperate();
			operate.setStatus(1);// 只查询启用状态的数据

			List<SystemOperate> operatelist = systemOperateService.findList(operate);
			model.addAttribute("operatelist", operatelist);
			model.addAttribute("roleId", roleId);
		}
		
		return "system/role/authz";
	}
	
	/**
	 * 根据角色ID查询角色拥有的资源与操作权限
	 *
	 * @author 	: <a href="mailto:hubo@95190.com">hubo</a>  
	 * @date 2015年5月16日 下午11:56:08
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/getAuth.json", method = RequestMethod.POST)
	@ResponseBody
	public List<SystemRoleResource> getAuth(String roleId) {
		List<SystemRoleResource> authlist = null;
		if (StringUtils.isNotBlank(roleId)) {
			SystemRoleResource roleResource = new SystemRoleResource();
			roleResource.setRoleId(Long.valueOf(roleId));

			authlist = systemRoleResourceService.findList(roleResource);
		}

		return authlist;
	}
	
	/**
	 * 保存角色资源及操作权限
	 *
	 * @author 	: <a href="mailto:hubo@95190.com">hubo</a>  
	 * @date 2015年5月16日 下午11:56:08
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/saveAuthzRole.json", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> saveAuthzRole(String roleId, String permissons) {
		Map<String, Object> map = getStatusMap();
		
		if (StringUtils.isNotBlank(permissons) && StringUtils.isNotBlank(roleId)) {
			Class<SystemRoleResource> clzz = SystemRoleResource.class;

			// 先根据角色ID删除所有再添加新数据
			int count = systemRoleResourceService.delete(Long.valueOf(roleId), clzz);
			if(count >= 0){
				List<SystemRoleResource> list = JSON.parseArray(permissons, clzz);
				count = systemRoleResourceService.saveBatch(list, clzz);
			}

			map.put("status", count);
		}

		return map;
	}
}
