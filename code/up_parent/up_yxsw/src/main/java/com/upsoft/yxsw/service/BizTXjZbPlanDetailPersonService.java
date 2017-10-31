package com.upsoft.yxsw.service;

import java.util.List;

import com.upsoft.system.service.BaseService;
import com.upsoft.yxsw.entity.BizTXjZypCxMakePersonKh;


/**
 * Copyright (c) 2015,重庆扬讯软件技术有限公司<br>
 * All rights reserved.<br>
 *
 * 文件名称：BizTXjZbPlanDetailPersonService.java<br>
 * 摘要：厂巡排班管理值班人员表<br>
 * -------------------------------------------------------<br>
 * 当前版本：1.1.0<br>
 * 作者：hy<br>
 * 完成日期：2017-10-11 <br>
 */
public interface BizTXjZbPlanDetailPersonService  extends BaseService {

	/**
	 * 根据日期获取当日值班人员列表
	 * @date 2017年10月11日 上午10:13:32
	 * @param cxMakeId
	 * @param belongWscId
	 * @param zypDate
	 * @author 胡毅
	 * @return
	 */
	List<BizTXjZypCxMakePersonKh> getPersonListByWscIdAndZypDate(String cxMakeId,String belongWscId, String zypDate);

}
