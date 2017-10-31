/**
 * 
 */
var grid;
var delDetailList = [];

function initComplete(){
	
	initGrid();
}

function initGrid(){
	grid = $("#wp_detail").quiGrid({
		columns:[
			        /*{ display: '班次详情Id', name:'detail_id', hide:true, align: 'center', width: "20%"},*/
					{ display: '班次', name:'detail_name', align: 'center', width: "25%", editor: { type: 'text',maxlength:30,tip:'不超过30个字的中文'}},
				    { display: '开始时间', name:'start_time',	align: 'center', width: "15%", editor: { type: 'date', dateFmt:'HH:mm'}},
				    { display: '结束时间', name:'end_time',	align: 'center', width: "15%", editor: { type: 'date', dateFmt:'HH:mm'}},
				    { display: '说明',	name: 'detail_desc',	align: 'center', width: "45%", editor: { type: 'textarea', maxnum:200}},
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
			 url:path+"/workPeriod/getDetailList",
			 params:{"workGroupId":wpId},
			 rownumbers:true,
			 checkbox:false,selectRowButtonOnly:false,
			 usePager:false,
			 enabledEdit: true,
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
	
//	Dialog.confirm("新增班次后会删除所有未生效的排班");
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
	
	var msg = "确定要删除吗？";
	if(strNVL(grid.getRow(rowindex).detail_id)){
		msg = "删除班次后会删除所有未生效的排班，确定要删除吗？"
	}
	Dialog.confirm(msg ,function(){
		
		if(strNVL(grid.getRow(rowindex).detail_id)){
			
			var delDetail = grid.getRow(rowindex);
			var del = {};
			del.detailId = delDetail.detail_id;
			del.detailName = delDetail.detail_name;
			del.startTime = delDetail.start_time;
			del.endTime = delDetail.end_time;
			del.detailDesc = delDetail.detail_desc;
			del.sortNum = delDetail.__index;
			delDetailList.push(del);
		}
		grid.deleteRow(rowindex);
		
		//更新排班时间
		rows = grid.getData();
		$("#startTime").val(rows[0].start_time);
		$("#endTime").val(rows[rows.length-1].end_time);
		
	});
}

//填写明细后验证数据
function onAfterEdit(e){
	
	if(e.column.name == 'detail_name'){
		
		if(e.value==""){
			top.Dialog.alert("班次明细名称不能为空！",null,null,null,2);
		}
	}

	if(e.column.name == 'start_time'){
		if(e.value==""){
			top.Dialog.alert("班次开始时间不能为空！",null,null,null,2);
		}else if(e.rowindex == 0){
			$("#startTime").val(e.value);
		}
	}
	
	if(e.column.name == 'end_time'){
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
		if(item.detail_name === null || item.detail_name === "" || item.detail_name === "null"){
			Dialog.alert("班次明细名称不能为空！")
			return false;
		}else if(item.start_time === null || item.start_time === "" || item.start_time === "null"){
			Dialog.alert("班次开始时间不能为空！")
			return false;
		}else if(item.end_time === null || item.end_time === "" || item.end_time === "null"){
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
	workPeriod.workGroupId = $("#workGroupId").val();
	workPeriod.updateTimestemp = $("#updateTimestemp").val();
	workPeriod.workGroupName = $("#workGroupName").val();
	workPeriod.workGroupDesc = $("#workGroupDesc").val();
	workPeriod.startTime = $("#startTime").val();
	workPeriod.endTime = $("#endTime").val();
	
	var workPeriodDetails = [], gDatas = grid.getData();
	for ( var wpd in gDatas) {
		var detail = {};
		detail.detailId = gDatas[wpd].detail_id;
		detail.detailName = gDatas[wpd].detail_name;
		detail.startTime = gDatas[wpd].start_time;
		detail.endTime = gDatas[wpd].end_time;
		detail.detailDesc = gDatas[wpd].detail_desc;
		detail.sortNum = gDatas[wpd].__index;
		workPeriodDetails.push(detail);
	}
	
	workPeriod.workGroupDetialList = JSON.stringify(workPeriodDetails);
	workPeriod.delDetailList = JSON.stringify(delDetailList);
	return workPeriod;
}

function submitForm(){
	grid.endEdit();
	if(validGridData()){
		var paramStr = getParam();
		jQuery.ajax({
			type : "post",
			url : path + "/workPeriod/doSaveEditWp",
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

//返回
function back(){
	
	location.href= backURL;
}