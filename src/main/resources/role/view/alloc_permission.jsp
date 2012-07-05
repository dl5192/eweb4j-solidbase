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
		style="float: left; display: block;height:50%; overflow: auto; width: 30%;">
		<div class="pageContent"
			style="height:50%;border-left: 1px #B8D0D6 solid; border-right: 1px #B8D0D6 solid">
			<table class="list" width="98%" layoutH="360" >
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
							<a style="color:blue; text-decoration:underline" href="${BaseURL}roles/${pojo.roleId}/permissions" target="ajax" rel="permission_detail" reloadFlag="1">${pojo.name}</a>
						</td>
						<!--td onclick="selectBox('${random}${pojo.roleId}')">${pojo.description}</td-->
					</tr>
				</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
	
	<div id="jbsxBox3" class="unitBox" style="margin-left: 30%; height:50%;">
		<div class="pageContent" style="border: 1px #B8D0D6 solid; height:50%" layoutH="360">
			<ul class="expand tree treeFolder treeCheck">
				<li><a>全部权限</a>
					
						<ul>
					    	<c:forEach var="perm" items="${permissions}">
								<li><a>${perm.permType.remark}</a>
									<ul>
										<c:forEach var="pojo" items="${perm.permissions}" >
											<li><a tname="permissionIds" tvalue="${pojo.permId}" >${pojo.name}</a></li>
										</c:forEach>
									</ul>
								</li>
							</c:forEach>
						</ul>
						
				</li>
			</ul>
		</div>
	</div>
	
	</form>
</div>

<script type="text/javascript">
	function interceptOnClick(id){
		var url = $("#permission_item_detail_"+id).attr("link");
		alertMsg.confirm("您确定要执行删除操作吗？", {
            okCall: function(){
            	$.post(url, {"item":id}, function(json){
					if(json.status){
						alertMsg.correct(json.message)
						$("#permission_item_"+id).hide();
					}else{
						alertMsg.error(json.message);
					}
            	}, "json");
        	}
		});
	}
</script>

<div class="pageContent" style="height:300px;overflow:auto !important;border: 1px #B8D0D6 solid;"
	id="permission_detail" >
</div>


