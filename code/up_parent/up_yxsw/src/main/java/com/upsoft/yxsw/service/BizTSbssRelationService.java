package com.upsoft.yxsw.service;

import java.util.List;
import java.util.Map;

import com.upsoft.system.bean.PageBean;
import com.upsoft.system.service.BaseService;
import com.upsoft.yxsw.entity.BizTSbssRelation;


/**
* Copyright (c) 2017,重庆扬讯软件技术有限公司<br>
* All rights reserved.<br>
*
* 文件名称：BizTSbssRelationService.java<br>
* 摘要：设施设备关联关系<br>
* -------------------------------------------------------<br>
* 当前版本：1.1.1<br>
* 作者：胡毅<br>
* 完成日期：2017年9月14日<br>
* -------------------------------------------------------<br>
* 取代版本：1.1.0<br>
* 原作者：胡毅<br>
* 完成日期：2017年9月14日<br>
 */
public interface BizTSbssRelationService  extends BaseService {

	/**
	 * 获取设施设备关联关系列表（ssId,设施Id）
	 * @date 2017年9月16日 下午5:26:13
	 * @author 胡毅
	 * @param ssId
	 * @param pageBean
	 * @return
	 */
	Map<String, Object> getSBSSRelationList(String ssId,PageBean pageBean);

	/**
	 * 批量保存设备设施关联关系
	 * @date 2017年9月17日 上午11:14:58
	 * @author 胡毅
	 * @param relationsList
	 * @return
	 */
	List<BizTSbssRelation> saveAll(List<BizTSbssRelation> relationsList);

	/**
	 * 批量删除
	 * @date 2017年9月17日 下午12:47:07
	 * @author 胡毅
	 * @param ids
	 */
	void deleteAll(String ids);

	/**
	 * 获取未被关联到设施的设备
	 * @date 2017年10月20日 下午2:33:43
	 * @author 胡毅
	 * @param bean
	 * @param params
	 * @return
	 */
	Map<String, Object> getEqList(PageBean bean, Map<String, Object> params);
	
}
