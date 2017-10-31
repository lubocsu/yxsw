// 计算index页面主体部分content高度
function contentH(){
	// var screenH = $(".index-max").height();
	var screenH = document.documentElement.clientHeight; //获得屏幕可用高度
	var heightSL = 0;//.content的同辈元素的和
	$(".content").siblings().each(function(index,domO){
		if ($(this).css("display") !== "none") {
			heightSL += $(this).outerHeight(true);
		};		
	});
	$(".content").height(screenH - heightSL);
}

// 使得 .content > .main内容上下居中
function centerMain(){
	var contentH = $(".content").height();//获得.content高度
	var mainH = $(".content > .main").height();//获得.main高度
	$(".content > .main").css("padding-top",(contentH-mainH)/2+"px");
	
	var mainW = $(".content > .main").width();//获得.main高度
	var subsystem = $(".content > .main > ._subsystem");
	if (null != subsystem&&"undefined"!=subsystem&&undefined!=subsystem&&subsystem.length!=0) {
		var subsystemW = ($(".content > .main > ._subsystem").width())
				+ parseInt($(".content > .main > ._subsystem")
						.css('marginLeft').replace("px", ""));
		var childNum = $(".content > .main > ._subsystem").length;
		var pl = (100-30*childNum)/2;
		//$(".content > .main").css("padding-left",
		//		(mainW - subsystemW * childNum) / 2 + "px");
		$(".content > .main").css("padding-left",pl+"%");
	}
}

// 控制左侧菜单收缩
function sidebarToggle(){
	$(".sidebar-box .level-1st > a").click(function(){
		var lst = $(this).parent();//获得li.level-1st
		var flag = $(lst).find("ul").css("display");
		if (flag == "none") {
			$(lst).find("ul").removeClass("hide");
			// $(lst).find(".pp").html("∧");				
			$(lst).siblings().find("ul").addClass("hide");
			// $(lst).siblings().find(".pp").html("∨");
		}else{
			$(lst).find("ul").addClass("hide");
			// $(lst).find(".pp").html("∨");
		}	
	});
}

//加载即执行
$(function(){
	contentH();
	centerMain();
	sidebarToggle();
});

//窗口大小改变事件
window.onresize = function(){
	contentH();
	centerMain();
	sidebarToggle();
}