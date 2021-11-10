package ca.mcgill.ecse321.librarysystem.service;


import static org.mockito.Mockito.lenient;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.anyInt;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.Test;

import ca.mcgill.ecse321.librarysystem.dao.CustomerRepository;
import ca.mcgill.ecse321.librarysystem.model.Customer;

@ExtendWith(MockitoExtension.class)
public class LibrarianServiceTest {
	@Mock
	private CustomerRepository customerRepository;
	
	@InjectMocks
	private LibrarianService librarianService;
	
	private static final int ACCOUNTID = 247;
	private static final int NONACCOUNTID = 361;
	
	private static final String FNAME = "Abiola";
	private static final String LNAME = "Olaniyan";
	private static final String EMAIL = "abiola.olaniyan@mail.com";
	private static final String ADDRESS = "123 Capital St.";
	private static final int BALANCE = 0;
	
	@BeforeEach
	public void setMockOutput() {
		lenient().when(customerRepository.findCustomerByAccountId(anyInt())).thenAnswer((InvocationOnMock invocation) -> {
			if (invocation.getArgument(0).equals(ACCOUNTID)) {
				Customer c = new Customer();
				c.setAccountId(ACCOUNTID);
				c.setAccountBalance(BALANCE);
				c.setFirstName(FNAME);
				c.setLastName(LNAME);
				c.setEmail(EMAIL);
				c.setAddress(ADDRESS);
				return c;
			}
			else {
				return null;
			}
		});
		
		// Whenever anything is saved, just return the parameter object
		Answer<?> returnParameterAsAnswer = (InvocationOnMock invocation) -> {
			return invocation.getArgument(0);
		};
		lenient().when(customerRepository.save(any(Customer.class))).thenAnswer(returnParameterAsAnswer);
	}
	
	// Test for creating a customer successfully
	@Test
	public void testCreateCustomer() {
		Customer c = null;
		try {
			c = librarianService.createCustomer(FNAME, LNAME, EMAIL, ADDRESS, BALANCE);
		} catch (IllegalArgumentException e) {
			fail();
		}
		assertNotNull(c);
		assertEquals(c.getFirstName(), FNAME);
		assertEquals(c.getLastName(), LNAME);
		assertEquals(c.getAccountBalance(), BALANCE);
		assertEquals(c.getEmail(), EMAIL);
		assertEquals(c.getAddress(), ADDRESS);
	}
	
	// Test for creating a customer but first name is empty
	@Test
	public void testCreateCustomerEmptyFirstName() {
		Customer c = null;
		String error = null;
		try {
			c = librarianService.createCustomer(null, LNAME, EMAIL, ADDRESS, BALANCE);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertNull(c);
		assertEquals("Your first name cannot be empty.", error);
	}
	
	// Test for creating a customer but first name contains a special character
	@Test
	public void testCreateCustomerFirstNameSpecialChar() {
		Customer c = null;
		String error = null;
		try {
			c = librarianService.createCustomer("Abiola 2.0", LNAME, EMAIL, ADDRESS, BALANCE);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertNull(c);
		assertEquals("Your first name cannot contain a number or a special character.", error);
	}
	
	// Test for creating a customer but last name is empty
	@Test
	public void testCreateCustomerEmptyLastName() {
		Customer c = null;
		String error = null;
		try {
			c = librarianService.createCustomer(FNAME, "", EMAIL, ADDRESS, BALANCE);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertNull(c);
		assertEquals("Your last name cannot be empty.", error);
	}
	
	// Test for creating a customer but last name contains a special character
	@Test
	public void testCreateCustomerLastNameSpecialChar() {
		Customer c = null;
		String error = null;
		try {
			c = librarianService.createCustomer(FNAME, "Olaniyan 2.0", EMAIL, ADDRESS, BALANCE);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertNull(c);
		assertEquals("Your last name cannot contain a number or a special character.", error);
	}
	
	// Test for creating a customer but email is empty
	@Test
	public void testCreateCustomerEmptyEmail() {
		Customer c = null;
		String error = null;
		try {
			c = librarianService.createCustomer(FNAME, LNAME, null, ADDRESS, BALANCE);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertNull(c);
		assertEquals("Your email cannot be empty.", error);
	}
	
	// Test for creating a customer but email exists
	@Test
	public void testCreateCustomerEmailExists() {
		Customer c = null;
		String error = null;
		lenient().when(customerRepository.existsByEmail(anyString())).thenReturn(true);
		try {
			c = librarianService.createCustomer(FNAME, LNAME, EMAIL, ADDRESS, BALANCE);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertNull(c);
		assertEquals("A customer with the provided email already exists.", error);
	}
	
	// Test for creating a customer but address is empty
	@Test
	public void testCreateCustomerEmptyAddress() {
		Customer c = null;
		String error = null;
		try {
			c = librarianService.createCustomer(FNAME, LNAME, EMAIL, "", BALANCE);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertNull(c);
		assertEquals("Your address cannot be empty.", error);
	}
	
	// Test for updating a customer successfully
	@Test
	public void testUpdateCustomer() {
		Customer c = null;
		try {
			c = librarianService.updateCustomer(ACCOUNTID, true, false, 100);
		} catch (IllegalArgumentException e) {
			fail();
		}
		assertNotNull(c);
		assertEquals(c.getAccountBalance(), 100);
		assertEquals(c.getIsVerified(), true);
		assertEquals(c.getIsLocal(), false);
	}
	
	// Test for updating a customer but id is not found
	@Test
	public void testUpdateCustomerIdNotFound() {
		Customer c = null;
		String error = null;
		try {
			c = librarianService.updateCustomer(NONACCOUNTID, false, false, BALANCE);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertNull(c);
		assertEquals("The customer with provided id cannot be found.", error);
	}
	
	// Test for updating a customer but balance provided is negative
	@Test
	public void testUpdateCustomerNegativeBalance() {
		Customer c = null;
		String error = null;
		try {
			c = librarianService.updateCustomer(ACCOUNTID, false, false, -9);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertNull(c);
		assertEquals("The provided account balance cannot be negative.", error);
	}
	
}
