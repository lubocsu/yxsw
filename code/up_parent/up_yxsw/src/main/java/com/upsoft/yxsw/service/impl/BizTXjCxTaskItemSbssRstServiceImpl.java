package com.upsoft.yxsw.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.Gson;
import com.upsoft.system.bean.CustomFileBean;
import com.upsoft.system.bean.WSLoginInfoBean;
import com.upsoft.system.service.AttachmentService;
import com.upsoft.system.service.BaseServiceImpl;
import com.upsoft.system.util.DateUtil;
import com.upsoft.system.util.ExceptionFormatUtil;
import com.upsoft.yxsw.constant.Constant;
import com.upsoft.yxsw.dao.BizTXjCxTaskItemSbssRstDAO;
import com.upsoft.yxsw.entity.BizTXjCxTaskItem;
import com.upsoft.yxsw.entity.BizTXjCxTaskItemFaultlc;
import com.upsoft.yxsw.entity.BizTXjCxTaskItemRstDet;
import com.upsoft.yxsw.entity.BizTXjCxTaskItemSbss;
import com.upsoft.yxsw.entity.BizTXjCxTaskItemSbssRst;
import com.upsoft.yxsw.mobile.bean.execute.CheckItemBean;
import com.upsoft.yxsw.mobile.bean.execute.CheckItemDetailBean;
import com.upsoft.yxsw.mobile.bean.execute.ExecuteSBSSBean;
import com.upsoft.yxsw.service.BizTGgSbssAttachBaseService;
import com.upsoft.yxsw.service.BizTXjCxTaskItemFaultlcService;
import com.upsoft.yxsw.service.BizTXjCxTaskItemRstDetService;
import com.upsoft.yxsw.service.BizTXjCxTaskItemSbssRstService;
import com.upsoft.yxsw.service.BizTXjCxTaskItemSbssService;


@Service
public class BizTXjCxTaskItemSbssRstServiceImpl extends BaseServiceImpl implements BizTXjCxTaskItemSbssRstService {

	private static final String S = File.separator;
	private static final String TOP = "uploadFiles";
	
	private Logger logger = Logger.getLogger(BizTXjCxTaskItemSbssRstServiceImpl.class);
	
	@Autowired
	private BizTXjCxTaskItemSbssService bizTXjCxTaskItemSbssService;
	@Autowired
	private BizTXjCxTaskItemRstDetService bizTXjCxTaskItemRstDetService;
//	@Autowired
//	private BizTXjCxTaskItemFaultlcService bizTXjCxTaskItemFaultlcService;
	@Autowired
	private BizTGgSbssAttachBaseService bizTGgSbssAttachBaseService;
	@Autowired
	private AttachmentService attachmentService;
	@Autowired
	private BizTXjCxTaskItemSbssRstDAO bizTXjCxTaskItemSbssRstDAO;
	
