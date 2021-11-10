package ca.mcgill.ecse321.librarysystem.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.lenient;
import static org.mockito.ArgumentMatchers.anyString;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;

import ca.mcgill.ecse321.librarysystem.dao.HolidayRepository;
import ca.mcgill.ecse321.librarysystem.model.Holiday;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;


@ExtendWith(MockitoExtension.class)
public class HolidayServiceTest {

	@Mock
	private HolidayRepository holidayRepository;

	@InjectMocks
	private HolidayService holidayService;

	private static final String HOLIDAY_KEY = "TestHoliday";
	private static final String NONEXISTING_KEY = "NotAHoliday";

	@BeforeEach
	public void setMockOutput() {
		lenient().when(holidayRepository.findHolidayByName(anyString())).thenAnswer((InvocationOnMock invocation) -> {
			if (invocation.getArgument(0).equals(HOLIDAY_KEY)) {
				Holiday holiday = new Holiday();
				holiday.setName(HOLIDAY_KEY);
				return holiday;
			} else {
				return null;
			}
		});
		lenient().when(holidayRepository.findAll()).thenAnswer((InvocationOnMock invocation) -> {
			Holiday holiday = new Holiday();
			holiday.setName(HOLIDAY_KEY);
			ArrayList<Holiday> list = new ArrayList<Holiday>();
			list.add(holiday);
			return list;
		});
		// Whenever anything is saved, just return the parameter object
		Answer<?> returnParameterAsAnswer = (InvocationOnMock invocation) -> {
			return invocation.getArgument(0);
		};
		lenient().when(holidayRepository.save(any(Holiday.class))).thenAnswer(returnParameterAsAnswer);

	}

