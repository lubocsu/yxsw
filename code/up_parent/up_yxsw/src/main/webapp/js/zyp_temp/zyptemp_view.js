/**
 * 
 */
var grid;
var diag;

function initComplete(){
	
	initGrid();
	
}

function initGrid(){
	grid = $("#zyptemp_detail").quiGrid({
		columns:[
					{ display: '指标组', name:'zxp_temp_itm_name', align: 'center', width: "25%"},
				    { display: '记录下达值', name:'jlxdz',	align: 'center', width: "15%", type:"status"},
				    { display: '记录完成值', name:'jlsbz',	align: 'center', width: "15%", type:"status"},
				    { display: '记录去除率',	name: 'jlqcl',	align: 'center', width: "15%", type:"status"},
				    { display: '说 明',	name: 'conf_desc',	align: 'center', width: "30%", },
				    { display: '操 作',	isAllowHide: false, align: 'center', width:80,
						render: function (rowdata, rowindex, value, column){
		            	   var html = '<div class="padding_top4 padding_left5">';
		            	   html += '<span class="img_list hand" title="查看" onclick="toViewItm(\'' + rowdata.zxp_temp_itm_id + '\')"></span>';
		                   html += '</div>';
		                   return html;
		                }
		           }
			  ],
			 url:path+"/zypTemp/getZypTempItemList",
			 params:{"tempId":tempId },
			 rownumbers:true,
			 checkbox:false,selectRowButtonOnly:false,
			 usePager:false,
		     height: '100%', width:"100%", percentWidthMode:true,
		     toolbar:{
		    	 items:[
		    		]
		     	}
			});
}

//数据格式化
$.quiDefaults.Grid.formatters['status'] = function (value, column) {
	if(value == 1){
		return "是";
	}else{
		return "否";
	}
}

function toViewItm(zxpTempItmId){
	
	if(diag && !diag.closed){
		diag.close();
	}
	
	diag = new top.Dialog();
	diag.Title = "查看配置内容";
	diag.URL = path + "/zypTemp/toViewTempItem?zxpTempItmId=" + zxpTempItmId;
	diag.Width = 1000;
	diag.Height = 800;
	diag.show();
}

function back(){
	
	location.href= backURL;
}

function refresh(){
	diag.close();
	grid.loadData();
}