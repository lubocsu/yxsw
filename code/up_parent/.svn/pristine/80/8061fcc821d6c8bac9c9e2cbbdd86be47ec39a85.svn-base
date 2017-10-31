<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
<script type="text/javascript" src="${path }/libs/js/tree/ztree/ztree.js"></script>
<script type="text/javascript" src="${path }/libs/js/form/selectTree.js"></script>
<script type="text/javascript" src="${path }/libs/js/form/validationRule.js"></script>
<script type="text/javascript" src="${path }/libs/js/form/validation.js"></script>
<script type="text/javascript" src="${path }/libs/js/form/form.js"></script>
<script type="text/javascript" src="${path }/libs/js/popup/drag.js"></script>
<script type="text/javascript" src="${path }/libs/js/popup/dialog.js"></script>
<!-- 数字步进器start -->
<script type="text/javascript" src="${path }/libs/js/form/stepper.js"></script>

<link rel="stylesheet" type="text/css" href="${path }/libs/js/tree/ztree/ztree.css"></link>
<link rel="stylesheet" type="text/css" href="${path }/libs/css/import_basic.css"/>
<link rel="stylesheet" type="text/css" id="skin" prePath="${path }/"/>
<link rel="stylesheet" type="text/css" id="customSkin"/>
<!--框架必需end-->
<script type="text/javascript">

jQuery(function(){
	 jQuery("input[type='radio'][name='status'][value='${user.status}']").attr('checked','checked');
});

