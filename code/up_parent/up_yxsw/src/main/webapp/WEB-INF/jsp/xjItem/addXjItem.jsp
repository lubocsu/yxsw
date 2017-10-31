<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="path" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>新增巡检点</title>
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
	<form id="myFormId" action="${path }/xjItem/doAddXjItem" method="post" target="frmright">
		<table class="tableStyle" formMode="transparent">
			<tr>
				<td><span><em style="color:red;">*</em></span>巡检点名称：</td>
				<td>
					<input class="validate[required,length[0,40]]" name="xjdItemName" id="xjdItemName" value="" type="text"  style="width:200px;"/>
				</td>
			</tr>
			<tr>
				<td><span><em style="color:red;">*</em></span>巡检点位置：</td>
				<td>
					<input class="validate[required,length[0,80]]" name="xjdItemAddress" id="xjdItemAddress" value="" type="text"  style="width:200px;"/>
				</td>
			</tr>
			<tr>
				<td>RFID编号：</td>
				<td>
					<input class="validate[custom[noSpecialCaracters],length[0,32],ajax[${path }/xjItem/validateRfidCode|* RFID编号已存在]]" name="rfidCode" id="rfidCode" value="" type="text"  style="width:200px;"/>
				</td>
			</tr>
			<tr>
				<td>巡检点说明：</td>
				<td>
					<textarea maxNum="40" name="xjdItemDesc" class="validate[length[0,40]]" style="width:270px;height:110px;"/></textarea>
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
         }else{
        	 return;
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