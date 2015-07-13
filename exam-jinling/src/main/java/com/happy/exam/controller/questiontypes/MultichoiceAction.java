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
import com.happy.exam.model.ExamMultipleChoice;
import com.happy.exam.service.ExamMultipleChoiceService;
import com.happy.exam.service.ExamTypeService;

/**
 * 多选题管理Action
 *
 * @version : Ver 1.0
 * @author	: <a href="mailto:954643309@qq.com">hgx</a>
 * @date	: 2015-7-9 上午9:17:55
 */
@Controller
@RequestMapping("/multichoice")
public class MultichoiceAction extends BaseAction{

	@Autowired
	private ExamTypeService examTypeService;
	
	@Autowired
	private ExamMultipleChoiceService examMultipleChoiceService;
	

	/**
	 * 多选题页面
	 *
	 * @author 	: <a href="mailto:954643309@qq.com">hgx</a>  2015-7-9 上午9:23:22
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/list.html", method = RequestMethod.GET)
	public String showMultiChoice(HttpServletRequest request,Model model) {
		super.log(request, "进入多选题列表页");
		return "/questiontypes/multichoice/list";
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
		super.log(request, "加载多选题试题类型树结构");
		return "questiontypes/multichoice/choiceExamTypeTree";
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
	public DataGridModel showExamMultipleChoicelist(HttpServletRequest request,
			DatagridDto dgDto, ExamMultipleChoice multichoice) {
		DataGridModel dataGridModel = new DataGridModel();

		multichoice.setSortColumns("CREATETIME DESC");
		Long total = examMultipleChoiceService.getTotalCount(multichoice);

		Pager pager = new Pager(dgDto.getPage(), dgDto.getRows(), total);
		pager = examMultipleChoiceService.findPageList(multichoice, pager);

		dataGridModel.setRows(pager.getDatas());
		dataGridModel.setTotal(total);
		super.log(request, "加载多选题试题列表");
		return dataGridModel;
	}

	/**
	 * 跳转到编辑试题页面
	 * 
	 * @version : Ver 1.0
	 * @author : <a href="mailto:954643309@qq.com">hgx</a>
	 * @date : 2015-7-9 上午9:17:55
	 */
	@RequestMapping(value = "/beforeEditMultiChoice.html", method = RequestMethod.GET)
	public String beforeEditMultiChoice(HttpServletRequest request,
			Model model, String id, String pname, String typeId, String flag)
			throws UnsupportedEncodingException {

		if (StringUtils.isNotBlank(flag) && flag.equals("2")) {// 修改用户信息回选
			ExamMultipleChoice multichoice = examMultipleChoiceService.getById(
					Long.valueOf(id), ExamMultipleChoice.class);
		String arr[] =multichoice.getPrototypeQuestion().split("\r\n");
			multichoice.setPrototypeQuestion(arr[0]);
			model.addAttribute("options",arr[1]);
			model.addAttribute("multichoice", multichoice);
			super.log(request, "跳转修改多选题页面："+toJson(multichoice));
		}else{
			ExamMultipleChoice  multichoice=new ExamMultipleChoice();
			multichoice.setTypeId(Long.valueOf(typeId));
			model.addAttribute("multichoice", multichoice);
			super.log(request, "跳转添加多选题页面："+toJson(multichoice));
		}

		/*if (StringUtils.isNotBlank(pname)) {
			model.addAttribute("pname",java.net.URLDecoder.decode(pname, "UTF-8"));
		}*/

		model.addAttribute("flag", flag);
		//model.addAttribute("typeId", typeId);
		return "/questiontypes/multichoice/edit";
	}
	
	/**
	 * 跳转到编辑试题页面
	 *
	 * @version : Ver 1.0
	 * @author	: <a href="mailto:954643309@qq.com">hgx</a>
	 * @date	: 2015-7-9 上午9:17:55
	 */
	@RequestMapping(value = "/editMultiChoice.json", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> editMultiChoice(HttpServletRequest request,Model model,
			ExamMultipleChoice  multichoice,String options) {
		Map<String, Object> map = getStatusMap();
		
		int count = 0;
		String content = multichoice.getPrototypeQuestion();
		multichoice.setPrototypeQuestion(content + "\r\n" + options);
		String answer = PoiUtil.getSingleMultiAnswer(content);
		multichoice.setAnswer(answer);
		multichoice.setQuestion(PoiUtil.getSingleMultiQuestion(content, options,ExamTypeEnum.MULTI.getKey()));
		multichoice.setScore("0.5");
		
		if(null != multichoice && null !=multichoice.getId()){//
			multichoice.setUpdateTime(new Date());
			count = examMultipleChoiceService.update(multichoice);
			map.put("flag",multichoice.getId());
			super.log(request, "修改多选题："+toJson(multichoice));
		}else{//添加
		    multichoice.setStatus(1);
		    multichoice.setCreateUser(super.getCurrentUserId());
		    multichoice.setCreateTime(new Date());
		    count =  examMultipleChoiceService.save(multichoice);
		    super.log(request, "添加多选题："+toJson(multichoice));
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
	@RequestMapping(value="/deleteMultiChoice.json",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> deleteMultiChoice(HttpServletRequest request,String ids) {
		Map<String, Object> map = getStatusMap();
		System.out.println("ids="+ids);
		if (null != ids && ids.length()>0) {
			List<String> list=new ArrayList<String>();
			String [] arr=ids.split(",");
			for (String string : arr) {
				list.add(string);
			}
			int count = examMultipleChoiceService.deleteBatch(list, ExamMultipleChoice.class);
			map.put("status", count);
			
			super.log(request, "删除多选题："+toJson(ids));
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
		return "questiontypes/multichoice/importexcel";
	}
	/**
	 * 
	 *多选题导入Excel
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
		List<ExamQuestionModel> list = (List<ExamQuestionModel>)super.parseExcel(file, ExamTypeEnum.MULTI, typeId);

		List<ExamMultipleChoice> savelist = new ArrayList<ExamMultipleChoice>();
		for (ExamQuestionModel examModel : list) {
			ExamMultipleChoice multichoice = new ExamMultipleChoice();
			PropertyUtils.copyProperties(multichoice, examModel);
			savelist.add(multichoice);
		}

		long count = examMultipleChoiceService.saveBatch(savelist,ExamMultipleChoice.class);
		if (count > 0) {
			msg = "导入成功: " + count + " 条";
		}
		super.log(request, "多选题"+msg);
		return msg;
	}
}
