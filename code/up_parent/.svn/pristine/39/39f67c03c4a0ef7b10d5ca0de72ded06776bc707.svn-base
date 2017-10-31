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
        	<div class="box2" panelTitle="设备基本信息" showStatus="false">
			<form action="${path}/warning/getWarningList" id="queryForm" method="post">
			<input type="hidden" id="orderCloseFlag" name="orderCloseFlag" value="2"/>
			<input type="hidden" id="warningTypeDefault" name="warningTypeDefault" value="1,8,9"/>
			<input type="hidden" id="initStartTime" value="${initStartTime }" />
			<input type="hidden" id="initEndTime" value="${initEndTime }" />
				<div class="details-max">
		          <div class="list-content">
    	            <div class="details-container">
         	          <div class="task-detail">
			  <table class="detail-list" >
                 	<tr>
                 		<th width="300" >设备名称：</th>
                 		<td width="300">${sb.name}</td>
                 		<th width="300">安装地址：</th>
                 		<td width="300">${sb.address}</td>
                 	</tr>
                 	<tr>
                 		<th>设备型号：</th>
                 		<td>${sb.sbxh}</td>
                 		<th>结构原理：</th>
                 		<td>${sb.jgyl}</td>
                 	</tr>
                 	<tr>
                 		<th>设备编号：</th>
                 		<%-- <td>${bizTSbTypesEntity.layer }</td> --%>
                 		<td align="left">${sb.code}</td>
                 		<th>必须扫码：</th>
                 		<td>${sb.byzd}</td>
                 	</tr>
                 	<tr>
                 		<th>性能参数：</th>
                 		<td>${sb.xlcs}</td>
                 		<th></th>
                 		<td>	
                 	<%-- <td  style="display:none;" id="sbidsbid">${sb.sbId}</td>  --%>
                 	<%-- <input id="sbidsbid"  >${sb.sbId} </input> --%>
                 	<%-- <input type="hidden" id="sbidsbid" name="orderCloseFlag" value="${sb.sbId}"/>  --%>
                 	<input type="hidden" id="sbidsbid" name="orderCloseFlag" value="${sb.sbId}"/>
                 		<%-- <td >${sb.sbId}</td> --%>
                 		</td>
                 	</tr>
                 	
                 </table>
                 </div>
                 </div>
                 </div>
                 </div>
			</form>
			 <span class="detail-title"  style="color: green;font-weight:bold;font-size:20px">
                  已关联检查项
                 </span>
			<div class="padding_right5">
				<div id="dataBasic"></div>
			</div>
        </div>
	</div>
