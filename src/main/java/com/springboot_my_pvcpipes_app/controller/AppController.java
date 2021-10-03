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
import com.springboot_my_pvcpipes_app.model.domain.User;

/**
 * @author Dcruz
 *
 */
@Controller
public class AppController {
	
	@Autowired
	private IUserRepository userRepo;
	
	@GetMapping("")
	public String viewHomePage() {
		return "index";
	}
	
	@GetMapping("/register")
	public String showRegistrationForm(Model model) {
		model.addAttribute("user", new User());
		
		return "signup_form";
	}
	
	@PostMapping("/process_register")
	public String processRegister(User user) {
	    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	    String encodedPassword = passwordEncoder.encode(user.getPassword());
	    user.setPassword(encodedPassword);
	     
	    userRepo.save(user);
	     
	    return "register_success";
	}
	
	@GetMapping("/users")
	public String listUsers(Model model) {
	    List<User> listUsers = userRepo.findAll();
	    model.addAttribute("listUsers", listUsers);
	     
	    return "users";
	}
	
	@GetMapping("/edit/{id}")
	public String showUpdateForm(@PathVariable("id") long id, Model model) {
	    User user = userRepo.findById(id)
	    	      .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
		
		model.addAttribute("user", user);
		
		return "update_form";
	}
	
	@PostMapping("/update/{id}")
	public String updateUser(@PathVariable("id") long id, @Valid User user, BindingResult result, Model model) {
		if(result.hasErrors()) {
			user.setId(id);
			return "update_form";
		}
		
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	    String encodedPassword = passwordEncoder.encode(user.getPassword());
	    user.setPassword(encodedPassword);
		
		userRepo.save(user);
		return "update_success";
	}
	
	@GetMapping("/login_success")
	public String showUserInfo(Model model) {	     
	    return "login_success";
	}
	
}
