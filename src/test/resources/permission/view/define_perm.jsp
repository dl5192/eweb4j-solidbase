<%@ page contentType="text/html; charset=utf-8" language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<div class="page">
	<div class="pageContent">
        <form method="post" action="${BaseURL}${model}/" class="pageForm required-validate" onsubmit="return validateCallback(this, ${openType}AjaxDone)">
        	<div class="pageFormContent" layoutH="58">
        		<div class="unit">
                    <label for="permission.name">权限名称</label>
                    <input class="required" name="permission.name" type="text" value="" />
                </div>
                <div class="unit">
                    <label for="permission.resource.uri">URI资源：</label>
                    <input class="required"  type="hidden" name="resources.dwz_resource.resId" value="-1" />
					<input id="uriVal"
						name="resources.dwz_resource.uri" type="text" 
						suggestFields="resId,uri" 
						suggestUrl="resources/json"
						lookupGroup="resources" lookupName="resource"
						openType="${openType}"/>
					
                    <label>现有资源：</label>
					<select name="" class="" id="uriSel" onchange="onSelectUri();">
						<option value="" >请选择</option>
						<c:forEach var="action" items="${actions}" >
							<option value="${action.uriMapping}#${action.httpMethod}">${action.uriMapping}@${action.httpMethod}</option>
						</c:forEach>
					</select>
                </div>
				<script>
					function onSelectUri(){
						var params = $("#uriSel").val().split("#");
						$("#uriVal").val(params[0]);
						var methods = params[1].split("|");
						$("input.method").attr("checked","");
						$("input.method").each(function(){
								var method = $(this).attr("checkVal");
								for(i=0;i<methods.length;i++){
									if(method == methods[i]){
										$(this).attr("checked","checked");
									}
								}
							});

					}
				</script>
				<dl class="nowrap">
                	<dt>HTTP方法：</dt>
					<dd>
						<c:forEach var="httpMethod" items="${httpMethodList}">
							<label><input type="checkbox" name="httpMethods" class="method" value="${httpMethod.codeId}" checkVal="${httpMethod.codeValue}" />${httpMethod.remark}</label>
						</c:forEach>
					</dd>
				</dl>

				<div class="unit">
                    <label for="permission.type.codeId">权限分类：</label>
					<select name="permission.type.codeId" class="required combox">
						<option value="" >请选择</option>
						<c:forEach var="pojo" items="${permTypes}">
							<option value="${pojo.codeId}" >${pojo.remark}</option>
						</c:forEach>
					</select>
                </div>
                
                <div class="unit">
                	<label for="permission.remark">备注：</label>
                	<textarea name="permission.remark" rows="5" cols="50"></textarea>
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