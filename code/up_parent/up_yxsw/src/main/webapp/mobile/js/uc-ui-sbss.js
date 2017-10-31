/* type-block样式控制。用到此结构的有：“任务详情”页 二级导航*/
function typeBlockController(){
	//	初始化:展开第一个块
	$(".type-block > li").eq(0).addClass("expanded");
	
	$(".type-block > li").click(function(){
		$(this).addClass("expanded").siblings().removeClass("expanded");
	});
	$(".type-block.normal-menu > li").eq(0).removeClass("expanded");
	$(".type-block.normal-menu > li").unbind("click");
	$(".type-block.normal-menu > li").click(function(){
		$(this).toggleClass("expanded").siblings().removeClass("expanded");
	});
}

//取证点击添加效果实现
function addClick(){
	var num=0,
		id="incidentTime";
	$(".div-content").on("click",".scene-manage-advise .block-add",function(){
		num++;
		var sma = $(this).parents(".scene-manage-advise");
		var newBlock = $(sma).clone();
		newBlock.find(".block-text textarea").val("");
		
		
		//更换元素name 开始
		newBlock.find(".block-text textarea").each(function(){
			$(this).attr("name",$(this).attr("name").replace(/\d+/g,'') + num);
		});
		newBlock.find(".radio-box>label").each(function(){
			$(this).attr("name",$(this).attr("name").replace(/\d+/g,'') + num);
		});
		newBlock.find(".situation input").each(function(){
			$(this).attr("name",$(this).attr("name").replace(/\d+/g,'') + num);
		});
		//更换元素name 结束
		
		$(this).remove();
		newBlock.find("input").val("");
		newBlock.find(".radio-box>label").removeClass("selected");
		if(newBlock.find(".block-timewidget")){
			id="incidentTime"+num;
			newBlock.find(".block-timewidget").children("input").attr("id",id);
		}
		sma.after(newBlock);
		datePicker(id);
	});
}
function Tabchange(){
	//	初始化
	$(".tab .tab-left li").eq(0).addClass("selected");
	$(".tab-right .tab-content").addClass("hide").eq(0).removeClass("hide");

	$(".tab .tab-left li").click(function(){
		$(this).addClass("selected").siblings().removeClass("selected");
		$(".tab-right .tab-content").eq($(this).index()).removeClass("hide").siblings().addClass("hide");
	});
	
	$(".tab-right .tab-content li").click(function(){
		$(this).addClass("selected").siblings().removeClass("selected");
	});
}
/* icon-list的显示和隐藏*/
function iconListController(){
	//初始化
	$(".detail-body .icon-list").addClass("hidden");
	$(".detail-body .default-arrow").parent("span").click(function(){
		$(this).find("i").toggleClass("expend").parent().next().toggleClass("hidden");
		$(this).parent().siblings().find(".default-arrow").removeClass("expend").parent().next().addClass("hidden");
	});
}
//simple-tab选中
function simpleTabController(){
//	初始化：默认选中第一个
	$(".simple-tab .simple-tab-top > ul > li").eq(0).addClass("selected");
	$(".simple-tab .simple-tab-body > ul > li").addClass("hidden").eq(0).removeClass("hidden");
	
	$(".simple-tab .simple-tab-top > ul > li").on("click",function(){
		$(this).addClass("selected").siblings().removeClass("selected");
		$(".simple-tab .simple-tab-body > ul > li").eq($(this).index()).removeClass("hidden").siblings().addClass("hidden");
	});
}

