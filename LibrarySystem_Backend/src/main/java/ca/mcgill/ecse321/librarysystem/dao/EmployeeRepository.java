package ca.mcgill.ecse321.librarysystem.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.librarysystem.model.Librarian;

public interface EmployeeRepository extends CrudRepository<Librarian, Integer>{
	
	Librarian findByAccountId(Integer id);
	
	Librarian findByName(String firstName, String lastName);
	
	boolean existsByAccountId(Integer id);
	
	boolean existsByName(String firstName, String lastName);
}
