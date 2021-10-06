/**
 * 
 */
package com.springboot_my_pvcpipes_app.model.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.springboot_my_pvcpipes_app.model.domain.Role;

/**
 * @author Dcruz
 *
 */
public interface IRoleRepository extends JpaRepository<Role, Long> {
	@Query("SELECT r from Role r WHERE r.name = ?1")
	public Role findByName(String name);
}
