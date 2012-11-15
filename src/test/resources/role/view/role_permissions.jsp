<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="panelBar">
	<ul class="toolBar">
		<li>
			<a title="确实要删除这些记录吗?" target="selectedTodo" rel="permissionIds"
				href="${BaseURL}roles/role-permission/remove/?roleId=${role.roleId}&_method=delete" class="delete">
				<span>批量删除</span>
			</a>
		</li>
	</ul>
</div>

<table class="list" width="100%">
	<thead>
		<tr>
			<th width="30"><input type="checkbox" group="permissionIds" class="checkboxCtrl"></th>
			<th width="300">权限名称</th>
			<th>资源URI</th>
			<th width="200">HTTP方法</th>
			<th width="40">操作</th>
		</tr>
	</thead>

	<tbody>
		<c:if test="${role.permissions == null}">
	    	<tr><td colspan="4"><center>抱歉，没有任何记录。</center></td></tr>
	    </c:if>
		<c:forEach var="pojo" items="${role.permissions}">
			<tr id="permission_item_${pojo.permId}" target="pojo_id" rel="${pojo.permId}">
				<td style="text-align:center;"><input id="${random}${pojo.permId}" name="permissionIds" value="${pojo.permId}" type="checkbox"></td>
				
				<td onclick="selectBox('${random}${pojo.permId}')">${pojo.name}</td>
				<td onclick="selectBox('${random}${pojo.permId}')">${pojo.resource.uri}</td>
				<td onclick="selectBox('${random}${pojo.permId}')">${pojo.httpMethodStr}</td>
				<td>
					<a href="#" title="删除" rel="permission_detail" onclick="return interceptOnClick(${pojo.permId });" link="${BaseURL}roles/role-permission/remove/?roleId=${role.roleId}&permissionIds=${pojo.permId}&_method=delete" id="permission_item_detail_${pojo.permId }" class="btnDel">删除</a>
				</td>
			</tr>
		</c:forEach>
	</tbody>
</table>
