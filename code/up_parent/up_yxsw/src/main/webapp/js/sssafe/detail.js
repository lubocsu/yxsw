/**
 * 设施关联安全js对象
 */
(function() {
	
	var SBSSRelation = window.SBSSRelation={
		config : function(){
				
		},
		
		initGrid : function(){
			grid = $("#SsCheckGrid").quiGrid({
				columns:[	

				         { display: '安全提醒标题', name: 'title', align: 'left', width: "25%", showTitle:true,
						    	/* render: function (rowdata, rowindex, value, column){
					    			var html = "<a href='javascript:;' onclick=onView('" + rowdata.order_id + "');>" + value + "</a>";
					    			return html;
			                 	} */
						    },
						  { display: '安全提醒内容', name: 'content', align: 'center', width:"25%", showTitle:true}, 
						    { display: '是否重要提醒', name: 'is_important', type:"important", align: 'center', width: "25%",  showTitle:true},
						   // { display: '公告是否有效', name: 'fault_address', align: 'left', width: "25%", showTitle:true},
						    { display: '提醒类型', name: 'tx_type',type:"txtype",   align: 'left', width: "25%", showTitle:true},
				],
			    url: path+'/SsSafe/getList?ssId='+ssId, rownumbers:true, checkbox:true, selectRowButtonOnly:false, height: '95%', width:"100%",
			    pageSize:20, percentWidthMode:true,
			  toolbar:{
			    	items:[
			    		{text: '新增', click: SBSSRelation.add, iconClass: 'icon_add',visible: trueOrFalse},
			    		{ line : true },
			    		{text: '删除', click: SBSSRelation.del, iconClass: 'icon_delete',visible: trueOrFalse}
			      ]}
			});
		},
	
	
		
		add:function(){
			var unCkeckRowsLength=grid.getData().length;
			var diag = new Dialog();
			diag.Title = "新增安全提醒";
			//diag.URL = "${path}/message/toAddMessage";
			diag.URL=path+'/SsSafe/toAddCheck?ssId='+ssId;
			//diag.URL="${path}/eqAsCheck/toAddCheck";
			diag.ShowButtonRow=true;
			diag.Height = 800;
			diag.Width = 800;
			//diag.OkButtonText=" 确 定 ";
			  diag.OKEvent = function(){

			        var inputValue = diag.innerFrame.contentWindow.add(refreshTree,unCkeckRowsLength);

			        diag.close();

			        };
			diag.show();
			
		},
		del : function(){
			var rows = grid.getSelectedRows();
			var unCkeckRowsLength=grid.getData().length;
			if(rows.length==0){
				Dialog.alert("请选择要删除的关联关系");
				return;
			}
			Dialog.confirm("是否删除安全定义？",function(){
				var IDS = new Array();
				for ( var i in rows) {
					IDS.push(rows[i].warning_id);
				}
				$.post(path+"/SsSafe/delOne?ssId="+ssId,{"ids":IDS.join(",")},function(result){
					Dialog.alert(result.message);
					if(result.flag){
						grid.loadData();
						if(unCkeckRowsLength==rows.length){
							refreshTree();
						}
					
					}
				});
			});
			
		}
	}
	
})();

function refresh(isUpdate){
	if(!isUpdate){
		//重置排序
    	grid.options.sortName='uid';
    	grid.options.sortOrder="desc";
    	//页号重置为1
		grid.setNewPage(1);
	}
	grid.loadData();
}

//对类型格式化
$.quiDefaults.Grid.formatters['important'] = function (value, column) {
	if(value=="1"){	
	return "是"	;
	}else if (value=="0"){		
	return "否";
	}else{
		
		return "出现未知错误";
	}

}
//对重要性格式化
$.quiDefaults.Grid.formatters['txtype'] = function (value, column) {
   if(value=="1"){
	   return "设备安全";
   }
   else if(value=="2"){
	   return "设施安全"; 
   }else if(value=="3"){ 
	   return " 巡检点安全";
   }else{
	   return "出现未知错误";
   }
   
}
function refreshTree(){
	
	parent.refreshTree();
}

