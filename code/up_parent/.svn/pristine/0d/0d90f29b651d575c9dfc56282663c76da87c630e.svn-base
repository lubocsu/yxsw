<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath}"></c:set>
<!-- 电子相册 -->
<!doctype html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>电子相册</title>
	<link rel="stylesheet" type="text/css" href="${path }/css/album.css">
	<style type="text/css">
		.close-div div {
			color: gray;
			height: 40px;
			line-height: 40px;
			width: 50%;
			text-align: right;
			font-weight: bold;
			font-size: 14px;
			float: left;
		}
	</style>
</head>
<body>
	<!-- 电子相册 -->
	<div class="album-box hidden">
		<div class="close-div">
				<a href="javascript:;" onclick="closeAlbumBox()">×</a>						
		</div>
		<div class="mod18">
			<span id="prev" class="btn prev"></span>
			<span id="next" class="btn next"></span>
			<div id="picBox" class="picBox">	
				<div class="pic-c" style="height:inherit;margin-left:75px;margin-right:76px;">				
					<ul class="cf">
						<c:forEach items="${imgs  }" var="item">
							<li> <a href="#"><img src="${ImageServerURL }/${item.attachmentPath}" alt=""></a> </li>
						</c:forEach>
					</ul>
				</div>
			</div>
			<div id="listBox" class="listBox">				
				<ul class="cf">
					<c:forEach items="${imgs  }" var="item">
						<li><img src="${ImageServerURL }/${item.attachmentPath}" alt=""></li>
					</c:forEach>
				</ul>				
			</div>
			<div class="clear"></div>
		</div>
	</div>

	<script type="text/javascript" src="${path }/js/jquery-1.7.2.min.js"></script>
	<script type="text/javascript" src="${path }/js/jqueryPhoto.js"></script>
	<script type="text/javascript">
		jQuery(function(){
			showAlbum();
		});
		window.onresize = function(){
			initAlbum();
		}
		function showAlbum(){
			$("body").addClass("maskStyle");
			//显示电子相册
			$(".album-bg").removeClass('hidden');
			$(".album-box").removeClass('hidden');	
			initAlbum();
		}
		function initAlbum(){
			// 计算电子相册外层宽高
			var albumBoxH = $(".album-box").height();
			var albumboxW = $(".album-box").width();
			var mod18H = albumBoxH-60;
			$(".mod18").height(mod18H);
			$(".mod18").width(albumboxW);
			// 计算picBox高度
			var listBoxH = $(".listBox").outerHeight(true);
			var closeDivH = $(".close-div").outerHeight(true);
			$(".picBox").height(mod18H-listBoxH-closeDivH)
			// 计算li宽度
			$(".pic-c ul li").width($(".pic-c").width());	

			$(".pic-c li").each(function(){
				$(this).find("img").css("max-width",$(this).find("a").width());
				$(this).find("img").css("max-height",$(this).find("a").height());
				// 计算span的宽度
				$(this).find("span").width($(this).find("img").width());
				//控制img位置
				$(this).find("img").css("margin-left",($(this).find("a").width()-$(this).find("img").width())/2);
				//控制文字说明位置
				$(this).find("span").css("margin-left",($(this).find("a").width()-$(this).find("span").width())/2);

			});
			// listBox li 的宽度
			var listBoxLiH = $(".listBox li").width()
			// li的总宽度设置为listBox的宽度
			var listBoxW  = $(".listBox li").length*listBoxLiH;
			// listBox最大宽度：获得能够整数存放li的listBox宽度
			var listBoxMaxW = Math.floor($(".pic-c li a").width()*0.7/listBoxLiH)*listBoxLiH;
			if(listBoxW > listBoxMaxW){
				listBoxW = listBoxMaxW;
			}
			// 设置listBox宽度
			$(".listBox").width(listBoxW);
			// 控制上下翻图按钮位置
			var l = ($(".mod18").width() - $(".mod18 .listBox").width()-30)/2;
			$(".mod18 #prev").css("left",l);
			$(".mod18 #next").css("right",l);

			//执行动画
			albumAnimate();
		}
		function closeAlbumBox(){
			$("body").removeClass("maskStyle");
			$(".album-bg").addClass('hidden');
			$(".album-box").addClass('hidden');	
			parent.hidePic();
		}
	</script>
</body>
</html>