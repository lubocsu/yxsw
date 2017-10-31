package com.upsoft.system.service;

import java.io.Serializable;
import java.util.List;

import com.upsoft.system.entity.BaseEntity;


/**
* Copyright (c) 2015,重庆扬讯软件技术有限公司<br>
* All rights reserved.<br>
*
* 文件名称：IBaseService.java<br>
* 摘要：简要描述本文件的内容<br>
* -------------------------------------------------------<br>
* 当前版本：1.1.1<br>
* 作者：蒋迪<br>
* 完成日期：2015年1月23日<br>
* -------------------------------------------------------<br>
* 取代版本：1.1.0<br>
* 原作者：蒋迪<br>
* 完成日期：2015年1月23日<br>
*/
public interface BaseService {
	
	/**
	 * 保存实体对象
	 * @date 2015年1月23日 下午1:36:35
	 * @author 蒋迪
	 * @param t	
	 * @return 	返回保存后的实体对象
	 */
	public <T extends BaseEntity> T save(T t);
	
	/**
	 * 根据实体类型，主键查询实体对象
	 * @date 2015年1月23日 下午1:36:37
	 * @author 蒋迪
	 * @param classT		实体类型
	 * @param key			主键值
	 * @return 	返回实体对象，未找到对象返回null
	 */
	public <T extends BaseEntity> T findOne(Class<T> classT, Serializable key);
	
	/**
	 * 根据实体类型，主键删除实体
	 * @date 2015年1月23日 下午1:36:40
	 * @author 蒋迪
	 * @param classT		实体类型
	 * @param key			主键值
	 * @return 	成功返回true，失败返回false
	 */
	public <T extends BaseEntity> Boolean delete(Class<T> classT, Serializable key);
	
	/**
	 * 根据实体类型，批量删除实体
	 * @date 2015年5月15日 下午2:28:40
	 * @author 蒋迪
	 * @param classT
	 * @param keys
	 * @return
	 */
	public <T extends BaseEntity>Boolean deleteBatch(Class<T> classT, List<Serializable> keys);
	
	/**
	 * 更新实体对象
	 * @date 2015年1月23日 下午1:36:43
	 * @author 蒋迪
	 * @param t		
	 * @return 	返回更新后的实体对象
	 */
	public <T extends BaseEntity> T update(T t);
}
