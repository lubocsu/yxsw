/**
 * 设备js对象
 */
(function() {
	var path = "";
	var value="";
	
	var treeObj = null;
	var selectedNode = null;
	var equipmentId = null;
	
	var Equipment = window.Equipment = {
			
		config : function(config){
			this.config = config;
			path = config.path;
			//value1=config.value1;
			this.setTree();
			this.initTree();
		},
		// 初始化树
		initTree : function() {
			jQuery.ajax({
				type : 'POST',
				//url : path+'/eqAsCheck/getEquipmentTree1?input='+value1,
				url : path+'/eqAsCheck/getEquipmentTree1',
				// url:path+'/eqAsCheck/getCheck',
				 data: { 'input': value } ,
				dataType : 'json',
				success : function(nodes){
					treeObj = $.fn.zTree.init(jQuery("#tree"), Equipment.treeSetting, nodes);

				},
				error : function(a){
					Dialog.alert("设备树获取失败");
				}
			});
		},
		
		refreshTree: function(parentId){
			treeObj.reAsyncChildNodes(null, "refresh",true);
			equipmentId=parentId;
		},
		

		
	
		
		setTree : function(){
			this.treeSetting = {
					view: {
						dblClickExpand: true,
						//showIcon: false,
						fontCss:function(treeId,node){
							return(!!node.highlight) ? {color:"#FF0000", "font-weight":"bold"} : {color:"#333", "font-weight":"normal"};
						}
					},
					async:{
						enable :true,
						dataType: 'JSON',
						//url:path+'/eqAsCheck/getEquipmentTree1'
					//	url : path+'/eqAsCheck/getEquipmentTree1?input='+value
							url : path+'/eqAsCheck/getEquipmentTree1',
							 data: { 'input': value} ,
						
					},
					callback: {
						onClick: this.zTreeSelect,
						/*onAsyncSuccess:function(event, treeId, treeNode, msg){
							treeObj.expandAll(true);
							if(equipmentId.length!=0){
								selectedNode = treeObj.getNodeByParam("id",equipmentId,null);
							}
							if(null!=selectedNode){
								treeObj.selectNode(treeObj.getNodeByParam("id", selectedNode.id, null));
							}
						}*/
					},
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
			//jQuery("#equipment-detail-iframe").attr("src",path+"/Equipment/showDetail?sbTypeId="+id + "&parentName=" + parentNodeName );
			jQuery("#eq-detail-iframe").attr("src",path+"/eqAsCheck/showEqInfo?eqId="+id );
		}
		
	};
})();

function searchTree1(){
	value = jQuery("#searchTree").val();
	// Equipment.refreshTree();
	console.log(value);
	//获取根节点
	var zTree = $.fn.zTree.getZTreeObj("tree");
	//zTree.reAsyncChildNodes(null,"refresh");
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