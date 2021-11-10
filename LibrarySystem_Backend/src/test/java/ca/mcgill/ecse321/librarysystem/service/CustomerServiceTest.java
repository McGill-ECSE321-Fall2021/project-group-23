package ca.mcgill.ecse321.librarysystem.service;

import java.util.ArrayList;
import java.util.List;

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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.Test;

import ca.mcgill.ecse321.librarysystem.dao.CustomerRepository;
import ca.mcgill.ecse321.librarysystem.model.Customer;

@ExtendWith(MockitoExtension.class)
public class CustomerServiceTest {
	@Mock
	private CustomerRepository customerRepository;
	
	@InjectMocks
	private CustomerService customerService;
	
  private static final int NONACCOUNTID = 321;

	private static final int ACCOUNTID = 222;
	private static final String FNAME = "Zi Chao";
	private static final String LNAME = "Zhang";
  private static final String PASSWORD = "Topsecret123";
	private static final String EMAIL = "zi.c.zhang@mail.com";
	private static final String ADDRESS = "1 King Street";
  private static final boolean ISVERIFIED = false;
  private static final boolean ISLOCAL = false;
	private static final int BALANCE = 0;

  private static final int ACCOUNTID2 = 333;
  private static final String FNAME2 = "Zi Chao*";
	private static final String LNAME2 = "_Zhang";
  private static final String PASSWORD2 = "2Short";
	private static final String EMAIL2 = "";
	private static final String ADDRESS2 = "";
  private static final boolean ISVERIFIED2 = false;
  private static final boolean ISLOCAL2 = true;
	private static final int BALANCE2 = -3;
	
