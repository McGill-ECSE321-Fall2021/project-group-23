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
import ca.mcgill.ecse321.librarysystem.model.WeeklySchedule;
import ca.mcgill.ecse321.librarysystem.service.LibrarianService;

@CrossOrigin(origins = "*")
@RestController
public class LibrarianRestController {
	@Autowired LibrarianService librarianService;
	
	@GetMapping(value = { "/librarians", "/librarians/" })
	public List<LibrarianDto> getAllLibrarians() {
		List<LibrarianDto> librarianDtos = new ArrayList<>();
		for (Librarian librarian : librarianService.getAllLibrarians()) {
			librarianDtos.add(convertToDto(librarian));
		}
		return librarianDtos;
	}
	
	@GetMapping(value = { "/librarian/{librarianId}", "librarian/{librarianId}/" })
	public LibrarianDto getLibrarianById(@PathVariable("librarianId") int librarianId)
	throws IllegalArgumentException{
		return convertToDto(librarianService.getLibrarian(librarianId));
	}
	
	@GetMapping(value = { "/librarian/{firstName}/{lastName}", "librarian/{firstName}/{lastName}/" })
	public LibrarianDto getLibrarianByFirstAndLastName(@PathVariable("firstName") String firstName, @PathVariable("lastName") String lastName)
	throws IllegalArgumentException{
		return convertToDto(librarianService.getLibrarian(firstName, lastName));
	}
	
	@GetMapping(value = { "/librarian/{weeklySchedule}", "librarian/{weeklySchedule}/" })
	public LibrarianDto getLibrarianByWeeklySchedule(@PathVariable("weeklySchedule") WeeklySchedule weeklySchedule)
	throws IllegalArgumentException{
		return convertToDto(librarianService.getLibrarian(weeklySchedule));
	}
	
	@PostMapping(value = { "/createLibrarian/{firstName}/{lastName}/{password}", "/createLibrarian/{firstName}/{lastName}/{password}/" })
	public LibrarianDto createLibrarian(@PathVariable("firstName") String firstName, 
			@PathVariable("lastName") String lastName,
			@PathVariable("password") String password) throws IllegalArgumentException{
		Librarian librarian = librarianService.createLibrarian(firstName, lastName, password);
		return convertToDto(librarian);
	}
	
	@PutMapping(value = { "/updateLibrarianSchedule/{librarianId}/{newSchedule}", "/updateLibrarianSchedule/{librarianId}/{newSchedule}/" })
	public LibrarianDto updateLibrarianSchedule(@PathVariable("librarianId") int librarianId, @PathVariable("newSchedule") WeeklySchedule newSchedule) {
		Librarian librarian = librarianService.assignWeeklySchedule(librarianId, newSchedule);
		return convertToDto(librarian);
	}
	
	@DeleteMapping(value = { "/deleteLibrarian/{librarianId}", "/deleteLibrarian/{librarianId}/" }) 
	public LibrarianDto deleteLibrarian(@PathVariable("librarianId") int librarianId) {
		return convertToDto(librarianService.deleteLibrarian(librarianId));
	}
	
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
		LibrarianDto librarianDto = new LibrarianDto(l.getAccountId(), l.getFirstName(), l.getLastName(), l.getPassword(), l.getLibrarianSchedule());
		return librarianDto;
	}
}
