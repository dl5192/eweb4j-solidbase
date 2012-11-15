<%@ page contentType="text/html; charset=utf-8" language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<div class="page">
	<div class="pageContent">
        <form method="post" action="${BaseURL}${model}/" class="pageForm required-validate" onsubmit="return validateCallback(this, ${openType}AjaxDone)">
            <div class="pageFormContent" layoutH="58">
				
				<span style="clear: both; display: block; height: 10px;" />
				<label>部门名称：</label>
				<input class="required " name="department.code.remark" type="text" />

				<span style="clear: both; display: block; height: 10px;" />
				<label>部门代码：</label>
				<input class="required " name="department.code.codeValue" type="text" />
				
				<span style="clear: both; display: block; height: 10px;" />
				<label>部门级别：</label>
				<select name="department.level.codeId" class="required combox">
					<option value="">请选择</option>
					<c:forEach var="item" items="${levels}">
						<option value="${item.codeId}">${item.remark}</option>
					</c:forEach>
				</select>
				
				<span style="clear: both; display: block; height: 10px;" />
				<label>上级部门：</label>
				<select name="department.code.parent.codeId" class="combox">
					<option value="">请选择</option>
					<c:forEach var="item" items="${parents}">
						<option value="${item.codeId}">${item.remark}</option>
					</c:forEach>
				</select>
				
				<span style="clear: both; display: block; height: 10px;" />
				<label>部门类型：</label>
				<select name="department.departCate.codeId" class="required combox">
					<option value="">请选择</option>
					<c:forEach var="item" items="${cates}">
						<option value="${item.codeId}">${item.remark}</option>
					</c:forEach>
				</select>
				
            </div>
			<div class="formBar">
				<ul>
					<li><div class="buttonActive"><div class="buttonContent"><button type="submit">提交</button></div></div></li>
					<li><div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div></li>
				</ul>
			</div>
       </form>
	</div>
</div>