<%@ page contentType="text/html; charset=utf-8" language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<div class="page">
	<div class="pageContent">
        <form method="post" action="${BaseURL}files/${pojo.id}" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone)">
            <div class="pageFormContent" layoutH="58">
				<input type="hidden" name="_method" value="put" />
				<div class="unit">
					<label>文件类别：</label>
					<select name="files.cate.id" class="required combox">
						<option value="">请选择</option>
						<c:forEach var="item" items="${cates}">
							<option value="${item.id}" <c:if test="${item.id == pojo.cate.id}">selected="selected"</c:if>>${item.name}</option>
						</c:forEach>
					</select>
				</div>
                <div class="unit">
					<label>保存路径：</label>
					<input value="${pojo.savePath}" size="58" class="required" name="files.savePath" disabled="disabled" />
				</div>
				
				<div class="unit">
					<label>文件大小：</label>
					<input value="${pojo.size}" class="required" name="files.size" disabled="disabled" />
				</div>
				
				<div class="unit">
					<label>文件名称：</label>
					<input value="${pojo.displayName}" size="58" class="required" name="files.displayName" />
				</div>
				
				<div class="unit">
                    <label>文件描述</label>
                    <textarea name="files.intro" rows="3" cols="50">${pojo.intro}</textarea>
                </div>
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