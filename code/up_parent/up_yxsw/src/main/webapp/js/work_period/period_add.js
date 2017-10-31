/**
 * 
 */
var grid;

function initComplete(){
	
	initGrid();
	repairCss();
}

function initGrid(){
	grid = $("#wp_detail").quiGrid({
		columns:[
					{ display: '班次', name:'detailName', align: 'center', width: "20%", editor: { type: 'text',maxlength:30,tip:'不超过30个字的中文'}},
				    { display: '开始时间', name:'startTime',	align: 'center', width: "15%", editor: { type: 'date', dateFmt:'HH:mm'}},
				    { display: '结束时间', name:'endTime',	align: 'center', width: "15%", editor: { type: 'date', dateFmt:'HH:mm'}},
				    { display: '说明',	name: 'detailDesc',	align: 'center', width: "45%", editor: { type: 'textarea', maxnum:200}},
				    /*{ display: '在用状态',	name: 'zy_status',	align: 'center', 	width: "5%"},*/
				    { display: '操作',	isAllowHide: false, align: 'center', width:80,
						render: function (rowdata, rowindex, value, column){
		            	   var html = '<div class="padding_top4 padding_left5">';
		                  /* if(isModifyAble){
		                	   html += '<span class="img_edit hand" title="修改" onclick="beginEdit(\'' + rowindex + '\')"></span>';
		                   }*/
		                   if(isRemoveAble){
		                	   html += '<span class="img_delete hand" title="删除" onclick="doDelWpd(\'' + rowindex + '\')"></span>';
		                   }
		                   html += '</div>';
		                   return html;
		                }
		           }
				  ],
//			 url:path+"/workPeriod/getPeriodList",
			 data:{"rows":[]},
			 rownumbers:true,
			 checkbox:false,selectRowButtonOnly:false,
			 usePager:false,
			 enabledEdit: true,
//			 clickToEdit: false,
			 onAfterEdit: onAfterEdit,
		     height: '100%', width:"100%", percentWidthMode:true,
		     toolbar:{
		    	 items:[
				      {text: '新增', click: toAddWpd, iconClass: 'icon_add',visible:true},
				      { line : true }
		    		]
		     	}
			});
}

//新增班次详情行
function toAddWpd(){
	var r = grid.getSelectedRow() || null;
	var isBefore = r == null ? true : false; 
	var rowDataNull={
			detailName : null,
			startTime:null,
			endTime:null,
			detailDesc:null
	};
	grid.addEditRow(rowDataNull,r,isBefore);
}

//删除班次详情行
function doDelWpd(rowindex){
	Dialog.confirm("确定要删除吗？",function(){
		grid.deleteRow(rowindex);
		
		//更新排班时间
		rows = grid.getData();
		$("#startTime").val(rows[0].startTime);
		$("#endTime").val(rows[rows.length-1].endTime);
	});
}

//填写明细后验证数据
function onAfterEdit(e){
	
	if(e.column.name == 'detailName'){
		if(e.value==""){
			top.Dialog.alert("班次明细名称不能为空！",null,null,null,2);
		}
	}

	if(e.column.name == 'startTime'){
		if(e.value==""){
			top.Dialog.alert("班次开始时间不能为空！",null,null,null,2);
		}else if(e.rowindex == 0){
			$("#startTime").val(e.value);
		}
	}
	
	if(e.column.name == 'endTime'){
		if(e.value==""){
			top.Dialog.alert("班次结束时间不能为空！",null,null,null,2);
		}else if(e.rowindex == grid.getData().length-1){
			$("#endTime").val(e.value);
		}
	}
}

//验证grid数据 
function validGridData(){
	var rows = grid.getData();
	for(var i=0; i<rows.length; i++){
		var item = rows[i];
		if(item.detailName === null || item.detailName === "" || item.detailName === "null"){
			Dialog.alert("班次明细名称不能为空！")
			return false;
		}else if(item.startTime === null || item.startTime === "" || item.startTime === "null"){
			Dialog.alert("班次开始时间不能为空！")
			return false;
		}else if(item.endTime === null || item.endTime === "" || item.endTime === "null"){
			Dialog.alert("班次结束时间不能为空！")
			return false;
		}
	}
	if($("#startTime").val() != $("#endTime").val()){
		Dialog.alert("班次周期需为24小时！<br>请按照排班顺序添加排班明细。");
		return false;
	}else{
		return true;
	}
}

//组装数据
function getParam(){
	var workPeriod = {};
	workPeriod.workGroupName = $("#workGroupName").val();
	workPeriod.workGroupDesc = $("#workGroupDesc").val();
	workPeriod.startTime = $("#startTime").val();
	workPeriod.endTime = $("#endTime").val();
	
	var workPeriodDetails = [], gDatas = grid.getData();
	for ( var wpd in gDatas) {
		var detail = {};
		detail.detailName = gDatas[wpd].detailName;
		detail.startTime = gDatas[wpd].startTime;
		detail.endTime = gDatas[wpd].endTime;
		detail.detailDesc = gDatas[wpd].detailDesc;
		detail.sortNum = gDatas[wpd].rowPosition;
		workPeriodDetails.push(detail);
	}
	
	workPeriod.workGroupDetialList = JSON.stringify(workPeriodDetails);
	return workPeriod;
}

function submitForm(){
	grid.endEdit();
	if(validGridData()){
		var paramStr = getParam();
		jQuery.ajax({
			type : "post",
			url : path + "/workPeriod/doAddWp",
			cache : false,
			data : paramStr,
			dataType : "json",
			success : function(result) {
	            	if(result.flag == true){
	    				
	    				Dialog.alert(result.message,function(){
	    					
	    					back();
	                    });
	    			}else{
	    				
	    				Dialog.alert(result.message);
	    			}
			}
		});
	}
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