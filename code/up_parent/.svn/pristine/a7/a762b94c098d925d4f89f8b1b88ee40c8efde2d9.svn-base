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
			<div class="padding_right5">
				<div id="dataBasicp"></div>
			</div>
        </div>
	</div>
<script type="text/javascript">
	
	var grid = null;
	var diag = new parent.Dialog();
	
	//框架初始化函数
	function initComplete(){
		//初始化grid组件
		initGrid();
	}
	
	//初始化grid组件
	function initGrid(){
		grid = $("#dataBasicp").quiGrid({
			columns:[	
					    { display: '安全提醒标题', name: 'title', align: 'left', width: "30%",showTitle:true},
					    { display: '安全提醒内容', name: 'content', align: 'center', width: "60%",showTitle:true},
					    { display: '是否重要提醒', name: 'is_important', align: 'center', width: "10%"},
		    		],
		    url: '${path}/spotRelate/getRelatedWData?spotId=${spotId }',rownumbers:true,checkbox:true,selectRowButtonOnly:false,
		    height: '100%', width:"100%", pageSize:20, percentWidthMode:true,		 
		    toolbar:{
		           	 items:[
		           	        {text: '新增', click: addWarning, iconClass: 'icon_add'},
		           	        {line  : true },
		           	     	{text: '删除', click: delEquipment, iconClass: 'icon_delete'},
		           	        {line  : true }
						   ]
		   }
		});
	}
	
	//新增安全定义
	function addWarning(){
		var csType = "${csType }";
		if(csType != "3"){
			parent.Dialog.alert("非厂站用户不能新增！");
		}else{
// 			var diag = new Dialog();
			diag.Title = "新增安全提醒定义";
			diag.URL = "${path }/spotRelate/toAddWar?spotId=${spotId }";
			diag.Width = 900;
			diag.Height = 500;
			//设置保存按钮文字（默认是确定）
			diag.OkButtonText = "确定";
			diag.OKEvent = function(){
				//调用子页面submitForm 方法
				diag.innerFrame.contentWindow.addWar(refresh);
			};
			diag.show();
		}
	}
	
	//单个或者批量删除
	function delEquipment() {
		var csType = "${csType }";
		if(csType != "3"){
			parent.Dialog.alert("非厂站用户不能新增！");
		}else{
		    var rows = grid.getSelectedRows();
		    var rowsLength = rows.length;
		    var ids = "";
		    if(rowsLength == 0) {
		    	parent.Dialog.alert("请选中要删除的安全定义!");
		        return;
		    }
		    for(var i = 0 ; i < rowsLength ; i++ ){
		    	ids += ( rows[i].attach_base_id + "," );
		    }
		    parent.Dialog.confirm("确定要删除吗？",function(){
		    	
			    $.ajax({
		            cache: true,
		            type: "POST",
		            url:'${path}/spotRelate/delRelateWar?spotId=${spotId }',
		            data:{"ids":ids},
		            async: false,
		            error: function(request) {
		            	Dialog.alert("提交失败");
		            },
		            success: function(responseText, statusText, xhr, $form){
		            	//关闭窗口
		                grid.loadData();
		                if(responseText.message != true){
		                	refreshTree("${spotId }");
		                }
		            }
		        });
		    });
		}
	}
	
	//标记、删除后的提示
	function handleResult(result){
			parent.Dialog.alert(result,null,null,null,5);
			grid.loadData();
	}
	
	//刷新表格数据并重置排序和页数
    function refresh(){
    	grid.loadData();
    	diag.close();
    	refreshTree("${spotId }");
    }
  //刷新树
    function refreshTree(spotId){
    	parent.refreshTree(spotId);
    }
</script>
</body>
</html>