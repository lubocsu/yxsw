package com.upsoft.yxsw.service;

import java.util.Map;

import com.upsoft.system.bean.PageBean;
import com.upsoft.system.service.BaseService;
import com.upsoft.yxsw.entity.BizTXjdItem;

public interface MobileScanService extends BaseService{

	/**
	 * 通过设备设施code查找设备信息
	 * 
	 * @date 2017年9月27日 上午9:11:53
	 * @author 陈涛
	 * @param sbssCode
	 * @return 
	 */
	Map<String, Object> getSbss(String sbssCode);

	/**
	 * 获取巡检纪录
	 * 
	 * @date 2017年9月27日 下午3:42:15
	 * @author 陈涛
	 * @param bean
	 * @param params
	 * @return 
	 */
	Map<String, Object> getXjRecordListOnMobile(String sbId,PageBean bean,
			Map<String, Object> params);

	/**
	 * 通过RFID编号获取巡检点
	 * 
	 * @date 2017年9月28日 上午10:41:29
	 * @author 陈涛
	 * @param rfidCode
	 * @return 
	 */
	BizTXjdItem getXjItem(String rfidCode);

	/**
	 * 根据巡检点ID获取巡检点巡检记录
	 * 
	 * @date 2017年9月28日 下午3:41:21
	 * @author 陈涛
	 * @param xjItemId
	 * @param bean
	 * @param params
	 * @return 
	 */
	Map<String, Object> getXjItemRecordListOnMobile(String xjItemId,
			PageBean bean, Map<String, Object> params);
	
	
}
