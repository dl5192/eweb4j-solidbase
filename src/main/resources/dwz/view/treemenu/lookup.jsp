<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<div class="pageHeader">
	<form rel="pagerForm" method="get" action="${BaseURL}${listPage.searchForm.action}" onsubmit="return dwzSearch(this, 'dialog');">
	<div class="searchBar">
		<ul class="searchContent">
			<li>
				<label>菜单名称:</label>
				<input style="float:left;" class="textInput" name="keyword" value="${listPage.searchForm.keyword}" type="text">
				<div style="float:right; margin-top:-2px;" class="buttonActive"><div class="buttonContent"><button type="submit">查询</button></div></div>
			</li>
		</ul>
		
	</div>
	</form>
</div>
<div class="pageContent">

	<table class="table" layoutH="118" targettype="dialog" width="100%">
		<thead>
			<tr>
				<th>ID</th>
				<th>导航菜单</th>
				<th>父菜单</th>
				<th>菜单名称</th>
				<th>链接</th>
				<th width="80">查找带回</th>
			</tr>
		</thead>				
		<tbody>
			<c:forEach var="pojo" items="${listPage.pojos}">
			<tr>
				<td>${pojo.treeMenuId}</td>
				<td>${pojo.navMenu.name}</td>
				<td>${pojo.parent.name}</td>
				<td>${pojo.name}</td>
				<td>${pojo.href}</td>
				<td>
					<a class="btnSelect" href="javascript:$.bringBack({navMenuId:'${pojo.navMenu.navMenuId}', pid:'${pojo.parent.treeMenuId}', treeMenuId:'${pojo.treeMenuId}', name:'${pojo.name}'})" title="查找带回">选择</a>
				</td>
			</tr>
			</c:forEach>
		</tbody>
	</table>

	<div class="panelBar">
		<div class="pages">
			<span>每页</span>

			<select name="numPerPage" onchange="lookupPageBreak(this.value)">
				<option selected="selected" value="1">调整</option>
				<option value="10">10</option>
				<option value="20">20</option>
				<option value="50">50</option>
				<option value="100">100</option>
			</select>
			<span>条，共${listPage.dpc.allCount}条记录，每页${listPage.dpc.numPerPage}条，当前第${listPage.dpc.currentPage}/${listPage.dpc.pageCount}页</span>
		</div>
		<div class="pagination" targetType="dialog" totalCount="${listPage.dpc.allCount}" numPerPage="${listPage.dpc.numPerPage}" pageNumShown="10" currentPage="${listPage.dpc.currentPage}"></div>
		<form id="pagerForm" method="get" action="${BaseURL}${listPage.searchForm.action}">
			<input type="hidden" name="pageNum" value="1" />
			<input type="hidden" name="numPerPage" value="${listPage.dpc.numPerPage}" />
			<input type="hidden" name="keyword" value="${listPage.searchForm.keyword}" />
		</form>
	</div>
</div>