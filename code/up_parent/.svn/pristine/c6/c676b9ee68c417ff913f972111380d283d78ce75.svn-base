package com.upsoft.yxsw.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.upsoft.system.bean.PageBean;
import com.upsoft.system.service.BaseServiceImpl;
import com.upsoft.yxsw.dao.BizTXjZypTemplateItmDetDao;
import com.upsoft.yxsw.entity.BizTXjZypTemplateItmDet;
import com.upsoft.yxsw.service.BizTXjZypTemplateItmDetService;


@Service
public class BizTXjZypTemplateItmDetServiceImpl extends BaseServiceImpl implements BizTXjZypTemplateItmDetService {
	
	@Autowired
	private BizTXjZypTemplateItmDetDao itmDetDao;
	
	/**
	 * XXXXXXXXXXXXXXXXXXXXXXXX
	 * @date 2017-09-30
	 * @author 
	 * @param bean
	 * @param params
	 * @return 
	 */
	public Map<String, Object> getList(PageBean bean, Map<String, Object> params){
		return params;
	}
	
	/**
	 * XXXXXXXXXXXXXXXXXXXXXXXXXXXX
	 * @date 2017-09-30
	 * @author 
	 * @param order
	 * @return 
	 */
	public BizTXjZypTemplateItmDet save(BizTXjZypTemplateItmDet bizTXjZypTemplateItmDet){
		return itmDetDao.save(bizTXjZypTemplateItmDet);
	}
	
	
	/**
	 * XXXXXXXXXXXXXXX
	 * @date 2017-09-30
	 * @author 
	 * @param 
	 * @return 
	 */
	public BizTXjZypTemplateItmDet getBizTXjZypTemplateItmDetById(String id){
		return null;
	}

	@Override
	public List<BizTXjZypTemplateItmDet> save(List<BizTXjZypTemplateItmDet> iteDetList) {
		return itmDetDao.save(iteDetList);
	}
}
