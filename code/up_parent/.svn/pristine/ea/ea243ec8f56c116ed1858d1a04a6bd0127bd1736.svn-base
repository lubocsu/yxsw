<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>作业票验收</title>
<%@include file="/system/common/header_splitmode.jsp" %>
<!--数据表格start-->
<script src="${path}/libs/js/table/quiGrid.js" type="text/javascript"></script>
<!-- 表单start -->
<script src="${path}/libs/js/form/form.js" type="text/javascript"></script>
<script type="text/javascript" src="${path}/libs/js/form/datePicker/WdatePicker.js"></script>
<!-- 树组件start -->
<script type="text/javascript" src="${path}/libs/js/tree/ztree/ztree.js"></script>
<link type="text/css" rel="stylesheet" href="${path}/libs/js/tree/ztree/ztree.css"></link>
<script type="text/javascript" src="${path }/libs/js/form/selectTree.js"></script>

<script type="text/javascript" src="${path }/js/yxsw-common.js"></script>
<script type="text/javascript" src="${path }/js/dateutil.js"></script>
<script type="text/javascript" src="${path }/js/zyp_temp/zyptemp_list.js"></script>

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
</style>
</head>
<body>
	<div id="layout1">
		<div id="centerCon" position="center" style="">
        	<div class="box2" panelTitle="查询" showStatus="false">
			<form action="${path}/zypReceive/getZypReceiveList" id="queryForm" method="post">
				<table style="width:100%">
					<tr>
						<td style="width:18%;text-align:right">作业票日期：</td>
						<td style="width:16%;text-align:left">
							<input type="text" class="date" value="${zyp.zypDate }" name="zypDate" id="zypDate" readonly="readonly" onFocus="WdatePicker({isShowClear:true,dateFmt:'yyyy-MM-dd'})" />
<%-- 							<input type="text" name="zypDate" id="zypDate" style="width:140px" value="${zyp.zypDate }" /> --%>
						</td>
						<td style="width:18%;text-align:right">作业票编号：</td>
						<td style="width:16%;text-align:left">
							<input type="text" name="zypCode" id="zypCode" style="width:140px" value="${zyp.zypCode }" />
						</td>
						<td rowspan="2" style="text-align:left;" valign="bottom">
							<button type="button" onclick="searchHandler()"><span class="icon_find">查询</span></button>
							<button type="button" onclick="resetSearch()"><span class="icon_reload">重置</span></button>
						</td>
					</tr>
				</table>
			</form>
			</div>
			<div class="padding_right5">
				<div id="dataBasic"></div>
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
	
	//初始化grid组件
	function initGrid(){
		var params = [
		  			{name:"zypDate",value:"${zyp.zypDate }"},
		  			{name:"zypCode",value:"${zyp.zypCode }"}
		  		]
		grid = $("#dataBasic").quiGrid({
			columns:[	
			         { display: '作业票编号', name: 'zyp_code', align: 'center', width:'20%', showTitle:true },
					 { display: '作业票日期', name: 'zyp_date', align: 'center', width: 120, showTitle:true },
					 { display: '负责人', name: 'fzr_name', align: 'center', width: "20%", showTitle:true},
					 { display: '拟定人', name: 'tbry_name', align: 'center', width: '20%', showTitle:true},
					 { display: '作业票状态', name: 'cxzyp_status_name', align: 'center',width: 80, showTitle:true},
					 { display: '说明', name: 'zyp_desc', align: 'center',width: '40%', showTitle:true},
					 { display: '操作', isAllowHide: false, align: 'center', width:80,
					    	render: function (rowdata, rowindex, value, column){
								 var html ='<div class="padding_top4 padding_left5">'
	                               	 + '<span class="img_list hand" title="查看并验收" onclick="detailAndCheck(\'' + rowdata.cx_make_id + '\')"></span>'
	                               	 + '</div>';
	                               	 return html;
			                 }
					    }
		    		],
		    url: '${path}/zypReceive/getZypReceiveList',rownumbers:true,checkbox:false,selectRowButtonOnly:false,params:params,
		    height: '100%', width:"100%", pageSize:20, percentWidthMode:true,		 
		    toolbar:{
		           	 items:[
						   ]
		   }
		});
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
		$("#zypDate").val("");
		$("#zypCode").val("");
		searchHandler();
		
	}
	
	//查看并审核
	function detailAndCheck(cxMakeId){
		var param = {};
		param.zypDate = jQuery("#zypDate").val();
		param.zypCode = jQuery("#zypCode").val();
		var backURL = encodeURIComponent("/zypReceive/init");
		window.location = "${path}/zypReceive/detailAndReceive?cxMakeId="+cxMakeId+"&backURL="+backURL+"&queryParam="+JSON.stringify(param);
	}
	
	//刷新表格数据并重置排序和页数
    function refresh(){
    	grid.loadData();
    }
</script>
</body>
</html>