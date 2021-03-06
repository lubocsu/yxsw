<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>作业票填报</title>
<%@include file="/system/common/header_splitmode.jsp" %>
<!--数据表格start-->
<script src="${path}/libs/js/table/quiGrid.js" type="text/javascript"></script>
<!-- 表单start -->
<script src="${path}/libs/js/form/form.js" type="text/javascript"></script>
<script type="text/javascript" src="${path}/libs/js/form/datePicker/WdatePicker.js"></script>
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
			<form action="#" id="queryForm" method="post">
			<table style="width:100%">
				<tr>
					<td style="width:18%;text-align:right">作业票日期：</td>
					<td style="width:16%;text-align:left">
						<input type="text" class="date" value="${zyp.zypDate }" name="zypDate" id="zypDate" readonly="readonly" onFocus="WdatePicker({isShowClear:true,dateFmt:'yyyy-MM-dd'})" />
					</td>
					<td style="width:12%;text-align:right">作业票编号：</td>
					<td style="width:18%;text-align:left">
						<input type="text" value="${zyp.zypCode }" id="zypCode" name="zypCode" />
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
	function initComplete(){
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
									 +'<span class="img_list hand" title="详情" onclick="toView(\'' + rowdata.cx_make_id + '\')"></span>'
	                               	 +'<span class="img_edit hand" title="填报" onclick="toModify(\'' + rowdata.cx_make_id + '\')"></span></div>';
	   		                 	     return html;
			                 }
					    }
		    		],
		    url: '${path}/writeCxMake/getCxMakeList',params:params, rownumbers:true, checkbox:true, selectRowButtonOnly:false, height: '100%', width:"100%",
		    pageSize:20, percentWidthMode:true
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
	
	function toView(cxMakeId){
		// 跳转到作业票详情和流程处理页面，并记住当前查询参数
		var param = {};
		param.zypDate = jQuery("#zypDate").val();
		param.zypCode = jQuery("#zypCode").val();
		var backURL = encodeURIComponent("/writeCxMake/init");
		window.location = path+"/cxmake/detailAndProcess/init?cxMakeId="+cxMakeId+"&backURL="+backURL+"&queryParam="+JSON.stringify(param);
	}

	function toModify(cxMakeId){
		if(("${onlyQuery}"=="true")){
			Dialog.alert("非厂站用户不能进行作业票填报！")
			return;
		}
		// 跳转到新增页面，并记住当前查询参数
		var param = {};
		param.zypDate = jQuery("#zypDate").val();
		param.zypCode = jQuery("#zypCode").val();
		var backURL = encodeURIComponent("/writeCxMake/init");
		window.location = path+"/writeCxMake/toModify?cxMakeId="+cxMakeId+"&backURL="+backURL+"&queryParam="+JSON.stringify(param);
	}
	
	function handleResult(result){
			Dialog.close();
			if(result.flag){
				Dialog.alert(result.message,null,null,null,3);	
			}
			grid.loadData();
	}
	
</script>
</body>
</html>