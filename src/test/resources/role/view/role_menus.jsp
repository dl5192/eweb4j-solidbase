<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="panelBar">
	<ul class="toolBar">
		<li>
			<a title="确实要删除这些记录吗?" target="selectedTodo" rel="treeMenuIds"
				href="${BaseURL}roles/role-menu/remove/?roleId=${role.roleId}&_method=delete" class="delete">
				<span>批量删除树形菜单</span>
			</a>
		</li>
		<li class="line">line</li>
		
		<li>
			<a title="确实要删除这些记录吗?" target="selectedTodo" rel="navMenuIds"
				href="${BaseURL}roles/role-menu/remove/?roleId=${role.roleId}&_method=delete" class="delete">
				<span>批量删除导航菜单</span>
			</a>
		</li>
		
	</ul>
</div>

<table class="list" width="100%">
	<thead>
		<tr>
			<th width="30"><input type="checkbox" group="treeMenuIds,navMenuIds" class="checkboxCtrl"></th>
			<th width="200">菜单类型</th>
			<th width="300">菜单名称</th>
			<th>菜单链接</th>
			<th width="40">操作</th>
		</tr>
	</thead>

	<tbody>
		<c:if test="${role.menus == null && role.navMenus == null}">
	    	<tr><td colspan="4"><center>抱歉，没有任何记录。</center></td></tr>
	    </c:if>
		<c:forEach var="pojo" items="${role.menus}">
			<tr id="menu_item_${pojo.treeMenuId}" target="pojo_id" rel="${pojo.treeMenuId}">
				<td style="text-align:center;"><input id="${random}${pojo.treeMenuId}" name="treeMenuIds" value="${pojo.treeMenuId}" type="checkbox"></td>
				<td onclick="selectBox('${random}${pojo.treeMenuId}')">树形菜单</td>
				<td onclick="selectBox('${random}${pojo.treeMenuId}')">${pojo.name}</td>
				<td onclick="selectBox('${random}${pojo.treeMenuId}')">${pojo.href}</td>
				<td>
					<a href="#" title="删除" rel="menu_detail" onclick="return interceptOnClick(${pojo.treeMenuId });" link="${BaseURL}roles/role-menu/remove/?roleId=${role.roleId}&treeMenuIds=${pojo.treeMenuId}&_method=delete" id="menu_item_detail_${pojo.treeMenuId }" class="btnDel">删除</a>
				</td>
			</tr>
		</c:forEach>
		
		<c:forEach var="pojo" items="${role.navMenus}">
			<tr id="nav_item_${pojo.navMenuId}" rel="${pojo.navMenuId}">
				<td style="text-align:center;"><input id="${random}${pojo.navMenuId}" name="navMenuIds" value="${pojo.navMenuId}" type="checkbox"></td>
				<td onclick="selectBox('${random}${pojo.navMenuId}')">导航菜单</td>
				<td onclick="selectBox('${random}${pojo.navMenuId}')">${pojo.name}</td>
				<td onclick="selectBox('${random}${pojo.navMenuId}')">${pojo.href}</td>
				<td>
					<a href="#" title="删除" rel="menu_detail" onclick="return interceptOnClickForNav(${pojo.navMenuId });" link="${BaseURL}roles/role-menu/remove/?roleId=${role.roleId}&navMenuIds=${pojo.navMenuId}&_method=delete" id="nav_item_detail_${pojo.navMenuId }" class="btnDel">删除</a>
				</td>
			</tr>
		</c:forEach>
	</tbody>
</table>