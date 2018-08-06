package com.liangjj.crm.pojo;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * @author 小亮
 * 数据库客户信息pojo类
 *
 */
public class DBCustomer {
	private Integer cid;
	private String cus_name;
	@DateTimeFormat(pattern="yyyy-MM-dd")  
	@JsonFormat(pattern="yyyy-MM-dd")  
	private Date cus_birthday;
	private String cus_sex;
	private String cus_address;
	private String cus_industry;
	private String cus_level;
	private String telnum;
		  
	public Integer getCid() {
		return cid;
	}
	public void setCid(Integer cid) {
		this.cid = cid;
	}
	public String getCus_name() {
		return cus_name;
	}
	public void setCus_name(String cus_name) {
		this.cus_name = cus_name;
	}
	public Date getCus_birthday() {
		return cus_birthday;
	}
	public void setCus_birthday(Date cus_birthday) {
		this.cus_birthday = cus_birthday;
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
	public String getCus_industry() {
		return cus_industry;
	}
	public void setCus_industry(String cus_industry) {
		this.cus_industry = cus_industry;
	}
	public String getCus_level() {
		return cus_level;
	}
	public void setCus_level(String cus_level) {
		this.cus_level = cus_level;
	}
	public String getTelnum() {
		return telnum;
	}
	public void setTelnum(String telnum) {
		this.telnum = telnum;
	}
	@Override
	public String toString() {
		return "DBCustomer [cid=" + cid + ", cus_name=" + cus_name + ", cus_birthday=" + cus_birthday + ", cus_sex="
				+ cus_sex + ", cus_address=" + cus_address + ", cus_industry=" + cus_industry + ", cus_level="
				+ cus_level + ", telnum=" + telnum + "]";
	}
	  
	
}
