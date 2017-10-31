package com.upsoft.yxsw.service;

import java.util.List;

import com.upsoft.system.service.BaseService;
import com.upsoft.yxsw.entity.BizTXjZypCxMakePersonKh;


/**
 * Copyright (c) 2015,重庆扬讯软件技术有限公司<br>
 * All rights reserved.<br>
 *
 * 文件名称：BizTXjZypCxMakePersonKhService.java<br>
 * 摘要：人员考核service<br>
 * -------------------------------------------------------<br>
 * 当前版本：1.1.0<br>
 * 作者：<br>
 * 完成日期：2017-10-11 <br>
 */
public interface BizTXjZypCxMakePersonKhService  extends BaseService {

	/**
	 * 获取作业票下的人员考核列表
	 * @date 2017年10月11日 上午9:34:18
	 * @author 胡毅
	 * @param cxMakeId
	 * @return
	 */
	List<BizTXjZypCxMakePersonKh> getListByCxMakeId(String cxMakeId);
	

}
