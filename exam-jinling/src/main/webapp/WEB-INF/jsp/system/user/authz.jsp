<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
 
 <table class="table table-bordered">
   <caption>角色列表</caption>
   <thead>
      <tr>
        <th> &nbsp;</th>
         <th>角色名称</th>
         <th>备注</th>
      </tr>
   </thead>
   <tbody>
   	<c:forEach items="${rolelist }" var="el">
      <tr>
         <td>
			<input type="checkbox" name="roleChkbox" value="${el.roleId }">
		</td>
         <td>${el.roleName }</td>
         <td>${el.remark }</td>
      </tr>
   	</c:forEach>
   </tbody>
</table>