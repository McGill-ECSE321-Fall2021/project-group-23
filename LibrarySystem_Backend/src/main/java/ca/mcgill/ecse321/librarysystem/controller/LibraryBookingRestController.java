package ca.mcgill.ecse321.librarysystem.controller;

import java.sql.Date;
import java.sql.Time;
import java.util.List;
import java.util.stream.Collectors;
import java.util.ArrayList;
import java.time.LocalTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import ca.mcgill.ecse321.librarysystem.service.LibraryBookingService;
import ca.mcgill.ecse321.librarysystem.service.CustomerService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.DeleteMapping;
import ca.mcgill.ecse321.librarysystem.dto.LibraryBookingDto;
import ca.mcgill.ecse321.librarysystem.model.LibraryBooking;

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
@PostMapping(value = {"/createLibraryBooking/{customerId}", "/createLibraryBooking/{customerId}/" })
public LibraryBookingDto createLibraryBooking(
    @PathVariable("customerId") int customerId,
    @RequestParam Date startDate,
    @RequestParam Date endDate,
    @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.TIME, pattern = "HH:mm") LocalTime startTime,
	@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.TIME, pattern = "HH:mm") LocalTime endTime
    
) {

    LibraryBookingDto libraryBookingDto = convertToDto(libraryBookingService.createLibraryBooking(startDate, endDate, Time.valueOf(startTime), Time.valueOf(endTime), customerId));
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
@PutMapping(value = { "/updateLibraryBookingDateAndTime/{libraryBookingId}", "/updateLibraryBookingDateAndTime/{libraryBookingId}/" })
public LibraryBookingDto updateLibraryBookingDateAndTime(
    @PathVariable("libraryBookingId") int reservationId,
    @RequestParam Date startDate,
    @RequestParam Date endDate,
    @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.TIME, pattern = "HH:mm") LocalTime startTime,
	@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.TIME, pattern = "HH:mm") LocalTime endTime
    )
{
    LibraryBookingDto libraryBooking = convertToDto(libraryBookingService.updateLibraryBookingDateAndTime(reservationId, startDate, endDate, Time.valueOf(startTime), Time.valueOf(endTime)));
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
	 * converts a libraryBooking to a libraryBooking Dto
	 * 
	 */
private LibraryBookingDto convertToDto(LibraryBooking LibraryBooking) {
    if (LibraryBooking == null) {
        throw new InvalidInputException("There is no such LibraryBooking!");
    }
    LibraryBookingDto libraryBooking = new LibraryBookingDto(LibraryBooking.getId(), LibraryBooking.getCustomer().getAccountId(), LibraryBooking.getStartDate(), LibraryBooking.getEndDate(), LibraryBooking.getStartTime(), LibraryBooking.getEndTime());
    return libraryBooking;
}

}
