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
import com.happy.exam.model.ExamShortQuestion;
import com.happy.exam.service.ExamShortQuestionService;
import com.happy.exam.service.ExamTypeService;

/**
 * 简答题管理Action
 *
 * @version : Ver 1.0
 * @author	: <a href="mailto:954643309@qq.com">hgx</a>
 * @date	: 2015-7-9 上午9:17:55
 */
@Controller
@RequestMapping("/shortquestion")
public class ShortQuestionAction extends BaseAction{

	@Autowired
	private ExamTypeService examTypeService;
	
	@Autowired
	private ExamShortQuestionService examShortQuestionService;
	

	/**
	 * 简答题页面
	 *
	 * @author 	: <a href="mailto:954643309@qq.com">hgx</a>  2015-7-9 上午9:23:22
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/list.html", method = RequestMethod.GET)
	public String showShortQuestion(HttpServletRequest request,Model model) {
		super.log(request, "进入简答题列表页");
		return "/questiontypes/shortquestion/list";
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
		super.log(request, "加载简答题试题类型树结构");
		return "questiontypes/shortquestion/choiceExamTypeTree";
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
	public DataGridModel showExamShortQuestionlist(HttpServletRequest request,DatagridDto dgDto,ExamShortQuestion  shortquestion) {
		DataGridModel dataGridModel = new DataGridModel();
		
		shortquestion.setSortColumns("CREATETIME DESC");
		Long total = examShortQuestionService.getTotalCount(shortquestion);
		
		Pager pager = new Pager(dgDto.getPage(), dgDto.getRows(), total);
		pager = examShortQuestionService.findPageList(shortquestion, pager);

		dataGridModel.setRows(pager.getDatas());
		dataGridModel.setTotal(total);
		super.log(request, "加载简答题试题列表");
		return dataGridModel;
	}
	
	/**
	 * 跳转到编辑试题页面
	 *
	 * @version : Ver 1.0
	 * @author	: <a href="mailto:954643309@qq.com">hgx</a>
	 * @date	: 2015-7-9 上午9:17:55
	 */
	@RequestMapping(value = "/beforeEditShortQuestion.html", method = RequestMethod.GET)
	public String beforeEditShortQuestion(HttpServletRequest request,Model model, String id, String pname,
			String typeId, String flag) throws UnsupportedEncodingException {
		
		if (StringUtils.isNotBlank(flag) && flag.equals("2")) {// 修改用户信息回选
			ExamShortQuestion  shortquestion = examShortQuestionService.getById(
					Long.valueOf(id), ExamShortQuestion.class);
			model.addAttribute("shortquestion", shortquestion);
			super.log(request, "跳转修改简答题页面："+toJson(shortquestion));
		}else{
			ExamShortQuestion  shortquestion=new ExamShortQuestion();
			shortquestion.setTypeId(Long.valueOf(typeId));
			model.addAttribute("shortquestion", shortquestion);
			super.log(request, "跳转添加简答题页面："+toJson(shortquestion));
		}

		/*if (StringUtils.isNotBlank(pname)) {
			model.addAttribute("pname",java.net.URLDecoder.decode(pname, "UTF-8"));
		}*/

		model.addAttribute("flag", flag);
		//model.addAttribute("typeId", typeId);
		return "/questiontypes/shortquestion/edit";
	}
	
	/**
	 * 跳转到编辑试题页面
	 *
	 * @version : Ver 1.0
	 * @author	: <a href="mailto:954643309@qq.com">hgx</a>
	 * @date	: 2015-7-9 上午9:17:55
	 */
	@RequestMapping(value = "/editShortQuestion.json", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> editShortQuestion(HttpServletRequest request,Model model,
			ExamShortQuestion  shortquestion,String options) {
		Map<String, Object> map = getStatusMap();
		
		int count = 0;
		shortquestion.setScore(PoiUtil.getShortsScore(shortquestion.getQuestion()));//分数
		if(null != shortquestion && null !=shortquestion.getId()){//
			shortquestion.setUpdateTime(new Date());
			count = examShortQuestionService.update(shortquestion);
			map.put("flag",shortquestion.getId());
			super.log(request, "修改简答题："+toJson(shortquestion));
		}else{//添加
		    shortquestion.setStatus(1);
		    shortquestion.setCreateUser(super.getCurrentUserId());
		    shortquestion.setCreateTime(new Date());
		    count =  examShortQuestionService.save(shortquestion);
		    super.log(request, "添加简答题："+toJson(shortquestion));
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
	@RequestMapping(value="/deleteShortQuestion.json",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> deleteShortQuestion(HttpServletRequest request,String ids) {
		Map<String, Object> map = getStatusMap();
		System.out.println("ids="+ids);
		if (null != ids && ids.length()>0) {
			List<String> list=new ArrayList<String>();
			String [] arr=ids.split(",");
			for (String string : arr) {
				list.add(string);
			}
			int count = examShortQuestionService.deleteBatch(list, ExamShortQuestion.class);
			map.put("status", count);
			
			super.log(request, "删除简答题："+toJson(ids));
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
		return "questiontypes/shortquestion/importexcel";
	}
	/**
	 * 
	 *简答题导入Excel
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
		List<ExamQuestionModel> list = (List<ExamQuestionModel>)super.parseExcel(file, ExamTypeEnum.SHORTS, typeId);

		List<ExamShortQuestion> savelist = new ArrayList<ExamShortQuestion>();
		for (ExamQuestionModel examModel : list) {
			ExamShortQuestion shortquestion = new ExamShortQuestion();
			PropertyUtils.copyProperties(shortquestion, examModel);
			savelist.add(shortquestion);
		}

		long count = examShortQuestionService.saveBatch(savelist,ExamShortQuestion.class);
		if (count > 0) {
			msg = "导入成功: " + count + " 条";
		}
		super.log(request, "简答题"+msg);
		return msg;
	}
}
