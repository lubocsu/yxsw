//点击右键处理
function OnRightClick(event, treeId, treeNode) {
    //在节点之外显示一种菜单
    if (!treeNode && event.target.tagName.toLowerCase() != "button" && $(event.target).parents("a").length == 0) {
        zTree.cancelSelectedNode();
        showRMenu("root", event.clientX, event.clientY);
    //在节点里面显示另一种菜单
    } else if(treeNode && !treeNode.noR) {
        zTree.selectNode(treeNode);
        showRMenu(treeNode.menuType, event.clientX, event.clientY);
    }
}

//根据设置弹出不同的右键菜单
function showRMenu(type, x, y) {
    $("#rMenu ul").show();
    if(type == "root") {//节点外菜单
        $("#m_add_select").show();
        $("#m_del_select").show();
        $("#m_cls_select").show();
        $("#m_add").hide();
        $("#m_del").hide();
    }else if(type == "add") {//显示允许
        $("#m_add").show();
        $("#m_del").hide();
        $("#m_add_select").hide();
        $("#m_del_select").hide();
        $("#m_cls_select").hide();
    }else if(type == "del") {//显示禁止
        $("#m_add").hide();
        $("#m_del").show();
        $("#m_add_select").hide();
        $("#m_del_select").hide();
        $("#m_cls_select").hide();
    }else if(type == "either") {//都显示
        $("#m_add").show();
        $("#m_del").show();
        $("#m_add_select").hide();
        $("#m_del_select").hide();
        $("#m_cls_select").hide();
    }

    $("#rMenu").css({"top":y+"px", "left":x+"px", "visibility":"visible"});

    //点击菜单外时隐藏菜单
    $("body").bind("mousedown", onBodyMouseDown);
}

//隐藏菜单
function hideRMenu() {
    if ($("#rMenu")) $("#rMenu").css({"visibility": "hidden"});
    $("body").unbind("mousedown", onBodyMouseDown);
}

//点击菜单外时隐藏菜单
function onBodyMouseDown(event){
    if (!(event.target.id == "rMenu" || $(event.target).parents("#rMenu").length > 0)) {
        $("#rMenu").css({"visibility" : "hidden"});
    }
}

//以下是点击右键菜单的处理函数
function addPermission(){
	hideRMenu();
	var nodes = zTree.getSelectedNodes();
	if (nodes && nodes.length > 0) {
		top.Dialog.confirm("确认允许访问  " + nodes[0].name + " 吗？",function(){
	        var relateId = $("#relateId").val();
	        //此处进行ajax后台数据处理
	        $.post(path + "/permissionData/savePermissionData",
	        	{"type":1, "orgId":nodes[0].id, "relateId":relateId, "status":"1","systemCode":systemCode},
	        	function(result){
	        		if(result.success){
	        			// 刷新页面
		        		window.location.reload(true);
	        		}
	        },"json");
			// 刷新页面
			window.location.reload(true);
        });
	}
}
function delPermission(){
	hideRMenu();
	var nodes = zTree.getSelectedNodes();
	if (nodes && nodes.length > 0) {
		top.Dialog.confirm("确认禁止访问  " + nodes[0].name + " 吗？",function(){
			var relateId = $("#relateId").val();
            //此处进行ajax后台数据处理
            $.post(path + "/permissionData/savePermissionData", 
            	{"type":1, "orgId":nodes[0].id, "relateId":relateId, "status":"0","systemCode":systemCode}, 
            	function(result){
            		if(result.success){
	        			// 刷新页面
		        		window.location.reload(true);
	        		}
			},"json");
        });
	}
}
function addSelectPermissions(){
	hideRMenu();
	var checkedNodes = zTree.getCheckedNodes(true);
	if(checkedNodes.length == 0 ){
		top.Dialog.alert("请先选择机构！",null,null,null,2)
	}else{
		var msg = "确认允许访问选中的 " + checkedNodes.length + "个机构吗？";
		top.Dialog.confirm(msg, function(){
			var ids = "";
			for(var i = 0; i < checkedNodes.length; i++){
				if(i == 0){
					ids = ids + checkedNodes[i].id;
				}else{
					ids = ids + "," + checkedNodes[i].id;
				}		
			}
			//此处进行ajax后台数据处理
			var relateId = $("#relateId").val();
	        $.post(path + "/permissionData/savePermissionDataList", 
	        	{"type":1, "orgIds":ids, "relateId":relateId, "status":"1","systemCode":systemCode}, 
	        	function(result){
	        		if(result.success){
	        			// 刷新页面
		        		window.location.reload(true);
	        		}
			},"json");
	    });
	}
}
function delSelectPermissions(){
	hideRMenu();
	var checkedNodes = zTree.getCheckedNodes(true);
	if(checkedNodes.length == 0 ){
		top.Dialog.alert("请先选择机构！",null,null,null,2)
	}else{
		var msg = "确认禁止访问选中的 " + checkedNodes.length + "个机构吗？";
		top.Dialog.confirm(msg, function(){
			var ids = "";
			for(var i = 0; i < checkedNodes.length; i++){
				if(i == 0){
					ids = ids + checkedNodes[i].id;
				}else{
					ids = ids + "," + checkedNodes[i].id;
				}		
			}
			//此处进行ajax后台数据处理
			var relateId = $("#relateId").val();
	        $.post(path + "/permissionData/savePermissionDataList", 
	        	{"type":1, "orgIds":ids, "relateId":relateId, "status":"0","systemCode":systemCode}, 
	        	function(result){
	        		if(result.success){
	        			// 刷新页面
		        		window.location.reload(true);
	        		}
			},"json");
	    });
	}
}
function clsSelectPermissions(){
	hideRMenu();
	var checkedNodes = zTree.getCheckedNodes(true);
	if(checkedNodes.length == 0 ){
		top.Dialog.alert("请先选择机构！",null,null,null,2)
	}else{
		var msg = "确认删除选中的 " + checkedNodes.length + "个数据访问权限吗？";
		top.Dialog.confirm(msg, function(){
			var ids = "";
			for(var i = 0; i < checkedNodes.length; i++){
				if(i == 0){
					ids = ids + checkedNodes[i].id;
				}else{
					ids = ids + "," + checkedNodes[i].id;
				}		
			}
			//此处进行ajax后台数据处理
			var relateId = $("#relateId").val();
	        $.post(path + "/permissionData/delPermissionDataList", 
	        	{"type":1, "orgIds":ids, "relateId":relateId,"systemCode":systemCode}, 
	        	function(result){
	        		if(result.success){
	        			// 刷新页面
		        		window.location.reload(true);
	        		}
			},"json");
	    });
	}
}