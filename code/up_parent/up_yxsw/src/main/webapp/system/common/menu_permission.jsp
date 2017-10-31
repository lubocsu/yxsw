<%-- 
本文件为功能权限控制公共引入部分
示例：
<%@include file="/system/common/menu_permission.jsp" %>
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript">
var btnPermissions = {};
// 导入导出权限 （import 为关键字 所以需要特殊处理）
btnPermissions.export_ = '${KEY_FOR_IN_MENU_PERMISSION.export }'== '0' ? false : true; 
btnPermissions.import_ = '${KEY_FOR_IN_MENU_PERMISSION["import"] }'== '0' ? false : true; 
// 上传下载权限
btnPermissions.upfile_ = '${KEY_FOR_IN_MENU_PERMISSION.upfile }'== '0' ? false : true; 
btnPermissions.downfile_ = '${KEY_FOR_IN_MENU_PERMISSION.downfile }'== '0' ? false : true; 
// 增删改查权限
btnPermissions.add_ = '${KEY_FOR_IN_MENU_PERMISSION.add }'== '0' ? false : true; 
btnPermissions.remove_ = '${KEY_FOR_IN_MENU_PERMISSION.remove }'== '0' ? false : true;
btnPermissions.modify_ = '${KEY_FOR_IN_MENU_PERMISSION.modify }'== '0' ? false : true; 
btnPermissions.query_ = '${KEY_FOR_IN_MENU_PERMISSION.query }'== '0' ? false : true; 
</script>