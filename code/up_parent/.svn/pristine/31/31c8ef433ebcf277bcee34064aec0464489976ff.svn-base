// JavaScript Document
/*左边二级导航*/
function menu_left(){
	jQuery(".menu-max li ul").addClass("hide");
	jQuery(".menu-max li:first ul").removeClass("hide");
		jQuery(".menu-max li").click(function(){
			$(this).children("ul").removeClass("hide");
			$(this).addClass("selected");
	
			$(this).siblings().find("ul").addClass("hide");
			$(this).siblings().removeClass("selected").find("li").removeClass("selected");
		});	

}

/* 主体框架计算 */
function index_frame(){
	/* 获取屏幕可用高度 */
	var height0 = document.documentElement.clientHeight;
	/* 循环计算所有兄弟节点高度 */
	var height1 = 0;
	jQuery(".content").siblings().each(function(i,v){
		if(jQuery(this).css("display") !== "none"){
			height1 += jQuery(this).outerHeight(); 
		}	
	});
	var height2 = height0 - height1;
	jQuery(".content").height(height2);
}

/*计算收折是内容区的宽度*/
function index_container(){
	var width0 =  jQuery(".content").outerWidth();
	var width1 = 0;
	jQuery(".index-container").siblings().each(function(i,v){
		if(jQuery(this).css("display") !== "none"){
			width1 += jQuery(this).outerWidth(); 
		}	
	});
	jQuery(".index-container").width(width0-width1);
}

/* 主体click点击事件 */
function index_click(){
	var cick = jQuery(".index-click .click-images");
	var cick_css =  jQuery(".index-click").css("left");
	var left = jQuery(".menu-left");	
	var left_css = left.css("left");
	var right = jQuery(".index-container");
	var right_css = right.css("left");
	cick.click(function(){
		if(left.hasClass("hide")){
			left.removeClass("hide");
			cick.removeClass("click-images2");
			jQuery(".index-click").css("left",cick_css);
			right.css("left",right_css);
//			index_container();    // 内容框架改成绝对定位了，只需要设置left值即可改变宽度
		}else{
			left.addClass("hide");
			cick.addClass("click-images2");
			jQuery(".index-click").css("left",0);
			right.css("left",(cick.width())+'px');
//			index_container();   // 内容框架改成绝对定位了，只需要设置left值即可改变宽度
		}	
	});
}

// .form-table各控件对齐
function stControlAlign(){
	var inputW = $(".form-table .input-time-split").parent().width()*0.8;
	var splitTimeSpacingW = $(".form-table .time-split-spacing").outerWidth();
	$(".form-table .input-time-split").width((inputW-splitTimeSpacingW-2)/2);// 设置一个单元格中有两个输入框的input宽度
	var smallBtnW = $(".form-table .small-btn").outerWidth();
	$(".form-table .input-operable").width(inputW-smallBtnW*2);// 设置具有“选”“清”按钮的输入框的宽度
	var thW = $(".form-table th").width();
	var tdW = $(".form-table td").width();
	$(".form-table .input-colspan3").width((tdW+5)+thW+tdW*0.8);// 设置一个夸3列的输入框
}


/* 窗口大小改变事件 */
window.onresize=function(){
	index_frame();
}

$(function(){
	menu_left();   // 左侧菜单点击收折
	index_frame(); // 主体框架
	index_click(); // 点击事件
    stControlAlign();
//    index_container();
});


