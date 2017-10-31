package com.upsoft.system.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.upsoft.system.bean.CustomFileBean;
import com.upsoft.system.bean.WSLoginInfoBean;
import com.upsoft.system.constant.CommonConstant;
import com.upsoft.system.dao.AttachmentDao;
import com.upsoft.system.entity.ComTAttachment;
import com.upsoft.system.service.AttachmentService;
import com.upsoft.system.service.BaseServiceImpl;
import com.upsoft.system.util.DateUtil;

@Service
public class AttachmentServiceImpl extends BaseServiceImpl implements AttachmentService{

	@Autowired
	private AttachmentDao attachmentDao;
	
	@Override
	public void saveAttachment(String businessId,String attachment,WSLoginInfoBean loginInfo) {
		if(StringUtils.isNotBlank(attachment)){
			//获取用户信息
			String userId = loginInfo.getUser().getUserId();
			String orgId = loginInfo.getUser().getOrgId();
			Date date = new Date();
			String time = DateUtil.dateToString(date, "yyyyMMddHHmmss");
			List<CustomFileBean> listBean = new Gson().fromJson(attachment,new TypeToken<List<CustomFileBean>>() {}.getType());
			int index = 1;
			for (CustomFileBean customFileBean : listBean) {
				ComTAttachment comTAttachment = new ComTAttachment();
				comTAttachment.setBusinessId(businessId);
				comTAttachment.setAttachmentIndex(index++);
				comTAttachment.setAttachmentPath(customFileBean.getLocalPath());
				comTAttachment.setAttachmentSize(customFileBean.getSize().toString());
				comTAttachment.setAttachmentSuffix(customFileBean.getSuffix());
				comTAttachment.setNewAttachmentName(customFileBean.getFileId());
				comTAttachment.setOldAttachmentName(customFileBean.getOldName());
				comTAttachment.setValid(CommonConstant.STATUS_VALID);
				
				comTAttachment.setCreator(userId);
				comTAttachment.setCreatorOrg(orgId);
				comTAttachment.setCreateTime(time);
				comTAttachment.setModifier(userId);
				comTAttachment.setModifierOrg(orgId);
				comTAttachment.setModifyTime(time);
				comTAttachment.setTimeStamp(String.valueOf(date.getTime()));
				
				attachmentDao.save(comTAttachment);
			}
			
		}
	}

	@Override
	public List<ComTAttachment> getAttachmentList(String noticeId) {
		
		String sql = "SELECT *  FROM COM_T_ATTACHMENT T WHERE T.BUSINESS_ID='"+ noticeId +"'";
		List<ComTAttachment> result = attachmentDao.queryListBySql(sql, new HashMap<String,Object>(),ComTAttachment.class);
		
		return result;
	}
	
	@Override
	public void delAttachment(String delAttachment){
		if(StringUtils.isNotBlank(delAttachment)){
			String[] ids = delAttachment.split(",");
			for (String id : ids) {
				
				attachmentDao.delete(findOne(ComTAttachment.class, id));
			}
		}
	}

	@Override
	public List<ComTAttachment> getAttachmentList(List<String> noticeIdList) {
		String sql = "SELECT *  FROM COM_T_ATTACHMENT T WHERE T.BUSINESS_ID in (:noticeIdList) ORDER BY T.BUSINESS_ID,T.ATTACHMENT_INDEX ASC";
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("noticeIdList", noticeIdList);
		List<ComTAttachment> result = attachmentDao.queryListBySql(sql, params,ComTAttachment.class);
		return result;
	}
}
