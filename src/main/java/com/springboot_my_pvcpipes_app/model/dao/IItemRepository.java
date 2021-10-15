/**
 * 
 */
package com.springboot_my_pvcpipes_app.model.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.springboot_my_pvcpipes_app.model.domain.Item;
import com.springboot_my_pvcpipes_app.model.domain.Role;

/**
 * @author Dcruz
 *
 */
public interface IItemRepository extends JpaRepository<Item, Long>{
	@Query("SELECT i from Item i WHERE i.name = ?1")
	public Item findByName(String name);
}
