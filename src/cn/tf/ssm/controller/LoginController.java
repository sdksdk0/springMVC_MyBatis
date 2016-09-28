package cn.tf.ssm.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {
	
	@RequestMapping("/login.action")
	public String login(HttpSession session,String username,String password){
		
		session.setAttribute("activeUser", username);
		return "redirect:/items/queryItems.action";
	}
}
