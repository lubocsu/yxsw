/**
 * 设施关联设备js对象
 */
(function() {
	
	var SBSSRelation = window.SBSSRelation={
		config : function(){
				
		},
		
		initGrid : function(){
			grid = $("#sbWarnGrid").quiGrid({
				columns:[	

				         { display: '检查项编号', name: 'check_item_code', align: 'left', width: "33%", showTitle:true,
						    },
						   { display: '检查项名称', name: 'check_item_name', align: 'center', width:"33%", showTitle:true},
						   { display: '检查项说明', name: 'check_item_desc',   align: 'center', width:"34%", showTitle:true},
				],
			    url: path+'/sbWarn/getList?sbId='+sbId, rownumbers:true, checkbox:true, selectRowButtonOnly:false, height: '95%', width:"100%",
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
			diag.Title = "新增检查项";
			//diag.URL = "${path}/message/toAddMessage";
			diag.URL=path+'/sbWarn/toAddCheck?sbId='+sbId;
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
			Dialog.confirm("是否删除所选择的检查项?",function(){
				var IDS = new Array();
				for ( var i in rows) {
					IDS.push(rows[i].check_item_id);
				}
				$.post(path+"/sbWarn/delOne?sbId="+sbId,{"ids":IDS.join(",")},function(result){
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

