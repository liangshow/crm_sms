package com.liangjj.crm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.liangjj.crm.dao.BaseDictDao;
import com.liangjj.crm.pojo.BaseDict;
import com.liangjj.crm.service.BaseDictService;


/**
 * @author 小亮
 *分类表持久层操作实现类
 */
@Service
public class BaseDictServiceImpl implements BaseDictService{
	
	@Autowired
	private BaseDictDao baseDictDao;

	@Override
	public List<BaseDict> getDictList() {
		// TODO Auto-generated method stub
		return baseDictDao.getDictList();
	}

}
