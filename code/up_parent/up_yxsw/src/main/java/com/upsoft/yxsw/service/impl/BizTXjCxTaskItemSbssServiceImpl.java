package com.upsoft.yxsw.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.upsoft.login.support.webservice.ServiceReceiver;
import com.upsoft.system.entity.ComTAttachment;
import com.upsoft.system.service.AttachmentService;
import com.upsoft.system.service.BaseServiceImpl;
import com.upsoft.system.util.DateUtil;
import com.upsoft.yxsw.constant.Constant;
import com.upsoft.yxsw.constant.DictConstant;
import com.upsoft.yxsw.controller.cxMake.bean.WriteCxMakeSBPojo;
import com.upsoft.yxsw.dao.BizTXjCxTaskItemSbssDAO;
import com.upsoft.yxsw.entity.BizTXjCxTaskItemSbss;
import com.upsoft.yxsw.mobile.bean.AttachmentPathAndNameBean;
import com.upsoft.yxsw.mobile.bean.execute.CheckItemBean;
import com.upsoft.yxsw.mobile.bean.execute.ExecuteSBSSBean;
import com.upsoft.yxsw.mobile.bean.task.SbssListBean;
import com.upsoft.yxsw.mobile.bean.task.WaningListBean;
import com.upsoft.yxsw.service.BizTGgWarningManageService;
import com.upsoft.yxsw.service.BizTSsBaseinfoService;
import com.upsoft.yxsw.service.BizTXjCxTaskItemSbssRstService;
import com.upsoft.yxsw.service.BizTXjCxTaskItemSbssService;


@Service
public class BizTXjCxTaskItemSbssServiceImpl extends BaseServiceImpl implements BizTXjCxTaskItemSbssService {
	
	@Autowired
	private BizTXjCxTaskItemSbssDAO bizTXjCxTaskItemSbssDAO;
	@Autowired
	private BizTGgWarningManageService bizTGgWarningManageService;
	@Autowired
	private AttachmentService attachmentService;
	@Autowired
	private BizTSsBaseinfoService bizTSsBaseinfoService;
	@Autowired
	private BizTXjCxTaskItemSbssRstService bizTXjCxTaskItemSbssRstService;
	@Autowired
	private JdbcTemplate jdbcTemplateZK;
	
	private String NVL(Object o){ return null!=o?o.toString():""; }
	
	public List<BizTXjCxTaskItemSbss> getEntityListByTaskItemId(String taskItemId){
		String sql = "SELECT * FROM BIZ_T_XJ_CX_TASK_ITEM_SBSS T WHERE T.TASK_ITEM_ID='"+taskItemId+"'";
		return bizTXjCxTaskItemSbssDAO.queryListBySql(sql, new HashMap<String,Object>(), BizTXjCxTaskItemSbss.class);
	}

	@Override
	public List<SbssListBean> getBeanListByTaskItemId(String taskItemId) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT T.TTASK_ITEM_SBSS_ID, T.TASK_ITEM_ID, T.DETAIL_TYPE, T.SBSS_ID, T.NAME, T.MUST_SCAN, T.EWM_CONFIRMED_TYPE, T.HAVE_COMPLETE, T.OPT_TIME, T.BYZD, T.SF_FAULT, T.XJ_DESC, ");
		sql.append(" CASE WHEN T.DETAIL_TYPE ='1' THEN SB.SB_CODE ELSE SS.CODE END AS  SBSSCODE,");
		sql.append(" CASE WHEN T.DETAIL_TYPE ='1' THEN SB.SB_SORT ELSE '' END AS  SBSORT,");
		sql.append(" CASE WHEN T.DETAIL_TYPE='1' THEN (SELECT TY.NAME FROM BIZ_T_SB_TYPES TY WHERE TY.SB_TYPE_ID=SB.SB_TYPE_ID) ELSE '' END AS SBTYPENAME,");
		sql.append(" CASE WHEN T.DETAIL_TYPE ='1' THEN  SB.SBXH ELSE '' END AS  SBXH,");
		sql.append(" CASE WHEN T.DETAIL_TYPE ='1' THEN ZY_STATUS ELSE '' END AS  ZYSTATUS,");
		sql.append(" CASE WHEN T.DETAIL_TYPE='1' THEN ''  ELSE SS.LAYER END AS SSLAYER");
		sql.append(" FROM BIZ_T_XJ_CX_TASK_ITEM_SBSS ");
		sql.append("  T LEFT JOIN BIZ_T_SB_BASEINFO SB ON SB.SB_ID=T.SBSS_ID ");
		sql.append("  LEFT JOIN BIZ_T_SS_BASEINFO SS ON SS.SS_ID=T.SBSS_ID ");
		sql.append(" WHERE T.TASK_ITEM_ID ='"+taskItemId+"'");
		List<Map<String,Object>> list = bizTXjCxTaskItemSbssDAO.queryListBySql(sql.toString(), new HashMap<String,Object>());
		List<SbssListBean> result = new ArrayList<SbssListBean>();
		for (Map<String, Object> map : list) {
			SbssListBean tmp = new SbssListBean();
			tmp.setTtaskItemSbssId(NVL(map.get("ttask_item_sbss_id")));
			tmp.setTaskItemId(NVL(map.get("task_item_id")));
			tmp.setDetailType(NVL(map.get("detail_type")));
			tmp.setSbssId(NVL(map.get("sbss_id")));
			tmp.setName(NVL(map.get("name")));
			tmp.setMustScan(NVL(map.get("must_scan")));
			tmp.setEwmConfirmedType(NVL(map.get("ewm_confirmed_type")));
			tmp.setHaveComplete(NVL(map.get("have_complete")));
			tmp.setOptTime(NVL(map.get("opt_time")));
			tmp.setSfFault(NVL(map.get("sf_fault")));
			tmp.setXjDesc(NVL(map.get("xj_desc")));
			tmp.setSbssCode(NVL(map.get("sbsscode")));
			tmp.setSbSort(NVL(map.get("sbsort")));
			tmp.setSbTypeName(NVL(map.get("sbtypename")));
			tmp.setSbxh( NVL(map.get("sbxh")));
			String zyStatus = NVL(map.get("zystatus"));
			tmp.setZyStatusName(NVL(ServiceReceiver.getDictValue1(DictConstant.ZY_STATUS.getValue(), zyStatus).get(zyStatus)));
			tmp.setSsLayer(NVL(bizTSsBaseinfoService.getSSLayer(NVL(map.get("sslayer"))).get("layername")));
			result.add(tmp);
		}
		
