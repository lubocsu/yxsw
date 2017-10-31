$.fn.verticalTabSetIdx=function(i){
	var $instance=$(this);
	var totalLength;
	var $obj;
	if ($instance.attr("iframeMode") == true || $instance.attr("iframeMode") == "true") {
		$obj=$instance.find("a").eq(i);
		var dataObj=$instance.data("data");
		if(!dataObj){
			return;
		}
		//获取数据前缀
		var dataRoot="list";
		if($instance.attr("dataRoot")){
			dataRoot=$instance.attr("dataRoot");
		}
		//获取选项卡个数
		totalLength=dataObj[dataRoot].length;
		
		var $iframe=$instance.find(".verticalTab_con").eq(0).find("iframe").eq(0);
		$iframe.attr("src",dataObj[dataRoot][i].url);
		
		$obj.prevAll("a").find("span").removeClass("verticalTab_current_middle");
		$obj.nextAll("a").find("span").removeClass("verticalTab_current_middle");
		$obj.find("span").addClass("verticalTab_current_middle");
	}
	else{
		$obj=$instance.find(".verticalTab_normal_middle").eq(i);
		totalLength=$instance.find(".verticalTab_con").length;
		$instance.find(".verticalTab_con").hide();
		$instance.find(".verticalTab_con").eq(i).fadeIn();
		
		$obj.prevAll("span").removeClass("verticalTab_current_middle");
		$obj.nextAll("span").removeClass("verticalTab_current_middle");
		$obj.addClass("verticalTab_current_middle");
	}
	
	
	
	$obj.prevAll("span").removeClass("verticalTab_current_top");
	$obj.prevAll("span").removeClass("verticalTab_current_middle");
	$obj.prevAll("span").removeClass("verticalTab_current_bottom");
	
	$obj.nextAll("span").removeClass("verticalTab_current_top");
	$obj.nextAll("span").removeClass("verticalTab_current_middle");
	$obj.nextAll("span").removeClass("verticalTab_current_bottom");
	
	
	$obj.prev().addClass("verticalTab_current_top");
	$obj.next().addClass("verticalTab_current_bottom");
	
	$instance.trigger("actived",i);
};

$.fn.verticalTabSetEnable=function(i,enable){
	var $instance=$(this);
	var totalLength;
	var $obj;
	if ($instance.attr("iframeMode") == true || $instance.attr("iframeMode") == "true") {
		var dataObj=$instance.data("data");
		if(!dataObj){
			return;
		}
		//获取数据前缀
		var dataRoot="list";
		if($instance.attr("dataRoot")){
			dataRoot=$instance.attr("dataRoot");
		}
		//获取选项卡个数
		totalLength=dataObj[dataRoot].length;
		
		$obj=$instance.find("a").eq(i);
		if(enable==true){
			$obj.find("span").removeClass("disabled");
			$obj.bideClickIframe(true,$instance,i,totalLength);
		}
		else{
			$obj.find("span").addClass("disabled");
			$obj.bideClickIframe(false,$instance,i,totalLength);
		}
	}
	else{
		totalLength=$instance.find(".verticalTab_con").length;
		
		$obj=$instance.find(".verticalTab_normal_middle").eq(i);
		if(enable==true){
			$obj.removeClass("disabled");
			$obj.bideClick(true,$instance,i,totalLength);
		}
		else{
			$obj.addClass("disabled");
			$obj.bideClick(false,$instance,i,totalLength);
		}
	}
	
};


