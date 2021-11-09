package ca.mcgill.ecse321.librarysystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import ca.mcgill.ecse321.librarysystem.dto.CustomerDto;
import ca.mcgill.ecse321.librarysystem.model.Customer;
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

	/**
	 * Helper Method to convert a Customer to a Customer Dto
	 * @author Zi Chao
	 * @param user
	 * @return CustomerDto
	 */
	private CustomerDto convertToDto(Customer customer) {
		if (customer == null) {
			throw new IllegalArgumentException("The provided customer does not exist.");
		}
		CustomerDto customerDto = new CustomerDto(customer.getFirstName(), customer.getLastName(), customer.getAccountId(), customer.getPassword(), customer.getEmail(), customer.getIsVerified(), customer.getIsLocal(), customer.getAccountBalance());
	
		return customerDto;
	}
}
