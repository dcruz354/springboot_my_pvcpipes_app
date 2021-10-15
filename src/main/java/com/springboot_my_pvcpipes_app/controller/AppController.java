/**
 * 
 */
package com.springboot_my_pvcpipes_app.controller;

import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.springboot_my_pvcpipes_app.model.dao.IItemRepository;
import com.springboot_my_pvcpipes_app.model.dao.IUserRepository;
import com.springboot_my_pvcpipes_app.model.domain.Item;
import com.springboot_my_pvcpipes_app.model.domain.Role;
import com.springboot_my_pvcpipes_app.model.domain.User;
import com.springboot_my_pvcpipes_app.model.services.item.ItemService;
import com.springboot_my_pvcpipes_app.model.services.user.UserService;

/**
 * @author Dcruz
 * Controller for handling mapping web requests onto methods 
 * in request-handling classes with flexible method signatures.
 */
@Controller
public class AppController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ItemService itemService;
	
    @Autowired
    private IUserRepository repoUser;
    
    @Autowired
    private IItemRepository repoItem;
	
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
		userService.registerDefaultUser(user);
	    return "register_success";
	}
	
	// mapping HTTP GET requests onto specific handler methods
	@GetMapping("/users")
	public String listUsers(Model model) {
	    List<User> listUsers = userService.listAll();
	    model.addAttribute("listUsers", listUsers);
	     
	    return "users";
	}
	

	
	// mapping HTTP GET requests onto specific handler methods
	@GetMapping("/edit/{id}")
	public String showUpdateForm(@PathVariable("id") Long id, Model model) {
	    User user = userService.get(id);
	    List<Role> listRoles = userService.getRoles();
		
		model.addAttribute("user", user);
		model.addAttribute("listRoles", listRoles);
		
		return "update_form";
	}
	
	// mapping HTTP POST requests onto specific handler methods
	@PostMapping("/update/save")
	public String updateUser(@Valid User user, BindingResult result, Model model) {
		userService.save(user);
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
		User user = userService.get(id);
		
		model.addAttribute("user", user);
	     
	    return "user";
	}
	
	@GetMapping("/403")
	public String error403() {
		return "403";
	}
	
	@GetMapping("/home")public String showHome(Model model) {
		return "home";
	}
	
	@GetMapping("/items/{id}")
	public String listItems(@PathVariable("id") long id, Model model) {
		User user = userService.get(id);
		Set<Item> listItems =  user.getItems();
		model.addAttribute("listItem", listItems);
		return "items";
	}
	
	@GetMapping("/allItems")
	public String listItems(Model model) {
		List<Item> listItems =  itemService.itemList();
		model.addAttribute("listItem", listItems);
		return "items";
	}
	
	// mapping HTTP GET requests onto specific handler methods
	@GetMapping("/newItem/{id}")
	public String showNewItemForm(@PathVariable("id") long id, Model model) {
		model.addAttribute("item", new Item(null, 0.0d, 0));
		return "new_item_form";
	}
	
	// mapping HTTP POST requests onto specific handler methods
	@PostMapping("/process_item/{id}")
	public String processItem(@PathVariable("id") long id, Model model, Item item) {
		Item newItem = new Item(item.getName(), item.getPrice(), item.getQuantity());
		itemService.saveItem(newItem);
		User user = repoUser.findById(id).get();
		Item itemUser = repoItem.findByName(item.getName());
		user.addItem(itemUser);
		repoUser.save(user);
	    return "save_success";
	}
	
}
