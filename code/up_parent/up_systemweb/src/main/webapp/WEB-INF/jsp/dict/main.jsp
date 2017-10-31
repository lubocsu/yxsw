<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="path" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>数据字典编辑</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript">
var path = '${path }';
</script>
<!--框架必需start-->
<script type="text/javascript" src="${path }/libs/js/jquery.js"></script>
<script type="text/javascript" src="${path }/libs/js/language/cn.js"></script>
<script type="text/javascript" src="${path }/libs/js/framework.js"></script>
<link rel="stylesheet" type="text/css" href="${path }/libs/css/import_basic.css"/>
<link rel="stylesheet" type="text/css" id="skin" prePath="${path }/" scrollerY="false"/>
<link rel="stylesheet" type="text/css" id="customSkin"/>
<!--分离模式-弹窗组件start-->
<script type="text/javascript" src="${path }/libs/js/popup/drag.js"></script>
<script type="text/javascript" src="${path }/libs/js/popup/dialog.js"></script>
<!--树组件start -->
<link rel="stylesheet" type="text/css" href="${path }/libs/js/tree/ztree/ztree.css"/>
<script type="text/javascript" src="${path }/libs/js/tree/ztree/ztree.js"></script>
<!--布局控件start-->
<script type="text/javascript" src="${path }/libs/js/nav/layout.js"></script>
<!--数据表格start-->
<script type="text/javascript" src="${path }/libs/js/table/quiGrid.js"></script>
<!-- 表单start -->
<script type="text/javascript" src="${path }/libs/js/form/form.js"></script>
<!--箭头分页start-->
<script type="text/javascript" src="${path }/libs/js/nav/pageArrow.js"></script>
<style type="text/css">
	/* 搜索栏样式调整*/
	.searchBox {margin: 5px;}
	/* 分页条样式调整 */
	#pager {margin: 0 8px;}
	/* 修正自动断行问题 */
	.l-grid-tree-content {display: inherit; white-space: nowrap;}
	/* 修正grid-tree 无法展开问题 */
	.l-grid-row-cell-inner .l-grid-tree-space {position:relative; z-index: 999;}