<script type="text/javascript">
	//var sbidsbid=$("#sbidsbid").val();
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
		//var sbidsbid=$("#sbidsbid").val();
		grid = $("#dataBasic").quiGrid({
			columns:[	
					    { display: '检查项编号', name: 'check_item_code', align: 'left', width: "33%", showTitle:true,
					    },
					   { display: '检查项名称', name: 'check_item_name', align: 'center', width:"33%", showTitle:true},
					   { display: '描述', name: 'check_item_desc',   align: 'center', width:"34%", showTitle:true},
					    { display: '操作',	isAllowHide: false, align: 'left', width:80,
			                 
			                 
			                 render:function(rowdata,rowindex,value,column){
			                	 var html='<div class="padding_top4 padding_left5">'
			                		// + '<span class="img_list hand" title="查看" onclick="onView(\'' + rowdata.cxzb_id + '\')"></span>';
			                		// html +='<span class="img_edit hand" title="修改" onclick="upInd(\'' + rowdata.cxzb_id + '\')"></span>' 
			                		 html += '<span class="img_delete hand" title="删除" onclick="onDelete(\'' + rowdata.check_item_id+'\')"></span>'
			                		 
			                		 return html;
			                	 
			                 } 
			            }
					  
					  
					   
		    		],
		   // url: '${path}/eqAsCheck/getList?sbId=4028802d5e80440d015e8045d22f0000',
				   url: '${path}/eqAsCheck/getList?sbId='+sbidsbid ,
						 // url: '${path}/eqAsCheck/getList' ,
		    params:params,
		    rownumbers:true,
		    checkbox:false,
		    selectRowButtonOnly:false,
		    height: '100%',
		    width:"100%",
		    pageSize:20,
		    percentWidthMode:true,		 
		    toolbar:{
		           	 items:[
		           	        {text: '新增', click: addInd, iconClass: 'icon_add'},
		           	    
						   ]
		   }
		});
	}
	
	/* //grid时间row格式化
    jQuery.quiDefaults.Grid.formatters['time'] = function (value, column) {
		
		return dateStr2Str(value,"$1-$2-$3 $4:$5");
	} */
	//对类型格式化
     $.quiDefaults.Grid.formatters['inputlx'] = function (value, column) {
		if(value=="1"){	
		return "普通文本"	;
		}else if (value=="2"){		
		return "数值";
		}else{
			
			return "出现未知错误";
		}
	}  
	//对计量单位格式化
     $.quiDefaults.Grid.formatters['jldw'] = function (value, column) {
 		if(value=="1"){	
 		return "kg"	;
 		}else if (value=="2"){		
 		return "m3";
 		}
 		else if (value=="3"){		
 	 		return "kw.h";
 	 		}
 		else if (value=="4"){		
 	 		return "mg/l";
 	 		}
 		else if (value=="5"){		
 	 		return "m";
 	 		}
 		else if (value=="6"){		
 	 		return "m2";
 	 		}
 		else if (value=="7"){		
 	 		return "%";
 	 		}
 		else if (value=="8"){		
 	 		return "￥";
 	 		}
 		else if (value=="9"){		
 	 		return "%";
 	 		}
 		else{
 			
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
		$("#cxzb_code").val("");
		$("#cxzb_name").val("");
		searchHandler();
		
	}
	
	 function onView(rowid){
			Dialog.open({
				URL:"${path }/indicative/toShowInd?cxzb_id=" + rowid,
				Title:"查看",Width:650, Height:480});
		}


	 /*跳转到另一个添加页面不需要弹窗*/
	
 	/* function addInd(){
		
	location.href="${path}/eqAsCheck/toAddCheck?sbId="+sbidsbid;
	} */

	 function addInd(){ 
	var diag = new Dialog();
	diag.Title = "添加检查项";
	//diag.URL = "${path}/message/toAddMessage";
	diag.URL='${path}/eqAsCheck/toAddCheck?sbId='+sbidsbid;
	//diag.URL="${path}/eqAsCheck/toAddCheck";
	diag.ShowButtonRow=false;
	diag.Height = 800;
	diag.Width = 800;
	//diag.OkButtonText=" 确 定 ";
	
	diag.show();
	} 
	 /*跳转到另一个修改页面不需要弹窗*/
	 function upInd(rowid){
		 location.href= "${path }/indicative/toUpInd?cxzb_id=" + rowid;
		 
	 }
	//批量删除
	function deleteUnit() {

	    var rows = grid.getSelectedRows();
	    var rowsLength = rows.length;
	    var messageIds = "";
	    if(rowsLength == 0) {
	        top.Dialog.alert("请选中要删除的消息!");
	        return;
	    }
	    for(var i = 0 ; i < rowsLength ; i++ ){
	    	messageIds += ( rows[i].messageid + "," );
	    }
	    top.Dialog.confirm("确定要删除吗？",function(){
	            $.post("${path}/message/deleteMessage",
	                    //获取所有选中行
	                    { "messageIds" : messageIds.substring(0,messageIds.length - 1)},
	                    function(result){
	                        handleResult(result.status);
	                    },
	                    "json");
	        });
	}
	
/*
 * 
   删除后提示
 */
function handleResult(result){
	if(result== 1){
		Dialog.alert("删除成功！",null,null,null,1);
		grid.loadData();
	}else if(result==2){
		Dialog.alert("删除失败请重试");
	}else{	
		Dialog.alert("删除失败请重试");
	}
}
	//发送(新增)消息
	function addUnit(){
		var diag = new Dialog();
		diag.Title = "发送系统消息";
		diag.URL = "${path}/message/toAddMessage";
		diag.ShowButtonRow=false;
		diag.Height = 180;
		diag.Width = 430;
		diag.OkButtonText=" 确 定 ";
		diag.OKEvent = function(){
			diag.innerFrame.contentWindow.submitThisForm();
		};
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
			$.post("${path }/warning/delWarning",
					//获取所有选中行
					getSelectIds(grid),
					function(result){
						handleResult(result.status);
					},
					"json");
		});
	}
	/* 
	删除一列通过检查项id
	*/
	function onDelete(rowid){
		Dialog.confirm("确定要删除该记录吗？",function(){
		  	//删除记录
		  	$.post("${path }/eqAsCheck/delOne",
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
			ids += selectedRows[i].cxzb_id+ ",";
			
			
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