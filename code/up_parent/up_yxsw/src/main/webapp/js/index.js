(function() {
	var insIndex = window.insIndex = {
		/** 主页所有定时器注册根（insIndex.intervals.） */
		intervals : { },

		/** 主页所有全局变量注册根 insIndex.globalObj.*/
		globalObj : { },

		/** 获取友好信息（用户信息） */
		getFriendlyInfo : function() {
			jQuery.ajax({
				type : "post",
				url : path + "/index/getFriendlyInfo",
				data : {
				},
				success : function(data) {
					jQuery("#username").html(data.userName);
					jQuery("#orgname").html("【" + data.orgName + "】");
					jQuery("#today").html(data.today);
				},
			});

		},
		/** 在页面指定区域打印时间 */
		printDateInfo : function() {
			var weekDayLabels = new Array("星期日","星期一","星期二","星期三","星期四","星期五","星期六");
			var now = new Date();
		    var year=now.getFullYear();
			var month=now.getMonth()+1;
			var day=now.getDate();
		    var currentime = year+"年"+month+"月"+day+"日 "+weekDayLabels[now.getDay()];
			return '【今天是'+currentime+"】";
		},

		locationHref : function(url) {
			location.href = url + "&tokenId=" + tokenId  + "&_platform=" + _platform;
		} ,
		
		locationPlatformIndex : function(){
			location.href = up_systemweb_platform+"?tokenId=" + tokenId ;
		},
		localtionSystemwebIndex : function(){
			location.href = up_systemweb_index+"?tokenId=" + tokenId + "&_platform=" + _platform;
		}

	};

})();

/**-----------需要加载及运行的一些东西-------------**/
jQuery(function() {
	
	/**--显示机构人员信息及时间信息--**/
	insIndex.getFriendlyInfo();
	/**--------控制菜单显示--------**/
	jQuery.ajax({
		type : "post",
		url : path + "/index/getIndexMainFuncList",
		async : false,
		success : function(rltData) {
			makeMenuLi(rltData);
			
			if(_system&&_system=="true"){
				jQuery("#header-menu").append('<li onclick=\'insIndex.localtionSystemwebIndex();\'><img style="margin-bottom:-5px;" src="'+path+ '/images/topmenu/icon_system.png" /><a>后台管理</a></li>');
			}
			if(_platform&&_platform=="true"){
				jQuery("#header-menu").append('<li onclick=\'insIndex.locationPlatformIndex();\'><img style="margin-bottom:-5px;" src="'+path+ '/images/topmenu/icon_home.png" /><a>首页</a></li>');
			}
			jQuery("#header-menu").append('<li onclick=\'top.Dialog.confirm("确定要退出系统吗",function(){logout();});\'><img style="margin-bottom:-5px;" src="'+path+ '/images/topmenu/icon_exit.png" /><a>退出</a></li>');
			// 菜单生成完成后默认选中第一个子菜单
			var firstMenu= jQuery(".menu-max li").find("a[target='index-iframe']");
			if(firstMenu.length>0){
				for ( var i in firstMenu) {
					$(firstMenu[i]).parent().addClass("selected");
					jQuery("iframe[class='index-iframe']").attr("src",$(firstMenu[i]).attr("href"));
					break;
				}
			}
		}
	});
});
// 循环生成菜单
function makeMenuLi(rltData){
	var childNode = new Array();
	// 渲染一级菜单
	for (var i = 0; i < rltData.length; i++) {
		var html = "<li ";
		if (rltData[i].parentid == path.substring(1,path.length)||rltData[i].parentid==null) {
			if (rltData[i].url) {
				html += 'id="'+rltData[i].id+'" onClick="menuClick(\'' + rltData[i].url + '\',\''+rltData[i].functiontype+'\',\''+rltData[i].param+'\')">';
				html += '<a><span class="li-images"></span>'+rltData[i].name+'</a></li>';
			}else{
				html += 'id="'+rltData[i].id+'"><a><span class="li-images"></span>'+rltData[i].name+'</a></li>';
			}
			jQuery(".menu-max").append(html);
		} else {
			childNode.push(rltData[i]);
		}
	}
	// 渲染子级菜单
	var childChildNode = new Array();
	for (var j = 0; j < childNode.length; j++) {
		var url = childNode[j].url;
		if(url){
			if(url.toLowerCase().indexOf("tokenid")==-1){
				if(url.indexOf("?")==-1){
					url += "?tokenId="+tokenId;
				}else{
					url += "&tokenId="+tokenId;
				}
			}
		}
		var html = '<li id="'+childNode[j].id+'"><a href="'+url+'" target="index-iframe"><span class="li-images"></span>'+childNode[j].name+'</a></li>'
		var menu = jQuery("#"+childNode[j].parentid);
		if(null!=menu&&undefined!=menu&&"undefined"!=menu&&menu.length>=1){
			if(menu.children("ul").length>0){
				menu.find("ul").append(html);
			}else{
				menu.append('<ul>'+html+'</ul>');
			}
		}else{
			childChildNode.push(childNode[j]);
		}
	}
	//递归生成更多子菜单
	if(childChildNode.length>0){
		makeMenuLi(childChildNode);
	}
}


/**
 * 主菜单跳转方式
 * @param url
 * @param functiontype
 * @param urlparam
 */
function menuClick(url,functiontype,urlparam){
	// 后台系统中菜单功能类型等于2表示在主窗口打开
	if(functiontype==2) {
		if(urlparam=='toMapDiv'){
		}
		if(urlparam=='toMisDiv'){/**隐藏地图菜单的打开方式**/
		}
		if(urlparam=='jsFunction'){
			eval(url);
		}
	}else if(functiontype=3){ // 菜单功能类型等于3表示在新窗口打开
		insIndex.locationHref(url);
	}
}
