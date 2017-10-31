<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="path" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>用户管理主页</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!--框架必需start-->
<link href="${path }/libs/css/import_basic.css" rel="stylesheet" type="text/css"/>
<link rel="stylesheet" type="text/css" id="skin" prePath="${path }/" scrollerY="false"/>
<link rel="stylesheet" type="text/css" id="customSkin"/>
<script type="text/javascript" src="${path }/libs/js/jquery.js"></script>
<script type="text/javascript" src="${path }/libs/js/language/cn.js"></script>
<script type="text/javascript" src="${path }/libs/js/main.js"></script>
<!--框架必需end-->

<!--分离模式-框架必需start-->
<script type="text/javascript" src="${path }/libs/js/framework.js"></script>
<!--分离模式-框架必需end-->

<!--分离模式-弹窗组件start-->
<script type="text/javascript" src="${path }/libs/js/popup/drag.js"></script>
<script type="text/javascript" src="${path }/libs/js/popup/dialog.js"></script>
<!--分离模式-弹窗组件end-->

<!--树组件start -->
<link href="${path }/libs/js/tree/ztree/ztree.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="${path }/libs/js/tree/ztree/ztree.js"></script>
<!--树组件end -->

<!--布局控件start-->
<script type="text/javascript" src="${path }/libs/js/nav/layout.js"></script>
<!--布局控件end-->

<!--数据表格start-->
<script type="text/javascript" src="${path }/libs/js/table/quiGrid.js"></script>
<!--数据表格end-->

<!-- 表单start -->
<script type="text/javascript" src="${path }/libs/js/form/form.js"></script>
<!-- 表单end -->

<!--数字分页start-->
<script type="text/javascript" src="${path }/libs/js/nav/pageNumber.js"></script>
<!--数字分页end-->

<script language="javascript" src="${path }/plug/lodop/LodopFuncs.js"></script> 
<object  id="LODOP_OB" classid="clsid:2105C259-1E0C-4534-8141-A753534CB4CA" width=0 height=0>  
	<embed id="LODOP_EM" type="application/x-print-lodop" width=0 height=0></embed> 
</object> 
<style type="text/css">
.img_relate {
	background-image: url(${path }/image/relate.png);
    width: 20px;
    height: 16px;
    display:inline-block;
}
.permissionData {
	background-image: url(${path }/image/permissionConfig.png);
    width: 16px;
    height: 16px;
    display:inline-block;
}
</style>

