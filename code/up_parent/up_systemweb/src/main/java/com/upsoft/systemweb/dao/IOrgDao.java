package com.upsoft.systemweb.dao;

import java.io.Serializable;

import com.upsoft.system.dao.IBaseDao;
import com.upsoft.system.entity.SysOrgEntity;

public interface IOrgDao extends IBaseDao<SysOrgEntity, Serializable> {
	public SysOrgEntity findByOrgId(String orgid);
}
