package ca.mcgill.ecse321.librarysystem.dao;

import java.util.Set;

import javax.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Repository;

import ca.mcgill.ecse321.librarysystem.model.Album;
import ca.mcgill.ecse321.librarysystem.model.Archive;
import ca.mcgill.ecse321.librarysystem.model.Book;
import ca.mcgill.ecse321.librarysystem.model.Customer;
import ca.mcgill.ecse321.librarysystem.model.Item;
import ca.mcgill.ecse321.librarysystem.model.Movie;
import ca.mcgill.ecse321.librarysystem.model.Newspapers;
import ca.mcgill.ecse321.librarysystem.model.Item.Status;
@Repository
public class ItemReservationRepository {

    @Autowired
    EntityManager entityManager;
    

    //Movie
    @Transactional
    public Item createMovie(Status status, int itemId, String title ) {
        Item newItem = new Movie();
        newItem.setStatus(status);
        //newItem.setCanBeBorrowed(true);
        newItem.setItemId(itemId);
        newItem.setTitle(title);
        entityManager.persist(newItem);
        return newItem;

    }

    @Transactional
    public Item getMovie(String title) {
        Item item = entityManager.find(Movie.class, title);
        return item;
    }

    //Book
    @Transactional
    public Item createBook(Status status, int itemId, String title ) {
        Item newItem = new Book();
        newItem.setStatus(status);
        //newItem.setCanBeBorrowed(true);
        newItem.setItemId(itemId);
        newItem.setTitle(title);
        entityManager.persist(newItem);
        return newItem;

    }

    @Transactional
    public Item getBook(String title) {
        Item item = entityManager.find(Book.class, title);
        return item;
    }

    //Album
    @Transactional
    public Item createAlbum(Status status, int itemId, String title ) {
        Item newItem = new Album();
        newItem.setStatus(status);
        //newItem.setCanBeBorrowed(true);
        newItem.setItemId(itemId);
        newItem.setTitle(title);
        entityManager.persist(newItem);
        return newItem;

    }

    @Transactional
    public Item getAlbum(String title) {
        Item item = entityManager.find(Album.class, title);
        return item;
    }

    //Archive
    @Transactional
    public Item createArchive(Status status, int itemId, String title ) {
        Item newItem = new Archive();
        newItem.setStatus(status);
        //newItem.setCanBeBorrowed(true);
        newItem.setItemId(itemId);
        newItem.setTitle(title);
        entityManager.persist(newItem);
        return newItem;

    }

    @Transactional
    public Item getArchive(String title) {
        Item item = entityManager.find(Archive.class, title);
        return item;
    }

    //Newspapers
    @Transactional
    public Item createNewspapers(Status status, int itemId, String title ) {
        Item newItem = new Newspapers();
        newItem.setStatus(status);
        //newItem.setCanBeBorrowed(true);
        newItem.setItemId(itemId);
        newItem.setTitle(title);
        entityManager.persist(newItem);
        return newItem;

    }

    @Transactional
    public Item getNewspapers(String title) {
        Item item = entityManager.find(Newspapers.class, title);
        return item;
    }

    @Transactional
    public Customer createCustomer(String firstName, String lastName, String address, int accountId, String email, boolean verified, boolean local, String password, int balance){
        Customer newCustomer = new Customer();
        newCustomer.setFirstName(firstName);
        newCustomer.setLastName(lastName);
        newCustomer.setAddress(address);
        newCustomer.setAccountId(accountId);
        newCustomer.setEmail(email);
        newCustomer.setIsVerified(verified);
        newCustomer.setIsLocal(local);
        newCustomer.setPassword(password);
        newCustomer.setAccountBalance(balance);
        entityManager.persist(newCustomer);
        return (Customer) newCustomer;
    }

    @Transactional
    public Customer getCustomer(int accountId) {
        Customer u = entityManager.find(Customer.class, accountId);
        return u;
    }
}

