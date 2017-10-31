package com.upsoft.systemweb.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.upsoft.system.bean.LogExpandBean;
import com.upsoft.system.bean.PageBean;
import com.upsoft.system.entity.BaseEntity;
import com.upsoft.system.service.BaseService;

/**
 * 
 * Copyright (c) 2015,重庆扬讯软件技术有限公司<br>
 * All rights reserved.<br>
 * 
 * 文件名称：LogService.java<br>
 * 摘要：日志服务接口<br>
 * -------------------------------------------------------<br>
 * 当前版本：1.1.1<br>
 * 作者：TW<br>
 * 完成日期：2015年2月3日<br>
 * -------------------------------------------------------<br>
 * 取代版本：1.1.0<br>
 * 原作者：TW<br>
 * 完成日期：2015年2月3日<br>
 */
public interface LogService extends BaseService {

	/**
	 * 数据变更日志
	 * 保存日志（只保存基本信息）【注】1、调用日志必须在业务数据事务提交前执行；2、entity对象必须和业务数据提交事务保存的数据一致（
	 * 即在调用日志和保存业务数据之间，不能有任何改动entity对象的操作，建议saveLog写在保存业务数据前一句）。
	 * 
	 * @date 2015年2月5日 下午7:24:37
	 * @author TW
	 * @param request
	 *            http请求
	 * @param entity
	 *            操作的实体对象
	 */
	public void saveLog(HttpServletRequest request, BaseEntity entity);

	/**
	 * 数据变更日志
	 * 保存日志（可传入扩展信息）【注】1、调用日志必须在业务数据事务提交前执行；2、entity对象必须和业务数据提交事务保存的数据一致（
	 * 即在调用日志和保存业务数据之间，不能有任何改动entity对象和expandBean对象的操作，建议saveLog写在保存业务数据前一句）。
	 * 
	 * @date 2015年2月5日 下午7:43:19
	 * @author TW
	 * @param request
	 *            http请求
	 * @param entity
	 *            操作的实体对象
	 * @param expanBean
	 *            扩展信息
	 */
	public void saveLog(HttpServletRequest request, BaseEntity entity,
			LogExpandBean expandBean);

	/**
	 * 查询日志分页数据列表及总条数
	 * 
	 * @date 2015年2月3日 下午4:37:55
	 * @author TW
	 * @param pars
	 *            查询过滤条件
	 * @param pageBean
	 *            分页参数
	 * @return
	 */
	public Map<String, Object> getLogListAndCount(Map<String, Object> pars,
			PageBean pageBean);
	
}
