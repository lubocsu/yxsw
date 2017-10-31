package com.upsoft.yxsw.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.upsoft.system.bean.PageBean;
import com.upsoft.system.bean.WSLoginInfoBean;
import com.upsoft.system.constant.CommonConstant;
import com.upsoft.system.service.BaseServiceImpl;
import com.upsoft.system.util.DateUtil;
import com.upsoft.yxsw.dao.BizTXjdItemDao;
import com.upsoft.yxsw.entity.BizTXjdItem;
import com.upsoft.yxsw.service.BizTXjdItemService;


@Service
public class BizTXjdItemServiceImpl extends BaseServiceImpl implements BizTXjdItemService {

	@Autowired
	private BizTXjdItemDao bizTXjdItemDao;
	
	@Override
	public Map<String, Object> getXjItemData(PageBean pageBean,Map<String, Object> params,WSLoginInfoBean loginInfo) {
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT T.XJD_ITEM_ID,T.XJD_ITEM_NAME,T.XJD_ITEM_ADDRESS,T.XJD_ITEM_DESC,T.RFID_CODE,T.CREATOR_NAME,T.BELONG_WSC_NAME FROM BIZ_T_XJD_ITEM T ");
		sql.append(" WHERE 1=1 ");
		sql.append(" AND T.BELONG_WSC_ID IN ");
		sql.append(" (SELECT O.ORGID FROM SYS_ORG O START WITH O.ORGID = '" + loginInfo.getCsOrgId() + "' CONNECT BY PRIOR O.ORGID = O.PARENTORGID)");
		sql.append(" and t.del_flag='"+CommonConstant.STATUS_DISABLE+"' ");
		if(null != params){
			if(params.containsKey("xjdItemName")){
				sql.append(" AND T.XJD_ITEM_NAME LIKE '%"+params.get("xjdItemName").toString()+"%' ");
			}
			if(params.containsKey("xjdItemAddress")){
				sql.append(" AND T.XJD_ITEM_ADDRESS LIKE '%"+params.get("xjdItemAddress").toString()+"%' ");
			}
			if(params.containsKey("rfidCode")){
				sql.append(" AND T.RFID_CODE LIKE '%"+params.get("rfidCode").toString()+"%' ");
			}
			
			//手机端所用查询条件
			if(params.containsKey("rfidid")){
				if(params.get("rfidid").toString().equals(CommonConstant.STATUS_DISABLE)){
					
					sql.append(" AND T.RFID_CODE  is null ");
				}else if(params.get("rfidid").toString().equals(CommonConstant.STATUS_VALID)){
					sql.append(" AND T.RFID_CODE  is not null ");
				}
			}
		}
		
		sql.append(" ORDER BY T.RFID_CODE DESC,T.CREATE_TIMESTEMP DESC ");
		
		return bizTXjdItemDao.queryPaginationListBySql(sql.toString(), new HashMap<String,Object>(), pageBean);
	}

	@Override
	public void saveXjItem(String xjdItemName, String xjdItemAddress,
			String rfidCode, String xjdItemDesc, WSLoginInfoBean loginInfo) {
		BizTXjdItem bizTXjdItem = new BizTXjdItem();
		String userId = loginInfo.getUser().getUserId();
		String userName = loginInfo.getUser().getUserName();
		Date date = new Date();
		
		bizTXjdItem.setXjdItemName(xjdItemName);
		bizTXjdItem.setXjdItemAddress(xjdItemAddress);
		bizTXjdItem.setXjdItemDesc(xjdItemDesc);
		bizTXjdItem.setRfidCode(rfidCode);
		
		bizTXjdItem.setBelongWscId(loginInfo.getCsOrgId());
		bizTXjdItem.setBelongWscName(loginInfo.getCsOrgName());
		bizTXjdItem.setDelFlag(Long.parseLong(CommonConstant.STATUS_DISABLE));
		bizTXjdItem.setCreatorAccount(userId);
		bizTXjdItem.setCreatorName(userName);
		bizTXjdItem.setCreateTimestemp(DateUtil.dateToString(date, "yyyyMMddHHmmss"));
		bizTXjdItem.setUpdatorAccount(userId);
		bizTXjdItem.setUpdatorName(userName);
		bizTXjdItem.setUpdateTimestemp(DateUtil.dateToString(date, "yyyyMMddHHmmss"));
		
		bizTXjdItemDao.save(bizTXjdItem);
		
	}

