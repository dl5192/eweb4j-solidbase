<%@ page contentType="text/html; charset=utf-8" language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<div class="page">
	<div class="pageContent">
        <form method="post" action="${BaseURL}${editPage.action}" class="pageForm required-validate" onsubmit="return validateCallback(this, navTabAjaxDone)">
            <div class="pageFormContent" layoutH="58">
            	<input type="hidden" name="_method" value="put" />
            	<span style=" clear:both;display:block; height:10px; width:100%;"></span>
                <dl class="nowrap">
                	<dt>所属导航菜单：</dt>
					<dd>
						<input class="required"  type="hidden" name="navmenus.dwz_navMenu.navMenuId" value="${editPage.pojo.navMenu.navMenuId}" />
						<input class="required" 
						name="navmenus.dwz_navMenu.name" type="text" 
						value="${editPage.pojo.navMenu.name}"
						suggestFields="navMenuId,name" 
						suggestUrl="${BaseURL}navmenus/suggest.json" 
						clearId = "pid"
						cascadeSuggestId="parentSuggestInput"
						cascadeSuggestUrlFormatParam="navMenuId"
						cascadeLookupId="parentLookupA"
						cascadeLookupUrlFormatParam="navMenuId"
						lookupGroup="navmenus" lookupName="navMenu"/>
						<a class="btnLook" href="${BaseURL}navmenus/lookupSearch" 
						lookupGroup="navmenus" lookupName="navMenu">查找带回</a>
					</dd>
				</dl>
				<dl class="nowrap">
					<dt>父菜单：</dt>
					<dd>
						<input class="required"  id = "pid" type="hidden" name="${editPage.model}.dwz_treeMenu.treeMenuId" value="${editPage.pojo.parent.treeMenuId}" />
						<input class="required" 
						id = "parentSuggestInput"
						suggestUrlFormat = "${BaseURL}${editPage.model}/$s1/suggest.json?treeMenuId=${editPage.pojo.treeMenuId}"
						name="${editPage.model}.dwz_treeMenu.name" type="text" 
						value="${editPage.pojo.parent.name}"
						suggestFields="navMenuId,pid,treeMenuId,name" 
						suggestUrl="${BaseURL}${editPage.model}/${editPage.pojo.navMenu.navMenuId}/suggest.json?treeMenuId=${editPage.pojo.treeMenuId}" 
						lookupGroup="${editPage.model}" lookupName="treeMenu"/>
						<a id="parentLookupA" lookupUrlFormat = "${BaseURL}${editPage.model}/$s1/lookupSearch?treeMenuId=${editPage.pojo.treeMenuId}" class="btnLook" 
						href="${BaseURL}${editPage.model}/${editPage.pojo.navMenu.navMenuId}/lookupSearch?treeMenuId=${editPage.pojo.treeMenuId}" lookupGroup="${editPage.model}" lookupName="treeMenu">查找带回</a>
					</dd>
				</dl>
            	<span style=" clear:both;display:block; height:10px; width:100%;"></span>
                <input type="hidden" name="treeMenuId" value="${editPage.pojo.treeMenuId}" />
				<label>菜单名称：</label>
				<input id="name" class="required" name="treeMenu.name" type="text" maxlength="16" value="${editPage.pojo.name}" />
                <span style=" clear:both;display:block; height:10px; width:100%;"></span>
                <label>显示方式：</label>
                <select name="treeMenu.target" class="required" >
                	<option value="navTab"
                		<c:if test="${editPage.pojo.target == 'navTab'}">selected="selected"</c:if>
                	>选项卡页</option>
                	<option value="dialog"
                		<c:if test="${editPage.pojo.target == 'dialog'}">selected="selected"</c:if>
                	>对话框窗口</option>
                </select>
                <span style=" clear:both;display:block; height:10px; width:100%;"></span>
                <label>对话框宽度：</label>
                <input id="width" name="treeMenu.width" type="text" class=""  value="${editPage.pojo.width}" />
                <span style="color:red; padding-left:10px;">打开的对话框的宽度</span>
                <span style=" clear:both;display:block; height:10px; width:100%;"></span>
                <label>对话框高度：</label>
                <input id="height" name="treeMenu.height" type="text" class=""  value="${editPage.pojo.height}" />
                <span style="color:red; padding-left:10px;">打开的对话框的高度</span>
                <span style=" clear:both;display:block; height:10px; width:100%;"></span>
                <label>是否是外部页：</label>
                <input id="external" name="treeMenu.external" type="checkbox" ${editPage.pojo.external}/>
                <span style="color:red; padding-left:10px;">例如iframeTab </span>
                <span style=" clear:both;display:block; height:10px; width:100%;"></span>
                <label>标记代码：</label>
                <input id="rel" name="treeMenu.rel" type="text" value="${editPage.pojo.rel}" />
                <span style="color:red; padding-left:10px;">可以取所有文字的拼音头字母组成</span>
                <span style=" clear:both;display:block; height:10px; width:100%;"></span>
                <label>是否自动刷新：</label>
                <input id="reloadFlag" name="treeMenu.reloadFlag" type="checkbox" ${editPage.pojo.reloadFlag}/>
                <span style="color:red; padding-left:10px;">是否自动重新加载 </span>
                <span style=" clear:both;display:block; height:10px; width:100%;"></span>
                <label>链接：</label>
                <input id="href" name="treeMenu.href" type="text" value="${editPage.pojo.href}" />
                <span style="color:red; padding-left:10px;">点击菜单后跳转的路径</span>
                <span style=" clear:both;display:block; height:10px; width:100%;"></span>
                <label>排序：</label>
                <input id="rank" name="treeMenu.rank" class="required"  type="text" value="${editPage.pojo.rank}" />
                <span style="color:red; padding-left:10px;">同级菜单下，数字越小越靠前</span>
				<span style=" clear:both;display:block; height:10px; width:100%;"></span>
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