	@Test
	public void testCreateHoliday() {
		Holiday holiday = null;
		String name = "Christmas";
		Calendar c = Calendar.getInstance();
		c.set(2021, Calendar.DECEMBER, 24, 0, 0, 0);
		Date startDate = new Date(c.getTimeInMillis());
		
		Calendar c1 = Calendar.getInstance();
		c1.set(2021, Calendar.DECEMBER, 25, 0, 0, 0);
		Date endDate = new Date(c1.getTimeInMillis());
		try {
			holiday = holidayService.createHoliday(name, startDate, endDate);
		} catch (IllegalArgumentException e) {
			fail();
		}
		assertNotNull(holiday);
		assertEquals(name,holiday.getName());
		assertEquals(startDate.toString(),holiday.getStartDate().toString());
		assertEquals(endDate.toString(),holiday.getEndDate().toString());
	}
	@Test
	public void testCreateHolidayAlreadyExist() {
		Holiday holiday = null;
		String error = null;

		Calendar c = Calendar.getInstance();
		c.set(2021, Calendar.DECEMBER, 24, 0, 0, 0);
		Date startDate = new Date(c.getTimeInMillis());
		
		Calendar c1 = Calendar.getInstance();
		c1.set(2021, Calendar.DECEMBER, 25, 0, 0, 0);
		Date endDate = new Date(c1.getTimeInMillis());
		try {
			holiday = holidayService.createHoliday(HOLIDAY_KEY, startDate, endDate);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertNull(holiday);
		assertEquals("There are already holiday for this day (Try updating).", error);
	}

	@Test
	public void testCreateHolidayNull() {
		Holiday holiday = null;
		String error = null;
		
		String name = null;
		Date startDate = null;
		Date endDate = null;
		try {
			holiday = holidayService.createHoliday(name, startDate, endDate);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertNull(holiday);
		assertEquals("holiday name cannot be empty! start date cannot be empty! end date cannot be empty!", error);

	}
	@Test
	public void testCreateHolidayEmpty() {
		Holiday holiday = null;
		String error = null;
		
		String name = null;
		Calendar c = Calendar.getInstance();
		c.set(2022, Calendar.MARCH, 16, 0, 0, 0);
		Date startDate = new Date(c.getTimeInMillis());
		
		Calendar c1 = Calendar.getInstance();
		c1.set(2023, Calendar.MARCH, 20, 0, 0, 0);
		Date endDate = new Date(c1.getTimeInMillis());
		try {
			holiday = holidayService.createHoliday(name, startDate, endDate);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertNull(holiday);
		assertEquals("holiday name cannot be empty!", error);
	}
	@Test
	public void testCreateHolidayStartDateEmpty() {
		Holiday holiday = null;
		String error = null;
		
		String name = "Halloween";
		Date startDate = null;
		
		Calendar c1 = Calendar.getInstance();
		c1.set(2023, Calendar.MARCH, 20, 0, 0, 0);
		Date endDate = new Date(c1.getTimeInMillis());
		try {
			holiday = holidayService.createHoliday(name, startDate, endDate);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertNull(holiday);
		assertEquals("start date cannot be empty!", error);
	}
	@Test
	public void testCreateHolidayEndDateEmpty() {
		Holiday holiday = null;
		String error = null;
		
		String name ="Summer";
		Calendar c = Calendar.getInstance();
		c.set(2022, Calendar.MARCH, 16, 0, 0, 0);
		Date startDate = new Date(c.getTimeInMillis());
		
		Date endDate = null;
		try {
			holiday = holidayService.createHoliday(name, startDate, endDate);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertNull(holiday);
		assertEquals("end date cannot be empty!", error);
	}

	@Test
	public void testCreateHolidayEndDateBeforeStartDate() {
		String name = "March Break";
		Calendar c = Calendar.getInstance();
		c.set(2022, Calendar.MARCH, 16, 0, 0, 0);
		Date startDate = new Date(c.getTimeInMillis());
		
		Calendar c1 = Calendar.getInstance();
		c1.set(2022, Calendar.MARCH, 10, 0, 0, 0);
		Date endDate = new Date(c1.getTimeInMillis());

		String error = null;
		Holiday holiday = null;
		try {
			holiday = holidayService.createHoliday(name, startDate, endDate);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertNull(holiday);
		// check error
		assertEquals("start date can't be after end date", error);
	}


	@Test
	public void testGetExistingHoliday() {
		assertEquals(HOLIDAY_KEY, holidayService.getHoliday(HOLIDAY_KEY).getName() );
	}

	@Test
	public void testGetNonExistingHoliday() {
		String error = null;
		Holiday holiday = null;
		try {
			holiday = holidayService.getHoliday(NONEXISTING_KEY);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertNull(holiday);
		// check error
		assertEquals("holiday not found", error);
	}

	@Test
	public void testGetHolidayNameDoesNotExist() {
		String name = "Apple";

		String error = null;
		Holiday holiday = null;
		try {
			holiday = holidayService.getHoliday(name);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertNull(holiday);
		// check error
		assertEquals("holiday not found", error);
	}
	
	@Test
	public void testUpdateHolidayNameDoesNotExist() {
		String name = "PineApple";
		Calendar c = Calendar.getInstance();
		c.set(2022, Calendar.MARCH, 16, 0, 0, 0);
		Date newStartDate = new Date(c.getTimeInMillis());
		
		Calendar c1 = Calendar.getInstance();
		c1.set(2022, Calendar.MARCH, 20, 0, 0, 0);
		Date newEndDate = new Date(c1.getTimeInMillis());

		String error = null;
		Holiday holiday = null;
		try {
			holiday = holidayService.updateHolidayDates(name, newStartDate, newEndDate);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertNull(holiday);
		// check error
		assertEquals("holiday not found", error);
	}
	
	@Test
	public void testDeleteHolidayNameDoesNotExist() {
		String name = "Wrong";

		String error = null;
		Holiday holiday = null;
		try {
			holiday = holidayService.deleteHoliday(name);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertNull(holiday);
		// check error
		assertEquals("holiday not found", error);
	}
	@Test
	public void testUpdateHoliday() {
		Holiday holiday = null;

		Calendar c = Calendar.getInstance();
		c.set(2021, Calendar.DECEMBER, 22, 0, 0, 0);
		Date newStartDate = new Date(c.getTimeInMillis());
		
		Calendar c1 = Calendar.getInstance();
		c1.set(2021, Calendar.DECEMBER, 26, 0, 0, 0);
		Date newEndDate = new Date(c1.getTimeInMillis());
		try {
			holiday = holidayService.updateHolidayDates(HOLIDAY_KEY, newStartDate, newEndDate);
		} catch (IllegalArgumentException e) {
			fail();
		}
		assertNotNull(holiday);
		assertEquals(HOLIDAY_KEY,holiday.getName());
		assertEquals(newStartDate.toString(),holiday.getStartDate().toString());
		assertEquals(newEndDate.toString(),holiday.getEndDate().toString());
	}
	@Test
	public void testUpdateHolidayEndDateBeforeStartDate() {
		Holiday holiday = null;
		String error = null;

		Calendar c = Calendar.getInstance();
		c.set(2021, Calendar.DECEMBER, 26, 0, 0, 0);
		Date newStartDate = new Date(c.getTimeInMillis());
		
		Calendar c1 = Calendar.getInstance();
		c1.set(2021, Calendar.DECEMBER, 22, 0, 0, 0);
		Date newEndDate = new Date(c1.getTimeInMillis());
		try {
			holiday = holidayService.updateHolidayDates(HOLIDAY_KEY, newStartDate, newEndDate);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertNull(holiday);
		assertEquals("start date can't be after end date", error);
	}
	
	@Test
	public void testDeleteHoliday() {

		Holiday holiday = null;
		try {
			holiday = holidayService.deleteHoliday(HOLIDAY_KEY);
		} catch (IllegalArgumentException e) {
			fail();
		}
		assertNotNull(holiday);
		// check error
		assertEquals(HOLIDAY_KEY, holiday.getName());
	}

}
