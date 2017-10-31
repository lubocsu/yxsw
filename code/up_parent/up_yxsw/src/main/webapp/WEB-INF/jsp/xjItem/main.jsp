<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>巡检点管理</title>
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
			<form action="${path}/xjItem/getXjItemData" id="queryForm" method="post">
				<table align="center" style="width:100%;">
					<tr>
						<td  style="width:15%;text-align:right">巡检点名称：</td>
						<td  style="width:20%;text-align:left"><input type="text" id="xjdItemName" name="xjdItemName"  value=""  style="width:200px;"/></td>
						<td  style="width:15%;text-align:right">巡检点位置：</td>
						<td  style="width:20%;text-align:left"><input type="text" id="xjdItemAddress" name="xjdItemAddress"  value=""  style="width:200px;"/></td>
						
					</tr>
					<tr>
						<td  style="width:15%;text-align:right">RFID编号：</td>
						<td  style="width:20%;text-align:left"><input type="text" id="rfidCode" name="rfidCode"  value=""  style="width:200px;"/></td>
						<td></td>
						<td></td>
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
		//初始化grid组件
		initGrid();
	}
	
	//初始化grid组件
	function initGrid(){
		grid = $("#dataBasic").quiGrid({
			columns:[	
					    { display: '巡检点名称', name: 'xjd_item_name', align: 'left', width: "15%", showTitle:true},
					    { display: '巡检点位置', name: 'xjd_item_address', align: 'left', width: "20%", showTitle:true},
					    { display: '巡检点说明', name: 'xjd_item_desc', align: 'left', width: "50%", showTitle:true},
					    { display: 'RFID编号', name: 'rfid_code', align: 'center', width: "10%" },
					    { display: '操作', isAllowHide: false, align: 'center', width:"5%",
					    	render: function (rowdata, rowindex, value, column){
								 var html ='<div class="padding_top4 padding_left5">'
	                               	 + '<span class="img_list hand" title="查看" onclick="onView(\'' + rowdata.xjd_item_id + '\')"></span>'
	                                 + '<span class="img_edit hand" title="修改" onclick=modifyXjItem(\''+rowdata.xjd_item_id+'\')></span>'
	                               	 + '</div>';
	                               	
	                               	 return html;
			                 }
					    }
		    		],
		    url: '${path}/xjItem/getXjItemData',rownumbers:true,checkbox:true,selectRowButtonOnly:false,
		    height: '100%', width:"100%", pageSize:20, percentWidthMode:true,		 
		    toolbar:{
		           	 items:[
		           	        {text: '新增', click: addXjItem, iconClass: 'icon_add'},
		           	        {line  : true },
		           	     	{text: '删除', click: stopFactory, iconClass: 'icon_delete'},
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
		$("#xjdItemName").val("");
		$("#xjdItemAddress").val("");
		$("#rfidCode").val("");
		searchHandler();
		
	}
	
	//查看
	function onView(id){
		$.ajaxSettings.async = false;
		Dialog.open({
			URL:"${path}/xjItem/xjItemDetail?xjItemId=" + id,
			Title:"查看详情",Width:700,Height:500});
		//刷新表格数据 
		grid.loadData();
	}
	
	//新增巡检点
	function addXjItem(){
		
		var diag = new Dialog();
		diag.Title = "新增巡检点";
		diag.URL = "${path }/xjItem/toAddXjItem";
		diag.Width = 400;
		diag.Height = 260;
		//设置保存按钮文字（默认是确定）
		diag.OkButtonText = "确定";
		diag.OKEvent = function(){
			//调用子页面submitForm 方法
			diag.innerFrame.contentWindow.submitForm();
		};
		diag.show();
	}
	
	//单个或者批量删除
	function stopFactory() {

	    var rows = grid.getSelectedRows();
	    var rowsLength = rows.length;
	    if(rowsLength == 0) {
	        Dialog.alert("请选中要删除的巡检点!");
	        return;
	    }
	    if(rowsLength > 1){
	    	 Dialog.alert("一次只能删除一个巡检点!");
		     return;
	    }
	    var param = {"id":rows[0].xjd_item_id};
		$.ajax({
            cache: true,
            type: "POST",
            url:'${path}/xjItem/checkItem',
            data:param,
            async: false,
            error: function(request) {
            	Dialog.alert("提交失败");
            },
            success: function(responseText, statusText, xhr, $form){
            	if(responseText.result.valid == true){
            		
            	    Dialog.confirm("确定要删除吗？",function(){
        	            $.post("${path}/xjItem/delXjItem",
        	                    //获取所有选中行
        	                    { "id" : rows[0].xjd_item_id},
        	                    function(result){
        	                        handleResult(result.message);
        	                    },
        	                    "json");
        	        });
            	}else{
	            	Dialog.alert(responseText.msg.message);
            	}
            }
        });
	}
	
	//标记、删除后的提示
	function handleResult(result){
			Dialog.alert(result,null,null,null,5);
			grid.loadData();
	}
	
	//修改巡检点
	function modifyXjItem(id){
		var diag = new Dialog();
		diag.Title = "修改巡检点";
		diag.URL = "${path }/xjItem/toModifyXjItem?xjItemId="+id;
		diag.Width = 400;
		diag.Height = 260;
		//设置保存按钮文字（默认是确定）
		diag.OkButtonText = "确定";
		diag.OKEvent = function(){
			//调用子页面submitForm 方法
			diag.innerFrame.contentWindow.submitForm();
		};
		diag.show();
	}
	
	//刷新表格数据并重置排序和页数
    function refresh(){
    	grid.loadData();
    }
</script>
</body>
</html>