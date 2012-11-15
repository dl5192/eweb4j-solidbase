<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="panelBar">
	<ul class="toolBar">
		<li>
			<a title="确实要删除这些记录吗?" target="selectedTodo" rel="roleIds"
				href="${BaseURL}users/user-role/remove/?userId=${user.id}&_method=delete" class="delete">
				<span>批量删除</span>
			</a>
		</li>
	</ul>
</div>

<table class="list" width="100%">
	<thead>
		<tr>
			<th width="30"><input type="checkbox" group="roleIds" class="checkboxCtrl"></th>
			<th width="300">角色</th>
			<th>备注</th>
			<th width="40">操作</th>
		</tr>
	</thead>

	<tbody>
		<c:if test="${user.roles == null}">
	    	<tr><td colspan="4"><center>抱歉，没有任何记录。</center></td></tr>
	    </c:if>
		<c:forEach var="pojo" items="${user.roles}">
			<tr id="role_item_${pojo.roleId}" rel="${pojo.roleId}">
				<td style="text-align:center;"><input id="${random}${pojo.roleId}" name="roleIds" value="${pojo.roleId}" type="checkbox"></td>
				
				<td onclick="selectBox('${random}${pojo.roleId}')">${pojo.name}</td>
				<td onclick="selectBox('${random}${pojo.roleId}')">${pojo.description}</td>
				<td>
					<a href="#" title="删除" rel="role_detail" onclick="return interceptOnClick(${pojo.roleId });" link="${BaseURL}users/user-role/remove/?userId=${user.id}&roleIds=${pojo.roleId}&_method=delete" id="role_item_detail_${pojo.roleId }" class="btnDel">删除</a>
				</td>
			</tr>
		</c:forEach>
	</tbody>
</table>
