package ca.mcgill.ecse321.librarysystem.service;

import java.util.List;
import java.util.ArrayList;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ca.mcgill.ecse321.librarysystem.dao.ItemRepository;
import ca.mcgill.ecse321.librarysystem.model.Album;
import ca.mcgill.ecse321.librarysystem.model.Archive;
import ca.mcgill.ecse321.librarysystem.model.Book;
import ca.mcgill.ecse321.librarysystem.model.Item;
import ca.mcgill.ecse321.librarysystem.model.Movie;
import ca.mcgill.ecse321.librarysystem.model.Newspapers;

@Service
public class ItemService {

    @Autowired
    ItemRepository itemRepository;

    @Transactional
    public Item createItem(String title, String type){

        if (title == null || title.trim() == "") throw new InvalidInputException("Invalid title");
        if (type == null) throw new InvalidInputException("Invalid item type");


        if (type.toUpperCase().equals("BOOK")) {
            Book book = new Book();
            book.setTitle(title);
            book.setStatus(Item.Status.AVAILABLE);
            int id = itemRepository.save(book).getItemId();
           // book.setItemId(id);
            return book;
        } else if (type.toUpperCase().equals("MOVIE")) {
            Movie movie = new Movie();
            movie.setTitle(title);
            movie.setStatus(Item.Status.AVAILABLE);
            int id = itemRepository.save(movie).getItemId();
           // movie.setItemId(id);
            return movie;
        } else if (type.toUpperCase().equals("NEWSPAPERS") || type.toUpperCase().equals("NEWSPAPER")) {
            Newspapers newspaper = new Newspapers();
            newspaper.setTitle(title);
            newspaper.setStatus(Item.Status.AVAILABLE);
            int id = itemRepository.save(newspaper).getItemId();
           // newspaper.setItemId(id);
            return newspaper;
        } else if (type.toUpperCase().equals("ARCHIVE")) {
            Archive archive = new Archive();
            archive.setTitle(title);
            archive.setStatus(Item.Status.AVAILABLE);
            int id = itemRepository.save(archive).getItemId();
           // archive.setItemId(id);
            return archive;
        } else if (type.toUpperCase().equals("ALBUM")) {
            Album album = new Album();
            album.setTitle(title);
            album.setStatus(Item.Status.AVAILABLE);
            int id = itemRepository.save(album).getItemId();
           // album.setItemId(id);
            return album;
        } 
        throw new InvalidInputException("Invalid item type");
        
    }
    /**
     * returns an item given the id
     * @param id
     * @return
     */
    @Transactional
    public Item getItem (int id) {
        Item item = itemRepository.findItemByItemId(id);
        return item;
    }

    /**
     * Returns a list of items based on title (List to deal with duplicates)
     * @param title
     * @return
     */
    @Transactional
    public List<Item> getItem(String title) {
        return itemRepository.findItemByTitle(title);
    }

    /**
     * returns a list of all items
     * @return
     */
    @Transactional
    public List<Item> getAllItems() {
        return toList(itemRepository.findAll());
    }

    /**
     * Deletes the item given an Id
     * @param id
     */
    @Transactional
    public Item deleteItem(int id) {
        Item item = itemRepository.findItemByItemId(id);
        if (item == null) {
            throw new InvalidInputException("Can't find item with ID = " + id);
        }
        itemRepository.deleteByItemId(id);
        return item;
    }
    /**
     * Modifies an item's title, given a valid ID
     * @param id
     * @param newTitle
     * @return
     */
    @Transactional
    public Item modifyItem(int id, String newTitle) {
        Item item = itemRepository.findItemByItemId(id);
        if (newTitle == null || newTitle.trim() == "") {
            throw new InvalidInputException("Invalid new Title");
        }
        if (item == null) {
            throw new InvalidInputException("Can't find item with ID = " + id);
        }
        item.setTitle(newTitle);
        itemRepository.save(item);
        return item;
    }

    /**
     * Converts iterable to List, taken from ECSE 321 tutorial code
     * @param <T>
     * @param iterable
     * @return
     */
    private <T> List<T> toList(Iterable<T> iterable){
		List<T> resultList = new ArrayList<T>();
		for (T t : iterable) {
			resultList.add(t);
		}
		return resultList;
	}
    
}
