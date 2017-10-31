<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>指标项主页</title>
<!-- 框架分离模式start -->
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

.ex-icon{
	width:1.2rem;
	height:1.2rem;
	text-align:center;
	line-height:1.2rem;
	border-radius:50%;
	background:#f00;
	color:#fff;
	border:solid 1px #f00;
	font-size:0.7rem;
	top:0;
	/* right:0.2rem; */
}
</style>
</head>
<body>

	<div id="layout1">
		<div id="centerCon" position="center" style="">
        	
			<div class="padding_right5">
				<div id="dataBasic" style="float: left"></div>
			</div>
        </div>
	</div>
<script type="text/javascript">
	
	var grid = null;
	var trueOrFalse=true
	console.log('${type}')
	console.log('${nowId}')
	  if('${type}'<=2){
		
		trueOrFalse=false
	 
	}else{
		trueOrFalse=true
			
	} 
	 
	//框架初始化函数
	function initComplete(){
		//初始化grid组件
		
		var params = [
						{name:"cxzb_name",value:"${cxzb_name }"},
						{name:"cxzb_tblx",value:"${cxzb_tblx }"},
						{name:"cxzb_unit",value:"${cxzb_unit }"},
						{name:"reportTimeEnd",value:"${reportTimeEnd }"},
						{name:"cxzb_code",value:"${cxzb_code}"}
				]
		initGrid(params);
	}
	
	//初始化grid组件 暂时不判断用户类型
	function initGrid(params){
		grid = $("#dataBasic").quiGrid({
			columns:[	
					    { display: '任务日期', name: 'cx_task_date', align: 'center',type:"taskdate", width: "15%", showTitle:true,
					    	
					    },
					    { display: '巡检人员', name: 'task_person', align: 'center', width:"10%", showTitle:true},
					    { display: '巡检点名称', name: 'xjd_item_name', type:"jldw"  , align: 'center', width:"10%", showTitle:true},
					    { display: '完成时间', name: 'opt_time',type:"finishdate",   align: 'center', width: "15%", showTitle:true},
					    { display: '是否有问题', name: 'sf_fault',type:"havequestion",   align: 'center', width: "10%", showTitle:true},
					    { display: '班组长确认是否有问题', name: 'bzz_sf_fault',type:"bzz",   align: 'center', width: "15%", showTitle:true},
					    { display: '生技科确认是否有问题', name: 'sjk_sf_fault',type:"sjk",   align: 'center', width: "15%", showTitle:true},
					    { display: '班组长', name: 'bzz_name',type:"",   align: 'center', width: "10%", showTitle:true},
				
					    { display: '操作',	isAllowHide: false, align: 'center', width:80,
							
			                 
			                 
			                 render:function(rowdata,rowindex,value,column){
			                	 var html='<div class="padding_top4 padding_left5">'
			                		 + '<span class="img_list hand" title="查看" onclick="onView(\'' + rowdata.ttask_item_sbss_id + '\')"></span>';
			                		 return html;
			                	 
			                 } 
			            }
					  
					  
					   
		    		],
		    url: '${path}/eqInfo/toWatchDetailSs?sbId='+'${sbId}',
		    params:params,
		    rownumbers:true,
		    checkbox:true,
		    selectRowButtonOnly:false,
		    height: '100%',
		    width:"100%",
		    pageSize:20,
		    percentWidthMode:true,		 

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

	
	 //对类型格式化
     $.quiDefaults.Grid.formatters['sborss'] = function (value, column) {
		if(value=="1"){	
		return "设备"	;
		}else if (value=="2"){		
		return "设施";
		}else{
			
			return ;
		}
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
	
	//查询
    function searchHandler(){
    	var query = $("#queryForm").formToArray(); 
		grid.setOptions({ params : query});
		//页号重置为1
		grid.setNewPage(1);
		//刷新表格数据 
		grid.loadData();
    }
	
	//重置查询
	function resetSearch(){
		$("#queryForm select").attr("selectedValue","");
		$("#queryForm select").resetValue();
		$("#startDate").val("");
		$("#endDate").val("");
		$("#xjd_name").val("");
		$("#sborss_name").val("");
		$("#task_code").val("");
		$("#task_type").val("");
		searchHandler();
		
	}
	
	//查看
	function onView(sbSsId){
	var url="${path}/patrollingRecords/toViewCheckInfo?ttaskItemSbssId="+sbSsId;
		window.open(url);
	
	}

	
	//刷新表格数据并重置排序和页数
	function refresh(isUpdate){
		if(!isUpdate){
			//重置排序
	    	grid.options.sortName='uid';
	    	grid.options.sortOrder="desc";
	    	//页号重置为1
			grid.setNewPage(1);
		}
		grid.loadData();
	}

</script>
<!-- <script type="text/javascript">
 function back(){
	 
	 
	 location.href='${path}'+"/eqInfo/init";
 }
</script> -->
</body>
</html>