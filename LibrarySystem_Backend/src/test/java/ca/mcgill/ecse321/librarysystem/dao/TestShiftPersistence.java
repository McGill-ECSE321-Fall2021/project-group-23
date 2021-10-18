package ca.mcgill.ecse321.librarysystem.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.sql.Time;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import ca.mcgill.ecse321.librarysystem.model.Shift;
import ca.mcgill.ecse321.librarysystem.model.Shift.DayOfWeek;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TestShiftPersistence {
	@Autowired
	private ShiftRepository shiftRepository;
	
	@BeforeEach
	public void clearDatabase() {
		shiftRepository.deleteAll();
	}
	
	@Test
	public void testPersistAndLoadShiftById() {
		// First test (find by shift id)
		Shift shift = new Shift();
		
		Shift shift1 = shiftRepository.save(shift);
		
		shift = null;
		
		shift = shiftRepository.findByShiftId(shift1.getShiftId());
		assertNotNull(shift);
		assertEquals(shift.getShiftId(), shift1.getShiftId());
	}
	
	@Test
	public void testPersistAndLoadShiftByDayAndTimes() {
		// Second test (finding by day of week and times)
		Shift shift = new Shift();
		shift.setWorkingDay(DayOfWeek.Monday);
		shift.setStartTime(Time.valueOf("10:00:00"));
		shift.setEndTime(Time.valueOf("16:00:00"));
		
		shiftRepository.save(shift);
		
		shift = null;
		
		shift = shiftRepository.findByWorkingDayAndStartTimeAndEndTime(DayOfWeek.Monday,Time.valueOf("10:00:00") , Time.valueOf("16:00:00"));
		assertNotNull(shift);
		assertEquals(shift.getWorkingDay(), DayOfWeek.Monday);
		assertEquals(shift.getStartTime(), Time.valueOf("10:00:00"));
		assertEquals(shift.getEndTime(), Time.valueOf("16:00:00"));
	}
}
