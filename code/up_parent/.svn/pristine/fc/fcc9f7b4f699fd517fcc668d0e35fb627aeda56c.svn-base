package com.upsoft.yxsw.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.upsoft.system.service.BaseServiceImpl;
import com.upsoft.yxsw.dao.BizTXjCxTaskItemRstDetDAO;
import com.upsoft.yxsw.entity.BizTXjCxTaskItemRstDet;
import com.upsoft.yxsw.mobile.bean.execute.CheckItemDetailBean;
import com.upsoft.yxsw.service.BizTXjCxTaskItemRstDetService;


@Service
public class BizTXjCxTaskItemRstDetServiceImpl extends BaseServiceImpl implements BizTXjCxTaskItemRstDetService {

	@Autowired
	private BizTXjCxTaskItemRstDetDAO bizTXjCxTaskItemRstDetDAO;
	
	@Override
	public List<CheckItemDetailBean> getTaskItemRstDetListByRstId(String sbssRstId) {
		String sql = "select t.*,t.rowid from  Biz_t_Xj_Cx_Task_Item_Rst_Det  t where t.sbss_rst_id='"+sbssRstId+"'";
		List<BizTXjCxTaskItemRstDet> rstDetList = bizTXjCxTaskItemRstDetDAO.queryListBySql(sql, new HashMap<String,Object>(), BizTXjCxTaskItemRstDet.class);
		List<CheckItemDetailBean> list = new ArrayList<CheckItemDetailBean>();
		for (BizTXjCxTaskItemRstDet rstDet : rstDetList) {
			CheckItemDetailBean bean = new CheckItemDetailBean();
			bean.setSelName(rstDet.getSelName());
			bean.setSelValue(rstDet.getSelValue());
			bean.setSfmr(rstDet.getSfmr());
			bean.setSfzc(rstDet.getSfzc());
			list.add(bean);
		}
		return list;
	}
	
}
