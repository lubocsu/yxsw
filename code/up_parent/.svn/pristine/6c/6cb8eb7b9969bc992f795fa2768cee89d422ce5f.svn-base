<%@page language="java" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<c:set var="path" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    <title>导入文件</title>
	<!--框架必需start-->
	<script type="text/javascript" src="${path }/libs/js/jquery.js"></script>
	<script type="text/javascript" src="${path }/libs/js/language/cn.js"></script>
	<script type="text/javascript" src="${path }/libs/js/framework.js"></script>
	<link href="${path }/libs/css/import_basic.css" rel="stylesheet" type="text/css"/>
	<link rel="stylesheet" type="text/css" id="skin" prePath="${path }/" scrollerY="false"/>
	<link rel="stylesheet" type="text/css" id="customSkin"/>
	<!--框架必需end-->
	
	<!-- 异步上传控件start -->
	<script type="text/javascript" src="${path }/libs/js/form/upload/fileUpload.js"></script>
	<script type="text/javascript" src="${path }/libs/js/form/upload/handlers.js"></script>
	<!-- 异步上传控件end -->
	
  </head>
  
  <body>
  	<input type="hidden" name="type" value="${type }"/>
  	<div style="text-align: center;">
  	<table style="text-align: center;width: 100%;height: 90%;">
  		<tr>
  			<td colspan="2"><button type="button" id="uploadBtn"><span class="icon_xls">导入</span></button></td>
  		</tr>
  		<tr>
  			<td colspan="2" class="padding_top5">说明：导入的excel需要遵循规定的格式<br />
  				<div id="uploadList"></div>
  			</td>
  		</tr>
  		<tr>
  			<td colspan="2"><a href="${path }/uploadFiles/excelTemplate/${templateName }.xls" class="red underline">点击这里</a>下载导入的excel数据模板</td>
  		</tr>
  	</table>
		</div>

	<script>

		var upload3;
		var catalogId3;
		function initComplete() {
			//生成catalogId
			upload3 = $.fileUpload.start({
				contextPath : "${path}",
				buttonContainer : "uploadBtn",
				fileListContainer : "uploadList",
				uploadUrl : "/common/importExcel?&entityName=${entityName}",
				fileSize : 10240,
				fileTypes : '*.xls'
			});
		};
	</script>
  </body>
</html>
