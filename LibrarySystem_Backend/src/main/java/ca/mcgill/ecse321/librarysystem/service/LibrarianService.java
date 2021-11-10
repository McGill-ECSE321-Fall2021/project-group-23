package ca.mcgill.ecse321.librarysystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.librarysystem.dao.CustomerRepository;
import ca.mcgill.ecse321.librarysystem.dao.LibrarianRepository;
import ca.mcgill.ecse321.librarysystem.dao.WeeklyScheduleRepository;
import ca.mcgill.ecse321.librarysystem.model.Customer;

@Service
public class LibrarianService {
  @Autowired
  LibrarianRepository librarianRepository;
  @Autowired
  WeeklyScheduleRepository weeklyScheduleRepository;
  @Autowired
  CustomerRepository customerRepository;

  @Transactional
  public Customer createCustomer(String firstName, String lastName, String email, String address, int balance) {
    if (firstName == null || firstName.replaceAll("\\s+", "").length() == 0) {
      throw new IllegalArgumentException("Your first name cannot be empty.");
    } else if (firstName.matches(".*\\d.*") || containsSpecialCharacter(firstName)) {
      throw new IllegalArgumentException("Your first name cannot contain a number or a special character.");
    } else if (lastName == null || lastName.replaceAll("\\s+", "").length() == 0) {
      throw new IllegalArgumentException("Your last name cannot be empty.");
    } else if (lastName.matches(".*\\d.*") || containsSpecialCharacter(firstName)) {
      throw new IllegalArgumentException("Your last name cannot contain a number or a special character.");
    } else if (email == null || email.replaceAll("\\s+", "").length() == 0) {
      throw new IllegalArgumentException("Your email cannot be empty.");
    } else if (customerRepository.existsByEmail(email)) {
      throw new IllegalArgumentException("A customer with the provided email already exists.");
    } else if (address == null || address.replaceAll("\\s+", "").length() == 0) {
      throw new IllegalArgumentException("Your address cannot be empty.");
    } else {
      Customer customer = new Customer();
      customer.setFirstName(firstName);
      customer.setLastName(lastName);
      customer.setEmail(email);
      customer.setAddress(address);
      customer.setAccountBalance(balance);
      customerRepository.save(customer);
      return customer;
    }
  }

  @Transactional
  public Customer updateCustomer(int id, boolean newIsVerified, boolean newIsLocal, int newBalance) {
    if (String.valueOf(id).length() == 0) {
      throw new IllegalArgumentException("Customer id cannot be empty.");
    } else {
      Customer customer = customerRepository.findCustomerByAccountId(id);
      if (customer == null) {
        throw new IllegalArgumentException("The customer with provided id cannot be found.");
      }
      if (String.valueOf(newBalance).length() == 0 || newBalance < 0) {
        throw new IllegalArgumentException("The provided account balance cannot be empty or negative.");
      } else {
        customer.setIsVerified(newIsVerified);
        customer.setIsLocal(newIsLocal);
        customer.setAccountBalance(newBalance);
        customerRepository.save(customer);
        return customer;
      }
    }
  }

  // ------------------ Helper Methods ---------------------
  /**
   * helper method that checks if string contains special character(s)
   * @param str 
   * @return boolean
   */
  private boolean containsSpecialCharacter(String str) {
    String specialChars = "/*!@#$%^&*()\"{}_[]|\\?/<>,.";
    for (int i = 0; i < str.length(); i++) {
      if (specialChars.contains(Character.toString(str.charAt(i)))) {
        return true;
      }
    }
    return false;
  }
}
