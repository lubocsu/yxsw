package com.upsoft.yxsw.service;

import java.util.List;
import java.util.Map;

import com.upsoft.system.bean.PageBean;
import com.upsoft.system.entity.SysUser;
import com.upsoft.system.service.BaseService;
import com.upsoft.yxsw.entity.BizTBaseFactoryInfoEntity;
import com.upsoft.yxsw.entity.BizTSbTypesEntity;

public interface EquipmentFactoryService extends BaseService{

	/**
	 * 获取设备厂商数据
	 * 
	 * @date 2017年9月9日 下午2:41:14
	 * @author 陈涛
	 * @param pageBean
	 * @param params
	 * @return 
	 */
	Map<String, Object> getEqFactoryData(PageBean pageBean,
			Map<String, Object> params);

	/**
	 * 新增设备厂商
	 * 
	 * @date 2017年9月9日 下午5:02:09
	 * @author 陈涛
	 * @param bizTBaseFactoryInfoEntity
	 * @param user
	 * @return 
	 */
	void saveEquipmentFactory(
			BizTBaseFactoryInfoEntity bizTBaseFactoryInfoEntity, SysUser user);

	/**
	 * 根据ID查找设备厂商
	 * 
	 * @date 2017年9月9日 下午6:12:32
	 * @author 陈涛
	 * @param id
	 * @return 
	 */
	Map<String, Object> findOneById(String id);

	/**
	 * 更新设备厂商
	 * 
	 * @date 2017年9月11日 上午10:13:40
	 * @author 陈涛
	 * @param bizTBaseFactoryInfoEntity
	 * @param user 
	 */
	void updateEquipmentFactory(
			BizTBaseFactoryInfoEntity bizTBaseFactoryInfoEntity, SysUser user);

	/**
	 * 单个或者批量停用厂商
	 * 
	 * @date 2017年9月11日 上午10:38:43
	 * @author 陈涛
	 * @param idArray
	 * @return 
	 */
	int updateFactoryByIds(List<String> idArray,SysUser user);

}
