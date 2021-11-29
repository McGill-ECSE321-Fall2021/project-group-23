package ca.mcgill.ecse321.librarysystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import ca.mcgill.ecse321.librarysystem.dto.CustomerDto;
import ca.mcgill.ecse321.librarysystem.dto.LibrarianDto;
import ca.mcgill.ecse321.librarysystem.model.Customer;
import ca.mcgill.ecse321.librarysystem.model.Librarian;
import ca.mcgill.ecse321.librarysystem.service.LibrarianService;

@CrossOrigin(origins = "*")
@RestController
public class LibrarianRestController {
	@Autowired LibrarianService librarianService;
	
	// Update the customer's verification status, and/or local status, and/or balance
	@PutMapping(value= { "/updateCustomer/{id}/{newIsVerified}/{newIsLocal}/{newBalance}", "/updateCustomer/{id}/{newIsVerified}/{newIsLocal}/{newBalance}/" })
	public CustomerDto updateCustomer(@PathVariable("id") int id, @PathVariable("newIsVerified") boolean newIsVerified, @PathVariable("newIsLocal") boolean newIsLocal, @PathVariable("newBalance") int newBalance) {
		Customer customer = librarianService.updateCustomer(id, newIsVerified, newIsLocal, newBalance);
		return convertToDto(customer);
	}

	@GetMapping(value= { "/loginLibrarian/{id}/{password}/", "/loginLibrarian/{id}/{password}" })
	public LibrarianDto loginLibrarian(@PathVariable("id") int id, @PathVariable("password") String password) {
		Librarian librarian = librarianService.loginLibrarian(id, password);
		return convertToDto(librarian);
	}

	// Create customer account with first name, last name, email, address, and balance
	@PostMapping(value= { "/createCustomer/{firstName}/{lastName}/{email}/{address}/{balance}", "/createCustomer/{firstName}/{lastName}/{email}/{address}/{balance}/" })
	public CustomerDto createCustomer(@PathVariable("firstName") String firstName, @PathVariable("lastName") String lastName, @PathVariable("email") String email, @PathVariable("address") String address, @PathVariable("balance") int balance) {
		Customer customer = librarianService.createCustomer(firstName, lastName, email, address, balance);
    return convertToDto(customer);
	}

	/**
	 * Helper Method to convert a Customer to a Customer Dto
	 * @author Zi Chao
	 * @param user
	 * @return CustomerDto
	 */
	private CustomerDto convertToDto(Customer customer) {
		if (customer == null) {
			throw new InvalidInputException("The provided customer does not exist.");
		}
		CustomerDto customerDto = new CustomerDto(customer.getFirstName(), customer.getLastName(), customer.getAccountId(), customer.getPassword(), customer.getEmail(), customer.getIsVerified(), customer.getIsLocal(), customer.getAccountBalance(), customer.getAddress());
	
		return customerDto;
	}
	private LibrarianDto convertToDto(Librarian librarian) {
		if (librarian == null) {
			throw new InvalidInputException("The provided librarian does not exist.");
		}
		LibrarianDto librarianDto = new LibrarianDto(librarian.getAccountId(), librarian.getFirstName(), librarian.getLastName(), librarian.getPassword());
	
		return librarianDto;
	}
}
