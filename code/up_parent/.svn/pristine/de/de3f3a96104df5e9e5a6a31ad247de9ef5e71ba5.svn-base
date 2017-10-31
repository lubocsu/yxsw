<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt"%>
<c:set var="path" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>设备类型详情</title>
<link href="${path }/css/core.css" rel="stylesheet" type="text/css"/>
<script src="${path }/js/jquery-1.7.2.min.js" type="text/javascript"></script>
<link rel="stylesheet" type="text/css" href="${path }/libs/css/import_basic.css"/>
<link rel="stylesheet" type="text/css" id="skin" prePath="${path }/" splitMode="true" href="${path }/libs/skins/blue/style.css"/>
<link rel="stylesheet" type="text/css" id="customSkin" href="${path }/system/layout/skin/style.css"/>
<script type="text/javascript" src="${path }/libs/js/jquery.js"></script>
<script type="text/javascript" src="${path }/libs/js/language/cn.js"></script>
<script type="text/javascript" src="${path }/libs/js/framework.js"></script>
<script type="text/javascript" src="${path }/libs/js/main.js"></script>
<script type="text/javascript" src="${path }/libs/js/table/quiGrid.js"></script>
<!--布局控件start-->
<script type="text/javascript" src="${path}/libs/js/nav/layout.js"></script>
<!--数据表格start-->
<script src="${path}/libs/js/table/quiGrid.js" type="text/javascript"></script>
<!-- 表单start -->
<script src="${path}/libs/js/form/form.js" type="text/javascript"></script>
<!--弹窗组件start-->
<script type="text/javascript" src="${path}/libs/js/popup/drag.js"></script>
<script type="text/javascript" src="${path}/libs/js/popup/dialog.js"></script>
<!-- 日期选择框start -->
<script type="text/javascript" src="${path}/libs/js/form/datePicker/WdatePicker.js"></script>
<!-- 树形下拉框start -->
<script type="text/javascript" src="${path }/libs/js/form/selectTree.js"></script>

<script type="text/javascript" src="${path }/js/dateutil.js"></script>
<script type="text/javascript">
	jQuery(function(){
		jQuery(".to_hide_over_content").on("click",function(){
			var tipicon = jQuery.trim(jQuery(this).find("span:eq(2)").text());
			if(tipicon=="∨"){
				
				jQuery(this).find("span:eq(0)").text("展开");
				jQuery(this).find("span:eq(2)").text("∧");
				jQuery(this).next(".detail-list2").hide();
			}else{
				jQuery(this).find("span:eq(0)").text("隐藏");
				jQuery(this).find("span:eq(2)").text("∨");
				jQuery(this).next(".detail-list2").show();
			}
		});
	});
</script>
<style type="text/css">
	#ssInfoDetailTable tr td{
		vertical-align: top;
		padding-top: 7px;
	}
