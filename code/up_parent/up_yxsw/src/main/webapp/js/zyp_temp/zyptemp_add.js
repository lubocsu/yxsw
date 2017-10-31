/**
 * 
 */
var grid;

function initComplete(){
	
//	initGrid();
	
	//绑定提交事件
	$('#form').submit(function(){
		
		$('#form').validationEngine();
	    var valid = $('#form').validationEngine({returnIsValid: true});
	    if(valid){
	    	$(this).ajaxSubmit({
	            //表单提交成功后的回调
	            success: function(responseText, statusText, xhr, $form){
	            	
	            	if(responseText.flag == true){
	    				
	    				Dialog.alert(responseText.message,function(){
	    					if($("#tempId").val()){
	    						back();
	    					}else{
	    						location.href = path + "/zypTemp/toEditZypTemp?tempId=" + responseText.data.tempId + "&backURL=" + backURL + "&yy=false";
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

/*function initGrid(){
	grid = $("#zyptemp_detail").quiGrid({
		columns:[
					{ display: '班次', name:'detail_name', align: 'center', width: "25%", editor: { type: 'text',maxlength:50,tip:'不超过50个字的中文'}},
				    { display: '开始时间', name:'start_time',	align: 'center', width: "15%", editor: { type: 'date', dateFmt:'HH:mm'}},
				    { display: '结束时间', name:'end_time',	align: 'center', width: "15%", editor: { type: 'date', dateFmt:'HH:mm'}},
				    { display: '说明',	name: 'detail_desc',	align: 'center', width: "45%", editor: { type: 'textarea', maxnum:200}},
				    { display: '操作',	isAllowHide: false, align: 'center', width:80,
						render: function (rowdata, rowindex, value, column){
		            	   var html = '<div class="padding_top4 padding_left5">';
		                   if(isModifyAble){
		                	   html += '<span class="img_edit hand" title="修改" onclick="beginEdit(\'' + rowindex + '\')"></span>';
		                   }
		                   if(isRemoveAble){
		                	   html += '<span class="img_delete hand" title="删除" onclick="doDelWpd(\'' + rowindex + '\')"></span>';
		                   }
		                   html += '</div>';
		                   return html;
		                }
		           }
			  ],
			 url:path+"/workPeriod/getDetailList",
			 params:{"workGroupId":tempId },
			 rownumbers:true,
			 checkbox:false,selectRowButtonOnly:false,
			 usePager:false,
		     height: '100%', width:"100%", percentWidthMode:true,
		     toolbar:{
		    	 items:[
				      {text: '新增', click: toAddItm, iconClass: 'icon_add',visible:true},
				      { line : true }
		    		]
		     	}
			});
}*/

//添加
function toAddItm(){
	
}

//添加
function toEditItm(itmId){
	
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