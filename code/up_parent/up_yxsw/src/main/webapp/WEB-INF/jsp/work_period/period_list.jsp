<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>班次管理</title>
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
<script type="text/javascript" src="${path }/js/work_period/period_list.js"></script>

<script type="text/javascript">

	var path = "${path}";
	//按钮权限
	var isRemoveAble = '${KEY_FOR_IN_MENU_PERMISSION.remove }'== '0' ? false : true; 
	var isAddAble = '${KEY_FOR_IN_MENU_PERMISSION.add }'== '0' ? false : true;   
	var isModifyAble = '${KEY_FOR_IN_MENU_PERMISSION.modify }'== '0' ? false : true; 
	
	var params = [
					{name:"workGroupName",value:"${workGroupName }"},
		];
</script>
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
						<td style="width:18%;text-align:right">班次名称：</td>
						<td style="width:16%;text-align:left">
							<input type="text" name="workGroupName" id="workGroupName" style="width:140px" value="${workGroupName}" />
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
	//初始化grid组件
	//查询
   /*  function searchHandler(){
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
		$("#checkItemName").val("");
		$("#checkItemDesc").val("");
		searchHandler();
	}
	
	function toAdd(){
		// 跳转到新增页面，并记住当前查询参数
		var param = {};
		param.checkItemName = jQuery("#checkItemName").val();
		param.checkItemType = jQuery("#checkItemType").val();
		param.checkItemDesc = jQuery("#checkItemDesc").val();
		param.inputType = jQuery("#inputType").val();
		
		window.location = path+"/checkitem/toAdd?queryParam="+JSON.stringify(param);
	}
	
	function toDelete(){
		var rows = grid.getSelectedRows();
		var rowsLength = rows.length;
		var checkitemIds = new Array();
		if(rowsLength == 0) {
			Dialog.alert("请选中要删除的记录!");
			return;
		}else{
			for(var i = 0;i<rowsLength;i++) {
				checkitemIds.push(rows[i].check_item_id);
			}
		}
		Dialog.confirm("确定要删除所选"+rowsLength+"条检查项？",function(){
		  	$.post(path+"/checkitem/doDel",{"checkitemIds":checkitemIds.join(",")},function(result){
		  			handleResult(result);
				},"json");
				grid.loadData();
			});	
	}
	
	function toView(checkItemId){
		var diag = new Dialog();
		diag.Title = "查看检查项";
		diag.URL = "${path }/checkitem/toView?checkItemId="+checkItemId;
		diag.Height = 420;
		diag.Width = 700;
		diag.show();
	}

	function toModify(checkItemId){
		var param = {};
		param.checkItemName = jQuery("#checkItemName").val();
		param.checkItemType = jQuery("#checkItemType").val();
		param.checkItemDesc = jQuery("#checkItemDesc").val();
		param.inputType = jQuery("#inputType").val();
		
		window.location = path+"/checkitem/toModify?checkItemId="+checkItemId+"&queryParam="+JSON.stringify(param);
	}
	
	function handleResult(result){
			Dialog.close();
			if(result.flag){
				Dialog.alert(result.message,null,null,null,3);	
			}
			grid.loadData();
	}
	 */
</script>
</body>
</html>