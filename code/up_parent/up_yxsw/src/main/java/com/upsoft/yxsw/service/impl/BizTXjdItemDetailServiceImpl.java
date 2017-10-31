package com.upsoft.yxsw.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.upsoft.system.bean.WSLoginInfoBean;
import com.upsoft.system.service.BaseServiceImpl;
import com.upsoft.system.util.DateUtil;
import com.upsoft.yxsw.constant.Constant;
import com.upsoft.yxsw.dao.BizTGgSbssAttachBaseDAO;
import com.upsoft.yxsw.dao.BizTXjdItemDetailDao;
import com.upsoft.yxsw.entity.BizTGgSbssAttachBase;
import com.upsoft.yxsw.entity.BizTXjdItemDetail;
import com.upsoft.yxsw.service.BizTXjdItemDetailService;


@Service
public class BizTXjdItemDetailServiceImpl extends BaseServiceImpl implements BizTXjdItemDetailService {

	@Autowired
	private BizTXjdItemDetailDao bizTXjdItemDetailDao;
	@Autowired
	private BizTGgSbssAttachBaseDAO bizTGgSbssAttachBaseDAO;
	
	@Override
	public void saveRelated(String spotId, List<String> idArray,
			List<String> nameArray,String detailType) {
		BizTXjdItemDetail bizTXjdItemDetail;
		for (int i = 0; i < idArray.size(); i++) {
			 bizTXjdItemDetail = new BizTXjdItemDetail();
			 
			bizTXjdItemDetail.setXjdItemId(spotId);
			bizTXjdItemDetail.setDetailType(detailType);
			bizTXjdItemDetail.setSbssId(idArray.get(i));
			bizTXjdItemDetail.setName(nameArray.get(i));
			
			bizTXjdItemDetailDao.save(bizTXjdItemDetail);
		}
	}

	@Override
	public void delRelated(List<String> idArray) {
		for (String id : idArray) {
			bizTXjdItemDetailDao.delete(findOne(BizTXjdItemDetail.class, id));
		}
		
	}

	@Override
	public Boolean getCount(String spotId) {
		String sql = " SELECT COUNT(1)  from BIZ_T_XJD_ITEM_DETAIL T   WHERE T.XJD_ITEM_ID='"+ spotId +"'";
		Long count = bizTXjdItemDetailDao.queryCountBySql(sql, new HashMap<String,Object>());
		String sql2= "select count(1) from BIZ_T_GG_SBSS_ATTACH_BASE t where t.code='"+spotId+"'"; 
		Long count2 =  bizTGgSbssAttachBaseDAO.queryCountBySql(sql2, new HashMap<String,Object>());
		if(count+count2 > 0){
			return true;
		}else{
			return false;
		}
	}

	@Override
	public void saveRelatedWar(String spotId, List<String> idArray,
			List<String> nameArray, WSLoginInfoBean loginInfo) {
		BizTGgSbssAttachBase bizTGgSbssAttachBase;
		Date date = new Date();
		for (int i = 0; i < idArray.size(); i++) {
			bizTGgSbssAttachBase = new BizTGgSbssAttachBase();
			 
			bizTGgSbssAttachBase.setCode(spotId);
			bizTGgSbssAttachBase.setSbOrSs(Constant.DETAIL_TYPE.WARNING.getValue());
			bizTGgSbssAttachBase.setConfType(Constant.PZ_TYPE.SAFETYPE.getValue());
			bizTGgSbssAttachBase.setDetailId(idArray.get(i));
			bizTGgSbssAttachBase.setName(nameArray.get(i));
			bizTGgSbssAttachBase.setSortNum(Long.parseLong(String.valueOf(i)));
			bizTGgSbssAttachBase.setCreatorAccount(loginInfo.getUser().getUserId());
			bizTGgSbssAttachBase.setCreatorName(loginInfo.getUser().getUserName());
			bizTGgSbssAttachBase.setCreateTimestemp(DateUtil.dateToString(date, "yyyyMMddHHmmss"));
			bizTGgSbssAttachBase.setBelongDept(loginInfo.getUser().getOrgId());
			bizTGgSbssAttachBase.setBelongWscId(loginInfo.getCsOrgId());
			bizTGgSbssAttachBase.setBelongWscName(loginInfo.getCsOrgName());
			bizTGgSbssAttachBase.setUpdatorAccount(loginInfo.getUser().getUserId());
			bizTGgSbssAttachBase.setUpdatorName(loginInfo.getUser().getUserName());
			bizTGgSbssAttachBase.setUpdateTimestemp(DateUtil.dateToString(date, "yyyyMMddHHmmss"));
			
			bizTGgSbssAttachBaseDAO.save(bizTGgSbssAttachBase);
		}
	}

	@Override
	public void delRelateWar(List<String> idArray) {
		
		for (String id : idArray) {
			
			bizTGgSbssAttachBaseDAO.delete(findOne(BizTGgSbssAttachBase.class,id));
		}
		
	}
	
}
