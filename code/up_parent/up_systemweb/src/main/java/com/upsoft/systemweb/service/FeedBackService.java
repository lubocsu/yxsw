package com.upsoft.systemweb.service;

import java.util.List;
import java.util.Map;

import com.upsoft.system.bean.PageBean;
import com.upsoft.system.entity.FeedBackEntity;
import com.upsoft.system.service.BaseService;

/**
* Copyright (c) 2015,重庆扬讯软件技术有限公司<br>
* All rights reserved.<br>
*
* 文件名称：InfoFeedbackService.java<br>
* 摘要：信息反馈SERVICE<br>
* -------------------------------------------------------<br>
* 当前版本：1.1.1<br>
* 作者：李  红<br>
* 完成日期：2015年4月2日<br>
* -------------------------------------------------------<br>
* 取代版本：1.1.0<br>
* 原作者：李  红<br>
* 完成日期：2015年4月2日<br>
 */

public interface FeedBackService extends BaseService {
    /**
     * 分页查询权限List和总数
     * @date 2015年4月2日 下午2:13:06
     * @author 李  红
     * @param systemCode
     * @param title
     * @param content
     * @param pageBean
     * @return
     */
    public Map<String, Object> findListAndCount(Map<String,Object> params, PageBean pageBean);

    /**
     * 提交保存
     * @date 2015年4月2日 下午2:13:12
     * @author 李  红
     * @param infoFeedbackEntity
     */
    public void saveOrUpdate(FeedBackEntity feedbackEntity);

    /**
     * 删除信息
     * @date 2015年4月2日 下午2:13:21
     * @author 李  红
     * @param infoFeedbackEntity
     */
    public void delete(FeedBackEntity feedbackEntity);

    /**
     * 查询子系统定义信息 
     * @date 2015年4月2日 下午2:13:38
     * @author 李  红
     * @return
     */
    public List<Map<String,Object>> findSystemDefine();

    /**
     * 查询单个信息
     * @date 2015年4月2日 下午2:13:55
     * @author 李  红
     * @param id
     * @return
     */
    public Map<String,Object> findOne(String id);
}
