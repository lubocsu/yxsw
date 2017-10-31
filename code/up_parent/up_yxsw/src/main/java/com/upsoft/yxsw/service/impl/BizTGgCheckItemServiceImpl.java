package com.upsoft.yxsw.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.upsoft.login.support.webservice.ServiceReceiver;
import com.upsoft.system.bean.PageBean;
import com.upsoft.system.service.BaseServiceImpl;
import com.upsoft.system.util.MapUtil;
import com.upsoft.yxsw.constant.Constant;
import com.upsoft.yxsw.constant.DictConstant;
import com.upsoft.yxsw.dao.BizTGgCheckItemDAO;
import com.upsoft.yxsw.entity.BizTGgCheckItem;
import com.upsoft.yxsw.entity.BizTGgCheckItemDetail;
import com.upsoft.yxsw.entity.BizTGgSbssAttachBase;
import com.upsoft.yxsw.service.BizTGgCheckItemDetailService;
import com.upsoft.yxsw.service.BizTGgCheckItemService;
import com.upsoft.yxsw.service.BizTGgSbssAttachBaseService;

@Service
public class BizTGgCheckItemServiceImpl extends BaseServiceImpl implements BizTGgCheckItemService {

	private static final Logger logger = Logger.getLogger(BizTGgCheckItemServiceImpl.class);
	
	@Autowired
	private BizTGgCheckItemDAO bizTGgCheckItemDAO;

	@Autowired
	private BizTGgCheckItemDetailService bizTGgCheckItemDetailService;
	
