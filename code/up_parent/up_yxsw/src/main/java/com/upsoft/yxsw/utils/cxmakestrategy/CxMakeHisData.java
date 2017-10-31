package com.upsoft.yxsw.utils.cxmakestrategy;

import java.util.ArrayList;
import java.util.List;

public class CxMakeHisData {
	private String zbname = "";
	private String starttime = "";
	private String endtime = "";
	private String title = "";
	
	//上报值的信息
	private String maxData = "";//最大值
	private String minData = "";//最小值
	private String avgData = "";//平均值
	
	//浮动制的相关信息
	private String fdzMaxData = "";
	private String fdzMinData = "";
	private String fdzAvgData = "";
	
	/**
	 * 组装的列表数据，可用于生成线状图
	 */
	private List<ItemData> dataList = new ArrayList<ItemData>();

	public String getStarttime() {
		return starttime;
	}

	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}

	public String getEndtime() {
		return endtime;
	}

	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getMaxData() {
		return maxData;
	}

	public void setMaxData(String maxData) {
		this.maxData = maxData;
	}

	public String getMinData() {
		return minData;
	}

	public void setMinData(String minData) {
		this.minData = minData;
	}

	public String getAvgData() {
		return avgData;
	}

	public void setAvgData(String avgData) {
		this.avgData = avgData;
	}

	public List<ItemData> getDataList() {
		return dataList;
	}

	public void setDataList(List<ItemData> dataList) {
		this.dataList = dataList;
	}

	public String getZbname() {
		return zbname;
	}

	public void setZbname(String zbname) {
		this.zbname = zbname;
	}

	public String getFdzMaxData() {
		return fdzMaxData;
	}

	public void setFdzMaxData(String fdzMaxData) {
		this.fdzMaxData = fdzMaxData;
	}

	public String getFdzMinData() {
		return fdzMinData;
	}

	public void setFdzMinData(String fdzMinData) {
		this.fdzMinData = fdzMinData;
	}

	public String getFdzAvgData() {
		return fdzAvgData;
	}

	public void setFdzAvgData(String fdzAvgData) {
		this.fdzAvgData = fdzAvgData;
	}
	
	
}
