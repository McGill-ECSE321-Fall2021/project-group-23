package ca.mcgill.ecse321.librarysystem.dao;

import java.sql.Time;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import ca.mcgill.ecse321.librarysystem.model.OpeningsHours;
import ca.mcgill.ecse321.librarysystem.model.OpeningsHours.DayOfWeek;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TestOpeningsHoursPersistence {
	@Autowired
	private OpeningsHoursRepository openingsHoursRepository;

	@AfterEach
	public void clearDatabase() {
		openingsHoursRepository.deleteAll();
	}

	@Test
	public void testPersistAndOpeningsHours() {
		
		DayOfWeek dayOfWeek = DayOfWeek.Monday;
		Time startTime = java.sql.Time.valueOf(LocalTime.of(9, 30));
		Time endTime = java.sql.Time.valueOf(LocalTime.of(17, 00));
		
		OpeningsHours oh = new OpeningsHours();
		oh.setOpeningDay(dayOfWeek);
		oh.setStartTime(startTime);
		oh.setEndTime(endTime);
		openingsHoursRepository.save(oh);

		oh = null;
		oh = openingsHoursRepository.findOpeningsHoursByOpeningDay(dayOfWeek);

		assertNotNull(oh);
		assertEquals(dayOfWeek, oh.getOpeningDay());
		assertEquals(startTime, oh.getStartTime());
		assertEquals(endTime, oh.getEndTime());
	}
}