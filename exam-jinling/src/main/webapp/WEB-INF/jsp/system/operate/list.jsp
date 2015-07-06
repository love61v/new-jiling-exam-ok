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
<meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport' />
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<base href="<%=basePath%>">

<title>操作权限管理</title>
</head>
<body>
      <table id="operate_table" cellspacing="0" cellpadding="0" toolbar="#operate_toolbar" style="margin-top:3px;">
        <thead>
            <tr>
                <th data-options="field:'operateName',width:50,align:'center'">名称</th>
                <th data-options="field:'engName',width:50,align:'center'">英文名</th>
                <th data-options="field:'status',width:30,align:'center',formatter: formatStatus">状态</th>
                <th data-options="field:'remark',width:50,align:'center'">备注</th>
            </tr>
        </thead>
   	</table>
   	
   	<div id="operate_toolbar">
   		<div class="form-inline" >
   			<form id="operate_form" style="margin-top:20px;">
		   		<input type="text" class="form-control" id="operateName" placeholder="请输入名称">
		    	<a class="btn btn-success" href="javascript:void(0)"   onclick="OperateHandler.search();">
					<i class="icon-search icon-white"></i>查询
				</a>
			 
				<div class="pull-right"> 
				  <div class="btn-group" data-toggle="buttons-checkbox">
				    	<a class="btn btn-success" id="saveOperate" href="javascript:void(0)"   onclick="OperateHandler.beforeEditOperate(1);">
							<i class="icon-plus icon-white"></i>添加
						</a>
				    	<a class="btn btn-success" id="updateOperate" href="javascript:void(0)" onclick="OperateHandler.beforeEditOperate(2);">
							<i class="icon-edit icon-white"></i>修改
						</a>
						<a class="btn btn-success" id="beforeDeleteOperate" href="javascript:void(0)"  onclick="OperateHandler.beforeDeleteOperate();">
							<i class="icon-trash icon-white"></i>删除 
						</a>
					</div>
				</div>
		 </form>
		</div>
	</div>
    
 <!-- 用户编辑 -->
 <div class="modal fade" id="editOperate">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" id="cancleEditOperate"  data-dismiss="modal" aria-hidden="true">×</button>
        <h4 class="modal-title"><span style="color:blue;">编辑用户</span></h4>
      </div>
      <!-- remote加载的页面渲染到此容器中 -->
       <div class="modal-body"></div>
      
      <div class="modal-footer">
        <button type="button" class="btn btn-danger" data-dismiss="modal"><i class="icon-remove icon-white"></i>取消</button>
        <button type="button" class="btn btn-success" onclick="OperateHandler.editOperate();"><i class="icon-ok icon-white"></i>&nbsp;提&nbsp;&nbsp;交</button>
      </div>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->


 <!-- 提示是否删除 -->
 <div class="modal fade" id="isDeleteOperateTip">
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
        <button type="button" class="btn btn-success" onclick="OperateHandler.deleteOperate()">
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
<script src='${ctx}/js/system/operate/operate.js' type='text/javascript'></script>

</body>
</html>