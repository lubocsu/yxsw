<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="path" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>人员巡检情况统计</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="/system/common/header_splitmode.jsp"%>
<link href="${path }/css/core.css" rel="stylesheet" type="text/css"/>
<!--树组件start -->
<script type="text/javascript" src="${path}/libs/js/tree/ztree/ztree.js"></script>
<link href="${path}/libs/js/tree/ztree/ztree.css" rel="stylesheet"
	type="text/css" />
<!--弹窗组件start-->
<script type="text/javascript" src="${path}/libs/js/popup/drag.js"></script>
<script type="text/javascript" src="${path}/libs/js/popup/dialog.js"></script>
<!--布局控件start-->
<script type="text/javascript" src="${path }/libs/js/nav/layout.js"></script>
<!--布局控件end-->
<!--echart图start  -->
<script type="text/javascript" src="${path }/libs/js/echarts/echarts.js"></script>
<!--echart图end  -->
<!--基本选项卡start-->
<script type="text/javascript" src="${path }/libs/js/nav/basicTab.js"></script>
<!--基本选项卡end-->
<script type="text/javascript"
	src="${path }/libs/js/form/datePicker/WdatePicker.js"></script>
<!--引入chart 配置项  start-->
<script type="text/javascript" src="${path }/js/piss/piss1.js"></script>
<!--引入chart 配置项    start-->
<style>
.l-layout-center {
	border: none !important;
}

.l-layout-left {
	border-bottom: none !important;
}

.l-layout-drophandle-left {
	width: 10px;
}

/* iframe{border: red 1px solid; */
/*     margin-bottom: 100px;} */
</style>


</head>
<body>

	<div id="layout1" style="width:100%">
	  <div id="centerCon" position="center" style="width:100%">
	  
	   <div name="数据" >
				    <div class="padding_right5">
					<div id="data">
							<table class="tableStyle" mode="list">
								<tr align="center">
									<th width="12.5%">序号</th>
									<th width="12.5%">人员</th>
									<th width="12.5%">任务总数</th>
									<th width="12.5%">完成数</th>
									<th width="12.5%">按时完成数</th>
									<th width="12.5%">超期数</th>
									<th width="12.5%">正常超期数</th>
									<th width="12.5%">按时完成率(不含正常超时)</th>
									
								</tr>
								<c:if test="${empty list }">
									<tr>
										<td colspan="10" align="center" style="color: grey;">暂无数据</td>
									</tr>
								</c:if>
								<c:set var="index" value="1"></c:set>
								<c:forEach items="${list }" var="map">
									<tr>
										<td  align="center">${index }</td>
										<c:set var="index" value="${index+1 }"></c:set> 
										<td align="center">${map.person_name}</td>
										<td align="center">${map.alls }  </td>
										<td align="center">${map.completes }</td>
										<td align="center">${map.not_over_times }</td>
										<td align="center">${map.over_times }</td>
										<td align="center">${map.zc_over_times }</td>
										<td align="center">${map.rate }%</td>
									
										
										
									</tr>
								</c:forEach>
							</table>
						</div>
					</div>
			    </div>
	 </div>
	
	</div>
	
	
	   <div class="list-btm">
            <div class="list-btm-div">
                <div class="div-btn-sbt">
                   
                    <button class="btm-button" type="button"  onclick="back();"><span class="back-icon">返回</span></button>
                </div>
            </div>
        </div>
   
</body>

<script type="text/javascript">
 function back(){
	 
	 
	// location.href='${path}'+"/piss/init";
	  window.history.back()
 }
</script>		
	
</html>