package com.upsoft.yxsw.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.upsoft.system.bean.PageBean;
import com.upsoft.system.constant.CommonConstant;
import com.upsoft.system.entity.SysUser;
import com.upsoft.system.service.BaseServiceImpl;
import com.upsoft.system.util.DateUtil;
import com.upsoft.yxsw.constant.DictConstant;
import com.upsoft.yxsw.dao.EquipmentFactoryDao;
import com.upsoft.yxsw.entity.BizTBaseFactoryInfoEntity;
import com.upsoft.yxsw.service.EquipmentFactoryService;

@Service
public class EquipmentFactoryServiceImpl extends BaseServiceImpl implements EquipmentFactoryService{

	@Autowired
	private EquipmentFactoryDao equipmentFactoryDao;
	
	@Override
	public Map<String, Object> getEqFactoryData(PageBean pageBean,
			Map<String, Object> params) {
		
		StringBuffer sql = new StringBuffer();
		sql.append(" select d1.data1 typename,d2.data1 xzname,d3.data1 outservicename,t.* from biz_t_base_factory_info t ");
		sql.append(" left join sys_dict_tree_data d1 on d1.parentnodeid='"+DictConstant.FACTORY_TYPE.getValue()+"' and d1.code = t.type ");
		sql.append(" left join sys_dict_tree_data d2 on d2.parentnodeid='"+DictConstant.FACTORY_XZ.getValue()+"' and d2.code = t.zjxz ");
		sql.append(" left join sys_dict_tree_data d3 on d3.parentnodeid='"+DictConstant.CHECKITEM_SFMR.getValue()+"' and d3.code = t.out_service ");
		sql.append(" WHERE 1=1 ");

		if(null != params){
			if(params.containsKey("name")){
				sql.append(" AND T.NAME LIKE '%"+params.get("name").toString()+"%' ");
			}
			if(params.containsKey("outService")){
				sql.append(" AND T.OUT_SERVICE = '"+ params.get("outService").toString() +"' ");
			}
			if(params.containsKey("factoryXz")){
				sql.append(" AND T.ZJXZ = '"+ params.get("factoryXz").toString() +"' ");
			}
			if(params.containsKey("factoryType")){
				sql.append(" AND T.TYPE = '"+ params.get("factoryType").toString() +"' ");
			}
		}
		
		sql.append(" ORDER BY T.CREATE_TIMESTEMP DESC ");
		
		return equipmentFactoryDao.queryPaginationListBySql(sql.toString(), new HashMap<String,Object>(), pageBean);
	}

	@Override
	public void saveEquipmentFactory(
			BizTBaseFactoryInfoEntity bizTBaseFactoryInfoEntity, SysUser user) {
		String userId = user.getUserId();
		String userName = user.getUserName();
		bizTBaseFactoryInfoEntity.setCreatorAccount(userId);
		bizTBaseFactoryInfoEntity.setCreatorName(userName);
		bizTBaseFactoryInfoEntity.setUpdatorAccount(userId);
		bizTBaseFactoryInfoEntity.setUpdatorName(userName);
		Date date = new Date();
		bizTBaseFactoryInfoEntity.setCreateTimestemp(DateUtil.dateToString(date, "yyyyMMddHHmmss"));
		bizTBaseFactoryInfoEntity.setUpdateTimestemp(DateUtil.dateToString(date, "yyyyMMddHHmmss"));
		
		equipmentFactoryDao.save(bizTBaseFactoryInfoEntity);
	}

	@Override
	public Map<String, Object> findOneById(String id) {
		StringBuffer sql = new StringBuffer();
		sql.append(" select d1.data1 typename,d2.data1 xzname,d3.data1 outservicename,t.* from biz_t_base_factory_info t ");
		sql.append(" left join sys_dict_tree_data d1 on d1.parentnodeid='"+DictConstant.FACTORY_TYPE.getValue()+"' and d1.code = t.type ");
		sql.append(" left join sys_dict_tree_data d2 on d2.parentnodeid='"+DictConstant.FACTORY_XZ.getValue()+"' and d2.code = t.zjxz ");
		sql.append(" left join sys_dict_tree_data d3 on d3.parentnodeid='"+DictConstant.CHECKITEM_SFMR.getValue()+"' and d3.code = t.out_service ");
		sql.append(" WHERE 1=1 ");
		sql.append(" and t.id = '"+id+"'");
		List<Map<String,Object>> result  = equipmentFactoryDao.queryListBySql(sql.toString(), new HashMap<String,Object>());
		if(null != result && result.size()>0){
			return result.get(0);
		}else{
			return null;
		}
	}

	@Override
	public void updateEquipmentFactory(
			BizTBaseFactoryInfoEntity bizTBaseFactoryInfoEntity, SysUser user) {
		
		String userId = user.getUserId();
		String userName = user.getUserName();
		bizTBaseFactoryInfoEntity.setUpdatorAccount(userId);
		bizTBaseFactoryInfoEntity.setUpdatorName(userName);
		Date date = new Date();
		bizTBaseFactoryInfoEntity.setUpdateTimestemp(DateUtil.dateToString(date, "yyyyMMddHHmmss"));
		
		equipmentFactoryDao.save(bizTBaseFactoryInfoEntity);
	}

	@Override
	public int updateFactoryByIds(List<String> idArray,SysUser user) {
		int result = 0;
		for (String id : idArray) {
			BizTBaseFactoryInfoEntity bizTBaseFactoryInfoEntity = equipmentFactoryDao.findOne(id);
			bizTBaseFactoryInfoEntity.setOutService(Long.parseLong(CommonConstant.STATUS_VALID));
			String userId = user.getUserId();
			String userName = user.getUserName();
			Date date = new Date();
			bizTBaseFactoryInfoEntity.setUpdatorAccount(userId);
			bizTBaseFactoryInfoEntity.setUpdatorName(userName);
			bizTBaseFactoryInfoEntity.setUpdateTimestemp(DateUtil.dateToString(date, "yyyyMMddHHmmss"));
			
			equipmentFactoryDao.save(bizTBaseFactoryInfoEntity);
			result ++;
		}
		return result;
	}

}
