<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<div class="pageHeader">
	<form rel="pagerForm" onsubmit="return navTabSearch(this);" action="${BaseURL}${listPage.searchForm.action}" method="get">
	<div class="searchBar">
		<table class="searchContent">
			<tr>
				<td>
					&nbsp;&nbsp;关键词：<input name="keyword" value="" class="required" type="text"/>
				</td>
                <td>
                    <div class="subBar" style="margin-top:-4px;">
                        <ul style="float:left;">
                            <li><div class="buttonActive"><div class="buttonContent"><button type="submit">查询</button></div></div></li>
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
			<li><a class="add" href="${BaseURL}${listPage.model}/new" rel="${listPage.model}_new" target="dialog" title="添加"><span>添加</span></a></li>
			<li><a class="edit" href="${BaseURL}${listPage.model}/{pojo_id}/edit" rel="${listPage.model}_edit" target="dialog" warn="请选择一条记录"><span>修改</span></a></li>
			<li><a title="确定要删除吗？" warn="您没有选择任何项目！请选择列表中的数据，选中项将会以蓝色高亮显示！" 
			target="selectedTodo" rel="ids" href="${BaseURL}${listPage.model}/batchRemove?_method=delete" class="delete"><span>删除</span></a></li>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="112">
		<thead>
			<tr>
				<th width="30">
				<c:if test="${fn:length(listPage.thead) > 0}">
				<input type="checkbox" group="ids" class="checkboxCtrl">
				</c:if>
				</th>
				<c:forEach var="thead" items="${listPage.thead}">
					<th width="${thead.width}">${thead.label}</th>
				</c:forEach>
				<c:if test="${fn:length(listPage.thead) > 0}">
				<th width="60">操作</th>
				</c:if>
			</tr>
		</thead>
		<tbody>
		<c:if test="${fn:length(listPage.trdatas) == 0 || listPage.trdatas == null}">
	    	<tr><td><center>还没有内容。<a href="${BaseURL}${listPage.model}/new" rel="${listPage.model}_new" target="dialog" style="color:blue; text-decoration:underline;">点击添加+</a></center></td></tr>
	    </c:if>
		<c:forEach var="dataItem" items="${listPage.trdatas}">
			<tr target="pojo_id" rel="${dataItem.id}">
				<td><input id="${random}${dataItem.id }" name="ids" value="${dataItem.id }" type="checkbox"></td>
				<c:forEach var="item" items="${dataItem.datas }">
					<td onclick="selectBox('${random}${dataItem.id }')" name="ids">${item}</td>
				</c:forEach>
				<td>
					<a title="删除" target="ajaxTodo" href="${BaseURL}${listPage.model}/${dataItem.id }?_method=delete" class="btnDel">删除</a>
					<a title="编辑depart_${dataItem.id}" target="dialog" href="${BaseURL}${listPage.model}/${dataItem.id }/edit" rel="${listPage.model}_${dataItem.id}_edit" class="btnEdit">编辑</a>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="panelBar">
		<div class="pages">
			<span>每页</span>
			<select class="combox" name="numPerPage" change="navTabPageBreak" param="numPerPage">
				<option value="1">调整</option>
				<option value="10">10</option>
				<option value="20">20</option>
				<option value="50">50</option>
				<option value="100">100</option>
				<option value="200">200</option>
			</select>
			<span>条，共${listPage.dpc.allCount}条记录，每页${listPage.dpc.numPerPage}条，当前第${listPage.dpc.currentPage}/${listPage.dpc.pageCount}页</span>
		</div>
		
		<div class="pagination" targetType="navTab" totalCount="${listPage.dpc.allCount}" numPerPage="${listPage.dpc.numPerPage}" pageNumShown="10" currentPage="${listPage.dpc.currentPage}"></div>
		<form id="pagerForm" method="get" action="${BaseURL}${listPage.searchForm.action}">
			<input type="hidden" name="pageNum" value="1" />
			<input type="hidden" name="numPerPage" value="${listPage.dpc.numPerPage}" />
			<input type="hidden" name="keyword" value="${listPage.searchForm.keyword}" />
		</form>

	</div>
</div>