//normal_tab选项卡
function normal_tab (){
	$(".normal-tab-body > div").hide();
	$(".problem-tab-body > div").eq(0).show();
	$(".problem-tab-top ul li").eq(0).addClass("selected");
	$(".task-mask").hide();
	$(".normal-tab-top ul li").on("click",function(){
		$(this).addClass("selected").siblings().removeClass("selected");
		$(".task-mask").show().css("z-index",11);
		$(".normal-tab-body > div").eq($(this).index()).show().siblings().hide();
	});
	//筛选条件
	$(".normal-tab-body .choose dl dd").on("click",function(){
		$(this).toggleClass("selected");
	});
	//重置
	$(".normal-tab-body .choose .reset").on("click",function(){
//		$(".search-conditions dl dd>span").removeClass("selected");
		$(".search-conditions").eq(0).find("dl dd>span").removeClass("selected");
		$(".backlog ul li").removeClass("selected");
		$(".normal-tab-top ul li").removeClass("selected");
		$(".task-mask").hide();
		$(".normal-tab-body > div").hide();
		downRefresh();
	});
	//完成
	$(".normal-tab-body .choose .submit").on("click",function(){
		$(".normal-tab-top ul li").removeClass("selected");
		$(".normal-tab-body .choose dl dd").removeClass("selected");
		$(".task-mask").hide();
		$(".normal-tab-body > div").hide();
		downRefresh();
	});
	//待办
	$(".normal-tab-body").on("click",".backlog ul li",function(){
		$(".normal-tab-top ul li").removeClass("selected");
		$(this).addClass("selected").siblings().removeClass("selected");
		var name = $(this).find(".left").text();
		var id = $(this).parents("div").attr("id");
		if(id=="backlog_one"){
			if(name=="全部"){
				$("#li_one>div").text("类型");
			}else{
				$("#li_one>div").text(name);
			}
		}
		$(".task-mask").hide();
		$(".normal-tab-body > div").hide();
		//加载刷新代码。。。
		downRefresh();
       	
        // 加载完毕需要重置
        $.pullToRefreshDone('.pull-to-refresh-content');
		
	});
	//点击空白
	$(".task-mask").on("click",function(){
		$(".normal-tab-top ul li").removeClass("selected");
		$(this).hide();
		$(".normal-tab-body > div").hide();
		$(".more").addClass("hide");
	});
}
//刷新数据
function downRefresh(){
	
	pageStatus("down");
	onRefresh(function(lastIndex,pageSize,isRefreshDone){
		if(!isRefreshDone){
			$.alert('刷新数据未成功');
		}
		if ( lastIndex < pageSize) {
    		$('.infinite-scroll-preloader').remove();
    		// 加载完毕，则注销无限加载事件，以防不必要的加载
    		$.detachInfiniteScroll($('.infinite-scroll'));
    	}
		$.pullToRefreshDone('.pull-to-refresh-content');
	},"down",null);
	$.init();
}
//sui刷新组件初始化
function initRefresh(){
	$(document).on('refresh', '.pull-to-refresh-content',function(e) {
		downRefresh();
        // 加载完毕需要重置
        $.pullToRefreshDone('.pull-to-refresh-content');
		
	});
		var loading = false;
	 $(document).on('infinite', '.infinite-scroll-bottom',function() {
          // 如果正在加载，则退出
          if (loading) return;
          // 设置flag
          loading = true;
          pageStatus("up");
          onRefresh(function(lastIndex,pageSize,isRefreshed){
        	if(!isRefreshed){
        		$.alert("刷新数据未成功");
        	}
        	if ( lastIndex < pageSize) {
        		// 加载完毕，则注销无限加载事件，以防不必要的加载
        		$.detachInfiniteScroll($('.infinite-scroll'));
        		// 删除加载提示符
        		$('.infinite-scroll-preloader').remove();
        		return;
        	}
        },"up",null);
          loading = false;
              //容器发生改变,如果是js滚动，需要刷新滚动
              $.refreshScroller();
      });
      	$.init();
}
function pageStatus(flag){
	if(flag == "up"){
		++pageNo;
	}else{
		pageNo = 1;
	}
}
//<header></header>右上角按钮点击时出现的内容。使用到的页面有：任务详情页面、任务列表页面（或许不再使用）、应急主页
function headRightBtnController(btn,showC){
	var mask = $('<div class="task-mask"></div>');
	$("#"+btn).click(function(){
		$(this).parents("header").after(mask);
		$("#"+showC).removeClass("hide");
		
		$(mask).click(function(){
			$("#"+showC).addClass("hide");
			$(this).remove();
		});
	});
	
	//如果showC是more结构,那么点击.more-list li,隐藏more结构和遮罩
	if($("#"+showC).hasClass("more")){
		$("#"+showC).find(".more-list li").on("click",function(){
			$("#"+showC).addClass("hide");
			$(mask).remove();
		});
	}
}

