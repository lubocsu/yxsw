﻿<%@page import="com.upsoft.systemweb.controller.IndexController"%>
<%@page import="com.upsoft.login.controller.LoginController"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="path" value="${pageContext.request.contextPath}"></c:set>
<c:set var="LOGIN_PREFIX" value="<%=LoginController.FORWARD_PREFIX%>"></c:set> 
<c:set var="INDEX_PREFIX" value="<%=IndexController.FORWARD_PREFIX%>"></c:set> 
<%
if(session.getAttribute("skinName")==null){
	session.setAttribute("skinName","skin");
	session.setAttribute("themeColor","blue");
}
 %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<meta charset="UTF-8">
<title>智慧水务后台管理系统</title>
<!--框架必需start-->
<link href="${path}/libs/css/import_basic.css" rel="stylesheet" type="text/css"/>
<link href="${path}/libs/skins/<%=session.getAttribute("themeColor")%>/style.css" rel="stylesheet" type="text/css" id="theme"  themeColor="<%=session.getAttribute("themeColor")%>" positionTarget="positionContent"/>
<link href="${path}/system/layout/<%=session.getAttribute("skinName")%>/style.css" rel="stylesheet" type="text/css" id="skin"  skinPath="system/layout/<%=session.getAttribute("skinName")%>/"/>
<script type="text/javascript" src="${path}/libs/js/jquery.js"></script>
<script type="text/javascript" src="${path}/libs/js/language/cn.js"></script>
<script type="text/javascript" src="${path}/libs/js/main.js"></script>
<!--框架必需end-->

<!--引入弹窗组件start-->
<script type="text/javascript" src="${path}/libs/js/popup/drag.js"></script>
<script type="text/javascript" src="${path}/libs/js/popup/dialog.js"></script>
<!--引入弹窗组件end-->

<!--分隔条start-->
<script type="text/javascript" src="${path}/libs/js/nav/spliter.js"></script>
<!--分隔条end-->
<script type="text/javascript" src="${path}/system/login/logout.js"></script>
<script>
function bookmarksite(title, url){
    if (window.sidebar){ // firefox
        window.sidebar.addPanel(title, url, "");
    } else if (window.opera && window.print) { // opera
        var elem = document.createElement('a');
        elem.setAttribute('href', url);
        elem.setAttribute('title', title);
        elem.setAttribute('rel', 'sidebar');
        elem.click();
    }else if(document.all){
        window.external.AddFavorite(url, title);
    }// ie
}
function exitHandler(){
	top.Dialog.confirm("确定要退出系统吗",function(){});
}
function lockScreen(){
	top.Dialog.confirm("确定要锁屏吗，锁屏后只有重新输入密码才能解除。",function(){
		openLockWindow();
	});
}
function openLockWindow(){
	var diag = new top.Dialog();
		diag.Title = "系统锁屏";
		diag.ID = "a1";
		diag.URL="${path}/sample/fullform/lock.jsp";
		diag.Width=350;
		diag.Height=150;
		diag.ShowCloseButton=false;
		diag.ShowCancelButton=false;
		diag.ShowOkButton=false;
		diag.ButtonAlign="center";
		diag.AllowChangeIndex=false;
		diag.show();
		diag.addButton("btn1","注销系统",function(){
			top.Dialog.confirm("确定要退出系统吗",function(){logout();});
		});
		diag.addButton("btn2","确定解锁",function(){
			top.document.getElementById("_DialogFrame_a1").contentWindow.validateForm();
		});
		$.post("${path}/userAction.do?method=lockScreen");
}

function validate(){
	var isLockScreen = "${isLockScreen}";
	if(null!=isLockScreen&&isLockScreen.length>0){
		openLockWindow();
	}
}

function closeTip(){
	jQuery.jCookie('closeTip',"sure");
	$("#lbox").hideTip();
}

jQuery(function(){
	// 防止浏览器后退 2017.10.21 by huyi
	history.pushState(null, null, document.URL);
    window.addEventListener('popstate', function () {
        history.pushState(null, null, document.URL);
    });
	
	jQuery.ajax({
		type : "post",
		url : "${path}/${INDEX_PREFIX}/getFriendlyInfo",
		success : function(data) {
		    var currenUserInfo = "当前用户："+data.userName+"　【"+data.orgName+"】";
		    jQuery("#bs_navright .bs_nav .padding_left5").html(currenUserInfo);
		}
	});
});

