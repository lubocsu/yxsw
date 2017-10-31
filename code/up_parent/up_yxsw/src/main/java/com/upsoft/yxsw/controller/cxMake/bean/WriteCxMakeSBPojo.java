package com.upsoft.yxsw.controller.cxMake.bean;

import java.io.Serializable;

/**
* Copyright (c) 2017,重庆扬讯软件技术有限公司<br>
* All rights reserved.<br>
*
* 文件名称：WriteCxMakeSBPojo.java<br>
* 摘要：作业票上报内容<br>
* -------------------------------------------------------<br>
* 取代版本：1.1.0<br>
* 原作者：胡毅<br>
* 完成日期：2017年10月10日<br>
 */
public class WriteCxMakeSBPojo implements Serializable {
	
	private static final long serialVersionUID = 2117421877845879451L;
	private String makeTmpItemId;
	private String jlsbz; // 完成值
	private String jlqcl; // 去除率
	public String getMakeTmpItemId() {
		return makeTmpItemId;
	}
	public void setMakeTmpItemId(String makeTmpItemId) {
		this.makeTmpItemId = makeTmpItemId;
	}
	public String getJlsbz() {
		return jlsbz;
	}
	public void setJlsbz(String jlsbz) {
		this.jlsbz = jlsbz;
	}
	public String getJlqcl() {
		return jlqcl;
	}
	public void setJlqcl(String jlqcl) {
		this.jlqcl = jlqcl;
	}
}
