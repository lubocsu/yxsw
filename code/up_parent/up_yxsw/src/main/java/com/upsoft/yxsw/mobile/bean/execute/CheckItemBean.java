package com.upsoft.yxsw.mobile.bean.execute;

import java.io.Serializable;
import java.util.List;

/**
* Copyright (c) 2017,重庆扬讯软件技术有限公司<br>
* All rights reserved.<br>
*
* 文件名称：CheckItemBean.java<br>
* 摘要：执行检查时的检查项bean<br>
* -------------------------------------------------------<br>
* 取代版本：1.1.0<br>
* 原作者：胡毅<br>
* 完成日期：2017年9月24日<br>
 */
public class CheckItemBean implements Serializable {

	private static final long serialVersionUID = -5505682323263047806L;
	
	private String checkItemId;
	private String checkItemCode;
	private String checkItemName;
	private String inputType;
	private List<CheckItemDetailBean> detailList ;
	private String checkItemValue;// 执行后的检查值
	
	public String getCheckItemId() {
		return checkItemId;
	}
	public void setCheckItemId(String checkItemId) {
		this.checkItemId = checkItemId;
	}
	public String getCheckItemCode() {
		return checkItemCode;
	}
	public void setCheckItemCode(String checkItemCode) {
		this.checkItemCode = checkItemCode;
	}
	public String getCheckItemName() {
		return checkItemName;
	}
	public void setCheckItemName(String checkItemName) {
		this.checkItemName = checkItemName;
	}
	public String getInputType() {
		return inputType;
	}
	public void setInputType(String inputType) {
		this.inputType = inputType;
	}
	public List<CheckItemDetailBean> getDetailList() {
		return detailList;
	}
	public void setDetailList(List<CheckItemDetailBean> detailList) {
		this.detailList = detailList;
	}
	public String getCheckItemValue() {
		return checkItemValue;
	}
	public void setCheckItemValue(String checkItemValue) {
		this.checkItemValue = checkItemValue;
	}
	
}