</head>
<body>
<div id="publicCon">
        <div id="leftCon" position="left" style="" panelTitle="组织机构目录">
        	<div class="orgTreeContainer">
        		<ul id="tree" class="ztree"></ul>
        	</div>
        </div>
        <div id="centerCon" position="center" style="">
        	<div id="myTestDiv" class="box2" panelTitle="查询" showStatus="false">
			<form action="/user/getUserData" id="queryForm" method="post">
			<input type="hidden" id="parentOrgId" name="parentOrgId" value=""/>
			<table align="center">
				<tr>
					<td>姓名：</td>
					<td>
						<input type="text" id="nameInput" name="userName"/>
						<input type="text" style="width:2px;display:none;"/>
					</td>
					<td>用户名：</td>
					<td>
						<input type="text" id="accountInput" name="loginId"/>
						<input type="text" style="width:2px;display:none;"/>
					</td>
					<td>用户工种：</td>
					<td>
						<select name="occupation"  url="${path }/dict/findDictItems?treeId=D000018" prompt="请选择"></select>
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
	//定义grid
	var grid = null;
	//定义选中的树节点
	var selectTreeNode = null;
	
	//定义树节点初始数据
	var nodes =[
		{ id:"0", parentId:-1, name:"组织机构目录", open:true, icon:"${path }/libs/icons/home.gif"}
	];
	
	//树属性配置
	var selectionSetting = {
			view: {
				dblClickExpand: true
			},
			callback: {
				onClick: zTreeSelect
			}
	};
	//初始化函数
	function initComplete(){
		//当提交表单刷新本页面时关闭弹窗
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
			}
		 });
		
	}
	
	//初始化tree处理
	function initTree() {
		$.ajax({
			type : 'POST',
			url : '${path }/org/getOrgTree',
			data : {
				path:'${path}',
			},
			success : function(result){
				nodes = nodes.concat(result);
				$.fn.zTree.init($("#tree"), selectionSetting, nodes);
			},
			error : function(a){
				Dialog.alert("访问服务器端出错！");
			},
			dataType : 'json'
		});
	}
	
	//初始化Grid处理
	function initGrid() {
		grid = $("#dataBasic").quiGrid({
			columns:[
				{ display: '姓名',	name: 'username',	align: 'center',	width: "15%"},
			    { display: '用户名',	name: 'loginid',	align: 'center', 	width: "15%"},
			    { display: '用户类型',	name: 'usertypes',	align: 'center', 	width: "15%"},
			    { display: '用户工种',	name: 'occuptions',	align: 'center', 	width: "15%"},
			    { display: '所属机构',	name: 'orgname',	align: 'center', 	width: "20%"},
			    { display: '办公电话',	name: 'officetel',	align: 'center', 	width: "20%"},
           		{ display: '操作',	isAllowHide: false, align: 'left', width:120,
						 render: function (rowdata, rowindex, value, column){
	                 	    var html='<div class="padding_top4 padding_left5">';
			                 	   	if("admin" != rowdata.loginid){
			                                 html+= '<span class="img_list hand" title="查看" onclick="onView(\'' + rowdata.userid + '\')"></span>';
									}
	                                if(isModifyAble && "admin" != rowdata.loginid){
	                                	 html += '<span class="img_edit hand" title="修改" onclick="onEdit(\'' + rowdata.userid + '\')"></span>';
	                                }
									if("admin" != rowdata.loginid){
										html += '<span class="img_relate hand" title="关联角色" onclick="onRelateRole(\'' + rowdata.userid+'\')"></span>'
										html += '<span class="permissionData hand" title="数据权限分配" onclick=configPermissionData(\''+rowdata.userid+'\',\''+rowdata.username+'\')></span>';
										html += '<span class="permissionData hand" title="查看完整数据权限" onclick=toPermissionData(\''+rowdata.userid+'\',\''+rowdata.username+'\')></span>';
									}
									
	                                 	 
	                             + '</div>';
	                             return html;
		                 }
		            }
			  ],
		 url: '${path }/user/getUserData',sortName: 'orgname,orgno',rownumbers:true,
		 checkbox:true,selectRowButtonOnly:false,
         height: '100%', width:"100%", pageSize:20, percentWidthMode:true,
         toolbar:{
        	 items:[
        		  {text: '新增', click: addUser,    iconClass: 'icon_add',visible:isAddAble},
        		  { line : true },
        		  {text: '删除', click: deleteUnit, iconClass: 'icon_delete',visible:isRemoveAble},
        		  { line : true },
        		  {text: '导入', click: showImportDialog, iconClass: 'icon_import',visible:false},
        		  { line : true },
        		  {text: '导出当前页', click: exportPageData, iconClass: 'icon_export'},
        		  { line : true },
        		  {text: '导出全部', click: exportTotalData, iconClass: 'icon_export'},
        		  { line : true }
//         		  {text: '打印', click: printData, iconClass: 'icon_print'}
        		]
         	},
         	onCheckRow:function(checked, rowdata, rowindex){
         		if("admin"==rowdata.loginid){
         			Dialog.alert("你不能对管理用户进行操作");
         			grid.unselect(rowindex);
         		}
         	}
		});
	}
	
	//新增
	function addUser() {
		var diag = new Dialog();
		diag.Title = "新增用户";
		diag.URL = "${path }/user/toAddUser?path='${path}'&orgId="+$("#parentOrgId").val();
		diag.ShowButtonRow=true;
		diag.Height = 521;
		diag.Width = 650;
		diag.OkButtonText=" 确 定 ";
		diag.OKEvent = function(){
			diag.innerFrame.contentWindow.submitThisForm();
		};
		diag.show();
	}
	//打印
	function printData(){
		var LODOP=getLodop(document.getElementById('LODOP_OB'),document.getElementById('LODOP_EM'));
		var strBodyStyle="<style>${path }/libs/css/import_basic.css</style>";
		var strFormHtml= strBodyStyle + jQuery("#publicCon").html();
		LODOP.ADD_PRINT_HTM(10,55,"100%","100%",strFormHtml);
		LODOP.PREVIEW();
	}
	//查看
	function onView(rowid){
		Dialog.open({
			URL:"${path }/user/toViewUser?userId=" + rowid,
			Title:"查看",Width:650, Height:520});
	}
	//修改	
	function onEdit(rowid){
		var diag = new Dialog();
		diag.Title = "修改用户";
		diag.URL = "${path }/user/toModifyUser?userId=" + rowid;
		diag.ShowButtonRow=true;
		diag.Height = 521;
		diag.Width = 650;
		diag.OkButtonText=" 确 定 ";
		diag.OKEvent = function(){
			diag.innerFrame.contentWindow.submitThisForm();
		};
		diag.show();
	}
	//删除	
	function onDelete(rowid){
		Dialog.confirm("确定要删除该记录吗？",function(){
		  	//删除记录
		  	$.post("${path }/user/delUser",
		  			{"ids":rowid},
		  			function(result){
		  				handleResult(result.status);
					},"json");
			});
	}
	
	//关联角色
	function onRelateRole(rowid){
		
		var diag = new Dialog();
		diag.Title = "角色选择器";
		diag.URL = "${path }/user/toRelateRole?userId=" + rowid;
		diag.ShowButtonRow=true;
		diag.Height = 320;
		diag.Width = 495;
		diag.OkButtonText="确定";
		diag.OKEvent = function(){
			diag.innerFrame.contentWindow.submitThisForm();
		};
		diag.show();
		diag.addButton("next","重置",function(){
			diag.innerFrame.contentWindow.resetForm();
		});
	}
	
	//分配数据权限
	function configPermissionData(userid, userName){
		var diag = new Dialog();
		diag.Title = "数据权限配置";
		diag.Height = 350;
		diag.Width = 400;
		diag.URL = "${path }/permissionData/toUserManage?userId=" + userid+"&userName=" + userName;
// 		diag.ShowButtonRow=true;
// 		diag.OkButtonText=" 确定 ";
// 		diag.CancelButtonText="取消";
// 		diag.OKEvent = function(){
// 			//调用子页面submitForm 方法
// 			diag.innerFrame.contentWindow.addSelectPermissions();
// 		};
		diag.show();
	}
	
	//查看数据权限
	function toPermissionData(userid, userName){
		var diag = new Dialog();
		diag.Title = "查看完整的数据权限";
		diag.Height = 350;
		diag.Width = 400;
		diag.URL = "${path }/permissionData/toFullPermissionData?userId=" + userid+"&userName="+userName;
		diag.show();
	}
		
	//批量删除
	function deleteUnit() {
		var rows = grid.getSelectedRows();
		var rowsLength = rows.length;
		if(rowsLength == 0) {
			Dialog.alert("请选中要删除的记录!");
			return;
		}else{
			for (var i = 0; i < rows.length; i++) {
				if("admin" == rows[i].loginid){
					Dialog.alert("您要删除的行中包含管理员用户<br />　　　请剔除管理员用户");
					return;
				}
			}
		}
		Dialog.confirm("确定要删除吗？",function(){
			$.post("${path }/user/delUsers",
					//获取所有选中行
					getSelectIds(grid),
					function(result){
						handleResult(result.status);
					},
					"json");
		});
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
	
	//获取所有选中行获取选中行的id 格式为 ids=1&ids=2 
	function getSelectIds(grid) {
		var selectedRows = grid.getSelectedRows();
		var selectedRowsLength = selectedRows.length;
		var ids = "";
		
		for(var i = 0;i<selectedRowsLength;i++) {
			ids += selectedRows[i].userid + ",";
		}
		return {"ids":ids};
	}
	
	//导入
	function showImportDialog(){
		// 下载模板名称
		var templateName = "用户信息";
		// 需要导入数据对应实体名称（注意大小写）
		var entityName = "SysUser";
		Dialog.open({Title:"导入用户信息", 
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
		var nameInput = $("#nameInput").val();
		var accountInput = $("#accountInput").val();
		var parentOrgId = $("#parentOrgId").val();
		var url = "${path}/common/exportExcel";
		url += "?pager.pageSize=" + pagerSize;
		url += "&pager.pageNo=" + pageNo;
		url += "&sort=" + sort;
		url += "&direction=" + direction;
		url += "&serviceName=userServiceImpl";
		if (nameInput!=""){
			url += "&userName=" + nameInput;	
		}
		if (accountInput!=""){
			url += "&loginId=" + accountInput;	
		}
		if (parentOrgId!=""){
			url += "&parentOrgId=" + parentOrgId;	
		}
		url += "&status=1,0";
		url += "&fileName=用户信息.xls";
		window.location = url;
	}
	
	//点击树节点刷选对应的表格数据 
	function zTreeSelect(event, treeId, treeNode) {
		var query = null;
		selectTreeNode = treeNode;
		query = {
				'parentOrgId':treeNode.id,
				'userName':jQuery("#nameInput").val(),
				'loginId':jQuery("#accountInput").val()
				};
		$("#parentOrgId").val(treeNode.id);
		grid.setOptions({ params : query});
		    //页号重置为1
		    grid.setNewPage(1);
		    //刷新表格数据 
			grid.loadData();
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
    	jQuery("#nameInput").val("");
    	jQuery("#accountInput").val("");
    	$("#queryForm select").attr("selectedValue","");
		$("#queryForm select").resetValue();
		searchHandler();
    }
    
    //刷新表格数据并重置排序和页数
    function refresh(isUpdate){
    	if(!isUpdate){
    		//重置排序
        	grid.options.sortName='userid';
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

	jQuery.quiDefaults.Grid.formatters['sex'] = function (value, column) {
		if(value=='0'){
	 		return '男';
	 	}
	 	if(value=='1'){
	 		return '女';
	 	}
	};
	jQuery.quiDefaults.Grid.formatters['sex'] = function (value, column) {
		if(value=='0'){
	 		return '男';
	 	}
	 	if(value=='1'){
	 		return '女';
	 	}
	};
	
</script>
</body>
</html>