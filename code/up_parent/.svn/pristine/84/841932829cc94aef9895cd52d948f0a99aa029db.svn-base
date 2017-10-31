package com.upsoft.yxsw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.upsoft.yxsw.service.BizTXjCxTaskService;

/**
 * 
 */
@Controller
@RequestMapping("/cxtask")
public class BizTXjCxTaskController  {
	private static final String JSP_PREFIX = "/WEB-INF/jsp/cxtask";
	
	@Autowired
	public BizTXjCxTaskService bizTXjCxTaskService;
	
	
	/**
	 *  初始化页面
	 * @param map
	 * @return
	 */
	@RequestMapping("/init")
	public String init(Model map){
		return JSP_PREFIX + "/main";
	}
	
}
