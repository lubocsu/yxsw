package com.upsoft.systemweb.service;

import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.upsoft.system.bean.PageBean;
import com.upsoft.system.entity.SysPermissionEntity;

/**
 * Copyright (c) 2015,重庆扬讯软件技术有限公司<br>
 * All rights reserved.<br>
 * 
 * 文件名称：RoleManageService.java<br>
 * 摘要：菜单权限服务接口<br>
 * -------------------------------------------------------<br>
 * 当前版本：1.1.1<br>
 * 作者：李 红<br>
 * 完成日期：2015年1月22日<br>
 * -------------------------------------------------------<br>
 * 取代版本：1.1.0<br>
 * 原作者：李 红<br>
 * 完成日期：2015年1月22日<br>
 */

public interface PermissionService {
    /**
     * 分页查询权限List和总数
     * 
     * @date 2015年1月28日 下午5:47:26
     * @author 李 红
     * @param systemCode
     * @param roleName
     * @param pageBean
     * @return
     */
    public Map<String, Object> findPermissionListAndCount(String systemCode,String parentId, String permissionName, PageBean pageBean);

    /**
     * 保存权限信息
     * 
     * @date 2015年1月28日 下午5:48:03
     * @author 李 红
     * @param roleEntity
     */
    public void saveOrUpdatePermission(SysPermissionEntity permissionEntity);

    /**
     * 删除权限信息
     * 
     * @date 2015年1月28日 下午5:48:15
     * @author 李 红
     * @param roleId
     */
    public void delPermission(String permissionIds);

    /**
     * 查询子系统定义信息
     * 
     * @date 2015年1月28日 下午5:48:27
     * @author 李 红
     * @return
     */
    public List<Map<String,Object>> findSystemDefine();

    /**
     * 查询子权限树
     * 
     * @date 2015年1月28日 下午5:49:04
     * @author 李 红
     * @param roleId
     * @return
     */
    public Map<String,Object> findPermissionChild(String parentId);

    /**
     * 获取权限树
     * @date 2015年2月5日 上午11:59:40
     * @author 李  红
     * @param systemCode
     * @return
     */
    public List<Map<String,Object>> findPermissionTree(String systemCode,String permissionId); 
    
    /**
     * 获取菜单树
     * @date 2015年2月12日 下午4:27:22
     * @author 李  红
     * @param systemCode
     * @return
     */
    public List<Map<String,Object>> findMenuTree(String systemCode); 
    /**
     * 查询单个权限信息
     * @date 2015年2月26日 下午2:16:42
     * @author 李  红
     * @param permissionId
     * @return
     */
    public List<Map<String,Object>> findOne(String permissionId);
    /**
     * 查询子权限信息
     * @date 2015年2月26日 下午2:16:03
     * @author 李  红
     * @param parentPermissionId
     * @return
     */
    public List<Map<String,Object>> findByParent(String parentPermissionId);
    /**
     * 保存权限用户信息
     * 
     * @date 2015年1月28日 下午5:49:34
     * @author 李 红
     * @param roleId
     * @param userIds
     */
    public void savePermissionToMenu(String permissionId, String menuIds);
    
    /**
     * 获取排序编码
     * @date 2015年2月5日 上午9:18:18
     * @author 李  红
     * @param systemCode
     * @return
     */
    public Long queryOrderNo(String systemCode);

    /**
     * 验证是否存在
     * @date 2015年2月6日 下午4:33:49
     * @author 李  红
     * @param systemCode
     * @param field
     * @param value
     * @param permissionId
     * @return
     */
    public Boolean validExists(String systemCode,String field,String value,String permissionId);
    
    /**
     * 获取菜单按钮权限
     * @date 2015年2月5日 下午1:34:46
     * @author 吴炫
     * @param permissionId
     * @return
     */
    public Map<String, Integer>	queryMenuBtnPermission(String permissionNo, String userId, String sysCode);
    
    /**
     * 获取权限与菜单
     * @date 2015年2月11日 下午4:57:38
     * @author 李  红
     * @param systemCode
     * @param permissionId
     * @return
     */
    public JSONObject findPermissionForMenu(String systemCode,String permissionId); 
    /**
     * 获取权限与菜单
     * @date 2015年2月11日 下午4:57:38
     * @author 李  红
     * @param systemCode
     * @param permissionId
     * @return
     */
    public List<Map<String,Object>> findBtnPermission(String btnPermission); 
    /**
     * 查看权限
     * @date 2015年2月26日 下午4:20:12
     * @author 李  红
     * @param permissionId
     * @return
     */
    public List<Map<String,Object>> findPermissionView(String permissionId); 
}
