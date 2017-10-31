<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>指标项主页</title>
<!-- 框架分离模式start -->
<link rel="stylesheet" type="text/css" href="${path }/libs/css/import_basic.css"/>
<link rel="stylesheet" type="text/css" id="skin" prePath="${path }/" splitMode="true" href="${path }/libs/skins/blue/style.css"/>
<link rel="stylesheet" type="text/css" id="customSkin" href="${path }/system/layout/skin/style.css"/>
<script type="text/javascript" src="${path }/libs/js/jquery.js"></script>
<script type="text/javascript" src="${path }/libs/js/language/cn.js"></script>
<script type="text/javascript" src="${path }/libs/js/framework.js"></script>
<script type="text/javascript" src="${path }/libs/js/main.js"></script>
<script type="text/javascript" src="${path }/libs/js/table/quiGrid.js"></script>
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
<!-- 树形下拉框start -->
<script type="text/javascript" src="${path }/libs/js/form/selectTree.js"></script>

<script type="text/javascript" src="${path }/js/dateutil.js"></script>

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

.ex-icon{
	width:1.2rem;
	height:1.2rem;
	text-align:center;
	line-height:1.2rem;
	border-radius:50%;
	background:#f00;
	color:#fff;
	border:solid 1px #f00;
	font-size:0.7rem;
	top:0;
	/* right:0.2rem; */
}
</style>
</head>
<body>
	<div id="layout1">
		<div id="centerCon" position="center" style="">
        	<div class="box2" panelTitle="查询" showStatus="false">
			<form action='${path}/eqAsCheck/getUnCheckList?sbId='+sbidsbid id="queryForm" method="post">
			<input type="hidden" id="orderCloseFlag" name="orderCloseFlag" value="2"/>
			<input type="hidden" id="warningTypeDefault" name="warningTypeDefault" value="1,8,9"/>
			<input type="hidden" id="initStartTime" value="${initStartTime }" />
			<input type="hidden" id="initEndTime" value="${initEndTime }" />
				<div class="details-max">
		          <div class="list-content">
    	            <div class="details-container">
         	          <div class="task-detail">
			  <table class="detail-list" >
                 
                 		<th></th>
                 		<td>	
                 
                 	<input type="hidden" id="sbidsbid" name="orderCloseFlag" value="${code.code}"/> 
                 		</td>
                 	</tr>
                 	<tr>
				
					<tr>
					
					<td  style="width:15%;text-align:right">检查项编号：</td>
					<td  style="width:15%;text-align:left"><input type="text" id="code" class="input119" onkeydown="if(event.keyCode==13){return false;}" name="code" value="${title }"/></td>
					
					<td  style="width:15%;text-align:right">检查项名称或说明：</td>
					<td  style="width:15%;text-align:left"><input type="text" id="name" class="input119" onkeydown="if(event.keyCode==13){return false;}" name="name" /></td>
				
					
					<td rowspan="2">
						<button type="button" onclick="searchHandler()" id="search"><span class="icon_find">查询</span></button>
						<button type="button" onclick="resetSearch()"><span class="icon_reload">重置</span></button>
					</td>
				</tr>
                 	
                 </table>
                 </div>
                 </div>
                 </div>
                 </div>
			</form>
		
			<div class="padding_right5">
				<div id="dataBasic"></div>
			</div>
        </div>
	</div>
<script type="text/javascript">

	var grid = null;
	var sbidsbid=$("#sbidsbid").val();
	//框架初始化函数
	function initComplete(){
		//初始化grid组件
		var params = [
						{name:"cxzb_name",value:"${cxzb_name }"},
						{name:"cxzb_tblx",value:"${cxzb_tblx }"},
						{name:"cxzb_unit",value:"${cxzb_unit }"},
						{name:"reportTimeEnd",value:"${reportTimeEnd }"},
						{name:"cxzb_code",value:"${cxzb_code}"}
				]
		initGrid(params);
	}
	
	//初始化grid组件 暂时不判断用户类型
	function initGrid(params){
	
		grid = $("#dataBasic").quiGrid({
			columns:[	
					    { display: '检查项编号', name: 'check_item_code', align: 'center', width: "33%", showTitle:true,
					    },
					   { display: '检查项名称', name: 'check_item_name', align: 'center', width:"33%", showTitle:true},
					   { display: '检查项说明', name: 'check_item_desc',   align: 'center', width:"34%", showTitle:true},
			
					  
					  
					   
		    		],
				   url: '${path}/SsCheck/getUnCheckList?ssId='+sbidsbid ,
		    params:params,
		    rownumbers:true,
		    checkbox:true,
		    selectRowButtonOnly:false,
		    height: '100%',
		    width:"100%",
		    pageSize:10,
		    percentWidthMode:true,		 
	
		});
	}

     $.quiDefaults.Grid.formatters['inputlx'] = function (value, column) {
		if(value=="1"){	
		return "普通文本"	;
		}else if (value=="2"){		
		return "数值";
		}else{
			
			return "出现未知错误";
		}
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
		$("#queryForm select").attr("selectedValue","");
		$("#queryForm select").resetValue();
		$("#name").val("");
		$("#code").val("");
		searchHandler();
		
	}
	
	//批量添加
	function add(refresh,unCkeckRowsLength) {
		var rows = grid.getSelectedRows();
		var rowsLength = rows.length;
		if (rowsLength == 0) {
			Dialog.alert("请选中添加记录!");
			return;
		} else {

			Dialog.confirm("确定要新增吗？", function() {
				$.post('${path }/SsCheck/addUnit?ssId=' + sbidsbid,
				//获取所有选中行
				getSelectIds(grid), function(result) {
					handleResult(result.status);
					if(unCkeckRowsLength==0){
	                       refresh();
	                      }
				}, "json");
			});
		}

	}

	
	
/*
 * 
   删除后提示
 */
function handleResult(result){
	 if(result== 1){
		Dialog.alert("添加成功！",null,null,null,1);
		
		grid.loadData();
		Dialog.close();
		parent.refresh(false);
	    //关闭窗口
	    parent.Dialog.close();
	}else if(result==2){
		Dialog.alert("添加失败请重试");
	}else{	
		Dialog.alert("添加失败请重试");
	} 
	
}
	

	/* 
	删除一列通过检查项id
	*/
	function onDelete(rowid){
		Dialog.confirm("确定要添加该记录吗？",function(){
		  	//删除记录
		  	$.post('${path }/SsCheck/addUnit?ssId='+sbidsbid,
		  			{"id":rowid},
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
			ids += selectedRows[i].check_item_id+ ",";
			
			
			console.log(selectedRows[i]);
		}
		return {"ids":ids};
	}
	//刷新表格数据并重置排序和页数
	function refresh(isUpdate){
		if(!isUpdate){
			//重置排序
	    	grid.options.sortName='uid';
	    	grid.options.sortOrder="desc";
	    	//页号重置为1
			grid.setNewPage(1);
		}
		grid.loadData();
	}
</script>

</body>
</html>