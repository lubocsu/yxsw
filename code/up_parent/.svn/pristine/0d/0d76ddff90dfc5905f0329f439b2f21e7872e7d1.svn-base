package com.upsoft.systemweb.service;

import java.util.List;
import java.util.Map;

import com.upsoft.system.bean.PageBean;
import com.upsoft.system.service.BaseService;

/**
* Copyright (c) 2015,重庆扬讯软件技术有限公司<br>
* All rights reserved.<br>
*
* 文件名称：DictTreeService.java<br>
* 摘要：数据字典树服务层<br>
* -------------------------------------------------------<br>
* 当前版本：1.1.1<br>
* 作者：Awesan<br>
* 完成日期：2015年1月21日<br>
* -------------------------------------------------------<br>
* 取代版本：1.1.0<br>
* 原作者：Awesan<br>
* 完成日期：2015年1月21日<br>
*/
public interface DictTreeService extends BaseService{
	
	/**
	 * 查询数据字典树
	 * @date 2015年1月22日 下午3:51:00
	 * @author Awesan
	 * @param pageNo
	 * @param pageSize
	 * @param pars
	 * @return 
	 */
	public List<Map<String, Object>> queryDictTreeByPage(int pageNo, int pageSize, Map<String, Object> pars);
	
	/**
	 * 查找
	 * @date 2015年1月29日 上午9:10:25
	 * @author Awesan
	 * @param bean
	 * @param pars
	 * @return 
	 */
	public Map<String, Object> queryDictByPagination(PageBean bean, Map<String,Object> pars);
	
	/**
	 * @date 2015年1月29日 上午9:10:46
	 * @author Awesan
	 * @param bean
	 * @param pars
	 * @return 
	 */
	public Map<String, Object> queryDataParentByPagination(PageBean bean, Map<String,Object> pars);
	
	/**
	 * 返回当前最大排序号
	 * @date 2015年1月28日 下午1:55:50
	 * @author Awesan
	 * @return 
	 */
	public Long queryMaxOrderNo();
	
	
	/**
	 * 返回指定Id的字典在第几页
	 * @date 2015年2月2日 上午11:44:45
	 * @author Awesan
	 * @param id
	 * @param groupid 2015.03.25 hy
	 * @param pageSize
	 * @return 
	 */
	public Long getPageNoById(String id, String groupid, int pageSize);
}
