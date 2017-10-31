<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="path" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>修改数据项</title>
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
					<input type="hidden" name="nodeId"  id="nodeId" value="${dictDataBean.nodeId }">
					<div class="selectCustom validate[required,functionCall[checkParentId|* 父级不能是本身或者子级节点!]]" boxWidth="270" id="selectCostom8" selWidth="270" name="parentNodeId">
				        <div>
				        <table>
				            <tr>
				                <td>字典名称：</td>
				                <td><input type="text" class="textinput" id="searchInput"/></td>
				            </tr>
				        </table>
				        </div>
				        <div id="customContent8" style="font-size:12px;"></div>
				    </div>
				</td>
			</tr>
			<tr>
				<td>数据代码：</td>
				<td>
					<input type="text" name="code" class="validate[length[0,100],ajax[${path }/dict/checkCodeOrData?treeId=${treeId }&nodeId=${dictDataBean.nodeId }&validateName=code|* 数据代码已存在!]]" value="${dictDataBean.code }" style="width:270px;" />
				</td>
			</tr>
			<tr>
				<td>数据1：</td>
				<td>
					<input type="text" name="data1" class="validate[length[0,100],ajax[${path }/dict/checkCodeOrData?treeId=${treeId }&nodeId=${dictDataBean.nodeId }&validateName=data1|* 数据1已存在!]]" value="${dictDataBean.data1 }"  style="width:270px;"/>
				</td>
			</tr>
			<tr>
				<td>数据2：</td>
				<td>
					<input type="text" name="data2" class="validate[length[0,100]]" value="${dictDataBean.data2 }"  style="width:270px;"/>
				</td>
			</tr>
			<tr>
				<td>数据3：</td>
				<td>
					<input type="text" name="data3" class="validate[length[0,100]]" value="${dictDataBean.data3 }"  style="width:270px;"/>
				</td>
			</tr>
			<tr>
				<td>排序号：</td>
				<td>
					<input type="text" name="OrderNo" class="stepper"  min="1" max="9999" value="${dictDataBean.orderNo }"  style="width:270px;"/>
				</td>
			</tr>
		</table>
	</form>
<script type="text/javascript">
var g5;
var defaultId = '${dictDataBean.parentNodeId }';
var defaultName = '${parentNodeName }';

function initComplete(){
	g5 = $("#customContent8").quiGrid({
        columns: [ 
            { display: '父级名称', name: 'name', align: 'center',  width: "50%"}
           /*  { display: '字典类型', name: 'treetype', align: 'center' , width: "20%"}, */
           /*  { display: '字典备注', name: 'remark',  align: 'center' , width: "50%"} */
        ], 
        url:"${path }/dict/findDataParentsGrid", pageSize: 5, rownumbers:true, checkbox:false, percentWidthMode:true,
        height: '220', width:"270", showPageInfo:false, onSelectRow:onSelectRowHandler
    });
	
	//设置默认值
	if(defaultName){
		 $("#selectCostom8").setValue(defaultId, defaultName);
	}
	
    $("#selectCostom8").bind("boxOpen", function(){
    	g5.resetHeight();
    	g5.resetWidth();
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
                        top.frmright.refresh(false);
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

/*
 * 检查父Id是否符合要求
 * 返回true 表示不通过
 */
function checkParentId(){
	var flag = false;
	var nodeId = $("#nodeId").val();
	var parentId = $("#selectCostom8").attr("relValue");
	//不能为本身
	if(nodeId == parentId){
		flag = true;
	}
	//异步验证不能为子级
	jQuery.ajax({
		url : "${path }/dict/getChildNodes",
		type : "post",
		dataType : "json",
		async: false,
		cache : false,
		data : {
			nodeId : nodeId
		},
		success : function(datas) {
			for(var i in datas){
				var id = datas[i].nodeId;
				if(id == parentId){
					flag = true;
					break;
				}
			}
		}
	});
	return flag;
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