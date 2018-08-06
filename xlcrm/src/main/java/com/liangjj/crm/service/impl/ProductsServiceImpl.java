package com.liangjj.crm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.liangjj.crm.dao.ProductsDao;
import com.liangjj.crm.pojo.Product;
import com.liangjj.crm.service.ProductsService;

/**
 * @author 小亮
 * 查询商品的业务层
 */
@Service
public class ProductsServiceImpl implements ProductsService{
	
	@Autowired
	private ProductsDao productsDao;

	@Override
	public List<Product> getProductsById(Integer id) {
		return productsDao.getProductsById(id);
	}
	

	
}
