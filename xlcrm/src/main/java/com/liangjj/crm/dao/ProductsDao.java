package com.liangjj.crm.dao;

import java.util.List;

import com.liangjj.crm.pojo.Product;

/**
 * @author 小亮
 *商品持久层接口
 */
public interface ProductsDao {
	public List<Product> getProductsById(Integer id);
}
