/**
 * 设施类型js对象
 */
(function() {
	var path = "";
	var treeObj = null;
	var sbwarn = window.sbwarn = {
			
		config : function(config){
			this.config = config;
			path = config.path;
			this.setTree();
			this.initTree();
		},
		// 初始化树
		initTree : function(val) {
			jQuery.ajax({
				type : 'POST',
			//	url : path+'/ssbase/getSSTree',
				url : path+'/SsCheck/getTree',
				dataType : 'json',
				data:{"value":val},
				success : function(nodes){
					treeObj = $.fn.zTree.init(jQuery("#tree"), sbwarn.treeSetting, nodes);
					treeObj.expandAll(true);
				},
				error : function(a){
					Dialog.alert("设备类型目录获取失败");
				}
			});
		},
		
		refreshTree: function(){
			treeObj.reAsyncChildNodes(null, "refresh",true);
		},
		
		setTree : function(){
			this.treeSetting = {
					view: {
						dblClickExpand: true,
						fontCss: getNodeFont,
						nameIsHTML: true,
						/*fontCss:function(treeId,node){
							return(!!node.highlight) ? {color:"#A60000", "font-weight":"bold"} : {color:"#333", "font-weight":"normal"};
						}*/
					},
					callback: {
						onClick: this.zTreeSelect,
						onAsyncSuccess:function(event, treeId, treeNode, msg){
							treeObj.expandAll(true);
							
						}
					},
					async: {
				        enable: true,
				        dataType: 'JSON',
				       // url: path+'/ssbase/getSSTree'
				        url: path+'/SsCheck/getTree'
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
			//jQuery("#ss-detail-iframe").attr("src",path+"/ssattachsb/showDetail?ssId="+id + "&parentName=" + parentNodeName );
			jQuery("#ssCheck-detail-iframe").attr("src",path+"/SsCheck/showDetail?ssId="+id + "&parentName=" + parentNodeName );
		}
	};
})();

function searchTree(){
	//var value = jQuery("#searchTree").val();
	var value = jQuery("#_select").val();
	sbwarn.initTree(value);
//	//获取根节点
//	var zTree = $.fn.zTree.getZTreeObj("tree");
//	var node = zTree.getNodeByParam("id", 0, null);
//	getNodesByParamFuzzy("name", value, node);
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
// 关联的有颜色变化
function getNodeFont(treeId, node) {

    return node.font ? node.font : {};

}


