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
  Customer findCustomerByAccountId(int accountId);

  /**
   * @return all customers
   */
  List<Customer> findAll();

  /**
   * Delete a customer by email
   * 
   * @param email
   */
  void deleteByAccountId(int accountId);

}