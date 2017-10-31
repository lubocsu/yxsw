<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="path" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>树的编辑</title>
<!--框架必需start-->
<script type="text/javascript" src="${path }/libs/js/jquery.js"></script>
<script type="text/javascript" src="${path }/libs/js/language/cn.js"></script>
<script type="text/javascript" src="${path }/libs/js/framework.js"></script>
<script type="text/javascript" src="${path }/libs/js/form/datePicker/WdatePicker.js"></script>
<script type="text/javascript" src="${path }/libs/js/form/validationRule.js"></script>
<script type="text/javascript" src="${path }/libs/js/form/validation.js"></script>
<script type="text/javascript" src="${path }/libs/js/table/quiGrid.js"></script>
<script type="text/javascript" src="${path }/libs/js/form/form.js"></script>
<script type="text/javascript" src="${path }/libs/js/popup/drag.js"></script>
<script type="text/javascript" src="${path }/libs/js/popup/dialog.js"></script>

<link rel="stylesheet" type="text/css" href="${path }/libs/css/import_basic.css"/>
<link rel="stylesheet" type="text/css" id="skin" prePath="${path }/"/>
<link rel="stylesheet" type="text/css" id="customSkin"/>
<script type="text/javascript" src="${path }/libs/js/form/upload/fileUpload.js"></script>
<script type="text/javascript" src="${path }/libs/js/form/upload/handlers.js"></script>
<script type="text/javascript" src="${path }/js/plug-in/customupload.js"></script>

<script type="text/javascript">
      var attachmentList=JSON.stringify(CustomUploadCallBackData_UseQUI);
	var path = "${path}";
function initComplete(){
    //表单提交
    $('#myFormId').submit(function(){
    	console.log("${path }/warning/doAddWarning?attachmentList="+JSON.stringify(CustomUploadCallBackData_UseQUI));	
            var valid = $('#myFormId').validationEngine({returnIsValid: true});
            if(valid){
               $(this).ajaxSubmit({
                    //表单提交成功后的回调
                    success: function(responseText, statusText, xhr, $form){
                        top.Dialog.alert(responseText.msg,function(){
                        	closeWin();
                        });
                    }
                }); 
             }
            //阻止表单默认提交事件
            return false; 
    });
}

//重置
function closeWin(){
    //刷新数据
    parent.refresh(false);
    //关闭窗口
    parent.Dialog.close();
}

//提供给父页面调用提交表单
function submitThisForm() {
	jQuery("#myFormId").submit();
}
 

</script>

<style type="text/css">
._address {
	width: 440px !important;
}

.selectbox {
	width: 107px !important;
}

textarea {
	width: 90% !important;
	height: 200px !important;
}

input[type="text"] {
	width: 130px;
}

input[type="password"] {
	width: 130px;
}
</style>
</head>
<%-- <body>
<form action="${path }/warning/doAddWarning?attachmentList="+JSON.stringify(CustomUploadCallBackData_UseQUI)   method="post" id="myFormId">
	<div panelWidth="1200px" style="margin-right: 5px !important;">
	
	<fieldset > 
		<legend>安全提醒定义</legend> 
		<table class="tableStyle" formMode="transparent" footer="normal">
		
			<tr>
				
				<td style="width: 10%">安全提醒标题：</td>
				<td style="width: 40%"><input type="text" name="title" class="validate[required,length[0,25],ajax[${path }/warning/validLoginId|* 输入标题过长]]" width:70% /><span style="color: red;"> *</span></td>
			<td style="width: 40%"><input type="text" name="title" class="validate[required,length[0,25]]" width:70% /><span style="color: red;"> *</span></td>
				<td style="width: 10%"></td>
				<td style="width: 40%"></td>
			</tr>
			<tr>
				<td  style="text-align:right">提醒类型：</td>
				<td  style="width:25%;text-align:left"><select  selWidth="143" class="validate[required]"  labelField="value"  name="warningType" prompt="请选择" selectedValue="${warningType }" url="${path}/platform/getSupportData" params='{"parentNodeId":"UM000008"}'></select></td>
				<td  style="width:15%;text-align:right">是否重要提醒：</td>
				<td  style="width:25%;text-align:left"><select  selWidth="143"  class="validate[required]"  labelField="value"  name="is_important" prompt="请选择" selectedValue="${is_important }" url="${path}/platform/getSupportData" params='{"parentNodeId":"UM000006"}'></select></td>
			</tr>
			<tr>
    			<td>安全提醒内容：</td>
				<td colspan="3">
				<textarea style="width:1200px;  class="validate[length[0,25]]"   height:600px" name="content" ></textarea>
				</td>
				</tr>
		</table>
	</fieldset> 
	
	</div>
 	<div>
 	<th style="width:10%;">附件上传：</th>
 		<div>
 			<span id="uploadBtn"></span>
	  		<div id="uploadList"></div>
 		</div>
 	</div> 
 	<script type="text/javascript">
		var upload1 = new CustomUpload();
		upload1.init("safeIndicative",new Date().getTime(),{
			"btn" : "uploadBtn",
			"listDiv" : "uploadList",
			"fileSize" : '2048000',
			"fileTypes" : '*.*'
		});
	</script>
</body> --%>
</html>