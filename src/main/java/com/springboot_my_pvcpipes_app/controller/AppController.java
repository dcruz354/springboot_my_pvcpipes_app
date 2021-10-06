/**
 * 
 */
package com.springboot_my_pvcpipes_app.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.springboot_my_pvcpipes_app.model.dao.IUserRepository;
import com.springboot_my_pvcpipes_app.model.domain.Role;
import com.springboot_my_pvcpipes_app.model.domain.User;
import com.springboot_my_pvcpipes_app.model.services.user.UserService;

/**
 * @author Dcruz
 * Controller for handling mapping web requests onto methods 
 * in request-handling classes with flexible method signatures.
 */
@Controller
public class AppController {
	
	@Autowired
	private UserService service;
	
	// mapping HTTP GET requests onto specific handler methods
	@GetMapping("")
	public String viewHomePage() {
		return "index";
	}
	
	// mapping HTTP GET requests onto specific handler methods
	@GetMapping("/register")
	public String showRegistrationForm(Model model) {
		model.addAttribute("user", new User());
		
		return "signup_form";
	}
	
	// mapping HTTP POST requests onto specific handler methods
	
	@PostMapping("/process_register")
	public String processRegister(User user) {
		service.registerDefaultUser(user);
	    return "register_success";
	}
	
	// mapping HTTP GET requests onto specific handler methods
	@GetMapping("/users")
	public String listUsers(Model model) {
	    List<User> listUsers = service.listAll();
	    model.addAttribute("listUsers", listUsers);
	     
	    return "users";
	}
	
	// mapping HTTP GET requests onto specific handler methods
	@GetMapping("/edit/{id}")
	public String showUpdateForm(@PathVariable("id") Long id, Model model) {
	    User user = service.get(id);
	    List<Role> listRoles = service.getRoles();
		
		model.addAttribute("user", user);
		model.addAttribute("listRoles", listRoles);
		
		return "update_form";
	}
	
	// mapping HTTP POST requests onto specific handler methods
	@PostMapping("/update/save")
	public String updateUser(@Valid User user, BindingResult result, Model model) {
		service.save(user);
		return "update_success";
	}
	
	// mapping HTTP GET requests onto specific handler methods
	@GetMapping("/login_success")
	public String showUserInfo(Model model) {	     
	    return "login_success";
	}
	
	// mapping HTTP GET requests onto specific handler methods
	@GetMapping("/user/{id}")
	public String listUserInformation(@PathVariable("id") long id, Model model) {
		User user = service.get(id);
		
		model.addAttribute("user", user);
	     
	    return "user";
	}
	
}
