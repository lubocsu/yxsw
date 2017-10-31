package com.upsoft.yxsw.controller.checkItem;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.upsoft.yxsw.service.BizTGgCheckItemfKxxSelService;

/**
* Copyright (c) 2017,重庆扬讯软件技术有限公司<br>
* All rights reserved.<br>
*
* 文件名称：BizTGgCheckItemfKxxSelController.java<br>
* 摘要：检查项预定义可选项从表内容<br>
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
@RequestMapping("/checkitemselkxx")
public class BizTGgCheckItemfKxxSelController  {
	@Autowired
	public BizTGgCheckItemfKxxSelService bizTGgCheckItemfKxxSelService;

	/**
	 * 根据检查项预定于主表ID获取对应从表信息
	 * @return
	 */
	@RequestMapping("/getCheckItemSelKxxByParentId")
	@ResponseBody
	public List<Map<String,Object>> getCheckItemSelKxxByParentId(@RequestParam(required = true)String parentId){
		return bizTGgCheckItemfKxxSelService.getCheckItemSelKxxByParentId(parentId);
	}

}
