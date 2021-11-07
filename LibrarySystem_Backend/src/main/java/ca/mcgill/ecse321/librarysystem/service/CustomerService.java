package ca.mcgill.ecse321.librarysystem.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.librarysystem.dao.CustomerRepository;
import ca.mcgill.ecse321.librarysystem.model.Customer;

@Service
public class CustomerService {
  
  @Autowired
  CustomerRepository customerRepository;
  	/**
	 * Create a Customer account
	 * @author Zi Chao
	 * @param firstName
	 * @param lastName
	 * @param password
   * @param email
   * @param isVerified
   * @param isLocal
   * @param address
   * @param balance
	 * @return Customer
	 */
  @Transactional
  public Customer createCustomer(String firstName, String lastName, String password, String email, boolean isVerified, boolean isLocal, String address, Integer balance) {
    if (firstName == null || firstName.replaceAll("\\s+","").length() == 0) {
      throw new IllegalArgumentException("Your first name cannot be empty.");
    }
    else if (firstName.matches(".*\\d.*") || containsSpecialCharacter(firstName)) {
      throw new IllegalArgumentException("Your first name cannot contain a number or a special character.");
    }
    else if (lastName == null || lastName.replaceAll("\\s+","").length() == 0) {
      throw new IllegalArgumentException("Your last name cannot be empty.");
    }
    else if (lastName.matches(".*\\d.*") || containsSpecialCharacter(firstName)) {
      throw new IllegalArgumentException("Your last name cannot contain a number or a special character.");
    }
    else if (password == null || password.length() < 8 || !password.matches(".*[A-Z].*")) {
      throw new IllegalArgumentException("Your password must have at least 8 characters and a capital letter.");
    }
    else if (email == null || email.replaceAll("\\s+","").length() == 0) {
      throw new IllegalArgumentException("Your email cannot be empty.");
    }
    else if (customerRepository.existsByEmail(email)) {
      throw new IllegalArgumentException("A customer with the provided email already exists.");
    }
    else if (address == null || address.replaceAll("\\s+","").length() == 0) {
      throw new IllegalArgumentException("Your address cannot be empty.");
    }
    else {
      Customer customer = new Customer();
      customer.setFirstName(firstName);
      customer.setLastName(lastName);
      customer.setPassword(password);
      customer.setEmail(email);
      customer.setAddress(address);
      customer.setIsVerified(isVerified);
      customer.setIsLocal(isLocal);
      customer.setAccountBalance(balance);
      customerRepository.save(customer);
      return customer;
    }
  }
  
  @Transactional
  public Customer getCustomerByFirstNameAndLastName(String firstName, String lastName) {
    Customer customer = customerRepository.findCustomerByFirstNameAndLastName(firstName, lastName);
    return customer;
  }

  @Transactional
  public Customer getCustomerByAccountId(int id) {
    Customer customer = customerRepository.findCustomerByAccountId(id);
    return customer;
  }

  @Transactional
  public Customer getCustomerByEmail(String email) {
    Customer customer = customerRepository.findCustomerByEmail(email);
    return customer;
  }

  @Transactional
  public List<Customer> getAllCustomers() {
    return toList(customerRepository.findAll());
  }

  @Transactional
  public Customer deleteCustomer(int id) {
    if (!customerRepository.existsByAccountId(id)) {
      throw new IllegalArgumentException("Customer account with provided id does not exist.");
    }
      Customer customer = customerRepository.findCustomerByAccountId(id);
      customerRepository.delete(customer);
      return customer;
  }

  // ------------------ Helper Methods ---------------------

    /**
    * helper method that checks if string contains special character(s) 
    * @param str
    * @ return boolean
    */
  private boolean containsSpecialCharacter(String str) {
    String specialChars = "/*!@#$%^&*()\"{}_[]|\\?/<>,.";
    for (int i=0; i<str.length(); i++) {
      if (specialChars.contains(Character.toString(str.charAt(i)))) {
        return true;
      }
    }
    return false;
  }

  	/**
	 *  helper method that converts iterable to list
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
