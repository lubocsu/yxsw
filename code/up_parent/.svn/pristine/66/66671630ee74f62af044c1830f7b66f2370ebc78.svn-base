package com.upsoft.system.util;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

/**
* Copyright (c) 2015,重庆扬讯软件技术有限公司<br>
* All rights reserved.<br>
*
* 文件名称：TreeUtil.java<br>
* 摘要：map集合操作类<br>
* -------------------------------------------------------<br>
* 当前版本：1.1.1<br>
* 作者：null<br>
* 完成日期：2015年5月14日<br>
* -------------------------------------------------------<br>
* 取代版本：1.1.0<br>
* 原作者：null<br>
* 完成日期：2015年5月14日<br>
*/
public class MapUtil {
	
	/**
	 * 对不符合QUI ztree数据规范的map对象重命名
	 * QUI 单选不用转换，可以使用 labelField valueField 指定
	 * 树型和多选则需要id parentId name 格式的数据
	 * @date 2015年5月14日 上午11:46:35
	 * @author null
	 * @param datas
	 * @param id:id字段名
	 * @param parentId:parentId字段名 没有parentId则传null
	 * @param name:name字段名
	 * @return 转换后的map集合
	 */
	public static List<Map<String, Object>> convertTreeMap(List<Map<String, Object>> datas, String id, String parentId, String name){
		if(datas == null || datas.size() == 0) return datas;
		for(Map<String, Object> data : datas){
			if(id != null && "".equals(id) && !"id".equals(id)){
				data.put("id", data.get(id));
				data.remove(id);
			}
			if(name != null && "".equals(name) && !"name".equals(name)){
				data.put("name", data.get(name));
				data.remove(name);
			}
			if(parentId != null){
				if("".equals(parentId) && !"parentId".equals(parentId)){
					data.put("parentId", data.get(parentId));
					data.remove(parentId);
				}
			}else{
				data.put("parentId", null);
			}
		}
		return datas;
	}
	
	/**
	 * 判断map集合中是否包含指定参数(value不为空)
	 * 如 Map<String, Object> conditon = new HashMap<String, Object>();
	 *	conditon.put("id", "123");
	 *	conditon.put("name", "test");
	 *	conditon.put("sex", "male");
	 *	boolean has = MapUtil.hasParam(conditon, "id");
	 * @date 2015年7月20日 下午6:02:06
	 * @author null
	 * @param map 集合
	 * @param key 键值
	 * @return 返回map集合中是否包含指定参数
	 */
	public static boolean hasParam(Map<String, Object> condition, String key){
		if(condition != null 
				&& condition.containsKey(key)
				&& condition.get(key) != null
				&& StringUtils.isNotBlank(condition.get(key).toString())){
			return true;
		}else{
			return false;
		}
	}
	
}
