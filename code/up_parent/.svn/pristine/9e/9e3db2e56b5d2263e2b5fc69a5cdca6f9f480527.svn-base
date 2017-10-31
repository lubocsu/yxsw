package com.upsoft.systemweb.service;

import java.util.List;
import java.util.Map;

import com.upsoft.system.bean.PageBean;
import com.upsoft.system.entity.DictTreeDataEntity;
import com.upsoft.system.service.BaseService;

/**
* Copyright (c) 2015,重庆扬讯软件技术有限公司<br>
* All rights reserved.<br>
*
* 文件名称：DictTreeDataService.java<br>
* 摘要：数据字典节点服务层<br>
* -------------------------------------------------------<br>
* 当前版本：1.1.1<br>
* 作者：Awesan<br>
* 完成日期：2015年1月22日<br>
* -------------------------------------------------------<br>
* 取代版本：1.1.0<br>
* 原作者：Awesan<br>
* 完成日期：2015年1月22日<br>
*/
public interface DictTreeDataService extends BaseService{
	
	/**
	 * 分页查询字典树节点数据
	 * @date 2015年1月22日 下午3:56:39
	 * @author Awesan
	 * @param pageNo
	 * @param pageSize
	 * @param pars
	 * @return 
	 */
	public List<DictTreeDataEntity> queryDictTreeDataByPage(int pageNo, int pageSize, Map<String, Object> pars);
	
	/**
	 * 异步返回treeId下一级节点
	 * @date 2015年2月3日 下午4:24:56
	 * @author Awesan
	 * @return 
	 */
	public Map<String, Object> queryDictDataByAjax(Map<String, Object> pars, PageBean pageBean);
	
	/**
	 * 异步获取子节点(只获取下一级子节点)
	 * @date 2015年2月3日 下午5:31:40
	 * @author Awesan
	 * @param pars
	 * @return 
	 */
	public Map<String, Object> queryChildDataByAjax(String parentId);
	
	/**
	 * 查询字典数节点记录数
	 * @date 2015年1月22日 下午5:25:03
	 * @author Awesan
	 * @param pars
	 * @return 
	 */
	public Long queryDictDataCount(Map<String, Object> pars);
	
	/**
	 * 返回目录下的数据项
	 * @date 2015年1月26日 下午12:05:03
	 * @author Awesan
	 * @param dictName
	 * @return 
	 */
	public List<DictTreeDataEntity> queryDataByTreeId(String id);
	
	/**
	 * 返回数据项下的子数据项
	 * @date 2015年1月29日 上午11:41:09
	 * @author Awesan
	 * @param id
	 * @return 
	 */
	public List<DictTreeDataEntity> queryDataByParentId(String id);
	
	/**
	 * 返回某字典下最大排序号
	 * @date 2015年1月28日 下午1:55:08
	 * @author Awesan
	 * @return 
	 */
	public Long queryMaxOrderNoByTreeId(String treeId);
	
	/**
	 * 根据字典ID和数据Code返回数据项
	 * @date 2015年1月28日 下午5:31:46
	 * @author Awesan
	 * @param treeId
	 * @param nodeCode
	 * @return 
	 */
	public DictTreeDataEntity queryByTreeIdAndNodeCode(String treeId, String nodeCode);
	
	public DictTreeDataEntity queryByTreeIdAndData1(String treeId, String data1);
	
	public DictTreeDataEntity queryByParentIdAndNodeCode(String parentId, String nodeCode);
	
	/**
	 * 根据Id批量删除
	 * @date 2015年1月29日 上午10:01:49
	 * @author Awesan
	 * @param id
	 * @return 
	 */
	public boolean deleteByIds(String[] id);
	
	/**
	 * 根据字典ID返回所有子数据项
	 * 默认 key 为 code， value 为 data1
	 * @date 2015年1月30日 上午11:26:14
	 * @author 蒋迪
	 * @param treeId	字典ID
	 * @return 
	 */
	public Map<String, String> queryByTreeId(String treeId);
	
	/**
	 * 根据 字典nodeId返回所有子数据项
	 * @date 2015年2月3日 上午10:56:10
	 * @author Awesan
	 * @param nodeId
	 * @return 
	 */
	public List<DictTreeDataEntity> queryDataByNodeId(String nodeId);
	
	/**
	 * 检查字段值在treeId下是否已存在
	 * @date 2015年2月3日 下午2:46:20
	 * @author Awesan
	 * @param treeId
	 * @param fieldName
	 * @param fieldvalue
	 * @return 
	 */
	public List<DictTreeDataEntity> checkRepeatInTreeId(String treeId, String fieldName, String fieldvalue);
	
	/**
	 * 根据nodeId返回treeId
	 * @date 2015年2月9日 下午4:07:15
	 * @author Awesan
	 * @param nodeId
	 * @return 
	 */
	public String queryTreeIdByNodeId(String nodeId);
	
	/**
	 * 只获取第一级数据
	 * @date 2015年3月16日 下午2:14:50
	 * @author 吴炫
	 * @param nodeId
	 * @return
	 */
	public List<DictTreeDataEntity> queryRootDateByPid(String nodeId);
	
	/**
	 * 获取手机版本号
	 * @date 2015年3月31日 下午8:57:00
	 * @author 冉恒鑫
	 * @param nodeId
	 * @return
	 */
	public Map<String, Object> queryMobileVersion();
	
	/**
	 * 获取问题反馈类型
	 * @date 2015年4月2日 下午7:16:01
	 * @author 冉恒鑫
	 * @return
	 */
	public List<Map<String, Object>> getFeedbackType();
}
