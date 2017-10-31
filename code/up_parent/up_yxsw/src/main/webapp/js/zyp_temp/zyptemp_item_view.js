/**
 * 
 */
var grid;
var diag;
var oriFunc;	//编辑配置内容方法

function initComplete(){
	
	initGrid();
}

//指标项列表
function initGrid(){
	grid = $("#zyptemp_item_detail").quiGrid({
		columns:[
					{ display: '指标项编码', name:'cxzb_code', align: 'center', width: "40%"},
				    { display: '指标项名称', name:'cxzb_name',	align: 'center', width: "60%"},
				    { display: '操 作',	isAllowHide: false, align: 'center', width:100,
						render: function (rowdata, rowindex, value, column){
		            	   var html = '<div class="padding_top4 padding_left5">';
		            	   html += '<span class="img_list hand" title="查看" onclick="toViewZbx(\'' + rowdata.cxzb_id + '\')"></span>';
		                   html += '</div>';
		                   return html;
		                }
		           }
			  ],
			 url:path+"/zypTemp/getItemZbxList",
			 params:{"zxpTempItmId":zxpTempItmId },
			 rownumbers:true,
			 checkbox:false,selectRowButtonOnly:false,
		     height: '100%', width:"100%", percentWidthMode:true,pageSize:20,
		     toolbar:{
		     	}
			});
}

function toViewZbx(cxzbId){
	Dialog.open({
		URL:path + "/indicative/toShowInd?cxzb_id=" + cxzbId,
		Title:"查看",Width:650, Height:480});
}