package com.upsoft.yxsw.service;

import java.util.List;
import java.util.Map;

import com.upsoft.system.bean.PageBean;
import com.upsoft.system.bean.WSLoginInfoBean;
import com.upsoft.system.service.BaseService;
import com.upsoft.yxsw.entity.BizTXjdItem;



/**
* Copyright (c) 2017,重庆扬讯软件技术股份有限公司<br>
* All rights reserved.<br>
*
* 文件名称：BizTXjdItemService.java<br>
* 摘要：简要描述本文件的内容<br>
* -------------------------------------------------------<br>
* 当前版本：1.0<br>
* 作者：陈涛<br>
* 完成日期：2017年9月15日<br>
* -------------------------------------------------------<br>
*/
public interface BizTXjdItemService  extends BaseService {

	/**
	 * @date 2017年9月15日 下午7:31:19
	 * @author 陈涛
	 * @param pageBean
	 * @param params
	 * @return 
	 */
	Map<String, Object> getXjItemData(PageBean pageBean,
			Map<String, Object> params,WSLoginInfoBean loginInfo);


	/**
	 * 保存巡检点
	 * 
	 * @date 2017年9月15日 下午8:29:56
	 * @author 陈涛
	 * @param xjdItemName
	 * @param xjdItemAddress
	 * @param rfidCode
	 * @param xjdItemDesc
	 * @param loginInfo 
	 */
	void saveXjItem(String xjdItemName, String xjdItemAddress, String rfidCode,
			String xjdItemDesc, WSLoginInfoBean loginInfo);

	/**
	 * 验证是否存在
	 * 
	 * @date 2017年9月15日 下午8:52:55
	 * @author 陈涛
	 * @param validateValue
	 * @return 
	 */
	Boolean rfidExists(String validateValue);


	/**
	 * 通过ID获取巡检点信息
	 * 
	 * @date 2017年9月16日 上午9:58:31
	 * @author 陈涛
	 * @param xjItemId
	 * @return 
	 */
	BizTXjdItem findOneById(String xjItemId);


	/**
	 * 修改巡检点
	 * 
	 * @date 2017年9月16日 上午10:38:55
	 * @author 陈涛
	 * @param xjdItemId
	 * @param xjdItemName
	 * @param xjdItemAddress
	 * @param rfidCode
	 * @param xjdItemDesc
	 * @param loginInfo 
	 */
	void updateXjItem(String xjdItemId, String xjdItemName,
			String xjdItemAddress, String rfidCode, String xjdItemDesc,
			WSLoginInfoBean loginInfo);


	/**
	 * 验证是否挂接工艺段
	 * 
	 * @date 2017年9月16日 上午11:24:36
	 * @author 陈涛
	 * @param ids
	 * @return 
	 */
	Boolean checkItem(String id);


	/**
	 * 逻辑删除巡检点
	 * 
	 * @date 2017年9月16日 上午11:43:04
	 * @author 陈涛
	 * @param id
	 * @param loginInfo
	 * @return 
	 */
	void updateXjItemByIds(String id, WSLoginInfoBean loginInfo);


	/**
	 * 获取巡检点，组装成树
	 * 
	 * @date 2017年9月16日 下午2:47:49
	 * @author 陈涛
	 * @return 
	 */
	List<Map<String, Object>> getPointTree(WSLoginInfoBean loginInfo);


	/**
	 * 获取本场所下未关联的巡检点
	 * 
	 * @date 2017年9月19日 上午9:37:21
	 * @author 陈涛
	 * @param pageBean
	 * @param params
	 * @param loginInfo
	 * @return 
	 */
	Map<String, Object> getNoRelatedData(PageBean pageBean,
			Map<String, Object> params, WSLoginInfoBean loginInfo);


	/**
	 * 获取已关联的巡检点
	 * 
	 * @date 2017年9月19日 上午10:29:12
	 * @author 陈涛
	 * @param pageBean
	 * @param technicsId
	 * @param loginInfo
	 * @return 
	 */
	Map<String, Object> getRelatedItemData(PageBean pageBean,
			String technicsId, WSLoginInfoBean loginInfo);


	/**
	 * 获取下属厂站
	 * @date 2017年9月29日 下午3:29:33
	 * @author 陈涛
	 * @param loginInfo
	 * @return 
	 */
	List<Map<String, Object>> getOrgList(WSLoginInfoBean loginInfo);


	/**
	 * 通过扫标签给巡检点绑定RFID
	 * 
	 * @date 2017年9月30日 下午5:56:36
	 * @author 陈涛
	 * @param xjdItemId
	 * @param rfidCode 
	 */
	void updateRfidItem(String xjdItemId, String rfidCode);


	/**
	 * 查看该RFID标签是否被绑定
	 * 
	 * @date 2017年10月16日 上午10:41:14
	 * @author 陈涛
	 * @param rfidCode
	 * @return 
	 */
	boolean isRfidBinded(String rfidCode);
	
}
