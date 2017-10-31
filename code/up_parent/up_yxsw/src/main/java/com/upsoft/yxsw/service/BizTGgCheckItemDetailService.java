package com.upsoft.yxsw.service;

import java.util.List;
import java.util.Map;

import com.upsoft.system.service.BaseService;
import com.upsoft.yxsw.entity.BizTGgCheckItemDetail;


/**
* Copyright (c) 2017,重庆扬讯软件技术有限公司<br>
* All rights reserved.<br>
*
* 文件名称：BizTGgCheckItemDetailService.java<br>
* 摘要：简要描述本文件的内容<br>
* -------------------------------------------------------<br>
* 当前版本：1.1.1<br>
* 作者：胡毅<br>
* 完成日期：2017年9月9日<br>
* -------------------------------------------------------<br>
* 取代版本：1.1.0<br>
* 原作者：胡毅<br>
* 完成日期：2017年9月9日<br>
 */
public interface BizTGgCheckItemDetailService  extends BaseService {
	
	/**
	 * 保存一项检查项明细
	 * @date 2017年9月9日 下午5:52:55
	 * @author 胡毅
	 * @param bizTGgCheckItemDetail
	 * @return
	 */
	public BizTGgCheckItemDetail save(BizTGgCheckItemDetail bizTGgCheckItemDetail);

	/**
	 * 根据检查项ID获取明细信息，返回map集合，并将字典值转换为汉字,字段为对应属性加上"Name"
	 * @date 2017年9月11日 下午5:04:45
	 * @author 胡毅
	 * @param checkItemId
	 * @return
	 */
	public List<Map<String, Object>> getListByCheckItemId(String checkItemId);

	/**
	 * 根据检查项ID获取明细信息,返回 实体集合
	 * @date 2017年9月11日 下午8:20:52
	 * @author 胡毅
	 * @param checkItemId
	 * @param _null 没有作用，只用于占位
	 * @return
	 */
	public List<BizTGgCheckItemDetail> getListByCheckItemId(String checkItemId, Boolean _null);

	/**
	 * 根据检查项Id删除明细
	 * @date 2017年9月11日 下午9:04:10
	 * @author 胡毅
	 * @param checkItemId
	 */
	public void deleteByCheckItemId(String checkItemId);
	
}
