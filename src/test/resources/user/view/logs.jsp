<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<div class="pageHeader">
	<form rel="pagerForm" onsubmit="return navTabSearch(this);" action="${listPage.searchForm.action}" method="get">
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
			<!--li><a class="add" href="${listPage.model}/new" target="dialog" title="添加"><span>添加</span></a></li>
			<li><a class="edit" href="${listPage.model}/{pojo_id}/edit" target="dialog" warn="请选择一条记录"><span>修改</span></a></li>
			<li><a title="确定要删除吗？" warn="您没有选择任何项目！请选择列表中的数据，选中项将会以蓝色高亮显示！" 
			target="selectedTodo" rel="ids" href="${listPage.model}/batchRemove?_method=delete" class="delete"><span>删除</span></a></li>
			-->
		</ul>
	</div>
	<table class="table" width="100%" layoutH="112">
		<thead>
			<tr>
				<th width="30"><input type="checkbox" group="ids" class="checkboxCtrl"></th>
				<c:forEach var="thead" items="${listPage.thead}">
					<th width="${thead.width}">${thead.label}</th>
				</c:forEach>
				<th width="60">操作</th>
			</tr>
		</thead>
		<tbody>
		<c:if test="${listPage.trdatas == null}">
	    	<tr><td><center>抱歉，没有任何记录。</center></td></tr>
	    </c:if>
		<c:forEach var="dataItem" items="${listPage.trdatas}">
			<tr target="pojo_id" rel="${dataItem.id}">
				<td><input id="${random}${dataItem.id }" name="ids" value="${dataItem.id }" type="checkbox"></td>
				<c:forEach var="item" items="${dataItem.datas }">
					<td onclick="selectBox('${random}${dataItem.id }')" name="ids">${item}</td>
				</c:forEach>
				<td>
					<a title="删除" target="ajaxTodo" href="${listPage.model}/${dataItem.id }?_method=delete" class="btnDel">删除</a>
					<a title="编辑" target="dialog" href="${listPage.model}/${dataItem.id }/edit" class="btnEdit">编辑</a>
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
		<form id="pagerForm" method="get" action="users/logs">
			<input type="hidden" name="pageNum" value="1" />
			<input type="hidden" name="numPerPage" value="${listPage.dpc.numPerPage}" />
			<input type="hidden" name="keyword" value="${listPage.searchForm.keyword}" />
		</form>

	</div>
</div>
