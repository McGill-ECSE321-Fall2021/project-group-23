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
   * @param firstName,lastName
   * @return customer (Customer)
   */
  Customer findCustomerByName(String firstName, String lastName);

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
   * Check if customer account exists by full name
   * 
   * @param firstName,lastName
   * @return boolean
   */
  boolean existsByName(String firstName, String lastName);

}