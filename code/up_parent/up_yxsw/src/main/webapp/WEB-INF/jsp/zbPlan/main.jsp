<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>厂巡排班</title>
<!-- 框架分离模式start -->
<link rel="stylesheet" type="text/css" href="${path }/libs/css/import_basic.css"/>
<link rel="stylesheet" type="text/css" id="skin" prePath="${path }/" splitMode="true" href="${path }/libs/skins/blue/style.css"/>
<link rel="stylesheet" type="text/css" id="customSkin" href="${path }/system/layout/skin/style.css"/>
<script type="text/javascript" src="${path }/libs/js/jquery.js"></script>
<script type="text/javascript" src="${path }/libs/js/language/cn.js"></script>
<script type="text/javascript" src="${path }/libs/js/framework.js"></script>
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
<!-- 树组件start -->
<script type="text/javascript" src="${path}/libs/js/tree/ztree/ztree.js"></script>
<link type="text/css" rel="stylesheet" href="${path}/libs/js/tree/ztree/ztree.css"></link>
<!-- 树形下拉框start -->
<script type="text/javascript" src="${path }/libs/js/form/selectTree.js"></script>
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
.selectbox{width:180px !important;}
</style>
</head>
<body>
	<div id="layout1">
		<div id="centerCon" position="center" style="">
        	<div class="box2" panelTitle="查询" showStatus="false">
			<form action="${path}/zbPlan/getZbPlanData" id="queryForm" method="post">
				<table align="center" style="width:100%;">
					<tr>
						<td  style="width:15%;text-align:right">排班日期：</td>
						<td  style="width:20%;text-align:left"><input type="text" class="date" value="${zbDate }" name="zbDate" id="zbDate"  style="width:200px;"  readonly="readonly"  onFocus="WdatePicker({dateFmt: 'yyyy-MM-dd',isShowClear:true})" /></td>
						<td  style="width:15%;text-align:right">所属单位：</td>
						<td  style="width:20%;text-align:left">
							<select labelField="orgname" valueField="orgid" selWidth="200" name="org" id="org" prompt="请选择" selectedValue="${org }" url="${path}/zbPlan/getOrgTree" ></select>
						</td>
						<td >
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
	var csOrgType = '${csOrgType }';
	//框架初始化函数
	function initComplete(){
		var params = [
// 						{name:"zbFzrId",value:"${zbFzrId }"},
						{name:"zbDate",value:"${zbDate }"},
						{name:"org",value:"${org }"}
			]
		//初始化grid组件
		initGrid(params);
	}
	
	//初始化grid组件
	function initGrid(params){
		grid = $("#dataBasic").quiGrid({
			columns:[	
					    { display: '排班日期', name: 'zb_date', align: 'center', width: "20%",type:"time"},
// 					    { display: '排班负责人', name: 'zb_fzr_mc', align: 'center', width: "10%"},
					    { display: '值班开始日期', name: 'start_time', align: 'center', width: "25%",type:"time"},
					    { display: '值班结束日期', name: 'end_time', align: 'center', width: "25%",type:"time"},
					    { display: '是否生效', name: 'valid_flag', align: 'center', width: "10%",type:"valid"},
					    { display: '所属单位', name: 'belong_wsc_name', align: 'center', width: "15%"},
					    { display: '操作', isAllowHide: false, align: 'center', width:"5%",
					    	render: function (rowdata, rowindex, value, column){
								 var html ='<div class="padding_top4 padding_left5">'
	                               	 + '<span class="img_list hand" title="查看" onclick="onView(\'' + rowdata.zb_plan_id + '\')"></span>';
	                               	 if(rowdata.valid_flag == "0" ){
	                               		 
	                               		html += '<span class="img_edit hand" title="修改" onclick=editZbPlan(\''+rowdata.zb_plan_id+'\')></span>';
	                               	 }
	                                 if(rowdata.valid_flag == "0"){
	                                 	html += '<span class="img_delete hand" title="删除" onclick=delZbPlan(\''+rowdata.zb_plan_id+'\',\''+rowdata.update_timestemp+'\')></span>';
	                                 }
	                               	 html += '</div>';
	                               	
	                               	 return html;
			                 }
					    }
		    		],
		    url: '${path}/zbPlan/getZbPlanData',rownumbers:true,checkbox:true,selectRowButtonOnly:false,params:params,
		    height: '100%', width:"100%", pageSize:20, percentWidthMode:true,		 
		    toolbar:{
		           	 items:[
		           	        {text: '新增', click: addZbPlan, iconClass: 'icon_add'},
		           	        {line  : true }
						   ]
		   }
		});
	}
	
	jQuery.quiDefaults.Grid.formatters['valid'] = function (value, column) {
		var valid = "";
		if(value == "0"){
			valid = "否";
		}else{
			valid = "是";
		}
		return valid;
	}
	//格式化时间
	jQuery.quiDefaults.Grid.formatters['time'] = function (value, column) {
		var time = timeFormatting(value);
		return time;
	}
	//格式化时间
	function timeFormatting(value){
		var time = "";
		if(value != null){
			var year = value.substring(0,4);
			var month =  value.substring(4,6);
			var day = value.substring(6,8);
			var hour = value.substring(8,10);
			var minute = value.substring(10,12);
			var second = value.substring(12,14);
			
			 time = year + "-" + month + "-" + day + " "; 
			 if(null != hour && hour != ""){
				 time = time + hour;
			 }
			 if(null != minute && minute != ""){
				 time = time + ":" + minute;
			 }
			 if(null != second && second != ""){
				 time = time + ":" + second;
			 }
		}
		return time;
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
		$("#zbDate").val("");
		$("#queryForm select").attr("selectedValue","");
		$("#queryForm select").resetValue();
		searchHandler();
		
	}
	
	//查看
	function onView(id){
		var org = jQuery("select[name='org']").val();
		var zbDate = jQuery("#zbDate").val();
		var backURL = "${path}/zbPlan/init?org="+org+"&zbDate="+zbDate;
		location.href = "${path}/zbPlan/detail?zbPlanId="+id+"&backURL="+encodeURIComponent(backURL);
	}
	
	//新增
	function addZbPlan(){
		if(csOrgType != "3"){
			Dialog.alert("非厂站人员不能新增!");
			return;
		}
		location.href = "${path}/zbPlan/toZbPlan";
	}
	
	//删除
	function delZbPlan(id,update) {
		if(csOrgType != "3"){
			Dialog.alert("非厂站人员不能删除!");
			return;
		}
	    Dialog.confirm("确定要删除吗？",function(){
		    	$.ajax({
		            cache: true,
		            type: "POST",
		            url:'${path}/zbPlan/delZbPlan',
		            data:{"id":id,"update":update},
		            async: false,
		            error: function(request) {
		            	Dialog.alert("提交失败");
		            },
		            success: function(responseText, statusText, xhr, $form){
		            	Dialog.alert(responseText.message, function(){
		            		if(responseText.message == "删除成功!"){
		            			refresh();
		            		}
		                });
		            }
		        });
	        });
	}
	
	
	//修改厂商
	function editZbPlan(id){
		if(csOrgType != "3"){
			Dialog.alert("非厂站人员不能修改!");
			return;
		}
		var zbFzrId = jQuery("#zbFzrId").attr("relValue");
		var org = jQuery("select[name='org']").val();
		var zbDate = jQuery("#zbDate").val();
		var backURL = "${path}/zbPlan/init?zbFzrId="+zbFzrId+"&org="+org+"&zbDate="+zbDate;
		location.href = "${path}/zbPlan/toModifyZbPlan?zbPlanId="+id+"&backURL="+encodeURIComponent(backURL);
	}
	
	//刷新表格数据并重置排序和页数
    function refresh(){
    	grid.loadData();
    }
</script>
</body>
</html>