<%@tag import="java.util.Date"%>
<%@tag import="java.text.SimpleDateFormat"%>
<%@ tag pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<%@ attribute name="longValue" required="false" type="java.lang.Long"%>
<%@ attribute name="stringValue" required="false" type="java.lang.String"%>
<%@ attribute name="inputPattern" required="false" type="java.lang.String"%>
<%@ attribute name="outputPattern" required="false" type="java.lang.String"%>

<%
	if(inputPattern == null){
		inputPattern="yyyy-MM-dd HH:mm:ss";
	}
	if(outputPattern == null){
		outputPattern="yyyy-MM-dd HH:mm:ss";
	}
	String dateString = "";
	// 同时设置了stringValue 和 longValue 处理 longValue
	if (longValue != null && !longValue.equals(0L)) {
		Date date = new Date(longValue);
		try {
			SimpleDateFormat formatter = new SimpleDateFormat(outputPattern);
			dateString = formatter.format(date);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}else if(stringValue != null && !"".equals(stringValue)){
		try {
			SimpleDateFormat formatter = new SimpleDateFormat(inputPattern);
			Date date = formatter.parse(stringValue);
			formatter.applyPattern(outputPattern);
			dateString = formatter.format(date);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	out.print(dateString);
%>
