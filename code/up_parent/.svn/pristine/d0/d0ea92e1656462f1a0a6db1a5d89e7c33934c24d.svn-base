<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>设备厂商管理</title>
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
</style>
</head>
<body>
	<div id="layout1">
		<div id="centerCon" position="center" style="">
        	<div class="box2" panelTitle="查询" showStatus="false">
			<form action="${path}/eqFactory/getEqFactoryData" id="queryForm" method="post">
				<table align="center" style="width:100%;">
					<tr>
						<td  style="width:15%;text-align:right">厂商名称：</td>
						<td  style="width:20%;text-align:left"><input type="text" id="factoryName" name="factoryName"  value="${name }"  style="width:200px;"/></td>
						<td  style="width:15%;text-align:right">厂商类型：</td>
						<td  style="width:20%;text-align:left"><select labelField="value" valueField="key" selWidth="200" name="factoryType" prompt="请选择" selectedValue="${factoryType }" url="${path}/platform/getSupportData" params='{"parentNodeId":"${factory_Type }"}'></select></td>
						
					</tr>
					<tr>
						<td  style="width:15%;text-align:right">厂商性质：</td>
						<td  style="width:20%;text-align:left"><select labelField="value" valueField="key" selWidth="200" name="factoryXz" prompt="请选择" selectedValue="${factoryXz }" url="${path}/platform/getSupportData" params='{"parentNodeId":"${factory_Xz }"}'></select></td>
						<td  style="width:15%;text-align:right">是否停用：</td>
						<td  style="width:20%;text-align:left"><select labelField="value" valueField="key" selWidth="200" name="outService" prompt="请选择" selectedValue="${outService }" url="${path}/platform/getSupportData" params='{"parentNodeId":"${out_Service }"}'></select></td>
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
	
	//框架初始化函数
	function initComplete(){
		var params = [
						{name:"factoryName",value:"${name }"},
						{name:"factoryType",value:"${factoryType }"},
						{name:"outService",value:"${outService }"},
						{name:"factoryXz",value:"${factoryXz }"}
			]
		//初始化grid组件
		initGrid(params);
	}
	
	//初始化grid组件
	function initGrid(params){
		grid = $("#dataBasic").quiGrid({
			columns:[	
					    { display: '厂商名称', name: 'name', align: 'left', width: "20%", 
					    	render: function (rowdata, rowindex, value, column){
				    			var html = "<a href='javascript:;' onclick=onView('" + rowdata.id + "');>" + value + "</a>";
				    			return html;
		                 }
					    },
					    { display: '厂商类型', name: 'typename', align: 'center', width: "10%"},
					    { display: '厂商性质', name: 'xzname', align: 'center', width: "5%"},
					    { display: '厂商地址', name: 'address', align: 'left', width: "35%", showTitle:true },
					    { display: '主要联系人', name: 'contact_people', align: 'center', width: "10%"},
					    { display: '联系人手机号', name: 'contact_phone', align: 'center', width: "10%"},
					    { display: '是否停用', name: 'outservicename', align: 'center', width: "5%"},
					    { display: '操作', isAllowHide: false, align: 'center', width:"5%",
					    	render: function (rowdata, rowindex, value, column){
								 var html ='<div class="padding_top4 padding_left5">'
	                               	 + '<span class="img_list hand" title="查看" onclick="onView(\'' + rowdata.id + '\')"></span>'
	                                 + '<span class="img_edit hand" title="修改" onclick=editFactory(\''+rowdata.id+'\')></span>'
	                               	 + '</div>';
	                               	
	                               	 return html;
			                 }
					    }
		    		],
		    url: '${path}/eqFactory/getEqFactoryData',rownumbers:true,checkbox:true,selectRowButtonOnly:false,params:params,
		    height: '100%', width:"100%", pageSize:20, percentWidthMode:true,		 
		    toolbar:{
		           	 items:[
		           	        {text: '新增', click: addFactory, iconClass: 'icon_add'},
		           	        {line  : true },
		           	     	{text: '停用', click: stopFactory, iconClass: 'icon_delete'},
		           	        {line  : true }
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
		$("#factoryName").val("");
		$("#queryForm select").attr("selectedValue","");
		$("#queryForm select").resetValue();
		searchHandler();
		
	}
	
	//查看
	function onView(id){
		var factoryType = jQuery("select[name='factoryType']").val();
		var factoryXz = jQuery("select[name='factoryXz']").val();
		var outService = jQuery("select[name='outService']").val();
		var factoryName = jQuery("#factoryName").val();
		var backURL = "${path}/eqFactory/init?factoryType="+factoryType+"&factoryXz="+factoryXz+"&outService="+outService
				+"&factoryName="+factoryName;
		location.href = "${path}/eqFactory/factoryDetail?id="+id+"&backURL="+encodeURIComponent(backURL);
	}
	
	//新增设备厂商
	function addFactory(){
		location.href = "${path}/eqFactory/toAddFactory";
	}
	
	//单个或者批量删除
	function stopFactory() {

	    var rows = grid.getSelectedRows();
	    var rowsLength = rows.length;
	    var ids = "";
	    var flag = true;
	    if(rowsLength == 0) {
	        Dialog.alert("请选中要停用的设备厂商!");
	        return;
	    }
	    for(var i = 0 ; i < rowsLength ; i++ ){
	    	ids += ( rows[i].id + "," );
	    	outservice = rows[i].out_service;
	    	if(outservice == "1"){
	    		flag = false;
	    		break;
	    	}
	    }
	    if(flag){
		    Dialog.confirm("确定要停用吗？",function(){
		            $.post("${path}/eqFactory/stopFactory",
		                    //获取所有选中行
		                    { "ids" : ids.substring(0,ids.length - 1)},
		                    function(result){
		                        handleResult(result.status);
		                    },
		                    "json");
		        });
	    }else{
	    	Dialog.alert("选中的设备厂商已经被停用!");
	    }
	}
	
	//标记、删除后的提示
	function handleResult(result){
			Dialog.alert(result,null,null,null,5);
			grid.loadData();
	}
	
	//修改厂商
	function editFactory(id){
		var factoryType = jQuery("select[name='factoryType']").val();
		var factoryXz = jQuery("select[name='factoryXz']").val();
		var outService = jQuery("select[name='outService']").val();
		var factoryName = jQuery("#factoryName").val();
		var backURL = "${path}/eqFactory/init?factoryType="+factoryType+"&factoryXz="+factoryXz+"&outService="+outService
				+"&factoryName="+factoryName;
		location.href = "${path}/eqFactory/toModifyFactory?id="+id+"&backURL="+encodeURIComponent(backURL);
	}
	
	//刷新表格数据并重置排序和页数
    function refresh(){
    	grid.loadData();
    }
</script>
</body>
</html>