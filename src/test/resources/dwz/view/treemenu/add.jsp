<%@ page contentType="text/html; charset=utf-8" language="java" pageEncoding="utf-8"%>
<div class="page">
	<div class="pageContent">
        <form method="post" action="${BaseURL}${model}/" class="pageForm required-validate" onsubmit="return validateCallback(this, navTabAjaxDone)">
            <div class="pageFormContent" layoutH="58">
            	<dl class="nowrap">
                	<dt>所属导航菜单：</dt>
					<dd>
						<input class="required"  type="hidden" name="navmenus.dwz_navMenu2.navMenuId" value="" />
						<input class="required" 
						name="navmenus.dwz_navMenu2.name" type="text" 
						value="请选择"
						suggestFields="navMenuId,name" 
						suggestUrl="${BaseURL}navmenus/suggest.json"
						clearId = "pid2" 
						cascadeSuggestId="parentSuggestInput2"
						cascadeSuggestUrlFormatParam="navMenuId"
						cascadeLookupId="parentLookupA2"
						cascadeLookupUrlFormatParam="navMenuId"
						lookupGroup="navmenus" lookupName="navMenu2"/>
						<a class="btnLook" href="${BaseURL}navmenus/lookupSearch" 
						lookupGroup="navmenus" lookupName="navMenu2">查找带回</a>
					</dd>
				</dl>
				<dl class="nowrap">
					<dt>父菜单：</dt>
					<dd>
						<input class="required"  id = "pid2" type="hidden" name="${model}.dwz_treeMenu2.treeMenuId" value="0" />
						<input class="required" 
						id = "parentSuggestInput2"
						suggestUrlFormat = "${BaseURL}${model}/$s1/suggest.json?treeMenuId=0"
						name="${model}.dwz_treeMenu2.name" type="text" 
						value="请选择"
						suggestFields="navMenuId,pid,treeMenuId,name" 
						suggestUrl="#" 
						lookupGroup="${model}" lookupName="treeMenu2"/>
						<a id="parentLookupA2" lookupUrlFormat = "${BaseURL}${model}/$s1/lookupSearch" class="btnLook" 
						href="#" lookupGroup="${model}" lookupName="treeMenu2">查找带回</a>
					</dd>
				</dl>
				<span style=" clear:both;display:block; height:10px; width:100%;"></span>
				<label>菜单名称：</label>
				<input id="name" class="required" name="treeMenu.name" type="text" maxlength="16" value="" />
                <span style=" clear:both;display:block; height:10px; width:100%;"></span>
                <label>显示方式：</label>
                <select name="treeMenu.target" class="required" >
                	<option selected="selected" value="navTab">选项卡页</option>
                	<option value="dialog">对话框窗口</option>
                </select>
                <span style=" clear:both;display:block; height:10px; width:100%;"></span>
                <label>对话框宽度：</label>
                <input id="width" name="treeMenu.width" type="text" class=""  value="" />
                <span style="color:red; padding-left:10px;">打开的对话框的宽度</span>
                <span style=" clear:both;display:block; height:10px; width:100%;"></span>
                <label>对话框高度：</label>
                <input id="height" name="treeMenu.height" type="text" class=""  value="" />
                <span style="color:red; padding-left:10px;">打开的对话框的高度</span>
                <span style=" clear:both;display:block; height:10px; width:100%;"></span>
                <label>是否是外部页：</label>
                <input id="external" name="treeMenu.external" type="checkbox" />
                <span style="color:red; padding-left:10px;">例如iframeTab </span>
                <span style=" clear:both;display:block; height:10px; width:100%;"></span>
                <label>标记代码：</label>
                <input id="rel" name="treeMenu.rel" type="text" value="" />
                <span style="color:red; padding-left:10px;">可以取所有文字的拼音头字母组成</span>
                <span style=" clear:both;display:block; height:10px; width:100%;"></span>
                <label>是否自动刷新：</label>
                <input id="reload" name="treeMenu.reload" type="checkbox" />
                <span style="color:red; padding-left:10px;">是否自动重新加载 </span>
                <span style=" clear:both;display:block; height:10px; width:100%;"></span>
                <label>链接：</label>
                <input id="href" name="treeMenu.href" type="text" value=""  />
                <span style="color:red; padding-left:10px;">点击菜单后跳转的路径</span>
                <span style=" clear:both;display:block; height:10px; width:100%;"></span>
                <label>排序：</label>
                <input id="rank" name="treeMenu.rank" type="text" class="required"  value="0" />
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