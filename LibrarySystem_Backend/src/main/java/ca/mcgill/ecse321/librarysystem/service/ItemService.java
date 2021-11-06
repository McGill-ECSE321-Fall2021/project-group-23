package ca.mcgill.ecse321.librarysystem.service;

import java.util.List;
import java.util.ArrayList;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;

import ca.mcgill.ecse321.librarysystem.dao.ItemRepository;
import ca.mcgill.ecse321.librarysystem.model.Album;
import ca.mcgill.ecse321.librarysystem.model.Archive;
import ca.mcgill.ecse321.librarysystem.model.Book;
import ca.mcgill.ecse321.librarysystem.model.Item;
import ca.mcgill.ecse321.librarysystem.model.Movie;
import ca.mcgill.ecse321.librarysystem.model.Newspapers;

public class ItemService {

    @Autowired
    ItemRepository itemRepository;

    @Transactional
    public Item createItem(String title, String type){

        if (title == null || title == "") throw new IllegalArgumentException("Invalid title");
        if (type == null) throw new IllegalArgumentException("Invalid item type");


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
        } else if (type.toUpperCase().equals("NEWSPAPERS")) {
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
        throw new IllegalArgumentException("Invalid item type");
        
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
    public void deleteItem(int id) {
        itemRepository.deleteByItemId(id);
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
