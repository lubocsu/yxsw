<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<title>巡检任务列表</title>
	<meta name="viewport" content="initial-scale=1, maximum-scale=1">
	<meta name="apple-mobile-web-app-capable" content="yes">
	<meta name="apple-mobile-web-app-status-bar-style" content="black">
	<!--SUI mobile必须-->
	<link rel="stylesheet" href="${path}/mobile/libs/sui/css/sm.css" />
	<link rel="stylesheet" href="${path}/mobile/libs/sui/css/sm-extend.css" />    
	<!--自定义样式-->
	<link rel="stylesheet" href="${path}/mobile/css/yxapp.css" />
</head>
<body>
	<div class="page-group">
        <div class="page page-current">
        <!-- 你的html代码 -->
        <header class="bar bar-nav">	
		    <span class="custom-icon fanhui"></span>
		    <h1 class="title">巡检任务列表</h1>
		    <span class="custom-icon search"></span>
		    <input class="search-ipt" type="text"/>
			<button class="cancel">取消</button>
			<button class="cleartext"></button>
		</header>
		<div class="task-mask">
		</div>
		<div class="pull-content-father">
			<section class="normal-tab blue-normal-tab">
<!-- 			<div class="normal-tab-top"> -->
<!-- 				<ul> -->
<!-- 					<li id="li_one" style="width:100%;"><div>是否挂接RFID</div><span></span><span class="li-line"></span></li> -->
<!-- 				</ul> -->
<!-- 			</div> -->
<!-- 			<div class="normal-tab-body"> -->
<!-- 				<div class="backlog" id="backlog_one"> -->
<!-- 					<ul></ul> -->
<!-- 				</div> -->
<!-- 			</div> -->
			<div class="pull-content-father top0">
				<div class="content infinite-scroll infinite-scroll-bottom pull-to-refresh-content" data-ptr-distance="55" data-distance="100">
				    <!-- 默认的下拉刷新层 -->
				    <div class="pull-to-refresh-layer">
				        <div class="preloader"></div>
				        <div class="pull-to-refresh-arrow"></div>
				    </div>
				    <!-- 下面是正文 -->
				 	<div class="task-list-body">
						<ul>
						</ul>
					</div>
				  <!-- 加载提示符 -->
		          <div class="infinite-scroll-preloader">
		              <div class="preloader"></div>
		          </div>
				</div>
			</div>
		</section>
		<div class="lscz-search">
			<div class="search-conditions" id="search">
				<div class="search-conditions" id="search">
					<dl>                                      
						<dt>时间范围</dt>
						<dd>
							<div class="choose-time">
								<span class="starttime"><input type="text" id="starttime1" data-toggle='date'placeholder="请选择" /></span>-<span class="endtime"><input type="text" id="endtime1" data-toggle='date' placeholder="请选择" /></span>	
							</div>
						</dd>
					</dl>
				</div>
			</div>
		</div><!--lscz-search END-->
			<div class="mask"></div>
		</div>
		</div><!-- page标签-->   
	</div>
	<!--SUI mobile必须-->
	<script type='text/javascript' src='${path}/mobile/js/zepto.js' charset='utf-8'></script>
	<script type='text/javascript' src='${path}/mobile/libs/sui/js/sm.js' charset='utf-8'></script>
	<script type='text/javascript' src='${path}/mobile/libs/sui/js/sm-extend.js' charset='utf-8'></script>	
	<!--自定义脚本-->
	<script type="text/javascript" src="${path}/mobile/js/uc-ui.js"></script>
	<script type="text/javascript" src="${path}/mobile/js/yxapp.js"></script>
	<script>
		var pageNo = parseInt("${pageIndex}");
		var pageSize = parseInt("${pageSize}");
		$(function(){
			
		onRefresh(function(lastIndex,pageSize,isRefreshDone){
			if(!isRefreshDone){
				$.alert('刷新数据未成功');
			}
			if ( lastIndex < pageSize) {
	    		$('.infinite-scroll-preloader').remove();
	    		// 加载完毕，则注销无限加载事件，以防不必要的加载
        		$.detachInfiniteScroll($('.infinite-scroll'));
	    	}
			$.pullToRefreshDone('.pull-to-refresh-content');
		},"down","inits");
		
		});
		
		function onRefresh(func,udflag,inits){
			var time = new Date();
			var smonthfull = (time.getMonth()+1)<10?'0' + (time.getMonth()+1) : (time.getMonth()+1);
			var today = time.getFullYear() + "-" + smonthfull + "-" + time.getDate();
			$("#starttime1").calendar({
	    value: [today]
			});
			$("#endtime1").calendar({
	    value: [today]
			});
			
			var starttime1 = $("#starttime1").val();
			var endtime1 = $("#endtime1").val();
			var taskName = $(".search-ipt").val();
			var param ={
					"starttime1":starttime1,
					"endtime1":endtime1,
					"taskName" :taskName
			}
			// 初始化获取页面数据
			$.ajax({
	            type: "POST",
	            url: "${path}/mobile/inspectionRecord/getCxTaskList",
	            data: {
	            		"tokenId":"${tokenId}",
	            		"pager.pageNo":pageNo,
	            		"pager.pageSize":pageSize,
	            		"param":JSON.stringify(param)
	            	  },
	            success: function (appresult) {
	                var flag = appresult.flag;
	                
	                if(flag){
	                	var cxTaskList = appresult.data.cxTaskList;
	                	//生成工单数据
	                	var listsul = $(".task-list-body").find("ul");
	                	if(udflag != "up"){
	                		listsul.empty();
	                		
	                	}
	                	for(var i in cxTaskList){
	                		var equipmentStatusList = '<li onclick="toCxTaskDetail(\''+ cxTaskList[i].cx_task_id +'\');">';
	                		equipmentStatusList += '<div class="company-top"><h3>'+(cxTaskList[i].cx_task_name!=null?cxTaskList[i].cx_task_name:"--")+'</h3></div>';
	                		equipmentStatusList += '<div class="company-cont"><table>';
	                		equipmentStatusList += '<tr><th>任务编号：</th><td  style="width:70%;">'+(cxTaskList[i].cx_task_code!=null?cxTaskList[i].cx_task_code:"--")+'</td></tr>';
	                		equipmentStatusList += '<tr><th>任务日期：</th><td>'+(cxTaskList[i].cx_task_date!=null?cxTaskList[i].cx_task_date:"--")+'</td></tr>';
	                		equipmentStatusList += '<tr><th>是否超期：</th><td>'+(cxTaskList[i].is_over_time!=null?cxTaskList[i].is_over_time:"--")+'</td></tr>';
	                		equipmentStatusList += '</table></div></li>';
	                		listsul.append(equipmentStatusList);
	                	}
	                	
		                if(udflag=="up"){
	  	  	  				func(cxTaskList.length,pageSize,true);
	  	  	  			}else if(udflag=="down"){
	  	  					func(cxTaskList.length,pageSize,true);
	  	  	  	  		}
	                }else{
	                	if(appresult.data.login==false){
		                	alert(appresult.message);
							yxapp.project.toLogin();
						}else{
							$.alert(appresult.message);
						}
	                }
	            },
	            error:function(result){
		    		var data  = yxapp.base.getBaseData("network");
		        	   if(data == "false"){
			       		$.alert("网络连接失败，请检查网络后重试！");
		        	   }else{
		        		   $.alert("提交失败，请重试！");
		        	}
		       	}
	        });
		}
		function toCxTaskDetail(cxTaskId){
			var url = "${path}/mobile/inspectionRecord/toCxTaskDetail?cxTaskId=" + cxTaskId + "&tokenId=${tokenId}";
			location.href = url;
		}
		$(document).on('click','.fanhui', function () {
			yxapp.project.toIndex();
		});
	</script>
</body>
</html>