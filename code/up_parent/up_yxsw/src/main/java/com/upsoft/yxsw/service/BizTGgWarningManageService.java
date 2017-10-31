package com.upsoft.yxsw.service;

import java.util.List;
import java.util.Map;

import com.upsoft.system.bean.PageBean;
import com.upsoft.system.bean.WSLoginInfoBean;
import com.upsoft.system.service.BaseService;
import com.upsoft.yxsw.entity.BizTGgWarningManageEntity;

public interface BizTGgWarningManageService extends  BaseService {

	Map<String, Object> getWarningListAndCount(Map<String, Object> pars,
			PageBean bean);
	
	/**
	 * 删除以逗号分隔的用户Id集（eg:"id,id,id,id,"）
	 * @date 2017年9月9日 下午3:39:08
	 * @author 杨磊
	 * @param warningids
	 */
	public void deleteSplitWarnings(String warningids);
	
	/**
	 * 删除一个id
	 * @date 2017年9月9日 下午4:10:38
	 * @author 杨磊
	 * @param warningid
	 */
	public void deleteOneWarning(String warningid);
	

/**
 * 查询一个安全定义的明细
 * @date 2017年9月9日 下午5:39:32
 * @author 杨磊
 * @param pars
 * @param start
 * @param length
 * @return
 */
	public List<Map<String, Object>> getWarningList(Map<String, Object> pars,
			int start, int length);
 /**
  * 检查是是否关联安全提醒
  * @date 2017年9月12日 下午1:36:11
  * @author 杨磊
  * @param id
  * @return
  */
   Boolean checkWarning(String id);

/**
 * 获取已关联的巡检点安全提醒定义
 * 
 * @date 2017年9月18日 下午5:38:51
 * @author 陈涛
 * @param pageBean
 * @param spotId
 * @param loginInfo
 * @return 
 */
Map<String, Object> getRelatedData(PageBean pageBean, String spotId,
		WSLoginInfoBean loginInfo);

/**
 * 获取未关联的安全提醒定义
 * 
 * @date 2017年9月18日 下午5:59:32
 * @author 陈涛
 * @param spotId
 * @param pageBean
 * @param params
 * @param loginInfo
 * @return 
 */
Map<String, Object> getNoRelatedData(String spotId,PageBean pageBean,
		Map<String, Object> params, WSLoginInfoBean loginInfo);

/**
 * 根据提醒类型获取安全提醒信息
 * @date 2017年9月22日 下午8:12:30
 * @author 胡毅
 * @param attach_code
 * @param tx_type
 * @return
 */
List<Map<String, Object>> getWaningListByTxType(String attach_code, String tx_type);

}
