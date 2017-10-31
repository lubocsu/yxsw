package com.upsoft.yxsw.service.impl;


import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.upsoft.login.support.webservice.ServiceReceiver;
import com.upsoft.system.bean.WSLoginInfoBean;
import com.upsoft.system.service.BaseServiceImpl;
import com.upsoft.system.util.DateUtil;
import com.upsoft.yxsw.constant.DictConstant;
import com.upsoft.yxsw.dao.BizTXjZypCxMakeHisDao;
import com.upsoft.yxsw.entity.BizTXjZypCxMakeHis;
import com.upsoft.yxsw.service.BizTXjZypCxMakeHisService;


@Service
public class BizTXjZypCxMakeHisServiceImpl extends BaseServiceImpl implements BizTXjZypCxMakeHisService {

	@Autowired
	private BizTXjZypCxMakeHisDao bizTXjZypCxMakeHisDao;
	
	@Override
	public List<BizTXjZypCxMakeHis> getZypCxMakeHisList(String cxMakeId) {
		
		String sql = " SELECT T.HIS_ID,T.CX_MAKE_ID,T.OPT_ID,T.OPT_NAME,T.OPT_TYPE,T.OPT_CONTENT,T.OPT_DESC,T.OPT_TIME FROM BIZ_T_XJ_ZYP_CX_MAKE_HIS T WHERE T.CX_MAKE_ID='"+ cxMakeId +"' ORDER BY T.OPT_TIME ASC";
		//作业票操作类型
		List<Map<String,Object>> zypOptType = ServiceReceiver.getDictSelect(DictConstant.ZYP_OPT_TYPE.getValue());
		Map<String,Object> zypOptTypeMap = new HashMap<String,Object>();
		for (Map<String, Object> maps : zypOptType) {
			zypOptTypeMap.put(maps.get("key").toString(), maps.get("value"));
		}
		List<BizTXjZypCxMakeHis> result = bizTXjZypCxMakeHisDao.queryListBySql(sql, new HashMap<String,Object>(), BizTXjZypCxMakeHis.class );
		if(null != result && result.size() > 0){
			for (BizTXjZypCxMakeHis bizTXjZypCxMakeHis : result) {
				bizTXjZypCxMakeHis.setOptType(zypOptTypeMap.get(bizTXjZypCxMakeHis.getOptType()).toString());
			}
		}
		return result;
	}

	@Override
	public void saveProcess(String cxMakeId, String radio, String content,String optType,WSLoginInfoBean loginInfo) {
		BizTXjZypCxMakeHis bizTXjZypCxMakeHis =  new BizTXjZypCxMakeHis();
		Date date = new Date();
		bizTXjZypCxMakeHis.setCxMakeId(cxMakeId);
		bizTXjZypCxMakeHis.setOptContent(radio);
		bizTXjZypCxMakeHis.setOptDesc(content);
		bizTXjZypCxMakeHis.setOptType(optType);
		bizTXjZypCxMakeHis.setOptId(loginInfo.getUser().getUserId());
		bizTXjZypCxMakeHis.setOptName(loginInfo.getUser().getUserName());
		bizTXjZypCxMakeHis.setOptTime(DateUtil.dateToString(date, "yyyyMMddHHmmss"));
		
		bizTXjZypCxMakeHisDao.save(bizTXjZypCxMakeHis);
	}
	
}