	@BeforeEach
	public void setMockOutput() {
		lenient().when(customerRepository.findCustomerByAccountId(anyInt())).thenAnswer((InvocationOnMock invocation) -> {
			if (invocation.getArgument(0).equals(ACCOUNTID)) {
				Customer customer = new Customer();
				customer.setAccountId(ACCOUNTID);
				customer.setAccountBalance(BALANCE);
				customer.setFirstName(FNAME);
				customer.setLastName(LNAME);
        customer.setPassword(PASSWORD);
				customer.setEmail(EMAIL);
				customer.setAddress(ADDRESS);
        customer.setIsVerified(ISVERIFIED);
        customer.setIsLocal(ISLOCAL);
				return customer;
			}
      else if (invocation.getArgument(0).equals(ACCOUNTID2)) {
        Customer customer = new Customer();
				customer.setAccountId(ACCOUNTID2);
				customer.setAccountBalance(BALANCE2);
				customer.setFirstName(FNAME2);
				customer.setLastName(LNAME2);
        customer.setPassword(PASSWORD2);
				customer.setEmail(EMAIL2);
				customer.setAddress(ADDRESS2);
        customer.setIsVerified(ISVERIFIED2);
        customer.setIsLocal(ISLOCAL2);
				return customer;
      }
			else {
				return null;
			}
		});
		
    lenient().when(customerRepository.findCustomerByEmail(anyString())).thenAnswer((InvocationOnMock invocation) -> {
			if (invocation.getArgument(0).equals(EMAIL)) {
				Customer customer = new Customer();
				customer.setAccountId(ACCOUNTID);
				customer.setAccountBalance(BALANCE);
				customer.setFirstName(FNAME);
				customer.setLastName(LNAME);
        customer.setPassword(PASSWORD);
				customer.setEmail(EMAIL);
				customer.setAddress(ADDRESS);
        customer.setIsVerified(ISVERIFIED);
        customer.setIsLocal(ISLOCAL);
				return customer;
			}
      else if (invocation.getArgument(0).equals(EMAIL2)) {
        Customer customer = new Customer();
				customer.setAccountId(ACCOUNTID2);
				customer.setAccountBalance(BALANCE2);
				customer.setFirstName(FNAME2);
				customer.setLastName(LNAME2);
        customer.setPassword(PASSWORD2);
				customer.setEmail(EMAIL2);
				customer.setAddress(ADDRESS2);
        customer.setIsVerified(ISVERIFIED2);
        customer.setIsLocal(ISLOCAL2);
				return customer;
      }
			else {
				return null;
			}
    });

    lenient().when(customerRepository.findCustomerByFirstNameAndLastName(anyString(), anyString())).thenAnswer((InvocationOnMock invocation) -> {
			if (invocation.getArgument(0).equals(FNAME) && invocation.getArgument(1).equals(LNAME) ) {
				Customer customer = new Customer();
				customer.setAccountId(ACCOUNTID);
				customer.setAccountBalance(BALANCE);
				customer.setFirstName(FNAME);
				customer.setLastName(LNAME);
        customer.setPassword(PASSWORD);
				customer.setEmail(EMAIL);
				customer.setAddress(ADDRESS);
        customer.setIsVerified(ISVERIFIED);
        customer.setIsLocal(ISLOCAL);
				return customer;
			}
      else if (invocation.getArgument(0).equals(FNAME2) && invocation.getArgument(1).equals(LNAME2)) {
        Customer customer = new Customer();
				customer.setAccountId(ACCOUNTID2);
				customer.setAccountBalance(BALANCE2);
				customer.setFirstName(FNAME2);
				customer.setLastName(LNAME2);
        customer.setPassword(PASSWORD2);
				customer.setEmail(EMAIL2);
				customer.setAddress(ADDRESS2);
        customer.setIsVerified(ISVERIFIED2);
        customer.setIsLocal(ISLOCAL2);
				return customer;
      }
			else {
				return null;
			}
    });

    lenient().when(customerRepository.findAll()).thenAnswer((InvocationOnMock invocation) -> {
      Customer customer = new Customer();
      customer.setAccountId(ACCOUNTID);
      customer.setAccountBalance(BALANCE);
      customer.setFirstName(FNAME);
      customer.setLastName(LNAME);
      customer.setPassword(PASSWORD);
      customer.setEmail(EMAIL);
      customer.setAddress(ADDRESS);
      customer.setIsVerified(ISVERIFIED);
      customer.setIsLocal(ISLOCAL);

      Customer customer2 = new Customer();
      customer2.setAccountId(ACCOUNTID2);
      customer2.setAccountBalance(BALANCE2);
      customer2.setFirstName(FNAME2);
      customer2.setLastName(LNAME2);
      customer2.setPassword(PASSWORD2);
      customer2.setEmail(EMAIL2);
      customer2.setAddress(ADDRESS2);
      customer2.setIsVerified(ISVERIFIED2);
      customer2.setIsLocal(ISLOCAL2);

      List<Customer> customers = new ArrayList<Customer>();
      customers.add(customer);
      customers.add(customer2);
      return customers;
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
		Customer customer = null;
		try {
			customer = customerService.createCustomer(FNAME, LNAME, PASSWORD, EMAIL, ISVERIFIED, ISLOCAL, ADDRESS, BALANCE);
		} catch (IllegalArgumentException e) {
			fail();
		}
		assertNotNull(customer);
		assertEquals(customer.getFirstName(), FNAME);
		assertEquals(customer.getLastName(), LNAME);
    assertEquals(customer.getPassword(), PASSWORD);
		assertEquals(customer.getAccountBalance(), BALANCE);
		assertEquals(customer.getEmail(), EMAIL);
		assertEquals(customer.getAddress(), ADDRESS);
    assertEquals(customer.getIsVerified(), ISVERIFIED);
    assertEquals(customer.getIsLocal(), ISLOCAL);
	}
	
	// Test for creating a customer but first name is empty
	@Test
	public void testCreateCustomerEmptyFirstName() {
		Customer customer = null;
		String error = null;
		try {
			customer = customerService.createCustomer("", LNAME, PASSWORD, EMAIL, ISVERIFIED, ISLOCAL, ADDRESS, BALANCE);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertNull(customer);
		assertEquals("Your first name cannot be empty.", error);
	}

	// Test for creating a customer but first name contains a number
	@Test
	public void testCreateCustomerFirstNameContainsNumber() {
		Customer customer = null;
		String error = null;
		try {
			customer = customerService.createCustomer("Zi Chao 2", LNAME, PASSWORD, EMAIL, ISVERIFIED, ISLOCAL, ADDRESS, BALANCE);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertNull(customer);
		assertEquals("Your first name cannot contain a number or a special character.", error);
	}


	// Test for creating a customer but first name contains a special character
	@Test
	public void testCreateCustomerFirstNameContainsSpecialChar() {
		Customer customer = null;
		String error = null;
		try {
			customer = customerService.createCustomer("Zi Chao*", LNAME, PASSWORD, EMAIL, ISVERIFIED, ISLOCAL, ADDRESS, BALANCE);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertNull(customer);
		assertEquals("Your first name cannot contain a number or a special character.", error);
	}
	
	// Test for creating a customer but last name is empty
	@Test
	public void testCreateCustomerEmptyLastName() {
		Customer customer = null;
		String error = null;
		try {
			customer = customerService.createCustomer(FNAME, "", PASSWORD, EMAIL, ISVERIFIED, ISLOCAL, ADDRESS, BALANCE);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertNull(customer);
		assertEquals("Your last name cannot be empty.", error);
	}
	
	// Test for creating a customer but last name contains a number
	@Test
	public void testCreateCustomerLastNameContainsNumber() {
		Customer customer = null;
		String error = null;
		try {
			customer = customerService.createCustomer(FNAME, "Zhang1", PASSWORD, EMAIL, ISVERIFIED, ISLOCAL, ADDRESS, BALANCE);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertNull(customer);
		assertEquals("Your last name cannot contain a number or a special character.", error);
	}

	// Test for creating a customer but last name contains a special character
	@Test
	public void testCreateCustomerLastNameContainsSpecialChar() {
		Customer customer = null;
		String error = null;
		try {
			customer = customerService.createCustomer(FNAME, "_Zhang", PASSWORD, EMAIL, ISVERIFIED, ISLOCAL, ADDRESS, BALANCE);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertNull(customer);
		assertEquals("Your last name cannot contain a number or a special character.", error);
	}
	
  // Test for creating a customer but password is less than 8 characters
  @Test
  public void testCreateCustomerPasswordTooShort() {
    Customer customer = null;
    String error = null;
    try {
      customer = customerService.createCustomer(FNAME, LNAME, "2Short", EMAIL, ISVERIFIED, ISLOCAL, ADDRESS, BALANCE);
    } catch (IllegalArgumentException e) {
      error = e.getMessage();
    }
    assertNull(customer);
    assertEquals("Your password must have at least 8 characters and a capital letter.", error);
  }

  // Test for creating a customer but password does not contain a capital letter
  @Test
  public void testCreateCustomerPasswordContainsNoCapitalLetter() {
    Customer customer = null;
    String error = null;
    try {
      customer = customerService.createCustomer(FNAME, LNAME, "passwordnocap", EMAIL, ISVERIFIED, ISLOCAL, ADDRESS, BALANCE);
    } catch (IllegalArgumentException e) {
      error = e.getMessage();
    }
    assertNull(customer);
    assertEquals("Your password must have at least 8 characters and a capital letter.", error);
  }

	// Test for creating a customer but email is empty
	@Test
	public void testCreateCustomerEmptyEmail() {
		Customer customer = null;
		String error = null;
		try {
			customer = customerService.createCustomer(FNAME, LNAME, PASSWORD, "", ISVERIFIED, ISLOCAL, ADDRESS, BALANCE);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertNull(customer);
		assertEquals("Your email cannot be empty.", error);
	}
	
	// Test for creating a customer but email exists
	@Test
	public void testCreateCustomerEmailExists() {
		Customer customer = null;
		String error = null;
		lenient().when(customerRepository.existsByEmail(anyString())).thenReturn(true);
		try {
			customer = customerService.createCustomer(FNAME, LNAME, PASSWORD, EMAIL, ISVERIFIED, ISLOCAL, ADDRESS, BALANCE);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertNull(customer);
		assertEquals("A customer with the provided email already exists.", error);
	}
	
	// Test for creating a customer but address is empty
	@Test
	public void testCreateCustomerEmptyAddress() {
		Customer customer = null;
		String error = null;
		try {
			customer = customerService.createCustomer(FNAME, LNAME, PASSWORD, EMAIL, ISVERIFIED, ISLOCAL, "", BALANCE);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertNull(customer);
		assertEquals("Your address cannot be empty.", error);
	}

  // Test for updating a customer successfully
	@Test
	public void testUpdateCustomer() {
		Customer customer = null;
		try {
			customer = customerService.updateCustomer(ACCOUNTID, "Thismynewpass123", "1 Queen Street");
		} catch (IllegalArgumentException e) {
			fail();
		}
		assertNotNull(customer);
		assertEquals(customer.getPassword(), "Thismynewpass123");
		assertEquals(customer.getAddress(), "1 Queen Street");
	}
	
	// Test for updating a customer but id is not found
	@Test
	public void testUpdateCustomerIdNotFound() {
		Customer customer = null;
		String error = null;
		try {
			customer = customerService.updateCustomer(NONACCOUNTID, "Abdouallah", "Tahdi");
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertNull(customer);
		assertEquals("The customer with provided id cannot be found.", error);
	}
	
	// Test for updating a customer but new password is less 8 characters 
	@Test
	public void testUpdateCustomerPasswordTooShort() {
		Customer customer = null;
		String error = null;
		try {
			customer = customerService.updateCustomer(ACCOUNTID, "2Short", "1 King Street");
		} catch (IllegalArgumentException e) {
      error = e.getMessage();
    }
    assertNull(customer);
    assertEquals("Your password must have at least 8 characters and a capital letter.", error);
  }

  	// Test for updating a customer but new password contains no capital letter
	@Test
	public void testUpdateCustomerPasswordContainsNoCapitalLetter() {
		Customer customer = null;
		String error = null;
		try {
			customer = customerService.updateCustomer(ACCOUNTID, "newpassnocap", "1 King Street");
		} catch (IllegalArgumentException e) {
      error = e.getMessage();
    }
    assertNull(customer);
    assertEquals("Your password must have at least 8 characters and a capital letter.", error);
  }

  // Test for updating a customer but new address is empty
	@Test
	public void testUpdateCustomerEmptyAddress() {
		Customer customer = null;
		String error = null;
		try {
			customer = customerService.updateCustomer(ACCOUNTID, "Topsecret123", "");
		} catch (IllegalArgumentException e) {
      error = e.getMessage();
    }
    assertNull(customer);
    assertEquals("Your address cannot be empty.", error);
  }

  	// Test for deleting customer successfully
	@Test
	public void testDeleteCustomer() {
		Customer customer = null;
		lenient().when(customerRepository.existsByAccountId(anyInt())).thenReturn(true);
		try {
			customer = customerService.deleteCustomer(ACCOUNTID);
		} catch (IllegalArgumentException e) {
			fail();
		}
		assertNotNull(customer);
		assertEquals(customer.getAccountId(), ACCOUNTID);
	}
	
	// Test for deleting customer but account id not found
	@Test
	public void testDeleteCustomerIdNotFound() {
		Customer customer = null;
		String error = null;
		try {
			customer = customerService.deleteCustomer(NONACCOUNTID);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertNull(customer);
		assertEquals("Customer account with provided id does not exist.", error);
	}


}
