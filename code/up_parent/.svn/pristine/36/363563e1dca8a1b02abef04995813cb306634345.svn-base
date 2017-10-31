package com.upsoft.yxsw.service;

import java.util.List;
import java.util.Map;

import com.upsoft.system.service.BaseService;


/**
* Copyright (c) 2017,重庆扬讯软件技术有限公司<br>
* All rights reserved.<br>
*
* 文件名称：BizTGgCheckItemfKxxSelService.java<br>
* 摘要：可选已定义从表service<br>
* -------------------------------------------------------<br>
* 当前版本：1.1.1<br>
* 作者：胡毅<br>
* 完成日期：2017年9月10日<br>
* -------------------------------------------------------<br>
* 取代版本：1.1.0<br>
* 原作者：胡毅<br>
* 完成日期：2017年9月10日<br>
 */
public interface BizTGgCheckItemfKxxSelService  extends BaseService {
	
	
	/**
	 * 获取检查项预定于可选项主表内容和从表内容，树形结构
	 * 字段名称：主表 itemf_kxx_id->id;kxx_value -> name
	 * 		  从表id->id;sel_value->code;itemf_kxx_id->parentid;sel_name-> name
	 * @date 2017年9月10日 上午10:55:23
	 * @author 胡毅
	 * @return
	 */
	public List<Map<String,Object>> getKxxAndKxxSEL();
	
	/**
	 * 
	 * @date 2017年9月10日 下午1:47:52
	 * @author 胡毅
	 * @param parentId
	 * @return
	 */
	public List<Map<String,Object>> getCheckItemSelKxxByParentId(String parentId);
	
}
