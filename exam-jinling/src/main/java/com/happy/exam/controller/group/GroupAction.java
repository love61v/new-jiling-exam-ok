package com.happy.exam.controller.group;

import java.io.UnsupportedEncodingException;
 
import java.util.Arrays;
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

import com.alibaba.fastjson.JSON;
import com.happy.exam.common.bean.DataGridModel;
import com.happy.exam.common.dto.DatagridDto;
import com.happy.exam.common.dto.TreegridDto;
import com.happy.exam.common.pager.Pager;
import com.happy.exam.controller.BaseAction;
import com.happy.exam.model.SystemGroup;
import com.happy.exam.model.SystemRole;
import com.happy.exam.model.SystemRoleGroup;
import com.happy.exam.model.SystemUserGroup;
import com.happy.exam.service.SystemGroupService;
import com.happy.exam.service.SystemRoleGroupService;
import com.happy.exam.service.SystemUserGroupService;


/**
 *  组管理action
 *
 * @version : Ver 1.0
 * @author	: <a href="mailto:h358911056@qq.com">hubo</a>
 * @date	: 2015年6月1日 下午9:41:32 
 */
@Controller
@RequestMapping("/group")
public class GroupAction extends BaseAction{

	@Autowired
	private SystemGroupService systemGroupService;
	
	@Autowired
	private SystemUserGroupService systemUserGroupService;
	
	@Autowired
	private SystemRoleGroupService systemRoleGroupService;

