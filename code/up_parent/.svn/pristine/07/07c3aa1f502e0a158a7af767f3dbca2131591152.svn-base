package com.upsoft.systemweb.service.impl;

import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.upsoft.login.support.webservice.SysUtils;
import com.upsoft.system.bean.LogExpandBean;
import com.upsoft.system.bean.PageBean;
import com.upsoft.system.bean.TableColCommnetsBean;
import com.upsoft.system.constant.CommonConstant.QueryType;
import com.upsoft.system.entity.BaseEntity;
import com.upsoft.system.entity.LogDataOperationEntity;
import com.upsoft.system.entity.SysUser;
import com.upsoft.system.service.BaseServiceImpl;
import com.upsoft.systemweb.dao.LogDAO;
import com.upsoft.systemweb.service.LogService;

@Service
public class LogServiceImpl extends BaseServiceImpl implements LogService {

	@Autowired
	private LogDAO logDAO;

//	@Autowired
//	private EntityManager entityManager;
	

	@Override
	public void saveLog(HttpServletRequest request, BaseEntity entity) {
		LogExpandBean expanBean = null;
		this.saveLog(request, entity, expanBean);
	}
	

	@Override
	
	public void saveLog(HttpServletRequest request, BaseEntity entity,
			LogExpandBean expandBean) {

		// 根据entity获取修改的字段列表
		List<ColumnChangeBean> changeColumnList = this
				.getChangeColumnList(entity);

		// 获取日志操作类型
		Map<String, String> opTypeMap = this.getOpType(entity);

		// 获取登录用户信息
		SysUser user = SysUtils.getLoginSysUser(request);
		// 保存
		LogDataOperationEntity log = null;
		for (ColumnChangeBean changeColum : changeColumnList) {
			log = new LogDataOperationEntity();
			log.setLogType(opTypeMap.get("logType"));
			log.setUserName(user.getUserName());
			log.setUserId(user.getLoginId());
			log.setOperationTime(new Date());
			log.setOperationType(opTypeMap.get("operationType"));
			log.setColumnName(changeColum.getColumnName());
			log.setColumnDesc(changeColum.getColumnDesc());
			if (changeColum.getOldValue() != null) {
				log.setOldValue(changeColum.getOldValue().toString());
			}
			if (changeColum.getNewValue() != null) {
				log.setNewValue(changeColum.getNewValue().toString());
			}

			if (expandBean != null) {
				log.setRemark(expandBean.getRemark());
			}
			logDAO.save(log);
		}

	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> getLogListAndCount(Map<String, Object> pars,
			PageBean pageBean) {
		Object[] obj = this.getQuerySentence(pars, QueryType.LIST);
		Map<String, Object> userListAndCount = new HashMap<String, Object>();
		userListAndCount = logDAO.queryPaginationListBySql(obj[0].toString(),
				(Map<String, Object>) obj[1], pageBean);

		return userListAndCount;
	}

	/**
	 * 获取调用log日志服务的Controller层的类名及方法名
	 * 
	 * @date 2015年2月5日 下午4:46:09
	 * @author TW
	 * @return
	 */
	private Map<String, String> getOpType(BaseEntity entity) {

		Map<String, String> opTypeMap = new HashMap<String, String>();
		// Controller层调用的日志的服务的类名 和 方法名
		String logType = "";
		String operationType = "";
		StackTraceElement stacks[] = Thread.currentThread().getStackTrace();
		for (StackTraceElement stack : stacks) {
			String className = stack.getClassName();
			if (className.contains("com.upsoft")
					&& className.contains("Controller")) {
				className = className.substring(className.lastIndexOf(".") + 1,
						className.length());
				String methodName = stack.getMethodName();

				logType = getLogTypeByClassName(className);
				operationType = getOpTypeByMethodName(entity, methodName);

				opTypeMap.put("logType", logType);
				opTypeMap.put("operationType", operationType);

				break;
			}
		}
		return opTypeMap;
	}

	/**
	 * 根据Controller类名获取模块名称
	 * 
	 * @date 2015年2月6日 下午5:37:52
	 * @author TW
	 * @param className
	 *            Controller 类名
	 * @return
	 */
	private String getLogTypeByClassName(String className) {

		String logType = className;
		if (className.contains("DictTree")) {
			logType = "数据字典";
		} else if (className.contains("Log")) {
			logType = "日志";
		} else if (className.contains("Login")) {
			logType = "登录";
		} else if (className.contains("Menu")) {
			logType = "菜单";
		} else if (className.contains("Org")) {
			logType = "机构";
		} else if (className.contains("Permission")) {
			logType = "权限";
		} else if (className.contains("Role")) {
			logType = "角色";
		} else if (className.contains("User")) {
			logType = "用户";
		}

		if (!(logType.equals(className))) {
			logType = logType + "模块";
		}

		return logType;

	}

	/**
	 * 根据Controller层的方法名判断操作类型
	 * 
	 * @date 2015年2月6日 下午5:38:26
	 * @author TW
	 * @param methodName
	 *            Controller层方法名
	 * @return
	 */
	@SuppressWarnings("unused")
	private String getOpTypeByMethodName(String methodName) {

		String opType = methodName;

		return opType;

	}

	/**
	 * 根据需要操作的实体对象和Controller层的方法名联合判断操作类型
	 * 
	 * @date 2015年2月6日 下午5:39:07
	 * @author TW
	 * @param entity
	 *            操作的实体对象
	 * @param methodName
	 *            Controller层的方法名
	 * @return
	 */
	private String getOpTypeByMethodName(BaseEntity entity, String methodName) {

		String opType = methodName;

		String idValue = this.getVOIdValue(entity);
		if (StringUtils.isBlank(idValue)) {
			opType = "新增";
		} else if (methodName.contains("del")) {
			opType = "删除";
		} else {
			opType = "修改";
		}

		return opType;

	}

	/**
	 * 根据实体，获取操作前与操作后发生变化的字段列表
	 * 
	 * @date 2015年2月5日 下午4:17:20
	 * @author TW
	 * @param entity
	 *            操作对象的实体
	 * @return
	 */
	private List<ColumnChangeBean> getChangeColumnList(BaseEntity entity) {

		List<ColumnChangeBean> changeColumnList = new ArrayList<ColumnChangeBean>();
		ColumnChangeBean changeColumn = null;

		// 获取数据库中字段及字段对应的备注信息
		List<TableColCommnetsBean> colCommentsList = this
				.getColCommentsList(entity);

		// 获取操作对象修改前的数据
		BaseEntity oldEntity = (BaseEntity) this.findOne(entity.getClass(),
				this.getVOIdValue(entity));

		// 通过反射 遍历实体所有属性，找出发生改变的字段及修改前和修改后的值
		Field[] fields = entity.getClass().getDeclaredFields();
		for (int i = 0; i < fields.length; i++) {

			changeColumn = new ColumnChangeBean();
			String attrName = fields[i].getName();
			Class<?> attrType = fields[i].getType();
			Method method = getGetMethod(entity.getClass(), attrName);

			try {

				Object oldValue = null;
				Object newValue = null;

				// 通过反射获取 oldValue 和 newValue 的值
				if (oldEntity != null) {
					oldValue = method.invoke(oldEntity, new Object[0]);
				}
				newValue = method.invoke(entity, new Object[0]);

				// 排除""与null值被视为不等的情况,把null 全部转为""
				if (oldValue == null) {
					oldValue = "";
				}
				if (newValue == null) {
					newValue = "";
				}

				// 判断字段值是否有变动
				if (!(newValue.equals(oldValue))) {
					// 特殊类型格式化
					oldValue = this.formatObjectVal(attrType, oldValue);
					newValue = this.formatObjectVal(attrType, newValue);

					changeColumn.setColumnName(attrName);
					changeColumn.setOldValue(oldValue);
					changeColumn.setNewValue(newValue);
					changeColumn.setColumnDesc(this.getColRemark(
							colCommentsList, attrName));

					changeColumnList.add(changeColumn);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return changeColumnList;
	}

	/**
	 * 通过字段注解列表 和 字段名 获取字段备注信息
	 * 
	 * @date 2015年2月6日 下午1:23:28
	 * @author TW
	 * @param colCommentsList
	 *            字段注解列表
	 * @param colName
	 *            字段名
	 * @return
	 */
	private String getColRemark(List<TableColCommnetsBean> colCommentsList,
			String colName) {
		String colRemark = "";
		for (TableColCommnetsBean colInfo : colCommentsList) {
			if (colInfo.getColumn_name().equals(colName.toUpperCase())) {
				colRemark = colInfo.getComments();
			}
		}
		return colRemark;
	}

	/**
	 * 格式化Object对象值
	 * 
	 * @date 2015年2月6日 下午1:24:27
	 * @author TW
	 * @param classType
	 *            被格式化Object的具体类型
	 * @param val
	 *            被格式化的对象
	 * @return
	 */
	private String formatObjectVal(Class<?> classType, Object val) {

		if (val == null) {
			val = "";
		}
		if (StringUtils.isNotBlank(val.toString())) {
			if (classType == Date.class) {
				SimpleDateFormat sdf = new SimpleDateFormat(
						"yyyy-MM-dd HH:mm:ss");
				return sdf.format(val);
			}
		}

		return val.toString();
	}

	/**
	 * 根据实体获取数据库字段及字段备注信息
	 * 
	 * @date 2015年2月6日 下午12:50:23
	 * @author TW
	 * @param entity
	 * @return
	 */
	private List<TableColCommnetsBean> getColCommentsList(BaseEntity entity) {
		// 根据entity注解获取对应数据库中的表名
		String tableName = getTableNameByEntity(entity);

		return this.getColCommnetsList(tableName, "");
	}

	/**
	 * 根据表名及字段名获取数据库字段及字段备注信息
	 * 
	 * @date 2015年2月6日 上午9:41:53
	 * @author TW
	 * @param tableName
	 *            表名
	 * @param colName
	 *            字段名
	 * @return
	 */
	private List<TableColCommnetsBean> getColCommnetsList(String tableName,
			String colName) {

		List<TableColCommnetsBean> colCommnetsList = new ArrayList<TableColCommnetsBean>();

		StringBuffer sql = new StringBuffer();
		sql.append("select * from user_col_comments t where 1=1");
		Map<String, Object> parsCon = new HashMap<String, Object>();
		if (StringUtils.isNoneBlank(tableName)) {
			sql.append(" and t.table_name =:tableNameCon");
			parsCon.put("tableNameCon", tableName.toUpperCase());
		}

		if (StringUtils.isNoneBlank(colName)) {
			sql.append(" and t.column_name =:colNameCon");
			parsCon.put("colNameCon", colName.toUpperCase());
		}

        List<Map<String, Object>> colComnentsQueryList = logDAO.queryListBySql(sql.toString(), 0, 0, parsCon);
		TableColCommnetsBean colInfo = null;
		for (Map<String, Object> temp : colComnentsQueryList) {
			colInfo = new TableColCommnetsBean();
			colInfo.setTable_name(temp.get("table_name") == null ? "" : temp.get("table_name").toString());
            colInfo.setColumn_name(temp.get("column_name")==null ? "" : temp.get("column_name").toString());
            colInfo.setComments(temp.get("comments")==null ? "" : temp.get("comments").toString());
			colCommnetsList.add(colInfo);
		}
		return colCommnetsList;
	}

	/**
	 * 根据实体获取表名（注解方式）
	 * 
	 * @date 2015年2月6日 上午11:13:46
	 * @author TW
	 * @param entity
	 *            实体
	 * @return
	 */
	private String getTableNameByEntity(BaseEntity entity) {
		String tableName = "";
		Annotation[] annotations = entity.getClass().getAnnotations();
		for (int i = 0; i < annotations.length; i++) {
			String annoStr = annotations[i].toString();
			if (annoStr.contains("@javax.persistence.Table")) {
				annoStr = annoStr.substring(annoStr.lastIndexOf("name=") + 5);
				while (annoStr.contains(",")) {
					annoStr = annoStr.substring(0, annoStr.lastIndexOf(","));
				}
				tableName = annoStr;
			}
		}
		return tableName;

	}

	/**
	 * 根据实体，获取Id字段对应的值（根据注解确定id字段，通过反射获取其对应值）
	 * 
	 * @date 2015年2月5日 下午4:20:19
	 * @author TW
	 * @param entity
	 * @return
	 */
	private String getVOIdValue(BaseEntity entity) {

		Object idValue = "";
		Field[] fields = entity.getClass().getDeclaredFields();

		// 遍历Bean属性
		for (int i = 0; i < fields.length; i++) {
			Field field = fields[i];
			String attrName = field.getName();
			// 获取属性对应的get方法
			Method method = getGetMethod(entity.getClass(), attrName);

			Annotation[] attrAnnotations = field.getAnnotations();
			Annotation[] methodAnnotations = method.getAnnotations();

			// 遍历属性上的注解
			for (int j = 0; j < attrAnnotations.length; j++) {
				if (attrAnnotations[j] != null) {
					String annotaitonStr = attrAnnotations[j].toString();
					if (annotaitonStr.contains("javax.persistence.Id")) {
						try {
							idValue = method.invoke(entity, new Object[0]);
						} catch (Exception e) {
							e.printStackTrace();
						}
						break;
					}
				}
			}
			// 遍历方法上的注解
			for (int j = 0; j < methodAnnotations.length; j++) {
				if (methodAnnotations[j] != null) {
					String annotaitonStr = methodAnnotations[j].toString();
					if (annotaitonStr.contains("javax.persistence.Id")) {
						try {
							idValue = method.invoke(entity, new Object[0]);
						} catch (Exception e) {
							e.printStackTrace();
						}
						break;
					}
				}
			}
		}

		if (idValue != null) {
			return idValue.toString();
		}
		return "";
	}

	/**
	 * 获取Entity的get方法
	 * 
	 * @date 2015年2月4日 下午3:25:55
	 * @author TW
	 * @param objectClass
	 *            类型
	 * @param fieldName
	 *            字段名称
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private Method getGetMethod(Class objectClass, String fieldName) {
		StringBuffer sb = new StringBuffer();
		sb.append("get");
		sb.append(fieldName.substring(0, 1).toUpperCase());
		sb.append(fieldName.substring(1));
		try {
			return objectClass.getMethod(sb.toString());
		} catch (Exception e) {
		}
		return null;
	}

	/**
	 * 私有方法：公共查询语句拼装
	 * 
	 * @date 2015年2月3日 下午4:38:42
	 * @author TW
	 * @param pars
	 *            查询条件
	 * @param type
	 *            查询类型
	 * @return
	 */
	private Object[] getQuerySentence(Map<String, Object> pars, QueryType type) {

		StringBuffer sql = new StringBuffer();
		Map<String, Object> parsCon = new HashMap<String, Object>();

		switch (type) {
		case LIST:
			sql.append("select *");
			break;
		case COUNT:
			sql.append("select count(1)");
			break;

		default:
			sql.append("select count(1)");
			break;
		}

		sql.append(" from sys_t_log_info t where 1=1");

		if (pars.containsKey("optusername") && pars.get("optusername") != null
				&& StringUtils.isNotBlank(pars.get("optusername").toString())) {
			sql.append(" and t.opt_user_name like :optusername");
			parsCon.put("optusername", "%" + pars.get("optusername") + "%");
		}
		
		if (pars.containsKey("optmodelcode") && pars.get("optmodelcode") != null
				&& StringUtils.isNotBlank(pars.get("optmodelcode").toString())) {
			sql.append(" and t.model_code = :optmodelcode ");
			parsCon.put("optmodelcode", pars.get("optmodelcode") );
		}

		if (pars.containsKey("opttypecode") && pars.get("opttypecode") != null
				&& StringUtils.isNotBlank(pars.get("opttypecode").toString())) {
			sql.append(" and t.opt_type_code = :opttypecode ");
			parsCon.put("opttypecode", pars.get("opttypecode") );
		}
		if (pars.containsKey("belongsystemcode") && pars.get("belongsystemcode") != null
				&& StringUtils.isNotBlank(pars.get("belongsystemcode").toString())) {
			sql.append(" and t.belong_system_code = :belongsystemcode ");
			parsCon.put("belongsystemcode", pars.get("belongsystemcode") );
		}
		
		if (pars.containsKey("optTimeStart") && pars.get("optTimeStart") != null
				&& StringUtils.isNotBlank(pars.get("optTimeStart").toString())) {
			sql.append(" and opt_time >= :optTimeStart ");
			String optTimeStart = pars.get("optTimeStart").toString().replaceAll("-", "") + "000000";
			
			parsCon.put("optTimeStart", optTimeStart);
		}
		
		if (pars.containsKey("optTimeEnd") && pars.get("optTimeEnd") != null
				&& StringUtils.isNotBlank(pars.get("optTimeEnd").toString())) {
			sql.append(" and opt_time <= :optTimeEnd ");
			String optTimeEnd = pars.get("optTimeEnd").toString().replaceAll("-", "") + "235959";
			parsCon.put("optTimeEnd", optTimeEnd);
		}
		
		return new Object[] { sql, parsCon };

	}

	/**
	 * 内部类，修改字段的属性类
	 */
	private class ColumnChangeBean implements Serializable {

		private static final long serialVersionUID = -8868029314692260685L;
		private String columnName;
		private Object oldValue;
		private Object newValue;
		private String columnDesc;

		public String getColumnName() {
			return columnName;
		}

		public void setColumnName(String columnName) {
			this.columnName = columnName;
		}

		public Object getOldValue() {
			return oldValue;
		}

		public void setOldValue(Object oldValue) {
			this.oldValue = oldValue;
		}

		public Object getNewValue() {
			return newValue;
		}

		public void setNewValue(Object newValue) {
			this.newValue = newValue;
		}

		public String getColumnDesc() {
			return columnDesc;
		}

		public void setColumnDesc(String columnDesc) {
			this.columnDesc = columnDesc;
		}

	}
}
