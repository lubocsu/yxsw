package com.upsoft.system.service;

import java.util.List;

import com.upsoft.system.bean.WSLoginInfoBean;
import com.upsoft.system.entity.ComTAttachment;

public interface AttachmentService extends BaseService{

	/**
	 * 保存附件
	 * @date 2017年9月14日 上午10:34:09
	 * @author 陈涛
	 * @businessId 业务ID
	 * @param attachment 保存附件基本信息字符串
	 * @loginInfo 登录用户信息
	 */
	public void saveAttachment(String businessId,String attachment,WSLoginInfoBean loginInfo);

	/**
	 * 通过业务ID获取附件
	 * 
	 * @date 2017年9月14日 下午2:04:25
	 * @author 陈涛
	 * @param noticeId
	 * @return 
	 */
	public List<ComTAttachment> getAttachmentList(String noticeId);
	
	/**
	 * 通过业务ID批量获取附件，按序号正序排序
	 * @param noticeIdList
	 */
	public List<ComTAttachment> getAttachmentList(List<String> noticeIdList);
	
	/**
	 * 批量删除附件记录
	 * @date 2017年9月14日 下午9:01:51
	 * @author 陈涛
	 * @param delAttachment 附件主键字符串，以逗号分隔
	 */
	public void delAttachment(String delAttachment);

}
