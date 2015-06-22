package com.happy.exam.controller.group;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.happy.exam.common.dto.TreegridDto;
import com.happy.exam.controller.BaseAction;
import com.happy.exam.model.SystemGroup;
import com.happy.exam.service.SystemGroupService;


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
	public String showModules(Model model) {
		return "/system/group/list";
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
	public TreegridDto showModulelist(SystemGroup group) {
		TreegridDto treegridDto = new TreegridDto();
		 
		List<SystemGroup> list = systemGroupService.findTreegrid(group);
		treegridDto.setRows(list);
		treegridDto.setTotal(list.size());

		return treegridDto;
	}
}
