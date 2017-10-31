<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="path" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>查看日志主页</title>
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

<!-- 日期选择框start -->
<script type="text/javascript" src="${path}/libs/js/form/datePicker/WdatePicker.js"></script>

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

</head>
<body>
<div id="publicCon">
        <div id="centerCon" position="center" style="">
        	<div id="myTestDiv" class="box2" panelTitle="查询" showStatus="false">
			<form action="/log/getLogData" id="queryForm" method="post">
			<input type="hidden" id="parentOrgId" name="parentOrgId" value=""/>
			<table align="center">
				<tr>
					<td>操作人：</td>
					<td>
						<input type="text" id="optusername" name="optusername"/>
					</td>
					<td>操作模块：</td>
					<td>
						<select labelField="data1" prompt="请选择" valueField="code" name="optmodelcode" url="${path}/common/getSupportData" params='{"dataId":"sysDict","condition":"parentnodeid=${LOG_OPT_MODEL }"}'></select>
					</td>
					<td>操作类型：</td>
					<td>
						<select labelField="data1" prompt="请选择" valueField="code" name="opttypecode" url="${path}/common/getSupportData" params='{"dataId":"sysDict","condition":"parentnodeid=${LOG_OPT_TYPE }"}'></select>
					</td>
				</tr>
				<tr>
					<td>模块所属系统：</td>
					<td>
						<select labelField="data1" prompt="请选择" valueField="code" name="belongsystemcode" url="${path}/common/getSupportData" params='{"dataId":"sysDict","condition":"parentnodeid=${LOG_BELONG_SYSTEM }"}'></select>
					</td>
					<td>操作时间：</td>
					<td colspan="3">
						<input type="text" class="date" name="optTimeStart" id="optTimeStart"  dateFmt="yyyy-MM-dd" style="width:140px" readonly="readonly" value=""   onFocus="WdatePicker({isShowClear:false})" />
<!-- 					</td> -->
<!-- 					<td> 至 </td> -->
<!-- 					<td> -->
						至
						<input type="text" class="date" name="optTimeEnd" id="optTimeEnd" dateFmt="yyyy-MM-dd" style="width:140px" readonly="readonly" value=""   onFocus="WdatePicker({isShowClear:false})" />
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
	//定义grid
	var grid = null;
	
	
	//初始化函数
	function initComplete(){
		//当提交表单刷新本页面时关闭弹窗
		Dialog.close();
		
		//初始化grid组件
		initGrid();
		
		//监听查询框的回车事件
		 $("#searchInput").keydown(function(event){
		 	if(event.keyCode==13){
				searchHandler();
			}
		 });
		
	}
	
	//初始化Grid处理
	function initGrid() {
		grid = $("#dataBasic").quiGrid({
			columns:[
				{ display: '操作人',	name: 'opt_user_name',	align: 'center',width: "5%"},
				{ display: '操作人单位',	name: 'opt_unit_name',	align: 'center',width: "15%"},
			    { display: '操作内容',	name: 'opt_content',	align: 'left', width: "25%",showTitle:true},
			    { display: '操作时间',	name: 'opt_time',	align: 'center', width: "20%",type:'opt_time'},
			    { display: '操作模块',	name: 'model_name',	align: 'center', width: "10%", showTitle:true},
			    { display: '所属系统',	name: 'belong_system_name',	align: 'center', width: "10%", showTitle:true},
			    { display: '操作类型',	name: 'opt_type_name',	align: 'center', width: "10%", showTitle:true},
           		{ display: '操作',	isAllowHide: false, align: 'center', width:"5%",
						 render: function (rowdata, rowindex, value, column){
	                 	    return '<div class="padding_top4 padding_left5">'
	                                 + '<span class="img_list hand" title="查看" onclick="onView(\'' + rowdata.id + '\')"></span>'
	                            	 + '</div>';
		                 }
		            }
			  ],
		 url: '${path }/log/getLogData',
		 sortName: 'opt_time',sortOrder:'desc',
		 rownumbers:true,checkbox:false,
         height: '100%', width:"100%", pageSize:10, percentWidthMode:true,selectRowButtonOnly:false
		});
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
			URL:"${path }/log/toViewLog?logId=" + rowid,
			Title:"查看",Width:600, Height:230});
	}
	
	//导入
	function showImportDialog(){
		Dialog.open({Title:"导入日志信息", 
						Message:"请上传excel文件", 
						URL:"${path }/plug/qui/sample/unit/uploadFile.jsp?type=1",
						Width:350,Height:130});
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
		var pagerSize = grid.options.pageSize;;
		var sort = grid.options.sortName;
		var direction = grid.options.sortOrder;
		var parentid = $("#parentId").val();
		var userName = $("#userName").val();
		var url = "${path }/plug/qui/userAction.do?method=exportData";
		if(isPage){
			url += "&pager.pageSize=" + pagerSize;
			url += "&pager.pageNo=" + pageNo;
			url += "&sort=" + sort;
			url += "&direction=" + direction;
			url += "&isPage=1";
		}else{
			url += "&isPage=0";
		}
		url += "&parentId=" + parentid;
		url += "&userinfor.userName" + userName;
		
		window.location = url;
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
    	$("#queryForm select").resetValue();
    	jQuery("#optusername").val("");
    	jQuery("#optTimeStart").val("");
    	jQuery("#optTimeEnd").val("");
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

	//Grid时间row格式化
	jQuery.quiDefaults.Grid.formatters['opt_time'] = function (value, column) {
		var year = value.substring(0,4);
		var month =  value.substring(4,6);
		var day = value.substring(6,8);
		var hour = value.substring(8,10);
		var minute = value.substring(10,12);
		var second = value.substring(12,14);
		var time = year + "-" + month + "-" + day + " " + hour + ":" + minute+ ":" + second;
		return time;
	}
	
</script>
</body>
</html>