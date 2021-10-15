/**
 * 
 */
package com.springboot_my_pvcpipes_app.model.services.item;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot_my_pvcpipes_app.model.dao.IItemRepository;
import com.springboot_my_pvcpipes_app.model.domain.Item;

/**
 * @author Dcruz
 *
 */
@Service
public class ItemService {
	@Autowired
	private IItemRepository itemRepo;
	
	public List<Item> itemList() {
		return itemRepo.findAll();
	}
	
	public void saveItem (Item item) {
		itemRepo.save(item);
	}
	
	public Item getItem(Long id) {
		return itemRepo.getById(id);
	}
	
	public void deleteItem(Long id) {
		itemRepo.deleteById(id);
	}
}
