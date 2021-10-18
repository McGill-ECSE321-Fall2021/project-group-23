package ca.mcgill.ecse321.librarysystem.dao;

import java.sql.Time;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.librarysystem.model.Shift;
import ca.mcgill.ecse321.librarysystem.model.Shift.DayOfWeek;

public interface ShiftRepository extends CrudRepository<Shift, Integer> {
	Shift findByShiftId(int shiftId);
	
	Shift findByWorkingDayAndStartTimeAndEndTime(DayOfWeek workingDay, Time startTime, Time endTime);
	
	boolean existsByWorkingDayAndStartTimeAndEndTime(DayOfWeek workingDay, Time startTime, Time endTime);
	
	boolean existsByShiftId(int shiftId);
}
