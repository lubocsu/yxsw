package com.upsoft.system.util;

import java.util.List;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

import com.google.gson.Gson;
import com.upsoft.system.bean.PageBean;

/**
* Copyright (c) 2015,重庆扬讯软件技术有限公司<br>
* All rights reserved.<br>
*
* 文件名称：PageUtil.java<br>
* 摘要：分页操作类<br>
* -------------------------------------------------------<br>
* 当前版本：1.1.1<br>
* 作者：Awesan<br>
* 完成日期：2015年1月23日<br>
* -------------------------------------------------------<br>
* 取代版本：1.1.0<br>
* 原作者：Awesan<br>
* 完成日期：2015年1月23日<br>
*/
public class PageUtil {

	/**
	 * 获取页码
	 * 
	 * @param request
	 * @return
	 */
	public static int getPageNo(HttpServletRequest request) {
		int pageNo = 1;
		String page_str = request.getParameter("pager.pageNo");
		if (StringUtils.isNotBlank(page_str)
				&& Pattern.matches("\\d*", page_str)) {
			pageNo = Integer.parseInt(page_str);
		}
		return pageNo;
	}

	/**
	 * 获取分页大小，如果配有字典可以设置为默认值
	 * 
	 * @param request
	 * @param defaultValue
	 * @return
	 */
	public static int getPageSize(HttpServletRequest request, int defaultValue) {
		int pageSize = defaultValue;
		String selectPageSize = request.getParameter("pager.pageSize");
		if (StringUtils.isNotBlank(selectPageSize)
				&& Pattern.matches("\\d*", selectPageSize)) {
			pageSize = Integer.parseInt(selectPageSize);
		}
		return pageSize;
	}
	
	/**
	 * @date 2015年1月23日 上午8:49:12
	 * @author Awesan
	 * @param pageNo
	 * @param total
	 * @param datas
	 * @return 
	 */
	public static <T> String getPageReturn(int pageNo, long total, List<T> datas){
		//拼接json
		StringBuffer result = new StringBuffer();
		result.append("{\"pager.pageNo\":");
		result.append(pageNo);
		result.append(",\"pager.totalRows\":");
		result.append(total);
		result.append(",\"rows\":");
		result.append(new Gson().toJson(datas));
		result.append("}");
		return result.toString();
	}

	public static String getSortName(HttpServletRequest request){
		return request.getParameter(PageBean.SORT_NAME);
	}
	
	public static String getSortOrder(HttpServletRequest request){
		return request.getParameter(PageBean.SORT_ORDER);
	}
}
