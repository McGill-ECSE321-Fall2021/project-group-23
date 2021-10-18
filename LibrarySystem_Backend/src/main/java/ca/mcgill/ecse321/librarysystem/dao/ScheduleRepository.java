package ca.mcgill.ecse321.librarysystem.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.librarysystem.model.Librarian;
import ca.mcgill.ecse321.librarysystem.model.Shift;
import ca.mcgill.ecse321.librarysystem.model.WeeklySchedule;

public interface ScheduleRepository extends CrudRepository<WeeklySchedule, Integer> {
	
	//WeeklySchedule findByWeeklyScheduleId(Integer weeklyScheduleId);
	
	// may or may not need
	//WeeklySchedule findByLibrarianAndWeeklyScheduleId(Librarian librarian, Integer weeklyScheduleId);
	
	//WeeklySchedule findByLibrarianAndShift(Shift shift, Librarian librarian);
	
	//boolean existsByLibrarian(Librarian librarian);
	
	//boolean existsByWeeklyScheduleId(Integer weeklyScheduleId);
}
