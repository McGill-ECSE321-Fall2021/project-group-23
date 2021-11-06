package ca.mcgill.ecse321.librarysystem.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.librarysystem.dao.LibrarianRepository;
import ca.mcgill.ecse321.librarysystem.dao.WeeklyScheduleRepository;
import ca.mcgill.ecse321.librarysystem.model.Librarian;
import ca.mcgill.ecse321.librarysystem.model.WeeklySchedule;

@Service
public class LibrarianService {
	@Autowired 
	LibrarianRepository librarianRepository;
	@Autowired
	WeeklyScheduleRepository weeklyScheduleRepository;
	
	@Transactional
	public Librarian createLibrarian(String firstName, String lastName, String password) {
		// Check for empty or null fields
		if (firstName == null || firstName.trim().length() == 0) {
			throw new IllegalArgumentException("First name cannot be empty.");
		}
		if (lastName == null || lastName.trim().length() == 0) {
			throw new IllegalArgumentException("Last name cannot be empty.");
		}
		if (password == null || password.trim().length() == 0) {
			throw new IllegalArgumentException("Password cannot be empty.");
		}
		
		Librarian librarian = new Librarian();
		librarian.setFirstName(firstName);
		librarian.setLastName(lastName);
		librarian.setPassword(password);
		librarianRepository.save(librarian);
		return librarian;
	}
	
	@Transactional
	public Librarian assignWeeklySchedule(int id, WeeklySchedule newSchedule) {
		// check if weekly schedule exists
		if (!weeklyScheduleRepository.existsByWeeklyScheduleId(newSchedule.getWeeklyScheduleId())) {
			throw new IllegalArgumentException("Provided weekly schedule could not be found in the database");
		}
		// check if librarian exists
		if (!librarianRepository.existsByAccountId(id)) {
			throw new IllegalArgumentException("Librarian with provided account id could not found.");
		}
		
		Librarian librarian = librarianRepository.findByAccountId(id);
		librarian.setLibrarianSchedule(newSchedule);
		librarianRepository.save(librarian);
		return librarian;
	}
	
	@Transactional
	public Librarian deleteLibrarian(int accountId) {
		// check if librarian exists
		if (!librarianRepository.existsByAccountId(accountId)) {
			throw new IllegalArgumentException("Librarian account to delete could not be found with provided id");
		}
		
		Librarian librarian = librarianRepository.findByAccountId(accountId);
		librarianRepository.delete(librarian);
		return librarian;
	}
	
	@Transactional
	public List<Librarian> deleteAllLibrarians() {
		List<Librarian> librarians = toList(librarianRepository.findAll());
		librarianRepository.deleteAll();
		return librarians;
	}
	
	@Transactional
	public Librarian getLibrarian(int id) {
		// check if librarian with provided id exists
		if (!librarianRepository.existsByAccountId(id)) {
			throw new IllegalArgumentException("Librarian with provided account id could not found.");
		}
		
		Librarian librarian = librarianRepository.findByAccountId(id);
		return librarian;
	}
	
	@Transactional
	public Librarian getLibrarian(String firstName, String lastName) {
		// check if librarian with first name and last name exists
		if (!librarianRepository.existsByFirstNameAndLastName(firstName, lastName)) {
			throw new IllegalArgumentException("Librarian with provided first name and last name could not be found.");
		}
		
		Librarian librarian = librarianRepository.findByFirstNameAndLastName(firstName, lastName);
		return librarian;
	}
	
	@Transactional
	public Librarian getLibrarian(WeeklySchedule schedule) {
		// Check if weekly schedule exists
		if (!weeklyScheduleRepository.existsByWeeklyScheduleId(schedule.getWeeklyScheduleId())) {
			throw new IllegalArgumentException("Provided weekly schedule could not be found in the database");
		}
		
		Librarian librarian = librarianRepository.findByLibrarianSchedule(schedule);
		return librarian;
	}
	
	@Transactional
	public List<Librarian> getAllLibrarians() {
		return toList(librarianRepository.findAll());
	}
	
	private <T> List<T> toList(Iterable<T> iterable){
		List<T> resultList = new ArrayList<T>();
		for (T t : iterable) {
			resultList.add(t);
		}
		return resultList;
	}
}