	@Override
	public List<String> saveUploadSbssExcuteInfo(ExecuteSBSSBean uploadData,WSLoginInfoBean loginInfo, Map<String, MultipartFile> fileMap,
			String uploadPath) throws Exception {
		String timestamp = DateUtil.dateToString(new Date(), DateUtil.DATE_FULL_FORMAT_N);
		List<String> savedAttachments = new ArrayList<String>();
		// 保存巡检任务巡检点设备与设施表检查项结果
		List<BizTXjCxTaskItemSbssRst> sbssRstList = new ArrayList<BizTXjCxTaskItemSbssRst>();
		// 保存错误巡检结果流程
		BizTXjCxTaskItemFaultlc faultRst = null;
		// 更新巡检任务巡检点设备与设施记录
		BizTXjCxTaskItemSbss taskItemSbss = bizTXjCxTaskItemSbssService.findOne(BizTXjCxTaskItemSbss.class, uploadData.getTtaskItemSbssId());
		taskItemSbss.setHaveComplete(Long.valueOf(Constant.YES_NO.YES.getValue()));
		taskItemSbss.setXjDesc(uploadData.getSbssCxDesc());
		// 结果是否正常
		boolean rstSfzc = true;
		List<CheckItemBean> checkItemBeanList = uploadData.getCheckItemList();
		for (CheckItemBean checkItemBean : checkItemBeanList) {
			BizTXjCxTaskItemSbssRst sbssRst = new BizTXjCxTaskItemSbssRst();
			sbssRst.setTaskItemSbssId(uploadData.getTtaskItemSbssId());
			sbssRst.setInputType(checkItemBean.getInputType());
			sbssRst.setCheckValue(checkItemBean.getCheckItemValue());
			sbssRst.setCheckItemName(checkItemBean.getCheckItemName());
			sbssRst.setCheckItemCode(checkItemBean.getCheckItemCode());
			sbssRstList.add(sbssRst);
			if(StringUtils.equals(sbssRst.getInputType(), Constant.CHECKITEM_INPUTTYPE_VALUE.RADIO.getValue())){
				// 保存巡检结果检查项选项明细表
				List<BizTXjCxTaskItemRstDet> rstDetList = new ArrayList<BizTXjCxTaskItemRstDet>();
				List<CheckItemDetailBean> checkItemDetList = checkItemBean.getDetailList();
				long index = 1;
				for (CheckItemDetailBean checkItemDetBea : checkItemDetList) {
					BizTXjCxTaskItemRstDet rstDet = new BizTXjCxTaskItemRstDet();
					rstDet.setSelName(checkItemDetBea.getSelName());
					rstDet.setSelSort(index++);
					rstDet.setSelValue(checkItemDetBea.getSelValue());
					rstDet.setSfmr(checkItemDetBea.getSfmr());
					rstDet.setSfzc(checkItemDetBea.getSfzc());
					rstDetList.add(rstDet);
					// 判断当前选择结果是否正常
					if(StringUtils.equals(sbssRst.getCheckValue(), rstDet.getSelValue())
							&&StringUtils.equals(rstDet.getSfzc(),Constant.SFZC.NO.getValue())){
						rstSfzc = false;
					}
				}
				
				sbssRst.setRstDetList(rstDetList);
			}
			sbssRstList.add(sbssRst);
		}
		if(!rstSfzc){
			faultRst = new BizTXjCxTaskItemFaultlc();
			// 保存错误信息处理流程
			faultRst.setOptContent(uploadData.getName()+"检查结果异常："+uploadData.getSbssCxDesc());
			faultRst.setOptId(loginInfo.getUser().getUserId());
			faultRst.setOptName(loginInfo.getUser().getUserName());
			faultRst.setOptTime(timestamp);
			faultRst.setTtaskItemSbssId(uploadData.getTtaskItemSbssId());
			taskItemSbss.setSfFault(Integer.valueOf(Constant.YES_NO.YES.getValue()));
		}else{
			taskItemSbss.setSfFault(Integer.valueOf(Constant.YES_NO.NO.getValue()));
		}
		
		/*****************************************开始保存流程*********************************************/
		// 1、更新巡检任务巡检点设备与设施记录
		bizTXjCxTaskItemSbssService.update(taskItemSbss);
		// 2、保存巡检任务巡检点设备与设施表检查项结果
		for (BizTXjCxTaskItemSbssRst sbssRst : sbssRstList) {
			BizTXjCxTaskItemSbssRst saved = save(sbssRst);
			if(null!=saved&&null!=sbssRst.getRstDetList()){
				for (BizTXjCxTaskItemRstDet rstDet : sbssRst.getRstDetList()) {
					rstDet.setSbssRstId(saved.getSbssRstId());
					bizTXjCxTaskItemRstDetService.save(rstDet);
				}
			}
		}
		// 3、保存错误巡检结果流程
//		if(null!=faultRst){
//			bizTXjCxTaskItemFaultlcService.save(faultRst);
//		}
		// 4、保存结果附件
		try {
			savedAttachments = saveResultFile(fileMap, timestamp, uploadData, uploadPath, savedAttachments,loginInfo);
		} catch (Exception e) {
			logger.error("保存巡检结果附件时出错："+ExceptionFormatUtil.formatExceptionTrace(e));
			throw e;
		}
		// 5、检查巡检点设备完成情况
		updateTaskItemCompleteStatus(uploadData.getTaskItemId());
		
		return savedAttachments;
	}

