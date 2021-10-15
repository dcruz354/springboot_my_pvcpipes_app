/**
 * 
 */
package com.springboot_my_pvcpipes_app.model.dao;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.springboot_my_pvcpipes_app.model.domain.Item;

/**
 * @author Dcruz
 *
 */
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
class ItemRepositoryTests {

	@Autowired private IItemRepository itemRepo;
	
    @Test
    public void testCreateItems() {
    	Item itemOne = new Item("Item One", 12.00, 12);
    	Item itemTwo = new Item("Item Two", 24.00, 24);
    	
    	itemRepo.saveAll(List.of(itemOne, itemTwo));
         
        List<Item> listItems = itemRepo.findAll();
        assertThat(listItems.size()).isEqualTo(2);
    }
}
