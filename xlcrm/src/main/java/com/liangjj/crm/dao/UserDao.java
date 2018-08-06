package com.liangjj.crm.dao;

import java.util.List;

import com.liangjj.crm.pojo.User;

/**
 * @author 小亮
 *用户持久层接口
 */
public interface UserDao {

	List<User> getUser(User user);

	List<User> getUserByName(String username);

	void addUser(User user);


}
