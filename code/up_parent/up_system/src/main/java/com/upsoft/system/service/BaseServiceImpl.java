package com.upsoft.system.service;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.transaction.annotation.Transactional;

import com.upsoft.system.entity.BaseEntity;

/**
* Copyright (c) 2015,重庆扬讯软件技术有限公司<br>
* All rights reserved.<br>
*
* 文件名称：BaseServiceImpl.java<br>
* 摘要：简要描述本文件的内容<br>
* -------------------------------------------------------<br>
* 当前版本：1.1.1<br>
* 作者：蒋迪<br>
* 完成日期：2015年1月23日<br>
* -------------------------------------------------------<br>
* 取代版本：1.1.0<br>
* 原作者：蒋迪<br>
* 完成日期：2015年1月23日<br>
*/
public abstract class BaseServiceImpl  implements BaseService{
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	@Transactional
	public <T extends BaseEntity> T save(T t) {
		entityManager.persist(t);
		return t;
	}

	@Override
	public <T extends BaseEntity> T findOne(Class<T> classT, Serializable s) {
		return entityManager.find(classT, s);
	}

	@Override
	@Transactional
	public <T extends BaseEntity> Boolean delete(Class<T> classT, Serializable key) {
		try {
			entityManager.remove(entityManager.find(classT, key));
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	@Transactional
	public <T extends BaseEntity>Boolean deleteBatch(Class<T> classT, List<Serializable> keys) {
		try {
			for (Serializable key : keys) {
				delete(classT, key);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	@Override
	@Transactional
	public <T extends BaseEntity> T update(T t) {
		return entityManager.merge(t);
	}

}
