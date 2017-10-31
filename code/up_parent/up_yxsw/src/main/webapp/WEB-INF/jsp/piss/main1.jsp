<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="path" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>人员巡检情况统计</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="/system/common/header_splitmode.jsp" %>
<!--树组件start -->
<script type="text/javascript" src="${path}/libs/js/tree/ztree/ztree.js"></script>
<link href="${path}/libs/js/tree/ztree/ztree.css" rel="stylesheet" type="text/css"/>
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
<script type="text/javascript"  src="${path }/libs/js/form/datePicker/WdatePicker.js"></script>
<!--引入chart 配置项  start-->
 <script type="text/javascript"  src="${path }/js/piss/piss1.js"></script> 
<!--引入chart 配置项    start-->
<style>
.l-layout-center{
    border:none!important;
}
.l-layout-left{
    border-bottom:none!important;
}
.l-layout-drophandle-left{
    width: 10px;
}

/* iframe{border: red 1px solid; */
/*     margin-bottom: 100px;} */
</style>

<script type="text/javascript">

jQuery(function(){
	
	var chartMap = JSON.parse('${chartMap}');
	
	initChart(chartMap);
	
	
	/* $("input[name='dateType']").live("click",function(){
		
		var val = this.value;
		if(val == 5){
			$("#startDate").attr("disabled",false);
			$("#endDate").attr("disabled",false);
		}else{
			$("#startDate").attr("disabled",true);
			$("#endDate").attr("disabled",true);
			getDate(val);
		}
	});
	
	var wwidth = window.innerWidth;
	var lwidth = wwidth/2 -15;
	$("#chart1").css("width",lwidth+"px");
	 */
	
	//$("input[name='dateType'][value='"+ ${reportForm.dateType} +"']").click();
	/* 集团领导和系统管理员可以看到图表 */
	//if(${userType} == 5 || ${userType}==4){
		//var chartMap1 = JSON.parse('${chartMap1 }');
		//initChart1(chartMap1);
		//initChart1();
		
	//}
});

function getDate(type){ 
	
	$.ajax({
        type: "POST",
        url: "${path}/piss/getDateByType",
        data:{dateType:type},
        success: function (result) {
				
    		if(result.flag == true){
    			$("#startDate").val(result.data.startDate);
    			$("#endDate").val(result.data.endDate);
    		}
		},
		error:function(result){
			Dialog.alert("获取数据失败，请稍后再试。");
		}
	});
}
//绑定钻取查询事件
$(".business a").live("click",function(){
	var orgCode = $(this).parent("td").attr("mid");
	var datetype=$('input[name="dateType"]:checked ').val();
	var url = "${path}/piss/getinspectionBusinessByOrgCode?orgCode=" + orgCode +"&startTime=${st}&endTime=${et}&queryMark=${queryMark}&dateType="+datetype;
	location.href = url;
	
})
//查询
function searchHandler(){
	$("#queryForm").attr("action","${path}/piss/getPissDate");
	jQuery("#queryForm").submit();
}
//重置
function resetSearch(){
	$("input[name='dateType'][value='3']").click();
	$("#queryForm").attr("action","${path}/piss/getPissDate");
	$("#queryForm").submit();
}

</script>
</head>
<body>
	<div id="layout1" style="width:100%">
		<div id="centerCon" position="center" style="width:100%">
        	<div class="box2" panelTitle="巡检业务统计" showStatus="false">
				<form action="${path}/piss/getPissDate" id="queryForm" method="post">
					<table align="center">
						<tr>
							<td colspan="3">
								<input type="radio" name="dateType" id="one-m" value="1"/><label for="one-m">近一个月</label>
								<input type="radio" name="dateType" id="three-m" value="2"/><label for="three-m">近三个月</label>
								<input type="radio" name="dateType" id="half-y" value="3"/><label for="half-y">近半年</label>
								<input type="radio" name="dateType" id="one-y" value="4"/><label for="one-y">近一年</label>
								<input type="radio" name="dateType" id="free" value="5"/><label for="free">自定义</label>
							</td>
						</tr>
						<tr>
							<td>
								时段：
							</td>
							<td>
								<input type="text" class="date" name="startDate" id="startDate" value="${st }" dateFmt="yyyy-MM-dd" style="width:140px" readonly="readonly" onFocus="WdatePicker({isShowClear:false})" />
								至 
								<input type="text" class="date" name="endDate" id="endDate" value="${et }" dateFmt="yyyy-MM-dd" style="width:140px" readonly="readonly"  onFocus="WdatePicker({isShowClear:false})" />
							</td>
							<td>
								<button type="button" onclick="searchHandler()"><span class="icon_find">查询</span></button>
								<button type="button" onclick="resetSearch()"><span class="icon_reload">重置</span></button>
							
							</td>
						</tr>
					</table>
				</form>
			</div>
			
			
			<%--     <c:if test="${userType == 5 or userType == 4}"> --%>
				  <!--   <div name="图表" style="height:570px;">
				    	<div>
				    		<div>
					    		<div id="chart1" style=" height: 880px;float:left"></div>
							
					    	</div>
				    	</div>
				    </div> -->
		<div>		<%-- </c:if> --%>
		<div>
		<div id="main1" style="width: 1600px;height:400px;"></div> </div>	</div>	
		<!-- <script type="text/javascript">
		var chartMap = JSON.parse('${chartMap1 }');
		
		initChart (chartMap);
		
		</script>
	 -->

           </script>
			<!-- <div class="basicTab" style="width:100%"> -->
			    <div name="数据" >
				    <div class="padding_right5">
					<div id="data">
							<table class="tableStyle" mode="list">
								<tr align="center">
									<th width="5%">序号</th>
									<th width="5%">组织机构</th>
									<th width="6%">参与人数</th>
									<th width="6%">巡检任务数</th>
									<th width="6%">完成数</th>
									<th width="8%">任务超期数</th>
									<th width="9%">超期率(%)</th>
									<th width="8%" title="正式巡检点总次数（点次）">巡检点累计巡检量</th>
									<th width="8%" title="临时巡检点总次数（点次）">临时点累计巡检量</th>
									<th width="8%">总里程（千米）</th>
									<th width="8%">人均里程（千米）</th>
									<th width="8%">发现问题数</th>
									<th width="6%">巡检点数</th>
									<th width="9%" title="已巡检点数/巡检点总数（正式巡检点）">巡检覆盖率(%)</th>
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
										<td mid="${map.org_code }" class="business"><a href="#">${map.org_name }</a></td>
										<td>${map.propernum }</td>
										<td>${map.tasks }</td>
										<td>${map.tasks_complete }</td>
										<td>${map.tasks_overtime }</td>
										<td>${map.overtime_rate }%</td>
										<td>${map.points }</td>
										<td>${map.lspoints }</td>
										<td>${map.ssummiles }</td>
										<td>${map.avg_miles }</td>
										<td>${map.afind_faults }</td>
										<td>${map.looked_count }</td>
										<td>${map.looked_rate }%</td>
									</tr>
								</c:forEach>
							</table>
						</div>
					</div>
			    </div>
				
			<!-- </div> -->
        </div>
	</div>
	
 <a href="http://www.w3school.com.cn">W3School</a>
 <div id="main1" style="width: 1600px;height:400px;"></div>
</body>

</html>