package com.liangjj.crm.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.Session;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.liangjj.crm.pojo.Result;
import com.liangjj.crm.pojo.User;
import com.liangjj.crm.service.UserService;


/**
 * 用户管理系统实现类
 * <p>Title: CustomerController</p>
 * <p>Description: </p>
 * <p>Company: www.itcast.cn</p> 
 * @version 1.0
 */
@RestController
public class UserController {

	
	//注入用户业务层实现类
	@Autowired
	private UserService userService;
	
	/**
	 * 用户注册
	 * @param user
	 * @return
	 */
	@RequestMapping(value="/user/add/{smscode}")
	public Result add(@PathVariable("smscode")String smscode,@RequestBody User user){
		
		boolean checkSmsCode =checkSmsCode(user.getMobile(), smscode);
		if(!checkSmsCode){
			return new Result(false, "验证码不正确");
		}
		try {
			userService.addUser(user);
			return new Result(true, "增加成功");
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(false, "增加失败");
		}
	}
	
	@Autowired
	private RedisTemplate redisTemplate;
	
	
	@Autowired
	private JmsTemplate jmsTemplate;
	
	@Autowired
	private Destination smsDestination;
	
//	@Value("${template_code}")
//	private String template_code;
//	
//	@Value("${sign_name}")
//	private String sign_name;

	
	
	/**
	 * 把手机号码和短信内容发送到activemq
	 * @param mobile
	 * @return
	 */
	@RequestMapping("/user/sendCode/{mobile}")
	public void sendCheckCode(@PathVariable("mobile") final String mobile){
		System.out.println("开始发送短信");
		//1生成六位的随机数（验证码）
		System.out.println("template_code:"+"SMS_140550373");
		final String smscode=(long)(Math.random()*1000000)+"";
		System.out.println(smscode);
		//2将验证码放入redis
		redisTemplate.boundHashOps("smscode").put(mobile, smscode);
		//3将短信内容发送给activeMQ
		jmsTemplate.send(smsDestination, new MessageCreator() {
			@Override
			public Message createMessage(Session session) throws JMSException {
				MapMessage message = session.createMapMessage();
				message.setString("mobile", mobile);//手机号
				message.setString("template_code", "SMS_140550373");//验证码模板
				message.setString("sign_name","亮晶晶");//签名
				Map map=new HashMap();
				map.put("number", smscode);				
				message.setString("param", JSON.toJSONString(map));
				return message;
			}
		});
	}
	
	
	/**
	 * 添加用户前检测验证码
	 * @param mobile
	 * @param code
	 * @return
	 */
	public boolean checkSmsCode(String mobile, String code) {
		 
		String systemcode= (String) redisTemplate.boundHashOps("smscode").get(mobile);
		if(systemcode==null){
			return false;
		}
		if(!systemcode.equals(code)){
			return false;
		}
		
		return true;
	}
	
	

}
