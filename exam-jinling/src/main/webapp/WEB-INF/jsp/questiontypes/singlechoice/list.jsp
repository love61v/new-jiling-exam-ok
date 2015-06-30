<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
<head>
<meta content='width=device-width, initial-scale=1, maximum-scale=1, singlechoice-scalable=no' name='viewport' />
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<base href="<%=basePath%>">

<title>单选题管理</title>
<link href='${ctx}/css/bootstrap/bootstrap.css' media='all' rel='stylesheet' type='text/css' /> 
<link rel="stylesheet" type="text/css" href="${ctx }/css/icon.css">
<link href='${ctx }/css/light-theme.css' id='color-settings-body-color' media='all' rel='stylesheet' type='text/css' />
<%-- <link rel="stylesheet" type="text/css" href="${ctx }/js/jquery-easyui/themes/default/easyui.css"> --%>
<link rel="stylesheet" type="text/css" href="${ctx }/js/jquery-easyui/themes/bootstrap/easyui.css">
<link rel="stylesheet" type="text/css" href="${ctx }/js/jquery-easyui/themes/bootstrap/datalist.css">


<script type="text/javascript" src="${ctx }/js/jquery-easyui/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="${ctx }/js/jquery-easyui/jquery.min.js"></script>
<script type="text/javascript" src="${ctx }/js/jquery-easyui/jquery.easyui.min.js"></script>
 
<body>
      <table id="singlechoice_table" cellspacing="0" cellpadding="0" toolbar="#tbar_single" style="margin-top:3px;">
        <thead>
            <tr>
                <th data-options="field:'id',width:20,align:'center'">编号</th>
                <th data-options="field:'question',width:50,align:'center'">问题</th>
                <th data-options="field:'answer',width:50,align:'center'">答案</th>
                <th data-options="field:'score',width:50,align:'center'">分值</th>
                <th data-options="field:'typeId',width:50,align:'center'">类别</th>
                <th data-options="field:'createTime',width:80, formatter:formatTime,align:'center'">创建时间</th>
                <th data-options="field:'updateTime',width:80, formatter:formatTime,align:'center'">更新时间</th>
            </tr>
        </thead>
   	</table>
   	
   	<div id="tbar_single">
   		<div class="form-inline" >
   				<form id="singlechoice_form" style="margin-top:20px;">
		   		<input type="text" class="form-control" name="question" id="question" placeholder="名称">
		    	<a class="btn btn-success" href="javascript:void(0)"   onclick="SinglechoiceHandler.search();">
					<i class="icon-search icon-white"></i>查询
				</a>
			 
			<span class="pull-right">
		    	<a class="btn btn-success" id="save" href="javascript:void(0)"   onclick="SinglechoiceHandler.importExcel();">
					<i class="icon-tint icon-white"></i>导入excel
				</a>
		    	<a class="btn btn-success" id="save" href="javascript:void(0)"   onclick="SinglechoiceHandler.exportExcel();">
					<i class="icon-fire icon-white"></i>导出模板
				</a>
		    	<a class="btn btn-success" id="save" href="javascript:void(0)"   onclick="SinglechoiceHandler.beforeEditSinglechoice(1);">
					<i class="icon-plus icon-white"></i>添加
				</a>
		    	<a class="btn btn-success" id="update" href="javascript:void(0)" onclick="SinglechoiceHandler.beforeEditSinglechoice(2);">
					<i class="icon-edit icon-white"></i>修改
				</a>
				<a class="btn btn-success" id="beforeDeleteSinglechoice" href="javascript:void(0)"  onclick="SinglechoiceHandler.beforeDeleteSinglechoice();">
					<i class="icon-trash icon-white"></i>删除 
				</a>
			</span>
			</form>
		</div>
	</div>
    
 <!-- 用户单选题目 -->
 <div class="modal fade" id="editSinglechoice">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" id="cancleEdit"  data-dismiss="modal" aria-hidden="true">×</button>
        <h4 class="modal-title"><span style="color:blue;">编辑单选题目</span></h4>
      </div>
      <!-- remote加载的页面渲染到此容器中 -->
       <div class="modal-body"></div>
      
      <div class="modal-footer">
        <button type="button" class="btn btn-danger" data-dismiss="modal"><i class="icon-remove icon-white"></i>取消</button>
        <button type="button" class="btn btn-success" onclick="SinglechoiceHandler.editSinglechoice();"><i class="icon-ok icon-white"></i>&nbsp;提&nbsp;&nbsp;交</button>
      </div>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

<!-- 导入excel -->
 <div class="modal fade" id="importExcelModal">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" id="cancleauthzSinglechoice"  data-dismiss="modal" aria-hidden="true">×</button>
        <h4 class="modal-title"><span style="color:blue;">导入excel</span></h4>
      </div>
      <!-- remote加载的页面渲染到此容器中 -->
       <div class="modal-body"></div>
      
      <div class="modal-footer">
        <button type="button" class="btn btn-danger" data-dismiss="modal"><i class="icon-remove icon-white"></i>取消</button>
        <button type="button" class="btn btn-success" onclick="SinglechoiceHandler.uploadExcel();"><i class="icon-ok icon-white"></i>&nbsp;确&nbsp;&nbsp;定</button>
      </div>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

 <!-- 提示是否删除 -->
 <div class="modal fade" id="isDeleteTip">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
        <h4 class="modal-title"><span style="color:blue;">提示</span></h4>
      </div>
      <div class="modal-body">
        <p><h3 style="color:red">你确定删除吗?</h3></p>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-danger" id="cancleDel" data-dismiss="modal">
        	<i class="icon-remove icon-white"></i>取消
        </button>
        <button type="button" class="btn btn-success" onclick="SinglechoiceHandler.deleteSinglechoice()">
        	<i class="icon-ok icon-white"></i>&nbsp;确&nbsp;&nbsp;定
        </button>
      </div>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->
    
<script src="${ctx}/js/bootstrap/bootstrap.min.js"></script>
<script src='${ctx}/js/plugins/validate/jquery.validate.min.js' type='text/javascript'></script>
<script src='${ctx}/js/plugins/validate/additional-methods.js' type='text/javascript'></script>
<script src='${ctx}/js/common.js' type='text/javascript'></script>
<script src='${ctx}/js/questiontypes/singlechoice/singlechoice.js' type='text/javascript'></script>
</body>
</html>