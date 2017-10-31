<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>组织机构管理</title>
<!--框架必需start-->
<script type="text/javascript" src="${path }/libs/js/jquery.js"></script>
<script type="text/javascript" src="${path }/libs/js/language/cn.js"></script>
<script type="text/javascript" src="${path }/libs/js/framework.js"></script>
<link href="${path }/libs/css/import_basic.css" rel="stylesheet" type="text/css"/>
<link rel="stylesheet" type="text/css" id="skin" prePath="${path }/" scrollerY="false"/>
<link rel="stylesheet" type="text/css" id="customSkin"/>
<!--树组件start -->
<script type="text/javascript" src="${path}/libs/js/tree/ztree/ztree.js"></script>
<link href="${path}/libs/js/tree/ztree/ztree.css" rel="stylesheet" type="text/css"/>
<!--布局控件start-->
<script type="text/javascript" src="${path}/libs/js/nav/layout.js"></script>
<!--数据表格start-->
<script src="${path}/libs/js/table/quiGrid.js" type="text/javascript"></script>
<!-- 表单start -->
<script src="${path}/libs/js/form/form.js" type="text/javascript"></script>
<!--弹窗组件start-->
<script type="text/javascript" src="${path}/libs/js/popup/drag.js"></script>
<script type="text/javascript" src="${path}/libs/js/popup/dialog.js"></script>
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

