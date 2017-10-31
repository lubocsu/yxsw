package com.upsoft.yxsw.service;

import java.util.List;
import java.util.Map;

import com.upsoft.system.bean.PageBean;
import com.upsoft.system.service.BaseService;
import com.upsoft.yxsw.entity.BizTGgCheckItem;
import com.upsoft.yxsw.entity.BizTGgCheckItemDetail;


/**
 * Copyright (c) 2015,重庆扬讯软件技术有限公司<br>
 * All rights reserved.<br>
 *
 * 文件名称：BizTGgCheckItemService.java<br>
 * 摘要：<br>
 * -------------------------------------------------------<br>
 * 当前版本：1.1.0<br>
 * 作者：<br>
 * 完成日期：2017-09-08 <br>
 */
public interface BizTGgCheckItemService extends BaseService {
	
	/**
	 * 获取检查项定义的分页数据，全部字段，字典值转换为汉字，字段为对一属性字段加上"_name"，del_flag = 0
	 * @param bean
	 * @param params
	 * @return
	 */
	public Map<String, Object> getList(PageBean bean, Map<String, Object> params);
	
	/**
	 * 保存 检查项定义信息
	 * @date 2017年9月9日 下午5:53:34
	 * @author 胡毅
	 * @param bizTGgCheckItem
	 * @return
	 */
	public BizTGgCheckItem save(BizTGgCheckItem bizTGgCheckItem);
	
	/**
	 * 验证检查项编码唯一性
	 * @date 2017年9月10日 下午3:33:59
	 * @author 胡毅
	 * @param checkItemCode
	 * @return
	 */
	public Long validateCheckItemCode(String checkItemCode);

	/**
	 * 保存检查项和检查项明细
	 * @date 2017年9月10日 下午4:13:22
	 * @author 胡毅
	 * @param checkItemEnity
	 * @param checkItemDetailEnityList
	 * @return
	 */
	public Boolean saveCheckItemAndDetail(BizTGgCheckItem checkItemEnity, List<BizTGgCheckItemDetail> checkItemDetailEnityList);

	/**
	 * 根据检查项ID批量删除检查项，并判断那些检查项已关联了设备设施，不能删除
	 * @date 2017年9月11日 上午11:07:42
	 * @author 胡毅
	 * @param checkItemIdList
	 */
	public List<BizTGgCheckItem> delete(List<String> checkItemIdList);

	/**
	 * 根据检查项Id查询检查项集合，无分页，全部字段
	 * @date 2017年9月11日 下午1:44:01
	 * @author 胡毅
	 * @param attachedCheckItemIdList
	 * @return
	 */
	public List<BizTGgCheckItem> getListByCheckItemIds(List<String> checkItemIdList);

	/**
	 * 根据检查项ID获取检查项信息，返回Map,字段重命名为实体类属性，并将字典值转换为汉字，字段为对应属性加上"Name"
	 * @date 2017年9月11日 下午4:47:52
	 * @author 胡毅
	 * @param checkItemId
	 * @return
	 */
	public Map<String, Object> findOne(String checkItemId);
  /**
   * 获取检查项与设备的关系通过设备Id
   * @date 2017年9月15日 上午10:16:02
   * @author 杨磊
   * @param sbId
   * @return
   */
	public List<Map<String, Object>> getCheckBySbId(String sbId,Map<String, Object> params);
	
//	/**
//	 * 根据检查项ID获取检查项信息，返回实体
//	 * @date 2017年9月11日 下午8:16:56
//	 * @author 胡毅
//	 * @param checkItemId
//	 * @return
//	 */
//	public BizTGgCheckItem findOne2(String checkItemId);

}
