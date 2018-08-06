package com.liangjj.crm.service;

import java.util.List;

import com.liangjj.crm.pojo.Product;

public interface ProductsService {
	public List<Product> getProductsById(Integer id);
}
