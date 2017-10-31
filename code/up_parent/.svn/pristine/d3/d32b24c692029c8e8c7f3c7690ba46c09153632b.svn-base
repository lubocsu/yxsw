<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="path" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>角色管理</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!--框架必需start-->
<link href="${path }/libs/css/import_basic.css" rel="stylesheet" type="text/css"/>
<link rel="stylesheet" type="text/css" id="skin" prePath="${path }/" scrollerY="false"/>
<link rel="stylesheet" type="text/css" id="customSkin"/>
<script type="text/javascript" src="${path }/libs/js/jquery.js"></script>
<script type="text/javascript" src="${path }/libs/js/language/cn.js"></script>
<script type="text/javascript" src="${path }/libs/js/main.js"></script>
<!--框架必需end-->

<!--分离模式框架必需start-->
<script type="text/javascript" src="${path }/libs/js/framework.js"></script>
<%-- <link rel="stylesheet" type="text/css" id="customSkin" href="${path }/plug/qui/system/layout/skin/style.css"/> --%>
<!--分离模式框架必需end-->

<!--树组件start -->
<link href="${path }/libs/js/tree/ztree/ztree.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="${path }/libs/js/tree/ztree/ztree.js"></script>
<!--树组件end -->

<!--分离模式-弹窗组件start-->
<script type="text/javascript" src="${path }/libs/js/popup/drag.js"></script>
<script type="text/javascript" src="${path }/libs/js/popup/dialog.js"></script>
<!--分离模式-弹窗组件end-->

<!--布局控件start-->
<script type="text/javascript" src="${path }/libs/js/nav/layout.js"></script>
<!--布局控件end-->

<!--数据表格start-->
<script src="${path }/libs/js/table/quiGrid.js" type="text/javascript"></script>
<!--数据表格end-->
<!-- 表单start -->
<script src="${path}/libs/js/form/form.js" type="text/javascript"></script>
<!-- 表单end -->
<style type="text/css">

.permissionConfig {
	background-image: url(${path }/image/permissionConfig.png);
    width: 16px;
    height: 16px;
    display:inline-block;
}
.permissionData {
	background-image: url(${path }/image/permissionConfig.png);
    width: 16px;
    height: 16px;
    display:inline-block;
}
.roleConfig {
	background-image: url(${path }/image/roleConfig.png);
    width: 16px;
    height: 16px;
    display:inline-block;
}