$.fn.verticalTabRender = function() {
	var $instance=$(this);
	
	var totalLength = null;
	
	//创建选项卡容器
	var $tabCon=$('<div class="verticalTab_left"></div>');
	
	//获取初始时索引
	var selectedIdx=0;
	if($instance.attr("selectedIdx")){
		selectedIdx=Number($instance.attr("selectedIdx"));
	}
	
	//获取是否禁用
	var enabled=true;
	if($instance.attr("allItemDisabled")==true||$instance.attr("allItemDisabled")=="true"){
		enabled=false;
	}
	
	var iframeTarget="frmrightChild";
	var $iframe;
	var showProgress=false;
	if ($instance.attr("showProgress") == true || $instance.attr("showProgress") == "true") {
		showProgress=true;
	}
	
	if ($instance.attr("iframeMode") == true || $instance.attr("iframeMode") == "true") {
		//内容添加样式
		if($instance.find(">div").length>1){
			alert(uncompile(quiLanguage.basicTab.errorMessage));
		}
		$instance.find(">div").addClass("verticalTab_con");
		$instance.find(">div").css({
			"overflowX":"hidden",
			"overflowY":"auto"
		});
		
		//获取iframe跳转的target
		$iframe=$instance.find(">div").eq(0).find("iframe").eq(0);
		if($iframe.attr("name")){
			iframeTarget=$iframe.attr("name");
		}
		
		
		//获取数据前缀
		var dataRoot="list";
		if($instance.attr("dataRoot")){
			dataRoot=$instance.attr("dataRoot");
		}
		//获取参数
		var paramsStr=$instance.attr("params");
		var paramsObj;
		if(paramsStr){
			
			try {
				paramsObj=JSON.parse(paramsStr);
			}
			catch(e){
				paramsObj="";
				alert(uncompile(quiLanguage.basicTab.paramErrorMessage));
			}
		}
		else{
			paramsObj="";
		}
		//获取数据
		var dataObj="";
		var urlStr=$instance.attr("url");
		var dataStr=$instance.attr("data");
		var dataObj2=$instance.data("data");
		//优先使用data
		if(dataObj2){
			createOptions(dataObj2);
		}
		else if(dataStr){
			try {
				dataObj=JSON.parse(dataStr);
			}
			catch(e){
				dataObj="";
				alert(uncompile(quiLanguage.basicTab.dataErrorMessage));
			}
			$instance.data("data",dataObj);
			createOptions(dataObj);
		}
		else if(urlStr){
			$.ajax({ 
			url: $instance.attr("url"), 
			dataType:"json",
			data:paramsObj,
			error:function(){
				alert(uncompile(quiLanguage.basicTab.urlErrorMessage));
			},
			success: function(data){
				$instance.data("data",data);
				dataObj=data;
				createOptions(data);
				
		    }
			});
		}
	}
	else {
		//获取选项卡个数
		totalLength=$instance.find(">div").length;
		$instance.find(">div").each(function(i){
			//获取标签名称
			var tabName = uncompile(quiLanguage.basicTab.tabName);
			if ($(this).attr("name")) {
				tabName = $(this).attr("name");
			}
			
			//内容添加样式
			$(this).addClass("verticalTab_con");
			//隐藏其他内容
			if (selectedIdx != i) {
				$(this).hide();
			}
			
			//设置标签样式
			var $firstObj = $('<span class="verticalTab_normal_top"></span>');
			$tabCon.append($firstObj);
			if (selectedIdx == i) {
				$firstObj.addClass("verticalTab_current_top");
			}
			var $obj1 = $('<span class="verticalTab_normal_middle"><div class="verticalTab_normal_middle_con"></div></span>');
			$tabCon.append($obj1);
			//添加选项标题
			$obj1.find("div").text(tabName);
			//存储自己所在的索引
			$obj1.data("idx", i);
			//禁用
			if(!enabled){
				$obj1.addClass("disabled");
			}
			var itemEnable=true;
			if($(this).attr("itemDisabled")=="true"||$(this).attr("itemDisabled")==true){
				$obj1.addClass("disabled");
				itemEnable=false;
			}
			
			if (selectedIdx == i) {
				$obj1.addClass("verticalTab_current_middle");
			}
			
			var $lastObj = $('<span class="verticalTab_normal_bottom"></span>');
			$tabCon.append($lastObj);
			if (selectedIdx == i) {
				$lastObj.addClass("verticalTab_current_bottom");
			}
			
			//点击事件
			if (enabled&&itemEnable) {
				$obj1.bideClick(true,$instance,i,totalLength);
			}
			
		});
		
	}
	$instance.prepend($tabCon);
	$instance.append('<div class="clear"></div>');
	//如果没有选项卡的时候隐藏选项卡DIV，并调整菜单显示区域宽度
	if(!totalLength || totalLength<1){
		$instance.find(".verticalTab_left").hide();
		$instance.find(".verticalTab_con").width(212);
	}
	function createOptions(dataObj){
		if(!dataObj){
			return;
		}
		//获取选项卡个数
		totalLength=dataObj[dataRoot].length;
		
		$.each(dataObj[dataRoot],function(i,item){
			//获取标签名称
			var tabName = uncompile(quiLanguage.basicTab.tabName);
			if (item.name) {
				tabName = item.name;
			}
			
			//设置初始的iframe路径
			if (selectedIdx == i) {
				$iframe.attr("src",item.url);
			}
			
			//设置标签样式
			var $firstObj = $('<span class="verticalTab_normal_top"></span>');
			$tabCon.append($firstObj);
			if (selectedIdx == i) {
				$firstObj.addClass("verticalTab_current_top");
			}
			
			var $obj1 = $('<span class="verticalTab_normal_middle"><div class="verticalTab_normal_middle_con"></div></span>');
			//创建链接
			var $a=$('<a></a>');
			$a.attr("href",item.url);
			$a.attr("target",iframeTarget);
			
			//添加选项标题
			$obj1.find("div").text(tabName);
			$a.append($obj1);
			$tabCon.append($a);
			
			if(!enabled){
				$obj1.addClass("disabled");
			}
			
			var itemEnable=true;
			if(item.itemDisabled){
				if(item.itemDisabled=="true"||item.itemDisabled==true){
					$obj1.addClass("disabled");
					itemEnable=false;
				}
			}
			//存储自己所在的索引
			$a.data("item", item);
			$obj1.data("idx", i);
			if (selectedIdx == i) {
				$obj1.addClass("verticalTab_current_middle");
			}
			
			var $lastObj = $('<span class="verticalTab_normal_bottom"></span>');
			$tabCon.append($lastObj);
			if (selectedIdx == i) {
				$lastObj.addClass("verticalTab_current_bottom");
			}
			
			//点击事件
			if (enabled&&itemEnable) {
				$a.bideClickIframe(true,$instance,i,totalLength);
			}
			else{
				$a.bideClickIframe(false,$instance,i,totalLength);
			}
		});
	}
};

