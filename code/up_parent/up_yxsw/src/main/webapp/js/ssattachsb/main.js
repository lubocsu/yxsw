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
			jQuery.ajax({
				type : 'POST',
				url : path+'/ssbase/getSSTree',
				dataType : 'json',
				success : function(nodes){
					treeObj = $.fn.zTree.init(jQuery("#tree"), SSBaseInfo.treeSetting);
//					treeObj.expandAll(true);
				},
				error : function(a){
					Dialog.alert("设备类型目录获取失败");
				}
			});
		},
		
		
		setTree : function(){
			this.treeSetting = {
					view: {
						dblClickExpand: true,
						fontCss:function(treeId,node){
							return(node.relationcount!=null&&node.relationcount>0) ? {color:"green"} : {color:"black"};
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
			jQuery("#ss-detail-iframe").attr("src",path+"/ssattachsb/showDetail?ssId="+id + "&parentName=" + parentNodeName );
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

function updateNodeColor(relationcount){
	 var treeObj = $.fn.zTree.getZTreeObj("tree");
	 var node = treeObj.getSelectedNodes()[0];
	 node.relationcount = relationcount
	 treeObj.updateNode(node);
}