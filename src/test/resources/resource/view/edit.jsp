<%@ page contentType="text/html; charset=utf-8" language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<div class="page">
	<div class="pageContent">
        <form method="post" action="${BaseURL}${editPage.action}/" class="pageForm required-validate" onsubmit="return validateCallback(this, ${openType}AjaxDone)">
            <div class="pageFormContent" layoutH="58">
				<input type="hidden" name="_method" value="put" />
				<input type="hidden" name="resource.resId" value="${editPage.pojo.resId}" />
				<span style="clear: both; display: block; height: 10px;" />
				<label>资源标识符URI：</label>
				<input class="required " name="resource.uri" type="text" value="${editPage.pojo.uri}" />

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