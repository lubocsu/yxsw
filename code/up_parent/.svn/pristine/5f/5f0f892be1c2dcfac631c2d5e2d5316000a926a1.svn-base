/**
 * 设备类型js对象
 */
(function() {
	var path = "";
	var treeObj = null;
	var selectedNode = null;
	var equipmentTypeId = null;
	var EquipmentType = window.EquipmentType = {
			
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
				url : path+'/spotRelate/getPointTree',
				dataType : 'json',
				success : function(nodes){
					treeObj = $.fn.zTree.init(jQuery("#tree"), EquipmentType.treeSetting, nodes);
				},
				error : function(a){
					Dialog.alert("获取巡检点失败");
				}
			});
		},
		
		refreshTree: function(parentId){
			treeObj.reAsyncChildNodes(null, "refresh",true);
			equipmentTypeId=parentId;
		},
		
		setTree : function(){
			this.treeSetting = {
					view: {
						dblClickExpand: true,
						fontCss: getNodeFont,
						nameIsHTML: true,
					},
					async:{
						enable :true,
						dataType: 'JSON',
						url:path+'/spotRelate/getPointTree'
					},
					callback: {
						onClick: this.zTreeSelect,
						onAsyncSuccess:function(event, treeId, treeNode, msg){
							treeObj.expandAll(true);
							if(null != equipmentTypeId.length &&  undefined != equipmentTypeId.length && equipmentTypeId.length!=0){
								selectedNode = treeObj.getNodeByParam("id",equipmentTypeId,null);
							}
							if(null!=selectedNode){
								treeObj.selectNode(treeObj.getNodeByParam("id", selectedNode.id, null));
							}
						}
					},
			};
		},
		
		zTreeSelect : function (event,treeId,treeNode) {
			if(treeNode.flag == "noClick"){
				return;
			}
			var id = treeNode.id;
			jQuery("#spot-iframe").attr("src",path+"/spotRelate/showDetail?spotId="+id);
		}
		
	};
})();

//关联的有颜色变化
function getNodeFont(treeId, node) {

    return node.font ? node.font : {};

}
