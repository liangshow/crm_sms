package com.liangjj.crm.service;

import java.util.List;

import com.liangjj.crm.pojo.User;

public interface UserService {

	List<User> getUser(User user);

	public User getUserByName(String username);

	void addUser(User user);

	boolean findUserByName(String username);
	
}
