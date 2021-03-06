package ca.mcgill.ecse321.librarysystem.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.librarysystem.model.Holiday;



public interface HolidayRepository extends CrudRepository<Holiday, String>{

	Holiday findHolidayByName(String name);

}