function initComplete(){
    //表单提交
    $('#myFormId').submit(function(){
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
    /* 屈锐华  */
    jQuery("#alterPwdBTN").click(function(){
    	alterPwdDialog=new top.Dialog();
		alterPwdDialog.Title = "修改密码";
		alterPwdDialog.URL = "${path }/user/toAlterUserPwd?path=${path}&userId=${user.userid}";
		alterPwdDialog.Width=350;
		alterPwdDialog.Height=200;
		alterPwdDialog.Left="60%";
		alterPwdDialog.show(); 
	 });
    
    //重置密码
    jQuery("#resetPWD").click(function(){
    	top.Dialog.confirm("是否将用户密码重置为000000",function(){
    		$.ajax({
    			type : 'POST',
    			url : '${path }/user/resetUserPWD',
    			data : {
    				userId:'${userId}',
    			},
    			success : function(result){
    				top.Dialog.alert("重置密码成功");
    			},
    			error : function(a){
    				Dialog.alert("重置密码失败");
    			},
    			dataType : 'json'
    		});
    	},
    	function(){
    		
    	});
	 });
}

//重置
function closeWin(){
    //刷新数据
    top.frmright.refresh(true);
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
	width: 440px !important;
	height: 60px !important;
}

input[type="text"] {
	width: 130px;
}

input[type="password"] {
	width: 130px;
}
</style>
</head>
<body>
	<form action="${path }/user/doAddUser" method="post" id="myFormId">
	<input type="hidden" name="userId" value="${user.userid }">
	<input type="hidden" name="userName" value="${user.username }">
	<input type="hidden" name="loginId" value="${user.loginid }">
	<input type="hidden" name="sex" value="${user.sex }">
	<input type="hidden" name="password" value="${user.password }">
	<div panelWidth="650" style="margin-right: 5px !important;">
	<div class="height_2"></div>
	<fieldset> 
		<legend>用户基本信息</legend> 
		<table class="tableStyle" formMode="transparent" footer="normal">
			<tr>
				<td width="17%">姓名：</td>
				<td width="33%">${user.username }</td>
				<td width="17%">性别：</td>
				<td width="33%"><c:if test="${user.sex eq '0'}">男</c:if><c:if test="${user.sex eq '1'}">女</c:if></td>
			</tr>
			<tr>
				<td>用户名：</td>
				<td>${user.loginid }</td>
				<td>所属机构：</td>
				<td>
				<div name="orgId" class="selectTree validate[required]" selectedvalue="${user.orgid }" boxHeight="100" url="${path }/org/getOrgTree"></div><span style="color: red;">　*</span>
				</td>
			</tr>
			<tr>
				<td>登录密码：</td><td>******&nbsp;<button type="button" id="alterPwdBTN">修改密码</button></td>
				<c:if test="${isRoot }">
					<td>重置密码：</td>
					<td><button type="button" id="resetPWD">重置密码</button></td>
				</c:if>
<!-- 				<td>帐号有效期：</td> -->
<%-- 				<td><input type="text" name="_validDate" class="date" value="<fmt:formatDate value="${user.validdate }" pattern="yyyy-MM-dd"/>"/></td> --%>
			</tr>
		</table>
	</fieldset> 
	<div class="height_2"></div>
	<fieldset> 
		<legend>用户详细信息</legend> 
		<table class="tableStyle" formMode="transparent" footer="normal">
			<tr>
				<td width="17%">手机串号：</td>
				<td width="33%"><input type="text" name="idCard" class="validate[length[0,32]]" value="${user.idcard }" /></td>
				<td width="17%">生日：</td>
				<td width="33%"><input type="text" name="_birthday" class="date"/></td>
<!-- 				<td width="17%">身份证号：</td> -->
<!-- 				<td width="33%"><input type="text" name="idCard" class="validate[custom[onlyNumber],length[18,18]]"/></td> -->
			</tr>
			<tr>
				<td>邮箱：</td>
				<td><input type="text" name="email" class="validate[custom[email],length[0,48]]" value="${user.email }"/></td>
				<td>传真：</td>
				<td><input type="text" name="fax" class="validate[length[0,48]]" value="${user.fax }"/></td>
			</tr>
			<tr>
				<td>办公电话：</td>
				<td><input type="text" name="officeTel" class="validate[length[0,22]]" value="${user.officetel }"/></td>
				<td>移动电话：</td>
				<td><input type="text" name="mobileTel" class="validate[length[0,22]]" value="${user.mobiletel }"/></td>
			</tr>
			<tr>
				<td>联系地址：</td>
				<td colspan="3"><input type="text" name="address" class="_address validate[length[0,32]]" value="${user.address }"/></td>
			</tr>
		</table>
	</fieldset> 
	<div class="height_2"></div>
	<fieldset> 
		<legend>用户其他信息</legend> 
		<table class="tableStyle" formMode="transparent" footer="normal">
			
			<tr>
				
				<td>状态：</td>
				<td><input type="radio" id="radio-status-1" name="status" value="1" class="hand" checked="checked"/><label for="radio-status-1" class="hand">可用</label>
					<input type="radio" id="radio-status-0" name="status" value="0" class="hand"/><label for="radio-status-0" class="hand">停用</label>
<!-- 					<input type="radio" id="radio-status-2" name="status" value="2" class="hand"/><label for="radio-status-2" class="hand">删除</label> -->
				</td>
				<td width="17%">是否系统用户：</td>
				<td width="33%">
					<select name="isSysUser" selectedValue="${user.issysuser }" data='{"list":[{"value":"1","key":"是"},{"value":"0","key":"否"}]}'></select>
				</td>				
			</tr>
			<tr>
				<td>岗位：</td>
				<td><select name="workPost" selectedValue="${user.workpost }"  url="${path }/dict/findDictItems?treeId=${dict.USER_WORK_POST }" prompt="请选择"></select></td>
				<td width="17%">排序号：</td>
				<td width="33%"><input type="text" name="orderNo" value="${orderNo }" class="stepper validate[custom[onlyNumber],length[0,4]]"/></td>
			</tr>
			<tr>
<!-- 				<td width="17%">用户类型：</td> -->
<%-- 				<td width="33%"><select name='_userType' class="validate[required]" url="${path }/dict/findDictItems?treeId=${dict.USER_TYPE }" selectedValue="${user.usertype }" prompt="请选择"></select> --%>
<!-- 				<span style="color: red;"> *</span></td> -->
				<td width="17%">QQ帐号：</td>
				<td width="33%"><input type="text" name="qq"  class="validate[custom[qq],length[0,12]]" value="${user.qq }"/></td>
			</tr>
<!-- 			<tr> -->
<!-- 				<td width="17%">用户工种：</td> -->
<%-- 				<td width="33%"><select name="occupation"  url="${path }/dict/findDictItems?treeId=${dict.USER_WORK_TYPE }" selectedValue="${user.occupation }" prompt="请选择"></select> --%>
<!-- 			</tr> -->
			<tr>
				<td>备注：</td>
				<td colspan="3">
					<textarea style="width:400px;" name="description" class="validate[length[0,100]]" maxNum="100">${user.description }</textarea>
				</td>
			</tr>
		</table>
	</fieldset>
	<div class="height_2"></div>
	</div>
</form>
</body>
</html>