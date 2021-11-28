package ca.mcgill.ecse321.librarysystem.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import ca.mcgill.ecse321.librarysystem.model.Customer;
import ca.mcgill.ecse321.librarysystem.dto.CustomerDto;
import ca.mcgill.ecse321.librarysystem.service.CustomerService;

@CrossOrigin(origins = "*")
@RestController
public class CustomerRestController {

  @Autowired
  private CustomerService customerService;

  	/**
	 * Return a list of all Customer DTOs
	 * @author Zi Chao
	 * @return List of Customer Dto 
	 */
  @GetMapping(value = { "/getAllCustomers", "/getAllCustomers/" })
  public List<CustomerDto> getAllCustomers() {
    List<CustomerDto> customerDtos = new ArrayList<>();
    for (Customer customer : customerService.getAllCustomers()) {
      customerDtos.add(convertToDto(customer));
    }
    return customerDtos;
  }

  @GetMapping(value = { "/getCustomerById/{id}", "/getCustomerById/{id}/" })
  public CustomerDto getCustomerByAccountId(@PathVariable("id") int id) throws IllegalArgumentException {
    return convertToDto(customerService.getCustomerByAccountId(id));
  }

  @GetMapping(value = { "/loginCustomer/{id}/{password}/", "/loginCustomer/{id}/{password}" })
  public CustomerDto loginCustomer(@PathVariable("id") int id, @PathVariable("password") String password) throws IllegalArgumentException {
    return convertToDto(customerService.loginCustomer(id, password));
  }

  @GetMapping(value = { "/getCustomerByEmail/{email}", "/getCustomerByEmail/{email}/" })
  public CustomerDto getCustomerByEmail(@PathVariable("email") String email) throws IllegalArgumentException {
    return convertToDto(customerService.getCustomerByEmail(email));
  }

  @GetMapping(value = { "/getCustomerByFirstNameAndLastName/{firstName}/{lastName}", "/getCustomerByFirstNameAndLastName/{firstName}/{lastName}/" })
  public CustomerDto getCustomerByFirstNameAndLastName(@PathVariable("firstName") String firstName, @PathVariable("lastName") String lastName) {
    return convertToDto(customerService.getCustomerByFirstNameAndLastName(firstName, lastName));
  }

  @PostMapping(value = { "/createCustomer/{firstName}/{lastName}/{password}/{email}/{isVerified}/{isLocal}/{address}/{balance}", "/createCustomer/{firstName}/{lastName}/{password}/{email}/{isVerified}/{isLocal}/{address}/{balance}/" })
  public CustomerDto createCustomer(@PathVariable("firstName") String firstName, @PathVariable("lastName") String lastName, @PathVariable("password") String password, @PathVariable("email") String email, @PathVariable("isVerified") boolean isVerified, @PathVariable("isLocal") boolean isLocal, @PathVariable("address") String address, @PathVariable("balance") int balance) {
    Customer customer = customerService.createCustomer(firstName, lastName, password, email, isVerified, isLocal, address, balance);
    return convertToDto(customer);
  }

  @PutMapping(value = { "/updateCustomer/{id}/{newPassword}/{newAddress}", "/updateCustomer/{id}/{newPassword}/{newAddress}/" })
  public CustomerDto updateCustomer(@PathVariable("id") int id, @PathVariable("newPassword") String newPassword, @PathVariable("newAddress") String newAddress) {
    Customer customer = customerService.updateCustomer(id, newPassword, newAddress);
    return convertToDto(customer);
  }

  @DeleteMapping(value = { "/deleteCustomer/{id}", "deleteCustomer/{id}/" }) 
  public CustomerDto deleteCustomer(@PathVariable("id") int id) {
    Customer customer = customerService.deleteCustomer(id);
    return convertToDto(customer);
  }

  	//-------------------------- Helper Methods -----------------------------

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
		CustomerDto customerDto = new CustomerDto(customer.getFirstName(), customer.getLastName(), customer.getAccountId(), customer.getPassword(), customer.getEmail(), customer.getIsVerified(), customer.getIsLocal(), customer.getAccountBalance(), customer.getAddress());

		return customerDto;
	}


}
