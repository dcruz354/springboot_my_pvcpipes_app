/**
 * 
 */
package com.springboot_my_pvcpipes_app.model.services.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.springboot_my_pvcpipes_app.model.dao.IUserRepository;
import com.springboot_my_pvcpipes_app.model.domain.User;

/**
 * @author Dcruz
 *
 */
public class CustomUserDetailsService implements UserDetailsService {
	
	@Autowired
	private IUserRepository userRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		User user = userRepo.findByEmail(username);
		if(user == null) {
			throw new UsernameNotFoundException("User not found");
		}
		return new CustomUserDetails(user);
	}
	

}