</style>
</head>
<body style="overflow: hidden;">
	<div class="details-max">
		<div class="list-content">
    	 <div class="details-container">
         	<div class="task-detail">
            	 <span class="detail-title">
                	 设施详情
                 </span>
                 <div class="to_hide_over_content" class="text">
          			<p><span>隐藏</span><span>&nbsp;</span><span>∨</span></p>
        		 </div> 
                 <table class="detail-list detail-list2" id="ssInfoDetailTable">
                 	<tr>
		                 		<th>父类设施：</th>
		                 		<td>${parentName }</td>
		                 		<th>设施名称：</th>
		                 		<td>${ssItem.name }</td>
		                 	</tr>
		                 	<tr>
		                 		<th>设施编号：</th>
		                 		<td>${ssItem.code }</td>
		                 		<th>层级关系：</th>
		                 		<td>${layerName }</td>
		                 	</tr>
		                 	<tr>
		                 		<th>功能说明：</th>
		                 		<td>${ssItem.function }</td>
		                 		<th>是否扫描二维码：</th>
		                 		<td>${byzdName }</td>
		                 	</tr>
		                 	<tr>
								<th>备注：</th>
								<td colspan="3">${ssItem.remark  }</td>
							</tr>
                 </table>
            </div>
              <div class="task-detail">
            	 <span class="detail-title">设施巡检记录</span>
                 <div class="to_hide_over_content" class="text">
          			<p><span>隐藏</span><span>&nbsp;</span><span>∨</span></p>
        		 </div> 
                 <div id="dataBasic" class="detail-list2" style="float: left;" ></div>
            </div>
         </div>
		</div>
    </div>
	
	 
	<script type="text/javascript">
	
	var grid = null;
	//框架初始化函数
	function initComplete(){
		//初始化grid组件
		initGrid();
	}
	
	//初始化grid组件 暂时不判断用户类型
	function initGrid(){
		grid = $("#dataBasic").quiGrid({
			columns:[	
					   { display: '任务日期', name: 'cx_task_date', align: 'center',type:"taskdate", width: "15%", showTitle:true},
					   { display: '巡检人员', name: 'task_person', align: 'center', width:"10%", showTitle:true},
					   { display: '巡检点名称', name: 'xjd_item_name', align: 'center', width:"10%", showTitle:true},
					   { display: '完成时间', name: 'opt_time',type:"finishdate",   align: 'center', width: "15%", showTitle:true},
					   { display: '是否有问题', name: 'sf_fault',type:"havequestion",   align: 'center', width: "10%", showTitle:true},
					   { display: '班组长确认是否有问题', name: 'bzz_sf_fault',type:"bzz",   align: 'center', width: "15%", showTitle:true},
					   { display: '生技科确认是否有问题', name: 'sjk_sf_fault',type:"sjk",   align: 'center', width: "15%", showTitle:true},
					   { display: '班组长', name: 'bzz_name',align: 'center', width: "10%", showTitle:true},
					   { display: '操作',	isAllowHide: false, align: 'center', width:80,
			                 render:function(rowdata,rowindex,value,column){
			                	 var html='<div class="padding_top4 padding_left5">'
			                		 + '<span class="img_list hand" title="查看" onclick="onView(\'' + rowdata.ttask_item_sbss_id + '\')"></span>';
			                		 return html;
			                	 
			                 } 
			            }
		    		],
		    url: '${path}/eqInfo/toWatchDetailSs?sbId='+'${ssItem.ssId}',
		    rownumbers:true,
		    height: '95%',
		    width:"100%",
		    pageSize:20,
		    percentWidthMode:true 

		});
	}
	
	 //对任务日期的格式化
	  $.quiDefaults.Grid.formatters['taskdate'] = function (value, column) {
			var str=value;
			return str.substring(0,4)+"-"+str.substring(4,6)+"-"+str.substring(6,8);
 	 } 
	 //对完成时间格式化
	  $.quiDefaults.Grid.formatters['finishdate'] = function (value, column) {
		    var str=value;
		    return str.substring(0,4)+"-"+str.substring(4,6)+"-"+str.substring(6,8)+" "+str.substring(8,10)+":"+str.substring(10,12)+":"+str.substring(12,14);
   	 } 
	
	$.quiDefaults.Grid.formatters['havequestion'] = function (value, column) {
		if(value=="1"){	
		return "是"	;
		}else if (value=="0"){		
		return "否";
		}else{
			
			return;
		}
	} 
	$.quiDefaults.Grid.formatters['bzz'] = function (value, column) {
		if(value=="1"){	
		return "是"	;
		}else if (value=="0"){		
		return "否";
		}else{
			
			return ;
		}
	} 
	$.quiDefaults.Grid.formatters['sjk'] = function (value, column) {
		if(value=="1"){	
		return "是"	;
		}else if (value=="0"){		
		return "否";
		}else{
			
			return ;
		}
	}  
	
	//查看
	function onView(sbSsId){
		var url="${path}/patrollingRecords/toViewCheckInfo?ttaskItemSbssId="+sbSsId;
		window.open(url);
	}
</script>
</body>

</html>