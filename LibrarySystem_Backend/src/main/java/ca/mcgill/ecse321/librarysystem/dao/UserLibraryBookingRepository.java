package ca.mcgill.ecse321.librarysystem.dao;

import javax.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ca.mcgill.ecse321.librarysystem.model.Customer;

@Repository
public class UserLibraryBookingRepository {
    
    @Autowired
    EntityManager entityManager;

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

