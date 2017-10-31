/*
 * WSLoginReturnBasicInfoBean.java
 * Created on 2015年1月22日 上午9:33:44
 * Copyright (c) 重庆扬讯软件技术有限公司  All Rights Reserved.
 * http://www.upsoft.com.cn
 *
 * This software is the confidential and proprietary information of UPSoft.
 * You shall not disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into with UPSoft.
 */
package com.upsoft.system.bean;

import java.util.List;
import java.util.Map;

import com.upsoft.system.entity.SysUser;

/**
 * Copyright (c) 2015,重庆扬讯软件技术有限公司<br>
 * All rights reserved.<br>
 *
 * 文件名称：WSLoginReturnBasicInfoBean.java<br>
 * 摘要：webservice登录返回基础数据bean<br>
 * -------------------------------------------------------<br>
 * 当前版本：1.1.1<br>
 * 作者：蒋迪<br>
 * 完成日期：2015年1月22日<br>
 * -------------------------------------------------------<br>
 * 取代版本：1.1.0<br>
 * 原作者：蒋迪<br>
 * 完成日期：2015年1月22日<br>
 */
public class WSLoginInfoBean {
	//系统用户
	private SysUser user;
	//最近登录时间
	private String refreshTime;
	//tokenId
	private String tokenId;
	//登录异常消息
	private String msg;
	
	/**登录用户所属的厂站或公司类型*/
	private String csOrgTypeId;
	/**登录用户所属的厂站或公司Id*/
	private String csOrgId;
	/**登录用户所属的厂站或公司编号*/
	private String csOrgCode;
	/**登录用户所属的厂站或公司名称*/
	private String csOrgName;
	
	/**登录用户机构链，四种：
	 * 集团->公司->厂所->部门；
	 * 集团->公司->厂所；
	 * 集团->公司；
	 * 集团；
	 * **/
	/**
	 * 内容格式：[{code=1, orgname=重庆水务集团, orgcode=CQSWJT, data1=集团}]
	 */
	private List<Map<String,Object>> orgLinkMap;
	
	// 请求ip
	private String requestIp;
	//本地地址+端口
	private String localAddress;
		
	//是否外网
	private boolean isExtranet;
	
	
	public SysUser getUser() {
		return user;
	}
	public void setUser(SysUser user) {
		this.user = user;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getRefreshTime() {
		return refreshTime;
	}
	public void setRefreshTime(String refreshTime) {
		this.refreshTime = refreshTime;
	}
	public String getTokenId() {
		return tokenId;
	}
	public void setTokenId(String tokenId) {
		this.tokenId = tokenId;
	}
	public String getRequestIp() {
		return requestIp;
	}
	public void setRequestIp(String requestIp) {
		this.requestIp = requestIp;
	}
	public String getLocalAddress() {
		return localAddress;
	}
	public void setLocalAddress(String localAddress) {
		this.localAddress = localAddress;
	}
	public boolean isExtranet() {
		return isExtranet;
	}
	public void setExtranet(boolean isExtranet) {
		this.isExtranet = isExtranet;
	}
	public String getCsOrgName() {
		return csOrgName;
	}
	public void setCsOrgName(String csOrgName) {
		this.csOrgName = csOrgName;
	}
	public String getCsOrgId() {
		return csOrgId;
	}
	public void setCsOrgId(String csOrgId) {
		this.csOrgId = csOrgId;
	}
	public String getCsOrgCode() {
		return csOrgCode;
	}
	public void setCsOrgCode(String csOrgCode) {
		this.csOrgCode = csOrgCode;
	}
	public String getCsOrgTypeId() {
		return csOrgTypeId;
	}
	public void setCsOrgTypeId(String csOrgTypeId) {
		this.csOrgTypeId = csOrgTypeId;
	}
	
	public List<Map<String, Object>> getOrgLinkMap() {
		return orgLinkMap;
	}
	public void setOrgLinkMap(List<Map<String, Object>> orgLinkMap) {
		this.orgLinkMap = orgLinkMap;
	}
	
}
