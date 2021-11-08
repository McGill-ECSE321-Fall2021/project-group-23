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
    public Customer updateCustomer(int id, boolean newIsVerified, boolean newIsLocal, int newBalance) {
      if (String.valueOf(id).length() == 0) {
        throw new IllegalArgumentException("Customer id cannot be empty.");
      } 
      else {
        Customer customer = customerRepository.findCustomerByAccountId(id);
        if (customer == null) {
          throw new IllegalArgumentException("The customer with provided id cannot be found.");
		}
		if (String.valueOf(newBalance).length() == 0 || newBalance < 0) {
			throw new IllegalArgumentException("The provided account balance cannot be empty or negative.");
		}
        else {
          customer.setIsVerified(newIsVerified);
          customer.setIsLocal(newIsLocal);
		  customer.setAccountBalance(newBalance);
          customerRepository.save(customer);
          return customer;
        }
      }
    }
}
