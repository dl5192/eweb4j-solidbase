<%@ page contentType="text/html; charset=utf-8" language="java"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<div class="page">
	<div class="pageContent">
        <form method="post" action="${BaseURL}${editPage.action}" class="pageForm required-validate" onsubmit="return validateCallback(this, ${openType}AjaxDone)">
            <input type="hidden" name="_method" value="put" />
            <div class="pageFormContent" layoutH="58">
                <input type="hidden" name="id" value="${editPage.pojo.id}" />
				
				<span style="clear: both; display: block; height: 10px;" />
                
                <label for="user.account">账号：</label>
                <input id="account" class="required" name="user.account" type="text" minlength="4" maxlength="16" value="${editPage.pojo.account}" />
                
                <span style="clear: both; display: block; height: 10px;" />
                
                <label for="user.password">密码：</label> 
                <input id="password" class="required" name="user.password" type="password" minlength="4" maxlength="32" value="${editPage.pojo.password}" />
                
                <span style="clear: both; display: block; height: 10px;" />
                
                <label for="user.rePwd">重复密码：</label> 
                <input id="repwd" class="required" name="user.rePwd" type="password" minlength="4" maxlength="32" value="${editPage.pojo.password}" />
                
                <span style="clear: both; display: block; height: 10px;" />
                
                <label for="user.status">状态：</label>
                <select name="user.status" class="required">
                    <option value="正常" 
                    	<c:if test="${editPage.pojo.status=='正常'}">selected="selected"</c:if>
                    >正常</option>
                    <option value="锁定"
                    	<c:if test="${editPage.pojo.status=='锁定'}">selected="selected"</c:if>
                    >锁定</option>
                </select> 
                
                <span style="clear: both; display: block; height: 10px;" />
                
                <label for="user.status">不受权限控制：</label>
                <select name="user.superPower" class="required">
                    <option value="yes" 
                    	<c:if test="${editPage.pojo.superPower=='yes'}">selected="selected"</c:if>
                    >是</option>
                    <option value="no"
                    	<c:if test="${editPage.pojo.superPower=='no'}">selected="selected"</c:if>
                    >否</option>
                </select> 
                
                <dl class="nowrap">
                	<dt>帐号有效期：</dt>
					<dd>
						<input id="account" class="date textInput readonly valid focus" name="user.availablePeriod" type="text" readonly="true" value="${editPage.pojo.availablePeriod}" /><a class="inputDateButton" href="javascript:;">选择</a>
					</dd>
				</dl>
				
                <span style="clear: both; display: block; height: 10px;" />
				
				<label>用户姓名：</label>
				<input id="account" class="" name="user.trueName" type="text" value="${editPage.pojo.trueName}" />

				<span style="clear: both; display: block; height: 10px;" />
				
				<label>证件号码：</label>
				<input id="account" class="" name="user.idNum" type="text" value="${editPage.pojo.idNum}"/>
				
				<span style="clear: both; display: block; height: 10px;" />
				
				<label>电子邮箱：</label>
				<input id="account" class="" name="user.email" type="text" value="${editPage.pojo.email}" />

				<span style="clear: both; display: block; height: 10px;" />
				
				<label>联系传真：</label>
				<input id="account" class="" name="user.fax" type="text" value="${editPage.pojo.fax}" />
			
				<span style="clear: both; display: block; height: 10px;" />
				
				<label>联系地址：</label>
				<input id="account" class="" name="user.addr" type="text" value="${editPage.pojo.addr}" />

				<span style="clear: both; display: block; height: 10px;" />
				
				<label>办公电话：</label>
				<input id="account" class="" name="user.officePhone" type="text" value="${editPage.pojo.officePhone}"/>

				<span style="clear: both; display: block; height: 10px;" />
				
				<label>手机号码：</label>
				<input id="account" class="" name="user.mobileNum" type="text" value="${editPage.pojo.mobileNum}" />

				<span style="clear: both; display: block; height: 10px;" />
				
				<label>家庭电话：</label>
				<input id="account" class="" name="user.homePhone" type="text" value="${editPage.pojo.homePhone}" />

                <span style="clear: both; display: block; height: 10px;" />
               
                <label for="___lastLoginTime">最后登陆时间：</label>
                <input disabled="disabled" name="___lastLoginTime" type="text" value="${editPage.pojo.lastLoginTime}" /> 
                
                <span style="clear: both; display: block; height: 10px;" />
                
                <label for="___lastLoginIp">最后登陆IP：</label> 
                <input disabled="disabled" name="___lastLoginIp" type="text" value="${editPage.pojo.lastLoginIp}" /> 
                
                <span style="clear: both; display: block; height: 10px;" />
                
                <label for="___regTime">注册时间：</label>
                <input disabled="disabled" name="___regTime" type="text" value="${editPage.pojo.regTime}" /> 
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