package com.liangjj.crm.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.liangjj.crm.service.UserService;

public class UserDetailsServiceImpl implements UserDetailsService{
	
	@Autowired
	private UserService userService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		System.out.println("安全验证开始");
		
		com.liangjj.crm.pojo.User user = userService.getUserByName(username);
		
		if (user!=null) { 
			//System.out.println("安全验证通过:"+user.getUsername()+"  密码："+user.getPassword());
			List<GrantedAuthority> grantAuths=new ArrayList();
			grantAuths.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
	        return new User(username,user.getPassword(), grantAuths);
		}
		System.out.println("安全验证失败");
		return null;
		
//		List<GrantedAuthority> grantAuths=new ArrayList();
//		grantAuths.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
//		return new User(username,"123456",grantAuths);

	}
	
}
