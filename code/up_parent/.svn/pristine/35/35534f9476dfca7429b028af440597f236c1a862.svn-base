<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="path" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>权限管理</title>
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
/* 修正自动断行问题 */
	.l-grid-tree-content {display: inherit; white-space: nowrap;}
/* 修正grid-tree 无法展开问题 */
	.l-grid-row-cell-inner .l-grid-tree-space {position:relative; z-index: 999;}
	
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
					<td>权限名称：</td>
					<td>
						<input type="text" id="searchInput" name="permissionName"/>
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
		$("#systemCode").val(firstCode);
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
				{ display: '权限名称',	name: 'permissionname',	 align: 'left',	width: "15%",id:"permissionId"},
			    { display: '权限编号',	name: 'permissionno',	showTitle:true	,	align: 'left', 	width: "15%"},
			    { display: '权限类型',	name: 'permissiontype',		align: 'left', 	width: "15%",type:"permissionType"},
			    { display: '排序号' , name: 'orderno',			align: 'left', 	width: "10%"},
			    { display: '是否有效',	name: 'enabled',			align: 'left',  width: "10%",type:"status"} ,
			    { display: '系统名称',	name: 'systemname', 	 	align: 'left',  width: "15%"} ,
			    { display: '系统代码',	name: 'systemcode', 	 	align: 'left',  width: "10%"} ,
           		{ display: '操作',	isAllowHide: false, align: 'left', width:"10%",
						 render: function (rowdata, rowindex, value, column){
							 if(isModifyAble&&rowdata.permissiontype==0){
								 return '<div align="center" class="padding_top4 padding_left5">'
								 + '<span class="img_list hand" title="查看" onclick=onView(\''+rowdata.permissionid+'\')></span>'
                                 + '<span class="img_edit hand" title="修改" onclick=modify(\''+rowdata.permissionid+'\')></span>'
                             + '</div>';
							 }
		                 }
		            }
			  ],
		 url: '${path }/permission/findPermissionList', sortName: 'orderno',sortOrder:'asc',rownumbers:true,checkbox:true,
         height: '100%', width:"100%", pageSize:10, selectRowButtonOnly:false,percentWidthMode:true,params:[{name:"systemCode",value:systemCode}],
         toolbar:{
        	 items:[
        		  {text: '新增', click: add,  iconClass: 'icon_add',visible:isAddAble},
        		  { line : true,visible:isAddAble },
        		  {text: '删除', click: del, iconClass: 'icon_delete',visible:isRemoveAble},
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
         	},
         	//以下为异步加载需要
        autoCheckChildren:true, tree: { columnId: 'permissionId' },treeAjax:true,
        treeChildDataPath:'${path }/permission/findPermissionChild?parentId=',treeAutoParam:"permissionid",
		});
	}
	
	$.quiDefaults.Grid.formatters['status'] = function (value, column) {
	    return (value=="1"?"是":"否");
	};
	$.quiDefaults.Grid.formatters['permissionType'] = function (value, column) {
	    return (value=="0"?"功能权限":"按钮权限");
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
	function add() {
		var systemCode = $("#systemCode").val();
		var systemName = $("#systemName").val();
		var parentPermissionId="";
		var rows=grid.getSelectedRows();
		if(rows!=null&&rows.length==1){
			parentPermissionId=rows[0].permissionid;
			systemCode=rows[0].systemcode;
			systemName=rows[0].systemname;
		}
		if(systemCode!=null&&systemCode.length>0&&systemCode!=0||parentPermissionId!=""){
				var diag = new Dialog();
				diag.Title = "新增权限";
				diag.Height =280;
				diag.Width = 650;
				diag.URL = "${path }/permission/toAddPermission?btnPermission=${dict.BTN_PERMISSIONNO}&systemCode="+systemCode+"&systemName="+systemName+"&parentPermissionId="+parentPermissionId,
				diag.ShowButtonRow=true;
				diag.OkButtonText=" 确定 ";
				diag.CancelButtonText="取消";
				diag.OKEvent = function(){
					//调用子页面submitForm 方法
					diag.innerFrame.contentWindow.submitForm();
				};
				diag.show();
		}else{
			Dialog.alert("请选择一个归属子系统或一个权限节点!");
			return;
		}
		
	}
	
	
	function onView(permissionId){
		var systemCode = $("#systemCode").val();
		var systemName = $("#systemName").val();
		var diag = new Dialog();
		diag.Title = "查看权限";
		diag.Width=650;
		diag.Height=200;
		diag.URL = "${path }/permission/onView?btnPermission=${dict.BTN_PERMISSIONNO}&permissionId="+permissionId+"&systemCode="+systemCode+"&systemName="+systemName,
		diag.ShowButtonRow=false;
		diag.show();
	}
	
	function modify(permissionId){
		var systemCode = $("#systemCode").val();
		var systemName = $("#systemName").val();
		var diag = new Dialog();
		diag.Title = "修改权限";
		diag.Width=650;
		diag.Height=280;
		diag.URL = "${path }/permission/toModifyPermission?btnPermission=${dict.BTN_PERMISSIONNO}&permissionId="+permissionId+"&systemCode="+systemCode+"&systemName="+systemName,
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
	function del() {
		var selectedRows = grid.getSelectedRows();
		var selectedRowsLength = selectedRows.length;
		
		if(selectedRowsLength == 0) {
			Dialog.alert("请选中要删除的记录!");
			return;
		}
		var permissionIds = "";
		for(var i = 0;i<selectedRowsLength;i++) {
			if(selectedRows[i].isParent){
				Dialog.alert("您要删除的项中包含有下级权限<br />　　　请先删除它的下级权限！",null,null,null,4);
				return;
			}
			if(i==0){
				permissionIds +="'"+ selectedRows[i].permissionid + "'";
			}else{
				permissionIds +=",'"+ selectedRows[i].permissionid + "'";
			}
			
		}
		Dialog.confirm("确定删除所选的"+selectedRowsLength+"个权限？",function(){
			$.post("${path }/permission/removePermission",
					//获取所有选中行
					{"permissionIds":permissionIds},
					
					function(result){
						handleResult(result);
					},
					"json");
		});
	}
	
	//删除后的提示
	function handleResult(result){
		if(result.message == "删除成功"){
			Dialog.alert("删除成功！",null,null,null,1);
			grid.loadData();
		}else{
			Dialog.alert("删除失败！");
		}
	}
	
	//菜单选择权限
	function onViewMenu(permissionId,systemCode){
		var diag = new Dialog();
		diag.Title = "菜单选择权限";
		diag.Width=550;
		diag.Height=310;
		diag.URL = "${path }/permission/toPermissionViewMenu?permissionId=" + permissionId+"&systemCode="+systemCode;
		diag.ShowButtonRow=true;
		diag.OkButtonText=" 确定 ";
		diag.CancelButtonText="取消";
		diag.OKEvent = function(){
			//调用子页面submitForm 方法
			diag.innerFrame.contentWindow.addRoleViewUser();
		};
		diag.show();
		diag.addButton("next","重置",function(){
			diag.innerFrame.contentWindow.resetForm();
		});
	}
	//点击树节点刷选对应的表格数据 
	function zTreeSelect(event,treeId,treeNode) {
		if(treeNode.id!='0'){
			var query = null;
			selectTreeNode = treeNode;
			query = {'systemCode':treeNode.id,
					 'permissionName':$("#searchInput").val()
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
        	grid.options.sortName='orderNo';
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