.l-grid-tree-content {display: block; white-space: nowrap;}
/* 修正grid-tree 无法展开问题 */
.l-grid-row-cell-inner .l-grid-tree-space {position:relative; z-index: 999;}
</style>
</head>
<body>
	<div id="layout1">
        <div id="leftCon" position="left" panelTitle="菜单目录">
        	<div class="orgTreeContainer">
        		<ul id="tree" class="ztree"></ul>
        	</div>
        </div>
        <div id="centerCon" position="center" style="">
        	<div class="box2" panelTitle="查询" showStatus="false">
			<form action="${path}/org/getGridOrgData" id="queryForm" method="post">
			<input type="hidden" id="parentMenuId" name="parentMenuId" value=""/>
			<input type="hidden" id="systemCode" name="systemCode" value=""/>
			<table>
				<tr>
					<td>菜单名称：</td>
					<td>
						<input type="text" id="searchInput" name="menuName" style="width: 250px;" watermark="支持全拼、首字母简写或名称搜索"/>
						<input type="text" style="width:2px;display:none;"/>
					</td>
					<td><button type="button" onclick="searchHandler()"><span class="icon_find">查询</span></button></td>
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
	//按钮权限
	var isRemoveAble = '${KEY_FOR_IN_MENU_PERMISSION.remove }'== '0' ? false : true; 
	var isAddAble = '${KEY_FOR_IN_MENU_PERMISSION.add }'== '0' ? false : true;   
	var isModifyAble = '${KEY_FOR_IN_MENU_PERMISSION.modify }'== '0' ? false : true; 
	 //设定不可编辑的节点id
    var noeditArray = ["1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19"];
	//定义全局grid对象，treeObj对象，选中的树节点
	var grid = null;
	var treeObj = null;
	var selectTreeNode = null;
	//定义树节点初始数据
	var nodes =[
		{ id:"0", parentId:-1, name:"系统菜单目录", systemcode:"-9999",open:true ,icon:"${path}/libs/icons/home.gif"}
	];
	//树属性配置
	var selectionSetting = {
			view: {
				fontCss: getFont,
				dblClickExpand: true
			},
			callback: {
				onClick: zTreeSelect,
				beforeClick: zTreeBeforeClick,
				onAsyncSuccess:onAsyncSuccess
			},
			async: {
		        enable: true,
		        dataType: 'JSON',
		        url: '${path}/menu/queryMenuTree'
		    },
	};
	//初始化函数
	function initComplete(){
		//当提交表单刷新本页面时关闭弹窗
		Dialog.close();
		$("#layout1").layout({ leftWidth: 180,onEndResize:function(){
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
	
	function initTree() {
		$.ajax({
			type : 'POST',
			url : '${path}/menu/queryMenuTree',
			dataType : 'json',
			success : function(result){
				nodes = nodes.concat(result);
				treeObj = $.fn.zTree.init($("#tree"), selectionSetting, nodes);
				var firstNode = treeObj.getNodeByParam("id", "up_systemweb", null);
				// 默认选中第一个节点（默认后台子系统节点）
				treeObj.selectNode(firstNode,false);
				jQuery("#systemCode").val(firstNode.systemcode);
				jQuery("#parentMenuId").val(firstNode.id);
				treeObj.expandNode(firstNode, true, true, true);
				//初始化grid组件
				initGrid();
			},
			error : function(a){
				Dialog.open("系统菜单目录获取失败");
			}
		});
	}
	
	function initGrid() {
		//初始化第一个系统编码
		var systemCode = jQuery("#systemCode").val();
		grid = $("#dataBasic").quiGrid({
			columns:[
				{ display: '菜单名称', name: 'name', id:'nameId', align: 'left',width: "30%"},
				{ display: '模块链接地址', name: 'linkurl', align: 'left',showTitle:true, width: "50%"},
			    { display: '是否有效', name: 'enabled', 	 align: 'center',width:80} ,
			    { display: '排序号', name: 'orderno', 	 align: 'center',  width:50} ,
			    { display: '描述说明', name: 'description', 	 align: 'center',  width:"20%"} ,
           		{ display: '操作', isAllowHide: false, align: 'center', width:80,
						 render: function (rowdata, rowindex, value, column){
							 var html='<div class="padding_top4 padding_left5">'
                                 + '<span class="img_list hand" title="查看" onclick="onView(\'' + rowdata.id + '\')"></span>';
							 if(isModifyAble){
								 html += '<span class="img_edit hand" title="修改" onclick="onEdit(\'' + rowdata.id + '\')"></span>';
							 }
							 html += '</div>';
	                 	    return html;
		                 }
		            }
			  ],
		 url: '${path}/menu/getGridMenuData', rownumbers:true,checkbox:true,selectRowButtonOnly:false,
		 sortName: 'orderno',sortOrder:'asc',usePager: false,
		 //行渲染 实质是对该行的tr标签进行属性的设置
		 alternatingRow: false,
	     rowAttrRender: function(rowdata, rowid){
	          return "无效" == rowdata.enabled ? "style=\"background-color:#F7CEB3;\"" : '';
	     },
         height: '100%', width:"100%",percentWidthMode:true,tree: { columnId: 'nameId' },params:[{name:"systemCode",value:systemCode}],
         toolbar:{
        	 items:[
        		  {text: '新增', click: addMenu, iconClass: 'icon_add',visible: isAddAble},
        		  { line : true },
        		  {text: '删除', click: onDelete, iconClass: 'icon_delete',visible: isRemoveAble},
        		  { line : true }
        		]
         	}
		});
	}
	
	//新增
	function addMenu(){
		var parentMenuId = $("#parentMenuId").val();
		var systemCode = $("#systemCode").val();
		var diag = new Dialog();
		diag.Title = "新增菜单";
		diag.URL = "${path}/menu/toAddMenu?parentMenuId=" + parentMenuId+"&systemCode="+systemCode;
		diag.ShowButtonRow=true;
		diag.Height =320;
		diag.Width = 650;
		diag.OkButtonText=" 确 定 ";
		diag.OKEvent = function(){
			diag.innerFrame.contentWindow.submitThisForm();
		};
		diag.show();
	}
	
	//新增一个树节点
	function addNewNode(newNode) {
		var pNode = treeObj.getNodesByParam("id", newNode.parentId, null);
		treeObj.addNodes(pNode[0], newNode);
	};

	//查看
	function onView(rowid){
		Dialog.open({
			URL:"${path}/menu/viewMenu?menuId=" + rowid,
			Title:"查看菜单",Width:650, Height:320});
	}
	//修改	
	function onEdit(rowid){
		var diag = new Dialog();
		diag.Title = "修改菜单";
		diag.URL = "${path}/menu/toModifyMenu?menuId=" + rowid;
		diag.ShowButtonRow=true;
		diag.Height = 320;
		diag.Width = 650;
		diag.OkButtonText=" 确 定 ";
		diag.OKEvent = function(){
			diag.innerFrame.contentWindow.submitThisForm();
		};
		diag.show();
	}
	// 修改机构时，若名称更改同时更改树节点名称
	function modifyNodeName(menuId,oldName ,newName){
		if(menuId != null){
			var nodes = treeObj.getNodesByParam("id", menuId, null);
			nodes[0].name = newName;
			treeObj.updateNode(nodes[0]);
			
		}else{
			var rootNode = treeObj.getNodeByParam("id","0",null);
			treeObj.reAsyncChildNodes(rootNode,"refresh");
		}
	}
	
	//删除	
	function onDelete(){
		var rows = grid.getSelectedRows();
		var rowsLength = rows.length;
		// 获取menuIds数组
		var menuIds = "";
		// 存在子节点的节点id
		var hasChildrenNodes = new Array();
		var index = 0;
		if(rowsLength == 0) {
			Dialog.alert("请选中要删除的记录!");
			return;
		}else{
			for(var i = 0;i<rowsLength;i++) {
				menuIds += rows[i].id+",";
				if(grid.hasChildren(rows[i])){
					hasChildrenNodes.push(rows[i].id);
					index++;
				}
			}
		}
		// 判断要删除的项目中是否存在有子节点的项
		if(index > 0){
			Dialog.alert("您要删除的项中包含有子菜单的项目<br />　　　请先删除它的子菜单！",null,null,null,4);
			return;
		}else{
			Dialog.confirm("确定要删除所选记录吗？",function(){
			  	//删除记录
			  	$.post("${path}/menu/removeMenu",
			  			{"menuIds":menuIds},
			  			function(result){
			  				handleResult(result.status);
						},"json");
						//刷新表格数据 
						grid.loadData();
						deleteNewNode(menuIds);
				});
		}
	}
		
	//删除树节点
	function deleteNewNode(menuIds) {
		var ids = new Array();
		ids = menuIds.split(",");
		jQuery.each(ids, function(i, n){
			var nodes = treeObj.getNodesByParam("id", n, null);
		    treeObj.removeNode(nodes[0]);
		});
	};
	
	//删除后的提示
	function handleResult(result){
		if(result == 1){
			Dialog.alert("删除成功！",null,null,null,1);
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
			ids += selectedRows[i].orgId + ",";
		}
		return {"orgIds":ids};
	}
	
	//点击树节点刷选对应的表格数据 
	function zTreeSelect(event,treeId,treeNode) {
		selectTreeNode = treeNode;
		menuName = jQuery("#searchInput").val();
		if("支持全拼、首字母简写或名称搜索"==menuName){
			menuName = "";
		}
		var query = {
			'parentMenuId' : treeNode.id,
			'systemCode' : treeNode.systemcode,
			'menuName' :menuName
		};
		$("#parentMenuId").val(treeNode.id);
		$("#systemCode").val(treeNode.systemcode);
		grid.setOptions({
			params : query
		});
		//页号重置为1
		grid.setNewPage(1);
		//刷新表格数据 
		grid.loadData();
	}
	// 判断是否具备点击事件
	function zTreeBeforeClick(treeId, treeNode, clickFlag) {
		return (treeNode.systemcode !== "-9999");
	}
	
	// 异步加载后默认展开所有节点
	function onAsyncSuccess(){
		var nodeid = jQuery("#parentMenuId").val();
		var selectNode = treeObj.getNodeByParam("id",nodeid,null);
		treeObj.selectNode(selectNode);
		var firstNode = treeObj.getNodeByParam("id", "up_systemweb", null);
		treeObj.expandNode(firstNode,true,true,true);
		treeObj.expandNode(selectNode,true,true,true);
	}

	function getFont(treeId, node) {
		return node.font ? node.font : {};
	}
	//查询
	function searchHandler() {
		/* var query = $("#queryForm").formToArray(); */
		menuName = jQuery("#searchInput").val();
		if("支持全拼、首字母简写或名称搜索"==menuName){
			menuName = "";
		}
		var query = {
			'parentMenuId' : jQuery("#parentMenuId").val(),
			'systemCode' : jQuery("#systemCode").val(),
			'menuName' : menuName
		};
		grid.setOptions({
			params : query
		});
		//页号重置为1
		grid.setNewPage(1);
		//刷新表格数据 
		grid.loadData();
	}

	//重置查询
	function resetSearch() {
		//$("#queryForm")[0].reset();
		jQuery("#searchInput").val("");
		searchHandler();
	}

	//刷新表格树,不需要重置排序和页码
	function refresh() {
		menuName = jQuery("#searchInput").val();
		if("支持全拼、首字母简写或名称搜索"==menuName){
			menuName = "";
		}
		var query = {
			'parentMenuId' : jQuery("#parentMenuId").val(),
			'systemCode' : jQuery("#systemCode").val(),
			'menuName' : menuName
		};
		grid.setOptions({
			params : query
		});
		grid.loadData();
	}

	//处理高度自适应，每次浏览器尺寸变化时触发
	function customHeightSet(contentHeight) {
		$(".cusBoxContent").height(contentHeight - 55);
		$(".orgTreeContainer").height(contentHeight - 30);
	}
</script>	
</body>
</html>