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

import ca.mcgill.ecse321.librarysystem.dto.LibrarianDto;
import ca.mcgill.ecse321.librarysystem.model.Librarian;
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
	@PostMapping(value = { "/createLibrarian/{firstName}/{lastName}/{password}", "/createLibrarian/{firstName}/{lastName}/{password}/" })
	public LibrarianDto createLibrarian(@PathVariable("firstName") String firstName, 
			@PathVariable("lastName") String lastName,
			@PathVariable("password") String password) throws IllegalArgumentException{
		Librarian librarian = librarianService.createLibrarian(firstName, lastName, password);
		return convertToDto(librarian);
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
	
	// Helper method to convert librarians to DTOs
	private LibrarianDto convertToDto(Librarian l) {
		if (l == null) {
			throw new IllegalArgumentException("There is no such Librarian!");
		}
		LibrarianDto librarianDto = new LibrarianDto(l.getAccountId(), l.getFirstName(), l.getLastName(), l.getPassword(), l.getLibrarianSchedule());
		return librarianDto;
	}
}