</script>
</head>
<body onload="validate()">
<div id="mainFrame">
	<!--头部与导航start-->
	<div id="hbox">
		<div id="bs_bannercenter">
			<div id="bs_bannerright">
				<div id="bs_bannerleft"></div>
			</div>
		</div>
		<div id="bs_navcenter">
			<div id="bs_navleft">
				<div id="bs_navright">
					<div class="bs_nav">
						
						<div class="float_left padding_top2 padding_left5">
							【当前用户
							<script>
								/* var weekDayLabels = new Array("星期日","星期一","星期二","星期三","星期四","星期五","星期六");
								var now = new Date();
							    var year=now.getFullYear();
								var month=now.getMonth()+1;
								var day=now.getDate();
							    var currentime = year+"年"+month+"月"+day+"日 "+weekDayLabels[now.getDay()]; */
							</script>】
						</div>	
						<div class="float_left" style="padding:2px 0 0 20px;" id="positionContent"></div>	
						<div class="float_right padding_top2 padding_right5">
							<span class="icon_home hand " id="index" hideNav="true" >首页</span>
							<span class="icon_fullscreen hand" id="fullSrceen" hideNav="true">开启全屏</span>
							<span class="icon_exit hand" onclick='top.Dialog.confirm("确定要退出系统吗",function(){logout();});'>退出系统</span>
						</div>
						<div class="clear"></div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!--头部与导航end-->
	<table width="100%" cellpadding="0" cellspacing="0" class="table_border0">
		<tr>
			<!--左侧区域start-->
			<td id="hideCon" class="ver01 ali01">
				<div id="lbox">
					<div id="lbox_topcenter">
						<div id="lbox_topleft">
							<div id="lbox_topright"></div>
						</div>
					</div>
					<div id="lbox_middlecenter">
						<div id="lbox_middleleft">
							<div id="lbox_middleright">
								<div id="bs_left">
										<IFRAME height="100%" width="100%"  frameBorder=0 id=frmleft name=frmleft src="${path}${INDEX_PREFIX}/toLeft?menuTypeMode=Tree"  allowTransparency="true"></IFRAME>
								</div>
								<!--更改左侧栏的宽度需要修改id="bs_left"的样式-->
							</div>
						</div>
					</div>
					<div id="lbox_bottomcenter">
						<div id="lbox_bottomleft">
							<div id="lbox_bottomright">
								<div class="lbox_foot"></div>
							</div>
						</div>
					</div>
				</div>
			</td>
			<!--左侧区域end-->
			
			<!--分隔栏区域start-->
			<td class="spliter main_shutiao" targetId="hideCon" beforeClickTip="收缩面板" afterClickTip="展开面板" beforeClickClass="bs_leftArr" afterClickClass="bs_rightArr">
			</td>
			<!--分隔栏区域end-->
			
			<!--右侧区域start-->
			<td class="ali01 ver01"  width="100%">
				<div id="rbox">
					<div id="rbox_topcenter">
						<div id="rbox_topleft">
							<div id="rbox_topright"></div>
						</div>
					</div>
					<div id="rbox_middlecenter">
						<div id="rbox_middleleft">
							<div id="rbox_middleright">
								<div id="bs_right">
								       <IFRAME height="100%" width="100%" frameBorder=0 id=frmright name=frmright src="${path}${INDEX_PREFIX}/toOpen"  allowTransparency="true"></IFRAME>
								</div>
							</div>
						</div>
					</div>
					<div id="rbox_bottomcenter" >
						<div id="rbox_bottomleft">
							<div id="rbox_bottomright"></div>
						</div>
					</div>
				</div>
			</td>
			<!--右侧区域end-->
		</tr>
	</table>
<!--尾部区域start-->
	<div id="fbox">
		<div id="bs_footcenter">
			<div id="bs_footleft">
				<div id="bs_footright">
					版权所有：重庆远通电子技术开发有限公司
				</div>
			</div>
		</div>
	</div>
</div>
<!--尾部区域end-->


<!--窗口任务栏区域start-->
<div id="dialogTask" class="dialogTaskBg" style="display:none;"></div>
<!--窗口任务栏区域end-->

<!--浏览器resize事件修正start-->
<div id="resizeFix"></div>
<!--浏览器resize事件修正end-->

<!--载进度条start-->
<div class="progressBg" id="progress" style="display:none;"><div class="progressBar"></div></div>
<!--载进度条end-->

<iframe id="sessionSkin" src="" width="0" height="0" style="display:none;"></iframe>
<script type="text/javascript">

var _owned_pc_systemcodes = $.parseJSON('${_owned_pc_systemcodes}'); //@required
var path = "${path}";
var tokenId = "${tokenId}";
var _platform = "${_platform}";
$(function(){
	if(_platform){
		$("#index").show();
	}else{
		$("#index").hide();
	}
	
    $("#index").click(function(){
    	window.location = "${up_systemweb_platform}?tokenId="+tokenId;
    });

});


</script>
</body>
</html>
