var zTree_Menu = null;
var treeData;
function initComplete(){
	setting.view = {
        fontCss: getFontCss
    };
	setting.callback = {
		onClick: onClick	
	};
	$("#scrollContent").css({
		"overflowY":"hidden"
	});
	$("#vTab").bind("actived",function(e,i){
		initTree(i);
		//存储点击tab索引
		jQuery.jCookie('leftvtabIdx',i.toString());
	});
	//每次刷新时保持上次打开的
	showContent();
}
function showContent(){
	//还原上次选中的tab
	var vtabIdx=jQuery.jCookie('leftvtabIdx');
	if(vtabIdx==false||vtabIdx=="false"){
		initTree(0);
	}
	else{
		var idx=parseInt(vtabIdx);
		$("#vTab").verticalTabSetIdx(idx);
		initTree(idx);
	}
	
}
function initTree(i){
	var item = $("#vTab").find("a").eq(i).data("item");
	setting.initTree(i, item, function(result){
		zTree_Menu = $.fn.zTree.init($("#treeDemo"), setting, result);
	});
}
function onClick(e, treeId, treeNode){
	//单击展开
	var zTree = $.fn.zTree.getZTreeObj("treeDemo");
	zTree.expandNode(treeNode);
	//出现进度条
	if(treeNode.url!=null){
		showProgressBar();
		//每次点击时设置当前位置内容
		if(treeNode.getParentNode()){
			top.positionContent=uncompile(quiLanguage.position.title)+treeNode.getParentNode().name+">>"+treeNode.name;
		}
		else{
			top.positionContent=uncompile(quiLanguage.position.title)+treeNode.name;
		}
		top.positionType="simple";
	}
}
