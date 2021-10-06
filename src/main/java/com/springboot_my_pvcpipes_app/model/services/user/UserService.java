/**
 * 
 */
package com.springboot_my_pvcpipes_app.model.services.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.springboot_my_pvcpipes_app.model.dao.IRoleRepository;
import com.springboot_my_pvcpipes_app.model.dao.IUserRepository;
import com.springboot_my_pvcpipes_app.model.domain.Role;
import com.springboot_my_pvcpipes_app.model.domain.User;

/**
 * @author Dcruz
 *
 */
@Service
public class UserService {
	
    @Autowired
    private IUserRepository userRepo;
     
    @Autowired IRoleRepository roleRepo;
     
   // @Autowired PasswordEncoder passwordEncoder;
     
    public void registerDefaultUser(User user) {
        
		// Implementation of PasswordEncoder that uses the BCrypt strong hashing function
	    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	    String encodedPassword = passwordEncoder.encode(user.getPassword());
	    user.setPassword(encodedPassword);
	    
	    Role roleUser = roleRepo.findByName("User");
        user.addRole(roleUser);
 
        userRepo.save(user);
    }
    
    public List<User> listAll() {
    	return userRepo.findAll();
    }
    
    public List<Role> getRoles() {
    	return roleRepo.findAll();
    }
    
    public User get(Long id) {
    	return userRepo.findById(id).get();
    }
    
    public void save(User user) {
		// Implementation of PasswordEncoder that uses the BCrypt strong hashing function
	    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	    String encodedPassword = passwordEncoder.encode(user.getPassword());
	    user.setPassword(encodedPassword);
         userRepo.save(user);
    }
}
