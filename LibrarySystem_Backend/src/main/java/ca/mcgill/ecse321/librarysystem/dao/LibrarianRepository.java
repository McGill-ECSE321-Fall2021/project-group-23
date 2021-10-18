package ca.mcgill.ecse321.librarysystem.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.librarysystem.model.Librarian;
import ca.mcgill.ecse321.librarysystem.model.WeeklySchedule;

public interface LibrarianRepository extends CrudRepository<Librarian, Integer>{
	Librarian findByAccountId(int accountId);
	
	Librarian findByFirstNameAndLastName(String firstName, String lastName);
	
	Librarian findByLibrarianSchedule(WeeklySchedule librarianSchedule);
	
	boolean existsByAccountId(int accountId);
	
	boolean existsByLibrarianSchedule(WeeklySchedule librarianSchedule);
	
	boolean existsByFirstNameAndLastName(String firstName, String lastName);
}
