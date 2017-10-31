package com.upsoft.yxsw.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.upsoft.system.service.BaseServiceImpl;
import com.upsoft.yxsw.constant.Constant;
import com.upsoft.yxsw.dao.BizTGgCheckItemfKxxDAO;
import com.upsoft.yxsw.service.BizTGgCheckItemfKxxService;


@Service
public class BizTGgCheckItemfKxxServiceImpl extends BaseServiceImpl implements BizTGgCheckItemfKxxService {

	@Autowired
	private BizTGgCheckItemfKxxDAO bizTGgCheckItemfKxxDAO;

	@Override
	public List<Map<String, Object>> getKxx() {
		StringBuilder sql = new StringBuilder();
		sql.append("( select t.itemf_kxx_id id, '' code, '' parentid, t.kxx_value name");
		sql.append("    from biz_t_gg_check_itemf_kxx t");
		sql.append("   where t.is_valid = '"+Constant.YES_NO.YES.getValue()+"') ");
		return bizTGgCheckItemfKxxDAO.queryListBySql(sql.toString(), new HashMap<String,Object>());
	}
	
}
