/**
 * 
 */
package com.springboot_my_pvcpipes_app.model.dao;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.annotation.Rollback;

import com.springboot_my_pvcpipes_app.model.domain.Role;
import com.springboot_my_pvcpipes_app.model.domain.User;

/**
 * @author Dcruz
 *
 */
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
class UserRepositoryTests {
    @Autowired
    private TestEntityManager entityManager;
    
    @Autowired
    private IUserRepository repoUser;
    
    @Autowired 
    private IRoleRepository repoRole;
    
    
    @Test
    public void testCreateUser() {
        User user = new User();
        user.setEmail("test@test.com");
        user.setPassword("test");
        user.setFirstName("testFN");
        user.setLastName("testLN");
        
	    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	    String encodedPassword = passwordEncoder.encode(user.getPassword());
	    user.setPassword(encodedPassword);

        User savedUser = repoUser.save(user);

        User existUser = entityManager.find(User.class, savedUser.getId());

        assertThat(user.getEmail()).isEqualTo(existUser.getEmail());
    }
    
    
    @Test
    public void testAddRoleToNewUser() {
        Role roleAdmin = repoRole.findByName("Admin");
         
        User user = new User();
        user.setEmail("test1@test1.com");
        user.setPassword("test1");
        user.setFirstName("test1FN");
        user.setLastName("test1LN");
        user.addRole(roleAdmin);  
        
	    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	    String encodedPassword = passwordEncoder.encode(user.getPassword());
	    user.setPassword(encodedPassword);
         
        User savedUser = repoUser.save(user);
         
        assertThat(savedUser.getRoles().size()).isEqualTo(1);
    }
    
    
    @Test
    public void testAddRoleToExistingUser() {
        User user = repoUser.findById(1L).get();
        Role roleUser = repoRole.findByName("User");
       // Role roleCustomer = new Role(3);
         
        user.addRole(roleUser);
        //user.addRole(roleCustomer);
         
        User savedUser = repoUser.save(user);
         
        assertThat(savedUser.getRoles().size()).isEqualTo(2);      
    }

}
