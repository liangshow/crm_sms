package com.liangjj.crm.pojo;

import java.security.KeyStore.PrivateKeyEntry;

//用户登陆信息pojo类
public class User {
	private int id;
	private String username;
	private String password;
	private String mobile;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
	
}
