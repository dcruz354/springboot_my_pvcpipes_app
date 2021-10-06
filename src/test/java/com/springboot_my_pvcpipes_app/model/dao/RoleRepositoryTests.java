/**
 * 
 */
package com.springboot_my_pvcpipes_app.model.dao;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.springboot_my_pvcpipes_app.model.domain.Role;

/**
 * @author Dcruz
 *
 */
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
class RoleRepositoryTests {

    @Autowired private IRoleRepository repo;
    
    @Test
    public void testCreateRoles() {
        Role user = new Role("User");
        Role admin = new Role("Admin");
        Role customer = new Role("Customer");
         
        repo.saveAll(List.of(user, admin, customer));
         
        List<Role> listRoles = repo.findAll();
         
        assertThat(listRoles.size()).isEqualTo(3);
    }
    

}