//时间线相关控制
function timeLineController(){
	$(".group-list").each(function(i,e){
		if($(e).children(".toggle-expand") && $(e).children(".time-line")){
			$(e).children(".toggle-expand").click(function(){
				$(this).toggleClass("fold").next(".time-line").toggle();
			});
		}
	});
}
//custom-checkbox 相关控制
function customCbController(){
	$(".custom-checkbox").on("click",function(){
		$(this).toggleClass("selected");
	});
}
//查看页面的图片列表
function imgListCheckController(){
	var btn = "";
	$(".img-list-check li").each(function(i,e){
		if(i >= 2){
			$(e).addClass("hidden");
			btn = "<button class='more-btn'>点击查看更多 >> </button>";
			$(this).parent().after(btn);
		}
	});
}
//network-list 相关控制
function netListController(){
//	初始化
	$(".network-list > li").eq(0).addClass("selected");
	
	$(".network-list > li").on("click",function(){
		$(this).addClass("selected").siblings().removeClass("selected");
	});
}

// bar头部设置
function headController(){
	$(".bar .title").each(function(i,e){
		if($(e).hasClass("align-left")){
			var operateW = $(e).next(".custom-operate").width();
			$(e).width($(".bar").width()-operateW-15);
			return true;
		}
		var leftW = $(e).prev(".custom-icon").width();
		var rightW = $(e).next(".custom-icon").width();
		var poo = leftW > rightW ? leftW :rightW;
		$(e).css({"width":$(".bar").width()-poo-20-poo-10+"px","left":poo+25,"right":poo});
		
		if($(e).hasClass("seach-condition")){
			$(e).css({"width":$(".bar").width()-leftW-20-rightW-10+"px","left":leftW+25,"right":rightW});
		}
	});
}
//bar 头部为搜索条件
function headSearchController(){
	$(".bar .seach-condition .seach-ipt").each(function(x,o){
		$(o).next(".clear").addClass("hidden");
		var ph = $(o).attr("placeholder");
		$(o).on('input', function() {
			if($(this).val()){
				$(this).next(".clear").removeClass("hidden");
				$(o).parents(".bar").find(".toggle-cc").html("搜索");
			}else{
				$(this).next(".clear").addClass("hidden");
				$(o).parents(".bar").find(".toggle-cc").html("取消");
			}
       	});
       
       	$(o).next(".clear").click(function(){
    		$(o).val("");
			$(o).next(".clear").addClass("hidden");
			$(o).parents(".bar").find(".toggle-cc").html("取消");
       	});
    });
}
//取证内容的图片的尺寸设置
function oeContentListImgSize(){
	var imgW = 0,imgH = 0,signW = 0,valueMin = "";
	$(".oe-content-list > li > .sign > img").each(function(i,e){
		imgW = $(e).width();
		imgH = $(e).height();
		signW = $(e).parent().height();
		valueMin = imgH < imgW ? "H" : "W"; 
		if(valueMin == "H"){
			$(e).height(signW);
			$(e).css("margin-left",-($(e).width()-$(e).height())/2);
		}else if(valueMin == "W"){
			$(e).width(signW);
			$(e).css("margin-top",-($(e).height()-$(e).width())/2);
		}
	});
}
//取证区域相关内容控制
function oeController(){
	$(".oe-content > .summary > .del").click(function(){
		$(this).addClass("hidden");
		$(this).parent().next().find("li .bs-info").addClass("right15rem");
		$(this).parent().next().find("li").append("<div class='checkbox'></div>");
		// 点击checkbox
		$(".oe-content-list li .checkbox").click(function(){
			$(this).toggleClass("checked");
			if ( $(".checked").length > 0 ) {
				//显示删除
				$("#del").text("删除("+$(".checked").length+")").addClass("warning").show();
				$("#submit").hide();
			}else{
				//显示提交
				$("#submit").text("提交").show();
				$("#del").hide();
			}
		});
	});
}
//点击消息中心右上角的删除图标
function onDeleteIcon(){
	$(".bar .custom-icon.del").click(function(){		
		if($(this).hasClass("delete")){
			$(".div-btn .all-choosed").text("全选");
		    $(this).removeClass("delete")
		           .text("取消  ");
		    //添加底部按钮
		    $(".content .news").removeClass("news-content")
		    				   .addClass("div-content");
		    $(".div-btn").removeClass("btn-disappear");
		    
		    //遍历添加复选框
		    $(".news-details .block-title").each(function(i,e){
		    	var checkBox = $("<span class='news-checkbox'></span>");
		    	$(e).prepend(checkBox); 
		    }) 
		    //消息中心复选框的点击事件
		    $(".group-list .news-block .news-checkbox").each(function(){
				$(this).on("click",function(){
					$(this).toggleClass("selected");
				    if($(".group-list .news-block .news-checkbox.selected").length==$(".group-list .news-block .news-checkbox").length){
				      $(".div-btn .all-choosed").text("取消全选");
				    }else{
				      $(".div-btn .all-choosed").text("全选");
				    }
			   })
			})
		    
		}else{
			$(this).addClass("delete")
			       .text(" ");
			//去掉底部按钮
			$(".content .news").removeClass("div-content")
		    				   .addClass("news-content");
		    $(".div-btn").addClass("btn-disappear");
			//遍历去除复选框
			$(".news-details .block-title").each(function(i,e){
				$(e).find(".news-checkbox").remove();
			})
		}	
	})
}
function allChoosed(){
	//点击消息中心底部的全选按钮
    $(".div-btn .all-choosed").click(function(){
    	if($(this).text()=="全选"){
    		 $(".group-list .news-block .news-checkbox").each(function(){
					$(this).addClass("selected");		   
			 })
    		$(this).text("取消全选");
    	}else{
    		$(this).text("全选");
    		$(".group-list .news-block .news-checkbox").each(function(){
					$(this).removeClass("selected");		   
			})
    	}
    });
}

