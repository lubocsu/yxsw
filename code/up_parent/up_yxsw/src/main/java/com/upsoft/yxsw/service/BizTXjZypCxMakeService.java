package com.upsoft.yxsw.service;

import java.util.List;
import java.util.Map;

import com.upsoft.system.bean.PageBean;
import com.upsoft.system.bean.WSLoginInfoBean;
import com.upsoft.system.service.BaseService;
import com.upsoft.yxsw.controller.cxMake.bean.CxMakeDetailPojo;
import com.upsoft.yxsw.controller.cxMake.bean.CxMakePersonHkPojo;
import com.upsoft.yxsw.controller.cxMake.bean.WriteCxMakeSBPojo;
import com.upsoft.yxsw.entity.BizTXjZypCxMake;
import com.upsoft.yxsw.entity.BizTXjZypCxMakeTmp;
import com.upsoft.yxsw.entity.BizTXjZypCxMakeTmpItem;
import com.upsoft.yxsw.mobile.bean.zyp.CxMakeDetailBean;


/**
 * Copyright (c) 2015,重庆扬讯软件技术有限公司<br>
 * All rights reserved.<br>
 *
 * 文件名称：BizTXjZypCxMakeService.java<br>
 * 摘要：作业票拟定-表头信息<br>
 * -------------------------------------------------------<br>
 * 当前版本：1.1.0<br>
 * 作者：<br>
 * 完成日期：2017-09-28 <br>
 */
public interface BizTXjZypCxMakeService  extends BaseService {

	/**
	 *  获取今日及以后以审核通过的作业票列表
	 * @date 2017年9月28日 下午4:46:45
	 * @author 胡毅
	 * @param pageBean
	 * @param csOrgId
	 */
	Map<String,Object> getZYPListForAPP(PageBean pageBean, String csOrgId);

	/**
	 * 获取作业票详情
	 * @date 2017年9月28日 下午5:57:23
	 * @author 胡毅
	 * @param cxMakeId
	 * @param taskDate
	 * @param wscOrgId
	 * @return
	 */
	CxMakeDetailBean getZYPDetailForAPP(String cxMakeId,String taskDate,String wscOrgId);

	/**
	 * 获取作业票详情，用于填报，验收，查看
	 * @date 2017年10月9日 下午7:40:35
	 * @author 胡毅
	 * @param cxMakeId
	 * @return
	 */
	CxMakeDetailPojo getZYPDetailForPC(String cxMakeId);
	
	/**
	 * 获取作业票列表
	 * @date 2017年9月30日 下午4:14:28
	 * @author 胡毅
	 * @param pageBean
	 * @param paramMap
	 * @return
	 */
	Map<String, Object> getCxMakeList(PageBean pageBean, Map<String, Object> paramMap);

	/**
	 * 作业票审核
	 * 
	 * @date 2017年10月10日 下午4:59:25
	 * @author 陈涛
	 * @param cxMakeId
	 * @param radio_
	 * @param content 
	 */
	void updateZypCheck(String cxMakeId, String radio_, String content,WSLoginInfoBean loginInfo);

	/**
	 * 作业票中止
	 * 
	 * @date 2017年10月10日 下午7:34:57
	 * @author 陈涛
	 * @param cxMakeId
	 * @param content
	 * @param loginInfo 
	 */
	void updateZypDiscontinue(String cxMakeId, String content,
			WSLoginInfoBean loginInfo);

	/**
	 * 作业票填报，并形成流程
	 * @date 2017年10月10日 下午4:59:44
	 * @author 胡毅
	 * @param makeTmpItemJSONList
	 * @param personKhJSONList
	 * @param loginInfo
	 * @param saveType 保存类型 1 仅保存，2 保存并提交
	 * @param cxMakeId
	 * @param weather
	 */
	void saveCxMakeTmpItemSBValue(List<WriteCxMakeSBPojo> makeTmpItemJSONList,List<CxMakePersonHkPojo> personKhJSONList,
				WSLoginInfoBean loginInfo, String saveType,String cxMakeId,String weather);
	
	/**
	 * 验证厂所在某日是否已有作业票
	 * @date 2017年10月11日 下午8:15:29
	 * @param zypDate yyyyMMdd
	 * @param csOrgId
	 * @return 已存在 true; 不存在 false;
	 */
	boolean ZypExists(String zypDate, String csOrgId);

	/**
	 * 作业票验收
	 * 
	 * @date 2017年10月12日 上午10:42:11
	 * @author 陈涛
	 * @param cxMakeId
	 * @param radioValue
	 * @param content
	 * @param loginInfo 
	 */
	void updateZypReceive(String cxMakeId, String radioValue, String content,
			WSLoginInfoBean loginInfo);

	/**
	 * 保存作业票拟定信息
	 * @date 2017年10月12日 下午7:40:27
	 * @param zypCxMake
	 * @param makeTmpList
	 */
	void saveZypcxMake(BizTXjZypCxMake zypCxMake, List<BizTXjZypCxMakeTmp> makeTmpList, String saveType, WSLoginInfoBean loginInfo);
	
	/**
	 * 保存修改后的作业票拟定信息
	 * @date 2017年10月12日 下午7:40:27
	 * @param zypCxMake
	 * @param makeTmpList
	 */
	void saveEditZypcxMake(BizTXjZypCxMake zypCxMake, List<BizTXjZypCxMakeTmpItem> makeTmpItemList, String saveType, WSLoginInfoBean loginInfo);
	
	/**
	 * 删除作业票拟定信息
	 * @date 2017年10月16日 下午4:04:40
	 * @param cxMakeId
	 */
	void delZypCxMake(String cxMakeId);
	
	/**根据版本号获取最新的作业票拟定信息
	 * @date 2017年10月18日 下午3:03:22
	 * @param version 版本
	 * @param csOrgId 厂所
	 * @return
	 */
	BizTXjZypCxMake getCxMakeLast(String csOrgId, Long version);
}
