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
 * 考试类型action
 * 
 * @version : Ver 1.0
 * @author : <a href="mailto:h358911056@qq.com">hubo</a>
 * @date : 2015年6月15日 下午10:45:36
 */
@Controller
@RequestMapping("/examtype")
public class ExamTypeAction extends BaseAction {

	@Autowired
	private ExamTypeService examTypeService;

	/**
	 * 试题类型表表页面
	 * 
	 * @author : <a href="mailto:954643309@qq.com">hgx</a> 2015-7-7 下午10:05:38
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/list.html", method = RequestMethod.GET)
	public String showExamTypes(Model model) {
		return "examtype/list";
	}

	/**
	 * 试题类型树页面
	 * 
	 * @author : <a href="mailto:954643309@qq.com">hgx</a> 2015-7-7 下午10:05:38
	 *         2015年5月16日 下午11:48:57
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/choiceExamTypeTree.html", method = RequestMethod.GET)
	public String choiceExamTypeTree(Model model) {
		return "examtype/choiceExamTypeTree";
	}
	

	/**
	 * 返回treeGrid试题类型数据
	 * 
	 * @author : <a href="mailto:954643309@qq.com">hgx</a> 2015-7-7 下午10:05:38
	 * @date 2015年5月16日 下午11:56:08
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/list.json", method = RequestMethod.POST)
	@ResponseBody
	public TreegridDto showExamTypelist(ExamType examType) {
		TreegridDto treegridDto = new TreegridDto();

		List<ExamType> list = examTypeService.findTreegrid(examType);
		treegridDto.setRows(list);
		treegridDto.setTotal(list.size());
		return treegridDto;
	}

	/**
	 * 跳转到编辑用户页面
	 * 
	 * @author : <a href="mailto:954643309@qq.com">hgx</a> 2015-7-7 下午10:05:38
	 * @param model
	 * @param request
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(value = "/beforeEditExamType.html", method = RequestMethod.GET)
	public String beforeEditExamType(Model model, String id, String pname,
			String parentId, String flag) throws UnsupportedEncodingException {
		if (StringUtils.isNotBlank(flag) && flag.equals("2")) {// 修改用户信息回选
			ExamType examType = examTypeService.getById(Long.valueOf(id),
					ExamType.class);
			model.addAttribute("examType", examType);
		}
		model.addAttribute("flag", flag);
		model.addAttribute("pid", parentId);
		if (StringUtils.isNotBlank(pname)) {
			model.addAttribute("pname",
					java.net.URLDecoder.decode(pname, "UTF-8"));
		}

		return "examtype/edit";
	}

	/**
	 * 编辑用户 存在ID则修改，否则添加
	 * 
	 * @author : <a href="mailto:954643309@qq.com">hgx</a> 2015-7-7 下午10:05:38
	 * @param model
	 * @param examType
	 * @return
	 */
	@RequestMapping(value = "/editExamtype.json", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> editExamType(Model model, ExamType examType) {
		Map<String, Object> map = getStatusMap();

		int count = 0;
		if (null != examType && null != examType.getId()) {// 修改用户
			examType.setUpdateTime(new Date());
			SystemUser systemUser = getCurrentSystemUser();
			examType.setCreateUser(systemUser.getCreateUser());
			count = examTypeService.update(examType);
			map.put("flag", examType.getId());
		} else {// 添加
			examType.setStatus(1);
			// examType.setId();
			SystemUser systemUser = getCurrentSystemUser();
			examType.setCreateUser(systemUser.getCreateUser());
			examType.setCreateTime(new Date());
			count = examTypeService.save(examType);
			map.put("flag", null);
		}
		map.put("status", count);

		return map;
	}

	/**
	 * 删除试题类型
	 * 
	 * @author : <a href="mailto:954643309@qq.com">hgx</a> 2015-7-7 下午10:05:38
	 * @param examType
	 * @return
	 */
	@RequestMapping(value = "/deleteExamtype.json", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> deleteExamType(ExamType examType) {
		Map<String, Object> map = getStatusMap();

		if (null != examType.getId()) {
			int count = examTypeService.deleteUnion(examType);
			map.put("status", count);
		}

		return map;
	}

}
