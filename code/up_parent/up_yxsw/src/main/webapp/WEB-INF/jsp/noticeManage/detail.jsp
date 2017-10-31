<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt"%>
<c:set var="path" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>公告详情</title>
<script type="text/javascript" src="${path }/js/jquery-1.7.2.min.js"></script>
<link href="${path }/css/core.css" rel="stylesheet" type="text/css"/>
<!-- 富文本编辑器 -->
<script type="text/javascript" charset="utf-8" src="${path }/ueditor/ueditor.config.js"></script>
<script type="text/javascript" charset="utf-8" src="${path }/ueditor/ueditor.all.js"> </script>
<script type="text/javascript" charset="utf-8" src="${path }/ueditor/lang/zh-cn/zh-cn.js"></script>
<!-- 异步上传控件start -->
<script type="text/javascript" src="${path }/libs/js/form/upload/fileUpload.js"></script>
<script type="text/javascript" src="${path }/libs/js/form/upload/handlers.js"></script>
<script type="text/javascript" src="${path }/js/plug-in/customupload.js"></script>
<!-- 异步上传控件end -->
<%-- <script type="text/javascript" src="${path }/js/pis-common.js"></script> --%>
<script type="text/javascript" src="${path }/js/dateutil.js"></script>
<script type="text/javascript">
	jQuery(function(){
		
		window.setTimeout(setContent,1000);//一秒后再调用赋值方法
		
		jQuery(".time").each(function(){
			$(this).text(dateStr2Str($(this).text(),"$1-$2-$3 $4:$5"));
		});
		
		jQuery(".to_hide_over_content").on("click",function(){
			var tipicon = jQuery.trim(jQuery(this).find("span:eq(2)").text());
			if(tipicon=="∨"){
				
				jQuery(this).find("span:eq(0)").text("展开");
				jQuery(this).find("span:eq(2)").text("∧");
				jQuery(this).next(".detail-list").hide();
			}else{
				jQuery(this).find("span:eq(0)").text("隐藏");
				jQuery(this).find("span:eq(2)").text("∨");
				jQuery(this).next(".detail-list").show();
			}
		});
	});
	
	
</script>
<style type="text/css">
	.task-audit-material table tr td{
		border: 1px solid #99ccff;
		text-align: center;
	}
	.showPic{width: 100%;height: 100%;display: none; position: relative;}
	#_left,#_right{width: 50%;height: 100%; float:left;}
	#showPic_1_2 iframe,#showPic_3 iframe {width: 100%;height: 100%;}
</style>
</head>
<body>
	<div class="details-max">
		<div class="list-content">
	    	 <div class="details-container">
	    	 	<!-- 基本信息 start -->
	         	<div class="task-detail">
	            	 <span class="detail-title">公告信息</span>
	                 <div class="to_hide_over_content" class="text">
	          			<p><span>隐藏</span><span>&nbsp;</span><span>∨</span></p>
	        		 </div> 
	                 <table class="detail-list">
	                 	<tr>
	                 		<th>标题：</th>
	                 		<td>${result.title }</td>
	                 		<th>重要程度：</th>
	                        <td>${result.importantLevel }</td>
	                 	</tr>
	                    <tr>
	                        <th>公告类型：</th>
	                    	<td>${result.ggType }</td>
	                    	<th>有效期：</th>
	                    	<td>
	                    		<fmt:parseDate value="${result.limitDate}"  var="limittime" pattern="yyyyMMddHHmm"/>
								<fmt:formatDate value="${limittime }" pattern="yyyy-MM-dd HH:mm"/>
	                    	</td>
	                    </tr>
	                    <tr>
	                    	<th>是否有效：</th>
	                    	<td>
	                    		<c:if test="${result.isAlive eq '0'}">否</c:if>
		                        <c:if test="${result.isAlive eq '1'}">是</c:if>
	                    	</td>
	                        <th >发布时间：</th>
	                        <td>
	                        	<fmt:parseDate value="${result.createTimestemp }"  var="createtime" pattern="yyyyMMddHHmmss"/>
								<fmt:formatDate value="${createtime }" pattern="yyyy-MM-dd HH:mm:ss"/>
	                        </td>
	                    </tr>
	                    <tr>
	                    	<th>创建人：</th>
	                    	<td>${result.creatorName}</td>
	                    	<th>所属单位：</th>
	                    	<td>${result.belongWscName}</td>
	                    </tr>
	                    <c:if test="${!empty attachmentList }">
		                    <tr>
		                    	<th>附件：</th>
		                    	<td colspan="3">
		                    		<div class="imglist">
		                    		<c:forEach items="${attachmentList }" var="item">
		                    			<a href="${ServerURL}${item.attachmentPath}"  download="${item.oldAttachmentName}.${item.attachmentSuffix}" >${item.oldAttachmentName }.${item.attachmentSuffix}</a>
		                    			<br />
		                    		</c:forEach>
		                    		</div>
		                    	</td>
		                    </tr>
	                    </c:if>
	                    <tr>
	                    	<code id="testcon" style="display:none;">${result.content}</code>
	                    	<th>内容：</th>
	                    	<td colspan="3"><div><script id="editor"  type="text/plain" style="width:85%;height:500px;"></script></div></td>
	                    </tr>
	                 </table>
	            </div>
	            <!-- 基本信息 end -->
	         </div>
         </div>
    </div>
    <!-- 底部按钮 -->
	<div class="list-btm">
	  	<div class="list-btm-div">
	        <div class="div-btn-sbt">
	             <button class="btm-button" type="button"  onclick="javascript:location.href='${backURL}'"><span class="back-icon">返回</span></button>
	        </div>
	    </div>
	</div>
	<script type="text/javascript">
     	//实例化编辑器
    	 var ue = UE.getEditor('editor');
     	//写入内容
     	function setContent() {
     	    UE.getEditor('editor').execCommand('insertHtml', $('#testcon').html());
     		setDisabled();
     	}
     	function setDisabled() {
            UE.getEditor('editor').setDisabled('fullscreen');
        }
    </script>
</body>
</html>