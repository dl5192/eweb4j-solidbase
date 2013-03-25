<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<div class="page">
	<div class="pageContent">
        <form method="post" action="${BaseURL}${model}/" class="pageForm required-validate" onsubmit="return validateCallback(this, ${openType}AjaxDone)">
        	<div class="pageFormContent" layoutH="58">
        		<dl class="nowrap">
                	<dt>代码类型：</dt>
					<dd>
						<input class="required"  type="hidden" name="codeTypes.dwz_codeType.codeId" value="0" />
						<input  class="required"
							name="codeTypes.dwz_codeType.codeValue" type="text" 
							suggestFields="codeId,codeValue,remark" 
							suggestUrl="${BaseURL}${model}/types.json"
							clearId = "codeAddCodeParentSuggestInput_clear" 
							cascadeSuggestId="codeAddCodeParentSuggestInput"
							cascadeSuggestUrlFormatParam="codeId"
							lookupGroup="codeTypes" lookupName="codeType"
							openType="${openType}"/>
					</dd>
				</dl>
				<dl class="nowrap">
					<dt>父代码类型：</dt>
					<dd>
						<input type="hidden" id = "codeAddCodeParentSuggestInput_clear" name="codeParents.dwz_codeParent.codeId" value="0" />
						<input
						id = "codeAddCodeParentSuggestInput"
						suggestUrlFormat = "${BaseURL}${model}/$s1/parents.json"
						name="codeParents.dwz_codeParent.codeValue" type="text" 
						suggestFields="codeId,codeValue,remark" 
						suggestUrl="#" 
						lookupGroup="codeParents" lookupName="codeParent"
						openType="${openType}"/>
					</dd>
				</dl>
				<div class="unit">
                    <label>代码值：</label>
                    <input id="codeName" class="required" name="code.codeValue" type="text"  maxlength="32" value="" />
                </div>
                <div class="unit">
                    <label>代码说明：</label>
                    <input id="remark" class="required" name="code.remark" type="text"  maxlength="50" value="" />
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