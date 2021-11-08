package ca.mcgill.ecse321.librarysystem.dao;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import ca.mcgill.ecse321.librarysystem.model.WeeklySchedule;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TestWeeklySchedulePersistence {
	@Autowired
	private WeeklyScheduleRepository weeklyScheduleRepository;
	
	@AfterEach
	public void clearDatabaseAfter() {
		weeklyScheduleRepository.deleteAll();
		
		
	}
	@BeforeEach
	public void clearrDatabaseAfter() {
		weeklyScheduleRepository.deleteAll();
		
		
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
}
