package ca.mcgill.ecse321.librarysystem.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.librarysystem.model.Customer;

public interface CustomerRepository extends CrudRepository<Customer, Integer> {

  /**
   * Find a customer by account ID
   * 
   * @param accountId
   * @return customer (Customer)
   */
  Customer findCustomerByAccountId(Integer accountId);

    /**
   * Find a customer by full name
   * 
   * @param firstName
   * @param lastName
   * @return customer (Customer)
   */
  Customer findCustomerByFirstNameAndLastName(String firstName, String lastName);
  
    /**
   * Find a customer by email
   * 
   * @param email
   * @return customer (Customer)
   */
  Customer findCustomerByEmail(String email);
  /**
   * @return all customers
   */
  List<Customer> findAll();

  /**
   * Delete a customer by email
   * 
   * @param email
   */
  void deleteByAccountId(Integer accountId);

  /**
   * Check if customer account exists by account ID
   * 
   * @param accountId
   * @return boolean
   */
  boolean existsByAccountId(Integer accountId);

  /**
   * Check if customer account exists by email
   * 
   * @param email
   * @return boolean
   */
  boolean existsByEmail(String email);

}