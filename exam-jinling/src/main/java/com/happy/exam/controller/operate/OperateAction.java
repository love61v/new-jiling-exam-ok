package com.happy.exam.controller.operate;

import java.util.Arrays;
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

import com.happy.exam.common.bean.DataGridModel;
import com.happy.exam.common.dto.DatagridDto;
import com.happy.exam.common.pager.Pager;
import com.happy.exam.controller.BaseAction;
import com.happy.exam.model.SystemOperate;
import com.happy.exam.service.SystemOperateService;

@Controller
@RequestMapping("/operate")
public class OperateAction extends BaseAction{
	
	@Autowired
	private SystemOperateService systemOperateService;
	
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
	public String showOperates(HttpServletResponse res,HttpServletRequest req) {
		return "/system/operate/list";
	}
	
	/**
	 * 返回dataGrid角色数据
	 *
	 * @author 	: <a href="mailto:hubo@95190.com">hubo</a>  
	 * @date 2015年5月16日 下午11:56:08
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/list.json", method = RequestMethod.POST)
	@ResponseBody
	public DataGridModel showOperatelist(DatagridDto dgDto,SystemOperate operate) {
		DataGridModel dataGridModel = new DataGridModel();
		
		Long total = systemOperateService.getTotalCount(operate);
		Pager pager = new Pager(dgDto.getPage(), dgDto.getRows(), total);
		pager = systemOperateService.findPageList(operate, pager);

		dataGridModel.setRows(pager.getDatas());
		dataGridModel.setTotal(total);

		return dataGridModel;
	}
	
	/**
	 * 返回dataGrid角色数据
	 *
	 * @author 	: <a href="mailto:hubo@95190.com">hubo</a>  
	 * @date 2015年5月16日 下午11:56:08
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/findlist.json", method = RequestMethod.POST)
	@ResponseBody
	public List<SystemOperate> findlist() {
		SystemOperate operate = new SystemOperate();
		operate.setStatus(1);

		return systemOperateService.findList(operate);
	}
	
	/**
	 * 跳转到编辑用户页面
	 *
	 * @author 	: <a href="mailto:h358911056@qq.com">hubo</a>  2015年6月7日 下午5:11:03
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/beforeEditOperate.html", method = RequestMethod.GET)
	public String beforeEditOperate(Model model, String operateId, String flag) {
		if (StringUtils.isNotBlank(flag) && flag.equals("2")) {// 修改用户信息回选
			SystemOperate operate = systemOperateService.getById(
					Long.valueOf(operateId), SystemOperate.class);
			model.addAttribute("operate", operate);
		}

		return "/system/operate/edit";
	}
	
	/**
	 * 编辑
	 * 存在ID则修改，否则添加
	 *
	 * @author 	: <a href="mailto:h358911056@qq.com">hubo</a>  2015年6月7日 下午11:45:43
	 * @param model
	 * @param operate
	 * @return
	 */
	@RequestMapping(value = "/editOperate.json", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> editOperate(Model model, SystemOperate operate) {
		Map<String, Object> map = getStatusMap();
		super.setCreateUser(operate);//创建用户
		
		int count = 0;
		if(null != operate && null != operate.getOperateId()){//修改
			count = systemOperateService.update(operate);
			map.put("flag", operate.getOperateId());
		}else{//添加
			operate.setStatus(1);
			count =  systemOperateService.save(operate);
			map.put("flag", null);
		}
		map.put("status", count);
		
		return map;
	}
	
	/**
	 * 删除
	 *
	 * @author 	: <a href="mailto:h358911056@qq.com">hubo</a>  2015年6月7日 下午3:30:41
	 * @param ids id串
	 * @return
	 */
	@RequestMapping(value="/deleteOperate.json",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> deleteOperate(String ids) {
		Map<String, Object> map = getStatusMap();

		if (StringUtils.isNotBlank(ids)) {
			String[] idArr = ids.split(",");
			int count = systemOperateService.deleteBatch(Arrays.asList(idArr),
					SystemOperate.class);

			map.put("status", count);
		}

		return map;
	}
	
}
