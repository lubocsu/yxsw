package com.upsoft.yxsw.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.upsoft.system.bean.PageBean;
import com.upsoft.system.entity.SysUser;
import com.upsoft.system.service.BaseService;
import com.upsoft.yxsw.entity.BizTXjZbItemEntity;
/**
 * 作业票下的指标项管理Service
* Copyright (c) 2017,重庆扬讯软件技术有限公司<br>
* All rights reserved.<br>
*
* 文件名称：BizTXjZbItemService.java<br>
* 摘要：简要描述本文件的内容<br>
* -------------------------------------------------------<br>
* 当前版本：1.1.1<br>
* 作者：杨磊<br>
* 完成日期：2017年9月11日<br>
* -------------------------------------------------------<br>
* 取代版本：1.1.0<br>
* 原作者：杨磊<br>
* 完成日期：2017年9月11日<br>
 */

public interface BizTXjZbItemService extends BaseService {

	Map<String, Object> getIndListAndCount(Map<String, Object> pars, PageBean bean);

	boolean deleteOneWarning(String cxzbId);

	void saveInd(BizTXjZbItemEntity bizTXjZbItemEntity, SysUser user);

	List<Map<String, Object>>  findOneByCode(String cxzb_code);

	List<Map<String, Object>> getIndList(Map<String, Object> pars, int i, int j);

	Boolean  checkTemplate(String id);

	Map<String, Object> getIndList4Temp(Map<String, Object> pars, PageBean bean);

}
