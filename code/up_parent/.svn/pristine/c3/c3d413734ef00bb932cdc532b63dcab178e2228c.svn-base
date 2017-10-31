<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="path" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>日志详情</title>
<!--框架必需start-->
<script type="text/javascript" src="${path }/libs/js/jquery.js"></script>
<script type="text/javascript" src="${path }/libs/js/language/cn.js"></script>
<script type="text/javascript" src="${path }/libs/js/framework.js"></script>
<script type="text/javascript" src="${path }/libs/js/form/datePicker/WdatePicker.js"></script>
<script type="text/javascript" src="${path }/libs/js/tree/ztree/ztree.js"></script>
<script type="text/javascript" src="${path }/libs/js/form/selectTree.js"></script>
<script type="text/javascript" src="${path }/libs/js/form/validationRule.js"></script>
<script type="text/javascript" src="${path }/libs/js/form/validation.js"></script>

<link rel="stylesheet" type="text/css" href="${path }/libs/js/tree/ztree/ztree.css"></link>
<link rel="stylesheet" type="text/css" href="${path }/libs/css/import_basic.css"/>
<link rel="stylesheet" type="text/css" id="skin" prePath="${path }/"/>
<link rel="stylesheet" type="text/css" id="customSkin"/>
<!--框架必需end-->
<script type="text/javascript">
jQuery(function(){
	jQuery(".valshow").css("margin-right", "15px");
	jQuery(".valshow").each(function(i){
		jQuery(this).attr("title",jQuery(this).text());
		jQuery(this).addClass("textSlice");
	});
});
function initComplete(){
    //表单提交
    $('#myFormId').submit(function(){
            var valid = $('#myFormId').validationEngine({returnIsValid: true});
            if(valid){
               $(this).ajaxSubmit({
                    //表单提交成功后的回调
                    success: function(responseText, statusText, xhr, $form){
                        top.Dialog.alert(responseText.resultData.msg,function(){
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
    var update = false;
    var isupdate = '<c:out value="${userinfor.userId}"/>';
    if(isupdate != ''){
        update = true;
    }else{
        update = false;
    }
    //刷新数据
    top.frmright.refresh(update);
    //关闭窗口
    top.Dialog.close();
}

</script>

<style type="text/css">

textarea {
	width: 435px !important;
	height: 60px !important;
}

</style>
</head>
<body>
	<form action="${path }/user/doAddUser" method="post" id="myFormId" target="frmright">
	<div panelWidth="650"  style="margin-right: 5px !important;">
	<div class="height_15"></div>
		<table class="tableStyle" formMode="transparent" footer="normal">
			<tr>
				<td width="20%">操作人：</td>
				<td width="30%"><span class="valshow">${log.optusername }</span></td>
				<td width="20%">所属单位：</td>
				<td width="30%"><span class="valshow">${log.optunitname }</span></td>
			</tr>
			<tr>
				<td>操作日期：</td>
				<td><span class="valshow">
					<fmt:parseDate value="${log.opttime }"  var="opttime" pattern="yyyyMMddHHmmss"/>
					<fmt:formatDate value="${opttime }" pattern="yyyy-MM-dd HH:mm:ss"/>
				</span></td>
				<td>操作模块：</td><td><span class="valshow">${log.modelname }</span></td>
			</tr>
			<tr>
				<td>操作类型：</td><td><span class="valshow">${log.opttypename }</span></td>
				<td>操作内容：</td><td><span class="valshow">${log.optcontent }</span></td>
			</tr>
			<tr>
				<td>类名：</td>
				<td><span class="valshow">${log.actionclassname }</span></td>
				<td>方法名：</td>
				<td><span class="valshow">${log.actionfunctionname }</span></td>
			</tr>
			<tr>
				<td>所属系统：</td><td><span class="valshow">${log.belongsystemname }</span></td>
			</tr>
		</table>
	<div class="height_15"></div>
	</div>
</form>
</body>
</html>