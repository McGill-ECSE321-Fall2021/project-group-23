package ca.mcgill.ecse321.librarysystem.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import ca.mcgill.ecse321.librarysystem.model.Customer;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TestCustomerPersistence {
  
	@Autowired
	private CustomerRepository customerRepository;

  @AfterEach
	public void clearDatabase() {
		// Fisrt, we clear all customer accounts to avoid exceptions due to inconsistencies
		customerRepository.deleteAll();
	}

	@Test
	public void testPersistAndLoadCustomer() {
		String customerFirstName = "Zi Chao";
    String customerLastName = "Zhang";
    String customerEmail = "zi.c.zhang@mail.mcgill.ca";
    String customerAddress = "554 Av. Circle";
    //Integer customerAccountId = Integer.valueOf(123456789);
    String customerPassword = "abc123";
    Integer customerAccountBalance = Integer.valueOf(0);
    boolean verificationStatus = true;
    boolean localStatus = true;

		// First example for object save/load
		Customer customer = new Customer();
		// First example for attribute save/load
		customer.setFirstName(customerFirstName);
    customer.setLastName(customerLastName);
    customer.setEmail(customerEmail);
    customer.setAddress(customerAddress);
    //customer.setAccountId(customerAccountId);
    customer.setPassword(customerPassword);
    customer.setAccountBalance(customerAccountBalance);
    customer.setIsVerified(verificationStatus);
    customer.setIsLocal(localStatus);

		customerRepository.save(customer);
/*
    // test finding customer by account ID
    customer = null;
		customer = customerRepository.findCustomerByAccountId();
		assertNotNull(customer);
    assertTrue(customerRepository.existsByAccountId(customerAccountId));
*/
    // test finding customer by first and last name
    customer = null;
    customer = customerRepository.findCustomerByFirstNameAndLastName(customerFirstName, customerLastName);
    assertNotNull(customer);
    assertTrue(customerRepository.existsByFirstNameAndLastName(customerFirstName, customerLastName));

    assertEquals(customerFirstName, customer.getFirstName());
    assertEquals(customerLastName, customer.getLastName());
    assertEquals(customerEmail, customer.getEmail());
    assertEquals(customerAddress, customer.getAddress());
		//assertEquals(customerAccountId, customer.getAccountId());
    assertEquals(customerPassword, customer.getPassword());
    assertEquals(customerAccountBalance, customer.getAccountBalance());
    assertEquals(verificationStatus, customer.getIsVerified());
    assertEquals(localStatus, customer.getIsLocal());  
	}




}
