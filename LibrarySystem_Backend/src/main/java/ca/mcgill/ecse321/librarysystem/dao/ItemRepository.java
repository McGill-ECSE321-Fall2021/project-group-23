package ca.mcgill.ecse321.librarysystem.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.librarysystem.model.Item;

public interface ItemRepository extends CrudRepository<Item, Integer>{

    Item findItemByItemId(int id);
    
}
