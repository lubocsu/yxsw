function  showAll(){
	zTree_Menu.expandAll(true);
}
function  hideAll(){
	zTree_Menu.expandAll(false);
	jQuery.jCookie('leftTreeNodeId',"false");
}
function findNode(){
	var value = $.trim($("#searchKey").val());
	if(value!=""){
		getNodesByParamFuzzy('name', value);
	}
}
 //根据某一条件查找节点 模糊查询
function getNodesByParamFuzzy(key, value, parentNode){
    var nodes = zTree_Menu.getNodesByParamFuzzy(key, value, parentNode);  
    //取消之前的高亮显示
    highlightNodes(zTree_Menu, zTree_Menu.highlightNodeList, false);
    //高亮显示
    highlightNodes(zTree_Menu, nodes, true);
    zTree_Menu.highlightNodeList = nodes;
    //选中第一个
    if(null != nodes && nodes.length > 0){
        zTree_Menu.expandAll(false);
		var pNode=nodes[0].getParentNode();
		zTree_Menu.expandNode(pNode,true);
		zTree_Menu.expandNode(nodes[0],true);
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
function getFontCss(treeId, node){
    return (!!node.highlight) ? {color:"#A60000", "font-weight":"bold"} : {color:"#333", "font-weight":"normal"};
}
//返回首页的处理
function homeHandler(){
	zTree_Menu.expandAll(false);
	top.positionType="none";
	jQuery.jCookie('leftTreeNodeId',"false");
}