<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>新增设备类型</title>
<!--分离模式框架必需start-->
<%@include file="/system/common/header_splitmode.jsp" %>
<link href="${path }/css/core.css" rel="stylesheet" type="text/css"/>

<link href="${path}/libs/js/tree/ztree/ztree.css" rel="stylesheet" type="text/css"/>
<script src="${path}/libs/js/tree/ztree/ztree.js" type="text/javascript" ></script>
<script src="${path }/libs/js/form/validationRule.js" type="text/javascript"></script>
<script src="${path }/libs/js/form/validation.js" type="text/javascript"></script>
<script src="${path }/js/plug-in/stringutil.js" type="text/javascript"></script>
<!-- 表单验证end -->
</head>
<body>
	<div class="details-max">
		<div class="list-content">
			<form id="myFormId" action="" method="post" target="frmright">
	    	 <div class="details-container">
	         	<div class="task-detail">
	            	 <span class="detail-title">
	                	 设施档案清单
	                 </span>
	                 <div class="to_hide_over_content" class="text">
	          			<p><span>隐藏</span><span>&nbsp;</span><span>∨</span></p>
	        		 </div> 
		                 <table class="detail-list" id="ssInfoDetailTable">
		                 	<tr>
		                 		<th>父类设施：</th>
		                 		<td>
		                 			<input class="textbox" name="parentName" id="parentName" value="${ssName }" type="text" readonly="readonly" style="width:180px;"/>
									<input type="hidden" name="parentId" id="parentId" value="${ssId }" />
									<input type="button" onclick="selectParent();" value="选" class="small_button" />
		                            <input type="button" onclick="clearParent();" value="清" class="small_button" />
		                 		</td>
		                 		<th><span><em style="color:red;">*</em></span>设施名称：</th>
		                 		<td>
									<input type="text" name="name" id="name" class="validate[required,length[0,42]]" value="" style="width:180px;"/>
		                 		</td>
		                 	</tr>
		                 	<tr>
		                 		<th><span><em style="color:red;">*</em></span>设施编号：</th>
		                 		<td><input type="text" name="code" id="code" class="validate[required,custom[notChinese],length[0,10],ajax[${path }/ssbase/validateSSCode | * 设施编号已存在]]" value="" style="width:180px;"/></td>
		                 		<th>是否扫二维码：</th>
		                 		<td><select labelField="value" valueField="key" selWidth="180" name="byzd" id="byzd" url="${path}/platform/getSupportData" params='{"parentNodeId":"${DELFLAG_DICT }"}' ></select></td>
		                 	</tr>
		                 	<tr>
		                 		<th>功能说明：</th>
		                 		<td colspan="3">
									<textarea maxNum="682" id="_function" name="_function"  value=""  style="width:50%;height:110px;"/></textarea>
								</td>
		                 	</tr>
		                 	<tr>
								<th>备注：</th>
								<td colspan="3">
									<textarea maxNum="682" name="remark"  style="width:50%;height:110px;"/></textarea>
								</td>
							</tr>
		                 </table>
	            	</div>
	         	</div>
			</form>
		</div>
		<!-- 底部按钮 -->
		<div class="list-btm">
	    	<div class="list-btm-div">
	            <div class="div-btn-sbt">
	            	<button class="btm-button" type="button"  onclick="doAdd();"><span class="ok-icon">保存</span></button>
	            </div>
	        </div>
	    </div>
     </div>
     
<script type="text/javascript">

	jQuery(function(){
		/***解决core中定义的td样式影响qui的td样式***/
		  jQuery(".mainCon td .selectbox").parent().css("width","auto");
		  jQuery(".mainCon td .selBtn").css("height","auto");
		  jQuery(".mainCon td .selBtn").css("width","11px");
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
	
	function selectParent(){
		
		Dialog.open({
			  ID:"c1",
		      InnerHtml: "",
		      Title:"选择父设施",
		      Width:340,
		      Height:400,
		});
		var $container=$(document.getElementById("_Container_c1"));
		$("#_Container_c1").css("overflow","auto");
	    var $tree=$(' <ul id="tree" class="ztree"></ul>');
	    var treeSetting = {
				view: {
					dblClickExpand: true
				},
				callback: {
					onClick: treeClick,
					beforeClick:function(treeId, treeNode, clickFlag){
						if(null==treeNode.code&&null==treeNode.delflag){
							return false;
						}else{
							return true;
						}
					}
				}
		};
		
		jQuery.ajax({
			type : 'POST',
			url : '${path}/ssbase/getSSTree',
			dataType : 'json',
			success : function(nodes){
			   var treeObj =  $.fn.zTree.init($tree, treeSetting, nodes);
			    $container.append($tree)
			},
			error : function(a){
				Dialog.open("获取设备类型失败");
			}
		});
	}
	
	function treeClick(event,treeId,treeNode){
		var id = treeNode.id;
		var name = treeNode.name;
		jQuery("#parentId").val(id);
		jQuery("#parentName").val(name);
		
		Dialog.close();
	}
		
	function clearParent(){
		jQuery("#parentId").val("");
		jQuery("#parentName").val("");
	}
	
	function doAdd(){
		$('#myFormId').validationEngine();
		var valid = $("#myFormId").validationEngine({returnIsValid: true});
		if(valid){
			$.ajax({
	            cache: true,
	            type: "POST",
	            url:'${path}/ssbase/doAdd',
	            data:$('#myFormId').serialize(),
	            error: function(request) {
	            	parent.Dialog.alert("提交失败");
	            },
	            success: function(responseText, statusText, xhr, $form){
	            	
	            	parent.Dialog.alert(responseText.message, function(){
	            		if(responseText.flag){
	            			var newNode = {};
	            			newNode.id = responseText.data.ssId;
	            			newNode.parentId = responseText.data.parentId;
	            			newNode.name = responseText.data.name;
	            			newNode.code = responseText.data.code;
	            			newNode.delflag = responseText.data.delflag;
	            			
		                	//刷新数据
		                	parent.refreshNewNode(newNode);
		                    //关闭窗口
		                    parent.Dialog.close();
		                    parent.jQuery("#ss-detail-iframe").attr("src","${path}/ssbase/showDetail?ssId="+ newNode.id + "&parentName=" + $("#parentName").val() );
	            		}else{
	            			 Dialog.close();
	            		}
	                });
	            }
	        });
		}
	}
	
</script>
</body>
</html>