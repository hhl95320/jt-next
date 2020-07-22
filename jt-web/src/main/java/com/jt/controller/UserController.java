package com.jt.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping({"/user/"})
public class UserController {

	/**
	 * 为了实现业务的功能拦截.html结尾的
	 * @return
	 */
	@RequestMapping("login")
	public String login() {
		return "login";
	}
	@RequestMapping("register")
	public String register() {
		return "register";
	}
}
