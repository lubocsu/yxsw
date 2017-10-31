/**
 * 设备类型js对象
 */
(function() {
	var path = "";
	var treeObj = null;
	var SSBaseInfo = window.SSBaseInfo = {
			
		config : function(config){
			this.config = config;
			path = config.path;
			this.setTree();
			this.initTree();
		},
		// 初始化树
		initTree : function() {
			treeObj = $.fn.zTree.init(jQuery("#tree"), SSBaseInfo.treeSetting);
		},
		
		refreshNewNode: function(newNode){
			var pNode = treeObj.getNodesByParam("id", newNode.parentId, null);
			treeObj.addNodes(pNode[0], newNode);
		},
		refreshTree: function(){
			treeObj.reAsyncChildNodes(null, "refresh",true);
		},
		
		//新增
		add : function(){
			var treeNode = treeObj.getSelectedNodes()[0];
			var id = null;
			var name = null;
			
			if(null != treeNode && undefined != treeNode){
				var delflag = treeNode.delflag;
				if(delflag == DELFLAG){
					Dialog.alert("该设备类型已经停用，不能新增其子类设施！");
				}else{
					
					var id = treeNode.id;
					var name = treeNode.name;
					jQuery("#ss-detail-iframe").attr("src",path+"/ssbase/toAdd?ssId="+id + "&ssName=" + name);
				}
			}else{
				 id = "";
				 name = "";
				 jQuery("#ss-detail-iframe").attr("src",path+"/ssbase/toAdd?ssId="+id + "&ssName=" + name);
			}
			
		},
		// 停用
		del : function() {
			var treeNode = treeObj.getSelectedNodes()[0];
			if(null != treeNode && undefined != treeNode){
				
				// 验证子节点是否存在
				var childrenNodes = treeNode.children;
				if(null != childrenNodes && undefined != childrenNodes){
					Dialog.confirm("该设施包含子设施，是否同时删除其子设施!",function(){
						// 验证设施下是否挂载又设备
						var bol = SSBaseInfo.validateSbUnderSs(treeNode);
						if(!bol){
							Dialog.confirm("该设施或其子设施下包含有设备关联关系，是否同时删除关联关系",function(){
								SSBaseInfo.doDel(treeNode);
							});
						}else{
							SSBaseInfo.doDel(treeNode);
						}
					});
				}else{
					// 验证设施下是否挂载又设备
					var bol = SSBaseInfo.validateSbUnderSs(treeNode);
					if(!bol){
						Dialog.confirm("该设施或其子设施下包含有设备关联关系，是否同时删除关联关系",function(){
							SSBaseInfo.doDel(treeNode);
						});
					}else{
						SSBaseInfo.doDel(treeNode);
					}
				}
			}else{
				Dialog.alert("请选中要停用的记录!");
				return;
			}
		},
		validateSbUnderSs:function(treeNode){
			var flag = true;
			$.ajax({
				url: path+"/ssbase/validateSbUnderSs?ssId="+treeNode.id,
				async: false,
				success:function(result){
					flag = result.flag;
				}
			});
			
			return flag;
		},
		
		doDel : function(treeNode){
			$.post(path+"/ssbase/del",{"ssId":treeNode.id},function(result2){
				if(result2.flag){
					Dialog.alert(result2.message)
					SSBaseInfo.refreshTree();
					 jQuery("#ss-detail-iframe").attr("src","");
					//关闭窗口
				}else{
					Dialog.alert(result2.message);
				}
			});
		},
		
		modify : function(){
			var treeNode = treeObj.getSelectedNodes()[0];
			if(null != treeNode && undefined != treeNode){
				var id = treeNode.id;
				var parentNode = treeNode.getParentNode();
				if(null==parentNode||undefined==parentNode){
					parentNode = {};parentNode.name="";
				}
				jQuery("#ss-detail-iframe").attr("src",path+"/ssbase/toModify?ssId="+id+"&parentName="+parentNode.name);
			}else{
				
				Dialog.alert("请选中要修改的记录!");
				return;
			}
		},
		
		setTree : function(){
			this.treeSetting = {
					view: {
						dblClickExpand: true,
						fontCss:function(treeId,node){
							return(!!node.highlight) ? {color:"#A60000", "font-weight":"bold"} : {color:"#333", "font-weight":"normal"};
						}
					},
					callback: {
						onClick: this.zTreeSelect,
						onAsyncSuccess:function(){
							treeObj.expandAll(true);
						},
						beforeClick:function(treeId, treeNode, clickFlag){
							if(null==treeNode.code&&null==treeNode.delflag){
								return false;
							}else{
								return true;
							}
						}
					},
					async: {
				        enable: true,
				        dataType: 'JSON',
				        url: path+'/ssbase/getSSTree'
				    }
			};
		},
		
		zTreeSelect : function (event,treeId,treeNode) {
			var id = treeNode.id;
			var parentNode = treeNode.getParentNode();
			var parentNodeName= null;
			var parentNodeId= null
			if(parentNode != null){
				parentNodeName = parentNode.name;
				parentNodeId = parentNode.id
			}else{
				parentNodeName = "";
				parentNodeId= "";
			}
			jQuery("#ss-detail-iframe").attr("src",path+"/ssbase/showDetail?ssId="+id + "&parentName=" + parentNodeName );
		}
	};
})();

function searchTree(){
	var value = jQuery("#searchTree").val();
	//获取根节点
	var zTree = $.fn.zTree.getZTreeObj("tree");
	var node = zTree.getNodeByParam("id", 0, null);
	getNodesByParamFuzzy("name", value, node);
}

//根据某一条件查找节点 模糊查询
function getNodesByParamFuzzy(key, value, parentNode){
    var zTree = $.fn.zTree.getZTreeObj("tree");
    var nodes = zTree.getNodesByParamFuzzy(key, value, parentNode);  
    //取消之前的高亮显示
    highlightNodes(zTree, zTree.highlightNodeList, false);
    //高亮显示
    highlightNodes(zTree, nodes, true);
    zTree.highlightNodeList = nodes;
    //选中第一个
    if(nodes.length>0){
        zTree.selectNode(nodes[0]);
    }
}

//高亮显示
function highlightNodes(zTree, nodes, highlight) {
    if(null == nodes)  return;
    for( var i = 0, l = nodes.length; i < l; i++) {
        nodes[i].highlight = highlight;
        zTree.updateNode(nodes[i]);
    }
}

