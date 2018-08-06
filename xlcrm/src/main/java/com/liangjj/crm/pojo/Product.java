package com.liangjj.crm.pojo;

import java.util.List;

/**
 * @author 小亮
 * 商品pojo类
 *
 */
public class Product {
	private Integer pid;
	private String name;
	private double price;
	private Integer number;
	
	public Integer getPid() {
		return pid;
	}
	public void setPid(Integer pid) {
		this.pid = pid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public Integer getNumber() {
		return number;
	}
	public void setNumber(Integer number) {
		this.number = number;
	}
	
	
}
