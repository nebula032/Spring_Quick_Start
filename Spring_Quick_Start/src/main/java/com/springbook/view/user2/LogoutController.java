package com.springbook.view.user2;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LogoutController {

	@RequestMapping("SpringMVC/logout.do")
	public String handleRequest(HttpSession session) {
		System.out.println("===> LogoutController(������̼�) �α׾ƿ� ó��");
		
		session.invalidate();

		return "redirect:login.do";
	}
}
