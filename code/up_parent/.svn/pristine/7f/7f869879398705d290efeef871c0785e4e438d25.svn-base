/**
 * 设施关联设备js对象
 */
(function() {
	
	var SBSSRelation = window.SBSSRelation={
		config : function(){
				
		},
		
		initGrid : function(){
			grid = $("#sbssRelationGrid").quiGrid({
				columns:[	
			        { display: '设备名称', name: 'sbname', align: 'center', width:'16%', showTitle:true},
				    { display: '设备类别', name: 'sbsort', align: 'left', width: "14%" },
				    { display: '设备类型', name: 'sbtypename', align: 'left', width: "14%" },
				    { display: '国产进口', name: 'gcjkname', align: 'center', width: '14%'},
				    { display: '设备型号', name: 'sbxh', align: 'center',width: '14%'},
				    { display: '参数性能', name: 'xncs', align: 'center',width: '14%'},
				    { display: '结构原理', name: 'jgyl', align: 'center',width: '14%'}
				],
			    url: path+'/ssattachsb/getSBSSRelationList?ssId='+ssId, rownumbers:true, checkbox:true, selectRowButtonOnly:false, height: '95%', width:"100%",
			    pageSize:20, percentWidthMode:true,
			    toolbar:{
			    	items:[
			    		{text: '新增', click: SBSSRelation.add, iconClass: 'icon_add',visible: true},
			    		{ line : true },
			    		{text: '删除', click: SBSSRelation.del, iconClass: 'icon_delete',visible: true}
			      ]}
			});
		},
	
		add : function(){
			var diag = new Dialog();
			diag.Title = "查看检查项";
			diag.URL = path+"/eqInfo/init?showToolBar=false&ssId="+ssId;
			diag.Height = 620;
			diag.Width = 1200;
			diag.ShowButtonRow=true;
			diag.OKEvent=function(){
				var rows = diag.innerFrame.contentWindow.grid.getSelectedRows();
				var relations=[];
				for ( var i in rows) {
					var relation = {};
					relation.ssId = ssId;
					relation.sbId = rows[i].sb_id;
					relations.push(relation);
				}
				$.post(path+"/ssattachsb/doAdd",{"relations":JSON.stringify(relations)},function(result){
					Dialog.alert(result.message);
					if(result.flag){
						grid.loadData();
					}
				});
				diag.close();
			}
			diag.show();
		},
		del : function(){
			var rows = grid.getSelectedRows();
			if(rows.length==0){
				Dialog.alert("请选择要删除的关联关系");
				return;
			}
			Dialog.confirm("是否从该设施下移除选定设备",function(){
				var IDS = new Array();
				for ( var i in rows) {
					IDS.push(rows[i].sbssid);
				}
				$.post(path+"/ssattachsb/doDel",{"ids":IDS.join(",")},function(result){
					Dialog.alert(result.message);
					if(result.flag){
						grid.loadData();
					}
				});
			});
			
		}
	}
	
})();

