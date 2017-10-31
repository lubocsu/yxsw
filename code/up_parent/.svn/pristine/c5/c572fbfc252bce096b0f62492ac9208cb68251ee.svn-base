package com.upsoft.yxsw.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.upsoft.system.service.BaseServiceImpl;
import com.upsoft.yxsw.constant.Constant;
import com.upsoft.yxsw.dao.BizTGgCheckItemfKxxSelDAO;
import com.upsoft.yxsw.service.BizTGgCheckItemfKxxSelService;


@Service
public class BizTGgCheckItemfKxxSelServiceImpl extends BaseServiceImpl implements BizTGgCheckItemfKxxSelService {
	
	@Autowired
	private BizTGgCheckItemfKxxSelDAO bizTGgCheckItemfKxxSelDAO;
	
	@Override
	public List<Map<String, Object>> getKxxAndKxxSEL() {
		StringBuilder sql = new StringBuilder();
		sql.append("( select t.itemf_kxx_id id, '' code, '' parentid, t.kxx_value name");
		sql.append("    from biz_t_gg_check_itemf_kxx t");
		sql.append("   where t.is_valid = '1') ");
		sql.append("  union all");
		sql.append("   (select t2.id           id,");
		sql.append("           t2.sel_value    code,");
		sql.append("           t2.itemf_kxx_id parentid,");
		sql.append("           t2.sel_name     name");
		sql.append("      from biz_t_gg_check_itemf_kxx_sel t2");
		sql.append("     where t2.itemf_kxx_id in");
		sql.append("           (select itemf_kxx_id from biz_t_gg_check_itemf_kxx where is_valid = '"+Constant.YES_NO.YES.getValue()+"'))");
		return bizTGgCheckItemfKxxSelDAO.queryListBySql(sql.toString(), new HashMap<String,Object>());
	}

	@Override
	public List<Map<String, Object>> getCheckItemSelKxxByParentId(String parentId) {
		String sql = "select id, itemf_kxx_id parentId, sel_value value, sel_name name, sel_value2 value2, sel_value3 value3, sel_sort sort from biz_t_gg_check_itemf_kxx_sel"
				+ " where itemf_kxx_id='"+parentId+"'"
				+ " order by sel_sort asc";
		return bizTGgCheckItemfKxxSelDAO.queryListBySql(sql, new HashMap<String,Object>());
	}

}
