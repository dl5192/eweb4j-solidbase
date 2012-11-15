<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="panelBar">
	<ul class="toolBar">
		<li>
			<a title="确实要删除这些记录吗?" target="selectedTodo" rel="departmentIds"
				href="${BaseURL}users/user-department/remove/?userId=${user.id}&_method=delete" class="delete">
				<span>批量删除</span>
			</a>
		</li>
	</ul>
</div>

<table class="list" width="100%">
	<thead>
		<tr>
			<th width="30"><input type="checkbox" group="departmentIds" class="checkboxCtrl"></th>
			<th>部门</th>
			<th width="40">操作</th>
		</tr>
	</thead>

	<tbody>
		<c:if test="${user.departments == null}">
	    	<tr><td colspan="4"><center>抱歉，没有任何记录。</center></td></tr>
	    </c:if>
		<c:forEach var="pojo" items="${user.departments}">
			<tr id="department_item_${pojo.departId}" rel="${pojo.departId}">
				<td style="text-align:center;"><input id="${random}${pojo.departId}" name="departmentIds" value="${pojo.departId}" type="checkbox"></td>
				
				<td onclick="selectBox('${random}${pojo.departId}')">${pojo.code.remark}</td>
				<td>
					<a href="#" title="删除" rel="department_detail" onclick="return interceptOnClick(${pojo.departId });" link="${BaseURL}users/user-department/remove/?userId=${user.id}&departmentIds=${pojo.departId}&_method=delete" id="department_item_detail_${pojo.departId }" class="btnDel">删除</a>
				</td>
			</tr>
		</c:forEach>
	</tbody>
</table>
