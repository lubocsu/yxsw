package com.upsoft.yxsw.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.upsoft.system.service.BaseServiceImpl;
import com.upsoft.yxsw.dao.BizTXjZbPlanDetailPersonDao;
import com.upsoft.yxsw.dao.BizTXjZypCxMakePersonKhDAO;
import com.upsoft.yxsw.entity.BizTXjZypCxMakePersonKh;
import com.upsoft.yxsw.service.BizTXjZbPlanDetailPersonService;


@Service
public class BizTXjZbPlanDetailPersonServiceImpl extends BaseServiceImpl implements BizTXjZbPlanDetailPersonService {

	@Autowired
	private BizTXjZbPlanDetailPersonDao bizTXjZbPlanDetailPersonDao;
	@Autowired
	private BizTXjZypCxMakePersonKhDAO bizTXjZypCxMakePersonKhDAO;
	
	@Override
	public List<BizTXjZypCxMakePersonKh> getPersonListByWscIdAndZypDate(String cxMakeId,String belongWscId, String zypDate) {
		Map<String,Object> params = new HashMap<String,Object>();
		List<BizTXjZypCxMakePersonKh> pseronKhList = bizTXjZypCxMakePersonKhDAO.queryListBySql("SELECT * FROM BIZ_T_XJ_ZYP_CX_MAKE_PERSON_KH WHERE CX_MAKE_ID='"+cxMakeId+"' ", params, BizTXjZypCxMakePersonKh.class);
		if(pseronKhList.size()==0){
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT DISTINCT T.PERSON_ID,T.PERSON_NAME");
			sql.append("  FROM BIZ_T_XJ_ZB_PLAN_DETAIL_PERSON T");
			sql.append(" WHERE T.PLAN_DETAIL_ID IN");
			sql.append("       (SELECT D.PLAN_DETAIL_ID");
			sql.append("          FROM BIZ_T_XJ_ZB_PLAN_DETAIL D");
			sql.append("         WHERE D.ZB_PLAN_ID = (SELECT P.ZB_PLAN_ID");
			sql.append("                                 FROM BIZ_T_XJ_ZB_PLAN P");
			sql.append("                                WHERE P.ZB_DATE = '"+zypDate+"'");
			sql.append("                                  AND P.BELONG_WSC_ID = '"+belongWscId+"'))");
			List<Map<String,Object>> personMapList = bizTXjZbPlanDetailPersonDao.queryListBySql(sql.toString(),params);
			for (Map<String, Object> map : personMapList) {
				BizTXjZypCxMakePersonKh tmp = new BizTXjZypCxMakePersonKh();
				tmp.setBkhId(map.get("person_id").toString());
				tmp.setBkhName(map.get("person_name").toString());
				tmp.setCxMakeId(cxMakeId);
				pseronKhList.add(tmp);
			}
		}
		return pseronKhList;
	}
	
}
