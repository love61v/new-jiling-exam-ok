package com.happy.exam.controller.questiontypes;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.happy.exam.common.bean.DataGridModel;
import com.happy.exam.common.bean.ExamQuestionModel;
import com.happy.exam.common.dto.DatagridDto;
import com.happy.exam.common.enums.ExamTypeEnum;
import com.happy.exam.common.pager.Pager;
import com.happy.exam.common.utils.PoiUtil;
import com.happy.exam.controller.BaseAction;
import com.happy.exam.model.ExamFillBlanks;
import com.happy.exam.service.ExamFillBlanksService;
import com.happy.exam.service.ExamTypeService;

/**
 * 填空题管理Action
 *
 * @version : Ver 1.0
 * @author	: <a href="mailto:954643309@qq.com">hgx</a>
 * @date	: 2015-7-9 上午9:17:55
 */
@Controller
@RequestMapping("/fill")
public class ExamFillBlanksAction extends BaseAction{

	@Autowired
	private ExamTypeService examTypeService;
	
	@Autowired
	private ExamFillBlanksService examFillBlanksService;
	

	/**
	 * 填空题页面
	 *
	 * @author 	: <a href="mailto:954643309@qq.com">hgx</a>  2015-7-9 上午9:23:22
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/list.html", method = RequestMethod.GET)
	public String showFillBlanks(HttpServletRequest request,Model model) {
		super.log(request, "进入填空题列表页");
		return "/questiontypes/examfillblanks/list";
	}
	
	/**
	 * 左侧选择树结构
	 *
	 * @author 	: <a href="mailto:954643309@qq.com">hgx</a>  2015-7-9 上午9:23:55
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/choiceExamTypeTree.html", method = RequestMethod.GET)
	public String choiceExamTypeTree(HttpServletRequest request,Model model) {
		super.log(request, "加载填空题试题类型树结构");
		return "questiontypes/examfillblanks/choiceExamTypeTree";
	}
 
	
	
	/**
	 * 返回dataGrid模块数据
	 *
	 * @version : Ver 1.0
	 * @author	: <a href="mailto:954643309@qq.com">hgx</a>
	 * @date	: 2015-7-9 上午9:17:55
	 * @return
	 */
	@RequestMapping(value = "/list.json", method = RequestMethod.POST)
	@ResponseBody
	public DataGridModel showExamFillBlankslist(HttpServletRequest request,DatagridDto dgDto,ExamFillBlanks  fillBlanks) {
		DataGridModel dataGridModel = new DataGridModel();
		
		fillBlanks.setSortColumns("CREATETIME DESC");
		Long total = examFillBlanksService.getTotalCount(fillBlanks);
		
		Pager pager = new Pager(dgDto.getPage(), dgDto.getRows(), total);
		pager = examFillBlanksService.findPageList(fillBlanks, pager);

		dataGridModel.setRows(pager.getDatas());
		dataGridModel.setTotal(total);
		super.log(request, "加载填空题试题列表");
		return dataGridModel;
	}
	
	/**
	 * 跳转到编辑试题页面
	 *
	 * @version : Ver 1.0
	 * @author	: <a href="mailto:954643309@qq.com">hgx</a>
	 * @date	: 2015-7-9 上午9:17:55
	 */
	@RequestMapping(value = "/beforeEditFillBlanks.html", method = RequestMethod.GET)
	public String beforeEditFillBlanks(HttpServletRequest request,Model model, String id, String pname,
			String typeId, String flag) throws UnsupportedEncodingException {
		
		if (StringUtils.isNotBlank(flag) && flag.equals("2")) {// 修改用户信息回选
			ExamFillBlanks  fillblanks = examFillBlanksService.getById(
					Long.valueOf(id), ExamFillBlanks.class);
			model.addAttribute("fillblanks", fillblanks);
			super.log(request, "跳转修改填空题页面："+toJson(fillblanks));
		}else{
			ExamFillBlanks  fillblanks=new ExamFillBlanks();
			fillblanks.setTypeId(Long.valueOf(typeId));
			model.addAttribute("fillblanks", fillblanks);
			super.log(request, "跳转添加填空题页面："+toJson(fillblanks));
		}

		/*if (StringUtils.isNotBlank(pname)) {
			model.addAttribute("pname",java.net.URLDecoder.decode(pname, "UTF-8"));
		}*/

		model.addAttribute("flag", flag);
		//model.addAttribute("typeId", typeId);
		return "/questiontypes/examfillblanks/edit";
	}
	
