package com.upsoft.yxsw.utils.cxmakestrategy;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.upsoft.system.util.DateUtil;



public class CalcCxMakeHisStrategyDefault implements ICalcCxMakeHisStrategy {

	/**
	 * 对指标项的历史数据进行处理
	 */
	@Override
	public CxMakeHisData calcCxMakeHis(List<Map<String,Object>> list,Map<String, Object> rootMap) {
		CxMakeHisData chd = new CxMakeHisData();
		List<ItemData> dataList = new ArrayList<ItemData>();
		for(Map<String,Object> map : list){
			if(null!=map.get("jlsbz")){
				ItemData id = new ItemData();
				id.setItem(DateUtil.stringToString((String)map.get("zyp_date"), "yyyyMMdd", "yyyy-MM-dd"));
				id.setValue(String.valueOf(map.get("jlsbz")));
				dataList.add(id);
			}
			
		}
		Object maxjlsbz = rootMap.get("max_jlsbz");
		Object minjlsbz = rootMap.get("min_jlsbz");
		Object avgjlsbz = rootMap.get("avg_jlsbz");
		String max_jlsbz = (null!=maxjlsbz?maxjlsbz.toString():"--");
		String min_jlsbz = (null!=minjlsbz?minjlsbz.toString():"--");
		String avg_jlsbz = (null!=avgjlsbz?avgjlsbz.toString():"--");
		chd.setAvgData(avg_jlsbz);
		chd.setMaxData(max_jlsbz);
		chd.setMinData(min_jlsbz);
		
		Object maxjlxdzfd = rootMap.get("max_jlxdzfd");
		Object minjlxdzfd = rootMap.get("min_jlxdzfd");
		Object avgjlxdzfd = rootMap.get("avg_jlxdzfd");
		String max_jlxdzfd = (null!=maxjlxdzfd?String.valueOf(((BigDecimal)maxjlxdzfd).doubleValue()):"--");
		String min_jlxdzfd = (null!=minjlxdzfd?String.valueOf(((BigDecimal)minjlxdzfd).doubleValue()):"--");
		String avg_jlxdzfd = (null!=avgjlxdzfd?String.valueOf(((BigDecimal)avgjlxdzfd).doubleValue()):"--");
		chd.setFdzAvgData(avg_jlxdzfd);
		chd.setFdzMaxData(max_jlxdzfd);
		chd.setFdzMinData(min_jlxdzfd);
		
		chd.setDataList(dataList);
		
		return chd;
	}

}
