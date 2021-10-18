package ca.mcgill.ecse321.librarysystem.dao;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.AfterEach;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import ca.mcgill.ecse321.librarysystem.model.Librarian;
import ca.mcgill.ecse321.librarysystem.model.WeeklySchedule;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TestLibrarianPersistence {
	@Autowired
	private WeeklyScheduleRepository weeklyScheduleRepository;
	@Autowired
	private LibrarianRepository librarianRepository;
	
	@AfterEach
	public void clearDatabaseAfter() {
		librarianRepository.deleteAll();
		weeklyScheduleRepository.deleteAll();
	}
	
	@Test
	public void testPersistAndLoadLibrarianById() {
		// First test (find by account id)
		Integer accountId = 0;
		Librarian librarian = new Librarian();
		librarian.setAccountId(accountId);
		
		Librarian librarian1 = librarianRepository.save(librarian);
		
		librarian = null;
		
		librarian = librarianRepository.findByAccountId(librarian1.getAccountId());
		
		assertNotNull(librarian);
		assertEquals(librarian.getAccountId(), librarian1.getAccountId());
	}
	
	@Test
	public void testPersistAndLoadLibrarianByFirstNameAndLastName() {
		// Second test (find by full name)
		String firstName = "Abiola";
		String lastName = "Olaniyan";
		Librarian librarian = new Librarian();
		librarian.setFirstName(firstName);
		librarian.setLastName(lastName);
		
		librarianRepository.save(librarian);
		
		librarian = null;
		
		librarian = librarianRepository.findByFirstNameAndLastName(firstName, lastName);
		
		assertNotNull(librarian);
		assertEquals(librarian.getFirstName(), firstName);
		assertEquals(librarian.getLastName(), lastName);
	}
	
	@Test
	public void testPersistAndLoadLibrarianByWeeklySchedule() {
		// Third test (find by weekly schedule)
		WeeklySchedule weeklySchedule = new WeeklySchedule();
		Librarian librarian = new Librarian();
		librarian.setLibrarianSchedule(weeklySchedule);
		weeklyScheduleRepository.save(weeklySchedule);
		
		librarianRepository.save(librarian);
		
		librarian = null;
		
		librarian = librarianRepository.findByLibrarianSchedule(weeklySchedule);
		
		assertNotNull(librarian);
		assertEquals(librarian.getLibrarianSchedule(), weeklySchedule);
	}
}
