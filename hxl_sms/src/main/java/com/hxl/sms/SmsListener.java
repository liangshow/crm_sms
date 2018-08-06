package com.hxl.sms;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;

@Component
public class SmsListener {
	
	@Autowired
	private SmsUtil smsUtil;

	@JmsListener(destination="sms")
	public void sendSms(Map<String,String> map){
		
		try {
			System.out.println("mobile"+map.get("mobile"));
			System.out.println("template_code"+map.get("template_code"));
			System.out.println("sign_name"+map.get("sign_name"));
			System.out.println("param"+map.get("param"));
			SendSmsResponse response = smsUtil.sendSms(map.get("mobile"),
					map.get("template_code") ,
					map.get("sign_name")  , 
					map.get("param") );
			System.out.println("code:"+response.getCode());
			System.out.println("message:"+response.getMessage());
			
		
		} catch (ClientException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
