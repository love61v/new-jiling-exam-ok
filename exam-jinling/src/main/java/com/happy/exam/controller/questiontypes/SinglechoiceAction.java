package com.happy.exam.controller.questiontypes;

 
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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

/**
 * 单选题action
 *
 * @version : Ver 1.0
 * @author : <a href="mailto:hubo@95190.com">hubo</a>
 * @date : 2015年5月17日 下午5:41:39
 */
@Controller
@RequestMapping("/singlechoice")
public class SinglechoiceAction extends BaseAction{
 

	@Autowired
	private ExamSingleChoiceService examSingleChoiceService;
	
	/**
	 * 列表页面
	 *
	 * @author 	: <a href="mailto:hubo@95190.com">hubo</a>  
	 * 2015年5月16日 下午11:48:57
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/list.html", method = RequestMethod.GET)
	public String showOperates(HttpServletResponse res,HttpServletRequest req) {
		return "questiontypes/singlechoice/list";
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
	public DataGridModel showlist(DatagridDto dgDto,ExamSingleChoice singleChoice) {
		DataGridModel dataGridModel = new DataGridModel();
		
		singleChoice.setSortColumns("CREATETIME DESC"); //按创建时间排序
		Long total = examSingleChoiceService.getTotalCount(singleChoice);
		
		Pager pager = new Pager(dgDto.getPage(), dgDto.getRows(), total);
		pager = examSingleChoiceService.findPageList(singleChoice, pager);

		dataGridModel.setRows(pager.getDatas());
		dataGridModel.setTotal(total);

		return dataGridModel;
	}
	
	/**
	 * 导入excel页面
	 *
	 * @author 	: <a href="mailto:hubo@95190.com">hubo</a>  
	 * 2015年5月16日 下午11:48:57
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/breforeImportExcel.html", method = RequestMethod.GET)
	public String breforeImportExcel() {
		return "questiontypes/singlechoice/importexcel";
	}
	
	/**
	 * 导入解析excel
	 *
	 * @author 	: <a href="mailto:358911056@qq.com">hubo</a>  2015-6-29 下午2:52:25
	 * @param file
	 * @param request
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping(value = "/importExcel.html", method = RequestMethod.POST)
	public void importExcel(@RequestParam("file")MultipartFile file,int type) throws Exception{
		if(!file.isEmpty()){
			String filename  = file.getOriginalFilename();
			if(filename.endsWith("xls") || filename.endsWith("xlsx")){
				InputStream fileIn = file.getInputStream();
				
				ExamQuestionModel model = new ExamQuestionModel();
				model.setCreateUser(super.getCurrentSystemUser().getUserId());
				model.setCreateTime(new Date());
				
				List<ExamQuestionModel> list = PoiUtil.parseList(fileIn, model, ExamTypeEnum.SINGLE);
				List<ExamSingleChoice> savelist = new ArrayList<ExamSingleChoice>();
				PropertyUtils.copyProperties(savelist, list);
				
				
				long count = examSingleChoiceService.saveBatch(savelist, ExamSingleChoice.class);
			}
		}
	}
}
