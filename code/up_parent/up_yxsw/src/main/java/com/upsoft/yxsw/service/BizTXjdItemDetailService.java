package com.upsoft.yxsw.service;

import java.util.List;

import com.upsoft.system.bean.WSLoginInfoBean;
import com.upsoft.system.service.BaseService;



/**
* Copyright (c) 2017,重庆扬讯软件技术股份有限公司<br>
* All rights reserved.<br>
*
* 文件名称：BizTXjdItemDetailService.java<br>
* 摘要：简要描述本文件的内容<br>
* -------------------------------------------------------<br>
* 当前版本：1.0<br>
* 作者：陈涛<br>
* 完成日期：2017年9月17日<br>
* -------------------------------------------------------<br>
*/
public interface BizTXjdItemDetailService  extends BaseService {

	/**
	 * 巡检点关联设备
	 * 
	 * @date 2017年9月17日 下午5:02:37
	 * @author 陈涛
	 * @param idArray
	 * @param nameArray
	 * @param user
	 * @return 
	 */
	void saveRelated(String spotId,List<String> idArray, List<String> nameArray,String detailType);

	/**
	 * 删除关联关系
	 * 
	 * @date 2017年9月18日 下午1:48:29
	 * @author 陈涛
	 * @param idArray 
	 */
	void delRelated(List<String> idArray);

	/**
	 * 查询巡检点关联是否为0 
	 * 
	 * @date 2017年9月18日 下午3:01:26
	 * @author 陈涛
	 * @param spotId
	 * @return 
	 */
	Boolean getCount(String spotId);

	/**
	 * 关联安全定义
	 * 
	 * @date 2017年9月22日 下午4:20:13
	 * @author 陈涛
	 * @param spotId
	 * @param idArray
	 * @param nameArray
	 * @param value 
	 */
	void saveRelatedWar(String spotId, List<String> idArray,
			List<String> nameArray, WSLoginInfoBean loginInfo);

	/**
	 * 删除安全定义关联
	 * 
	 * @date 2017年9月22日 下午5:25:24
	 * @author 陈涛
	 * @param idArray 
	 */
	void delRelateWar(List<String> idArray);
	
}
