package com.upsoft.system.bean;

import java.io.Serializable;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

/**
* Copyright (c) 2017,重庆扬讯软件技术有限公司<br>
* All rights reserved.<br>
*
* 文件名称：Result.java<br>
* 摘要：接口返回数据封装bean<br>
* -------------------------------------------------------<br>
* 当前版本：1.1.1<br>
* 作者：胡毅<br>
* 完成日期：2017年3月7日<br>
* -------------------------------------------------------<br>
* 取代版本：1.1.0<br>
* 原作者：胡毅<br>
* 完成日期：2017年3月7日<br>
 */
public class ResultBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	// 请求结果状态
	private Boolean flag;
	// 请求结果信息
	private String message;
	// 时间戳
	private Long timestamp;
	// 请求结果内容
	private Map<String,Object> data;

	public Boolean getFlag() {
		if(null==flag){
			flag = true;
		}
		return flag;
	}

	public void setFlag(Boolean flag) {
		this.flag = flag;
	}

	public String getMessage() {
		if(StringUtils.isBlank(message)){
			this.message = "请求成功";
		}
		return message;
	}

	public void setMessage(String message) {
		
		this.message = message;
	}

	public long getTimestamp(){
		if(null==timestamp){
			this.timestamp= System.currentTimeMillis();
		}
		return timestamp;
	}
	
	public void setTimestamp(long timeStamp){
		this.timestamp = timeStamp;
	}
	
	public Map<String, Object> getData() {
		return data;
	}

	public void setData(Map<String, Object> data) {
		this.data = data;
	}
	
}
