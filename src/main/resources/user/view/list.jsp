<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
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
			<li><a class="add" href="${BaseURL}${listPage.model}/new" target="dialog" width="500" height="600" title="添加"><span>添加</span></a></li>
			<li><a class="edit" href="${BaseURL}${listPage.model}/{pojo_id}/edit" width="500" height="600" target="dialog" warn="请选择一条记录"><span>修改</span></a></li>
			<li><a title="确实要删除这些用户信息吗?" target="selectedTodo" rel="ids" href="${BaseURL}${listPage.model}/batchRemove?_method=delete" class="delete"><span>删除</span></a></li>
			<li><a title="确实要锁定这些用户吗?" target="selectedTodo" rel="ids" href="${BaseURL}${listPage.model}/batchLock?_method=put" class="lock"><span>锁定</span></a></li>
			<li><a title="确实要解锁这些用户吗?" target="selectedTodo" rel="ids" href="${BaseURL}${listPage.model}/batchUnLock?_method=put" class="unLock"><span>解锁</span></a></li>
			<li class="line">line</li>
			<li><a class="icon" href="javascript:;"><span>导入EXCEL</span></a></li>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="112">
		<thead>
			<tr>
				<th width="30"><input type="checkbox" group="ids" class="checkboxCtrl"></th>
				<!--th width="50">ID</th-->
				<th width="200">账号</th>
				<th width="200">姓名</th>
				<th width="200">角色</th>
				<th width="200">部门</th>
				<th width="200">办公电话</th>
				<th width="200">手机号码</th>
				<!-- 
				<th>密码</th>
				 -->
				<th width="100">状态</th>
				<th width="100">帐号有效期</th>
				<th width="50">有效</th>
				<th width="200">上一次登陆时间</th>
				<th width="200">上一次登陆IP</th>
				<th width="200">注册时间</th>
				<th width="160">操作</th>
			</tr>
		</thead>
		<tbody>
        <c:if test="${listPage.pojos == null}">
	    	<tr><td><center>抱歉，没有任何记录。</center></td></tr>
	    </c:if>
        
		<c:forEach var="pojo" items="${listPage.pojos}">
			<tr target="pojo_id" rel="${pojo.id}">
				<td><input id="${random}${pojo.id}" name="ids" value="${pojo.id}" type="checkbox"></td>
				<!--td onclick="selectBox('${random}${pojo.id}')">${pojo.id}</td-->
				<td onclick="selectBox('${random}${pojo.id}')">${pojo.account}</td>
				<td onclick="selectBox('${random}${pojo.id}')">${pojo.trueName}</td>
				<td onclick="selectBox('${random}${pojo.id}')">
					<c:forEach var="role" items="${pojo.roles}" >
						${role.name},
					</c:forEach>	
				</td>
				<td onclick="selectBox('${random}${pojo.id}')">
					<c:forEach var="depart" items="${pojo.departments}" >
						${depart.code.remark},
					</c:forEach>
				</td>
				<td onclick="selectBox('${random}${pojo.id}')">${pojo.officePhone}</td>
				<td onclick="selectBox('${random}${pojo.id}')">${pojo.mobileNum}</td>
				<!-- 
				<td onclick="selectBox('${random}${pojo.id}')">******</td>
				 -->
				<td onclick="selectBox('${random}${pojo.id}')">
					<c:if test="${pojo.status == '正常'}"><span class="valid">正常</span></c:if>
					<c:if test="${pojo.status == '锁定'}"><span class="invalid">锁定</span></c:if>
				</td>
				<td onclick="selectBox('${random}${pojo.id}')">${pojo.availablePeriod}</td>
				<td onclick="selectBox('${random}${pojo.id}')">${pojo.isValid}</td>
				<td onclick="selectBox('${random}${pojo.id}')">${pojo.lastLoginTime}</td>
				<td onclick="selectBox('${random}${pojo.id}')">${pojo.lastLoginIp}</td>
				<td onclick="selectBox('${random}${pojo.id}')">${pojo.regTime}</td>
				<td>
                	<a title="锁定" target="ajaxTodo" href="${BaseURL}${listPage.model}/${pojo.id}/lock?_method=put" class="btnLock">锁定</a>
                    <a title="解锁" target="ajaxTodo" href="${BaseURL}${listPage.model}/${pojo.id}/unLock?_method=put" class="btnUnLock">解锁</a>
					<a title="删除" target="ajaxTodo" href="${BaseURL}${listPage.model}/${pojo.id}?_method=delete" class="btnDel">删除</a>
					<a title="编辑" width="500" height="600" target="dialog" href="${BaseURL}${listPage.model}/${pojo.id}/edit" class="btnEdit">编辑</a>
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
