<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<title>巡检任务设施设备列表</title>
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
		    <h1 class="title">设施设备列表</h1>
<!-- 		    <span class="custom-icon search"></span> -->
		    <input class="search-ipt" type="text"/>
			<button class="cancel">取消</button>
			<button class="cleartext"></button>
		</header>
		<div class="task-mask">
		</div>
		<div class="pull-content-father">
			<section class="normal-tab blue-normal-tab">
			<div class="normal-tab-top">
				<ul>
					<li id="li_one" style="width:100%;"><div>类型</div><span></span><span class="li-line"></span></li>
				</ul>
			</div>
			<div class="normal-tab-body">
				<div class="backlog" id="backlog_one">
					<ul></ul>
				</div>
			</div>
			<div class="pull-content-father toptworem">
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
			<div class="mask"></div>
		</div>
		</div><!-- page标签-->   
	</div>
	<!--SUI mobile必须-->
	<script type='text/javascript' src='${path}/mobile/js/zepto.js' charset='utf-8'></script>
	<script type='text/javascript' src='${path}/mobile/libs/sui/js/sm.js' charset='utf-8'></script>
	<script type='text/javascript' src='${path}/mobile/libs/sui/js/sm-extend.js' charset='utf-8'></script>	
	<!--自定义脚本-->
	<script type="text/javascript" src="${path}/mobile/js/uc-ui-sbss.js"></script>
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
			
			var sbOrssid = $(".backlog").eq(0).find("li.selected").attr("sbOrssid");
			if(sbOrssid == undefined){
				sbOrssid = "";
			}
			var param ={
					"sbOrssid":sbOrssid
			}
			// 初始化获取页面数据
			$.ajax({
	            type: "POST",
	            url: "${path}/mobile/inspectionRecord/getCxTaskSbssList",
	            data: {
	            		"tokenId":"${tokenId}",
	            		"cxTaskId":"${cxTaskId}",
	            		"pager.pageNo":pageNo,
	            		"pager.pageSize":pageSize,
	            		"param":JSON.stringify(param)
	            	  },
	            success: function (appresult) {
	                var flag = appresult.flag;
	                
	                if(flag){
	                	if(inits == "inits"){
	                		var sbOrss = appresult.data.sbOrss;
		                	// 生成设备设施信息状态
		                	var sbOrssul = $(".backlog").eq(0).find("ul");
		                	sbOrssul.append('<li sbOrssid=""><span class="left">全部</span><span class="right"></span></li>');
		                	for ( var i in sbOrss) {
		                		sbOrssul.append('<li sbOrssid="'+sbOrss[i].key+'"><span class="left">'+sbOrss[i].value+'</span><span class="right"></span></li>');
							}
	                	}
	                	var sbssList = appresult.data.sbssList;
	                	//生成工单数据
	                	var listsul = $(".task-list-body").find("ul");
	                	if(udflag != "up"){
	                		listsul.empty();
	                		
	                	}
	                	for(var i in sbssList){
	                		var equipmentStatusList = '<li onclick="toCxTaskSbssDetail(\''+ sbssList[i].ttask_item_sbss_id +'\');">';
	                		equipmentStatusList += '<div class="company-top"><h3>'+(sbssList[i].name!=null?sbssList[i].name:"--")+'</h3></div>';
	                		equipmentStatusList += '<div class="company-cont"><table>';
	                		equipmentStatusList += '<tr><th>类型：</th><td  style="width:70%;">'+(sbssList[i].detail_type!=null?sbssList[i].detail_type:"--")+'</td></tr>';
	                		equipmentStatusList += '<tr><th>巡检时间：</th><td>'+(sbssList[i].opt_time!=null?sbssList[i].opt_time:"--")+'</td></tr>';
// 	                		equipmentStatusList += '<tr><th>是否超期：</th><td>'+(sbssList[i].is_over_time!=null?sbssList[i].is_over_time:"--")+'</td></tr>';
	                		equipmentStatusList += '</table></div></li>';
	                		listsul.append(equipmentStatusList);
	                	}
	                	
		                if(udflag=="up"){
	  	  	  				func(sbssList.length,pageSize,true);
	  	  	  			}else if(udflag=="down"){
	  	  					func(sbssList.length,pageSize,true);
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
		function toCxTaskSbssDetail(ttaskItemSbssId){
			var url = "${path}/mobile/sbssScan/toXjDetail?ttaskItemSbssId=" + ttaskItemSbssId + "&tokenId=${tokenId}";
			location.href = url;
		}
		$(document).on('click','.fanhui', function () {
			yxapp.webview.goBack();
		});
	</script>
</body>
</html>