	/**
	 * 跳转到编辑试题页面
	 *
	 * @version : Ver 1.0
	 * @author	: <a href="mailto:954643309@qq.com">hgx</a>
	 * @date	: 2015-7-9 上午9:17:55
	 */
	@RequestMapping(value = "/editFillBlanks.json", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> editFillBlanks(HttpServletRequest request,Model model, ExamFillBlanks  fillblanks) {
		Map<String, Object> map = getStatusMap();
		
		int count = 0;
		String content = fillblanks.getPrototypeQuestion();
		String answer = PoiUtil.getFillAnswer(content);
		fillblanks.setAnswer(answer);
		fillblanks.setQuestion(PoiUtil.getFillQuestion(content, answer));
		fillblanks.setScore(answer.split(";").length * 0.5 + "");
		if(null != fillblanks && null !=fillblanks.getId()){//
			fillblanks.setUpdateTime(new Date());
			count = examFillBlanksService.update(fillblanks);
			map.put("flag",fillblanks.getId());
			super.log(request, "修改填空题："+toJson(fillblanks));
		}else{//添加
		    fillblanks.setStatus(1);
		    fillblanks.setCreateUser(super.getCurrentUserId());
		    fillblanks.setCreateTime(new Date());
		    count =  examFillBlanksService.save(fillblanks);
		    super.log(request, "添加填空题："+toJson(fillblanks));
		}
		map.put("status", count);
		
		return map;
	}
	
	/**
	 * 删除试题页面
	 *
	 * @version : Ver 1.0
	 * @author	: <a href="mailto:954643309@qq.com">hgx</a>
	 * @date	: 2015-7-9 上午9:17:55
	 */
	@RequestMapping(value="/deleteFillBlanks.json",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> deleteFillBlanks(HttpServletRequest request,String ids) {
		Map<String, Object> map = getStatusMap();
		System.out.println("ids="+ids);
		if (null != ids && ids.length()>0) {
			List<String> list=new ArrayList<String>();
			String [] arr=ids.split(",");
			for (String string : arr) {
				list.add(string);
			}
			int count = examFillBlanksService.deleteBatch(list, ExamFillBlanks.class);
			map.put("status", count);
			
			super.log(request, "删除填空题："+toJson(ids));
		}

		return map;
	}
	/**
	 * 导入Excel页面
	 *
	 * @author 	: <a href="mailto:954643309@qq.com">hgx</a>  2015-7-12 下午1:01:59
	 * @return
	 */
	@RequestMapping(value = "/breforeImportExcel.html", method = RequestMethod.GET)
	public String breforeImportExcel(HttpServletRequest request) {
		super.log(request, "跳转导入Excel页面");
		return "questiontypes/examfillblanks/importexcel";
	}
	/**
	 * 
	 *填空题导入Excel
	 * @author 	: <a href="mailto:954643309@qq.com">hgx</a>  2015-7-12 下午1:13:42
	 * @param file
	 * @param typeId
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/importExcel.json", method = RequestMethod.POST)
	@ResponseBody
	public String importExcel(HttpServletRequest request,@RequestParam("file") MultipartFile file,
			Long typeId) throws Exception {
		String msg = "导入失败";

		//excel中数据转换bean
		List<ExamQuestionModel> list = (List<ExamQuestionModel>)super.parseExcel(file, ExamTypeEnum.FILL, typeId);

		List<ExamFillBlanks> savelist = new ArrayList<ExamFillBlanks>();
		for (ExamQuestionModel examModel : list) {
			ExamFillBlanks fillblanks = new ExamFillBlanks();
			PropertyUtils.copyProperties(fillblanks, examModel);
			savelist.add(fillblanks);
		}

		long count = examFillBlanksService.saveBatch(savelist,ExamFillBlanks.class);
		if (count > 0) {
			msg = "导入成功: " + count + " 条";
		}
		super.log(request, "填空题"+msg);
		return msg;
	}
}