	@Override
	public Boolean rfidExists(String validateValue) {
		String sql = "SELECT *  FROM BIZ_T_XJD_ITEM T WHERE 1 = 1 AND T.DEL_FLAG = '0' AND T.RFID_CODE = '"+validateValue+"'";
		
		int count = bizTXjdItemDao.queryListBySql(sql,  new String[]{}).size();
		if(count > 0){
			return true;
		}else{
			return false;
		}
	}

	@Override
	public BizTXjdItem findOneById(String xjItemId) {
		
		return bizTXjdItemDao.findOne(xjItemId);
	}

	@Override
	public void updateXjItem(String xjdItemId, String xjdItemName,
			String xjdItemAddress, String rfidCode, String xjdItemDesc,
			WSLoginInfoBean loginInfo) {
		BizTXjdItem bizTXjdItem = bizTXjdItemDao.findOne(xjdItemId);
		
		bizTXjdItem.setXjdItemName(xjdItemName);
		bizTXjdItem.setXjdItemAddress(xjdItemAddress);
		bizTXjdItem.setXjdItemDesc(xjdItemDesc);
		bizTXjdItem.setRfidCode(rfidCode);
		
		Date date = new Date(); 
		bizTXjdItem.setUpdatorAccount(loginInfo.getUser().getUserId());
		bizTXjdItem.setUpdatorName(loginInfo.getUser().getUserName());
		bizTXjdItem.setUpdateTimestemp(DateUtil.dateToString(date, "yyyyMMddHHmmss"));
		
		bizTXjdItemDao.save(bizTXjdItem);
	}

	@Override
	public Boolean checkItem(String id) {
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT  COUNT(M.TECHNICS_ID) AS HAS_USE  FROM BIZ_T_XJ_TECHNICS_SCOPE_MANAGE M,BIZ_T_XJ_TECHNICS_SCOPE_ATT_XJ X ");
		sql.append(" WHERE 1=1 AND X.TECHNICS_ID = M.TECHNICS_ID ");
		sql.append(" AND M.DEL_FLAG = '0' ");
		sql.append(" AND X.XJD_ITEM_ID = '"+ id +"' ");
		
		Long count = bizTXjdItemDao.queryCountBySql(sql.toString(), new HashMap<String,Object>());
		if(count > 0){
			return true;
		}else{
			return false;
		}
	}

	@Override
	public void updateXjItemByIds(String id, WSLoginInfoBean loginInfo) {
		
		BizTXjdItem bizTXjdItem = bizTXjdItemDao.findOne(id);
		bizTXjdItem.setDelFlag(Long.parseLong(CommonConstant.STATUS_VALID));
		Date date = new Date(); 
		bizTXjdItem.setUpdatorAccount(loginInfo.getUser().getUserId());
		bizTXjdItem.setUpdatorName(loginInfo.getUser().getUserName());
		bizTXjdItem.setUpdateTimestemp(DateUtil.dateToString(date, "yyyyMMddHHmmss"));
		bizTXjdItem.setRfidCode("");
		
		bizTXjdItemDao.save(bizTXjdItem);
	}

	@Override
	public List<Map<String, Object>> getPointTree(WSLoginInfoBean loginInfo) {
		StringBuffer sql = new StringBuffer();
		sql.append(" select t.xjd_item_id id, t.xjd_item_name name,t.belong_wsc_id parentid, (case when tt.counts >= 1 then tt.counts else 0 end) count ")
		   .append(" from biz_t_xjd_item t ")
		   .append(" left join (")
		   .append(" select dd.xjd_item_id,sum(dd.counts) counts from ")
           .append(" (select d.xjd_item_id, count(1) counts ")
           .append(" from BIZ_T_XJD_ITEM_DETAIL d, biz_t_xjd_item t ")
           .append(" where t.xjd_item_id = d.xjd_item_id ")
           .append(" group by d.xjd_item_id ")
           .append(" union all ")
           .append(" select b.code xjd_item_id, count(1) counts ")
           .append(" from BIZ_T_GG_SBSS_ATTACH_BASE b, biz_t_xjd_item t ")
           .append(" where t.xjd_item_id = b.code ")
           .append(" group by b.code) dd group by dd.xjd_item_id ")
		   .append(") tt ")
		   .append(" on tt.xjd_item_id = t.xjd_item_id ")
		   .append(" where t.del_flag = '0' ");
		sql.append(" AND T.BELONG_WSC_ID IN ");
		sql.append(" (SELECT O.ORGID FROM SYS_ORG O START WITH O.ORGID = '" + loginInfo.getCsOrgId() + "' CONNECT BY PRIOR O.ORGID = O.PARENTORGID)");
		sql.append(" and t.del_flag='"+CommonConstant.STATUS_DISABLE+"' ");
		
		List<Map<String, Object>> result = bizTXjdItemDao.queryListBySql(sql.toString(), new HashMap<String,Object>());
		
		return result;
	}

