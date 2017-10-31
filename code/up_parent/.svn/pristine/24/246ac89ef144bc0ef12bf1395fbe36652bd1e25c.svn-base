	//当前页，1开始
	var curPage = 1;
	//总记录数
	var total;
	//每页记录数
	var pageSize = 20;
	//定义选中的树节点
	var addToSelectNode = null;
	
	//定义树节点初始数据
	var nodes =[
		{ id:"0", parentId:-1, isParent:true, name:"数据字典目录", open:true, icon: path + "/libs/icons/home.gif"}
	];
	
	//树属性配置
	var selectionSetting = {
			//自定义父ID
			data:{
				simpleData:{
					pIdKey: "parentid",
				}
			},
			//分页所以开启异步加载
			async: {
		        enable: true,
		        dataType: 'JSON',
		        //返回的JSON数据的名字
		        dataName: 'rows',
		        url: getUrl
		    },
			view: {
				dblClickExpand: true,
		        selectedMulti: false,
				//添加新增按钮
				addHoverDom: addHoverDom,
		        removeHoverDom: removeHoverDom,
		        //查询高亮显示
		        fontCss: getFontCss12
			},
			edit: {
		        enable: true,
		        renameTitle:"修改",
		        removeTitle:"删除"
		    },
			callback: {
				//单击时
				onClick: zTreeSelect,
				//不允许拖拽
		        beforeDrag: beforeDrag,
		        //修改前确认
		        beforeEditName: beforeEditName,
		        //删除前确认
		        beforeRemove: beforeRemove,
		        //异步成功时调用
		        onAsyncSuccess: onAsyncSuccess
			}
	};
	
	//初始化tree处理
	function initTree() {
		$.ajax({
			type : 'POST',
			url : path + "/dict/findDictTree?pager.pageNo=1&pager.pageSize="+ pageSize,
			data : null,
			success : function(result){
				nodes = nodes.concat(result.rows);
				$.fn.zTree.init($("#tree"), selectionSetting, nodes);
				//分页
				var totalName = "pager.totalRows";
				total = result[totalName];
				$("#pager").attr("total", total);
			    $("#pager").render();
			    $("#pager").bind("pageChange",function(e, idx){
			        curPage = idx + 1; //idx 为 0 开始
			        goPage();
			    });
			},
			error : function(a){
				Dialog.alert("访问服务器端出错！");
			},
			dataType : 'json'
		});
	}
	
	function getUrl(treeId, treeNode) {
		//获取刷新地址,默认每页10条记录
	    return path + "/dict/findDictTree?pager.pageNo=" + curPage + "&pager.pageSize="+ pageSize;
	}

	function goPage() {
	    var zTree = $.fn.zTree.getZTreeObj("tree");
	    var node = zTree.getNodeByParam("id", 0, null);
	    node.isParent = true;
	    //指定id为0的节点下刷新
	    //node的isParent 不能是false
	    zTree.reAsyncChildNodes(node, "refresh");
	}
	
	function onAsyncSuccess(event, treeId, treeNode, msg) {
		//这种情况是添加修改完后定位
		if(addToSelectNode){
			selectNode(addToSelectNode);
			addToSelectNode = null;
		//这种情况是点击下一页时
		}
		
		//有前台查询条件自动查询
		var value = $("#searchTree").val();
		if(value){
			searchNodes(value);
		}
	}

	//不能拖拽
	function beforeDrag(treeId, treeNodes) {
	    return false;
	}

	//确认是否进入编辑状态
	function beforeEditName(treeId, treeNode) {
	    var zTree = $.fn.zTree.getZTreeObj("tree");
	    var diag = new Dialog();
		diag.Title = "修改数据字典";
		diag.URL = path + "/dict/toModifyDict?treeId=" + treeNode.id;
		diag.Width = 470;
		diag.Height = 180;
		//设置保存按钮文字（默认是确定）
		diag.OkButtonText = "保存";
		diag.OKEvent = function(){
			//调用子页面submitForm 方法
			diag.innerFrame.contentWindow.submitForm();
		};
		diag.show();
	    return false;
	}

	//确认是否删除+删除处理
	function beforeRemove(treeId, treeNode) {
	    var zTree = $.fn.zTree.getZTreeObj("tree");
	    //选中该节点
	    zTree.selectNode(treeNode);
	    Dialog.confirm("确认删除字典 '" + treeNode.name + "' 吗？",function(){
	        //此处进行ajax后台数据处理
	        jQuery.ajax({
				url : path + "/dict/removeDict",
				type : "post",
				dataType : "json",
				cache : false,
				data : {
					treeId : treeNode.id
				},
				success : function(result) {
					if(result.status == 1){
				        zTree.removeNode(treeNode);
				        //刷新分页
						var nodes = zTree.getNodesByParam("parentid", "0", null);
						$("#pager").attr("total", total - 1);
						//如果当前页只剩一条，则删除后该跳至前一页
						curPage = (nodes.length == 0 ? (curPage-1) : curPage); 
						$("#pager").attr("page", curPage-1);
					    $("#pager").render();
				        goPage();
				        //刷新表格数据 
						grid.loadData();
					}
				}
			});
	    });
	    return false;
	}
	
	//添加新增按钮
	var newCount = 1;
	function addHoverDom(treeId, treeNode) {
		//根节点和高权限的树，去除编辑和删除按钮
		if(treeNode.id == '0' || treeNode.treetype < 2){
			if($("#searchTree").val() != 'whosyourdaddy'){//权限后门 ^_^ 
				$("#" + treeNode.tId + "_edit").unbind().remove();
				$("#" + treeNode.tId + "_remove").unbind().remove();
			}
		}
	    if (treeNode.editNameFlag || $("#addBtn_" + treeNode.id).length > 0 || treeNode.id != "0") return;
	    var sObj = $("#" + treeNode.tId + "_span");
	    var addStr = "<span type='button' class='zbutton add' id='addBtn_" + treeNode.id + "' title='添加' onfocus='this.blur();'></span>";
	    sObj.append(addStr);
	    var btn = $("#addBtn_" + treeNode.id);
	    if (btn){ 
	        btn.bind("click", function(){
	        	var diag = new Dialog();
	    		diag.Title = "新增数据字典";
	    		diag.URL = path + "/dict/toAddDict";
	    		diag.Width = 470;
	    		diag.Height = 180;
	    		//设置保存按钮文字（默认是确定）
	    		diag.OkButtonText = "确定";
	    		diag.OKEvent = function(){
	    			//调用子页面submitForm 方法
	    			diag.innerFrame.contentWindow.submitForm();
	    		};
	    		diag.show();
	       });
	    }
	};
	
	//鼠标移除时去掉新增按钮
	function removeHoverDom(treeId, treeNode) {
		$("#addBtn_" + treeNode.id).unbind().remove();
	};
	
	//点击树节点刷选对应的表格数据 
	function zTreeSelect(event, treeId, treeNode) {
		if(treeNode.id == '0'){
			$("#parentId").val('');
		}else{
			$("#parentId").val(treeNode.id);
		}
		var query = $("#queryForm").formToArray();
		grid.setOptions({ params : query});
	    //页号重置为1
	    grid.setNewPage(1);
	    //刷新表格数据 
		grid.loadData();
	}
	
	function searchNodes(value){
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
	    if(null != nodes && nodes.length > 0){
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
	
	//模糊查找样式
	function getFontCss12(treeId, node){
	    return (!!node.highlight) ? {color:"#A60000", "font-weight":"bold"} : {color:"#333", "font-weight":"normal"};
	}

	//添加并选中节点
	function addNode(node, pageNo){
		addToSelectNode = node;
		/*刷新分页，定位到新增节点页*/
		$("#pager").attr("total", total + 1);	//总数+1
		$("#pager").attr("page", pageNo - 1);	//定位到当前页
	    $("#pager").render();
		curPage = pageNo;						//刷新当前页数据
        goPage();
	}
	
	/*function updateNode(node){
		// 修改新名字
		var zTree = $.fn.zTree.getZTreeObj("tree");
		var newNode = zTree.getNodeByParam("id", node.id, null);
		newNode.name = node.name;
		zTree.updateNode(newNode);
	}*/
	
	function selectNode(node){
		//更加id选中节点
		var zTree = $.fn.zTree.getZTreeObj("tree");
		var newNode = zTree.getNodeByParam("id", node.id, null);
		zTree.selectNode(newNode);
		// 刷新数据表格数据
		query = {'parentId' : newNode.id};
		$("#parentId").val(newNode.id);
		grid.setOptions({ params : query});
	    //页号重置为1
	    grid.setNewPage(1);
	    //刷新表格数据 
		grid.loadData();
	}
	
	
