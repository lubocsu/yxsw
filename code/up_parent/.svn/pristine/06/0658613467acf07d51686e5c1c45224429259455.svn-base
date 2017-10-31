<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="path" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>修改数据字典</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!--框架必需start-->
<link rel="stylesheet" type="text/css" href="${path }/libs/css/import_basic.css"/>
<link rel="stylesheet" type="text/css" id="skin" prePath="${path }/" scrollerY="false"/>
<link rel="stylesheet" type="text/css" id="customSkin"/>
<script type="text/javascript" src="${path }/libs/js/jquery.js"></script>
<script type="text/javascript" src="${path }/libs/js/language/cn.js"></script>
<script type="text/javascript" src="${path }/libs/js/framework.js"></script>
<!--树组件start -->
<link rel="stylesheet" type="text/css" href="${path }/libs/js/tree/ztree/ztree.css"/>
<script type="text/javascript" src="${path }/libs/js/tree/ztree/ztree.js"></script>
<!--布局控件start-->
<script type="text/javascript" src="${path }/libs/js/nav/layout.js"></script>
<!--数据表格start-->
<script type="text/javascript" src="${path }/libs/js/table/quiGrid.js"></script>
<!-- 表单start -->
<script type="text/javascript" src="${path }/libs/js/form/form.js"></script>
<!-- 表单验证start -->
<script type="text/javascript" src="${path }/libs/js/form/validationRule.js"></script>
<script type="text/javascript" src="${path }/libs/js/form/validation.js"></script>
<!-- 树形下拉框start -->
<script type="text/javascript" src="${path }/libs/js/form/selectTree.js"></script>
<!-- 数字步进器start -->
<script type="text/javascript" src="${path }/libs/js/form/stepper.js"></script>
<style type="text/css">
	#myFormId { margin-top: 10px;}
</style>
</head>
<body>
	<form id="myFormId" action="${path }/dict/addDict" method="post" target="frmright">
		<table class="tableStyle" formMode="transparent">
			<tr>
				<td>字典名称：</td>
				<td colspan="3">
					<input type="hidden" name="treeId" value="${dictBean.treeId }">
					<input type="text" name="treeDescription" class="validate[required,length[0,16]" value="${dictBean.treeDescription }" style="width:353px;"/>
					<span style="color: red;"> *</span>
				</td>
			</tr>
			<tr>
				<td>字典类型：</td>
				<td>
					<select prompt="请选择" name="treeType" class="validate[required]" url="${path }/dict/findDictItems?treeId=${dict.DICT_TYPE }" id="treeType" selectedValue="${dictBean.treeType }"></select>
					<span class="star">*</span>
				</td>
				<td>限制级别：</td>
				<td>
					<select prompt="请选择" name="levelLimit" class="validate[required]" url="${path }/dict/findDictItems?treeId=${dict.DICT_LIMIT }" id="levelLimit"  selectedValue="${dictBean.levelLimit }"></select>
					<span class="star">*</span>
				</td>
			</tr>
			<tr>
				<td>排序号：</td>
				<td colspan="3">
					<input type="text" name="orderNo"  min="1" max="9999" class="stepper" value="${dictBean.orderNo }"  style="width:353px;"/>
				</td>
			</tr>
			<tr>
				<td>备注：</td>
				<td colspan="3">
					<textarea name="remark" maxNum="200" class="validate[length[0,200]]" style="width:350px; height:50px;">${dictBean.remark }</textarea>
				</td>
			</tr>
		</table>
	</form>
<script type="text/javascript">
function initComplete(){
	//表单提交
	$('#myFormId').submit(function(){ 
	    //判断表单的客户端验证是否通过
        var valid = $('#myFormId').validationEngine({returnIsValid: true});
        if(valid){
           $(this).ajaxSubmit({
                //表单提交成功后的回调
                success: function(responseText, statusText, xhr, $form){
                	parent.Dialog.alert(responseText.msg, function(){
                    	///如果添加成功，更新Ztree
	                    if(responseText.status){
	                    	var node = responseText.node;
	                    	top.frmright.addNode({name: node.treeDescription, id: node.treeId}, responseText.curPage);
	                    }
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
</script>
</body>
</html>