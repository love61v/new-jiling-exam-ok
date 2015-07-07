/*
 * Beijing happy Information Technology Co,Ltd.
 * All rights reserved.
 * 
 * <p>DepartmentAction.java</p>
 */
package com.happy.exam.controller.examtype;

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
import com.happy.exam.model.ExamType;
import com.happy.exam.model.SystemUser;
import com.happy.exam.service.ExamTypeService;

/**
 *  部门action
 *
 * @version : Ver 1.0
 * @author	: <a href="mailto:h358911056@qq.com">hubo</a>
 * @date	: 2015年6月15日 下午10:45:36 
 */
@Controller
@RequestMapping("/examtype")
public class ExamTypeAction extends BaseAction{

	@Autowired
	private ExamTypeService examTypeService;
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
	public String showExamTypes(Model model) {
		return "examtype/list";
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
	@RequestMapping(value = "/choiceExamTypeTree.html", method = RequestMethod.GET)
	public String choiceExamTypeTree(Model model) {
		return "examtype/choiceExamTypeTree";
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
	public TreegridDto showExamTypelist(ExamType module) {
		TreegridDto treegridDto = new TreegridDto();
		 
		List<ExamType> list = examTypeService.findTreegrid(module);
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
	@RequestMapping(value = "/beforeEditExamType.html", method = RequestMethod.GET)
	public String beforeEditExamType(Model model, String id,String pname,String parentId,String flag) throws UnsupportedEncodingException {
		if(StringUtils.isNotBlank(flag) && flag.equals("2")){//修改用户信息回选
			ExamType module = examTypeService.getById(Long.valueOf(id), ExamType.class);
			model.addAttribute("module", module);
		}
		model.addAttribute("flag", flag);
		model.addAttribute("pid", parentId);
		if(StringUtils.isNotBlank(pname)){
			model.addAttribute("pname", java.net.URLDecoder.decode(pname,"UTF-8"));
		}
		
		return "examtype/edit";
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
	@RequestMapping(value = "/editExamtype.json", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> editExamType(Model model, ExamType module) {
		Map<String, Object> map = getStatusMap();
		
		int count = 0;
		if(null != module && null!=module.getId()){//修改用户
			module.setUpdateTime(new Date());
			SystemUser systemUser=getCurrentSystemUser();
			module.setCreateUser(systemUser.getCreateUser());
			count = examTypeService.update(module);
			map.put("flag", module.getId());
		}else{//添加
			module.setStatus(1);
			//module.setId();
			SystemUser systemUser=getCurrentSystemUser();
			module.setCreateUser(systemUser.getCreateUser());
			module.setCreateTime(new Date());
			count =  examTypeService.save(module);
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
	@RequestMapping(value="/deleteExamtype.json",method=RequestMethod.POST)
	@ResponseBody public Map<String, Object> deleteExamType(ExamType module){
		Map<String, Object> map = getStatusMap();
		
		if(null!=module.getId()){
			int count = examTypeService.deleteUnion(module);
			map.put("status", count);
		}
		
		return map;
	}
	
}
