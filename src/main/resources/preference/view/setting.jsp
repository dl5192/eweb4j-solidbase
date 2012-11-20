<%@ page contentType="text/html; charset=utf-8" language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<div class="page">
	<div class="pageContent">
        <form method="post" action="${BaseURL}settings" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone)">
            <input type="hidden" name="_method" value="put" />
            <div class="pageFormContent" layoutH="58">
                <input type="hidden" name="id" value="${pojo.id}" />
				
				<span style="clear: both; display: block; height: 10px;" />
                
                <label for="setting.userDefaultRole.roleId">默认用户角色：</label>
                <select name="setting.userDefaultRole.roleId" class="combox">
					<option value="">请选择</option>
					<c:forEach var="item" items="${roles}">
						<option value="${item.roleId}"
							<c:if test="${item.roleId == pojo.userDefaultRole.roleId}">
								selected="selected"
							</c:if>
						>${item.name}</option>
					</c:forEach>
				</select>
				
                <span style="clear: both; display: block; height: 10px;" />
                
                <label for="setting.userPermControl">权限控制：</label>
                <select name="setting.userPermControl" class="required combox">
                	<option value="">请选择</option>
                    <option value="yes" 
                    	<c:if test="${pojo.userPermControl=='yes'}">selected="selected"</c:if>
                    >关闭</option>
                    <option value="no" 
                    	<c:if test="${pojo.userPermControl=='no'}">selected="selected"</c:if>
                    >开启</option>
                </select> 
                
            </div>
            
            <div class="formBar">
                <ul>
                    <li>
                        <div class="buttonActive">
                            <div class="buttonContent">
                                <button type="submit">提交</button>
                            </div>
                        </div>
                    </li>
                    <li>
                        <div class="button">
                            <div class="buttonContent">
                                <button type="button" class="close">取消</button>
                            </div>
                        </div>
                    </li>
                </ul>
            </div>
        </form>
	</div>
</div>