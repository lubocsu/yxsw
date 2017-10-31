/**
 * 
 */
var grid = null;

function initComplete(){
	initGrid(params);
}
//初始化Grid处理
function initGrid(params) {
	var gridSetting = {
			columns:[
				{ display: '设备编号',	name: 'sb_code',	align: 'center',	width: "12%"},
			    { display: '财务编号',	name: 'sb_fn_code',	align: 'center', 	width: "12%"},
			    { display: '设备名称',	name: 'sb_name',	align: 'center', 	width: "15%", showTitle:true},
			    { display: '设备类别',	name: 'sb_sort',	align: 'center', 	width: 60, type:"eqType" },
			    { display: '设备类型',	name: 'sb_type_id',	align: 'center', 	width: "10%"},
			    { display: '国产进口',	name: 'gcjk',	align: 'center', 	width: 60, type:"gcjk"},
			    { display: '设备型号',	name: 'sbxh',	align: 'center', 	width: "12%", showTitle:true},
			    { display: '性能参数',	name: 'xncs',	align: 'center', 	width: "12%", showTitle:true},
			    { display: '结构原理',	name: 'jgyl',	align: 'center', 	width: "12%", showTitle:true},
			    { display: '制造商',	name: 'manufacture_id',	align: 'center', 	width: "15%", showTitle:true},
			    { display: '在用状态',	name: 'zy_status',	align: 'center', 	width: 60, type:"zyStatus"},
			    { display: '操作',	isAllowHide: false, align: 'center', width:120,
					render: function (rowdata, rowindex, value, column){
	            	   var html = '<div class="padding_top4 padding_left5">';
	            	   html += '<span  id="ylphoto" title="查看记录" onclick="toWatchDetail(\'' + rowdata.sb_id + '\')"><img  src="../images/history.png" ></span>'+'&nbsp;';
	            	   html += '<span class="img_list hand" title="查看" onclick="toView(\'' + rowdata.sb_id + '\')"></span>';
	                   if(isModifyAble){
	                	   html += '<span class="img_edit hand" title="修改" onclick="toEdit(\'' + rowdata.sb_id + '\')"></span>';
	                   }
	                   if(isRemoveAble){
	                	   html += '<span class="img_delete hand" title="删除" onclick="toDel(\'' + rowdata.sb_id + '\',\'' + rowdata.update_timestemp + '\')"></span>';
	                   }
	                   + '</div>';
	                   return html;
	                }
	           }
			  ],
		 url:path+"/eqInfo/getEqList",
		 params:params,
		 rownumbers:true,
		 sortName:'sb_code',
		 sortOrder:'asc',
		 checkbox:!showToolBar,selectRowButtonOnly:false,
	     height: '100%', width:"100%", pageSize:20, percentWidthMode:true
		};
	
	if(showToolBar){
		gridSetting.toolbar ={
	    	 items:[
			      {text: '新增', click: toAddEq, iconClass: 'icon_add',visible:isAddAble},
			      { line : true }
	    		]
	     	}
	}
	
	grid = $("#dataBasic").quiGrid(gridSetting);
}

//数据格式化
$.quiDefaults.Grid.formatters['gcjk'] = function (value, column) {
	return GC_JK[value];
}
$.quiDefaults.Grid.formatters['eqType'] = function (value, column) {
	return EQ_TYPE[value];
}
$.quiDefaults.Grid.formatters['zyStatus'] = function (value, column) {
	return ZY_STATUS[value];
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
function toAddEq(){
	location.href = path + "/eqInfo/toAddEq" + "?backURL=" + getBackUrl($("#queryForm"), grid);
}

function toView(sbId){
	location.href = path + "/eqInfo/toViewEq" + "?backURL=" + getBackUrl($("#queryForm"), grid) + "&sbId=" + sbId;
}

function toEdit(sbId){
	location.href = path + "/eqInfo/toEditEq" + "?backURL=" + getBackUrl($("#queryForm"), grid) + "&sbId=" + sbId;
}
/*function toWatchDetail(sbId){
	
    location.href=path+"/eqInfo/toMain?sbId="+sbId;	
}*/

function toWatchDetail(sbId){
	
	Dialog.open({
		URL:path+"/eqInfo/toMain?sbId="+sbId,
		Title:"查看",Width:1300, Height:1000});
}
function toDel(sbId, timestamp){
	 Dialog.confirm("确定要删除吗？",function(){
         $.post(path+"/eqInfo/doDelEq",
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