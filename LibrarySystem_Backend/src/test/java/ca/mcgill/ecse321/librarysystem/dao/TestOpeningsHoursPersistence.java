package ca.mcgill.ecse321.librarysystem.dao;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

import ca.mcgill.ecse321.librarysystem.model.Holiday;
import ca.mcgill.ecse321.librarysystem.model.OpeningsHours;
import ca.mcgill.ecse321.librarysystem.model.OpeningsHours.DayOfWeek;

@RestController
@SpringBootApplication
public class TestOpeningsHoursPersistence {
	@Autowired
	private HolidayRepository holidayRepository;

	@AfterEach
	public void clearDatabase() {
		holidayRepository.deleteAll();
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
		/*openingsHoursRepository.save(oh);

		oh = null;
		oh = OpeningsHoursRepository.findOpeningsHoursByDayOfWeek(dayOfWeek);

		assertNotNull(oh);
		assertEquals(dayOfWeek, oh.getOpeningDay());
		assertEquals(startTime, oh.getStartTime());
		assertEquals(endTime, oh.getEndTime());*/
	}
}