/*
 * PageBean.java
 * Created on 2015年1月23日 下午4:49:47
 * Copyright (c) 重庆扬讯软件技术有限公司  All Rights Reserved.
 * http://www.upsoft.com.cn
 *
 * This software is the confidential and proprietary information of UPSoft.
 * You shall not disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into with UPSoft.
 */
package com.upsoft.system.bean;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

import com.upsoft.system.util.PageUtil;


/**
 * Copyright (c) 2015,重庆扬讯软件技术有限公司<br>
 * All rights reserved.<br>
 *
 * 文件名称：PageBean.java<br>
 * 摘要：简要描述本文件的内容<br>
 * -------------------------------------------------------<br>
 * 当前版本：1.1.1<br>
 * 作者：蒋迪<br>
 * 完成日期：2015年1月23日<br>
 * -------------------------------------------------------<br>
 * 取代版本：1.1.0<br>
 * 原作者：蒋迪<br>
 * 完成日期：2015年1月23日<br>
 * @see 和  com.upsoft.system.bean.PageBean一样二选一即可
 */
public class PageBean {

	//当前页数，前端界面request参数命名
	public static final String PAGE_INDEX = "pager.pageNo";
	//每页记录数，前端界面request参数命名
	public static final String PAGE_SIZE = "pager.pageSize";
	//总记录数，前端界面request参数命名
	public static final String TOTAL = "pager.totalRows";
	//排序列名称，前端界面request参数命名
	public static final String SORT_NAME = "sort";
	//排序顺序，默认desc，前端界面request参数命名
	public static final String SORT_ORDER = "direction";
	//默认排序规则
	private static final String DEFAULT_SORT_DIRECTION = "desc";
	//当前页
	private int pageIndex;
	//每页记录条数
	private int pageSize;
	//总页数
	private Long total;
	//排序列
	private String sortName;
	//排序规则
	private String sortOrder;
	
	public PageBean(HttpServletRequest request){
		//TODO JiangDi 从request中取出分页参数，如果为空用数据字典默认值代替pageSize
		pageIndex = PageUtil.getPageNo(request);
		pageSize = PageUtil.getPageSize(request, 1);
		total = 0l;
		sortName = PageUtil.getSortName(request);
		sortOrder = PageUtil.getSortOrder(request);
		if (StringUtils.isEmpty(sortOrder)){
			sortOrder = DEFAULT_SORT_DIRECTION;
		}
	}
	
	
	@Deprecated
	public PageBean(int pageIndex, int pageSize, long total){
		//TODO JiangDi 测试用构造函数，需删除
		this.pageIndex = pageIndex;
		this.pageSize = pageSize;
		this.total = total;
	}
	
	@Deprecated
	public PageBean(int pageIndex, int pageSize, long total, String sortName, String sortOrder){
		//TODO JiangDi 测试用构造函数，需删除
		this.pageIndex = pageIndex;
		this.pageSize = pageSize;
		this.total = total;
		this.sortName = sortName;
		this.sortOrder = sortOrder;
		if (StringUtils.isEmpty(this.sortOrder)){
			this.sortOrder = DEFAULT_SORT_DIRECTION;
		}
	}
	
	/**
	 * 根据当前页，每页记录条数，返回起始查询index
	 * @date 2015年1月26日 上午10:48:28
	 * @author 蒋迪
	 * @return 
	 */
	public int getStartIndex(){
		return (pageIndex-1)*pageSize;
	}
	
	public int getPageIndex() {
		return pageIndex;
	}
	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public Long getTotal() {
		return total;
	}
	public void setTotal(Long total) {
		this.total = total;
	}
	public String getSortName() {
		return sortName;
	}
	public void setSortName(String sortName) {
		this.sortName = sortName;
	}
	public String getSortOrder() {
		return sortOrder;
	}
	public void setSortOrder(String sortOrder) {
		this.sortOrder = sortOrder;
	}
}
