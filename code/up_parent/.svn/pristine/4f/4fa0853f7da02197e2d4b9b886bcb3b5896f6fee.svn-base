package com.upsoft.yxsw.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.upsoft.login.support.webservice.ServiceReceiver;
import com.upsoft.system.service.BaseServiceImpl;
import com.upsoft.yxsw.constant.DictConstant;
import com.upsoft.yxsw.dao.BizTGgCheckItemDetailDAO;
import com.upsoft.yxsw.entity.BizTGgCheckItemDetail;
import com.upsoft.yxsw.service.BizTGgCheckItemDetailService;


@Service
public class BizTGgCheckItemDetailServiceImpl extends BaseServiceImpl implements BizTGgCheckItemDetailService {
	
	private static final Logger logger = Logger.getLogger(BizTGgCheckItemDetailServiceImpl.class);
	
	@Autowired
	private BizTGgCheckItemDetailDAO bizTGgCheckItemDetailDAO;
	
	public BizTGgCheckItemDetail save(BizTGgCheckItemDetail bizTGgCheckItemDetail){
		
		return bizTGgCheckItemDetailDAO.save(bizTGgCheckItemDetail);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, Object>> getListByCheckItemId(String checkItemId) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT T.DETAIL_ID, T.CHECK_ITEM_ID, T.SEL_VALUE, T.SEL_NAME, T.SEL_SORT, T.SFZC, T.SFMR FROM BIZ_T_GG_CHECK_ITEM_DETAIL T WHERE T.CHECK_ITEM_ID='"+checkItemId+"'");
		List<BizTGgCheckItemDetail> list = bizTGgCheckItemDetailDAO.queryListBySql(sql.toString(),new HashMap<String,Object>(), BizTGgCheckItemDetail.class);
		List<Map<String,Object>> checkItemSFYCDictList = ServiceReceiver.getDictSelect(DictConstant.CHECKITEM_SFYC.getValue());
		List<Map<String,Object>> checkItemSFMRDictList = ServiceReceiver.getDictSelect(DictConstant.CHECKITEM_SFMR.getValue());
		List<Map<String,Object>> result = new ArrayList<Map<String,Object>>();
		for (BizTGgCheckItemDetail entity : list) {
			try {
				Map<String,Object> map = BeanUtils.describe(entity);
				for (Map<String, Object> sfycDict : checkItemSFYCDictList) {
					if(StringUtils.equals(entity.getSfzc().toString(), sfycDict.get("key").toString())){
						map.put("sfzcName", sfycDict.get("value"));
						break;
					}
				}
				for (Map<String, Object> sfmrDict : checkItemSFMRDictList) {
					if(StringUtils.equals(entity.getSfmr().toString(), sfmrDict.get("key").toString())){
						map.put("sfmrName", sfmrDict.get("value"));
						break;
					}
				}
				result.add(map);
			} catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
				logger.error("查询检查项明细时，实体转换为Map出错："+e.getMessage());
			}
		}
		return result;
	}

	@Override
	public List<BizTGgCheckItemDetail> getListByCheckItemId(String checkItemId, Boolean _null) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT T.DETAIL_ID, T.CHECK_ITEM_ID, T.SEL_VALUE, T.SEL_NAME, T.SEL_SORT, T.SFZC, T.SFMR FROM BIZ_T_GG_CHECK_ITEM_DETAIL T WHERE T.CHECK_ITEM_ID='"+checkItemId+"'");
		List<BizTGgCheckItemDetail> list = bizTGgCheckItemDetailDAO.queryListBySql(sql.toString(),new HashMap<String,Object>(), BizTGgCheckItemDetail.class);
		return list;
	}

	@Override
	public void deleteByCheckItemId(String checkItemId) {
		String sql = "DELETE BIZ_T_GG_CHECK_ITEM_DETAIL T WHERE T.CHECK_ITEM_ID='"+checkItemId+"'";
		bizTGgCheckItemDetailDAO.executeSql(sql, new HashMap<String,Object>());
	}
	
}
