package com.upsoft.yxsw.controller.cxMake.bean;

import java.io.Serializable;
import java.util.List;

import com.upsoft.yxsw.entity.BizTXjZypCxMakePersonKh;
import com.upsoft.yxsw.entity.BizTXjZypCxMakeTmp;

/**
* Copyright (c) 2017,重庆扬讯软件技术有限公司<br>
* All rights reserved.<br>
*
* 文件名称：CxMakeDetailBean.java<br>
* 摘要：作业票PC详情<br>
* -------------------------------------------------------<br>
* 取代版本：1.1.0<br>
* 原作者：胡毅<br>
* 完成日期：2017年9月28日<br>
 */
public class CxMakeDetailPojo implements Serializable {

	private static final long serialVersionUID = 1L;

	private String cxMakeId;
	private String zypDate;
	private String zypCode;
	private String fzrId;
	private String fzrName;
	private String zypDesc;
	private String warning;
	private String warningName;
	private String weatherCode;
	private String weatherName;
	private String updateTimestemp;
	private Long tempVersion;
	
	private List<BizTXjZypCxMakeTmp> makeTmpList; // 作业票处理段
	private List<BizTXjZypCxMakePersonKh> makePersonKhList; // 当日作业票人员考核信息
	
	public String getCxMakeId() {
		return cxMakeId;
	}
	public void setCxMakeId(String cxMakeId) {
		this.cxMakeId = cxMakeId;
	}
	public String getZypDate() {
		return zypDate;
	}
	public void setZypDate(String zypDate) {
		this.zypDate = zypDate;
	}
	public String getZypCode() {
		return zypCode;
	}
	public void setZypCode(String zypCode) {
		this.zypCode = zypCode;
	}
	public String getFzrId() {
		return fzrId;
	}
	public void setFzrId(String fzrId) {
		this.fzrId = fzrId;
	}
	public String getFzrName() {
		return fzrName;
	}
	public void setFzrName(String fzrName) {
		this.fzrName = fzrName;
	}
	public String getZypDesc() {
		return zypDesc;
	}
	public void setZypDesc(String zypDesc) {
		this.zypDesc = zypDesc;
	}
	public String getWarning() {
		return warning;
	}
	public void setWarning(String warning) {
		this.warning = warning;
	}
	public String getWarningName() {
		return warningName;
	}
	public void setWarningName(String warningName) {
		this.warningName = warningName;
	}
	public List<BizTXjZypCxMakeTmp> getMakeTmpList() {
		return makeTmpList;
	}
	public void setMakeTmpList(List<BizTXjZypCxMakeTmp> makeTmpList) {
		this.makeTmpList = makeTmpList;
	}
	public String getWeatherCode() {
		return weatherCode;
	}
	public void setWeatherCode(String weatherCode) {
		this.weatherCode = weatherCode;
	}
	public String getWeatherName() {
		return weatherName;
	}
	public void setWeatherName(String weatherName) {
		this.weatherName = weatherName;
	}
	public String getUpdateTimestemp() {
		return updateTimestemp;
	}
	public void setUpdateTimestemp(String updateTimestemp) {
		this.updateTimestemp = updateTimestemp;
	}
	public Long getTempVersion() {
		return tempVersion;
	}
	public void setTempVersion(Long tempVersion) {
		this.tempVersion = tempVersion;
	}
	public List<BizTXjZypCxMakePersonKh> getMakePersonKhList() {
		return makePersonKhList;
	}
	public void setMakePersonKhList(List<BizTXjZypCxMakePersonKh> makePersonKhList) {
		this.makePersonKhList = makePersonKhList;
	}
}