		return result;
	}
	
	@Override
	public Map<String, Object> updateAndGetWarningOfThis(String ttaskItemSbssId, String confirmType, String basePath) {
		// 更新记录
		BizTXjCxTaskItemSbss entity = findOne(BizTXjCxTaskItemSbss.class, ttaskItemSbssId);
		entity.setEwmConfirmedType(StringUtils.isNotBlank(confirmType)?Constant.RFID_CONFIRMED_TYPE.SCANNED.getValue():Constant.RFID_CONFIRMED_TYPE.CANNOT_SCAN.getValue());
		entity.setOptTime(DateUtil.dateToString(new Date(), DateUtil.DATE_FULL_FORMAT_N));
		this.update(entity);
		// 获取安全提醒
		List<WaningListBean> warningBeanList = new ArrayList<WaningListBean>();
		String sbssId = entity.getSbssId();
		List<Map<String, Object>> warningList = bizTGgWarningManageService.getWaningListByTxType(sbssId,entity.getDetailType());
		if(warningList.size()>0){
			List<String> warningIdList = new ArrayList<String>();
			for (Map<String, Object> warning : warningList) {
				WaningListBean wanBean = new WaningListBean();
				warningIdList.add(warning.get("warning_id").toString());
				wanBean.setWarningId(warning.get("warning_id").toString());
				wanBean.setTitle(warning.get("title").toString());
				wanBean.setContent((null!=warning.get("content")?warning.get("content").toString():""));
				wanBean.setTitleIco((null!=warning.get("title_icon")?warning.get("title_icon").toString():""));
				wanBean.setTxType(warning.get("tx_type").toString());
				wanBean.setIsHaveRead((null!=warning.get("is_have_read")?warning.get("is_have_read").toString():""));
				wanBean.setIsImportant((null!=warning.get("is_important")?warning.get("is_important").toString():""));
				warningBeanList.add(wanBean);
			}
			List<ComTAttachment> attachments = attachmentService.getAttachmentList(warningIdList);
			Map<String,List<AttachmentPathAndNameBean>> attachmentMap = new HashMap<String,List<AttachmentPathAndNameBean>>();
			for (ComTAttachment atta : attachments) {
				List<AttachmentPathAndNameBean> tmpList = null;
				if(attachmentMap.containsKey(atta.getBusinessId())){
					tmpList = attachmentMap.get(atta.getBusinessId());
				}else{
					tmpList = new ArrayList<AttachmentPathAndNameBean>();
				}
				AttachmentPathAndNameBean tmp = new AttachmentPathAndNameBean();
				tmp.setName(atta.getOldAttachmentName()+"."+atta.getAttachmentSuffix());
				tmp.setPath(basePath+atta.getAttachmentPath());
				tmpList.add(tmp);
				attachmentMap.put(atta.getBusinessId(), tmpList);
			}
			for (WaningListBean warningBean : warningBeanList) {
				warningBean.setAttachPathNameList(attachmentMap.get(warningBean.getWarningId()));
			}
		}
		Map<String, Object> data = new HashMap<String,Object>();
		data.put("warningList", warningBeanList);
		return data;
	}

	@Override
	public ExecuteSBSSBean getSBssExcuteInfo(String ttaskItemSbssId,String basePath) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT T.TTASK_ITEM_SBSS_ID, T.TASK_ITEM_ID, T.SBSS_ID, T.NAME,");
		sql.append("  (SELECT COUNT(0) FROM BIZ_T_XJ_CX_TASK_ITEM_SBSS TMP WHERE TMP.SBSS_ID = T.SBSS_ID  AND TMP.HAVE_COMPLETE='"+Constant.YES_NO.YES.getValue()+"' ) COUNT,");
		sql.append("       TI.XJD_ITEM_NAME,T.XJ_DESC");
		sql.append("  FROM BIZ_T_XJ_CX_TASK_ITEM_SBSS T");
		sql.append("  LEFT JOIN BIZ_T_XJ_CX_TASK_ITEM TI");
		sql.append("    ON TI.TASK_ITEM_ID = T.TASK_ITEM_ID");
		sql.append(" WHERE 1=1 AND T.ttask_item_sbss_id = '"+ttaskItemSbssId+"'");
		List<Map<String,Object>> baseInfo = bizTXjCxTaskItemSbssDAO.queryListBySql(sql.toString(), new HashMap<String,Object>());
		ExecuteSBSSBean result = new ExecuteSBSSBean();
		if(baseInfo.size()==0){
			return result;
		}
		if(null!=baseInfo){
			Map<String,Object> item = baseInfo.get(0);
			result.setTtaskItemSbssId(NVL(item.get("TTASK_ITEM_SBSS_ID".toLowerCase())));
			result.setTaskItemId(NVL(item.get("TASK_ITEM_ID".toLowerCase())));
			result.setSbssId(NVL(item.get("sbss_id")));
			result.setName(NVL(item.get("name")));
			result.setCount(Long.valueOf(NVL(item.get("count"))));
			result.setXjdItemName(NVL(item.get("XJD_ITEM_NAME".toLowerCase())));
			result.setSbssCxDesc(NVL(item.get("xj_desc")));
			// 获取设备设施记录的附件记录
			List<ComTAttachment> attachList = attachmentService.getAttachmentList(result.getTtaskItemSbssId());
			List<AttachmentPathAndNameBean> attachPathAndNameList = new ArrayList<AttachmentPathAndNameBean>();
			for (ComTAttachment atta : attachList) {
				AttachmentPathAndNameBean tmp = new AttachmentPathAndNameBean();
				tmp.setName(atta.getOldAttachmentName()+"."+atta.getAttachmentSuffix());
				tmp.setPath(basePath+atta.getAttachmentPath());
				attachPathAndNameList.add(tmp);
			}
			result.setImgList(attachPathAndNameList);
		}
		// 获取检查项内容
		List<CheckItemBean> checkItemList = bizTXjCxTaskItemSbssRstService.getListByTaskItemSbssId(ttaskItemSbssId);
		result.setCheckItemList(checkItemList);
		return result;
	}

	@Override
	public List<CheckItemBean> getCheckItemZKData(String sbssId, String checkItemListJson) {
		List<String> checkItemList = new Gson().fromJson(checkItemListJson,new TypeToken<List<String>>() {}.getType());
		List<CheckItemBean> result = new ArrayList<CheckItemBean>();
		// 获取隐射表中中控tagindex
		String sql = "SELECT T.JCX_ID ,T.TAGINDEX FROM BIZ_T_ZB_CJ T WHERE T.SB_ID='"+sbssId+"' AND T.JCX_ID IN (:checkItemList)";
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("checkItemList", checkItemList);
		List<Map<String,Object>> list1 = bizTXjCxTaskItemSbssDAO.queryListBySql(sql, params);
		if(list1.size()==0){
			return result;
		}
		Map<String,Object> tagIndexCheckItemIdMap = new HashMap<String,Object>();
		List<String> tagIndexList = new ArrayList<String>();
		for (Map<String, Object> map : list1) {
			tagIndexList.add(NVL(map.get("tagindex")));
			tagIndexCheckItemIdMap.put(NVL(map.get("tagindex")), map.get("jcx_id"));
		}
		// 获取中控数据值
		String subSql = "";
		for (String tagIndex : tagIndexList) {
			subSql += "'"+tagIndex+"',";
		}
		String sql2  = "SELECT T.TAGINDEX ,T.VAL FROM REALLIST T WHERE T.TAGINDEX IN ("+subSql.substring(0, subSql.length()-1)+")";
		List<Map<String,Object>> vals = jdbcTemplateZK.queryForList(sql2,new Object[]{});
		if(vals.size()==0){
			return result;
		}
		for (Map<String, Object> map : vals) {
			CheckItemBean bean = new CheckItemBean();
			bean.setCheckItemId(NVL(tagIndexCheckItemIdMap.get(NVL(map.get("TAGINDEX")))));
			bean.setCheckItemValue(NVL(map.get("VAL")));
			result.add(bean);
		}
		
		return result;
	}
}
