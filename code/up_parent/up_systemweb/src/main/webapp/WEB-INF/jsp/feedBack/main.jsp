<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="path" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>问题反馈</title>
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

<!-- 日期选择框start -->
<script type="text/javascript" src="${path }/libs/js/form/datePicker/WdatePicker.js"></script>
<!-- 日期选择框end -->
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
					<td>反馈标题：</td>
					<td>
						<input type="text" id="title" name="title"/>
						<input type="text" style="width:2px;display:none;"/>
					</td>
					<td>反馈内容：</td>
					<td>
						<input type="text" id="content" name="content"/>
						<input type="text" style="width:2px;display:none;"/>
					</td>
					<td>反馈时间：</td>
					<td>
					<td>
						<input type="text" name="beginTime" id="beginTime" class="date" value="${lastMonthToDay }"
						 dateFmt="yyyy-MM-dd" style="width: 108px;"/> 
						至
						<input type="text" name="endTime" id="endTime" class="dateIcon"  value="${ currentDay}"
						 onfocus="WdatePicker({skin:themeColor,minDate:'#F{$dp.$D(\'beginTime\')}'})" style="width: 108px;"/> 
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
		grid = $("#dataBasic").quiGrid({
			columns:[
				{ display: '反馈标题',	name: 'title', showTitle:true,	align: 'left',	width: "20%"},
				{ display: '反馈内容',	name: 'content',showTitle:true,	align: 'left',	width: "25%"},
			    { display: '所属系统',	name: 'systemname',	align: 'left',	width: "15%"},
			    { display: '反馈人',	name: 'username',	align: 'left',	width: "15%"},
			    { display: '反馈时间',	name: 'createtime',	align: 'left',	width: "15%"},
           		{ display: '操作',	isAllowHide: false, align: 'center',	width: "10%",
						 render: function (rowdata, rowindex, value, column){
								 return '<div class="padding_top4 padding_left5">'
	                                 + '<span class="img_list hand" title="查看" onclick="onView(\'' + rowdata.id + '\')"></span>'
	                             + '</div>';
		                 }
		            }
			  ],
		 url: '${path }/feedBack/findList',params:[{name:"systemCode","value":$("#systemCode").val()}],
		 sortName: 'createtime',sortOrder:'desc',
		 rownumbers:true,checkbox:true,selectRowButtonOnly:false,
         height: '100%', width:"100%", pageSize:10, percentWidthMode:true
         ,toolbar:{
        	 items:[
				  {text: '填报反馈', click: feedBack, iconClass: 'icon_add',visible:isAddAble},
				  { line : true },
          		  {text: '删除反馈', click: delData, iconClass: 'icon_delete',visible:isRemoveAble},
          		  { line : true },
          		  {text: '导出当前页', click: exportPageData, iconClass: 'icon_export'},
          		  { line : true },
          		  {text: '导出全部', click: exportTotalData, iconClass: 'icon_export'},
          		  { line : true }
          		]
           	},
           	onAfterShowData:function(){
           		if(grid.getData() == ""){
           			top.Dialog.alert("该系统使用良好，没有反馈信息。|提示信息",null,null,null);
           		}
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
    	//$("#queryForm")[0].reset();
    	jQuery("#title").val("");
    	jQuery("#content").val("");
    	jQuery("#beginTime").val("");
    	jQuery("#endTime").val("");
		searchHandler();
    }
    
    function feedBack(){
		var diag = new Dialog();
		diag.Title = "上报反馈信息";
		diag.Width=450;
		diag.Height=220;
		diag.URL = "${path }/feedBack/toAddFeedBack?systemCode="+$("#systemCode").val()+"&systemName="+$("#systemName").val();
		diag.ShowButtonRow=true;
		diag.OkButtonText=" 确定 ";
		diag.CancelButtonText="取消";
		diag.OKEvent = function(){
			//调用子页面submitForm 方法
			diag.innerFrame.contentWindow.submitForm();
		};
		diag.show();
	}
    
  //查看
	function onView(rowid){
		Dialog.open({
			URL:"${path }/feedBack/showInfo?id=" + rowid,
			Title:"查看详细",Width:410, Height:250});
	}
	
	//删除
	function delData(){
		var ids="";
		var selectRow=grid.getSelectedRows();
		var id="";
		if(selectRow.length>0){
			for(var i=0;i<selectRow.length;i++){
				id=selectRow[i].id;
				if(id){
					ids+=","+id;
				}
			}
			parent.Dialog.confirm("确认删除选中的"+selectRow.length+"条反馈信息",function(){
				$.ajax({
					url:"${path}/feedBack/removeFeedBack", 
					type:"POST",
					data:{
					    "ids":ids,
					},
					error:function(){
					},
					success: function(responseText){
						parent.Dialog.alert(responseText.message,function(){
							resetSearch();
				        });
					}
				});
			});
		}
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
		var sortOrder=grid.options.sortOrder;
		var direction = grid.options.sortOrder;
		var systemCode=$("#systemCode").val();
		var beginTime=jQuery("#beginTime").val();
		var endTime=jQuery("#endTime").val();
		var title = $("#title").val();
		var content = $("#content").val();
		var url = "${path}/common/exportExcel";
		url += "?pager.pageSize=" + pagerSize;
		url += "&pager.pageNo=" + pageNo;
		url += "&sort=" + sort;
		url += "&sortOrder="+sortOrder;
		url += "&direction=" + direction;
		url += "&systemCode=" + systemCode;
		url += "&serviceName=feedBackService";
		if(title){
			url += "&title=" + title;
		}if(content){
			url += "&content=" + content;
		}if(beginTime){
			url += "&beginTime=" + beginTime;
		}if(endTime){
			url += "&endTime=" + endTime;
		}
		window.location = url;
	}
	
	
	
	
	//点击树节点刷选对应的表格数据 
	function zTreeSelect(event,treeId,treeNode) {
		if(treeNode.id!='0'){
			var query = null;
			selectTreeNode = treeNode;
			query = {'systemCode':treeNode.id,
					 'title':$("#title").val(),
					 'content':$("#content").val(),
					 'beginTime':$("#beginTime").val(),
					 'endTime':$("#endTime").val()
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
        	grid.options.sortName='createtime';
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