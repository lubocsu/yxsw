<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>组织机构修改</title>
<!--框架必需start-->
<script type="text/javascript" src="${path }/libs/js/jquery.js"></script>
<script type="text/javascript" src="${path }/libs/js/language/cn.js"></script>
<script type="text/javascript" src="${path }/libs/js/framework.js"></script>
<link href="${path }/libs/css/import_basic.css" rel="stylesheet" type="text/css"/>
<link rel="stylesheet" type="text/css" id="skin" prePath="${path }/"/>
<link rel="stylesheet" type="text/css" id="customSkin"/>
<!-- 日期控件start -->
<script type="text/javascript" src="${path }/libs/js/form/datePicker/WdatePicker.js"></script>
<!-- 树组件start -->
<script type="text/javascript" src="${path }/libs/js/tree/ztree/ztree.js"></script>
<link type="text/css" rel="stylesheet" href="${path }/libs/js/tree/ztree/ztree.css"></link>
<!-- 树形下拉框start -->
<script type="text/javascript" src="${path }/libs/js/form/selectTree.js"></script>
<!-- 表单验证start -->
<script src="${path }/libs/js/form/validationRule.js" type="text/javascript"></script>
<script src="${path }/libs/js/form/validation.js" type="text/javascript"></script>
<!-- 表单异步提交start -->
<script src="${path }/libs/js/form/form.js" type="text/javascript"></script>
<!-- 表单异步提交end -->
<!-- 表单异步提交start -->
<script type="text/javascript" src="${path }/libs/js/form/stepper.js"></script>
<!-- 数字步进器start -->
<style type="text/css">
.input_width{width: 250px;}
</style>
</head>
<body>
	<form id="myFormId" action="${path }/org/doModifyOrg" method="post" target="frmright">
	<div panelWidth="650"  style="margin-right: 5px !important;">
	<div class="height_15"></div>
	<table class="tableStyle" formMode="transparent">
		<tr>
			<td width="110">父级机构：</td>
			<td>
				<input type="hidden" />
				<!-- 修改弹出框 判断父级机构是否是顶级机构 -->
				<c:choose>
					<c:when test="${sysOrg.parentOrgId eq '0' }">
						<c:set var="sysOrg_parentOrgId" value="" />
					</c:when>
					<c:otherwise>
						<c:set var="sysOrg_parentOrgId" value="${sysOrg.parentOrgId}" />
					</c:otherwise>
				</c:choose>
				<div class="selectTree validate[functionCall[validateParentOrg|* 父级不能是本身或者子级节点]]" selWidth="250" name="parentOrgId" id="parentOrgId" selectedValue="${sysOrg_parentOrgId }"  url="${path }/org/getOrgTree?path=${path}"></div></span>
			</td>
		</tr>
		<tr>
			<td>机构代码：</td>
			<td>
				<input type="hidden" name="orgId" id ="orgId" value="${sysOrg.orgId }" />
				<input type="text" name="orgCode" value="${sysOrg.orgCode }" class="validate[length[0,30],ajax[${path }/org/checkOrgCode?orgId=${sysOrg.orgId }|* 机构代码已存在!]] input_width" id="orgCode"/>
			</td>
		</tr>
		<tr>
			<td>机构名称：</td>
			<td>
				<input type="hidden" id="old_orgName" value="${sysOrg.orgName }"/>
				<input type="text" name="orgName" value="${sysOrg.orgName }" class="validate[required,length[0,50]] input_width" id="orgName"/><span class="star">*</span>
			</td>
		</tr>
		<tr>
			<td>机构简称：</td>
			<td><input type="text" name="orgShrotName" value="${sysOrg.orgShrotName }" class="validate[length[0,40]] input_width" id="orgShrotName" /></td>
		</tr>
		<tr>
			<td>机构类型：</td>
			<td id="orgTypeTd"></td>
		</tr>
		
		<tr>
			<td>地　　址：</td>
			<td><input type="text" name="address" value="${sysOrg.address }" class="validate[length[0,50]] input_width"/></td>
		</tr>
		<tr>
			<td>邮　　编：</td>
			<td><input type="text" name="zip" value="${sysOrg.zip }" class="validate[custom[zipcode]] input_width"/></td>
		</tr>
		<tr>
			<td>负责人：</td>
			<td><div class="selectTree" selWidth="250" name="principal" selectedValue="${sysOrg.principal }" url="${path}/org/getOrgUserTree" id="principal"></div></td>
		</tr>
		<tr>
			<td>联系电话：</td>
			<td><input type="text" name="tel" value="${sysOrg.tel }" value="" class="validate[length[0,20]] input_width"/></td>
		</tr>
		<tr>
			<td>排序号：</td>
			<td>
				<input type="text" name="orderNo" value="${sysOrg.orderNo }" style="width: 250px;"  min="1" max="9999" class="validate[custom[onlyNumber]] stepper input_width"/>
			</td>
		</tr>
		<tr>
			<td>是否有效：</td>
			<td>
				<input type="radio" id="radio-1" name="enabled" <c:if test="${sysOrg.enabled eq '1' }">checked="checked"</c:if> value="1" /><label for="radio-1" class="hand">是</label>
				<input type="radio" id="radio-2" name="enabled" <c:if test="${sysOrg.enabled eq '0' }">checked="checked"</c:if> value="0" /><label for="radio-2" class="hand">否</label>
			</td>
		</tr>
		<tr>
			<td>备注：</td>
			<td><textarea name="description" class="input_width" watermark="请输入机构的备注说明" maxNum="1300">${sysOrg.description }</textarea></td>
		</tr>
	</table>
	</div>
	</form>
