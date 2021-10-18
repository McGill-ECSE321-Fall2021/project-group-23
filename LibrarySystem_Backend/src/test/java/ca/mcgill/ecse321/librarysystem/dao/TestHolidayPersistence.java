package ca.mcgill.ecse321.librarysystem.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.sql.Date;
import java.time.LocalDate;
import java.time.Month;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import ca.mcgill.ecse321.librarysystem.model.Holiday;


@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TestHolidayPersistence {
	@Autowired
	private HolidayRepository holidayRepository;

	@AfterEach
	public void clearDatabase() {
		holidayRepository.deleteAll();
	}

	@Test
	public void testPersistAndHoliday() {
		String name = "Winter Break";
		Date startDate = java.sql.Date.valueOf(LocalDate.of(2021, Month.DECEMBER, 15));
		Date endDate = java.sql.Date.valueOf(LocalDate.of(2021, Month.DECEMBER, 31));
		
		Holiday holiday = new Holiday();
		holiday.setName(name);
		holiday.setStartDate(startDate);
		holiday.setEndDate(endDate);
		holidayRepository.save(holiday);

		holiday = null;
		holiday = holidayRepository.findHolidayByName(name);

		assertNotNull(holiday);
		assertEquals(name, holiday.getName());
		assertEquals(startDate, holiday.getStartDate());
		assertEquals(endDate, holiday.getEndDate());
	}
}