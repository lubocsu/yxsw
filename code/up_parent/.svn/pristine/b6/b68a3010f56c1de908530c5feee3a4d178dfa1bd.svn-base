package com.upsoft.systemweb.service;

import java.util.List;
import java.util.Map;

import com.upsoft.system.bean.PageBean;
import com.upsoft.system.entity.SysOrgEntity;
import com.upsoft.system.service.BaseService;

/**
 * 
 * Copyright (c) 2015,重庆扬讯软件技术有限公司<br>
 * All rights reserved.<br>
 * 
 * 文件名称：IOrgService.java<br>
 * 摘要：组织机构服务接口类<br>
 * -------------------------------------------------------<br>
 * 当前版本：1.1.1<br>
 * 作者：胡毅<br>
 * 完成日期：2015年1月22日<br>
 * -------------------------------------------------------<br>
 * 取代版本：1.1.0<br>
 * 原作者：胡毅<br>
 * 完成日期：2015年1月22日<br>
 */
public interface OrgService extends BaseService {
	/**
	 * 查询完整组织机构子机构
	 * 
	 * @date 2015年1月22日 上午11:39:26
	 * @author 胡毅
	 * @return
	 */
	public List<SysOrgEntity> querySysOrgList(int start, int length, Map<String, Object> pars);
	
	/**
	 * 查询完整组织机构子机构和自己
	 * 
	 * @date 2015年1月22日 上午11:39:26
	 * @author 胡毅
	 * @return
	 */
	public List<SysOrgEntity> querySysOrgList(int start, int length, String orgid);
	
	/**
	 * 验证组织机构编码的唯一性
	 * 
	 * @date 2015年1月23日 下午5:06:51
	 * @author 胡毅
	 * @param orgCode
	 * @return
	 */
	public long checkOrgCode(String orgCode,String orgId);

	/**
	 * 通用数据源(内容、数据)，可以带有分页参数
	 * 
	 * @date 2015年1月26日 下午2:15:52
	 * @author 胡毅
	 * @param bean
	 * @param pars
	 * @return
	 */
	public Map<String, Object> queryPagination(PageBean bean, Map<String, Object> pars);

	/**
	 * 通过父级机构ID批量删除子机构
	 * @date 2015年3月16日 下午12:01:57
	 * @author 胡毅
	 * @param id
	 */
	public void deleteOrgByParentId(String parentOrgId);
	
	/**
	 * 根据机构ID获取一个机构实体
	 * @date 2017年8月25日 下午3:59:49
	 * @author 胡毅
	 * @param orgId
	 * @return
	 */
	public SysOrgEntity findOneById(String orgId);

	/**
	 * 根据权限获取机构列表
	 * 
	 * @date 2017年4月26日 上午11:04:13
	 * @author 陈涛
	 * @param orgId
	 * @return 
	 */
	public List<Map<String, Object>> queryOrgsWithPermission(String orgId);

	/**
	 * 根据机构编码获取机构
	 * @date 2017年5月26日 下午2:23:54
	 * @author Administrator
	 * @param orgCode
	 * @return 
	 */
	public SysOrgEntity findOneByCode(String orgCode);

	/**
	 * 通过orgId获取当前机构及所属的机构的ID
	 * @date 2017年8月25日 下午3:59:26
	 * @author 胡毅
	 * @param orgId
	 * @return
	 */
	public List<String> iterateOrgIdsById(String orgId);
	
	/**
	 * 根据orgcode返回所有下属机构的orgcode
	 * @date 2015年8月4日 上午9:38:19
	 * @author null
	 * @param orgCode
	 * @return 
	 */
	public List<Map<String, Object>> iterateOrgAllColumnByCode(String orgCode);
	
	/**
	 * 通过orgId获取当前机构及所属的机构所有字段
	 * orgId 为 null 则 返回所有机构
	 * @date 2015年7月29日 下午4:16:01
	 * @author 冉恒鑫
	 * @param orgId
	 * @return
	 */
	public List<Map<String, Object>> iterateOrgAllColumnById(String orgId); 
	
	/**
	 * 返回用户可访问的结构树(数据权限控制)
	 * 2 parentId和parentCode可以指定根节点，不指定则返回所有
	 * 3 parentId和parentCode同时指定，则parentId生效
	 * @date 2016年6月3日 下午5:24:53
	 * @author null
	 * @param userId
	 * @param parentId
	 * @param parentCode
	 * @return 
	 */
	public List<Map<String, Object>> getOrgTreeInPermission(String userId, String parentId, String parentCode);
	
	/**
	 * 根据指定的orgid，查询该机构所属厂站级、公司级机构
	 * 
	 * @param orgId
	 * @return
	 */
	public SysOrgEntity queryUperCsSysOrgByOrgId(String orgId, String orgType);

	/**
	 * 新增机构时默认加上父级机构编码
	 * @date 2017年10月23日 下午5:02:38
	 * @author 胡毅
	 * @param sysOrg
	 * @return
	 */
	public SysOrgEntity save2(SysOrgEntity sysOrg);

	/**
	 * 修改机构时，同时修改以下所有子机构的前缀，和所属机构人员的状态
	 * @date 2017年10月23日 下午5:15:31
	 * @author 胡毅
	 * @param sysOrg
	 * @return
	 */
	public SysOrgEntity update2(SysOrgEntity sysOrg);
}
