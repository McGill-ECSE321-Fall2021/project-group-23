package ca.mcgill.ecse321.librarysystem.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import ca.mcgill.ecse321.librarysystem.dto.CustomerDto;
import ca.mcgill.ecse321.librarysystem.dto.LibrarianDto;
import ca.mcgill.ecse321.librarysystem.dto.ShiftDto;
import ca.mcgill.ecse321.librarysystem.dto.WeeklyScheduleDto;
import ca.mcgill.ecse321.librarysystem.model.Customer;
import ca.mcgill.ecse321.librarysystem.model.Librarian;
import ca.mcgill.ecse321.librarysystem.model.Shift;
import ca.mcgill.ecse321.librarysystem.model.WeeklySchedule;
import ca.mcgill.ecse321.librarysystem.service.LibrarianService;

@CrossOrigin(origins = "*")
@RestController
public class LibrarianRestController {
	@Autowired LibrarianService librarianService;
	
	// Return all current librarians in the database
	@GetMapping(value = { "/getAllLibrarians", "/getAllLibrarians/" })
	public List<LibrarianDto> getAllLibrarians() {
		List<LibrarianDto> librarianDtos = new ArrayList<>();
		for (Librarian librarian : librarianService.getAllLibrarians()) {
			librarianDtos.add(convertToDto(librarian));
		}
		return librarianDtos;
	}
	
	// Return librarian matching id
	@GetMapping(value = { "/getLibrarianById/{librarianId}", "getLibrarianById/{librarianId}/" })
	public LibrarianDto getLibrarianById(@PathVariable("librarianId") int librarianId)
	throws IllegalArgumentException{
		return convertToDto(librarianService.getLibrarianByAccountId(librarianId));
	}
	
	// Return librarian matching names
	@GetMapping(value = { "/getLibrarianByNames/{firstName}/{lastName}", "getLibrarianByNames/{firstName}/{lastName}/" })
	public LibrarianDto getLibrarianByFirstAndLastName(@PathVariable("firstName") String firstName, @PathVariable("lastName") String lastName)
	throws IllegalArgumentException{
		return convertToDto(librarianService.getLibrarianByNames(firstName, lastName));
	}
	
	// Return librarian with associated schedule
	@GetMapping(value = { "/getLibrarianBySchedule/{weeklyScheduleId}", "getLibrarianBySchedule/{weeklyScheduleId}/" })
	public LibrarianDto getLibrarianByWeeklySchedule(@PathVariable("weeklyScheduleId") int weeklyScheduleId)
	throws IllegalArgumentException{
		return convertToDto(librarianService.getLibrarianByScheduleId(weeklyScheduleId));
	}
	
	// Create a new librarian account
	@PostMapping(value = { "/createLibrarian/{firstName}/{lastName}/{password}/{scheduleId}", "/createLibrarian/{firstName}/{lastName}/{password}/{scheduleId}/" })
	public LibrarianDto createLibrarian(@PathVariable("firstName") String firstName, 
			@PathVariable("lastName") String lastName,
			@PathVariable("password") String password,
			@PathVariable("scheduleId") int scheduleId) throws IllegalArgumentException{
		Librarian librarian = librarianService.createLibrarian(firstName, lastName, password, scheduleId);
		return convertToDto(librarian);
	}
	
	// Update the customer's verification status, and/or local status, and/or balance
	@PutMapping(value= { "/updateCustomer/{id}/{newIsVerified}/{newIsLocal}/{newBalance}", "/updateCustomer/{id}/{newIsVerified}/{newIsLocal}/{newBalance}/" })
  public CustomerDto updateCustomer(@PathVariable("id") int id, @PathVariable("newIsVerified") boolean newIsVerified, @PathVariable("newIsLocal") boolean newIsLocal, @PathVariable("newBalance") int newBalance) {
      Customer customer = librarianService.updateCustomer(id, newIsVerified, newIsLocal, newBalance);
      return convertToDto(customer);
  }

	// Update the librarian's schedule
	@PutMapping(value = { "/updateLibrarianSchedule/{librarianId}/{newScheduleId}", "/updateLibrarianSchedule/{librarianId}/{newScheduleId}/" })
	public LibrarianDto updateLibrarianSchedule(@PathVariable("librarianId") int librarianId, @PathVariable("newScheduleId") int newScheduleId) {
		Librarian librarian = librarianService.assignWeeklySchedule(librarianId, newScheduleId);
		return convertToDto(librarian);
	}
	
	// Delete a librarian
	@DeleteMapping(value = { "/deleteLibrarian/{librarianId}", "/deleteLibrarian/{librarianId}/" }) 
	public LibrarianDto deleteLibrarian(@PathVariable("librarianId") int librarianId) {
		return convertToDto(librarianService.deleteLibrarian(librarianId));
	}
	
	// Delete all librarians in the system
	@DeleteMapping(value = { "/deleteAllLibrarians", "deleteAllLibrarians" })
	public List<LibrarianDto> deleteAllLibrarians() {
		List<LibrarianDto> librarianDtos = new ArrayList<>();
		for (Librarian librarian : librarianService.deleteAllLibrarians()) {
			LibrarianDto librarianDto = convertToDto(librarian);
			librarianDtos.add(librarianDto);
		}
		return librarianDtos;
	}
	
	private LibrarianDto convertToDto(Librarian l) {
		if (l == null) {
			throw new IllegalArgumentException("There is no such Librarian!");
		}
		LibrarianDto librarianDto = new LibrarianDto(l.getAccountId(), l.getFirstName(), l.getLastName(), l.getPassword(), convertToDto(l.getLibrarianSchedule()));
		return librarianDto;
	}
	
	private WeeklyScheduleDto convertToDto(WeeklySchedule ws) {
		if (ws == null) {
			throw new IllegalArgumentException("There is no such WeeklySchedule!");
		}
		Set<ShiftDto> shiftDtos = new HashSet<ShiftDto>();
		for (Shift s : ws.getShifts()) {
			shiftDtos.add(convertToDto(s));
		}
		WeeklyScheduleDto weeklyScheduleDto = new WeeklyScheduleDto(ws.getWeeklyScheduleId(), shiftDtos);
		return weeklyScheduleDto;
	}
	
	private ShiftDto convertToDto(Shift s) {
		if (s == null) {
			throw new IllegalArgumentException("There is no such Shift!");
		}
		ShiftDto shiftDto = new ShiftDto(s.getWorkingDay(),s.getStartTime(),s.getEndTime(),s.getShiftId());
		return shiftDto;
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
