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
import com.happy.exam.model.ExamOperationQuestion;
import com.happy.exam.model.ExamOperationQuestion;
import com.happy.exam.service.ExamOperationQuestionService;
import com.happy.exam.service.ExamOperationQuestionService;
import com.happy.exam.service.ExamTypeService;

/**
 * 操作题管理Action
 *
 * @version : Ver 1.0
 * @author	: <a href="mailto:954643309@qq.com">hgx</a>
 * @date	: 2015-7-9 上午9:17:55
 */
@Controller
@RequestMapping("/operquestion")
public class OperQuestionAction extends BaseAction{

	@Autowired
	private ExamTypeService examTypeService;
	
	@Autowired
	private ExamOperationQuestionService examOperationQuestionService;
	

	/**
	 * 操作题页面
	 *
	 * @author 	: <a href="mailto:954643309@qq.com">hgx</a>  2015-7-9 上午9:23:22
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/list.html", method = RequestMethod.GET)
	public String showOperQuestion(HttpServletRequest request,Model model) {
		super.log(request, "进入操作题列表页");
		return "/questiontypes/operquestion/list";
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
		super.log(request, "加载操作题试题类型树结构");
		return "questiontypes/operquestion/choiceExamTypeTree";
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
	public DataGridModel showExamOperationQuestionlist(HttpServletRequest request,DatagridDto dgDto,ExamOperationQuestion  operquestion) {
		DataGridModel dataGridModel = new DataGridModel();
		
		operquestion.setSortColumns("CREATETIME DESC");
		Long total = examOperationQuestionService.getTotalCount(operquestion);
		
		Pager pager = new Pager(dgDto.getPage(), dgDto.getRows(), total);
		pager = examOperationQuestionService.findPageList(operquestion, pager);

		dataGridModel.setRows(pager.getDatas());
		dataGridModel.setTotal(total);
		super.log(request, "加载操作题试题列表");
		return dataGridModel;
	}
	
	/**
	 * 跳转到编辑试题页面
	 *
	 * @version : Ver 1.0
	 * @author	: <a href="mailto:954643309@qq.com">hgx</a>
	 * @date	: 2015-7-9 上午9:17:55
	 */
	@RequestMapping(value = "/beforeEditOperQuestion.html", method = RequestMethod.GET)
	public String beforeEditOperQuestion(HttpServletRequest request,Model model, String id, String pname,
			String typeId, String flag) throws UnsupportedEncodingException {
		
		if (StringUtils.isNotBlank(flag) && flag.equals("2")) {// 修改用户信息回选
			ExamOperationQuestion  operquestion = examOperationQuestionService.getById(
					Long.valueOf(id), ExamOperationQuestion.class);
			model.addAttribute("operquestion", operquestion);
			super.log(request, "跳转修改操作题页面："+toJson(operquestion));
		}else{
			ExamOperationQuestion  operquestion=new ExamOperationQuestion();
			operquestion.setTypeId(Long.valueOf(typeId));
			model.addAttribute("operquestion", operquestion);
			super.log(request, "跳转添加操作题页面："+toJson(operquestion));
		}

		/*if (StringUtils.isNotBlank(pname)) {
			model.addAttribute("pname",java.net.URLDecoder.decode(pname, "UTF-8"));
		}*/

		model.addAttribute("flag", flag);
		//model.addAttribute("typeId", typeId);
		return "/questiontypes/operquestion/edit";
	}
	
	/**
	 * 跳转到编辑试题页面
	 *
	 * @version : Ver 1.0
	 * @author	: <a href="mailto:954643309@qq.com">hgx</a>
	 * @date	: 2015-7-9 上午9:17:55
	 */
	@RequestMapping(value = "/editOperQuestion.json", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> editOperQuestion(HttpServletRequest request,Model model,
			ExamOperationQuestion  operquestion,String options) {
		Map<String, Object> map = getStatusMap();
		
		int count = 0;
		operquestion.setScore(PoiUtil.getShortsScore(operquestion.getQuestion()));//分数
		if(null != operquestion && null !=operquestion.getId()){//
			operquestion.setUpdateTime(new Date());
			count = examOperationQuestionService.update(operquestion);
			map.put("flag",operquestion.getId());
			super.log(request, "修改操作题："+toJson(operquestion));
		}else{//添加
		    operquestion.setStatus(1);
		    operquestion.setCreateUser(super.getCurrentUserId());
		    operquestion.setCreateTime(new Date());
		    count =  examOperationQuestionService.save(operquestion);
		    super.log(request, "添加操作题："+toJson(operquestion));
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
	@RequestMapping(value="/deleteOperQuestion.json",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> deleteOperQuestion(HttpServletRequest request,String ids) {
		Map<String, Object> map = getStatusMap();
		System.out.println("ids="+ids);
		if (null != ids && ids.length()>0) {
			List<String> list=new ArrayList<String>();
			String [] arr=ids.split(",");
			for (String string : arr) {
				list.add(string);
			}
			int count = examOperationQuestionService.deleteBatch(list, ExamOperationQuestion.class);
			map.put("status", count);
			
			super.log(request, "删除操作题："+toJson(ids));
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
		return "questiontypes/operquestion/importexcel";
	}
	/**
	 * 
	 *操作题导入Excel
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
		List<ExamQuestionModel> list = (List<ExamQuestionModel>)super.parseExcel(file, ExamTypeEnum.OPERATE, typeId);

		List<ExamOperationQuestion> savelist = new ArrayList<ExamOperationQuestion>();
		for (ExamQuestionModel examModel : list) {
			ExamOperationQuestion operquestion = new ExamOperationQuestion();
			PropertyUtils.copyProperties(operquestion, examModel);
			savelist.add(operquestion);
		}

		long count = examOperationQuestionService.saveBatch(savelist,ExamOperationQuestion.class);
		if (count > 0) {
			msg = "导入成功: " + count + " 条";
		}
		super.log(request, "操作题"+msg);
		return msg;
	}
}
