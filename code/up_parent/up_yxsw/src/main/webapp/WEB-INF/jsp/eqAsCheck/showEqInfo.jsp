<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>新增检查项定义</title>
<%@include file="/system/common/header_splitmode.jsp" %>
<link href="${path }/css/core.css" rel="stylesheet" type="text/css"/>
<!-- 表单验证start -->
<script type="text/javascript" src="${path}/libs/js/form/form.js"></script>
<script type="text/javascript" src="${path }/libs/js/form/validationRule.js"></script>
<script type="text/javascript" src="${path }/libs/js/form/validation.js"></script>
<script type="text/javascript" src="${path }/libs/js/table/quiGrid.js"></script>

<script type="text/javascript">
	jQuery(function(){
		jQuery(".to_hide_over_content").on("click",function(){
			var tipicon = jQuery.trim(jQuery(this).find("span:eq(2)").text());
			if(tipicon=="∨"){
				
				jQuery(this).find("span:eq(0)").text("展开");
				jQuery(this).find("span:eq(2)").text("∧");
				jQuery(this).next(".detail-list").hide();
			}else{
				jQuery(this).find("span:eq(0)").text("隐藏");
				jQuery(this).find("span:eq(2)").text("∨");
				jQuery(this).next(".detail-list").show();
			}
		});
	});
</script>
</head>
<body>

   <h1>dwf</h1>
	<div class="details-max">
		<div class="list-content">
    	 <div class="details-container">
         	<div class="task-detail">
            	 <span class="detail-title">
                	 设备基本信息
                 </span>
                 <div class="to_hide_over_content" class="text">
          			<p><span>隐藏</span><span>&nbsp;</span><span>∨</span></p>
        		 </div> 
                 <table class="detail-list" >
                 	<tr>
                 		<th>设备名称：</th>
                 		<td>${parentName }</td>
                 		<th>安装地址：</th>
                 		<td>${bizTSbTypesEntity.name }</td>
                 	</tr>
                 	<tr>
                 		<th>设备型号：</th>
                 		<td>${bizTSbTypesEntity.code }</td>
                 		<th>计量单位：</th>
                 		<td>${bizTSbTypesEntity.unitname }</td>
                 	</tr>
                 	<tr>
                 		<th>设备编号：</th>
                 		<td>${bizTSbTypesEntity.layer }</td>
                 		<th>必须扫码：</th>
                 		<td>${bizTSbTypesEntity.orders }</td>
                 	</tr>
                 	<tr>
                 		<th>性能参数：</th>
                 		<td>${bizTSbTypesEntity.outservicename }</td>
                 		<th>结构原理：</th>
                 		<td>	
                 		<td>${bizTSbTypesEntity.remark }</td>
                 		</td>
                 	</tr>
                 	
                 </table>
               </div>
              <!--  <div>
                 
                 <span class="detail-title">
                                                       关联检查项
                 </span>
                 <div class="to_hide_over_content" class="text"><p><span>隐藏</span><span>&nbsp;</span><span>∨</span></p> </div> 
               </div>
			<div class="padding_right5">
				<div id="dataBasic"></div>
			</div> -->
             </div>
		</div>
    </div>
    <!-- 关联检查项页面 -->

                  <div class="padding_right5">
				       <div id="dataBasic"></div>
				       <h1>efwg</h1>
			     </div>

</body>
<script type="text/javascript">

var grid = null;

//框架初始化函数
function initComplete(){
  
	initGrid();
}
 
//初始化grid组件 暂时不判断用户类型
function initGrid(){
	grid = $("#dataBasic").quiGrid({
		columns:[	
				    { display: '指标项CODE', name: 'cxzb_code', align: 'left', width: "20%", showTitle:true,
				    	/* render: function (rowdata, rowindex, value, column){
			    			var html = "<a href='javascript:;' onclick=onView('" + rowdata.order_id + "');>" + value + "</a>";
			    			return html;
	                 	} */
				    },
				   { display: '指标项名称', name: 'cxzb_name', align: 'center', width:"20%", showTitle:true},
				   { display: '计量单位', name: 'cxzb_unit', type:"jldw"  , align: 'center', width:"20%", showTitle:true},
				    { display: '输入类型', name: 'cxzb_tblx', type:"inputlx", align: 'center', width: "20%",  showTitle:true},
				   // { display: '公告是否有效', name: 'fault_address', align: 'left', width: "25%", showTitle:true},
				    { display: '指标项说明', name: 'cxzb_desc',type:"",   align: 'left', width: "20%", showTitle:true},
				   // { display: '是否重要公告', name: 'order_receive_org_name', align: 'center', width: 90, showTitle:true},
				    { display: '操作',	isAllowHide: false, align: 'left', width:80,
						/*  render: function (rowdata, rowindex, value, column){
	                 	    var html='<div class="padding_top4 padding_left5">'
	                                 + '<span class="img_list hand" title="查看" onclick="onView(\'' + rowdata.waring_id + '\')"></span>';
	                                 if(isModifyAble && "admin" != rowdata.loginid){
	                                	 html += '<span class="img_edit hand" title="修改" onclick="onEdit(\'' + rowdata.waring_id + '\')"></span>';
	                                 }
	                                 	 html += '<span class="img_delete hand" title="删除" onclick="onDelete(\'' + rowdata.waring_id+'\')"></span>'
									if("admin" != rowdata.loginid){
										html += '<span class="img_relate hand" title="关联角色" onclick="onRelateRole(\'' + rowdata.waring_id+'\')"></span>'
									}
	                                 	 
	                             + '</div>';
	                             return html;
		                 } */
		                 
		                 
		                 render:function(rowdata,rowindex,value,column){
		                	 var html='<div class="padding_top4 padding_left5">'
		                		 + '<span class="img_list hand" title="查看" onclick="onView(\'' + rowdata.cxzb_id + '\')"></span>';
		                		 html +='<span class="img_edit hand" title="修改" onclick="upInd(\'' + rowdata.cxzb_id + '\')"></span>' 
		                		 html += '<span class="img_delete hand" title="删除" onclick="onDelete(\'' + rowdata.cxzb_id+'\')"></span>'
		                		 
		                		 return html;
		                	 
		                 } 
		            }
				  
				  
				   
	    		],
	    url: '${path}/indicative/getIndList',
	    params:params,
	    rownumbers:true,
	    checkbox:true,
	    selectRowButtonOnly:false,
	    height: '100%',
	    width:"100%",
	    pageSize:20,
	    percentWidthMode:true,		 
	    toolbar:{
	           	 items:[
	           	        {text: '新建指标项', click: addInd, iconClass: 'icon_add'},
	           	    
					   ]
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

function addInd(){
	
location.href="${path}/indicative/toAddInd";
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
if(result == 1){
	Dialog.alert("删除成功！",null,null,null,1);
	grid.loadData();
}else if(result==0){
	Dialog.alert("关联了检查项模板，不能删除");
}else{	
	Dialog.alert("删除失败")
}
}
//发送(新增)消息
function addUnit(){
	var diag = new Dialog();
	diag.Title = "发送系统消息";
	diag.URL = "${path}/message/toAddMessage";
	diag.ShowButtonRow=true;
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
删除一列
*/
function onDelete(rowid){
	Dialog.confirm("确定要删除该记录吗？",function(){
	  	//删除记录
	  	$.post("${path }/indicative/delOneInd",
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
</html>