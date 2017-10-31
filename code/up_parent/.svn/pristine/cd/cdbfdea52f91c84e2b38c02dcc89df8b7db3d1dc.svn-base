package com.upsoft.system.util;

import java.io.PrintWriter;
import java.io.StringWriter;

public class ExceptionFormatUtil {
	private final static int limit = 10000;
	public static String formatExceptionTrace(Exception ex){
		if(ex==null)
			return "";
		StringWriter sw = new StringWriter();
		PrintWriter pr = new PrintWriter(sw);
		ex.printStackTrace(pr);
		sw.flush();
		String traceContent = "";
		if(sw!=null)
		{
			if(sw.toString().length()>limit)
				traceContent= sw.toString().substring(0, limit);
			else
				traceContent = sw.toString();
		}
		return traceContent;
	}
	
	public static String formatThrowable(Throwable ex){
		if(ex==null)
			return "";
		StringWriter sw = new StringWriter();
		PrintWriter pr = new PrintWriter(sw);
		ex.printStackTrace(pr);
		sw.flush();
		String traceContent = "";
		if(sw!=null)
		{
			if(sw.toString().length()>limit)
				traceContent= sw.toString().substring(0, limit);
			else
				traceContent = sw.toString();
		}
		return traceContent;
	}
}
