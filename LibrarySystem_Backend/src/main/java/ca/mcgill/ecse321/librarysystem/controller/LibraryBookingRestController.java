package ca.mcgill.ecse321.librarysystem.controller;

import java.sql.Date;
import java.sql.Time;
import java.util.List;
import java.util.stream.Collectors;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import ca.mcgill.ecse321.librarysystem.service.LibraryBookingService;
import ca.mcgill.ecse321.librarysystem.service.CustomerService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import ca.mcgill.ecse321.librarysystem.dto.LibraryBookingDto;
import ca.mcgill.ecse321.librarysystem.model.Customer;
import ca.mcgill.ecse321.librarysystem.model.LibraryBooking;
import ca.mcgill.ecse321.librarysystem.dto.CustomerDto;

@CrossOrigin(origins = "*")
@RestController
public class LibraryBookingRestController {

@Autowired
private LibraryBookingService libraryBookingService;
    
@Autowired
private CustomerService customerService;

/**
	 * creates a libraryBooking 
	 * 
	 */
@PostMapping(value = {"/createLibraryBooking/{startDate}/{endDate}/{startTime}/{endTime}/{customerId}", "/createLibraryBooking/{startDate}/{endDate}/{startTime}/{endTime}/{customerId}/" })
public LibraryBookingDto createLibraryBooking(
    @PathVariable("startDate")Date startDate,
    @PathVariable("endDate")Date endDate,
    @PathVariable("startTime")Time startTime,
    @PathVariable("endTime")Time endTime,
    @PathVariable("customerId")int customerId
) {
    Customer customerSelected = customerService.getCustomerByAccountId(customerId);
    LibraryBooking libraryBooking = new LibraryBooking();
    LibraryBookingDto libraryBookingDto = new LibraryBookingDto(libraryBooking.getId(), convertToDto(customerSelected), startDate, endDate, startTime, endTime);
    return libraryBookingDto;
}

/**
	 * gets all the libraryBookingss
	 * 
	 */
@GetMapping(value = {"/getAllLibraryBooking", "/getAllLibraryBooking/" })
public List<LibraryBookingDto> getAllReservation() {
    return libraryBookingService.getAllLibraryBooking().stream().map(u -> convertToDto(u)).collect(Collectors.toList());
}

/**
	 * gets the libraryBooking with a unique id
	 * 
	 */
@GetMapping(value = {"/getLibraryBookingById/{libraryBookingId}", "/getLibraryBookingById/{libraryBookingId}/"})
public LibraryBookingDto getLibraryBookingById(
    @PathVariable("libraryBookingId") int libraryBookingId
) {
    return convertToDto(libraryBookingService.getLibraryBookingbyId(libraryBookingId));
}

/**
	 * gets all the libraryBookings made by a customer
	 * 
	 */
@GetMapping(value = {"/getLibraryBookingByCustomer/{customerId}", "/getLibraryBookingByCustomer/{customerId}/" })
public List<LibraryBookingDto> getLibraryBookingByCustomer(@PathVariable("customerId") int customerId) {
    List<LibraryBookingDto> libraryBookingDtos = new ArrayList<>();
    for (LibraryBooking libraryBooking : libraryBookingService.getLibraryBookingByCustomer(customerService.getCustomerByAccountId(customerId))) {
       libraryBookingDtos.add(convertToDto(libraryBooking));
    }
    return libraryBookingDtos;
}

/**
	 * deletes a libraryBooking with a specific id
	 * 
	 */
@DeleteMapping(value = { "/deleteLibraryBooking/{libraryBookingId}", "/deleteLibraryBooking/{libraryBookingId}/" })
public LibraryBookingDto deleteLibraryBooking(@PathVariable("libraryBookingId") int libraryBookingId) {
    LibraryBookingDto libraryBooking = convertToDto(libraryBookingService.deleteLibraryBooking(libraryBookingId));
    return libraryBooking;
}

/**
	 * deletes all libraryBookings
	 * 
	 */
@DeleteMapping(value = { "/deleteAllLibraryBooking", "/deleteAllLibraryBooking/" })
public List<LibraryBookingDto> deleteAllLibraryBooking() {
    List<LibraryBookingDto> libraryBookingDtos = new ArrayList<>();
    for (LibraryBooking libraryBooking : libraryBookingService.getAllLibraryBooking()) {
       libraryBookingDtos.add(convertToDto(libraryBooking));
    }
    libraryBookingService.deleteAllLibraryBooking();
    return libraryBookingDtos;
}

/**
	 * updates a libraryBooking date and time
	 * 
	 */
@PutMapping(value = { "/updateLibraryBookingDateAndTime/{libraryBookingId}/{startDate}/{endDate}/{startTime}/{endTime}", "/updateLibraryBookingDateAndTime/{libraryBookingId}/{startDate}/{endDate}/{startTime}/{endTime}/" })
public LibraryBookingDto updateLibraryBookingDateAndTime(
    @PathVariable("libraryBookingId") int reservationId,
    @PathVariable("startDate") Date startDate,
    @PathVariable("startDate") Date endDate,
    @PathVariable("startTime") Time startTime,
    @PathVariable("endTime") Time endTime
    )
{
    LibraryBookingDto libraryBooking = convertToDto(libraryBookingService.updateLibraryBookingDateAndTime(reservationId, startDate, endDate, startTime, endTime));
    return libraryBooking;

}

/**
	 * updates a libraryBooking customer
	 * 
	 */
@PutMapping(value = { "updateLibraryBookingCustomer/{libraryBookingId}/{customerId}", "updateLibraryBookingCustomer/{libraryBookingnId}/{customerId}" })
public LibraryBookingDto updateLibraryBookingCustomer(
    @PathVariable("libraryBookingId") int reservationId,
    @PathVariable("customerId") int customerId
) {
    LibraryBookingDto libraryBooking = convertToDto(libraryBookingService.updateLibraryBookingCustomer(reservationId, customerService.getCustomerByAccountId(customerId)));
    return libraryBooking;
}
/**
	 * converts a customer to a customer Dto
	 * 
	 */
private CustomerDto convertToDto(Customer customer) {
    if (customer == null) {
        throw new InvalidInputException("There is no such customer!");
    }
    CustomerDto customerDto = new CustomerDto(customer.getFirstName(), customer.getLastName(), customer.getAccountId(), customer.getPassword(), customer.getEmail(), customer.getIsVerified(), customer.getIsLocal(), customer.getAccountBalance());
    return customerDto;
}

/**
	 * converts a libraryBooking to a libraryBooking Dto
	 * 
	 */
private LibraryBookingDto convertToDto(LibraryBooking LibraryBooking) {
    if (LibraryBooking == null) {
        throw new InvalidInputException("There is no such LibraryBooking!");
    }
    LibraryBookingDto libraryBookingDto = new LibraryBookingDto();
    return libraryBookingDto;
}

}
