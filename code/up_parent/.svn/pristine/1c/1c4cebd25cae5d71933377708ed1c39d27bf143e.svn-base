package com.upsoft.yxsw.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.upsoft.system.service.BaseServiceImpl;
import com.upsoft.yxsw.entity.BizTXjZypCxMake;
import com.upsoft.yxsw.entity.BizTXjZypCxMakePersonKh;
import com.upsoft.yxsw.service.BizTXjZbPlanDetailPersonService;
import com.upsoft.yxsw.service.BizTXjZypCxMakePersonKhService;
import com.upsoft.yxsw.service.BizTXjZypCxMakeService;


@Service
public class BizTXjZypCxMakePersonKhServiceImpl extends BaseServiceImpl implements BizTXjZypCxMakePersonKhService {

	@Autowired
	private BizTXjZypCxMakeService bizTXjZypCxMakeService;
	@Autowired
	private BizTXjZbPlanDetailPersonService bizTXjZbPlanDetailPersonService; 
	
	@Override
	public List<BizTXjZypCxMakePersonKh> getListByCxMakeId(String cxMakeId) {
		// 获取作业票当日的值班人员
		BizTXjZypCxMake cxMake = bizTXjZypCxMakeService.findOne(BizTXjZypCxMake.class,cxMakeId);
		String belongWscId = cxMake.getBelongWscId();
		String zypDate = cxMake.getZypDate();
		
		List<BizTXjZypCxMakePersonKh> personList = bizTXjZbPlanDetailPersonService.getPersonListByWscIdAndZypDate(cxMakeId,belongWscId,zypDate);
		return personList;
	}
}
