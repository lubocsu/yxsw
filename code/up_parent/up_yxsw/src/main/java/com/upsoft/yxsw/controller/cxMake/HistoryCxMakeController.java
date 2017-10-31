package com.upsoft.yxsw.controller.cxMake;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.upsoft.system.util.DateUtil;
import com.upsoft.yxsw.constant.DictConstant;
import com.upsoft.yxsw.service.HistoryCxMakeService;
import com.upsoft.yxsw.utils.cxmakestrategy.CxMakeHisData;
import com.upsoft.yxsw.utils.cxmakestrategy.CxMakeHisStrategyFactory;
import com.upsoft.yxsw.utils.cxmakestrategy.ICalcCxMakeHisStrategy;

/**
 * Copyright (c) 2015,重庆扬讯软件技术有限公司<br>
 * All rights reserved.<br>
 *
 * 文件名称：HistoryCxMakeController.java<br>
 * 摘要：查询工作票指标项的历史情况<br>
 * -------------------------------------------------------<br>
 * 当前版本：1.1.0<br>
 * 作者：刘志华<br>
 * 完成日期：2017年9月18日 16:26:07<br>
 */
@Controller
@RequestMapping("/zbhistory")
public class HistoryCxMakeController {
	
	@Autowired
	HistoryCxMakeService historyCxMakeService;
	
	@RequestMapping("/init")
	public String init(ModelMap mod,@RequestParam(required =true)String wscid,
			  						@RequestParam(required =true)String zbxid,
			  						@RequestParam(required =true)String tempitemid,
			  						String starttime,String endtime,String weather){
		if(null == starttime || "".equals(starttime)){
			SimpleDateFormat sf = new SimpleDateFormat(DateUtil.DATE_FORMAT_WITHOUT_TIME);
			Calendar cal = Calendar.getInstance();
			endtime = sf.format(cal.getTime());
			cal.add(Calendar.DAY_OF_MONTH, -360);
			starttime =  sf.format(cal.getTime());
		}
		mod.put("wscid", wscid);
		mod.put("zbxid", zbxid);
		mod.put("tempitemid", tempitemid);
		mod.put("starttime", StringUtils.isNotBlank(starttime)?starttime:"");
		mod.put("endtime", StringUtils.isNotBlank(endtime)?endtime:"");
		mod.put("weather", StringUtils.isNotBlank(weather)?weather:"");
		mod.put("WEATHER_TYPE", DictConstant.WEATHER_TYPE.getValue());
		return "/WEB-INF/jsp/cxMake/historyCxMake/main";
	}
	
	/**
	 * 
	 * @param wscid 污水厂ID
	 * @param zbxid 指标项ID
	 * @param tmpitemid 作业票模板项ID
	 * @param weather 天气状况
	 */
	@ResponseBody
	@RequestMapping(value="/zbparam")
	public CxMakeHisData queryZbHistorys(@RequestParam(required =true)String wscid,
								  @RequestParam(required =true)String zbxid,
								  @RequestParam(required =true)String tempitemid,
								  String starttime,String endtime,String weather,ModelMap mod){
		
		if(StringUtils.isBlank(weather)||StringUtils.equals("null", weather)){
			weather = StringUtils.EMPTY;
		}
		String sql1 = getQuerySql(weather);
		String sql2 = getCalSql(weather);
		
		ArrayList<String> alist = new ArrayList<String>();
		alist.add(wscid);
		alist.add(zbxid);
		alist.add(tempitemid);
		alist.add(starttime.replace("-", StringUtils.EMPTY));
		alist.add(endtime.replace("-", StringUtils.EMPTY));
		if(null !=weather && !"".equals(weather))
		{
			alist.add(weather);
		}
		
		List<Map<String, Object>> list = historyCxMakeService.queryZbxHis(alist.toArray(), sql1);
		CxMakeHisData chd = new CxMakeHisData();
		if(list.size()>0){
			Map<String, Object> rootMap = historyCxMakeService.getCalZbxHis(alist.toArray(), sql2);
			
			ICalcCxMakeHisStrategy method = CxMakeHisStrategyFactory.getZbCalMethod(zbxid);
			chd = method.calcCxMakeHis(list, rootMap);
			String zbxName = historyCxMakeService.getZbxName(zbxid);
			chd.setZbname(zbxName);
			chd.setStarttime(starttime);
			chd.setEndtime(endtime);
			chd.setTitle(zbxName+"在"+starttime+"至"+endtime+"期间上报值情况");
			return chd;
		}else{
			return null;
		}
		
	}
	
	private String getQuerySql(String weather){
		StringBuilder sql = new StringBuilder("");
		 sql.append("SELECT M.ZYP_DATE,I.JLSBZ");
		 sql.append("  FROM BIZ_T_XJ_ZYP_CX_MAKE M,");
		 sql.append("	    BIZ_T_XJ_ZYP_CX_MAKE_TMP T,");
		 sql.append("			BIZ_T_XJ_ZYP_CX_MAKE_TMP_ITEM I");
		 sql.append("	 WHERE M.CX_MAKE_ID = T.CX_MAKE_ID");
		 sql.append("	    AND T.MAKE_TMP_ID = I.MAKE_TMP_ID");
		 sql.append("			AND M.BELONG_WSC_ID = ?  ");//厂站ID
		 sql.append("			AND I.CXZB_ID = ? ");//--要查询的具体指标
		 sql.append("			AND T.ZXP_TEMP_ITM_ID = ? ");// --作业票模板项主键
		 sql.append("			AND M.ZYP_DATE BETWEEN ? AND ? ");
		 
		if(null !=weather && !"".equals(weather))
			sql.append("           AND  M.WEATHER = ? ");
		 sql.append("			ORDER BY M.ZYP_DATE ASC");
		 
		 return sql.toString();
	}
	
	private String getCalSql(String weather){
		StringBuilder sql = new StringBuilder("");
		//实际的平均值，最大值，最小值
		 sql.append("SELECT MAX(I.JLSBZ) AS MAX_JLSBZ,MIN(I.JLSBZ) AS MIN_JLSBZ,AVG(I.JLSBZ) AS AVG_JLSBZ,MAX(I.JLXDZFD) AS MAX_JLXDZFD,MIN(I.JLXDZFD) AS MIN_JLXDZFD,AVG(I.JLXDZFD) AS AVG_JLXDZFD ");
		 sql.append("  FROM BIZ_T_XJ_ZYP_CX_MAKE M,");
		 sql.append("	    BIZ_T_XJ_ZYP_CX_MAKE_TMP T,");
		 sql.append("			BIZ_T_XJ_ZYP_CX_MAKE_TMP_ITEM I");
		 sql.append("	 WHERE M.CX_MAKE_ID = T.CX_MAKE_ID");
		 sql.append("	    AND T.MAKE_TMP_ID = I.MAKE_TMP_ID");
		 sql.append("			AND M.BELONG_WSC_ID = ?");   //厂站ID
		 sql.append("			AND I.CXZB_ID = ? "); //--要查询的具体指标
		 sql.append("			AND T.ZXP_TEMP_ITM_ID = ?  "); //--作业票模板项主键
		 sql.append("			AND M.ZYP_DATE BETWEEN ? AND ? ");
		 
		if(null !=weather && !"".equals(weather))
			sql.append("           AND  M.WEATHER = ? ");
		 sql.append("			ORDER BY M.ZYP_DATE ASC");
		 
		 return sql.toString();
	}
	
}
