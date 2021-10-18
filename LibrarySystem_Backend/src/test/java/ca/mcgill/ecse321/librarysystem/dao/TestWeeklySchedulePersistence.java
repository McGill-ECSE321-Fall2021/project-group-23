package ca.mcgill.ecse321.librarysystem.dao;

import java.sql.Time;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import ca.mcgill.ecse321.librarysystem.model.Shift;
import ca.mcgill.ecse321.librarysystem.model.WeeklySchedule;
import ca.mcgill.ecse321.librarysystem.model.Shift.DayOfWeek;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TestWeeklySchedulePersistence {
	@Autowired
	private WeeklyScheduleRepository weeklyScheduleRepository;
	@Autowired
	private ShiftRepository shiftRepository;
	
	@AfterEach
	public void clearDatabaseAfter() {
		weeklyScheduleRepository.deleteAll();
		shiftRepository.deleteAll();
	}
	
	@Test
	public void testPersistAndLoadWeeklyScheduleById() {
		// First test (by schedule id)
		WeeklySchedule weeklySchedule = new WeeklySchedule();
		
		// needed to get the id generated in database
		WeeklySchedule weeklySchedule1 = weeklyScheduleRepository.save(weeklySchedule);
		
		weeklySchedule = null;
		
		weeklySchedule = weeklyScheduleRepository.findByWeeklyScheduleId(weeklySchedule1.getWeeklyScheduleId());
		assertNotNull(weeklySchedule);
		assertEquals(weeklySchedule.getWeeklyScheduleId(), weeklySchedule1.getWeeklyScheduleId());
	}
	
	@Test
	public void testPersistAndLoadWeeklyScheduleByShifts() {
		// Second test (by shifts)
		Set<Shift> shifts = new HashSet<Shift>();
		Shift newShift = new Shift();
		newShift.setWorkingDay(DayOfWeek.Monday);
		newShift.setStartTime(Time.valueOf("10:00:00"));
		newShift.setEndTime(Time.valueOf("16:00:00"));
		shiftRepository.save(newShift);
		
		shifts.add(newShift);
		
		WeeklySchedule weeklySchedule = new WeeklySchedule();
		weeklySchedule.setLibrarianShifts(shifts);
		
		weeklyScheduleRepository.save(weeklySchedule);
		
		weeklySchedule = null;
		
		weeklySchedule = weeklyScheduleRepository.findByLibrarianShiftsIn(shifts);
		assertNotNull(weeklySchedule);
		assertEquals(weeklySchedule.getLibrarianShifts(), shifts);
	}
}
