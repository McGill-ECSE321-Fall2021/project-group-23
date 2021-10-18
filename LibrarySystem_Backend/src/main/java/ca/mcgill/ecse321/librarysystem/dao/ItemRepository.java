package ca.mcgill.ecse321.librarysystem.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ca.mcgill.ecse321.librarysystem.model.Item;



@Repository
public interface ItemRepository extends CrudRepository<Item, Integer>{
/**
     * Finds an item using the Item Id (unique)
     * @param id
     * @return
     */
    Item findItemByItemId(int id);


    /**
     * Searches for the item by name 
     * Returns a list<Item> since name is not unique
     * @param Name
     * @return
     */
    List<Item> findItemByTitle(String title);

    /**
     * Deletes an item using the Item Id
     * @param id
     * @return
     */
    void deleteByItemId(int id);
}
