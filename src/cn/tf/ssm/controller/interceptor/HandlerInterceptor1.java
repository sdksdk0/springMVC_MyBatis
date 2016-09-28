package cn.tf.ssm.controller.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class HandlerInterceptor1 implements HandlerInterceptor{

	
	//执行时机：Handler执行之前，进行Handler方法之前
	//使用场景：用户认证、授权
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
			Object handler) throws Exception {
		return false;
	}
	
	
	//执行时间:进入handler方法，将handler返回ModelAndView之前执行
	
	@Override
	public void postHandle(HttpServletRequest arequest, HttpServletResponse response,
			Object handler, ModelAndView modeAndView) throws Exception {
		
		
	}

	//handler执行完成
	//统一日志记录，异常处理
	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		
		
	}

	
	

}
