package com.liangjj.crm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.liangjj.crm.pojo.Product;
import com.liangjj.crm.service.ProductsService;

/**
 * @author 小亮
 *商品增删查改实现类
 */
@Controller
public class ProductsController {
	
	//注入商品业务层实现类
	@Autowired
	private ProductsService productsService;
	
	
	/**
	 * ajax异步请求，根据客户id查询相关商品信息
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/product/list")
	public @ResponseBody List<Product> getProductsById(@RequestParam("id")Integer id) throws Exception{
		//调用业务层查询商品数据
		List<Product> products=productsService.getProductsById(id);
		
		return products;
	}
}
