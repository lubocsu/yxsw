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
/* 修正自动断行问题 */
.l-grid-row-cell-inner {white-space: nowrap;}

</style>
</head>
<body>
	<div id="layout1">
        <div id="leftCon" position="left" panelTitle="组织机构目录">
        	<div class="orgTreeContainer">
        		<ul id="tree" class="ztree"></ul>
        	</div>
        </div>
        <div id="centerCon" position="center" style="">
        	<div class="box2" panelTitle="查询" showStatus="false">
			<form action="${path}/org/getGridOrgData" id="queryForm" method="post">
			<input type="hidden" id="parentOrgId" name="parentOrgId" value=""/>
			<table>
				<tr>
					<td>机构名称：</td>
					<td>
						<input type="text" id="searchInput" name="orgName" style="width: 250px;" watermark="支持全拼、首字母简写或名称搜索"/>
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
	//定义树节点初始数据
	var nodes =[
		{ id:"0", parentId:-1, name:"组织结构目录", open:true,icon:"${path}/libs/icons/home.gif"}
	];
	//树属性配置
	var selectionSetting = {
			view: {
				fontCss: getFont,
				dblClickExpand: true
			},
			callback: {
				onClick: zTreeSelect,
				onAsyncSuccess:onAsyncSuccess
			},
			async: {
		        enable: true,
		        dataType: 'JSON',
		        url: '${path}/org/getOrgTree'
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
			url : '${path}/org/getOrgTree',
			dataType : 'json',
			data : {path:'${path}'},
			success : function(result){
				nodes = nodes.concat(result);
				treeObj = $.fn.zTree.init($("#tree"), selectionSetting, nodes);
				var selectTreeNode = treeObj.getNodeByParam("id","0",null);
				jQuery("#parentOrgId").val(selectTreeNode.id);
				treeObj.selectNode(selectTreeNode);
				treeObj.expandAll(true);
				//初始化grid组件
				initGrid();
			},
			error : function(a){
				Dialog.open("机构树获取失败");
			}
		});
	}
	
	function initGrid() {
		grid = $("#dataBasic").quiGrid({
			columns:[
				{ display: '机构名称', name: 'orgname',     align: 'center', width: "20%",showTitle:true},
			    { display: '完整机构代码', name: 'orgcode', 	 align: 'left', width: "20%",showTitle:true},
			    { display: '地址', name: 'address', align: 'center', width: "20%",showTitle:true},
			    { display: '邮编', name: 'zip', 	 align: 'center',  width:80} ,
			    { display: '负责人', name: 'principalname', 	 align: 'center',  width:100} ,
			    { display: '联系电话', name: 'tel', 	 align: 'center',  width:"20%"} ,
			    { display: '备注', name: 'description', 	 align: 'center',  width:"20%",showTitle:true} ,
			    { display: '是否有效', name: 'enabled', 	 align: 'center', type:'enabled',  width:60} ,
           		{ display: '操作', isAllowHide: false, align: 'center', width:80,
						 render: function (rowdata, rowindex, value, column){
							 var html ='<div class="padding_top4 padding_left5">'
                                 + '<span class="img_list hand" title="查看" onclick="onView(\'' + rowdata.orgid + '\')"></span>';
							 if(isModifyAble){
								 html += '<span class="img_edit hand" title="修改" onclick="onEdit(\'' + rowdata.orgid + '\')"></span>';
							 }
							 if(false){
								 html += '<span class="img_delete hand" title="删除" onclick="onDelete(\'' + rowdata.orgid+'\',\''+rowindex + '\')"></span>';
							 }
                             html += '</div>';
	                 	    return html;
		                 }
		            }
			  ],
		      //行渲染 实质是对该行的tr标签进行属性的设置
			  alternatingRow: false,
		      rowAttrRender: function(rowdata, rowid){
		           return "0" == rowdata.enabled ? "style=\"background-color:#F7CEB3;\"" : '';
		      },
			 url: '${path}/org/getGridOrgData', sortName: 'orderno',sortOrder:'asc',rownumbers:true,checkbox:true,
			 selectRowButtonOnly:false,
	         height: '100%', width:"100%", pageSize:10, percentWidthMode:true,params:[{name:"parentOrgId",value:"0"}],
	         toolbar:{
	        	 items:[
	        		  {text: '新增', click: addUnit, iconClass: 'icon_add',visible:isAddAble},
	        		  { line : true },
	        		  {text: '删除', click: deleteUnit, iconClass: 'icon_delete',visible:isRemoveAble},
	        		  { line : true },
	        		 /*  {text: '导入', click: showImportDialog, iconClass: 'icon_import'},
	        		  { line : true }, */
	        		  {text: '导出当前页', click: exportPageData, iconClass: 'icon_export'},
	        		  { line : true },
	        		  {text: '导出全部', click: exportTotalData, iconClass: 'icon_export'}
	        		]
	         	}
			});
	}
	
	//新增
	function addUnit(){
		var parentOrgId = $("#parentOrgId").val();
		var diag = new Dialog();
		diag.Title = "新增组织机构";
		diag.URL = "${path}/org/toAddOrg?parentOrgId=" + parentOrgId;
		diag.ShowButtonRow=true;
		diag.Height = 465;
		diag.Width = 430;
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
			URL:"${path}/org/findOrg?orgId=" + rowid,
			Title:"查看组织机构",Width:400,Height:455});
	}
	//修改	
	function onEdit(rowid){
		var diag = new Dialog();
		diag.Title = "修改组织机构";
		diag.URL = "${path}/org/toModifyOrg?orgId=" + rowid;
		diag.ShowButtonRow=true;
		diag.Height = 465;
		diag.Width = 430;
		diag.OkButtonText=" 确 定 ";
		diag.OKEvent = function(){
			diag.innerFrame.contentWindow.submitThisForm();
		};
		diag.show();
	}
	// 修改机构时，若名称更改同时更改树节点名称
	function modifyNodeName(oldName ,newName){
		if(oldName !=null){
			var nodes = treeObj.getNodesByParam("name", oldName, null);
			nodes[0].name = newName;
			treeObj.updateNode(nodes[0]);
		}else{
			var rootNode = treeObj.getNodeByParam("id", "0", null);
			treeObj.reAsyncChildNodes(rootNode,"refresh");
		}
	}
	
	// 异步加载后默认展开所有节点
	function onAsyncSuccess(){
		var nodeid = jQuery("#parentOrgId").val();
		var selectNode = treeObj.getNodeByParam("id",nodeid,null);
		treeObj.selectNode(selectNode);
		treeObj.expandAll(true);
	}
	//删除	
	function onDelete(rowid,rowidx){
		var ids= new Array();
		ids.push(rowid);
		Dialog.confirm("确定要删除该记录吗？",function(){
		  	//删除记录
		  	$.post("${path}/org/removeOrg",
		  			{"orgIds":rowid},
		  			function(result){
		  				handleResult(result.status);
					},"json");
					//刷新表格数据 
					grid.loadData();
					deleteNewNode(ids);
			});
	}
		
	//批量删除
	function deleteUnit() {
		var rows = grid.getSelectedRows();
		var rowsLength = rows.length;
		//获取orgId 数组
		var ids = "";
		var indexIds = 0;
		var needDeleteIds = new Array();
		//存在用户的机构名称
		var names = "";
		var indexName = 0;
		//存在子机构的机构名称
		var hasChildIds = "";
		if(rowsLength == 0) {
			Dialog.alert("请选中要删除的记录!");
			return;
		}else{
			for(var i = 0;i<rowsLength;i++) {
				// 判断是否存在用户
				jQuery.ajax({
			    	type:"post",
					url:"${path}/org/validateUser",
					dataType:"json",
					data:{
						orgId:rows[i].orgid,
					},
					async:false,
					success:function(result){
						if(result||"true"==result){
							names += rows[i].orgname+"　";
							indexName ++;
						}else{
							ids += rows[i].orgid+",";
							needDeleteIds.push(rows[i].orgid);
							indexIds++;
						}
					}
				});
				// 判断是否存在子机构
				var parentNode = treeObj.getNodeByParam("id",rows[i].orgid);
				var tmpNode = treeObj.getNodesByParam("parentId",rows[i].orgid,parentNode);
				if(tmpNode.length>0){
					hasChildIds += rows[i].orgid+",";
				}			
			}
		}
		if(indexName>0){
			if(indexIds>0){
				var otherOrg = "<br />是否删除其他机构？";
				Dialog.confirm("机构"+names+"含有正在使用的用户，请先删除其下用户！"+otherOrg,function(){
					$.post("${path}/org/removeOrg",
							//获取所有选中行
							{"orgIds":ids},
							function(result){
								handleResult(result.status);
							},"json");
							deleteNewNode(needDeleteIds);
				});
			}else{
				Dialog.alert("机构"+names+"含有正在使用的用户，请先删除其下用户！");
			}
		}else{
			if(hasChildIds.length != 0){
				Dialog.confirm("所选机构下存在子机构，是否确定要删除吗？",function(){
					$.post("${path}/org/removeOrg",
							//获取所有选中行
							{
								"orgIds":ids,
								"hasChildIds" : hasChildIds
							},
							function(result){
								handleResult(result.status);
							},"json");
							deleteNewNode(needDeleteIds);
				});
			}else{
				Dialog.confirm("确定要删除吗？",function(){
					$.post("${path}/org/removeOrg",
							//获取所有选中行
							{"orgIds":ids},
							function(result){
								handleResult(result.status);
							},"json");
							deleteNewNode(needDeleteIds);
				});
			}
		}
	}
	
	//判断所选机构下是否存在正在使用的人员
	function validateUser(orgId){
		jQuery.ajax({
	    	type:"post",
			url:"${path}/org/validateUser",
			dataType:"json",
			data:{
				orgId:orgId,
			},
			async:false,
			success:function(result){
				return result;
			}
		});
	}
	
	//删除树节点
	function deleteNewNode(ids) {
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
	
	//导入
	function showImportDialog(){
		// 下载模板名称
		var templateName = "组织机构";
		// 需要导入数据对应实体名称（注意大小写）
		var entityName = "SysOrgEntity";
		Dialog.open({Title:"导入机构信息", 
						Message:"请上传excel文件", 
						URL:"${path}/common/toImportExcelPage?entityName="+entityName+"&templateName="+templateName,
						Width:350,Height:150});
	}
	
	//导出本页
	function exportPageData(){
		exportData(true);
	}
	
	//导出全部
	function exportTotalData(){
		exportData(false);
	}
	
	//导出处理
	function exportData(isPage){
		var pageNo = grid.options.page;
		var pagerSize = grid.options.pageSize;
		if(!isPage){
			pagerSize = 0;
		}
		var sort = grid.options.sortName;
		var direction = grid.options.sortOrder;
		var parentOrgId = $("#parentOrgId").val();
		var orgName = $("#searchInput").val();
		if("支持全拼、首字母简写或名称搜索"==orgName){
			orgName = "";
		}
		var url = "${path}/common/exportExcel";
		url += "?pager.pageSize=" + pagerSize;
		url += "&pager.pageNo=" + pageNo;
		url += "&sort=" + sort;
		url += "&direction=" + direction;
		url += "&serviceName=orgServiceImpl";
		if (parentOrgId!=""){
			url += "&parentOrgId=" + parentOrgId;	
		}
		if (orgName!=""){
			url += "&orgName=" + orgName;	
		}
		url += "&fileName=组织机构.xls";
		window.location = url;
	}
	
	
	//点击树节点刷选对应的表格数据 
	function zTreeSelect(event,treeId,treeNode) {
		var query = null;
		var orgName = $("#searchInput").val();
		if("支持全拼、首字母简写或名称搜索"==orgName){
			orgName = "";
		}
		query = {
					'parentOrgId':treeNode.id,
					'orgName':orgName
				};
		$("#parentOrgId").val(treeNode.id);
		grid.setOptions({ params : query});
		    //页号重置为1
		    grid.setNewPage(1);
		    //刷新表格数据 
			grid.loadData();
	}
	
	function getFont(treeId, node) {
		return node.font ? node.font : {};
	}
    //查询
    function searchHandler(){
    	var  query = {
					'parentOrgId':jQuery("#parentOrgId").val(),
					'orgName':jQuery("#searchInput").val()
				};
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
    
    //刷新表格数据并重置排序和页数
    function refresh(isUpdate){
    	if(!isUpdate){
    		//重置排序
        	grid.options.sortName="orderNo";
        	grid.options.sortOrder="desc";
        	//页号重置为1
    		grid.setNewPage(1);
    	}
    	
    	grid.loadData();
    }
    jQuery.quiDefaults.Grid.formatters['enabled'] = function (value, column) {
		if(value=='0'){
	 		return '无效';
	 	}
	 	if(value=='1'){
	 		return '有效';
	 	}
	};
	
	//处理高度自适应，每次浏览器尺寸变化时触发
	function customHeightSet(contentHeight){
		$(".cusBoxContent").height(contentHeight-55);
		$(".orgTreeContainer").height(contentHeight-30);
	}

</script>	
</body>
</html>