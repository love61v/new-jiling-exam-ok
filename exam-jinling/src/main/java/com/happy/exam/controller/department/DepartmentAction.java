/*
 * Beijing happy Information Technology Co,Ltd.
 * All rights reserved.
 * 
 * <p>DepartmentAction.java</p>
 */
package com.happy.exam.controller.department;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.happy.exam.common.dto.TreegridDto;
import com.happy.exam.model.Department;
import com.happy.exam.service.DepartmentService;

/**
 *  部门action
 *
 * @version : Ver 1.0
 * @author	: <a href="mailto:h358911056@qq.com">hubo</a>
 * @date	: 2015年6月15日 下午10:45:36 
 */
@Controller
@RequestMapping("/department")
public class DepartmentAction {

	@Autowired
	private DepartmentService departmentService;
	
	/**
	 * 部门页面
	 *
	 * @author 	: <a href="mailto:hubo@95190.com">hubo</a>  
	 * 2015年5月16日 下午11:48:57
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/list.html", method = RequestMethod.GET)
	public String showDepartment(Model model) {
		return "/system/department/list";
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
	public TreegridDto showDepartmentlist(Department department) {
		TreegridDto treegridDto = new TreegridDto();
		 
		List<Department> list = departmentService.findTreegrid(department);
		treegridDto.setRows(list);
		treegridDto.setTotal(list.size());

		return treegridDto;
	}
	
}