	private void updateTaskItemCompleteStatus(String taskItemId) {
		List<BizTXjCxTaskItemSbss> taskItemSbssList = bizTXjCxTaskItemSbssService.getEntityListByTaskItemId(taskItemId);
		long count = 0;
		for (BizTXjCxTaskItemSbss taskItemSbss : taskItemSbssList) {
			if(taskItemSbss.getHaveComplete()==Long.valueOf(Constant.YES_NO.YES.getValue())){
				count++;
			}
		}
		if(taskItemSbssList.size()==count){
			BizTXjCxTaskItem taskItem = findOne(BizTXjCxTaskItem.class, taskItemId);
			taskItem.setHaveComplete(Long.valueOf(Constant.YES_NO.YES.getValue()));
			taskItem.setOptTime(DateUtil.dateToString(new Date(), DateUtil.DATE_FULL_FORMAT_N));
		}
	}

	private List<String> saveResultFile(Map<String, MultipartFile> fileMap,String timestamp,ExecuteSBSSBean uploadData,String uploadPath,List<String> savedAttachments,WSLoginInfoBean loginInfo) throws Exception {
		int index = 1;
		for (String key : fileMap.keySet()) {
			MultipartFile multiFile = fileMap.get(key);
			String oldName = multiFile.getOriginalFilename();
			String onlyName = oldName.substring(0, oldName.indexOf("."));
			String onlySuffix = oldName.substring(oldName.indexOf(".")+1, oldName.length());
			String newName = String.valueOf(System.currentTimeMillis())+"-"+(index++) ;
			
			List<CustomFileBean> dataArr = new ArrayList<CustomFileBean>();
			CustomFileBean data = new CustomFileBean();
			// 标准数据返回格式
			data.setFileId(newName);
			data.setSuccess(true);
			data.setMsg(StringUtils.EMPTY);
			data.setOldName(new String(onlyName.getBytes("UTF-8"),"ISO8859-1"));
			data.setSize(multiFile.getSize());
			data.setSuffix(onlySuffix);
			data.setLocalPath(S+TOP+S+"resultFiles"+S+timestamp+S + newName+"."+onlySuffix);
			dataArr.add(data);
			attachmentService.saveAttachment(uploadData.getTtaskItemSbssId(),new Gson().toJson(dataArr), loginInfo);
			
			File localFile = new File(uploadPath + data.getLocalPath());
			if(!localFile.getParentFile().exists()){
				localFile.getParentFile().mkdirs();
			}
			multiFile.transferTo(localFile);
			savedAttachments.add(localFile.getAbsolutePath());
		}
		return savedAttachments;
	}

	@Override
	public List<CheckItemBean> getListByTaskItemSbssId(String ttaskItemSbssId) {
		String sql = "select t.* from  BIZ_T_XJ_CX_TASK_ITEM_SBSS_RST t where t.task_item_sbss_id='"+ttaskItemSbssId+"'";
		List<BizTXjCxTaskItemSbssRst> rstList = bizTXjCxTaskItemSbssRstDAO.queryListBySql(sql, new HashMap<String,Object>(), BizTXjCxTaskItemSbssRst.class);
		List<CheckItemBean> checkItemList = new ArrayList<CheckItemBean>();
		if(rstList.size()>0){
			for (BizTXjCxTaskItemSbssRst rst : rstList) {
				CheckItemBean ci = new CheckItemBean();
				ci.setCheckItemCode(rst.getCheckItemCode());
				ci.setCheckItemName(rst.getCheckItemName());
				ci.setCheckItemValue(rst.getCheckValue());
				ci.setInputType(rst.getInputType());
				List<CheckItemDetailBean> cidList = bizTXjCxTaskItemRstDetService.getTaskItemRstDetListByRstId(rst.getSbssRstId());
				ci.setDetailList(cidList);
				checkItemList.add(ci);
			}
		}else{
			// 首次获取信息，需要重检查项关联关系表里读取检查项
			checkItemList = bizTGgSbssAttachBaseService.getAttachInfoByTaskItemSbssId(ttaskItemSbssId);
		}
		return checkItemList;
	}
	
}
