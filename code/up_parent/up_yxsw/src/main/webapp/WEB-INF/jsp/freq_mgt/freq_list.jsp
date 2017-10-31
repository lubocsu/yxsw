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
<script type="text/javascript" src="${path }/js/freq_mgt/freq_list.js"></script>

<script type="text/javascript">

	var path = "${path}";
	//按钮权限
	var isRemoveAble = '${KEY_FOR_IN_MENU_PERMISSION.remove }'== '0' ? false : true; 
	var isAddAble = '${KEY_FOR_IN_MENU_PERMISSION.add }'== '0' ? false : true;   
	var isModifyAble = '${KEY_FOR_IN_MENU_PERMISSION.modify }'== '0' ? false : true; 
	
	var params = [
					{name:"freqSegmentName",value:"${freqSegmentName }"},
					{name:"detailId",value:"${detailId }"},
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
						<td style="width:18%;text-align:right">任务名称：</td>
						<td style="width:16%;text-align:left">
							<input type="text" name="freqSegmentName" id="freqSegmentName" style="width:140px" value="${freqSegmentName}" />
						</td>
						<td style="width:18%;text-align:right">任务班次：</td>
						<td style="width:16%;text-align:left">
							<select labelField="detail_name" valueField="detail_id" selWidth="253" name="detailId" id="detailId" class="validate[required]" selectedvalue="${detailId }"  prompt="请选择" url="${path}/workPeriod/getWpDetail"></select>
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
</body>
</html>