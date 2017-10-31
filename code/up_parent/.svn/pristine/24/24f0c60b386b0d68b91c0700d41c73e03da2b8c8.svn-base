/**
 * 设备类型js对象
 */
(function() {
	var path = "";
	var csOrg = "";
	var treeObj = null;
	var selectedNode = null;
	var technicsScopeId = null;
	var TechnicsScope = window.TechnicsScope = {
			
		config : function(config){
			this.config = config;
			path = config.path;
			csOrg = config.csOrg;
			this.setTree(csOrg);
			this.initTree(csOrg);
		},
		// 初始化树
		initTree : function(csOrg) {
			jQuery.ajax({
				type : 'POST',
				url : path+'/technicsScope/getTechnicsScopeTree?csOrg='+csOrg,
				dataType : 'json',
				success : function(nodes){
					treeObj = $.fn.zTree.init(jQuery("#tree"), TechnicsScope.treeSetting, nodes);
				},
				error : function(a){
					Dialog.alert("工艺段获取失败");
				}
			});
		},
		
		refreshTree: function(parentId){
			treeObj.reAsyncChildNodes(null, "refresh",true);
			technicsScopeId=parentId;
		},
		
		//新增
		add : function(){
			
			if(csOrgType == "3"){
				
				jQuery("#detail-iframe").attr("src",path+"/technicsScope/addTechnicsScope");
			}else{
				Dialog.alert("非厂站人员不能新增！");
			}
			
		},
		// 停用
		del : function() {
			if(csOrgType != "3"){
				Dialog.alert("非厂站人员不能删除！");
			}else{
				var treeNode = treeObj.getSelectedNodes()[0];
				if(null != treeNode && undefined != treeNode){
					Dialog.confirm("删除工艺段信息会删除相应的排班信息和巡检信息，确认删除吗？",function(){
							$.ajax({
					            cache: true,
					            type: "POST",
					            url: path + '/technicsScope/delTechnicsScope?technicsScopeId=' + treeNode.id,
					            async: false,
					            error: function(request) {
					                alert("停用失败！");
					            },
					            success: function(responseText, statusText, xhr, $form){
					            		Dialog.alert(responseText.message, function(){
					                	//刷新数据
//					                	refresh(parentId);
					                    //关闭窗口
					                    Dialog.close();
					                    var url = path+"/technicsScope/init";
					                    location.href = url;
					                });
					            }
					        });
						});
				}else{
					
					Dialog.alert("请选中要删除的工艺段!");
					return;
				}
			}
		},
		
		modify : function(){
			if(csOrgType != "3"){
				
				Dialog.alert("非厂站人员不能修改！");
			}else{
				var treeNode = treeObj.getSelectedNodes()[0];
				if(null != treeNode && undefined != treeNode){
					var id = treeNode.id;
					jQuery("#detail-iframe").attr("src",path+"/technicsScope/toModifyTechnicsScope?technicsScopeId="+id);
				}else{
					
					Dialog.alert("请选中要修改的记录!");
					return;
				}
			}
		},
		
		setTree : function(csOrg){
			this.treeSetting = {
					view: {
						dblClickExpand: true
					},
					async:{
						enable :true,
						dataType: 'JSON',
						url:path+'/technicsScope/getTechnicsScopeTree?csOrg='+csOrg,
					},
					callback: {
						onClick: this.zTreeSelect,
						onAsyncSuccess:function(event, treeId, treeNode, msg){
							treeObj.expandAll(true);
							if(technicsScopeId.length!=0){
								selectedNode = treeObj.getNodeByParam("id",technicsScopeId,null);
							}
							if(null!=selectedNode){
								treeObj.selectNode(treeObj.getNodeByParam("id", selectedNode.id, null));
							}
						}
					},
			};
		},
		
		zTreeSelect : function (event,treeId,treeNode) {
			var typeId = treeNode.typeids;
			if(null == typeId || undefined == typeId){
				var id = treeNode.id;
				
				jQuery("#detail-iframe").attr("src",path+"/technicsScope/showDetail?technicsId="+id);
			}else{
				return;
			}
		}
	};
})();