<!-- 异步提交start -->
<script type="text/javascript">
	function initComplete() {
		// 动态加载机构类型，只加载父级机构（有的话）类型以下的机构类型
		initOrgType(null);
		// 当父级机构发生变化时，要同时变化可选机构类型
		$("#parentOrgId").bind("change",function(){
			var node=$(this).data("selectedNode");
			initOrgType(node.orgTypeId);
	    }); 
		//表单提交
		$('#myFormId').submit(function() {
			// 判断机构名称是否改变
			var old_orgName = jQuery.trim(jQuery("#old_orgName").val());
			var orgName = jQuery.trim(jQuery("#orgName").val());
			//判断表单的客户端验证是否通过
			var valid = $('#myFormId').validationEngine({
				returnIsValid : true
			});
			if (valid) {
				$(this).ajaxSubmit({
					//表单提交成功后的回调
					success : function(responseText, statusText, xhr, $form) {
						parent.Dialog.alert(responseText.message, function() {
							closeWin(old_orgName,orgName);
						});
					}
				});
			}
			//阻止表单默认提交事件
			return false;
		});
	}

	function initOrgType(orgTypeId){
		if(orgTypeId==null){
			orgTypeId="${defaultOrgTypeId}";
		}
		$.post("${path }/dict/findDictItems",{"treeId":"${dict.ORG_TYPE }"},function(result){
			var $selTree = $("#orgTypeDiv");
			var prevLength = $selTree.length; 
			if(prevLength==0){
				$selTree = $('<select prompt="请选择" selWidth="250" name="orgTypeId"  id="orgTypeDiv"  class="validate[required]" selectedValue="${sysOrg.orgTypeId }"></select>');
			}
			 var orgTypeArr = new Array();
			 for ( var key in result) {
				 // 如果时修改机构，则初始化的机构类型有默认选择的类型，及初始化时条件为result[key].value>=orgTypeId
				var bool = null;
				if(prevLength==0){
					bool = (result[key].value>=orgTypeId);
				}else{
					bool = ((result[key].value>orgTypeId)||(result[key].value=="${default_orgtype_dept}"));
				}
				if(bool){
					orgTypeArr.push(result[key]);
				}
			}
		     $selTree.data("data",orgTypeArr);
		     if(prevLength==0){
			     $("#orgTypeTd").append($selTree);   
			     $("#orgTypeTd").append('<span class="star">*</span>'); 
		     }
		     //渲染树形下拉框 
		     $selTree.render();
		},"json");
	}
	
	// 提供给父页面调用提交表单
	function submitThisForm() {
		jQuery("#myFormId").submit();
	}
	
	//重置
	function closeWin(old_orgName,orgName) {
	    //刷新数据
	    top.frmright.refresh(true);
	    //只更新名称
	    if(old_orgName != orgName){
	    	top.frmright.modifyNodeName(old_orgName,orgName);
	    }else{
	    	// 发生树结构变动
	    	top.frmright.modifyNodeName(null,null);
	    }
	    //关闭窗口
	    parent.Dialog.close();
	}
	
	function validateParentOrg(){
		// 又有父级菜单（选中下拉树节点的id）
		var id = jQuery("#orgId").val();
		var node =jQuery("#parentOrgId").data("selectedNode");
		var preId = node.id;
		var preParentId = node.parentId;
		if(id == preId || id == preParentId){
			return true;
		}
		return false;
	}
</script>
<!-- 异步提交end -->	
</body>
</html>