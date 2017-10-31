package com.upsoft.systemweb.service;

import java.util.List;
import java.util.Map;

import com.upsoft.system.bean.PageBean;
import com.upsoft.system.entity.SysMenuEntity;
import com.upsoft.system.service.BaseService;

public interface MenuService extends BaseService {
	/**
	 * 根据用户Id获取用户权限下的菜单
	 * 
	 * @date 2015年1月22日 下午6:27:01
	 * @author 吴炫
	 * @param userId
	 * @return 菜单JSON字符串
	 */
	public String querySysMenusByUserId(String userId);

	/**
	 * 获取系统下菜单:Tree
	 * 
	 * @date 2015年1月27日 上午11:01:32
	 * @author 吴炫
	 * @param sysCode
	 * @return
	 */
	@Deprecated
	public List<Map<String, Object>> queryMenusBySysCodeAndUserId(String sysCode, String userId);

	/**
	 * 获取系统下菜单:Accordion
	 * 
	 * @date 2015年2月6日 上午9:47:30
	 * @author 吴炫
	 * @param menuId
	 * @param userId
	 * @return
	 */
	@Deprecated
	public List<Map<String, Object>> queryAccordionMenusByCondition(String sysCode, String userId);

	public Map<String, List<Map<String, Object>>> queryMenusBySysCodeAndUserIdForGridData(SysMenuEntity menu,PageBean bean,
			String userId);

	/**
	 * 根据用户ID和系统code获取菜单（级联形式）
	 * @date 2015年2月6日 上午9:47:30
	 * @author 吴炫
	 * @param menuId
	 * @param userId
	 * @return
	 */
	public List<Map<String, Object>> queryAccordionMenusByUserIdAndSystemCode(String userId, String sysCode);
	
	/**
	 * 根据菜单ID和用户ID获取菜单下的子菜单
	 * 
	 * @date 2015年1月22日 下午7:04:16
	 * @author 吴炫
	 * @param menuId
	 * @param userId
	 * @return
	 */
	public List<Map<String, Object>> queryMenusByMenuIdAndUserId(String menuId, String userId);

	
	/**
	 * 根据用户ID和系统code获取菜单
	 * @date 2015年7月28日 上午10:59:13
	 * @author 冉恒鑫
	 * @param userId
	 * @param systemCode
	 * @return
	 */
	public List<Map<String, Object>> queryMenusByUserIdAndSystemCode(String userId, String systemCode);
	
	/**
	 * 获取菜单树用于增删改查
	 * 
	 * @date 2015年2月3日 下午5:21:11
	 * @author 胡毅
	 * @param systemCode
	 * @return
	 */
	public List<Map<String, Object>> queryMenuTree(String systemCode,String path,String userid);

	/**
	 * 通过系统编码获取该系统的所有菜单
	 * 
	 * @date 2015年2月13日 下午4:53:48
	 * @author TW
	 * @param systemCode
	 *            系统编码
	 * @return
	 */
	public List<Map<String, Object>> queryMenuListBySystemCode(String systemCode);

}
