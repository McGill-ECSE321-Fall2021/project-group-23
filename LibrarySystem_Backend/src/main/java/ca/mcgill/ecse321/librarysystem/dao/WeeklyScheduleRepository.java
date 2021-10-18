package ca.mcgill.ecse321.librarysystem.dao;

import java.util.Set;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.librarysystem.model.Shift;
import ca.mcgill.ecse321.librarysystem.model.WeeklySchedule;

public interface WeeklyScheduleRepository extends CrudRepository<WeeklySchedule, Integer> {
	WeeklySchedule findByWeeklyScheduleId(int weeklyScheduleId);
	
	WeeklySchedule findByLibrarianShiftsIn(Set<Shift> librarianShifts);
	
	boolean existsByWeeklyScheduleId(int weeklyScheduleId);
	
	boolean existsByLibrarianShiftsIn(Set<Shift> librarianShifts);
}
