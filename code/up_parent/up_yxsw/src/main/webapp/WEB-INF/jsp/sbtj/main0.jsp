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
<script type="text/javascript" src="${path }/js/sbtj/sbtj.js"></script>
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
	  	<div class="box2" panelTitle="巡检业务统计" showStatus="false">
				<form action="${path}/piss/init" id="queryForm" method="post">
					<table align="center">
				
						<tr>
							<td>
								时段：
							</td>
							<td>
								<input type="text" class="date" name="startDate" id="startDate" value="${st }"   readonly="readonly" dateFmt="yyyy-MM-dd" style="width:140px"  onFocus="WdatePicker({isShowClear:false})" />
								至 
								<input type="text" class="date" name="endDate" id="endDate" value="${et }"  readonly="readonly"   dateFmt="yyyy-MM-dd" style="width:140px"  onFocus="WdatePicker({isShowClear:false})" />
							</td>
							<td></td>
							<td colspan="3">
								
								<input type="radio" name="dateType" id="one-work" value="0"/><label for="one-work">本周</label>
								<input type="radio" name="dateType" id="one-m" value="1"/><label for="one-m">本月</label>
								<input type="radio" name="dateType" id="one-y" value="4"/><label for="one-y">本年</label>
								<input type="radio" name="dateType" id="free" value="5"/><label for="free">自定义</label>
							
							</td>
							<td></td>
							<td>
								<button type="button" onclick="searchHandler()"><span class="icon_find">查询</span></button>
								<button type="button" onclick="resetSearch()"><span class="icon_reload">重置</span></button>
						
							</td>
						</tr>
					</table>
				</form>
			</div>
			<c:if test="${typeId==1 }">
	    <div id="main1" style="width: 1600px; height: 400px;"></div>
	      </c:if>
	   <div name="数据" >
				    <div class="padding_right5">
					<div id="data">
							<table class="tableStyle" mode="list">
								<tr align="center">
									<th width="12.5%">序号</th>
									<th width="12.5%">厂站</th>
									<th width="12.5%">任务总数</th>
									<th width="12.5%">完成数</th>
									<th width="12.5%">按时完成数</th>
									<th width="12.5%">超时次数</th>
									<th width="12.5%">正常超时次数</th>
									<th width="12.5%">按时完成率(不含正常超时)</th>
									
								</tr>
								<c:if test="${empty list }">
									<tr>
										<td colspan="10" align="center" style="color: grey;">暂无数据</td>
									</tr>
								</c:if>
								<c:set var="index" value="1"></c:set>
								<c:forEach items="${list }" var="map">
								 <input  type="hidden" id="csId" value="${map.belong_wsc_id}"> </input>
									<tr>
										<td  align="center">${index }</td>
										<c:set var="index" value="${index+1 }"></c:set> 
										<td mid="${map.belong_wsc_name }" class="business"><a href="#">${map.belong_wsc_name}</a></td>
										<td>${map.alls }  </td> 
										<td>${map.completes }</td>
										<td>${map.not_over_times }</td>
										<td>${map.over_times }</td>
										<td>${map.zc_over_times }</td>
										<td>${map.rate }</td>
										
										<%-- <td>${map.looked_rate }%</td>  --%>
										
										
									</tr>
								</c:forEach>
							</table>
						</div>
					</div>
			    </div>
	 </div>
	
	</div>
	
	
	
</body>
<script type="text/javascript">
 
   
   (function(){
	   
	 
	$("input[name='dateType']").live("click", function() {

			var val = this.value
			
			
			if(val == 5){
				$("#startDate").attr("disabled",false)
				$("#endDate").attr("disabled",false)
			}else{
				$("#startDate").attr("disabled",true)
				$("#endDate").attr("disabled",true)
				getDate(val);
			}
		})
		
	  $("input[name='dateType'][value='"+ ${reportForm.dateType} +"']").click() 
		var typeId = ${typeId}
		console.log(typeId)
		if (typeId == 1) {
			var chartMap = JSON.parse('${chartMap}')
			console.log(chartMap)
			initChart(chartMap)
		}

	})()
	

	$(".business a").live("click", function() {
		var datetype=$('input[name="dateType"]:checked ').val();
		console.log(datetype+"shijianleixi")
		//location.href = "http://www.baidu.com";
		var csId = $("#csId").val()
		console.log(csId)
		//location.href = '${path}/piss/getPerson?csId=' + csId +"&startTime=${st}&endTime=${et}&dateType="+datetype;
		location.href = '${path}/piss/getPerson?csId=' + csId +"&startTime=${st}&endTime=${et}"
	})

	//查询
	function searchHandler() {
		$("#queryForm").attr("action", "${path}/piss/init");
		jQuery("#queryForm").submit();
	}

	//重置
	function resetSearch() {
		$("input[name='dateType'][value='0']").click();
		$("#queryForm").attr("action", "${path}/piss/init");
		$("#queryForm").submit();
	}

	function getDate(type) {

		$.ajax({
			type : "POST",
			url : "${path}/piss/getDateByType",
			data : {
				dateType : type
			},
			success : function(result) {

				if (result.flag == true) {
					$("#startDate").val(result.data.startDate);
					$("#endDate").val(result.data.endDate);
				}
			},
			/* error : function(result) {
				Dialog.alert("获取数据失败，请稍后再试。");
			} */
		});
	}
</script>
		
		
		
	
</html>