//点击全部标为已读按钮
function onALLread(){
	$(".news .all-read").click(function(){
		$(".group-list .news-block .news-category").each(function(){
			$(this).removeClass("not-read");
		})
	})
}
//查询页表格列表转换
function cxChange(){
	$(".bar .custom-icon.list").click(function(){
		$(this).toggleClass("block");
		$(".cx-form").toggle();
		$(".cx-list").toggle();
	})
}
//radio-box单选
function radioBox(){
	$(".div-content").on("click",".radio-box>label",function(){
		$(this).addClass("selected").siblings().removeClass("selected")
	});
}
//checkbox-box复选
function checkboxBox(){
	$(".div-content").on("click",".checkbox-box>label",function(){
		$(this).toggleClass("selected")
	});
}
//datePicker初始化
function datePicker(id){
	$("#"+id).datetimePicker({
				onOpen:function(){
					$("body").append('<div style="width:100%;height:100%;position: absolute;z-index: 11499;background-color: #000000;opacity: 0.3;" class="dpMask" ></div>');
				},
				onClose:function(){
					$(".dpMask").remove();
				}
			});
}

//顶部搜索函数
function searchFunc(){
	$(".lscz-search").hide();
	$(".mask").hide();	
	$(".bar .search-ipt").addClass("hidden");
	$(".bar .cancel").addClass("hidden");
	$(".bar .cleartext").addClass("hidden");
	$(".bar .search").on("click",function(){
		$(".bar .search").addClass("hidden");
		$(".bar h1").addClass("hidden");
		$(".bar .search-ipt").removeClass("hidden");
		$(".bar .cancel").removeClass("hidden");
		$(".mask").show();
		$(".lscz-search").show();
	})
	$(".bar .cancel").on("click",function(){
		if($(this).text()=="取消"){
		$(".search-conditions").eq(1).find("dl dd>span").removeClass("selected");
		$(".bar .cancel").addClass("hidden");
		$(".bar .search-ipt").addClass("hidden");
		$(".bar .search").removeClass("hidden");
		$(".bar h1").removeClass("hidden");
		$(".bar .cleartext").addClass("hidden");
		$(".mask").hide();	
		$(".lscz-search").hide();
		}else if($(this).text()=="搜索"){
		$(".mask").hide();	
		$(".lscz-search").hide();
		//加载刷新出具。。。
		downRefresh();
//		$("#starttime1").val("");
//		$("#endtime1").val("");
		}
	})
	$(".bar .search-ipt").on("input",function(){
		if($(this).val()!=""){
			$(".bar .cleartext").removeClass("hidden");
			$(".bar .cancel").text("搜索");
		}else{
			$(".bar .cleartext").addClass("hidden");	
			$(".bar .cancel").text("取消");
		}
		$(".lscz-search").show();
	})
	
	$("#starttime1").on("click",function(){
			$(".bar .cleartext").removeClass("hidden");
			$(".bar .cancel").text("搜索");
	})
	
	$("#endtime1").on("click",function(){
			$(".bar .cleartext").removeClass("hidden");
			$(".bar .cancel").text("搜索");
	}) 
	
	$(".bar .search-ipt").on("click",function(){
		$(".lscz-search").show();
	})
	$(".bar .cleartext").on("click",function(){
		$(".bar .search-ipt").val("");
		$(".bar .cancel").text("取消");
		$(".search-conditions").eq(0).find("dl dd>span").removeClass("selected");
		$(".backlog ul li").removeClass("selected");
		$(".normal-tab-top ul li").removeClass("selected");
		$("#starttime1").val("");
		$("#endtime1").val("");
		$("#li_one>div").text("处理状态");
		$("#li_two>div").text("操作类型");
//		$("#picker").val("");
//		$("#userid").val("");
		downRefresh();
	})
}

