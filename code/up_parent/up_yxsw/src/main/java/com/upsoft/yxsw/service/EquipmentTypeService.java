package com.upsoft.yxsw.service;

import java.util.List;
import java.util.Map;

import com.upsoft.system.entity.SysUser;
import com.upsoft.system.service.BaseService;
import com.upsoft.yxsw.entity.BizTSbTypesEntity;

public interface EquipmentTypeService extends BaseService {

	/**
	 * 通过treeIdc查询设备类型树
	 * 
	 * @date 2017年9月8日 下午1:19:09
	 * @author 陈涛
	 * @param params
	 * @return 
	 */
	List<Map<String, Object>> queryByTreeId(Map<String, Object> params);

	/**
	 * 通过ID获取该类及其子类
	 * 
	 * @date 2017年9月8日 下午4:13:55
	 * @author 陈涛
	 * @param parentId
	 * @return 
	 */
	List<BizTSbTypesEntity> getEquipmentTypeByParentId(String parentId);


	/**
	 * 查看详情
	 * 
	 * @date 2017年9月8日 下午5:39:22
	 * @author 陈涛
	 * @param sbTypeId
	 * @return 
	 */
	Map<String,Object> findOne(String sbTypeId);

	/**
	 * 获取设备类型层级
	 * 
	 * @date 2017年9月8日 下午5:45:26
	 * @author 陈涛
	 * @param sbLayer
	 * @return 
	 */
	Map<String, Object> getEquipmentTypeLayer(String sbLayer);

	/**
	 * 保存设备类型
	 * 
	 * @date 2017年9月8日 下午4:13:52
	 * @author 陈涛
	 * @param params
	 * @return 
	 */
	BizTSbTypesEntity saveEquipmentType(String parentId, String name,
			String code, String unit, String orders, String isStopUse,
			String remark, SysUser user);

	/**
	 * 更新设备类型
	 * 
	 * @date 2017年9月8日 下午8:07:23
	 * @author 陈涛
	 * @param params
	 * @return 
	 */
	void updateEquipmentType(String equipmentTypeId,String parentId, String name, String code,
			String unit, String orders, String isStopUse, String remark,
			SysUser user);

	/**
	 * 批量停用设备类型
	 * 
	 * @date 2017年9月9日 上午10:10:18
	 * @author 陈涛
	 * @param equipmentTypeId
	 * @param user 
	 */
	void delEquipmentTypeById(String equipmentTypeId, SysUser user);

	/**
	 * 获取设备类型及其子类型
	 * 
	 * @date 2017年9月9日 下午1:20:29
	 * @author 陈涛
	 * @param equipmentTypeId
	 * @return 
	 */
	List<BizTSbTypesEntity> getSbListById(String equipmentTypeId);
    /**
     * 在设备类型添加报表中为了是父节点的数据都能够点而子节点不能点
     * @date 2017年10月12日 下午3:51:35
     * @author 杨磊
     * @param params
     * @return
     */
	List<Map<String, Object>> queryByTreeIdParent(Map<String, Object> params);

}