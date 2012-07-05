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
		style="float: left; display: block; overflow: auto; width: 55%; height:50%;">
		<div class="pageContent" layoutH="360"
			style="border-left: 1px #B8D0D6 solid; border-right: 1px #B8D0D6 solid">
			<table class="list" width="100%" >
				<thead>
					<tr>
						<th width="30"><input type="checkbox" group="userIds" class="checkboxCtrl"></th>
						<th width="100">用户账号</th>
						<th width="100">用户姓名</th>
						<th width="">角色</th>
					</tr>
				</thead>
				<tbody>
				<c:forEach var="pojo" items="${users}">
					<tr target="pojo_id" rel="${pojo.id}">
						<td width="30"><center><input id="${random}${pojo.id}" name="userIds" value="${pojo.id}" type="checkbox"></center></td>
						<td onclick="selectBox('${random}${pojo.id}')">
							<a style="color:blue; text-decoration:underline" href="users/${pojo.id}/departments" target="ajax" rel="department_detail">${pojo.account}</a>
						</td>
						<td onclick="selectBox('${random}${pojo.id}')">
							${pojo.trueName}
						</td>
						<td onclick="selectBox('${random}${pojo.id}')">
							<c:forEach var="pojo" items="${pojo.roles}" >
								${pojo.name}, 
							</c:forEach>
						</td>
					</tr>
				</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
	
	<div id="jbsxBox3" class="unitBox" style="margin-left: 55%; height:50%;">
		<div class="pageContent" style="border: 1px #B8D0D6 solid;" layoutH="360">
			<ul class="tree treeFolder treeCheck expand">
			    	<li><a>全部部门</a>
			    		${departmentTree}
			    	</li>
			    </ul>
		</div>
	</div>
	
	</form>
</div>

<script type="text/javascript">
	function interceptOnClick(id){
		var url = $("#department_item_detail_"+id).attr("link");
		alertMsg.confirm("您确定要执行删除操作吗？", {
            okCall: function(){
            	$.post(url, {"item":id}, function(json){
					if(json.status){
						alertMsg.correct(json.message)
						$("#department_item_"+id).hide();
					}else{
						alertMsg.error(json.message);
					}
            	}, "json");
        }
});



	}
</script>

<div class="pageContent" style="height:300px; overflow:auto;border: 1px #B8D0D6 solid;"
	id="department_detail">
</div>


