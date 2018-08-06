package com.liangjj.crm.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;



/**
 * @author 小亮
 * 登陆拦截器
 *
 */
public class LoginInterceptor implements HandlerInterceptor{

	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object arg2) throws Exception {
		//如果访问登陆页面，放行
		String uri=request.getRequestURL().toString();
		if(uri.contains("login")|uri.contains("css")|uri.contains("js")|uri.contains("jpg")|uri.contains("ont")){
			return true;
		}
		
		//a)拦截用户请求，判断用户是否登陆
		HttpSession session = request.getSession();
		Object username=session.getAttribute("username");
		if(username!=null){
			//b)用户已经登陆，放行
			return true;
		}
		//c)如果用户还没有登陆，跳转到登陆页面
		response.sendRedirect(request.getContextPath()+"/login.action");
		return false;
	}

}
