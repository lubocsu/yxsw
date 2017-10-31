/**
 * 
 */
var grid;

function initComplete(){
	
	initGrid();
}

function initGrid(){
	grid = $("#wp_detail").quiGrid({
		columns:[
			        { display: '班次详情Id', name:'detail_id', hide:true, align: 'center', width: "20%"},
					{ display: '班次', name:'detail_name', align: 'center', width: "20%"},
				    { display: '开始时间', name:'start_time',	align: 'center', width: "15%", type:"time"},
				    { display: '结束时间', name:'end_time',	align: 'center', width: "15%", type:"time"},
				    { display: '说明',	name: 'detail_desc',	align: 'center', width: "50%"},
				    /*{ display: '在用状态',	name: 'zy_status',	align: 'center', 	width: "5%"},*/
			  ],
			 url:path+"/workPeriod/getDetailList",
			 params:{"workGroupId":wpId},
			 rownumbers:true,
			 checkbox:false,selectRowButtonOnly:false,
			 usePager:false,
//			 clickToEdit: false,
		     height: '100%', width:"100%", percentWidthMode:true
			});
}

//返回
function back(){
	location.href= backURL;
}
