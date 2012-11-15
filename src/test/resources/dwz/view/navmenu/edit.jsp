<%@ page contentType="text/html; charset=utf-8" language="java" pageEncoding="utf-8"%>
<div class="page">
	<div class="pageContent">
        <form method="post" action="${BaseURL}${editPage.action}" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone)">
			<input type="hidden" name="_method" value="put" />
        	<div class="pageFormContent" layoutH="58">
        		<div class="unit">
        			<label>ID</label>
        			<input disabled="disabled" type="text" value="${editPage.pojo.navMenuId}" />
        		</div>
        		<div class="unit">
                    <label>菜单名</label>
                    <input id="name" class="required" name="navMenu.name" type="text" style="width:200px;" minlength="2" maxlength="32" value="${editPage.pojo.name}" />
                </div>
                <div class="unit">
                    <label>跳转路径</label>
                    <input id="href" class="required" name="navMenu.href" type="text" style="width:200px;" minlength="2" maxlength="255" value="${editPage.pojo.href}" />
                </div>
				<div class="unit">
                    <label>排序</label>
                    <input id="rank" class="required" name="navMenu.rank" type="text" style="width:100px;" maxlength="32" value="${editPage.pojo.rank}" />
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