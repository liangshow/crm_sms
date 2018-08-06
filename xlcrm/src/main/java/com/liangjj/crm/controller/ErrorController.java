package com.liangjj.crm.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author 小亮
 *错误信息跳转到相关页面
 */
@Controller
@RequestMapping("error")
public class ErrorController {
	private static final String BASE_DIR = "error/";
	@RequestMapping("400")
	public String handle1(HttpServletRequest request){
		return BASE_DIR + "400";
	}
	@RequestMapping("404")
	public String handle2(HttpServletRequest request){
		return BASE_DIR + "404";
	}
	@RequestMapping("500")
	public String handle3(HttpServletRequest request){
		return BASE_DIR + "500";
	}

}
