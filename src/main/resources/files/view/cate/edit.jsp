<%@ page contentType="text/html; charset=utf-8" language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<div class="page">
	<div class="pageContent">
        <form method="post" action="${BaseURL}file_cate/${pojo.id}" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone)">
            <div class="pageFormContent" layoutH="58">
				<input type="hidden" name="_method" value="put" />
				<div class="unit">
					<label>类别名称：</label>
					<input class="required " name="fileCate.name" value="${pojo.name}" />
				</div>
				<div class="unit">
					<label>缩略图：</label>
					<input class="required number" name="fileCate.thumb.id" value="${pojo.thumb.id}" />
				</div>
				
				<div class="unit">
                    <label>排序</label>
                    <input class="required" name="fileCate.sort" value="${pojo.sort}" />
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