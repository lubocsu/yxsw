<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="path" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>新建片区</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!--框架必需start-->
<%@include file="/system/common/header_splitmode.jsp" %>
<!--框架必需end-->

<!-- 异步上传控件start -->
<script type="text/javascript" src="${path }/libs/js/form/upload/fileUpload.js"></script>
<script type="text/javascript" src="${path }/libs/js/form/upload/handlers.js"></script>
<script type="text/javascript" src="${path }/js/plug-in/customupload.js"></script>
<!-- 异步上传控件end -->


</head>
	<body>
  	<div style="text-align: center;">
	  	<table style="text-align: center;width: 100%;height: 90%;">
	  		<tr>
	  			<td></td>
	  			<td class="padding_top5">
	  				<span id="uploadBtn"></span>
	  				<div id="uploadList"></div>
	  			</td>
	  		</tr>
	  		<tr>
  			</tr>
	  	</table>
	</div>
	<script type="text/javascript">
		var upload1 = new CustomUpload();
		upload1.init("test",new Date().getTime(),{
			"btn" : "uploadBtn",
			"listDiv" : "uploadList",
			"fileSize" : '2048000',
			"fileTypes" : '*.*'
		});
	</script>
	<!-- 一下为测试内容 -->
	<button onclick="testUploadCustomFileBeanData()" />
	<script type="text/javascript">
		function testUploadCustomFileBeanData(){
			var listStr = JSON.stringify(CustomUploadCallBackData_UseQUI);
			$.post(path+"/file/testUploadCustomFileBeanData",{"list":listStr},function(result){
				
			});
		}
	</script>
  </body>
</html>