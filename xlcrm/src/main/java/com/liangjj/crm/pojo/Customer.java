package com.liangjj.crm.pojo;

/**
 * @author 小亮
 *solr用户pojo
 */
public class Customer {
	private Integer id;
	private String cus_name;
	private String cus_sex;
	private String cus_address;
	private String cus_tel;
	private String cus_industry;
	private String level;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCus_name() {
		return cus_name;
	}
	public void setCus_name(String cus_name) {
		this.cus_name = cus_name;
	}
	public String getCus_sex() {
		return cus_sex;
	}
	public void setCus_sex(String cus_sex) {
		this.cus_sex = cus_sex;
	}
	public String getCus_address() {
		return cus_address;
	}
	public void setCus_address(String cus_address) {
		this.cus_address = cus_address;
	}
	public String getCus_tel() {
		return cus_tel;
	}
	public void setCus_tel(String cus_tel) {
		this.cus_tel = cus_tel;
	}
	public String getCus_industry() {
		return cus_industry;
	}
	public void setCus_industry(String cus_industry) {
		this.cus_industry = cus_industry;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	
	
}
