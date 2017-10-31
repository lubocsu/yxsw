<%@page import="com.upsoft.systemweb.controller.IndexController"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="path" value="${pageContext.request.contextPath}"></c:set>
<c:set var="INDEX_PREFIX" value="<%=IndexController.FORWARD_PREFIX%>"></c:set> 
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<!--框架必需start-->
		<script type="text/javascript" src="${path}/libs/js/jquery.js"></script>
		<script type="text/javascript" src="${path}/libs/js/language/cn.js"></script>
		<script type="text/javascript" src="${path}/libs/js/framework.js"></script>
		<link href="${path}/libs/css/import_basic.css" rel="stylesheet" type="text/css"/>
		<link rel="stylesheet" type="text/css" id="skin" prePath="${path}/" scrollerY="false"/>
		<link rel="stylesheet" type="text/css" id="customSkin"/>
		<!--框架必需end-->
		
		<!-- ztree start -->
		<script type="text/javascript" src="${path}/libs/js/tree/ztree/ztree.js"></script>
		<link href="${path}/libs/js/tree/ztree/ztree.css" rel="stylesheet" type="text/css"/>
		<!-- ztree end -->
		
		<!-- 垂直选项卡start-->
		<script type="text/javascript" src="${path}/js/index/left_verticalTab.js"></script>
		<!-- 垂直选项卡end-->

		<script type="text/javascript">
			function initComplete(){
				$("#scrollContent").css({
					"overflowY":"hidden"
				});
				if('${systemCode}'){
					$('#menuId').attr('src','${path}${INDEX_PREFIX}/initMenuTree?syscode=${systemCode}&selMenuType=${menuTypeMode}');
				}else{
					$("#vTab").bind("actived",function(e,i){
						jQuery.jCookie('leftvtabIdx',i.toString());
						initContent(i);
					});
					showContent();
				}
			}
			function showContent(){
				//还原上次选中的tab
				var vtabIdx=jQuery.jCookie('leftvtabIdx');
				if(vtabIdx==false||vtabIdx=="false"){
					initContent(0);
				}
				else{
					var idx=parseInt(vtabIdx);
					$("#vTab").verticalTabSetIdx(idx);
					initContent(idx);
				}
			}
			function initContent(idx){
				var item = $("#vTab").find("a").eq(idx).data("item");
				$('#menuId').attr('src','${path}${INDEX_PREFIX}/initMenuTree?syscode='+item.code+'&selMenuType=${menuTypeMode}');
			}
			function customHeightSet(contentHeight){
				$(".vtab_content").height(contentHeight);
// 				$(".vtab_content").height(contentHeight-45);
			}
		</script>
	</head>
	
	<body leftFrame="true">
		<div id="scrollContent">
			<div class="padding_left2">	
				<div class="verticalTab" id="vTab" iframeMode="true" data='${tabs}'>
					<div class="vtab_content" style="width:186px;border:0;">
						<IFRAME scrolling="no" style="position:absolute; left:0; top:0; right:0; bottom:0; width:100%; height:100%;" frameBorder="0" id="menuId" name="menuId" allowTransparency="true"></IFRAME>
					</div>
				</div>	
			</div>
		</div>	
	</body>
</html>