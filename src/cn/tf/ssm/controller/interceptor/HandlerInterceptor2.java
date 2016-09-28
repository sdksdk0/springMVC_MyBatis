package cn.tf.ssm.controller.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;



public class HandlerInterceptor2 implements HandlerInterceptor {

	
	//执行时机：Handler执行之前，进入Handler方法之前
	//使用场景：用户认证、授权（登陆拦截、操作权限的拦截）
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		System.out.println("HandlerInterceptor2..preHandle");
		
		return false;
	}

	//执行时机：进入Handler方法，在Handler返回ModelAndView之前执行
	//使用场景：通过操作modelAndView统一处理一些模型数据或视图页面
	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		System.out.println("HandlerInterceptor2..postHandle");
	}
	
	//执行时机：Handler执行完成
	//使用场景 ：统一操作日志记录、异常处理
	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		//记录用户操作日志
		//内容：哪个用户？什么时间？操作什么方法？操作结果？
		System.out.println("HandlerInterceptor2..afterCompletion");
	}

}
