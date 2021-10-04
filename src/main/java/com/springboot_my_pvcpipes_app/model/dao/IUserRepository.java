/**
 * 
 */
package com.springboot_my_pvcpipes_app.model.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.springboot_my_pvcpipes_app.model.domain.User;

/**
 * @author Dcruz
 * Interface can selectively expose CRUD methods by simply declaring 
 * methods of the same signature as those declared in CRUD Repository.
 */
public interface IUserRepository extends JpaRepository<User, Long> {
    @Query("SELECT u FROM User u WHERE u.email = ?1")
    public User findByEmail(String email);
}
