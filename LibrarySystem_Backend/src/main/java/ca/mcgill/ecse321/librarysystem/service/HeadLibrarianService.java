package ca.mcgill.ecse321.librarysystem.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.librarysystem.dao.LibrarianRepository;
import ca.mcgill.ecse321.librarysystem.model.HeadLibrarian;
import ca.mcgill.ecse321.librarysystem.model.Librarian;
import ca.mcgill.ecse321.librarysystem.model.WeeklySchedule;

@Service
public class HeadLibrarianService extends LibrarianService {
	@Autowired 
	LibrarianRepository librarianRepository;
	
	@Transactional
	public HeadLibrarian createHeadLibrarian(String firstName, String lastName, String password, int newScheduleId) {
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
		
		// Check if weeklySchedule exists
		if (!weeklyScheduleRepository.existsByWeeklyScheduleId(newScheduleId)) {
			throw new IllegalArgumentException("Weekly schedule with provided id could not be found in the database");
		}
		
		HeadLibrarian headLibrarian = new HeadLibrarian();
		headLibrarian.setFirstName(firstName);
		headLibrarian.setLastName(lastName);
		headLibrarian.setPassword(password);
		headLibrarian.setLibrarianSchedule(weeklyScheduleRepository.findByWeeklyScheduleId(newScheduleId));
		librarianRepository.save(headLibrarian);
		return headLibrarian;
	}
	
	@Transactional
	public HeadLibrarian updateHeadLibrarian(int accountId, String firstName, String lastName, String password, int newScheduleId) {
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
		
		// Check if weeklySchedule exists
		if (!weeklyScheduleRepository.existsByWeeklyScheduleId(newScheduleId)) {
			throw new IllegalArgumentException("Weekly schedule with provided id could not be found in the database");
		}
		
		if (!librarianRepository.existsById(accountId)) {
			throw new IllegalArgumentException("Head librarian with provided id could not be found in the database");
		}
		HeadLibrarian headLibrarian = (HeadLibrarian) librarianRepository.findByAccountId(accountId);
		headLibrarian.setFirstName(firstName);
		headLibrarian.setLastName(lastName);
		headLibrarian.setPassword(password);
		headLibrarian.setLibrarianSchedule(weeklyScheduleRepository.findByWeeklyScheduleId(newScheduleId));
		librarianRepository.save(headLibrarian);
		return headLibrarian;
	}
	
	@Transactional
	public Librarian createLibrarian(String firstName, String lastName, String password, int newScheduleId) {
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
		
		// Check if weeklySchedule exists
		if (!weeklyScheduleRepository.existsByWeeklyScheduleId(newScheduleId)) {
			throw new IllegalArgumentException("Weekly schedule with provided id could not be found in the database");
		}
		
		Librarian librarian = new Librarian();
		librarian.setFirstName(firstName);
		librarian.setLastName(lastName);
		librarian.setPassword(password);
		librarian.setLibrarianSchedule(weeklyScheduleRepository.findByWeeklyScheduleId(newScheduleId));
		librarianRepository.save(librarian);
		return librarian;
	}
	
	@Transactional
	public Librarian assignWeeklySchedule(int id, int newScheduleId) {
		// check if weekly schedule exists
		if (!weeklyScheduleRepository.existsByWeeklyScheduleId(newScheduleId)) {
			throw new IllegalArgumentException("Weekly schedule with provided id could not be found in the database");
		}
		// check if librarian exists
		if (!librarianRepository.existsByAccountId(id)) {
			throw new IllegalArgumentException("Librarian with provided account id could not found.");
		}
		
		Librarian librarian = librarianRepository.findByAccountId(id);
		librarian.setLibrarianSchedule(weeklyScheduleRepository.findByWeeklyScheduleId(newScheduleId));
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
	public Librarian getLibrarianByAccountId(int id) {
		// check if librarian with provided id exists
		if (!librarianRepository.existsByAccountId(id)) {
			throw new IllegalArgumentException("Librarian with provided account id could not found.");
		}
		
		Librarian librarian = librarianRepository.findByAccountId(id);
		return librarian;
	}
	
	@Transactional
	public Librarian getLibrarianByNames(String firstName, String lastName) {
		// check if librarian with first name and last name exists
		if (!librarianRepository.existsByFirstNameAndLastName(firstName, lastName)) {
			throw new IllegalArgumentException("Librarian with provided first name and last name could not be found.");
		}
		
		Librarian librarian = librarianRepository.findByFirstNameAndLastName(firstName, lastName);
		return librarian;
	}
	
	@Transactional
	public Librarian getLibrarianByScheduleId(int scheduleId) {
		// Check if weekly schedule exists
		if (!weeklyScheduleRepository.existsByWeeklyScheduleId(scheduleId)) {
			throw new IllegalArgumentException("Provided weekly schedule could not be found in the database");
		}
		WeeklySchedule schedule = weeklyScheduleRepository.findByWeeklyScheduleId(scheduleId);
		if (!librarianRepository.existsByLibrarianSchedule(schedule)) {
			throw new IllegalArgumentException("Librarian with provided weekly schedule could not be found.");
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
