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
		
		<!-- 树start-->
		<script type="text/javascript" src="${path}/libs/js/tree/ztree/ztree.js"></script>
		<link href="${path}/libs/js/tree/ztree/ztree.css" rel="stylesheet" type="text/css"/>
		
		<script type="text/javascript">
			var setting = {
				async: {
					enable: true,
					contentType:"application/x-www-form-urlencoded",
					url: "${path}${INDEX_PREFIX}/findLeafMenu",
					autoParam: ["id","name"],
					dataType: "json"
				},
				callback:{
					onClick:function(e, treeId, treeNode){
						//单击展开
						var zTree = $.fn.zTree.getZTreeObj("treeDemo");
						zTree.expandNode(treeNode);
						//出现进度条
						if(treeNode.url!=null){
							showProgressBar();
							//每次点击时设置当前位置内容
							if(treeNode.getParentNode()){
								top.positionContent=uncompile(quiLanguage.position.title)+treeNode.getParentNode().name+">>"+treeNode.name;
							}
							else{
								top.positionContent=uncompile(quiLanguage.position.title)+treeNode.name;
							}
							top.positionType="normal";
						}
					}
				}
			};
			function initComplete(){
				setting.view = {
					fontCss: getFontCss
				};
				var nodes = JSON.parse('${menus}');
				zTree_Menu = $.fn.zTree.init($("#treeDemo"), setting, nodes);
			}
			function customHeightSet(contentHeight){
				$(".scrollContent").height(contentHeight-60);
			}
			
		</script>
		<!-- 菜单操作start -->
		<script type="text/javascript" src="${path}/js/index/left_treeCommMethod.js"></script>
		<!-- 菜单操作end -->
	</head>
	
	<body>
		<div class="padding_top5 ali02" style="height:55px;">
			<table width="100%">
				<tr>
					
					<td width="80%" class="ali03">
						<input type="text" id="searchKey" value="">
					</td>
					<td>
						<span class="img_find hand" title="点击查找节点" keepDefaultStyle="true" onclick="findNode()"></span>
					</td>
				</tr>
				<tr>
					<td colspan="2" class="ali02">
						<a onclick="showAll()">全部展开</a>&nbsp;&nbsp;
						<a onclick="hideAll()">全部收缩</a>
					</td>
				</tr>
			</table>
		</div>
		<div id="scrollContent" style="overflow-x:hidden;">
			<div>
				<ul id="treeDemo" class="ztree"></ul>
			</div>
		</div>				
	</body>
</html>