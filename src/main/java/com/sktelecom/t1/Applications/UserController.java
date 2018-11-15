package com.sktelecom.t1.Applications;

import com.sktelecom.t1.Daos.UserMapper;
import com.sktelecom.t1.Models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/User")
public class UserController {

	@Autowired
	private UserMapper userMapper;
	
	//@Autowired
	//private UserService userService;

	@RequestMapping("/")
	public String user(Model model){
		model.addAttribute("user",new User());
		model.addAttribute("users", userMapper.selectAll());
		return "/User/index";
	}

	@RequestMapping("/add")
	public  String add(@ModelAttribute User user, Model model){
		user.setId(0);
		userMapper.insert(user);
		model.addAttribute("users", userMapper.selectAll());
		return "/User/index";
	}

	/*@ResponseBody
	@RequestMapping(value="/add", method = RequestMethod.POST)
	public String add(@RequestBody User user){
		userMapper.insert(user);
		return "success!";
	}

	@ResponseBody
	@RequestMapping(value="/query", method = RequestMethod.GET)
	public List<User> query(){
		return userMapper.selectAll();
	}*/
}
