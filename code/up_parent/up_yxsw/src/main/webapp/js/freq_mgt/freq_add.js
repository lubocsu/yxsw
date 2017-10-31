/**
 * 
 */
var grid;
var wpdNode;

function initComplete(){
	
	$("#detailId").bind("change",function(){

		wpdNode=$(this).data("selectedNode");
	    if($(this).attr("relValue")){
	        $(".date").removeAttr("disabled").val("");
	        $("#detailName").val(wpdNode.detail_name);
	        $("#timeScope_start").text("班次开始时间：" + dateStr2Str(wpdNode.start_time, '$1:$2', /(\d{2})(\d{2})/));
	        $("#timeScope_end").text("班次结束时间：" + dateStr2Str(wpdNode.end_time, '$1:$2', /(\d{2})(\d{2})/));
	    }else{
	    	$(".date").attr("disabled","disabled").val("");
	    	$("#timeScope_start, #timeScope_end").text("");
	    }
	});
	
	/*$("#startTime").bind("change",function(){
//		$(this).val();
		timeValidate();
//		console.log(dateStr2Str($(this).val(), '$1$2', /(\d{2}):(\d{2})/))
	})*/
	/*$("#startTime").change(function(){
		timeValidate();
	});
	
	$("#endTime").bind("change",function(){
		
	});*/
	
	//绑定提交事件
	$('#form').submit(function(){
		
		vlinput($("#startTime"));
		vlinput($("#endTime"));
		$('#form').validationEngine();
	    var valid = $('#form').validationEngine({returnIsValid: true});
	    if(valid){
	    	$(this).ajaxSubmit({
	            //表单提交成功后的回调
	            success: function(responseText, statusText, xhr, $form){
	            	
	            	if(responseText.flag == true){
	    				
	    				Dialog.alert(responseText.message,function(){
	    					if($("#freqSegmentId").val()){
	    						back();
	    					}else{
	    						location.href = path + "/freq/toEditFreq?freqId=" + responseText.data.freqSegmentId + "&backURL=" + backURL;
	    					}
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
}

function  timeValidate(el){
	
	var _timeInput = el;
	var time = dateStr2Str(_timeInput.val(), '$1$2', /(\d{2}):(\d{2})/);
	wpdNode = wpdNode?wpdNode:$("#detailId").data("selectedNode");
	if(wpdNode.start_time < wpdNode.end_time){	//班次起止时间不跨天
		if(time < wpdNode.start_time || time > wpdNode.end_time){
			Dialog.alert("巡检时间需在班次时间内！");
			_timeInput.val("");
		}else if(_timeInput.attr("id") == "startTime"){
			var endTime = $("#endTime").val();
			if(strNVL(endTime) && time > dateStr2Str(endTime, '$1$2', /(\d{2}):(\d{2})/)){
				Dialog.alert("开始时间不能晚于结束时间！");
				_timeInput.val("");
			}
		}else{
			var startTime = $("#startTime").val();
			if(strNVL(startTime) && time < dateStr2Str(startTime, '$1$2', /(\d{2}):(\d{2})/)){
				Dialog.alert("结束时间不能早于开始时间！");
				_timeInput.val("");
			}
		}
	}else if(wpdNode.start_time > wpdNode.end_time){	//班次起止时间跨天
		if(time < wpdNode.start_time && time > wpdNode.end_time){
			Dialog.alert("巡检时间需在班次时间内！");
			_timeInput.val("");
		}else if(_timeInput.attr("id") == "startTime"){	//开始时间
			
			var endTime = dateStr2Str($("#endTime").val(), '$1$2', /(\d{2}):(\d{2})/);
			if(strNVL(endTime) 
					&& ((endTime >= wpdNode.start_time && time >= wpdNode.start_time && time > endTime) 	//不跨天
							|| (endTime <= wpdNode.end_time && time <= wpdNode.end_time && time > endTime)	//不跨天
							|| (endTime >= wpdNode.start_time && time < wpdNode.end_time)	//跨天
							|| (time == '0000' && endTime >= wpdNode.start_time)	//特殊情况“0000”
							|| (endTime == '0000' && time <= wpdNode.end_time))){	//特殊情况“0000”
				Dialog.alert("开始时间不能晚于结束时间！");
				_timeInput.val("");
			}
		}else{	//结束时间
			var startTime = dateStr2Str($("#startTime").val(), '$1$2', /(\d{2}):(\d{2})/);
			if(strNVL(startTime) 
					&& ((startTime >= wpdNode.start_time && time >= wpdNode.start_time && time < startTime) 	//不跨天
							|| (startTime <= wpdNode.end_time && time <= wpdNode.end_time && time < startTime) 	//不跨天
							|| (startTime <= wpdNode.end_time && time >= wpdNode.start_time) 	//跨天
							|| (startTime == '0000' && time >= wpdNode.start_time)	//特殊情况“0000”
							|| (time == '0000' && startTime <= wpdNode.end_time))){	//特殊情况“0000”
				
				Dialog.alert("结束时间不能早于开始时间！");
				_timeInput.val("");
			}
		}
	}
}


function vlinput(el){
	timeValidate($(el));
}

function vlpick(){
	timeValidate($(this));
}

//删除班次详情行
function doDelWpd(rowindex){
	Dialog.confirm("确定要删除吗？",function(){
		grid.deleteRow(rowindex);
	});
}

function submitForm(){
	
	jQuery("#form").submit();
}

function back(){
	
	location.href= backURL;
}

function toDel(sbId, timestamp){
	 Dialog.confirm("确定要删除吗？",function(){
         $.post(path+"/workPeriod/doDelEq",
                 { "sbId" : sbId,"updateTimestemp":timestamp},
                 function(result){
                     if(result.flag){
                    	 Dialog.alert(result.message);
                    	 grid.loadData();
                     }else{
                    	 Dialog.alert(result.message);
                     }
                 },
                 "json");
     });
}