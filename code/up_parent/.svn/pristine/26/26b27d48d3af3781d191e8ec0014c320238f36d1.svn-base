/**
 * 
 */
var grid = null;

function initComplete(){
	initGrid();
}
//初始化Grid处理
function initGrid() {
	var gridSetting = {
			columns:[
				{ display: '设备编号',	name: 'sb_code',	align: 'center',	width: "20%"},
			    { display: '设备名称',	name: 'sb_name',	align: 'center', 	width: "20%", showTitle:true},
			    { display: '设备类别',	name: 'sb_sort',	align: 'center', 	width: "20%", type:"eqType" },
			    { display: '设备类型',	name: 'sb_type_id',	align: 'center', 	width: "20%"},
			    { display: '设备型号',	name: 'sbxh',	align: 'center', 	width: "20%", showTitle:true},
			    { display: '在用状态',	name: 'zy_status',	align: 'center', 	width: 60, type:"zyStatus"}
			   ],
		 url:path+"/ssattachsb/getEqInfoGridList",
		 rownumbers:true,
		 sortName:'sb_code',
		 sortOrder:'asc',
		 checkbox:true,selectRowButtonOnly:false,
	     height: '100%', width:"100%", pageSize:20, percentWidthMode:true
		};
	
	grid = $("#dataBasic").quiGrid(gridSetting);
}

//数据格式化

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
