<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="path" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>是否正常超期</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!--框架必需start-->
<script type="text/javascript" src="${path }/libs/js/jquery.js"></script>
<script type="text/javascript" src="${path }/libs/js/language/cn.js"></script>
<script type="text/javascript" src="${path }/libs/js/framework.js"></script>
<link rel="stylesheet" type="text/css" href="${path }/libs/css/import_basic.css"/>
<link rel="stylesheet" type="text/css" href="${path }/css/style.css"/>
<link rel="stylesheet" type="text/css" id="skin" prePath="${path }/" scrollerY="false"/>
<link rel="stylesheet" type="text/css" id="customSkin"/>
<!--树组件start -->
<!-- 组合下拉框start -->
<script type="text/javascript" src="${path }/libs/js/form/selectCustom.js"></script>
<!-- 表单start -->
<script type="text/javascript" src="${path }/libs/js/form/form.js"></script>
<!-- 表单验证start -->
<!-- 表单验证start -->
<script src="${path }/libs/js/form/validationRule.js" type="text/javascript"></script>
<script src="${path }/libs/js/form/validation.js" type="text/javascript"></script>
<!-- 表单验证end -->
<script type="text/javascript" src="${path }/libs/js/form/validationRule.js"></script>
<script type="text/javascript" src="${path }/libs/js/form/validation.js"></script>
<!-- 数字步进器start -->
<script type="text/javascript" src="${path }/libs/js/form/stepper.js"></script>
<style type="text/css">
#myFormId {margin-top: 10px;}
/* table ._th {color:#09c;} */
</style>
</head>
<body>
	<form id="myFormId" action="${path }/patrollingRecords/isZcOverTime" method="post" target="frmright">
		<table class="tableStyle" formMode="transparent">
			<input type="hidden"  value="${cxTaskId }" name="cxTaskId" id="cxTaskId"/>
			<tr>
				<td><span><em style="color:red;">*</em></span>是否正常超期:</td>
				<td>
					<select labelField="value" valueField="key"class="validate[required]"  selWidth="180" name="isOverTime" id="isOverTime" prompt="请选择" selectedValue="" url="${path}/platform/getSupportData" params='{"parentNodeId":"${overTime }"}'></select>
				</td>
			</tr>
			<tr>
				<td><span><em style="color:red;">*</em></span>说明：</td>
				<td>
					<textarea maxNum="1000" name="overtime_content" class="validate[required,length[0,1000]]" id="overtime_content"  style="width:270px;height:110px;"/></textarea>
				</td>
			</tr>
		</table>
	</form>
<script type="text/javascript">
  	//表单提交
	$('#myFormId').submit(function(){ 
    	//判断表单的客户端验证是否通过
        var valid = $('#myFormId').validationEngine({returnIsValid: true});
        if(valid){
           $(this).ajaxSubmit({
                //表单提交成功后的回调
                success: function(responseText, statusText, xhr, $form){
                	parent.Dialog.alert(responseText.message, function(){
                    	//刷新数据
                        parent.refresh(true);
                        //关闭窗口
                        parent.Dialog.close();
                    });
                }
            }); 
         }
        //阻止表单默认提交事件
        return false; 
	});

function submitForm(){
	$('#myFormId').submit();
}
</script>
</body>
</html>