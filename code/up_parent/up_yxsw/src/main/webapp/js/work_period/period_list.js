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
			{ display: '班次名称', name:'work_group_name', align: 'center', width: "20%"},
		    { display: '开始时间', name:'start_time',	align: 'center', width: "15%", type:"time"},
		    { display: '结束时间', name:'end_time',	align: 'center', width: "15%", type:"time"},
		    { display: '所属厂所', name:'belong_wscn_ame',	align: 'center', 	width: "20%"},
		    { display: '说明',	name: 'work_group_desc',	align: 'center', 	width: "30%"},
		    /*{ display: '在用状态',	name: 'zy_status',	align: 'center', 	width: "5%"}*///,
		    { display: '操作',	isAllowHide: false, align: 'center', width:80,
				render: function (rowdata, rowindex, value, column){
            	   var html = '<div class="padding_top4 padding_left5">';
            	   html += '<span class="img_list hand" title="查看" onclick="toView(\'' + rowdata.work_group_id + '\')"></span>';
                   if(isModifyAble){
                	   html += '<span class="img_edit hand" title="修改" onclick="toEdit(\'' + rowdata.work_group_id + '\')"></span>';
                   }
                   /*if(isRemoveAble){
                	   html += '<span class="img_delete hand" title="删除" onclick="toDel(\'' + rowdata.work_group_id + '\',\'' + rowdata.update_timestemp + '\')"></span>';
                   }*/
                   html += '</div>';
                   return html;
                }
           }
		  ],
	 url:path+"/workPeriod/getPeriodList",
	 params:params,
	 rownumbers:true,
	 sortName:'create_timestemp',
	 sortOrder:'desc',
	 checkbox:true,selectRowButtonOnly:false,
     height: '100%', width:"100%", pageSize:20, percentWidthMode:true,
     toolbar:{
    	 items:[
		      {text: '新增', click: toAddEq, iconClass: 'icon_add',visible:true},
		      { line : true }
    		]
     	}
	});
}

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

function toAddEq(){
	if(grid.getData().length > 0){
		Dialog.alert("每个厂所只可设置一条排班信息");
	}else{
		location.href = path + "/workPeriod/toAddWp" + "?backURL=" + getBackUrl($("#queryForm"), grid);
	}
}

function toView(workGroupId){
	location.href = path + "/workPeriod/toViewWp" + "?backURL=" + getBackUrl($("#queryForm"), grid) + "&workGroupId=" + workGroupId;
}

function toEdit(workGroupId){
	location.href = path + "/workPeriod/toEditWp" + "?backURL=" + getBackUrl($("#queryForm"), grid) + "&workGroupId=" + workGroupId;
}

function toDel(sbId, timestamp){
	 Dialog.confirm("确定要删除吗？",function(){
         $.post(path+"/workPeriod/doDelEq",
                 { "workGroupId" : workGroupId,"updateTimestemp":timestamp},
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