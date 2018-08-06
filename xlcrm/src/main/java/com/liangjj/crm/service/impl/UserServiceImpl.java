package com.liangjj.crm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.liangjj.crm.dao.UserDao;
import com.liangjj.crm.pojo.User;
import com.liangjj.crm.service.UserService;

/**
 * @author 小亮
 *用户业务层实现类
 */
@Transactional
@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserDao userDao;

	@Override
	public List<User> getUser(User user) {
		return userDao.getUser(user);
	}

	@Override
	public User getUserByName(String username) {
		List<User> users=userDao.getUserByName(username);
		if (users==null||users.size()<=0) {
			return null;
		}
		return users.get(0);
	}

	@Override
	public void addUser(User user) {
		userDao.addUser(user);
	}

	@Override
	public boolean findUserByName(String username) {
		// TODO Auto-generated method stub
		List<User> users=userDao.getUserByName(username);
		if (users.size()<=0) {
			return false;//该用户不存在 可以注册
		}else{
			return true;//用户存在，不能注册
		}
	}
	
	

}
