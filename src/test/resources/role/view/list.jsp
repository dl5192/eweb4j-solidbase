<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="pageHeader">
<form rel="pagerForm" onsubmit="return navTabSearch(this);"
	action="${BaseURL}${listPage.searchForm.action}" method="get">
<div class="searchBar">
<table class="searchContent">
	<tr>
		<td>&nbsp;&nbsp;角色名称：<input name="keyword" value=""
			class="required" type="text" /></td>
		<td>
		<div class="subBar" style="margin-top: -4px;">
		<ul style="float: left;">
			<li>
			<div class="buttonActive">
			<div class="buttonContent">
			<button type="submit">查询</button>
			</div>
			</div>
			</li>
		</ul>
		</div>
		</td>
	</tr>
</table>
</div>
</form>
</div>
<div class="pageContent">
<div class="panelBar">
<ul class="toolBar">
	<li><a class="add" href="${BaseURL}${listPage.model}/new" target="dialog" width="300" height="200" title="添加"><span>添加</span></a></li>
	<li><a class="edit" href="${BaseURL}${listPage.model}/{pojo_id}/edit" target="dialog" width="300" height="200" warn="请选择一条记录"><span>修改</span></a></li>
	<li><a title="确实要删除这些记录吗?" target="selectedTodo" rel="ids"
		href="${BaseURL}${listPage.model}/batchRemove?_method=delete" class="delete"><span>删除</span></a></li>
	<li class="line">line</li>
	<li><a class="icon" href="javascript:;"><span>导入EXCEL</span></a></li>
</ul>
</div>
<table class="table" width="100%" layoutH="112">
	<thead>
		<tr>
			<th width="30"><input type="checkbox" group="ids"
				class="checkboxCtrl"></th>
			<!-- >th width="50">ID</th-->
			<th width="200">角色名称</th>
			<th>角色描述</th>
			<th width="80">操作</th>
		</tr>
	</thead>
	<tbody>
	    <c:if test="${listPage.pojos == null}">
	    	<tr><td><center>抱歉，没有任何记录。</center></td></tr>
	    </c:if>
		<c:forEach var="pojo" items="${listPage.pojos}">
			<tr target="pojo_id" rel="${pojo.roleId}">
				<td><input id="${random}${pojo.roleId}" name="ids"
					value="${pojo.roleId}" type="checkbox"></td>
				<!--td onclick="selectBox('${random}${pojo.roleId}')">${pojo.roleId}</td-->
				<td onclick="selectBox('${random}${pojo.roleId}')">${pojo.name}</td>
				<td onclick="selectBox('${random}${pojo.roleId}')">${pojo.description}</td>
				<td><a title="删除" target="ajaxTodo"
					href="${BaseURL}${listPage.model}/${pojo.roleId}?_method=delete" class="btnDel">删除</a>
				<a title="编辑" target="dialog" href="${BaseURL}${listPage.model}/${pojo.roleId}/edit"
					class="btnEdit" width="300" height="200">编辑</a></td>
			</tr>
		</c:forEach>
	</tbody>
</table>
<div class="panelBar">
<div class="pages"><span>每页</span> <select class="combox"
	name="numPerPage" change="navTabPageBreak" param="numPerPage">
	<option value="1">调整</option>
	<option value="10">10</option>
	<option value="20">20</option>
	<option value="50">50</option>
	<option value="100">100</option>
	<option value="200">200</option>
</select> <span>条，共${listPage.dpc.allCount}条记录，每页${listPage.dpc.numPerPage}条，当前第${listPage.dpc.currentPage}/${listPage.dpc.pageCount}页</span>
</div>

<div class="pagination" targetType="navTab" totalCount="${listPage.dpc.allCount}"
	numPerPage="${listPage.dpc.numPerPage}" pageNumShown="10"
	currentPage="${listPage.dpc.currentPage}">
</div>
<form id="pagerForm" method="get" action="${BaseURL}${listPage.searchForm.action}">
    <input type="hidden" name="pageNum" value="1" /> 
	<input type="hidden" name="numPerPage" value="${listPage.dpc.numPerPage}" /> 
	<input type="hidden" name="keyword" value="${listPage.searchForm.keyword}" />
</form>

</div>
</div>