	@Override
	public Map<String, Object> getNoRelatedData(PageBean pageBean,
			Map<String, Object> params, WSLoginInfoBean loginInfo) {
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT EQ.* FROM ( ");
		sql.append(" SELECT B.XJD_ITEM_ID, B.XJD_ITEM_NAME,B.XJD_ITEM_ADDRESS, B.RFID_CODE,F_XJD_HAVE_ATTACH_TECHNICS(B.XJD_ITEM_ID,NULL) AS IS_ATTACH ");
		sql.append(" FROM BIZ_T_XJD_ITEM B WHERE 1=1");
		sql.append(" AND B.DEL_FLAG = '0' ");
		sql.append(" AND B.BELONG_WSC_ID ='"+loginInfo.getCsOrgId()+"' ");
		if(null != params){
			if(params.containsKey("eqName")){
				sql.append(" AND B.XJD_ITEM_NAME LIKE '%"+params.get("eqName").toString()+"%' ");
			}
			if(params.containsKey("setAddress")){
				sql.append(" AND B.XJD_ITEM_ADDRESS LIKE '%"+params.get("setAddress").toString()+"%' ");
			}
		}   
		sql.append(" ) eq ");
		sql.append(" WHERE EQ.IS_ATTACH = '0'  ");
		return bizTXjdItemDao.queryPaginationListBySql(sql.toString(), new HashMap<String,Object>(), pageBean);
	}

	@Override
	public Map<String, Object> getRelatedItemData(PageBean pageBean,
			String technicsId, WSLoginInfoBean loginInfo) {
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT D.ATT_ID,B.XJD_ITEM_NAME,B.XJD_ITEM_ADDRESS,B.RFID_CODE ");
		sql.append(" FROM biz_t_xjd_item B ");
		sql.append(" LEFT JOIN biz_t_xj_technics_scope_att_xj D ON B.XJD_ITEM_ID = D.XJD_ITEM_ID  ");  
		sql.append("  WHERE 1=1  ");     
	    sql.append(" AND D.TECHNICS_ID ='"+ technicsId +"'");
	  
		return bizTXjdItemDao.queryPaginationListBySql(sql.toString(), new HashMap<String,Object>(), pageBean);
	}

	@Override
	public List<Map<String, Object>> getOrgList(WSLoginInfoBean loginInfo) {
		String sql = " SELECT O.ORGID ID,O.ORGNAME NAME,O.PARENTORGID PARENTID FROM SYS_ORG O WHERE 1=1 AND O.ORGTYPEID='3' START WITH O.ORGID = '"+ loginInfo.getCsOrgId() +"' CONNECT BY PRIOR O.ORGID = O.PARENTORGID ";
		
		return bizTXjdItemDao.queryListBySql(sql, new HashMap<String, Object>());
	}

	@Override
	public void updateRfidItem(String xjdItemId, String rfidCode) {
		
		BizTXjdItem bizTXjdItem = bizTXjdItemDao.findOne(xjdItemId);
		bizTXjdItem.setRfidCode(rfidCode);
		bizTXjdItemDao.save(bizTXjdItem);
	}

	@Override
	public boolean isRfidBinded(String rfidCode) {
		String sql = "select count(1) from  biz_t_xjd_item t where t.rfid_code = '"+ rfidCode +"'";
		Long count = bizTXjdItemDao.queryCountBySql(sql, new HashMap<String,Object>());
		if(count > 0){
			return true;
		}else{
			return false;
		}
	}
}
