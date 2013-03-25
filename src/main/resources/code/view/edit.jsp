<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<div class="page">
	<div class="pageContent">
        <form method="post" action="${BaseURL}${editPage.action}" class="pageForm required-validate" onsubmit="return validateCallback(this, ${openType}AjaxDone)">
        	<div class="pageFormContent" layoutH="58">
        		<dl class="nowrap">
                	<dt>代码类型：</dt>
					<dd>
						<input name="_method" value="PUT" type="hidden"/>
						<input name="code.type.codeId" type="hidden" value="${editPage.pojo.type.codeId}" readonly="readonly"/>
						<input name="code.type.remark" value="${editPage.pojo.type.remark}" readonly="readonly"/>
					</dd>
				</dl>
				<dl class="nowrap">
					<dt>父代码类型：</dt>
					<dd>
						<input type="hidden" id = "codeUpdateCodeParentSuggestInput_clear" name="codeParents.dwz_codeParent.codeId" value="${editPage.pojo.parent.codeId}" />
						<input
						id = "codeUpdateCodeParentSuggestInput"
						value="${editPage.pojo.parent.remark}"
						name="codeParents.dwz_codeParent.codeValue" type="text" 
						suggestFields="codeId,codeValue,remark" 
						suggestUrl="${BaseURL}${editPage.model}/${editPage.pojo.type.codeId}/parents.json" 
						lookupGroup="codeParents" lookupName="codeParent"
						openType="${openType}"/>
					</dd>
				</dl>
				<div class="unit">
                    <label>代码值：</label>
                    <input id="codeValue" class="required" name="code.codeValue" type="text"  maxlength="32" value="${editPage.pojo.codeValue}" />
                </div>
                <div class="unit">
                    <label>代码说明：</label>
                    <input id="remark" class="required" name="code.remark" type="text"  maxlength="50" value="${editPage.pojo.remark}" />
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