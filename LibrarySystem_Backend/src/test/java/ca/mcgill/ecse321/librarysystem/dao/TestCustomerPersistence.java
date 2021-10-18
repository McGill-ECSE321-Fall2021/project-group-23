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
		String customer1FirstName = "Zi Chao";
    String customer1LastName = "Zhang";
    String customer1Email = "zi.c.zhang@mail.mcgill.ca";
    String customer1Address = "554 Av. Circle";
    String customer1Password = "abc123";
    Integer customer1AccountBalance = Integer.valueOf(0);
    boolean customer1VerificationStatus = true;
    boolean customer1LocalStatus = true;

		// First example for object save/load
		Customer customer1 = new Customer();
		// First example for attribute save/load
		customer1.setFirstName(customer1FirstName);
    customer1.setLastName(customer1LastName);
    customer1.setEmail(customer1Email);
    customer1.setAddress(customer1Address);
    customer1.setPassword(customer1Password);
    customer1.setAccountBalance(customer1AccountBalance);
    customer1.setIsVerified(customer1VerificationStatus);
    customer1.setIsLocal(customer1LocalStatus);

		Customer savedCustomer1 = customerRepository.save(customer1);
    
    // test finding customer by account ID
    customer1 = null;
		customer1 = customerRepository.findCustomerByAccountId(savedCustomer1.getAccountId());
		assertNotNull(customer1);
    assertTrue(customerRepository.existsByAccountId(savedCustomer1.getAccountId()));

    assertEquals(customer1FirstName, customer1.getFirstName());
    assertEquals(customer1LastName, customer1.getLastName());
    assertEquals(customer1Email, customer1.getEmail());
    assertEquals(customer1Address, customer1.getAddress());
		assertEquals(savedCustomer1.getAccountId(), customer1.getAccountId());
    assertEquals(customer1Password, customer1.getPassword());
    assertEquals(customer1AccountBalance, customer1.getAccountBalance());
    assertEquals(customer1VerificationStatus, customer1.getIsVerified());
    assertEquals(customer1LocalStatus, customer1.getIsLocal());  
	}

  @Test
	public void testPersistAndLoadCustomerByFirstNameAndLastName() {
		String customer2FirstName = "Kanye";
    String customer2LastName = "West";
    String customer2Email = "kanye.west@rapper.com";
    String customer2Address = "1 King Street";
    String customer2Password = "conda";
    Integer customer2AccountBalance = Integer.valueOf(0);
    boolean customer2VerificationStatus = true;
    boolean customer2LocalStatus = false;

		// First example for object save/load
		Customer customer2 = new Customer();
		// First example for attribute save/load
		customer2.setFirstName(customer2FirstName);
    customer2.setLastName(customer2LastName);
    customer2.setEmail(customer2Email);
    customer2.setAddress(customer2Address);
    customer2.setPassword(customer2Password);
    customer2.setAccountBalance(customer2AccountBalance);
    customer2.setIsVerified(customer2VerificationStatus);
    customer2.setIsLocal(customer2LocalStatus);

		Customer savedCustomer2 = customerRepository.save(customer2);

    // test finding customer by first and last name
    customer2 = null;
    customer2 = customerRepository.findCustomerByFirstNameAndLastName(customer2FirstName, customer2LastName);
    assertNotNull(customer2);
    assertTrue(customerRepository.existsByFirstNameAndLastName(customer2FirstName, customer2LastName));

    assertEquals(customer2FirstName, customer2.getFirstName());
    assertEquals(customer2LastName, customer2.getLastName());
    assertEquals(customer2Email, customer2.getEmail());
    assertEquals(customer2Address, customer2.getAddress());
		//assertEquals(customerAccountId, customer.getAccountId());
    assertEquals(customer2Password, customer2.getPassword());
    assertEquals(customer2AccountBalance, customer2.getAccountBalance());
    assertEquals(customer2VerificationStatus, customer2.getIsVerified());
    assertEquals(customer2LocalStatus, customer2.getIsLocal());  
	}

}
