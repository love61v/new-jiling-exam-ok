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
import com.happy.exam.model.ExamSingleChoice;
import com.happy.exam.service.ExamSingleChoiceService;
import com.happy.exam.service.ExamTypeService;

/**
 * 单选题管理Action
 *
 * @version : Ver 1.0
 * @author	: <a href="mailto:954643309@qq.com">hgx</a>
 * @date	: 2015-7-9 上午9:17:55
 */
@Controller
@RequestMapping("/singlechoice")
public class SinglechoiceAction extends BaseAction{

	@Autowired
	private ExamTypeService examTypeService;
	
	@Autowired
	private ExamSingleChoiceService examSingleChoiceService;
	

	/**
	 * 单选题页面
	 *
	 * @author 	: <a href="mailto:954643309@qq.com">hgx</a>  2015-7-9 上午9:23:22
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/list.html", method = RequestMethod.GET)
	public String showSingleChoice(HttpServletRequest request,Model model) {
		super.log(request, "进入单选题列表页");
		return "/questiontypes/singlechoice/list";
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
		super.log(request, "加载单选题试题类型树结构");
		return "questiontypes/singlechoice/choiceExamTypeTree";
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
	public DataGridModel showExamSingleChoicelist(HttpServletRequest request,DatagridDto dgDto,ExamSingleChoice  singlechoice) {
		DataGridModel dataGridModel = new DataGridModel();
		
		singlechoice.setSortColumns("CREATETIME DESC");
		Long total = examSingleChoiceService.getTotalCount(singlechoice);
		
		Pager pager = new Pager(dgDto.getPage(), dgDto.getRows(), total);
		pager = examSingleChoiceService.findPageList(singlechoice, pager);

		dataGridModel.setRows(pager.getDatas());
		dataGridModel.setTotal(total);
		super.log(request, "加载单选题试题列表");
		return dataGridModel;
	}
	
	/**
	 * 跳转到编辑试题页面
	 *
	 * @version : Ver 1.0
	 * @author	: <a href="mailto:954643309@qq.com">hgx</a>
	 * @date	: 2015-7-9 上午9:17:55
	 */
	@RequestMapping(value = "/beforeEditSingleChoice.html", method = RequestMethod.GET)
	public String beforeEditSingleChoice(HttpServletRequest request,Model model, String id, String pname,
			String typeId, String flag) throws UnsupportedEncodingException {
		
		if (StringUtils.isNotBlank(flag) && flag.equals("2")) {// 修改用户信息回选
			ExamSingleChoice  singlechoice = examSingleChoiceService.getById(
					Long.valueOf(id), ExamSingleChoice.class);
			String arr[] =singlechoice.getPrototypeQuestion().split("\r\n");
			singlechoice.setPrototypeQuestion(arr[0]);
			model.addAttribute("options",arr[1]);
			model.addAttribute("singlechoice", singlechoice);
			super.log(request, "跳转修改单选题页面："+toJson(singlechoice));
		}else{
			ExamSingleChoice  singlechoice=new ExamSingleChoice();
			singlechoice.setTypeId(Long.valueOf(typeId));
			model.addAttribute("singlechoice", singlechoice);
			super.log(request, "跳转添加单选题页面："+toJson(singlechoice));
		}

		/*if (StringUtils.isNotBlank(pname)) {
			model.addAttribute("pname",java.net.URLDecoder.decode(pname, "UTF-8"));
		}*/

		model.addAttribute("flag", flag);
		//model.addAttribute("typeId", typeId);
		return "/questiontypes/singlechoice/edit";
	}
	
	/**
	 * 跳转到编辑试题页面
	 *
	 * @version : Ver 1.0
	 * @author	: <a href="mailto:954643309@qq.com">hgx</a>
	 * @date	: 2015-7-9 上午9:17:55
	 */
	@RequestMapping(value = "/editSingleChoice.json", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> editSingleChoice(HttpServletRequest request,Model model,
			ExamSingleChoice  singlechoice,String options) {
		Map<String, Object> map = getStatusMap();
		int count = 0;
		
		String content = singlechoice.getPrototypeQuestion();
		singlechoice.setPrototypeQuestion(content + "\r\n" + options);
		String answer = PoiUtil.getSingleMultiAnswer(content);
		singlechoice.setAnswer(answer);
		singlechoice.setQuestion(PoiUtil.getSingleMultiQuestion(content,options,ExamTypeEnum.SINGLE.getKey()));
		singlechoice.setScore("0.5");
		
		if(null != singlechoice && null != singlechoice.getId()){//修改
			singlechoice.setUpdateTime(new Date());
			count = examSingleChoiceService.update(singlechoice);
			map.put("flag",singlechoice.getId());
			super.log(request, "修改单选题："+toJson(singlechoice));
		}else{//添加
		    singlechoice.setStatus(1);
		    singlechoice.setCreateUser(super.getCurrentUserId());
		    singlechoice.setCreateTime(new Date());
		    count =  examSingleChoiceService.save(singlechoice);
		    super.log(request, "添加单选题："+toJson(singlechoice));
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
	@RequestMapping(value="/deleteSingleChoice.json",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> deleteSingleChoice(HttpServletRequest request,String ids) {
		Map<String, Object> map = getStatusMap();
		System.out.println("ids="+ids);
		if (null != ids && ids.length()>0) {
			List<String> list=new ArrayList<String>();
			String [] arr=ids.split(",");
			for (String string : arr) {
				list.add(string);
			}
			int count = examSingleChoiceService.deleteBatch(list, ExamSingleChoice.class);
			map.put("status", count);
			
			super.log(request, "删除单选题："+toJson(ids));
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
		return "questiontypes/singlechoice/importexcel";
	}
	/**
	 * 
	 *单选题导入Excel
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
		List<ExamQuestionModel> list = (List<ExamQuestionModel>)super.parseExcel(file, ExamTypeEnum.SINGLE, typeId);

		List<ExamSingleChoice> savelist = new ArrayList<ExamSingleChoice>();
		for (ExamQuestionModel examModel : list) {
			ExamSingleChoice singlechoice = new ExamSingleChoice();
			PropertyUtils.copyProperties(singlechoice, examModel);
			savelist.add(singlechoice);
		}

		long count = examSingleChoiceService.saveBatch(savelist,ExamSingleChoice.class);
		if (count > 0) {
			msg = "导入成功: " + count + " 条";
		}
		super.log(request, "单选题"+msg);
		return msg;
	}
}