	@Autowired
	private BizTGgSbssAttachBaseService bizTGgSbssAttachBaseService;
	/**
	 * 获取检查项定义的分页数据
	 * @param bean
	 * @param params
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Map<String, Object> getList(PageBean pageBean, Map<String, Object> params) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT T.CHECK_ITEM_ID, T.CHECK_ITEM_CODE, T.CHECK_ITEM_NAME, T.CHECK_ITEM_TYPE, T.INPUT_TYPE, T.CHECK_ITEM_DESC, T.BYZD, T.DEL_FLAG, T.CREATE_TIMESTEMP, T.CREATOR_ACCOUNT, T.CREATOR_NAME, T.UPDATOR_ACCOUNT, T.UPDATOR_NAME, T.UPDATE_TIMESTEMP");
		sql.append(" FROM BIZ_T_GG_CHECK_ITEM T WHERE 1=1 AND T.DEL_FLAG='"+Constant.YES_NO.NO.getValue()+"'");
		Map<String,Object> paramMap = new HashMap<String,Object>();
		if(MapUtil.hasParam(params, "checkItemName")){
			sql.append(" AND T.CHECK_ITEM_NAME like '%"+params.get("checkItemName")+"%'");
//			paramMap.put("checkItemName", "'%"+params.get("checkItemName")+"%'");
		}
		if(MapUtil.hasParam(params, "checkItemType")){
			sql.append(" AND T.CHECK_ITEM_TYPE =:checkItemType");
			paramMap.put("checkItemType", params.get("checkItemType"));
		}
		if(MapUtil.hasParam(params, "checkItemDesc")){
			sql.append(" AND T.CHECK_ITEM_DESC like '%"+params.get("checkItemDesc")+"%'");
//			paramMap.put("checkItemDesc", "'%"+params.get("checkItemDesc")+"%'");
		}
		if(MapUtil.hasParam(params, "inputType")){
			sql.append(" AND T.INPUT_TYPE =:inputType");
			paramMap.put("inputType", params.get("inputType"));
		}
		
		Map<String,Object> result = bizTGgCheckItemDAO.queryPaginationListBySql(sql.toString(), paramMap, pageBean);
		List<Map<String,Object>> rows = (List<Map<String, Object>>) result.get("rows");
		List<Map<String,Object>> checkItemInputTypeDictList = ServiceReceiver.getDictSelect(DictConstant.CHECKITEM_INPUTTYPE.getValue());
		List<Map<String,Object>> checkItemTypeDictList = ServiceReceiver.getDictSelect(DictConstant.CHECKITEM_TYPE.getValue());
		for (Map<String, Object> map : rows) {
			String inputtype = map.get("input_type").toString();
			String type = map.get("check_item_type").toString();
			for (Map<String, Object> map2 : checkItemInputTypeDictList) {
				if(StringUtils.equals(inputtype, map2.get("key").toString())){
					map.put("input_type_name", map2.get("value"));
					break;
				}
			}
			for (Map<String, Object> map2 : checkItemTypeDictList) {
				if(StringUtils.equals(type, map2.get("key").toString())){
					map.put("check_item_type_name", map2.get("value"));
					break;
				}
			}
		}
		
		return result;

	}

	@Override
	public BizTGgCheckItem save(BizTGgCheckItem bizTGgCheckItem) {
		return bizTGgCheckItemDAO.save(bizTGgCheckItem);
	}

	@Override
	public Long validateCheckItemCode(String checkItemCode) {
		String sql = "SELECT COUNT(0) FROM BIZ_T_GG_CHECK_ITEM T WHERE 1=1 AND T.CHECK_ITEM_CODE =:checkItemCode";
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("checkItemCode", checkItemCode);
		return bizTGgCheckItemDAO.queryCountBySql(sql, params);
	}

	@Override
	public Boolean saveCheckItemAndDetail(BizTGgCheckItem checkItemEnity, List<BizTGgCheckItemDetail> checkItemDetailEnityList) {
		boolean flag = true;
		BizTGgCheckItem saved1 = bizTGgCheckItemDAO.save(checkItemEnity);
		if(null!=saved1){
			// 如果是修改，则删除旧的检查项明细,然后重新添加
			if(StringUtils.isNotBlank(checkItemEnity.getCheckItemId())){
				bizTGgCheckItemDetailService.deleteByCheckItemId(checkItemEnity.getCheckItemId());
			}
			for (BizTGgCheckItemDetail detatil : checkItemDetailEnityList) {
				detatil.setCheckItemId(saved1.getCheckItemId());
				BizTGgCheckItemDetail saved2 = bizTGgCheckItemDetailService.save(detatil);
				if(null==saved2){
					flag = false;
					break;
				}
			}
		}
		return flag;
	}

	@Override
	public List<BizTGgCheckItem> delete(List<String> checkItemIdList) {
		// 判断 设备设施关联关系是否存在，这些检查项不可被删除
		List<BizTGgSbssAttachBase> list = bizTGgSbssAttachBaseService.getAttachInfoByCheckItemIds(checkItemIdList);
		List<String> attachedCheckItemIdList = new ArrayList<String>();
		for (BizTGgSbssAttachBase attach : list) {
			attachedCheckItemIdList.add(attach.getDetailId());
		}
		// 分离出可删除的检查项Code
		checkItemIdList.removeAll(attachedCheckItemIdList);
		// 删除可删除检查项,逻辑
		for (String id : checkItemIdList) {
			this.deleteByID(id);
		}
		// 查询不可删除检查项
		if(attachedCheckItemIdList.size()>0){
			List<BizTGgCheckItem> list2 = this.getListByCheckItemIds(attachedCheckItemIdList);
			return list2;
		}else{
			return null;
		}
	}

	private void deleteByID(String id) {
		String sql = "UPDATE BIZ_T_GG_CHECK_ITEM T SET T.DEL_FLAG='"+Constant.YES_NO.YES.getValue()+"' WHERE T.CHECK_ITEM_ID = '"+id+"'" ;
		Map<String,Object> paramMap = new HashMap<String,Object>();
		bizTGgCheckItemDAO.executeSql(sql, paramMap);
	}

	@Override
	public List<BizTGgCheckItem>  getListByCheckItemIds(List<String> checkItemIdList) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT T.CHECK_ITEM_ID, T.CHECK_ITEM_CODE, T.CHECK_ITEM_NAME, T.CHECK_ITEM_TYPE, T.INPUT_TYPE, T.CHECK_ITEM_DESC, T.BYZD, T.DEL_FLAG, T.CREATE_TIMESTEMP, T.CREATOR_ACCOUNT, T.CREATOR_NAME, T.UPDATOR_ACCOUNT, T.UPDATOR_NAME, T.UPDATE_TIMESTEMP");
		sql.append(" FROM BIZ_T_GG_CHECK_ITEM T WHERE 1=1 ");
		Map<String,Object> paramMap = new HashMap<String,Object>();
		sql.append(" AND T.CHECK_ITEM_ID IN(:checkItemIdList)");
		paramMap.put("checkItemIdList", checkItemIdList);
		List<BizTGgCheckItem> list = bizTGgCheckItemDAO.queryListBySql(sql.toString(), paramMap, BizTGgCheckItem.class);
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> findOne(String checkItemId) {
		BizTGgCheckItem entity = bizTGgCheckItemDAO.findOne(checkItemId);
		Map<String,Object> checkItemMap = new HashMap<String,Object>();
		try {
			checkItemMap = BeanUtils.describe(entity);
			List<Map<String,Object>> checkItemInputTypeDictList = ServiceReceiver.getDictSelect(DictConstant.CHECKITEM_INPUTTYPE.getValue());
			List<Map<String,Object>> checkItemTypeDictList = ServiceReceiver.getDictSelect(DictConstant.CHECKITEM_TYPE.getValue());
			for (Map<String, Object> map2 : checkItemInputTypeDictList) {
				if(StringUtils.equals(entity.getInputType(), map2.get("key").toString())){
					checkItemMap.put("inputTypeName", map2.get("value"));
					break;
				}
			}
			for (Map<String, Object> map2 : checkItemTypeDictList) {
				if(StringUtils.equals(entity.getCheckItemType(), map2.get("key").toString())){
					checkItemMap.put("checkItemTypeName", map2.get("value"));
					break;
				}
			}
		} catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
			logger.error("查询检查项详情时出错："+e.getMessage());
		}
		return checkItemMap;
	}

	@Override
	public List<Map<String, Object>> getCheckBySbId(String sbId,Map<String,Object> params) {
		StringBuilder sql=new StringBuilder();
		sql.append("SELECT I.CHECK_ITEM_ID, I.CHECK_ITEM_CODE, I.CHECK_ITEM_NAME,   I.CHECK_ITEM_DESC,");
		sql.append("F_GET_CHECK_ITEM_ATTACHED_SBSS(I.CHECK_ITEM_ID,'1','");
		sql.append(sbId);
		sql.append("'");
		sql.append(")");
		sql.append("AS HAS_ATTACH, I.INPUT_TYPE");
		sql.append(" FROM BIZ_T_GG_CHECK_ITEM I");
		sql.append(" WHERE I.DEL_FLAG ='0'");
		sql.append("AND I.CHECK_ITEM_TYPE='1'");
		sql.append(" AND F_GET_CHECK_ITEM_ATTACHED_SBSS(I.CHECK_ITEM_ID,'1','");
		sql.append(sbId);
		sql.append("'");
		sql.append(")");
		sql.append("= '0'");
		
		
		List<Map<String, Object>> list = bizTGgCheckItemDAO.queryListBySql(sql.toString(), new HashMap<String,Object>());
		 if(list!=null&&list.size()>0){
			 return list;
		 }else{
			 return new ArrayList<>();	 
		 }
		
	}

//	@Override
//	public BizTGgCheckItem findOne2(String checkItemId) {
//		return bizTGgCheckItemDAO.findOne(checkItemId);
//	}
}
