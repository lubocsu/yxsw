package com.upsoft.yxsw.service.impl;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.upsoft.system.bean.PageBean;
import com.upsoft.system.service.BaseServiceImpl;
import com.upsoft.yxsw.dao.BizTXjFreqSegmentSbssDao;
import com.upsoft.yxsw.entity.BizTSbssRelation;
import com.upsoft.yxsw.entity.BizTXjFreqSegmentSbss;
import com.upsoft.yxsw.service.BizTXjFreqSegmentSbssService;

@Service
public class BizTXjFreqSegmentSbssServiceImpl extends BaseServiceImpl implements BizTXjFreqSegmentSbssService {
	
	@Autowired
	private BizTXjFreqSegmentSbssDao freqSegmentSbssDao;
	
	public Map<String, Object> getEqList(PageBean bean, Map<String, Object> params){
		
		StringBuilder sql = new StringBuilder();
		Map<String, Object> paramsMap = new HashMap<String,Object>();
		sql.append("SELECT S.FREQ_SEGMENT_SBSS_ID, S.XJD_ITEM_NAME , S.TECHNICS_NAME, B.SB_ID, B.SB_CODE,B.SB_NAME,B.SB_SORT,B.SB_TYPE_ID,B.SET_ADDRESS,B.ZY_STATUS, B.GCJK, B.SBXH, B.XNCS, T.NAME SB_TYPE_NAME");
		sql.append(" FROM BIZ_T_XJ_FREQ_SEGMENT_SBSS S LEFT JOIN BIZ_T_SB_BASEINFO B ON S.SBSS_ID = B.SB_ID ");
		sql.append(" LEFT JOIN BIZ_T_SB_TYPES T ON T.SB_TYPE_ID = B.SB_TYPE_ID ");
		sql.append(" WHERE 1=1 AND B.ZY_STATUS = '02' AND B.DEL_FLAG = '0' ");	//在用&未删除
		
		if(null != params.get("sbssType") && StringUtils.isNotBlank(params.get("sbssType").toString())){
			sql.append("  AND S.SBSS_TYPE = :sbssType ");
			paramsMap.put("sbssType", params.get("sbssType").toString());
		}
		if(null != params.get("freqId") && StringUtils.isNotBlank(params.get("freqId").toString())){
			sql.append("  AND S.FREQ_SEGMENT_ID = :freqId ");
			paramsMap.put("freqId", params.get("freqId").toString());
		}
		if(null != params.get("sbName") && StringUtils.isNotBlank(params.get("sbName").toString())){
			sql.append(" AND B.SB_NAME LIKE :sbName ");
			paramsMap.put("sbName", MessageFormat.format("%{0}%", params.get("sbName").toString()));
		}
		
		return freqSegmentSbssDao.queryPaginationListBySql(sql.toString(), paramsMap, bean);
	}
	
	public Map<String, Object> getSsList(PageBean bean, Map<String, Object> params){
		
		StringBuilder sql = new StringBuilder();
		Map<String, Object> paramsMap = new HashMap<String,Object>();
		sql.append("SELECT S.FREQ_SEGMENT_SBSS_ID, S.XJD_ITEM_NAME, S.TECHNICS_NAME, B.SS_ID, B.CODE, B.NAME, B.FUNCTION, B.LAYER");
		sql.append(" FROM BIZ_T_XJ_FREQ_SEGMENT_SBSS S LEFT JOIN BIZ_T_SS_BASEINFO B ON S.SBSS_ID = B.SS_ID ");
		sql.append(" WHERE 1=1 ");
		
		if(null != params.get("sbssType") && StringUtils.isNotBlank(params.get("sbssType").toString())){
			sql.append("  AND S.SBSS_TYPE = :sbssType ");
			paramsMap.put("sbssType", params.get("sbssType").toString());
		}
		if(null != params.get("freqId") && StringUtils.isNotBlank(params.get("freqId").toString())){
			sql.append("  AND S.FREQ_SEGMENT_ID = :freqId ");
			paramsMap.put("freqId", params.get("freqId").toString());
		}
		
		return freqSegmentSbssDao.queryPaginationListBySql(sql.toString(), paramsMap, bean);
	}
	
	/**
	 * XXXXXXXXXXXXXXXXXXXXXXXXXXXX
	 * @date 2017-09-20
	 * @author 
	 * @param order
	 * @return 
	 */
	public BizTXjFreqSegmentSbss save(BizTXjFreqSegmentSbss bizTXjFreqSegmentSbss){
		return bizTXjFreqSegmentSbss;
	}
	
	
	/**
	 * XXXXXXXXXXXXXXX
	 * @date 2017-09-20
	 * @author 
	 * @param 
	 * @return 
	 */
	public BizTXjFreqSegmentSbss getBizTXjFreqSegmentSbssById(String id){
		return null;
	}

	@Override
	public List<BizTXjFreqSegmentSbss> saveBizTXjFreqSegmentSbssList(List<BizTXjFreqSegmentSbss> sbssList) {
		
		return freqSegmentSbssDao.save(sbssList);
	}

	@Override
	public void delFreqSegmentSbss(String ferqSbssId) {
		
		freqSegmentSbssDao.delete(findOne(BizTXjFreqSegmentSbss.class, ferqSbssId));
	}

	@Override
	public Boolean delAll(String ids) {
		return freqSegmentSbssDao.deleteByIds(BizTXjFreqSegmentSbss.class, ids);
	}
}