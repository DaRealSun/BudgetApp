package com.budget.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.budget.demo.data.User;
import com.budget.demo.service.UserService;

@Controller
public class UserController {
	private final UserService userService;
	
	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}
	

	
	//List all user
	@GetMapping("/users")
	public String getAllUsers(Model model) {
	    List<User> users = userService.getAllUser();
	    model.addAttribute("users", users);
	    return "users";  // This is the name of the Thymeleaf view (i.e., users.html)
	}
	
	@GetMapping("/user/new")
	public String showNewUserForm(Model model) {
		User user = new User();
		model.addAttribute("user",user);
		return "new_user";// The name of thymeleaf template for creating a new user
	}
	
	@PostMapping("/user")
	public String createuser(@ModelAttribute("user") User user) {
		userService.createUser(user);
		return "redirect:/users";
	}

	@GetMapping("/user/edit/{id}")
	public String showUpdateForm(@PathVariable("id") long id, Model model) {
		User user = userService.getUserById(id);
		model.addAttribute("user",user);
		return "edit_user"; //the name of template editing user
	}
	
	@PostMapping("/user/update/{id}")
	public String updateUser( @ModelAttribute("user") User user, @PathVariable("id") long id) {
		userService.updateUser(user, id);
		return "redirect:/users";
		
	}
	
	@GetMapping("/user/delete/{id}")
	public String deleteUser(@PathVariable("id") long id) {
		userService.deleteUser(id);
		return "redirect:/users";
	}
}
