<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>公告管理管理</title>
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
			<form action="${path}/noticeManage/getNoticeData" id="queryForm" method="post">
				<table align="center" style="width:100%;">
					<tr>
						<td  style="width:15%;text-align:right">标题：</td>
						<td  style="width:20%;text-align:left"><input type="text" id="title" name="title"  value="${title }"  style="width:200px;"/></td>
						<td  style="width:15%;text-align:right">公告类型：</td>
						<td  style="width:20%;text-align:left"><select labelField="value" valueField="key" selWidth="200" name="ggType" prompt="请选择" selectedValue="${ggType }" url="${path}/platform/getSupportData" params='{"parentNodeId":"${gg_type }"}'></select></td>
					</tr>
					<tr>
						<td  style="width:15%;text-align:right">重要程度：</td>
						<td  style="width:20%;text-align:left"><select labelField="value" valueField="key" selWidth="200" name="importantLevel" prompt="请选择" selectedValue="${importantLevel }" url="${path}/platform/getSupportData" params='{"parentNodeId":"${important_level }"}'></select></td>
						<td  style="width:15%;text-align:right">是否有效：</td>
						<td  style="width:20%;text-align:left"><select labelField="value" valueField="key" selWidth="200" name="isAlive" prompt="请选择" selectedValue="${isAlive }" url="${path}/platform/getSupportData" params='{"parentNodeId":"${is_alive }"}'></select></td>
					</tr>
					<tr>
						<td  style="width:15%;text-align:right">发布时间：</td>
						<td  style="width:20%;text-align:left">
							<input type="text" class="date" name="startTime" id="startTime"    dateFmt="yyyy-MM-dd" style="width:90px" readonly="readonly" value="${startTime }"   onFocus="WdatePicker({isShowClear:false})" />
							至<input type="text" class="date" name="endTime" id="endTime"    dateFmt="yyyy-MM-dd" style="width:90px" readonly="readonly" value="${endTime }"   onFocus="WdatePicker({isShowClear:false})" />
						</td>
						<td></td><td></td>
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
		var params = [
						{name:"title",value:"${title }"},
						{name:"ggType",value:"${ggType }"},
						{name:"importantLevel",value:"${importantLevel }"},
						{name:"startTime",value:"${startTime }"},
						{name:"endTime",value:"${endTime }"},
						{name:"isAlive",value:"${isAlive }"}
			]
		//初始化grid组件
		initGrid(params);
	}
	
	//初始化grid组件
	function initGrid(params){
		grid = $("#dataBasic").quiGrid({
			columns:[	
					    { display: '标题', name: 'title', align: 'left', width: "50%",showTitle:true ,
					    	render: function (rowdata, rowindex, value, column){
				    			var html = "<a href='javascript:;' onclick=onView('" + rowdata.notice_id + "');>" + value + "</a>";
				    			return html;
		                 }
					    },
					    { display: '重要程度', name: 'important_level_name', align: 'center', width: "5%"},
					    { display: '公告类型', name: 'gg_type_name', align: 'center', width: "10%"},
					    { display: '是否有效', name: 'is_alive_name', align: 'center', width: "5%"},
					    { display: '有效期', name: 'limit_date', align: 'center', width: "10%",type:"limit_date"},
					    { display: '发布时间', name: 'create_timestemp', align: 'center', width: "10%",type:"create_timestemp"},
					    { display: '操作', isAllowHide: false, align: 'center', width:"10%",
					    	render: function (rowdata, rowindex, value, column){
								 var html ='<div class="padding_top4 padding_left5">'
	                               	 + '<span class="img_list hand" title="查看" onclick="onView(\'' + rowdata.notice_id + '\')"></span>'
	                                 + '<span class="img_edit hand" title="修改" onclick=editNotice(\''+rowdata.notice_id+'\')></span>'
	                               	 + '</div>';
	                               	
	                               	 return html;
			                 }
					    }
		    		],
		    url: '${path}/noticeManage/getNoticeData',rownumbers:true,checkbox:true,selectRowButtonOnly:false,params:params,
		    height: '100%', width:"100%", pageSize:20, percentWidthMode:true,		 
		    toolbar:{
		           	 items:[
		           	        {text: '新增', click: addNotice, iconClass: 'icon_add'},
		           	        {line  : true },
		           	     	{text: '设为无效', click: stopNotice, iconClass: 'icon_delete'},
		           	        {line  : true }
						   ]
		   }
		});
	}
	
	//格式化时间
	jQuery.quiDefaults.Grid.formatters['create_timestemp'] = function (value, column) {
		var time = timeFormatting(value);
		return time;
	}
	//格式化时间
	jQuery.quiDefaults.Grid.formatters['limit_date'] = function (value, column) {
		var time = timeFormatting(value);
		return time;
	}
	//格式化时间
	function timeFormatting(value){
		var time = "";
		if(value != null){
			var year = value.substring(0,4);
			var month =  value.substring(4,6);
			var day = value.substring(6,8);
			var hour = value.substring(8,10);
			var minute = value.substring(10,12);
			var second = value.substring(12,14);
			
			 time = year + "-" + month + "-" + day + " "; 
			 if(null != hour && hour != ""){
				 time = time + hour;
			 }
			 if(null != minute && minute != ""){
				 time = time + ":" + minute;
			 }
			 if(null != second && second != ""){
				 time = time + ":" + second;
			 }
		}
		return time;
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
		$("#title").val("");
		$("#content").val("");
		$("#startTime").val("");
		$("#endTime").val("");
		$("#queryForm select").attr("selectedValue","");
		$("#queryForm select").resetValue();
		searchHandler();
		
	}
	
	//查看
	function onView(id){
		var ggType = jQuery("select[name='ggType']").val();
		var importantLevel = jQuery("select[name='importantLevel']").val();
		var isAlive = jQuery("select[name='isAlive']").val();
		var title = jQuery("#title").val();
		var startTime = jQuery("#startTime").val();
		var endTime = jQuery("#endTime").val();
		var backURL = "${path}/noticeManage/init?ggType="+ggType+"&importantLevel="+importantLevel+"&isAlive="+isAlive
				+"&title="+title+"&startTime="+startTime+"&endTime="+endTime;
		location.href = "${path}/noticeManage/noticeDetail?id="+id+"&backURL="+encodeURIComponent(backURL);
	}
	
	//新增公告
	function addNotice(){
		location.href = "${path}/noticeManage/toAddNotice";
	}
	
	//单个或者批量停用
	function stopNotice() {

	    var rows = grid.getSelectedRows();
	    var rowsLength = rows.length;
	    var ids = "";
	    var flag = true;
	    if(rowsLength == 0) {
	        Dialog.alert("请选中要设为无效的公告!");
	        return;
	    }
	    for(var i = 0 ; i < rowsLength ; i++ ){
	    	ids += ( rows[i].notice_id + "," );
	    	isalive = rows[i].is_alive;
	    	if(isalive == "0"){
	    		flag = false;
	    		break;
	    	}
	    }
	    if(flag){
	    	
		    Dialog.confirm("确定要设为无效吗？",function(){
		            $.post("${path}/noticeManage/stopNotice",
		                    //获取所有选中行
		                    { "ids" : ids.substring(0,ids.length - 1)},
		                    function(result){
		                        handleResult(result.status);
		                    },
		                    "json");
		        });
	    }else{
	    	Dialog.alert("选中的公告已经被设为无效!");
	    }
	}
	
	//标记、删除后的提示
	function handleResult(result){
			Dialog.alert(result,null,null,null,5);
			grid.loadData();
	}
	
	//修改公告
	function editNotice(id){
		var ggType = jQuery("select[name='ggType']").val();
		var importantLevel = jQuery("select[name='importantLevel']").val();
		var isAlive = jQuery("select[name='isAlive']").val();
		var title = jQuery("#title").val();
		var startTime = jQuery("#startTime").val();
		var endTime = jQuery("#endTime").val();
		var backURL = "${path}/noticeManage/init?ggType="+ggType+"&importantLevel="+importantLevel+"&isAlive="+isAlive
				+"&title="+title+"&startTime="+startTime+"&endTime="+endTime;
		location.href = "${path}/noticeManage/toModifyNotice?id="+id+"&backURL="+encodeURIComponent(backURL);
	}
	
	//刷新表格数据并重置排序和页数
    function refresh(){
    	grid.loadData();
    }
</script>
</body>
</html>