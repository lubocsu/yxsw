package com.upsoft.yxsw.controller.checkItem;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.upsoft.yxsw.service.BizTGgCheckItemfKxxService;

/**
* Copyright (c) 2017,重庆扬讯软件技术有限公司<br>
* All rights reserved.<br>
*
* 文件名称：BizTGgCheckItemfKxxController.java<br>
* 摘要：检查项预定于可选项<br>
* -------------------------------------------------------<br>
* 当前版本：1.1.1<br>
* 作者：胡毅<br>
* 完成日期：2017年9月10日<br>
* -------------------------------------------------------<br>
* 取代版本：1.1.0<br>
* 原作者：胡毅<br>
* 完成日期：2017年9月10日<br>
 */
@Controller
@RequestMapping("/checkitemfxx")
public class BizTGgCheckItemfKxxController  {
	@Autowired
	public BizTGgCheckItemfKxxService bizTGgCheckItemfKxxService;
	
	/**
	 * 获取检查项预定义的可选项内容主表信息
	 * @return
	 */
	@RequestMapping("/getCheckItemKXX")
	@ResponseBody
	public List<Map<String,Object>> getCheckItemKXX(){
		return bizTGgCheckItemfKxxService.getKxx();
	}
}
