<%@ page contentType="text/html; charset=utf-8" language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<div class="page">
	<div class="pageContent">
       	<form style="padding:10px;" target="hidden_frame" class="pageForm required-validate" method="post" id="upload_form" action="${BaseURL}files/upload" enctype="multipart/form-data" >
		    <div class="unit">
				<label>上传文件：</label>
		    	<input style="padding-left:60px;" name="uploadFile" type="file" onChange="document.getElementById('upload_form').submit()"/>
		    </div>
		</form>
        <iframe name="hidden_frame" id="hidden_frame" style='display:none'></iframe>
        <script>
			function upload_callback(flag, data){
				if (flag) {
					if (data.id) {
						$('#id_wrap').css('display','block');
						$('#id').val(data.id);
					}
					if(data.displayName){
						$('#display_name').val(data.displayName);
					}
					if (data.savePath){
						$('#save_path_wrap').css('display','block');
						$('#save_path').val(data.savePath);
					}
					if (data.size){
						$('#size_wrap').css('display','block');
						$('#size').val(data.size);
					}
				}else
					alert('上传失败，原因->' + data);
			}
		</script>
        <form method="post" action="${BaseURL}files/" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone)">
            <div class="pageFormContent" layoutH="104">
				<div class="unit">
					<label>文件类别：</label>
					<select name="files.cate.id" class="combox">
						<option value="">请选择</option>
						<c:forEach var="item" items="${cates}">
							<option value="${item.id}">${item.name}</option>
						</c:forEach>
					</select>
				</div>
				
				<div id="id_wrap" class="unit" style="display:none;">
					<label>资源ID：</label>
					<input type="hidden" id="id" class="required" name="files.id" />
				</div>
				
                <div id="save_path_wrap" class="unit" style="display:none;">
					<label>保存路径：</label>
					<input id="save_path" size="58" class="required" name="files.savePath" disabled="disabled" />
				</div>
				
				<div id="size_wrap" class="unit" style="display:none;">
					<label>文件大小：</label>
					<input id="size" class="required" name="files.size" disabled="disabled" />
				</div>
				
				<div id="display_name_wrap" class="unit">
					<label>文件名称：</label>
					<input id="display_name" size="58" class="required" name="files.displayName" />
				</div>
				
				<div id="intro_wrap" class="unit">
                    <label>文件描述</label>
                    <textarea name="files.intro" rows="3" cols="50"></textarea>
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