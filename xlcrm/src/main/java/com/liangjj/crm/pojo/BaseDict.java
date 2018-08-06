package com.liangjj.crm.pojo;

/**
 * @author 小亮
 *分类表pojo
 */
public class BaseDict {
	private int id;
	private String industry;
	@Override
	public String toString() {
		return "BaseDict [id=" + id + ", industry=" + industry + "]";
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getIndustry() {
		return industry;
	}
	public void setIndustry(String industry) {
		this.industry = industry;
	}
	
	
}