//search-conditions
function searchCondition(){
	$(".search-conditions dl dd>span").on("click",function(){
		$(this).toggleClass("selected").siblings().removeClass("selected");
		var timeSlot = $(".search-conditions").find("span.selected").attr("timeSlot");
		var time = new Date();
		var smonthfull = (time.getMonth()+1)<10?'0' + (time.getMonth()+1) : (time.getMonth()+1);
		var sdayfull = time.getDate()<10?'0' + time.getDate() : time.getDate();
		var starttime = time.getFullYear() + "-" + smonthfull + "-" + sdayfull;
		var endtime = "";
		if(timeSlot == "today"){
			endtime = starttime;
		}else if(timeSlot == "week"){
			time = new Date(time.getTime() - 7 * 24 * 3600 * 1000);
		}else if(timeSlot == "month"){
			time.setMonth((time.getMonth()-1));
		}else if(timeSlot == "tmonth"){
			time.setMonth((time.getMonth()-3));
		}else if(timeSlot == "hyear"){
			time.setMonth((time.getMonth()-6));
		}
		var monthfull = (time.getMonth()+1)<10?'0' + (time.getMonth()+1) : (time.getMonth()+1);
		var dayfull = time.getDate()<10?'0' + time.getDate() : time.getDate();
		endtime = time.getFullYear() + "-" + monthfull + "-" + dayfull;
		var searchLocation = $(this).parent().find(".starttime input").attr("id");
		if(searchLocation == "starttime1"){
			$("#starttime1").val(endtime);
			$("#endtime1").val(starttime);
		}
		$(".bar .cancel").text("搜索");
		$(".bar .cleartext").removeClass("hidden");
	})
}
 $(function(){
 	searchCondition();
 	searchFunc();
 	radioBox();
 	checkboxBox();
	typeBlockController();/* type-block样式控制。用到此结构的有：“任务详情”页 二级导航*/
	addClick();
	Tabchange();
	iconListController();
	simpleTabController();
	normal_tab ();
	initRefresh();//刷新组件初始化
	timeLineController();//时间线相关控制
	customCbController();//custom-checkbox 相关控制
	imgListCheckController();//查看页面的图片列表
	netListController();//network-list 相关控制
	headController();// bar头部设置
	headSearchController();//bar 头部为搜索条件
	oeContentListImgSize();//取证内容的图片的尺寸设置
	oeController();//取证区域相关内容控制
	onDeleteIcon();
	onALLread();
	allChoosed();
	cxChange();//查询页表格列表转换
});

window.onresize=function(){
	headController();// bar头部设置
};