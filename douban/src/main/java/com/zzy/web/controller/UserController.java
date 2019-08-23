package com.zzy.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zzy.web.entity.UserTest;
import com.zzy.web.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	@Qualifier("userService")
	private UserService userService;

	@RequestMapping("/findOneUser.action")
	public String findOneUser(String id, HttpServletRequest request) {

		UserTest userTest = userService.findOne(id);

		System.out.println(userTest);
		request.setAttribute("id", userTest.getId());
		request.setAttribute("password", userTest.getPassword());

		return "success.jsp";
	}

	@RequestMapping("/findByidAndPasswd.action")
	public String findByidAndPasswd(String id, String password, HttpServletRequest request) {

		UserTest userTest = userService.findByidAndPasswd(id, password);

		System.out.println(userTest);
		request.setAttribute("id", userTest.getId());
		request.setAttribute("password", userTest.getPassword());

		return "success.jsp";
	}

}
