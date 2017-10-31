package com.upsoft.yxsw.service;


import java.util.List;

import com.upsoft.system.bean.WSLoginInfoBean;
import com.upsoft.system.service.BaseService;
import com.upsoft.yxsw.entity.BizTXjZypCxMakeHis;



/**
* Copyright (c) 2017,重庆扬讯软件技术股份有限公司<br>
* All rights reserved.<br>
*
* 文件名称：BizTXjZypCxMakeHisService.java<br>
* 摘要：简要描述本文件的内容<br>
* -------------------------------------------------------<br>
* 当前版本：1.0<br>
* 作者：陈涛<br>
* 完成日期：2017年10月10日<br>
* -------------------------------------------------------<br>
*/
public interface BizTXjZypCxMakeHisService  extends BaseService {

	/**
	 * 根据作业票id获取操作流程
	 * 
	 * @date 2017年10月10日 上午10:36:57
	 * @author 陈涛
	 * @param cxMakeId
	 * @return 
	 */
	List<BizTXjZypCxMakeHis> getZypCxMakeHisList(String cxMakeId);

	/**
	 * 保存流程
	 * 
	 * @date 2017年10月10日 下午5:29:46
	 * @author 陈涛
	 * @param cxMakeId
	 * @param radio
	 * @param content 
	 */
	void saveProcess(String cxMakeId, String radio, String content,String optType,WSLoginInfoBean loginInfo);
	
}
