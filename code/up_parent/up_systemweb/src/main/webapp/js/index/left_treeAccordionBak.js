var zTree_Menu = null;
function initComplete(){
	setting.view = {
		fontCss: getFontCss,
		showLine: false,
//		showIcon: false,
		selectedMulti: false,
		dblClickExpand: false,
//		addDiyDom: addDiyDom
	};
	setting.callback = {
		beforeClick:beforeClick,
		onClick: onClick	
	};
	initTreeMenu();
}

function initTreeMenu(){
	setting.initTree(0, {code: setting.systemCode}, function(result){
		zTree_Menu = $.fn.zTree.init($("#treeDemo"), setting, result);
		setTimeout(function(){
			showContent();
			 $("#searchKey").keydown(function(event){
			 	if(event.keyCode==13){
					findNode();
				}
			 });
		},200);
	});
}
	
//每次刷新时保持上次打开的
function showContent(){
	var treeNodeId=jQuery.jCookie('leftTreeNodeId');
	if(treeNodeId==false||treeNodeId=="false"){
		top.positionType="simple";
		top.positionContent="【"+uncompile(quiLanguage.treeAccordion.positionContent1)+"】";
	}else{
		if(zTree_Menu){
			var node = zTree_Menu.getNodeByParam("id", treeNodeId);
			//展开上次选中的
			zTree_Menu.selectNode(node);
			
			//如果上次选中的为第一级或第二级节点，则添加选中样式
			if (node.level === 0||node.level === 1) {
				//得到当前点击节点，添加选中样式
				var a = $("#" + node.tId + "_a");
				a.click();
			}
			if(node.url){
				//每次刷新时设置当前位置内容
				top.positionType="simple";
				if(node.getParentNode()){
					top.positionContent="【"+uncompile(quiLanguage.treeAccordion.positionContent2)+node.getParentNode().name+">>"+node.name+"】";
				}
				//打开上次的链接
				top.frmright.location=node.url;
			}
		}
	}
}
//每次点击切换状态和样式
function beforeClick(treeId, treeNode) {
	if (treeNode.level === 0) {
		//第一级移除选中样式
		$("#"+treeId).find("a").each(function(){
			if($(this).hasClass("curLevel0")){
				$(this).removeClass("curLevel0");
			}
		});
		
		var a = $("#" + treeNode.tId + "_a");
		a.addClass("curLevel0");
	
		//单击展开或收缩
		if(treeNode.open){
			zTree_Menu.expandNode(treeNode,false);
			var a = $("#" + treeNode.tId + "_a");
			a.removeClass("curLevel0");
			a.removeClass("curSelectedNode");
		}
		else{
			zTree_Menu.expandAll(false);
			zTree_Menu.expandNode(treeNode);
		}
	}
	else if (treeNode.level === 1) {
		//第二级移除选中样式
		$("#"+treeId).find("a").each(function(){
			if($(this).hasClass("curLevel1")){
				$(this).removeClass("curLevel1");
			}
		});
		
		var a = $("#" + treeNode.tId + "_a");
		a.addClass("curLevel1");
		//单击展开或收缩
		if(treeNode.open){
			zTree_Menu.expandNode(treeNode,false);
			var a = $("#" + treeNode.tId + "_a");
			a.removeClass("curLevel1");
			a.removeClass("curSelectedNode");
		}
		else{
			zTree_Menu.expandNode(treeNode);
			var a2 = $("#" + treeNode.getParentNode().tId + "_a");
			a2.addClass("curLevel0");
		}
	}
	else{
		zTree_Menu.expandNode(treeNode);
	}
}
		
function onClick(e,treeId, treeNode){
	if (treeNode.level === 0) {
		if(!treeNode.open){
			var a = $("#" + treeNode.tId + "_a");
			a.removeClass("curSelectedNode");
		}
	}
	
	//出现进度条
	if(treeNode.url!=null){
		showProgressBar();
		
		//每次点击时设置当前位置内容
		if(treeNode.getParentNode()){
			top.positionContent="【"+uncompile(quiLanguage.treeAccordion.positionContent2)+treeNode.getParentNode().name+">>"+treeNode.name+"】";
		}
		else{
			top.positionContent="【"+uncompile(quiLanguage.treeAccordion.positionContent2)+treeNode.name+"】";
		}
		top.positionType="simple";
	}
	
	//存储点击节点id
	jQuery.jCookie('leftTreeNodeId',treeNode.id.toString());
	if (treeNode.level === 0){
		if(!treeNode.open){
			jQuery.jCookie('leftTreeNodeId',"false");
		}
	}
}

function addDiyDom(treeId, treeNode) {
	var aObj = $("#" + treeNode.tId + "_a");
	if ($("#infoBtn_"+treeNode.id).length>0) return;
    if(treeNode.isParent){
        var infoCon= $('<div class="ztree_custom_info"></div>');
        var ul=$('<ul></ul>');
        $.each(treeNode.info, function(idx, item){
            var li=$('<li></li>');
            li.text(item.key+"："+item.value);
            if(item.colspan){
                if(item.colspan=="true"||item.colspan==true){
                    li.width(240);
                }
            }
            else{
                li.width(120);
            }
            ul.append(li);
        });
        ul.append('<div class="clear"></div>');
        infoCon.append(ul);
        aObj.after(infoCon);
    }
};

