<%@ page contentType="text/html; charset=utf-8" language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<div class="page">
	<div class="pageContent">
        <form method="post" action="${BaseURL}${model}/" class="pageForm required-validate" onsubmit="return validateCallback(this, ${openType}AjaxDone)">
            <div class="pageFormContent" layoutH="58">
            	<span style="clear: both; display: block; height: 10px;" />
				
				<label>账号：</label>
				<input id="account" class="required" name="user.account" type="text" minlength="4" maxlength="16" value="" />
				
				<span style="clear: both; display: block; height: 10px;" />
				
				<label>密码：</label>
				<input id="password" class="required" name="user.password" type="password" minlength="4" maxlength="32" value="" />
				
				<span style="clear: both; display: block; height: 10px;" />
				
                <label for="status">状态：</label>
                <select name="user.status" class="required">
                    <option selected="selected" value="正常">正常</option>
                    <option value="锁定">锁定</option>
                </select> 
                
                <span style="clear: both; display: block; height: 10px;" />
                
                <label for="user.status">不受权限控制：</label>
                <select name="user.superPower" class="required">
                    <option value="yes">是</option>
                    <option value="no">否</option>
                </select> 
                
                <dl class="nowrap">
                	<dt>帐号有效期：</dt>
					<dd>
						<input id="date" class="date textInput readonly valid focus" readonly="true" format="yyyy-MM-dd" name="user.availablePeriod" type="text" /><a class="inputDateButton" href="javascript:;">选择</a>
					</dd>
				</dl>
            
				<span style="clear: both; display: block; height: 10px;" />
				<label>用户姓名：</label>
				<input id="account" class="" name="user.trueName" type="text" />

				<span style="clear: both; display: block; height: 10px;" />
				<label>证件号码：</label>
				<input id="account" class="" name="user.idNum" type="text" />
				
				<span style="clear: both; display: block; height: 10px;" />
				<label>电子邮箱：</label>
				<input id="account" class="" name="user.email" type="text" />

				<span style="clear: both; display: block; height: 10px;" />
				<label>联系传真：</label>
				<input id="account" class="" name="user.fax" type="text"  />

				<span style="clear: both; display: block; height: 10px;" />
				<label>联系地址：</label>
				<input id="account" class="" name="user.addr" type="text" />

				<span style="clear: both; display: block; height: 10px;" />
				<label>办公电话：</label>
				<input id="account" class="" name="user.officePhone" type="text" />

				<span style="clear: both; display: block; height: 10px;" />
				<label>手机号码：</label>
				<input id="account" class="" name="user.mobileNum" type="text"/>

				<span style="clear: both; display: block; height: 10px;" />
				<label>家庭电话：</label>
				<input id="account" class="" name="user.homePhone" type="text"/>

				

				
             	
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