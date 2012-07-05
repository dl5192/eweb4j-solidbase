<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<div class="pageContent" layoutH="300">
	<form method="post" action="${BaseURL}${action}" class="pageForm required-validate" onsubmit="return validateCallback(this, ${openType}AjaxDone);">
	<div class="panelBar" style="padding:5px">
		<ul class="toolBar" >
			<li><input style="width:100px; height:23px;" type="submit" value="确认提交" href="#"
				target="dialog" mask="true"></input></li>
		</ul>
	</div>
	<div id="jbsxBox2" class="unitBox"
		style="float: left; display: block; overflow: auto; width: 30%; height:50%;">
		<div class="pageContent" layoutH="360"
			style="border-left: 1px #B8D0D6 solid; border-right: 1px #B8D0D6 solid">
			<table class="list" width="98%" >
				<thead>
					<tr>
						<th width="30"><input type="checkbox" group="roleIds" class="checkboxCtrl"></th>
						<th width="">角色名称</th>
						<!--th>描述</th-->
					</tr>
				</thead>
				<tbody>
				<c:forEach var="pojo" items="${roles}">
					<tr target="pojo_id" rel="${pojo.roleId}">
						<td width="30"><center><input id="${random}${pojo.roleId}" name="roleIds" value="${pojo.roleId}" type="checkbox"></center></td>
						<td onclick="selectBox('${random}${pojo.roleId}')">
							<a style="color:blue; text-decoration:underline" href="${BaseURL}roles/${pojo.roleId}/menus" target="ajax" rel="menu_detail">${pojo.name}</a>
						</td>
						<!--td onclick="selectBox('${random}${pojo.roleId}')">${pojo.description}</td-->
					</tr>
				</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
	
	<div id="jbsxBox3" class="unitBox" style="margin-left: 30%; height:50%;">
		<div class="pageContent" style="border: 1px #B8D0D6 solid;" layoutH="360">
			${menus}
		</div>
	</div>
	
	</form>
</div>

<script type="text/javascript">
	function interceptOnClick(id){
		var url = $("#menu_item_detail_"+id).attr("link");
		alertMsg.confirm("您确定要执行删除操作吗？", {
            okCall: function(){
            	$.post(url, {"item":id}, function(json){
					if(json.status){
						alertMsg.correct(json.message)
						$("#menu_item_"+id).hide();
					}else{
						alertMsg.error(json.message);
					}
            	}, "json");
        	}
		});
	}
	
	function interceptOnClickForNav(id){
		var url = $("#nav_item_detail_"+id).attr("link");
		alertMsg.confirm("您确定要执行删除操作吗？", {
            okCall: function(){
            	$.post(url, {"item":id}, function(json){
					if(json.status){
						alertMsg.correct(json.message)
						$("#nav_item_"+id).hide();
					}else{
						alertMsg.error(json.message);
					}
            	}, "json");
        	}
		});
	}
</script>

<div class="pageContent" style="height:300px; overflow:auto;border: 1px #B8D0D6 solid;"
	id="menu_detail">
</div>


