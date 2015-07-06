/*
 * Beijing happy Information Technology Co,Ltd.
 * All rights reserved.
 * 
 * <p>DepartmentAction.java</p>
 */
package com.happy.exam.controller.department;

import java.io.UnsupportedEncodingException;
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
public class DepartmentAction extends BaseAction{

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
		return "system/department/list";
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
	
	/**
	 * 跳转到编辑部门页面
	 *
	 * @author 	: <a href="mailto:h358911056@qq.com">hubo</a>  2015年6月7日 下午5:11:03
	 * @param model
	 * @param request
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping(value = "/beforeEditDepartment.html", method = RequestMethod.GET)
	public String beforeEditDepartment(Model model, Long deptId, String pname,
			Long pid, String flag) throws UnsupportedEncodingException {
		
		if (StringUtils.isNotBlank(flag) && flag.equals("2")) {// 修改部门信息回选
			Department department = departmentService.getById(deptId, Department.class);
			model.addAttribute("department", department);
		}

		if (StringUtils.isNotBlank(pname)) {
			model.addAttribute("pname",java.net.URLDecoder.decode(pname, "UTF-8"));
		}

		model.addAttribute("flag", flag);
		model.addAttribute("pid", pid);
		
		return "/system/department/edit";
	}
	
	/**
	 * 编辑部门
	 * 存在ID则修改，否则添加
	 *
	 * @author 	: <a href="mailto:h358911056@qq.com">hubo</a>  2015年6月7日 下午11:45:43
	 * @param model
	 * @param department
	 * @return
	 */
	@RequestMapping(value = "/editDepartment.json", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> editDepartment(Model model, Department department) {
		Map<String, Object> map = getStatusMap();
		
		int count = 0;
		if(null != department && null !=department.getDeptId()){//修改部门
			count = departmentService.update(department);
			map.put("flag", department.getDeptId());
		}else{//添加
			count =  departmentService.save(department);
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
	@RequestMapping(value="/deleteDepartment.json",method=RequestMethod.POST)
	@ResponseBody public Map<String, Object> deleteDepartment(Department department){
		Map<String, Object> map = getStatusMap();
		
		if(null != department.getDeptId()){
			int count = departmentService.delete(department.getDeptId(),Department.class);
			map.put("status", count);
		}
		
		return map;
	}
	
}