</style>
</head>
<body>
<div id="publicCon">
	<div id="leftCon" position="left" style="" panelTitle="数据字典目录">
		<div class="searchBox">
			<input type="text" style="width:140px;" watermark="输入关键字" id="searchTree"/>
			<span class="img_find hand" title="点击查找节点" keepdefaultstyle="true" onclick="searchTree()"></span>
		</div>
		<!-- 字典目录树区域 -->
		<div class="DictTreeContainer">
			<ul id="tree" class="ztree"></ul>
		</div>
		<!-- 树分页 -->
		<div class="pageArrow" pageSize="20" keepDefaultStyle="true" id="pager"></div>
	</div>
	<div id="centerCon" position="center" style="">
		<!-- 查询区 -->
     	<div class="box2" panelTitle="查询" showStatus="false">
			<form action="#" id="queryForm" method="post">
				<input type="hidden" id="parentId" name="parentId" value=""/>
				<table>
					<tr>
						<td>字典名称：</td>
						<td>
							<input type="text" id="searchParentNodeName" name="parentNodeName"/>
						</td>
						<td style="width: 70px; text-align: right;">数据代码：</td>
						<td>
							<input type="text" id="searchCode" name="code"/>
						</td>
						<td style="width: 50px; text-align: right;">数据1：</td>
						<td>
							<input type="text" id="searchData1" name="data1"/>
						</td>
						<td><button type="button" onclick="searchHandler()"><span class="icon_find">查询</span></button></td>
						<td><button type="button" onclick="resetSearch()"><span class="icon_reload">重置查询</span></button></td>
					</tr>
				</table>
			</form>
		</div>
		<!-- 数据展示区 -->
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
	
	//定义grid
	var grid = null;
	
	//初始化函数
	function initComplete(){
		//当保存表单刷新本页面时关闭弹窗
		Dialog.close();
		
		$("#publicCon").layout({ leftWidth: 180,onEndResize:function(){
			grid.resetWidth();
		}}); 
		
		//初始化tree
		initTree();
		
		//初始化grid组件
		initGrid();
		
		//监听查询框的回车事件
		$("#searchInput").keydown(function(event){
		 	if(event.keyCode==13){
				searchHandler();
				//阻止浏览器默认行为
		 		return false;
			}
		 });
		
	}
	
	function searchTree(){
		var value = $("#searchTree").val();
 		if(value){
 			searchNodes(value);
 		}
	}
	
	//初始化Grid处理
	function initGrid() {
		grid = $("#dataBasic").quiGrid({
			columns:[
			    { display: '数据代码',		name: 'code',			align: 'left', 	width: "25%", id:"dictId"},
			    { display: '数据1',		name: 'data1',			align: 'left', 	width: "25%", showTitle:true},
			    { display: '数据2',		name: 'data2',			align: 'left', 	width: "25%", showTitle:true},
			    { display: '数据3',		name: 'data3',			align: 'left', 	width: "25%", showTitle:true},
           		{ display: '操作',		isAllowHide: false, align: 'center', width:80,
						 render: function (rowdata, rowindex, value, column){
							var html = '<div class="padding_top4 padding_left5">';
							html = html + '<span class="img_list hand" title="查看" onclick="onView(\'' + rowdata.nodeid + '\')"></span>';
							//按钮权限+数据权限
							if(isModifyAble && rowdata.treetype > 0){
								html = html + '<span class="img_edit hand" title="修改" onclick="onEdit(\'' + rowdata.nodeid + '\')"></span>';
							}
							/* if(isRemoveAble && rowdata.treetype > 0){
								html = html + '<span class="img_delete hand" title="删除" onclick="onDelete(\'' + rowdata.nodeid+'\',' + rowindex + ')"></span>';
							} */
							html +=  '</div>';
	                 	    return html;
		                 }
		            }
			  ],
		 url: '${path }/dict/findDictDataGridByAjax', sortName: 'orderno',sortOrder:'asc',rownumbers:true, checkbox:true,
         height: '100%', width:"100%", pageSize:10, percentWidthMode:true,selectRowButtonOnly:false,
         toolbar:{
        	 items:[
        		  {text: '新增', click: addDictData,    iconClass: 'icon_add', visible: isAddAble },
        		  { line : true , visible: isAddAble},
        		  {text: '删除', click: deleteUnit, iconClass: 'icon_delete', visible: isRemoveAble },
        		  { line : true , visible: isRemoveAble}
        		]
         },
         //以下为异步加载需要
         autoCheckChildren:true, tree: { columnId: 'dictId' }, treeAjax:true,
         treeChildDataPath:'${path }/dict/findChildDataByAjax?parentId=',treeAutoParam:"nodeid",
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
    	//$("#queryForm")[0].reset();
    	jQuery("#searchParentNodeName").val("");
    	jQuery("#searchCode").val("");
    	jQuery("#searchData1").val("");
		searchHandler();
    }
    
 	 //新增字典数据
	function addDictData() {
		var parentNodeId = $("#parentId").val();
		var diag = new Dialog();
		diag.Title = "新增数据项";
		diag.URL = "${path }/dict/toAddDictData?parentNodeId=" + parentNodeId;
		diag.Width = 400;
		diag.Height = 220;
		//设置保存按钮文字（默认是确定）
		diag.OkButtonText = "确定";
		diag.OKEvent = function(){
			//调用子页面submitForm 方法
			diag.innerFrame.contentWindow.submitForm();
		};
		diag.show();
	}
  
    //刷新表格数据并重置排序和页数
    function refresh(isUpdate){
    	if(!isUpdate){
    		//重置排序
        	grid.options.sortName='orderno';
        	grid.options.sortOrder="asc";
        	//页号重置为1
    		grid.setNewPage(1);
    	}
    	grid.loadData();
    }
	
	//查看
	function onView(rowid){
		Dialog.open({
			URL:"${path }/dict/toViewDictData?nodeId=" + rowid,
			Title:"查看", Width:500, Height:160 });
	}
	
	//修改	
	function onEdit(rowid){
		var diag = new Dialog();
		diag.Title = "修改数据项";
		diag.URL = "${path }/dict/toModifyDictData?nodeId=" + rowid;
		diag.Width = 400;
		diag.Height = 220;
		//设置保存按钮文字（默认是确定）
		diag.OkButtonText = "确定";
		diag.OKEvent = function(){
			//调用子页面submitForm 方法
			diag.innerFrame.contentWindow.submitForm();
		};
		diag.show();
	}
	
	//删除	
	function onDelete(rowid, rowidx){
		Dialog.confirm("确定要删除该记录吗？",function(){
		  	//删除记录
		  	$.post("${path }/dict/removeDictData",
		  			{"ids":rowid},
		  			function(result){
		  				handleResult(result.status);
					},"json");
					//刷新表格数据 
					grid.loadData();
			});
	}
		
	//批量删除
	function deleteUnit() {
		var prompt = "确定要删除吗？";
		var rows = grid.getSelectedRows();
		var rowsLength = rows.length;
		if(rowsLength == 0) {
			Dialog.alert("请选中要删除的记录!");
			return;
		}
		
		for(var i=0; i < rowsLength; i++){
			//检查是否包含系统级字典
			if(rows[i].treetype == 0){
				Dialog.alert("系统级字典不允许删除！");
				return;
			}
			//检查是否包含子节点
			if(rows[i].isParent == true){
				prompt = "包含子级数据, 确定要删除吗？";
			}
		}
		
		Dialog.confirm(prompt, function(){
		$.post("${path }/dict/removeDictData",
			//获取所有选中行
			getSelectIds(grid),
			function(result){
				handleResult(result.status);
			},"json");
		});
	}
	
	//获取所有选中行获取选中行的id 格式为 ids=1&ids=2 
	function getSelectIds(grid) {
		var selectedRows = grid.getSelectedRows();
		var selectedRowsLength = selectedRows.length;
		var ids = "";
		for(var i = 0;i<selectedRowsLength;i++) {
			if(i < (selectedRowsLength -1)){
				ids += selectedRows[i].nodeid + ",";
			}else{//最后一次不用加逗号
				ids += selectedRows[i].nodeid;
			}
		}
		return {"ids":ids};
	}
	
	//删除后的提示
	function handleResult(result){
		if(result == 1){
			Dialog.alert("删除成功！",null,null,null,1);
			grid.loadData();
		}else{
			Dialog.alert("删除失败！");
		}
	}
	
	//处理高度自适应，每次浏览器尺寸变化时触发
	function customHeightSet(contentHeight){
		$(".cusBoxContent").height(contentHeight-55);
		$(".DictTreeContainer").height(contentHeight-90);
	}
</script>
<!--自定义 start-->
<script type="text/javascript" src="${path }/js/dict/dictTree.js"></script>
<!--自定义 end-->
</body>
</html>