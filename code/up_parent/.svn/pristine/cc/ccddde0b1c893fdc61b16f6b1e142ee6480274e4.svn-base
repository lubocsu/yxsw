package com.upsoft.systemweb.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.upsoft.system.bean.ExcelHeaderBean;
import com.upsoft.system.bean.ExportExcelBean;
import com.upsoft.system.bean.PageBean;
import com.upsoft.system.entity.FeedBackEntity;
import com.upsoft.system.service.BaseServiceImpl;
import com.upsoft.systemweb.dao.FeedBackDao;
import com.upsoft.systemweb.service.ExportExcelDataSource;
import com.upsoft.systemweb.service.FeedBackService;

/**
 * Copyright (c) 2015,重庆扬讯软件技术有限公司<br>
 * All rights reserved.<br>
 *
 * 文件名称：InfoFeedbackServiceImpl.java<br>
 * 摘要：信息反馈业务实现类<br>
 * -------------------------------------------------------<br>
 * 当前版本：1.1.1<br>
 * 作者：李  红<br>
 * 完成日期：2015年4月2日<br>
 * -------------------------------------------------------<br>
 * 取代版本：1.1.0<br>
 * 原作者：李  红<br>
 * 完成日期：2015年4月2日<br>
 */
@Service("feedBackService")
public class FeedBackServiceImpl extends BaseServiceImpl implements FeedBackService,ExportExcelDataSource {

    @Autowired
    private FeedBackDao feedbackDao;
    /* (non-Javadoc)
     * @see com.upsoft.system.service.ExportExcelDataSource#getDataSource(java.util.Map, com.upsoft.system.bean.PageBean)
     */
    @Override
    public ExportExcelBean getDataSource(Map<String, Object> params, PageBean pageBean) {
	
	ExportExcelBean excelBean = new ExportExcelBean();
	//获取excel表数据
	@SuppressWarnings("unchecked")
	List<Map<String, Object>> data =(List<Map<String, Object>>) findListAndCount(params,pageBean).get("rows");
	// 组装表头（key值与数据源名称保持一致）
	List<ExcelHeaderBean> header = new ArrayList<ExcelHeaderBean>();
	header.add(new ExcelHeaderBean("title", "反馈标题"));
	header.add(new ExcelHeaderBean("content", "反馈内容"));
	header.add(new ExcelHeaderBean("systemname", "所属系统"));
	header.add(new ExcelHeaderBean("username", "反馈人"));
	header.add(new ExcelHeaderBean("createtime", "反馈时间"));
	excelBean.setData(data);
	excelBean.setHeader(header);
	return excelBean;
    }

    /* (non-Javadoc)
     * @see com.upsoft.system.service.InfoFeedbackService#findListAndCount(java.lang.String, java.lang.String, java.lang.String, com.upsoft.system.bean.PageBean)
     */
    @Override
    public Map<String, Object> findListAndCount(Map<String,Object> params, PageBean pageBean) {
	
	// TODO Auto-generated method stub
		StringBuffer queyrSQL=new StringBuffer();
		Map<String,Object> queryParams=new HashMap<String, Object>();
		queyrSQL.append("SELECT F.ID,F.TITLE,F.CONTENT,F.SYSTEMCODE,D.SYSTEMNAME,F.USERID,U.USERNAME,TO_CHAR(F.CREATETIME,'YYYY-MM-DD HH24:MI:SS') CREATETIME FROM SYS_FEEDBACK F LEFT JOIN SYS_USER U ON F.USERID=U.USERID LEFT JOIN SYS_SYSTEM_DEFINE D ON F.SYSTEMCODE=D.SYSTEMCODE WHERE 1=1  ");
		if(params.get("systemCode")!=null&&!"".equals(params.get("systemCode"))){
		    queyrSQL.append(" AND F.SYSTEMCODE=:systemCode");
		    queryParams.put("systemCode",params.get("systemCode"));
		}
		if(params.get("title")!=null&&!"".equals(params.get("title"))){
		    queyrSQL.append(" AND F.TITLE LIKE :title");
		    queryParams.put("title","%"+params.get("title")+"%");
		}
		if(params.get("content")!=null&&!"".equals(params.get("content"))){
		    queyrSQL.append(" AND F.CONTENT LIKE :content");
		    queryParams.put("content","%"+params.get("content")+"%");
		}
		if(params.get("beginTime")!=null&&StringUtils.isNotEmpty(params.get("beginTime")+"")){
		    queyrSQL.append(" AND F.CREATETIME >= TO_DATE(:beginTime,'YYYY-MM-DD HH24:MI:SS')");
		    queryParams.put("beginTime", params.get("beginTime")+" 00:00:00");
		}if(params.get("endTime")!=null&&StringUtils.isNotEmpty(params.get("endTime")+"")){
		    queyrSQL.append(" AND F.CREATETIME <= TO_DATE(:endTime,'YYYY-MM-DD HH24:MI:SS')");
		    queryParams.put("endTime", params.get("endTime")+" 23:59:59");
		}
		return feedbackDao.queryPaginationListBySql(queyrSQL+"", queryParams, pageBean);
    }

    /* (non-Javadoc)
     * @see com.upsoft.system.service.InfoFeedbackService#saveOrUpdate(com.upsoft.system.entity.InfoFeedbackEntity)
     */
    @Override
    public void saveOrUpdate(FeedBackEntity infoFeedbackEntity) {
	feedbackDao.saveAndFlush(infoFeedbackEntity);
	// TODO Auto-generated method stub
	
    }

    /* (non-Javadoc)
     * @see com.upsoft.system.service.InfoFeedbackService#delete(com.upsoft.system.entity.InfoFeedbackEntity)
     */
    @Override
    public void delete(FeedBackEntity infoFeedbackEntity) {
	feedbackDao.delete(infoFeedbackEntity);
	// TODO Auto-generated method stub
	
    }

    /* (non-Javadoc)
     * @see com.upsoft.system.service.InfoFeedbackService#findSystemDefine()
     */
    @Override
    public List<Map<String, Object>> findSystemDefine() {
	
	// TODO Auto-generated method stub
	return null;
    }

    /* (non-Javadoc)
     * @see com.upsoft.system.service.InfoFeedbackService#findOne(java.lang.String)
     */
    @Override
    public Map<String, Object> findOne(String id) {
	// TODO Auto-generated method stub
	Map<String,Object> params=new HashMap<String, Object>();
	params.put("id", id);
	return this.feedbackDao.queryListBySql("SELECT F.ID,F.TITLE,F.CONTENT,F.SYSTEMCODE,F.USERID,U.USERNAME,TO_CHAR(F.CREATETIME,'YYYY-MM-DD HH24:MI:SS') CREATETIME,DF.SYSTEMNAME FROM SYS_FEEDBACK F LEFT JOIN SYS_USER U ON F.USERID=U.USERID LEFT JOIN SYS_SYSTEM_DEFINE DF ON F.SYSTEMCODE=DF.SYSTEMCODE WHERE F.ID=:id", params).get(0);
    }

}
