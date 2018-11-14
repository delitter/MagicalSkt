package com.sktelecom.t1.Applications;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.sktelecom.t1.Daos.UserMapper;
import com.sktelecom.t1.Models.User;

@Controller
@ResponseBody
@RequestMapping(value="/User")
public class UserController {

	@Autowired
	private UserMapper userMapper;
	
	//@Autowired
	//private UserService userService;

	@RequestMapping(value="/add", method = RequestMethod.POST)
	public String add(@RequestBody User user){
		userMapper.insert(user);
		return "success!";
	}
	
	@RequestMapping(value="/query", method = RequestMethod.GET)
	public List<User> query(){
		return userMapper.selectAll();
	}
}
