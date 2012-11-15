<%@ page contentType="text/html; charset=utf-8" language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=7" />
<meta name="author" content="cfuture" />
<title>${MainModel.title}</title>
<jsp:include page="css.jsp" />
<jsp:include page="javascript.jsp" />
<script type="text/javascript">
$(function(){
	DWZ.init("${BaseURL}dwzres/dwz.frag.xml", {
		//loginUrl:"../loginsub.jsp", loginTitle:"登录",	// 弹出登录对话框
		//loginUrl:"../login",	// 跳到登录页面
		statusCode:{ok:200, error:300, timeout:301}, //【可选】
		pageInfo:{pageNum:"pageNum", numPerPage:"numPerPage", orderField:"orderField", orderDirection:"orderDirection"}, //【可选】
		debug:false,	// 调试模式 【true|false】
		callback:function(){
			initEnv();
			$("#themeList").theme({themeBase:"themes"});
		}
	});
});
</script>
</head>

<body scroll="no">
	<div id="layout">
		<div id="header">
			<div class="headerNav"><a class="logo" href="http://www.lurencun.com">lurencun.com</a>
				<ul class="nav">
					<li><a href="javascript:;">欢迎 [<b>${loginUser.account}</b>]</a></li>
					<li><a href="${BaseURL}index.jsp" target="_blank">浏览首页</a></li>
					<li><a href="${BaseURL}users/profile" target="dialog" height="380">[我的资料 ]</a></li>
					<li><a href="${BaseURL}users/logout" onclick="var f = document.createElement('form');f.style.display = 'none'; this.parentNode.appendChild(f); f.method='POST'; f.action = this.href; var m = document.createElement('input');m.setAttribute('type','hidden');m.setAttribute('name','_method');m.setAttribute('value','put'); f.appendChild(m);f.submit();return false;">[退出 ]</a></li>
					<li><a href="${BaseURL}main/about-us" target="dialog">关于</a></li>
				</ul>
				<ul class="themeList" id="themeList">
					<li theme="default"><div class="selected">蓝色</div></li>
					<li theme="green"><div>绿色</div></li>
					<!--<li theme="red"><div>红色</div></li>-->
					<li theme="purple"><div>紫色</div></li>
					<li theme="silver"><div>银色</div></li>
					<li theme="azure"><div>天蓝</div></li>
				</ul>
			</div>

			<div id="navMenu">
				<ul>
					<c:forEach var="navMenu" items="${MainModel.navMenus}">
					<li><a href="${BaseURL}${navMenu.href}"><span>${navMenu.name}</span></a></li>
					</c:forEach>
				</ul>
			</div>
		</div>

		<div id="leftside">
			<div id="sidebar_s">
				<div class="collapse">
					<div class="toggleCollapse">
						<div></div>
					</div>
				</div>
			</div>
			<div id="sidebar">
				<div class="toggleCollapse">
					<h2>控制板</h2>
					<div>收缩</div>
				</div>
				<div class="accordion" fillSpace="sideBar">
					${MainModel.treeMenus}
				</div>
				
			</div>
		</div>
		<div id="container">
			<div id="navTab" class="tabsPage">
				<div class="tabsPageHeader">
					<div class="tabsPageHeaderContent"><!-- 显示左右控制时添加 class="tabsPageHeaderMargin" -->
						<ul class="navTab-tab">
							<li tabid="main" class="main"><a href="javascript:;"><span><span class="home_icon">桌面</span></span></a></li>
						</ul>
					</div>
					<div class="tabsLeft">left</div><!-- 禁用只需要添加一个样式 class="tabsLeft tabsLeftDisabled" -->
					<div class="tabsRight">right</div><!-- 禁用只需要添加一个样式 class="tabsRight tabsRightDisabled" -->
					<div class="tabsMore">more</div>
				</div>
				<ul class="tabsMoreList">
					<li><a href="javascript:;">桌面</a></li>
				</ul>
				<div class="navTab-panel tabsPageContent">
					<div class="page">
                    	<div class="pageContent">
                    		<div style=" overflow:auto;width:100%;height:100%; background:#ccc;"></div>
                        </div>
					</div>
				</div>
			</div>
		</div>

		<div id="taskbar" style="left:0px; display:none;">
			<div class="taskbarContent">
				<ul></ul>
			</div>
			<div class="taskbarLeft taskbarLeftDisabled" style="display:none;">taskbarLeft</div>
			<div class="taskbarRight" style="display:none;">taskbarRight</div>
		</div>
		<div id="splitBar"></div>
		<div id="splitBarProxy"></div>
	</div>

	<div id="footer">${MainModel.copyRight}</div>

	<!--拖动效果-->
	<div class="resizable"></div>
	<!--阴影-->
	<div class="shadow" style="width:508px; top:148px; left:296px;">
		<div class="shadow_h">
			<div class="shadow_h_l"></div>
			<div class="shadow_h_r"></div>
			<div class="shadow_h_c"></div>
		</div>
		<div class="shadow_c">
			<div class="shadow_c_l" style="height:296px;"></div>
			<div class="shadow_c_r" style="height:296px;"></div>
			<div class="shadow_c_c" style="height:296px;"></div>
		</div>
		<div class="shadow_f">
			<div class="shadow_f_l"></div>
			<div class="shadow_f_r"></div>
			<div class="shadow_f_c"></div>
		</div>
	</div>
	<!--遮盖屏幕-->
	<div id="alertBackground" class="alertBackground"></div>
	<div id="dialogBackground" class="dialogBackground"></div>

	<div id='background' class='background'></div>
	<div id='progressBar' class='progressBar'>数据加载中，请稍等...</div>
</body>
</html>