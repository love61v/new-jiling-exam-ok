/*
 * Beijing happy Information Technology Co,Ltd.
 * All rights reserved.
 * 
 * <p>ModuleAction.java</p>
 */
package com.happy.exam.controller.module;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.happy.exam.common.dto.TreegridDto;
import com.happy.exam.controller.BaseAction;
import com.happy.exam.model.SystemResource;
import com.happy.exam.model.SystemUser;
import com.happy.exam.service.SystemResourceService;

/**
 *  系统模块action
 *
 * @version : Ver 1.0
 * @author	: <a href="mailto:h358911056@qq.com">hubo</a>
 * @date	: 2015年6月1日 下午9:41:32 
 */
@Controller
@RequestMapping("/module")
public class ModuleAction extends BaseAction {

	@Autowired
	private SystemResourceService systemResourceService;
	
	/**
	 * 模块表表页面
	 *
	 * @author 	: <a href="mailto:hubo@95190.com">hubo</a>  
	 * 2015年5月16日 下午11:48:57
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/list.html", method = RequestMethod.GET)
	public String showModules(Model model) {
		return "system/module/list";
	}
	
	/**
	 * 模块树页面
	 *
	 * @author 	: <a href="mailto:hubo@95190.com">hubo</a>  
	 * 2015年5月16日 下午11:48:57
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/choiceModuleTree.html", method = RequestMethod.GET)
	public String choiceModuleTree(Model model) {
		return "system/module/choiceModuleTree";
	}
	
	
	/**
	 * 返回treeGrid模块数据
	 *
	 * @author 	: <a href="mailto:hubo@95190.com">hubo</a>  
	 * @date 2015年5月16日 下午11:56:08
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/list.json", method = RequestMethod.POST)
	@ResponseBody
	public TreegridDto showModulelist(SystemResource module) {
		TreegridDto treegridDto = new TreegridDto();
		 
		List<SystemResource> list = systemResourceService.findTreegrid(module);
		treegridDto.setRows(list);
		treegridDto.setTotal(list.size());

		return treegridDto;
	}
	
	/**
	 * 返回用户权限treeGrid模块数据
	 *
	 * @author 	: <a href="mailto:hubo@95190.com">hubo</a>  
	 * @date 2015年5月16日 下午11:56:08
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/showUserModules.json", method = RequestMethod.POST)
	@ResponseBody
	public TreegridDto findUserModuleTree() {
		SystemUser user = super.getCurrentSystemUser(); //当前用户 
		TreegridDto treegridDto = new TreegridDto();

		List<SystemResource> list = null;
		//系统管理员与超级管理员有所有模块权
		if(super.hasRole("system_admin") || super.hasRole("super_admin")){
			SystemResource module = new SystemResource();
			module.setStatus(1);
			list = systemResourceService.findTreegrid(module);
		}else{//其它角色按分配的权限展示
			list = systemResourceService.findUserModuleTree(user.getUserId());
		}
		treegridDto.setRows(list);
		treegridDto.setTotal(list.size());

		return treegridDto;
	}
	
	/**
	 * 跳转到编辑用户页面
	 *
	 * @author 	: <a href="mailto:h358911056@qq.com">hubo</a>  2015年6月7日 下午5:11:03
	 * @param model
	 * @param request
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping(value = "/beforeEditModule.html", method = RequestMethod.GET)
	public String beforeEditModule(Model model, String moduleId, String pname,
			String pid, String flag) throws UnsupportedEncodingException {
		
		if (StringUtils.isNotBlank(flag) && flag.equals("2")) {// 修改用户信息回选
			SystemResource module = systemResourceService.getById(
					Long.valueOf(moduleId), SystemResource.class);
			model.addAttribute("module", module);
		}

		if (StringUtils.isNotBlank(pname)) {
			model.addAttribute("pname",java.net.URLDecoder.decode(pname, "UTF-8"));
		}

		model.addAttribute("flag", flag);
		model.addAttribute("pid", pid);
		
		return "system/module/edit";
	}
	
	/**
	 * 编辑用户
	 * 存在ID则修改，否则添加
	 *
	 * @author 	: <a href="mailto:h358911056@qq.com">hubo</a>  2015年6月7日 下午11:45:43
	 * @param model
	 * @param module
	 * @return
	 */
	@RequestMapping(value = "/editModule.json", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> editModule(Model model, SystemResource module) {
		Map<String, Object> map = getStatusMap();
		super.setCreateUser(module);//创建用户
		
		int count = 0;
		if(null != module && null !=module.getResourceId()){//修改用户
			module.setUpdateTime(new Date());
			count = systemResourceService.update(module);
			map.put("flag", module.getResourceId());
		}else{//添加
			module.setStatus(1);
			module.setCreateTime(new Date());
			count =  systemResourceService.save(module);
			map.put("flag", null);
		}
		map.put("status", count);
		
		return map;
	}
	
	/**
	 * 删除模块
	 *
	 * @author 	: <a href="mailto:h358911056@qq.com">hubo</a>  2015年6月7日 下午3:30:41
	 * @param id  当前选中节点的id
	 * @param parnetId  不为空则上节点为父节点，则删除自身与其下所有子节点
	 * @return
	 */
	@RequestMapping(value="/deleteModule.json",method=RequestMethod.POST)
	@ResponseBody public Map<String, Object> deleteModule(SystemResource module){
		Map<String, Object> map = getStatusMap();
		
		if(null != module.getResourceId()){
			int count = systemResourceService.deleteUnion(module);
			map.put("status", count);
		}
		
		return map;
	}
	
}
