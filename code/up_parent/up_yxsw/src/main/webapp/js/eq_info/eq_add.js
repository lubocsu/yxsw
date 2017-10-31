/**
 * 
 */
var dialog;	//选择厂商弹出窗
var delAttachments = [];

function initComplete(){
	
	/***解决core中定义的td样式影响qui的td样式***/
	jQuery(".mainCon td .selectbox").parent().css("width","auto");
	jQuery(".mainCon td .selBtn").parent().css("text-indent","0px");
	
	//绑定提交事件
	$('#form').submit(function(){
		
		$('#form').validationEngine();
	    var valid = $('#form').validationEngine({returnIsValid: true});
	    if(valid){
	    	// 解码附件名称和提示
	    	var myCustomUploadCallBackData_UseQUI = CustomUploadCallBackData_UseQUI
	    	for ( var i in myCustomUploadCallBackData_UseQUI) {
	    		myCustomUploadCallBackData_UseQUI[i].oldName = decodeURIComponent(myCustomUploadCallBackData_UseQUI[i].oldName);
	    		myCustomUploadCallBackData_UseQUI[i].msg = decodeURIComponent(myCustomUploadCallBackData_UseQUI[i].msg);
			}
	    	
	    	//附件信息
	    	$("#attachmentList").val(JSON.stringify(myCustomUploadCallBackData_UseQUI));
	    	if($("#delAttachmentList")){
	    		$("#delAttachmentList").val(delAttachments.join(","));
	    	}
	    	$(this).ajaxSubmit({
	            //表单提交成功后的回调
	            success: function(responseText, statusText, xhr, $form){
	            	
	            	if(responseText.flag == true){
	    				
	    				Dialog.alert(responseText.message,function(){
	    					
	    					back();
	                    });
	    			}else{
	    				
	    				Dialog.alert(responseText.message);
	    			}
	            },
	            error: function(responseText, statusText, xhr, $form){
	            	Dialog.alert("提交失败");
	            }
	     	});
	     }
	    //阻止表单默认提交事件
	    return false; 
	});
	
	//附件上传
	var upload1 = new CustomUpload();
	upload1.init("eqInfo",new Date().getTime(),{
		"btn" : "uploadBtn",
		"listDiv" : "uploadList",
		/*"fileSize" : '2048000',*/
		"fileTypes" : '*.*'
	});
}


function submitForm(){
	
	jQuery("#form").submit();
}

function back(){
	
	location.href= backURL;
}

//选择厂商
function selectManufacture(){
	
	dialog = new Dialog();
	dialog.title = "选择厂商";
	dialog.Width = 800;
	dialog.Height = 360;
	dialog.URL = path + "/eqFactory/toListMini?factoryType=01";
	dialog.ShowButtonRow = true;
	dialog.OKEvent = function() {
		var rowData = dialog.innerFrame.contentWindow.grid.getSelectedRow();
		ChooseItem(rowData);
	};
	dialog.show();
}
//清除已选厂商
function clearManufacture(){
	$("#manufactureName").val("");
	$("#manufactureId").val("");
}

function ChooseItem(rowData){
	$("#manufactureName").val(rowData.name);
	$("#manufactureId").val(rowData.id);
	dialog.close();
}

function delAttachment(id, path, name, sufx){
	/*var attachment = {};
	attachment.businessId = id
	attachment.attachmentPath = path
	attachment.newAttachmentName = name
	attachment.attachmentSuffix = sufx
	delAttachments.push(attachment);*/
	delAttachments.push(id)
	$("#"+id).remove();
}