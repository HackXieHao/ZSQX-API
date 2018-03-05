package com.selfcreate.qingxie.controller.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.selfcreate.qingxie.bean.Msg;
import com.selfcreate.qingxie.bean.user.User;
import com.selfcreate.qingxie.service.user.UserService;

@RequestMapping(value = "/user/")
@Controller
public class UserController {

	@Autowired
	private UserService userService;
	
	@ResponseBody
	@RequestMapping(value = "getAll", method = RequestMethod.GET)
	public Msg getAll(){
		List<User> users = userService.getAll();
		return Msg.success("获取成功",users);
	}
	
}