$.fn.bideClick=function(enableClick,$instance,i,totalLength){
	var clickObj=$(this);
	clickObj.unbind("click");
	if(enableClick){
		clickObj.bind("click",{},function(){
			$instance.find(".verticalTab_con").hide();
			$instance.find(".verticalTab_con").eq(i).fadeIn();
			
			$(this).prevAll("span").removeClass("verticalTab_current_top");
			$(this).prevAll("span").removeClass("verticalTab_current_middle");
			$(this).prevAll("span").removeClass("verticalTab_current_bottom");
			
			$(this).nextAll("span").removeClass("verticalTab_current_top");
			$(this).nextAll("span").removeClass("verticalTab_current_middle");
			$(this).nextAll("span").removeClass("verticalTab_current_bottom");
			
			$(this).addClass("verticalTab_current_middle");
			$(this).prev().addClass("verticalTab_current_top");
			$(this).next().addClass("verticalTab_current_bottom");
			$instance.trigger("actived",i);
		});
	}
	else{
		
	}
};

$.fn.bideClickIframe=function(enableClick,$instance,i,totalLength){
	var clickObj=$(this);
	clickObj.unbind("click");
	if(enableClick){
		clickObj.bind("click",{},function(){
			$(this).prevAll("span").removeClass("verticalTab_current_top");
			$(this).prevAll("span").removeClass("verticalTab_current_middle");
			$(this).prevAll("span").removeClass("verticalTab_current_bottom");
			
			$(this).prevAll("a").find("span").removeClass("verticalTab_current_middle");
			
			$(this).nextAll("span").removeClass("verticalTab_current_top");
			$(this).nextAll("span").removeClass("verticalTab_current_middle");
			$(this).nextAll("span").removeClass("verticalTab_current_bottom");
			
			$(this).nextAll("a").find("span").removeClass("verticalTab_current_middle");
			
			$(this).find("span").addClass("verticalTab_current_middle");
			$(this).prev().addClass("verticalTab_current_top");
			$(this).next().addClass("verticalTab_current_bottom");
			if($instance.attr("showProgress")=="true"||$instance.attr("showProgress")==true){
				try{
					showProgressBar();
				}
				catch(e){}
			}
			$instance.trigger("actived",i);
		});
	}
	else{
		clickObj.bind("click",{},function(){
			return false;
		});
	}
};