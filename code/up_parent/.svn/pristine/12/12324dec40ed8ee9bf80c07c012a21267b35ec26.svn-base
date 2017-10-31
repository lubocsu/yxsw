<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="path" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>新增数据项</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!--框架必需start-->
<script type="text/javascript" src="${path }/libs/js/jquery.js"></script>
<script type="text/javascript" src="${path }/libs/js/language/cn.js"></script>
<script type="text/javascript" src="${path }/libs/js/framework.js"></script>
<link rel="stylesheet" type="text/css" href="${path }/libs/css/import_basic.css"/>
<link rel="stylesheet" type="text/css" id="skin" prePath="${path }/" scrollerY="false"/>
<link rel="stylesheet" type="text/css" id="customSkin"/>
<!--树组件start -->
<link rel="stylesheet" type="text/css" href="${path }/libs/js/tree/ztree/ztree.css"/>
<script type="text/javascript" src="${path }/libs/js/tree/ztree/ztree.js"></script>
<!--布局控件start-->
<script type="text/javascript" src="${path }/libs/js/nav/layout.js"></script>
<!--数据表格start-->
<script type="text/javascript" src="${path }/libs/js/table/quiGrid.js"></script>
<!-- 组合下拉框start -->
<script type="text/javascript" src="${path }/libs/js/form/selectCustom.js"></script>
<!-- 表单start -->
<script type="text/javascript" src="${path }/libs/js/form/form.js"></script>
<!-- 表单验证start -->
<script type="text/javascript" src="${path }/libs/js/form/validationRule.js"></script>
<script type="text/javascript" src="${path }/libs/js/form/validation.js"></script>
<!-- 数字步进器start -->
<script type="text/javascript" src="${path }/libs/js/form/stepper.js"></script>
<style type="text/css">
#myFormId {margin-top: 10px;}
</style>
</head>
<body>
	<form id="myFormId" action="${path }/dict/addDictData" method="post" target="frmright">
		<table class="tableStyle" formMode="transparent">
			<tr>
				<td>父级名称：</td>
				<td>
					<%-- <input type="hidden" name="parentNodeId" value="${parentNodeId }"> --%>
					<div class="selectCustom validate[required]" boxWidth="272"
					id="selectCostom8" selWidth="272" name="parentNodeId">
				        <div>
				        <table>
				            <tr>
				                <td>字典名称：</td>
				                <td><input type="text" class="textinput" id="searchInput" /></td>
				            </tr>
				        </table>
				        </div>
				        <div id="customContent8" style="font-size:12px;"></div>
				    </div>
				    <span class="star">*</span>
				</td>
			</tr>
			<tr>
				<td>数据代码：</td>
				<td>
					<input type="text" name="code" class="validate[required,length[0,100],ajax[${path }/dict/checkCodeOrData?treeId=${parentNodeId }&validateName=code|* 数据代码已存在!]]" value="" style="width:270px;" /><span class="star">*</span>
				</td>
			</tr>
			<tr>
				<td>数据1：</td>
				<td>
					<input type="text" name="data1" class="validate[required,length[0,100],ajax[${path }/dict/checkCodeOrData?treeId=${parentNodeId }&validateName=data1|* 数据1已存在!]]" value=""  style="width:270px;"/><span class="star">*</span>
				</td>
			</tr>
			<tr>
				<td>数据2：</td>
				<td>
					<input type="text" name="data2" class="validate[length[0,100]]" value=""  style="width:270px;"/>
				</td>
			</tr>
			<tr>
				<td>数据3：</td>
				<td>
					<input type="text" name="data3" class="validate[length[0,100]]" value=""  style="width:270px;"/>
				</td>
			</tr>
			<tr>
				<td>排序号：</td>
				<td>
					<input type="text" name="OrderNo" class="stepper validate[custom[onlyNumber]]"  min="1" max="9999" value="${defaultNo }"  style="width:270px;"/>
				</td>
			</tr>
		</table>
	</form>
<script type="text/javascript">
var g5;
var defaultId = '${parentNodeId }';
var defaultName = '${parentNodeName }';

function initComplete(){
	g5 = $("#customContent8").quiGrid({
        columns: [ 
            { display: '父级名称', name: 'name', align: 'center',  width: "100%"}
        ], 
        url:"${path }/dict/findDataParentsGrid", pageSize: 4, rownumbers:true, checkbox:false, percentWidthMode:true,
        height: '190', width:"270", showPageInfo:false, onSelectRow:onSelectRowHandler
    });
	
	//设置默认值
	if(defaultName){
		 $("#selectCostom8").setValue(defaultId, defaultName);
	}
	
    $("#selectCostom8").bind("boxOpen", function(){
    	g5.resetHeight();
    	g5.resetWidth();
    	//默认搜索选择字典目录关键字
    	$("#searchInput").val(defaultName);
    	queryGrid(defaultName); 
    });
	
  	//组合下拉框查询
    $("#searchInput").keyup(function(event){
        event = window.event || event;
        var keyCode = event.keyCode || event.which || event.charCode;        
        if(keyCode != 13 && keyCode != 37 && keyCode != 39 && keyCode !=9 && keyCode !=38 && keyCode !=40 ){
           //keyCode == 9是tab切换键
           //每次输入时查询结果
           queryGrid($(this).val()); 
        }
    });
    
  	//表单提交
	$('#myFormId').submit(function(){ 
    	//判断表单的客户端验证是否通过
        var valid = $('#myFormId').validationEngine({returnIsValid: true});
        if(valid){
           $(this).ajaxSubmit({
                //表单提交成功后的回调
                success: function(responseText, statusText, xhr, $form){
                	parent.Dialog.alert(responseText.msg, function(){
                    	//刷新数据
                        top.frmright.refresh(true);
                    	//parent.refresh(true);
                        //关闭窗口
                        parent.Dialog.close();
                    });
                }
            }); 
         }
        //阻止表单默认提交事件
        return false; 
	});
}

function submitForm(){
	$('#myFormId').submit();
}

//选择数据表格时设置值到下拉框
function onSelectRowHandler(){
    var row=g5.getSelectedRow();
    $("#selectCostom8").setValue(row.id, row.name);
}

function queryGrid(value){
    var query=[{name:"treeDesc",value:""}];
    query[0].value=value;
    g5.setOptions({ params : query});
    g5.setNewPage(1);
    g5.loadData();
}

</script>
</body>
</html>