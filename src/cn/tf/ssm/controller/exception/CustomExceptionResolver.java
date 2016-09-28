package cn.tf.ssm.controller.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

public class CustomExceptionResolver implements  HandlerExceptionResolver{

	@Override
	public ModelAndView resolveException(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex) {
		ex.	printStackTrace();	

		CustomException customException=null;
		
		//对于系统自定义异常类型直接获取异常信息
		if(ex instanceof CustomException){
			customException=(CustomException) ex;
		}else{
			//对于运行时异常，重新构造一个系统自定义的异常类型
			customException=new CustomException("系统忙，请稍后");
		}
		String message=customException.getMessage();
		ModelAndView modelAndView=new ModelAndView();
		modelAndView.addObject("message",message);
		modelAndView.setViewName("error");
				
		//将异常信息返回到页面
		return modelAndView;
	}

}
