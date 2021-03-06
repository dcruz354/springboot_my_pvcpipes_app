/**
 * 
 */
package com.springboot_my_pvcpipes_app.model.dao;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

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
    private IUserRepository repo;

    @Test
    public void testCreateUser() {
        User user = new User();
        user.setEmail("test@test.com");
        user.setPassword("test");
        user.setFirstName("testFN");
        user.setLastName("testLN");

        User savedUser = repo.save(user);

        User existUser = entityManager.find(User.class, savedUser.getId());

        assertThat(user.getEmail()).isEqualTo(existUser.getEmail());
    }

}
