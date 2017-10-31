package com.upsoft.yxsw.controller.cxMake;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.upsoft.system.util.DateUtil;
import com.upsoft.yxsw.constant.Constant;
import com.upsoft.yxsw.controller.cxMake.bean.CxMakeDetailPojo;
import com.upsoft.yxsw.entity.BizTXjZypCxMakeHis;
import com.upsoft.yxsw.service.BizTXjZypCxMakeHisService;
import com.upsoft.yxsw.service.BizTXjZypCxMakeService;

/**
* Copyright (c) 2017,重庆扬讯软件技术有限公司<br>
* All rights reserved.<br>
*
* 文件名称：CxMakeDetailAndProcessController.java<br>
* 摘要：作业票流程与详情信息<br>
* -------------------------------------------------------<br>
* 当前版本：1.1.1<br>
* 作者：胡毅<br>
* 完成日期：2017年9月30日<br>
* -------------------------------------------------------<br>
* 取代版本：1.1.0<br>
* 原作者：胡毅<br>
* 完成日期：2017年9月30日<br>
 */
@Controller
@RequestMapping("/cxmake/detailAndProcess")
public class CxMakeDetailAndProcessController {

	@Autowired
	private BizTXjZypCxMakeService bizTXjZypCxMakeService;
	@Autowired
	private BizTXjZypCxMakeHisService bizTXjZypCxMakeHisService;
	/**
	 * 跳转到作业票详情和流程页面
	 * @date 2017年9月30日 下午5:41:51
	 * @author 胡毅
	 * @param backURL
	 * @param queryParam
	 * @return
	 */
	@RequestMapping("/init")
	public String init(String backURL,String queryParam,String cxMakeId,ModelMap mod){
		mod.put("queryParam", queryParam);
		mod.put("backURL", backURL);
		mod.put("cxMakeId", cxMakeId);
		return "/WEB-INF/jsp/cxMake/cxMakeDetail/iframe";
	}
	
	@RequestMapping("/detail")
	public String detail(@RequestParam(required = true)String cxMakeId,ModelMap mod){
		mod.put("cxMakeId", cxMakeId);
		CxMakeDetailPojo pojo = bizTXjZypCxMakeService.getZYPDetailForPC(cxMakeId);
		mod.put("zyp", pojo);
		mod.put("TEXT_INPUT_TYPE", Constant.CHECKITEM_INPUTTYPE_VALUE.TEXT.getValue());
		mod.put("NUM_INPUT_TYPE", Constant.CHECKITEM_INPUTTYPE_VALUE.NUM.getValue());
		return "/WEB-INF/jsp/cxMake/cxMakeDetail/detail";
	}
	
	@RequestMapping("/process")
	public String process(@RequestParam(value="cxMakeId",required=true) String cxMakeId,
			HttpServletRequest request ,ModelMap map){
		List<BizTXjZypCxMakeHis> list = bizTXjZypCxMakeHisService.getZypCxMakeHisList(cxMakeId);
		List<Object[]> processNav = new ArrayList<Object[]>();
		List<Map<String,Object>> processContent = new ArrayList<Map<String,Object>>();
		List<String> wholeDates = new ArrayList<String>();
		if(null!=list){
			for (BizTXjZypCxMakeHis t : list) {
				Date createTime = StringUtils.isEmpty(t.getOptTime())?null:DateUtil.stringToDate(t.getOptTime(), "yyyyMMddHHmmss");
				String wholeTime = createTime==null?"":DateUtil.dateToString(createTime, "yyyy-MM-dd HH:mm:ss");
				String dateTime = createTime==null?"":DateUtil.dateToString(createTime, "yyyy-MM-dd");
				String hourTime = createTime==null?"":DateUtil.dateToString(createTime, "HH:mm");
				Object[] arr = new Object[3];
				if(!(wholeDates.contains(dateTime))){
					arr[0] = dateTime;
					wholeDates.add(dateTime);
				}
				arr[1] = hourTime;
				arr[2] = t.getOptType();
				processNav.add(arr);
				Map<String,Object> tmp =  new HashMap<String,Object>();
				if(StringUtils.isBlank(t.getOptDesc())){
					t.setOptDesc(t.getOptType());
				}
				tmp.put("title", t.getOptType());
				tmp.put("createTime", wholeTime);
				tmp.put("creator",t.getOptName());
				tmp.put("optContent", t.getOptContent());
				tmp.put("description", t.getOptDesc());
				processContent.add(tmp);
			}
		}
		map.put("processNav", processNav);
		map.put("processContent", processContent);
		return "/WEB-INF/jsp/cxMake/cxMakeDetail/process";
	}
}