/* 修正自动断行问题 */
.l-grid-row-cell-inner {white-space: nowrap;}
</style>
</head>
<body>
<div id="publicCon">
        <div id="leftCon" position="left" style="" panelTitle="系统目录">
        	<div class="orgTreeContainer">
        		<ul id="tree" class="ztree"></ul>
        	</div>
        </div>
        <div id="centerCon" position="center" style="">
        	<div class="box2" panelTitle="查询" showStatus="false">
        	<form action="#" id="queryForm" method="post">
			<input type="hidden" id="systemCode" name="systemCode" value=""/>
			<input type="hidden" id="systemName" name="systemName" value=""/>
			<table>
				<tr>
					<td>角色名称：</td>
					<td>
						<input type="text" id="searchInput" name="roleName"/>
					</td>
					<td><button type="button" onclick="searchHandler();"><span class="icon_find">查询</span></button></td>
					<td><button type="button" onclick="resetSearch()"><span class="icon_reload">重置查询</span></button></td>
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
	 //设定不可编辑的节点id
    var noeditArray = ["1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19"];
	//定义grid
	var grid = null;
	var treeObj=null;
	//定义选中的树节点
	var selectTreeNode = null;
	
	//定义树节点初始数据
	var nodes =[
		{ id:"0", parentId:-1, name:"水务子系统目录", open:true, icon:"${path }/libs/icons/home.gif"}
	];
	
	//树属性配置
	var selectionSetting = {
			view: {
				dblClickExpand: false
			},
			callback: {
				onClick: zTreeSelect
			}
	};
	//初始化函数
	function initComplete(){
		//当提交表单刷新本页面时关闭弹窗
		//Dialog.close();
		
		$("#publicCon").layout({ leftWidth: 180,onEndResize:function(){
			  	grid.resetWidth();
		}}); 
		
		//初始化tree
		initTree();
		
		
		
		//监听查询框的回车事件
		 $("#searchInput").keydown(function(event){
		 	if(event.keyCode==13){
				searchHandler();
			}
		 });
	}
	
	
	//初始化tree处理
	function initTree() {
		nodes = nodes.concat(JSON.parse('${treeMap}'));
		treeObj = $.fn.zTree.init($("#tree"), selectionSetting, nodes);
		var firstCode=nodes[1].id;
		var firstNode = treeObj.getNodesByParam("id", firstCode, null);
		// 默认选中第一个节点（默认后台子系统节点）
		treeObj.selectNode(firstNode[0],false);
		$("#systemCode").val(nodes[1].id);
		$("#systemName").val(nodes[1].name);
		//初始化grid组件
		initGrid();
		
		
		
	}
	var isRemoveAble = '${KEY_FOR_IN_MENU_PERMISSION.remove }'== '0' ? false : true; 
	var isAddAble = '${KEY_FOR_IN_MENU_PERMISSION.add }'== '0' ? false : true;   
	var isModifyAble = '${KEY_FOR_IN_MENU_PERMISSION.modify }'== '0' ? false : true; 
	//初始化Grid处理
	function initGrid() {
		var systemCode=$("#systemCode").val();
		grid = $("#dataBasic").quiGrid({
			columns:[
				{ display: '角色名称',	name: 'rolename',	align: 'left',	width: "20%"},
			    { display: '角色类型',	name: 'roletype',			align: 'left', 	width: "20%",type:"roleType"},
			    { display: '角色定义类型',	name: 'definetype',			align: 'left', 	width: "20%",type:"defineType"},
			    { display: '角色描述',	name: 'roledescription',	showTitle:true	,	align: 'left',  width: "20%"} ,
			    { display: '系统代码',	name: 'systemcode', 	 		align: 'left',  width: "20%"} ,
           		{ display: '操作',	isAllowHide: false, align: 'left', width:100,
						 render: function (rowdata, rowindex, value, column){
							 if(isModifyAble&&rowdata.definetype!='1'){
								 return '<div align="center" class="padding_top4 padding_left5">'
                                 + '  <span class="roleConfig hand" title="角色选择用户" onclick=onViewUser(\''+rowdata.roleid+'\',\''+rowdata.rolename+'\')></span>'
                                 + '  <span class="permissionConfig hand" title="菜单权限分配" onclick=onViewPermission(\''+rowdata.roleid+'\',\''+rowdata.rolename+'\')></span>'
                                 + '  <span class="permissionData hand" title="数据权限分配" onclick=onPermissionData(\''+rowdata.roleid+'\',\''+rowdata.rolename+'\')></span>'
                                 + '  <span class="img_edit hand" title="修改" onclick=modifyRole(\''+rowdata.roleid+'\')></span>'
                             + '</div>';
							 }
		                 }
		            }
			  ],
		 url: '${path }/role/findRoleList', sortName: 'roleName',rownumbers:true,checkbox:true,
         height: '100%', width:"100%", pageSize:10,selectRowButtonOnly:false,percentWidthMode:true,params:[{name:"systemCode",value:systemCode}],
         toolbar:{
        	 items:[
        		  {text: '新增', click: addRole,iconClass: 'icon_add',visible:isAddAble},
        		  { line : true,visible:isAddAble },
        		  {text: '修改', click: modifyRole,iconClass: 'icon_edit',visible:isModifyAble},
        		  { line : true,visible:isModifyAble },
        		  {text: '删除', click: deleteRole,iconClass: 'icon_delete',visible:isRemoveAble},
        		  { line : true,visible:isRemoveAble }
        		 /**
        		  ,
        		  {text: '导入', click: showImportDialog, iconClass: 'icon_import'},
        		  { line : true },
        		  {text: '导出当前页', click: exportPageData, iconClass: 'icon_export'},
        		  { line : true },
        		  {text: '导出全部', click: exportTotalData, iconClass: 'icon_export'}
          		 **/
        		]
         	}
		});
	}
	
	$.quiDefaults.Grid.formatters['roleType'] = function (value, column) {
	    return (value=="1"?"岗位角色":"流程角色");
	};

	$.quiDefaults.Grid.formatters['defineType'] = function (value, column) {
		return (value=="1"?"系统定义":"用户自定义");
	};
	 
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
    	//$("#queryForm")[0].reset();
    	jQuery("#searchInput").val("");
		searchHandler();
    }
    
	//新增
	function addRole() {
		var systemCode = $("#systemCode").val();
		var systemName = $("#systemName").val();
		if(systemCode!=null&&systemCode.length>0&&systemCode!=0){
			var diag = new Dialog();
			diag.Title = "新增角色";
			diag.Width=450;
			diag.Height=180;
			diag.URL = "${path }/role/toAddRole?systemCode="+systemCode+"&systemName="+systemName,
			diag.ShowButtonRow=true;
			diag.OkButtonText=" 确定 ";
			diag.CancelButtonText="取消";
			diag.OKEvent = function(){
				//调用子页面submitForm 方法
				diag.innerFrame.contentWindow.submitForm();
			};
			diag.show();
		}else{
			Dialog.alert("请选择归属子系统!");
			return;
		}
		
	}
	
	function modifyRole(roleId){
		if(roleId==null||""==roleId){
			Dialog.alert("请选择要修改的角色！");
			return;
		}
		var diag = new Dialog();
		diag.Title = "修改角色";
		diag.Width=450;
		diag.Height=150;
		diag.URL = "${path }/role/toModifyRole?roleId="+roleId,
		diag.ShowButtonRow=true;
		diag.OkButtonText="确定";
		diag.CancelButtonText="取消";
		diag.OKEvent = function(){
			//调用子页面submitForm 方法
			diag.innerFrame.contentWindow.submitForm();
		};
		diag.show();
	}
	
	//批量删除
	function deleteRole() {
		var rows = grid.getSelectedRows();
		var rowsLength = rows.length;
		
		if(rowsLength == 0) {
			Dialog.alert("请选中要删除的记录!");
			return;
		}
		var selectedRows = grid.getSelectedRows();
		var selectedRowsLength = selectedRows.length;
		var roleIds = "";
		for(var i = 0;i<selectedRowsLength;i++) {
			if(selectedRows[i].definetype==1){
				Dialog.alert("当前所选记录中包含系统定义类角色，请重新选择！");
				return;
			}
			if(i==0){
				roleIds +="'"+ selectedRows[i].roleid + "'";
			}else{
				roleIds +=",'"+ selectedRows[i].roleid + "'";
			}
			
		}
		Dialog.confirm("确定删除所选的"+rowsLength+"个角色？",function(){
			$.post("${path }/role/removeRole",
					//获取所有选中行
					{"roleIds":roleIds},
					
					function(result){
						handleResult(result);
					},
					"json");
		});
	}
	
	//删除后的提示
	function handleResult(result){
		if(result.message == "删除成功"){
			top.Dialog.alert("删除成功！",null,null,null,1);
			grid.loadData();
		}else{
			Dialog.alert("删除失败！");
		}
	}
	
	//获取所有选中行获取选中行的id 格式为 ids=1&ids=2 
	function getSelectIds(grid) {
		var selectedRows = grid.getSelectedRows();
		var selectedRowsLength = selectedRows.length;
		var ids = "";
		for(var i = 0;i<selectedRowsLength;i++) {
			if(selectedRows[i].definetype==1){
				Dialog.alert("当前所选记录中包含系统定义类角色，请重新选择！");
				ids="";
				return false;
			}
			if(i==0){
				ids +="'"+ selectedRows[i].roleId + "'";
			}else{
				ids +=",'"+ selectedRows[i].roleId + "'";
			}
			
		}
		return ids;
	}
	
	//查看用户
	function onViewUser(rowid,roleName){
		var diag = new Dialog();
		diag.Title = "角色选择用户";
		diag.Height = 320;
		diag.Width = 495;
		diag.URL = "${path }/role/toRoleViewUser?roleId=" + rowid+"&roleName="+roleName;
		diag.ShowButtonRow=true;
		diag.OkButtonText=" 确定 ";
		diag.CancelButtonText="取消";
		diag.OKEvent = function(){
			//调用子页面submitForm 方法
		diag.innerFrame.contentWindow.addRoleViewUser();
		};
		diag.show();
		diag.addButton("next","重置",function(){
			diag.innerFrame.contentWindow.resetTree();
		});
	}
	//查看权限
	function onViewPermission(rowid,roleName){
		var systemCode = $("#systemCode").val();
		var diag = new Dialog();
		diag.Title = "权限配置";
		diag.Height = 300;
		diag.Width = 300;
		diag.URL = "${path }/role/toRoleViewPermission?roleId=" + rowid+"&roleName="+roleName+"&systemCode="+systemCode;
		diag.ShowButtonRow=true;
		diag.OkButtonText=" 确定 ";
		diag.CancelButtonText="取消";
		diag.OKEvent = function(){
			//调用子页面submitForm 方法
			diag.innerFrame.contentWindow.addRoleViewPermission();
		};
		diag.show();
	}
	
	//查看数据权限权限
	function onPermissionData(rowid, roleName){
		var systemCode = $("#systemCode").val();
		var diag = new Dialog();
		diag.Title = "数据权限配置";
		diag.Height = 350;
		diag.Width = 400;
		diag.URL = "${path }/permissionData/toRoleManage?roleId=" + rowid+"&roleName="+roleName+"&systemCode="+systemCode;
// 		diag.ShowButtonRow=true;
// 		diag.OkButtonText=" 确定 ";
// 		diag.CancelButtonText="取消";
// 		diag.OKEvent = function(){
// 			//调用子页面submitForm 方法
// 			diag.innerFrame.contentWindow.addSelectPermissions();
// 		};
		diag.show();
	}
	
	//点击树节点刷选对应的表格数据 
	function zTreeSelect(event,treeId,treeNode) {
		if(treeNode.id!='0'){
			var query = null;
			selectTreeNode = treeNode;
			query = {'systemCode':treeNode.id,
					 'roleName':$("#searchInput").val()
						};
			$("#systemCode").val(treeNode.id);
			$("#systemName").val(treeNode.name);
			grid.setOptions({ params : query});
			    //页号重置为1
			    grid.setNewPage(1);
			    //刷新表格数据 
				grid.loadData();
		}
	}
    //刷新表格数据并重置排序和页数
    function refresh(isUpdate){
    	if(!isUpdate){
    		//重置排序
        	grid.options.sortName='roleName';
        	grid.options.sortOrder="desc";
        	//页号重置为1
    		grid.setNewPage(1);
    	}
    	grid.loadData();
    }
	
	//处理高度自适应，每次浏览器尺寸变化时触发
	function customHeightSet(contentHeight){
		$(".cusBoxContent").height(contentHeight-55);
		$(".orgTreeContainer").height(contentHeight-30);
	}
</script>	
</body>
</html>