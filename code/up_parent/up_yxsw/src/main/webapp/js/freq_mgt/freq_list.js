/**
 * 
 */
var grid = null;

function initComplete(){
	initGrid(params);
}
//初始化Grid处理
function initGrid(params) {
	grid = $("#dataBasic").quiGrid({
		columns:[
			{ display: '任务名称',	name: 'freq_segment_name',	align: 'left', width: "30%"},
			    { display: '班次',	name: 'detail_name',	align: 'center', width: "10%"},
			    { display: '开始时间',	name: 'start_time',	align: 'center', width: "20%", type:"time"},
			    { display: '结束时间',	name: 'end_time',	align: 'center', width: "20%", type:"time"},
			    { display: '所属单位',	name: 'belong_wsc_name', align: 'center', width: "20%"},
			    { display: '操作',	isAllowHide: false, align: 'center', width:80,
					render: function (rowdata, rowindex, value, column){
	            	   var html = '<div class="padding_top4 padding_left5">';
	            	   html += '<span class="img_list hand" title="查看" onclick="toView(\'' + rowdata.freq_segment_id + '\')"></span>';
	                   if(isModifyAble){
	                	   html += '<span class="img_edit hand" title="修改" onclick="toEdit(\'' + rowdata.freq_segment_id + '\')"></span>';
	                   }
	                   if(isRemoveAble){
	                	   html += '<span class="img_delete hand" title="删除" onclick="toDel(\'' + rowdata.freq_segment_id + '\',\'' + rowdata.update_timestemp + '\')"></span>';
	                   }
	                   + '</div>';
	                   return html;
	                }
	           }
		  ],
	 url:path+"/freq/getFreqList",
	 params:params,
	 rownumbers:true,
	 sortName:'start_time, detail_name',
	 sortOrder:'asc',
	 checkbox:true,selectRowButtonOnly:false,
     height: '100%', width:"100%", pageSize:20, percentWidthMode:true,
     toolbar:{
    	 items:[
		      {text: '新增', click: toAdd, iconClass: 'icon_add',visible:true},
		      { line : true }
    		]
     	}
	});
}

//时间格式化
$.quiDefaults.Grid.formatters['time'] = function (value, column) {
  return dateStr2Str(value,"$1:$2", /(\d{2})(\d{2})/);
}

//查询
function searchHandler(){
	var query = $("#queryForm").formToArray(); 
	grid.setOptions({ params : query});
	//页号重置为1
	grid.setNewPage(1);
	//刷新表格数据 
	grid.loadData();
}

//重置查询
function resetSearch(){
	$("#queryForm")[0].reset();
	$("#queryForm input").val("");
	$("#queryForm select").attr("selectedValue","");
	$("#queryForm select").resetValue();
	$("#queryForm .selectTree").attr("selectedValue","");
	$("#queryForm .selectTree").resetValue();
	searchHandler();
}

//
function toAdd(){
	location.href = path + "/freq/toAddFreq" + "?backURL=" + getBackUrl($("#queryForm"), grid);
}

function toView(freqId){
	location.href = path + "/freq/toViewFreq" + "?backURL=" + getBackUrl($("#queryForm"), grid) + "&freqId=" + freqId;
}

function toEdit(freqId){
	location.href = path + "/freq/toEditFreq" + "?backURL=" + getBackUrl($("#queryForm"), grid) + "&freqId=" + freqId;
}

function toDel(freqId, timestamp){
	 Dialog.confirm("确定要删除吗？",function(){
         $.post(path+"/freq/doDelFreq",
                 { "freqId" : freqId,"updateTimestemp":timestamp},
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