	/**
	 * 组页面
	 *
	 * @author 	: <a href="mailto:hubo@95190.com">hubo</a>  
	 * 2015年5月16日 下午11:48:57
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/list.html", method = RequestMethod.GET)
	public String showGroups(Model model) {
		return "/system/group/list";
	}
	
	/**
	 * 分组树页面
	 *
	 * @author 	: <a href="mailto:hubo@95190.com">hubo</a>  
	 * 2015年5月16日 下午11:48:57
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/choiceGroupTree.html", method = RequestMethod.GET)
	public String choiceGroupTree(Model model) {
		return "system/group/choiceGroupTree";
	}
 
	/**
	 * 跳绑定用户页面
	 *
	 * @author 	: <a href="mailto:hubo@95190.com">hubo</a>  
	 * 2015年5月16日 下午11:48:57
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/beforeUserBind.html", method = RequestMethod.GET)
	public String beforeUserBind(Model model) {
		return "system/group/bindUser";
	}
	
	/**
	 * 跳绑定用户页面
	 *
	 * @author 	: <a href="mailto:hubo@95190.com">hubo</a>  
	 * 2015年5月16日 下午11:48:57
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/beforeRoleBind.html", method = RequestMethod.GET)
	public String beforeRoleBind(Model model) {
		return "system/group/bindRole";
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
	public TreegridDto showGrouplist(SystemGroup group) {
		TreegridDto treegridDto = new TreegridDto();
		 
		List<SystemGroup> list = systemGroupService.findTreegrid(group);
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
	@RequestMapping(value = "/beforeEditGroup.html", method = RequestMethod.GET)
	public String beforeEditGroup(Model model, String groupId, String pname,
			String pid, String flag) throws UnsupportedEncodingException {
		
		if (StringUtils.isNotBlank(flag) && flag.equals("2")) {// 修改用户信息回选
			SystemGroup group = systemGroupService.getById(
					Long.valueOf(groupId), SystemGroup.class);
			model.addAttribute("group", group);
		}

		if (StringUtils.isNotBlank(pname)) {
			model.addAttribute("pname",java.net.URLDecoder.decode(pname, "UTF-8"));
		}

		model.addAttribute("flag", flag);
		model.addAttribute("pid", pid);
		
		return "/system/group/edit";
	}
	
	/**
	 * 编辑用户
	 * 存在ID则修改，否则添加
	 *
	 * @author 	: <a href="mailto:h358911056@qq.com">hubo</a>  2015年6月7日 下午11:45:43
	 * @param model
	 * @param group
	 * @return
	 */
	@RequestMapping(value = "/editGroup.json", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> editGroup(Model model, SystemGroup group) {
		Map<String, Object> map = getStatusMap();
		
		int count = 0;
		if(null != group && null !=group.getGroupId()){//修改用户
			group.setUpdateTime(new Date());
			count = systemGroupService.update(group);
			map.put("flag", group.getGroupId());
		}else{//添加
			group.setStatus(1);
			group.setCreateTime(new Date());
			count =  systemGroupService.save(group);
		}
		map.put("status", count);
		
		return map;
	}
	
	/**
	 * 删除组
	 *
	 * @author 	: <a href="mailto:h358911056@qq.com">hubo</a>  2015年6月7日 下午3:30:41
	 * @param id  当前选中节点的id
	 * @param parnetId  不为空则上节点为父节点，则删除自身与其下所有子节点
	 * @return
	 */
	@RequestMapping(value="/deleteGroup.json",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> deleteGroup(SystemGroup group) {
		Map<String, Object> map = getStatusMap();
		boolean boor = false;
		Long groupId = group.getGroupId();

		if (null != groupId) {
			SystemUserGroup userGroup = new SystemUserGroup();
			userGroup.setGroupId(groupId);
			long userCount = systemUserGroupService.getTotalCount(userGroup);
			
			if (userCount > 0) {
				map.put("msg", "分组下存在用户，无法删除");
			} else {
				SystemRoleGroup roleGroup = new SystemRoleGroup();
				roleGroup.setGroupId(groupId);
				long roleCount = systemRoleGroupService.getTotalCount(roleGroup);
				
				if (roleCount > 0) {
					map.put("msg", "分组下存在角色，无法删除");
				} else {
					boor = true;
				}
			}

			if (boor) {
				int count = systemGroupService.deleteUnion(group);
				map.put("status", count);
			}
		}

		return map;
	}
	
 
	/**
	 * 绑定用户
	 * 存在ID则修改，否则添加
	 *
	 * @author 	: <a href="mailto:h358911056@qq.com">hubo</a>  2015年6月7日 下午11:45:43
	 * @param model
	 * @param group
	 * @return
	 */
	@RequestMapping(value = "/bindUser.json", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> bindUser(Model model,String userGroups) {
		Map<String, Object> map = getStatusMap();
		long count = 0L;
		
		if(StringUtils.isNotBlank(userGroups)){
			Class<SystemUserGroup> clzz = SystemUserGroup.class;
			List<SystemUserGroup> list = JSON.parseArray(userGroups, clzz);
			count = systemUserGroupService.saveBatch(list, clzz);
		}
		 
		map.put("status", count);
		
		return map;
	}
	
	/**
	 * 删除组下的所有用户
	 *
	 * @author 	: <a href="mailto:h358911056@qq.com">hubo</a>  2015年7月1日 上午1:19:55
	 * @param groupId
	 * @param uerIds
	 * @return
	 */
	@RequestMapping(value = "/removeFromGroup.json", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> removeFromGroup(String groupId,String uerIds) {
		Map<String, Object> map = getStatusMap();
		long count = 0L;
		
		if(StringUtils.isNotBlank(groupId) && StringUtils.isNotBlank(uerIds)){
			count = systemUserGroupService.deleteUnion(groupId,Arrays.asList(uerIds.split(",")));
		}
		 
		map.put("status", count);
		
		return map;
	}
	
	/**
	 * 删除组下的角色
	 *
	 * @author 	: <a href="mailto:h358911056@qq.com">hubo</a>  2015年7月1日 上午1:25:37
	 * @param systemRoleGroup
	 * @return
	 */
	@RequestMapping(value = "/deleteGroupRole.json", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> deleteGroupRole(SystemRoleGroup systemRoleGroup) {
		Map<String, Object> map = getStatusMap();
		long count = 0L;
		if(null != systemRoleGroup.getRoleId() && null != systemRoleGroup.getGroupId()){
			count = systemRoleGroupService.deleteGroupRole(systemRoleGroup);
		}
		 
		map.put("status", count);
		
		return map;
	}
	
	
	/**
	 * 绑定角色到组
	 * 存在ID则修改，否则添加
	 *
	 * @author 	: <a href="mailto:h358911056@qq.com">hubo</a>  2015年6月7日 下午11:45:43
	 * @param model
	 * @param group
	 * @return
	 */
	@RequestMapping(value = "/bindRole.json", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> bindRole(Model model,String roleGroups) {
		Map<String, Object> map = getStatusMap();
		long count = 0L;
		
		if(StringUtils.isNotBlank(roleGroups)){
			Class<SystemRoleGroup> clzz = SystemRoleGroup.class;
			List<SystemRoleGroup> list = JSON.parseArray(roleGroups, clzz);
			count = systemRoleGroupService.saveBatch(list, clzz);
		}
		 
		map.put("status", count);
		
		return map;
	}
	
	/**
	 * 根据groupId查组的所有角色
	 *
	 * @author 	: <a href="mailto:hubo@95190.com">hubo</a>  
	 * @date 2015年5月16日 下午11:56:08
	 * @param groupId 组ID
	 * @return
	 */
	@RequestMapping(value = "/findRoleByGroupId.json", method = RequestMethod.POST,produces="application/json")
	@ResponseBody
	public DataGridModel findRoleByGroupId(Long groupId) {
		DataGridModel dataGridModel = new DataGridModel();
		List<SystemRole> list = systemRoleGroupService.findRoleByGroupId(groupId);
		dataGridModel.setRows(list);

		return dataGridModel;
	}
	 
}
