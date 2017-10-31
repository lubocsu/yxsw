package com.upsoft.systemweb.service;

import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.upsoft.system.bean.PageBean;
import com.upsoft.system.entity.SysRoleEntity;

/**
 * Copyright (c) 2015,重庆扬讯软件技术有限公司<br>
 * All rights reserved.<br>
 * 
 * 文件名称：RoleManageService.java<br>
 * 摘要：简要描述本文件的内容<br>
 * -------------------------------------------------------<br>
 * 当前版本：1.1.1<br>
 * 作者：李 红<br>
 * 完成日期：2015年1月22日<br>
 * -------------------------------------------------------<br>
 * 取代版本：1.1.0<br>
 * 原作者：李 红<br>
 * 完成日期：2015年1月22日<br>
 */

public interface RoleService {
    /**
     * 查询角色信息
     * 
     * @date 2015年1月28日 下午5:47:10
     * @author 李 红
     * @param params
     * @return
     */
    public List<SysRoleEntity> findRoleList(Map<String, Object> params);

    /**
     * 分页查询角色List和总数
     * 
     * @date 2015年1月28日 下午5:47:26
     * @author 李 红
     * @param systemCode
     * @param roleName
     * @param pageBean
     * @return
     */
    public Map<String, Object> findRoleListAndCount(String systemCode, String roleName, PageBean pageBean,String loginId);

    /**
     * 保存角色信息
     * 
     * @date 2015年1月28日 下午5:48:03
     * @author 李 红
     * @param roleEntity
     */
    public void saveOrUpdateRole(SysRoleEntity roleEntity);

    /**
     * 删除角色信息
     * 
     * @date 2015年1月28日 下午5:48:15
     * @author 李 红
     * @param roleId
     */
    public void delRole(String roleId);

    /**
     * 查询子系统定义信息
     * 
     * @date 2015年1月28日 下午5:48:27
     * @author 李 红
     * @return
     */
    public List<Map<String,Object>> findSystemDefine();

    /**
     * 查询角色与用户树
     * 
     * @date 2015年1月28日 下午5:48:43
     * @author 李 红
     * @param roleId
     * @return
     */
    public JSONObject findUserForOrgTree(String roleId);

    /**
     * 查询角色权限树
     * 
     * @date 2015年1月28日 下午5:49:04
     * @author 李 红
     * @param roleId
     * @return
     */
    public List<Map<String,Object>> findPermissionForRole(String roleId,String systemCode);

    /**
     * 查询单个角色信息
     * 
     * @date 2015年1月28日 下午5:49:14
     * @author 李 红
     * @param roleId
     * @return
     */
    public SysRoleEntity findRoleById(String roleId);

    /**
     * 保存角色用户信息
     * 
     * @date 2015年1月28日 下午5:49:34
     * @author 李 红
     * @param roleId
     * @param userIds
     */
    public void saveRoleViewUser(String roleId, String userIds);

    /**
     * 保存角色权限信息
     * 
     * @date 2015年1月28日 下午5:49:52
     * @author 李 红
     * @param roleId
     * @param permissionIds
     */
    public void saveRoleViewPermission(String roleId, String permissionIds);

}
