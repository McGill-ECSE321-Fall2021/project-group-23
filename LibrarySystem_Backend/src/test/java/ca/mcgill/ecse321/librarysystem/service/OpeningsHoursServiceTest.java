package ca.mcgill.ecse321.librarysystem.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.lenient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;

import ca.mcgill.ecse321.librarysystem.dao.OpeningsHoursRepository;
import ca.mcgill.ecse321.librarysystem.model.OpeningsHours;
import ca.mcgill.ecse321.librarysystem.model.OpeningsHours.DayOfWeek;

import java.sql.Time;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;


@ExtendWith(MockitoExtension.class)
public class OpeningsHoursServiceTest {

	@Mock
	private OpeningsHoursRepository openingsHoursRepository;

	@InjectMocks
	private OpeningsHoursService openingsHoursService;

	private static final DayOfWeek OPENINGSHOURS_KEY = DayOfWeek.Monday;//existing key
	private static final DayOfWeek NONEXISTING_KEY = DayOfWeek.Saturday;//non existing key

	@BeforeEach
	public void setMockOutput() {//set mock output
		lenient().when(openingsHoursRepository.findOpeningsHoursByOpeningDay(any(DayOfWeek.class))).thenAnswer((InvocationOnMock invocation) -> {
			if (invocation.getArgument(0).equals(OPENINGSHOURS_KEY)) {
				OpeningsHours openingsHours = new OpeningsHours();
				openingsHours.setOpeningDay(OPENINGSHOURS_KEY);
				return openingsHours;
			} else {
				return null;
			}
		});
		lenient().when(openingsHoursRepository.findAll()).thenAnswer((InvocationOnMock invocation) -> {
			OpeningsHours openingsHours = new OpeningsHours();
			openingsHours.setOpeningDay(OPENINGSHOURS_KEY);
			ArrayList<OpeningsHours> list = new ArrayList<OpeningsHours>();
			list.add(openingsHours);
			return list;
		});
		// Whenever anything is saved, just return the parameter object
		Answer<?> returnParameterAsAnswer = (InvocationOnMock invocation) -> {
			return invocation.getArgument(0);
		};
		lenient().when(openingsHoursRepository.save(any(OpeningsHours.class))).thenAnswer(returnParameterAsAnswer);
	}

	@Test
	public void testCreateOpeningsHours() {//test for create opening Hours
		OpeningsHours openingsHours = null;
		
		DayOfWeek day = DayOfWeek.Tuesday;//set day to Tuesday
		LocalTime startTime = LocalTime.parse("09:00");//set start time which will be converted to Time
		LocalTime endTime = LocalTime.parse("16:00");//set end time which will be converted to Time
		try {
			openingsHours = openingsHoursService.createOpeningsHours(day, Time.valueOf(startTime), Time.valueOf(endTime));
		} catch (IllegalArgumentException e) {
			fail();
		}
		assertNotNull(openingsHours);
		assertEquals(day,openingsHours.getOpeningDay());
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");//change the formatting to pass the test
		assertEquals(startTime.format(formatter).toString(), openingsHours.getStartTime().toString());
		assertEquals(endTime.format(formatter).toString(), openingsHours.getEndTime().toString());
	}
	@Test
	public void testCreateOpeningsHoursOnDayAlreadyExist() {
		OpeningsHours openingsHours = null;
		String error = null;
		
		DayOfWeek day = DayOfWeek.Monday;//set day to Tuesday
		LocalTime startTime = LocalTime.parse("09:00");//set start time which will be converted to Time
		LocalTime endTime = LocalTime.parse("16:00");//set end time which will be converted to Time
		try {
			openingsHours = openingsHoursService.createOpeningsHours(day, Time.valueOf(startTime), Time.valueOf(endTime));
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertNull(openingsHours);//should be null
		assertEquals("There are already opening hours for this day (Try updating).", error);
	}

	@Test
	public void testCreateOpeningsHoursNull() {//create opening hours with everything as null
		OpeningsHours openingsHours = null;
		String error = null;
		
		DayOfWeek day = null;
		Time startTime = null;
		Time endTime = null;
		try {
			openingsHours = openingsHoursService.createOpeningsHours(day, startTime, endTime);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertNull(openingsHours);//should be null
		assertEquals("Day of week name cannot be empty! Start Time cannot be empty! End Time cannot be empty!", error);
	}
	@Test
	public void testCreateOpeningsHoursEmpty() {
		OpeningsHours openingsHours = null;
		String error = null;
		
		DayOfWeek day = null;
		LocalTime startTime = LocalTime.parse("09:00");
		LocalTime endTime = LocalTime.parse("16:00");
		try {
			openingsHours = openingsHoursService.createOpeningsHours(day, Time.valueOf(startTime), Time.valueOf(endTime));
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertNull(openingsHours);
		assertEquals("Day of week name cannot be empty!", error);
	}
	
	@Test
	public void testCreateOpeningsHoursStartTimeEmpty() {
		OpeningsHours openingsHours = null;
		String error = null;
		
		DayOfWeek day = DayOfWeek.Friday;
		Time startTime = null;
		LocalTime endTime = LocalTime.parse("16:00");
		try {
			openingsHours = openingsHoursService.createOpeningsHours(day, startTime, Time.valueOf(endTime));
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertNull(openingsHours);
		assertEquals("Start Time cannot be empty!", error);
	}
	@Test
	public void testCreateOpeningsHoursEndTimeEmpty() {
		OpeningsHours openingsHours = null;
		String error = null;
		
		DayOfWeek day = DayOfWeek.Saturday;
		LocalTime startTime = LocalTime.parse("09:00");
		Time endTime = null;
		try {
			openingsHours = openingsHoursService.createOpeningsHours(day, Time.valueOf(startTime), endTime);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertNull(openingsHours);
		assertEquals("End Time cannot be empty!", error);
	}

	@Test
	public void testCreateOpeningsHoursEndTimeBeforeStartTime() {
		DayOfWeek day = DayOfWeek.Monday;
		LocalTime startTime = LocalTime.parse("19:00");
		LocalTime endTime = LocalTime.parse("16:00");

		String error = null;
		OpeningsHours openingsHours = null;
		try {
			openingsHours = openingsHoursService.createOpeningsHours(day,Time.valueOf(startTime), Time.valueOf(endTime));
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertNull(openingsHours);
		// check error
		assertEquals("Start time can't be after end time", error);
	}


	@Test
	public void testGetExistingOpeningsHours() {
		assertEquals(OPENINGSHOURS_KEY, openingsHoursService.getOpeningsHours(OPENINGSHOURS_KEY).getOpeningDay());
	}

	@Test
	public void testGetNonExistingOpeningsHours() {
		String error = null;
		OpeningsHours openingsHours = null;
		try {
			openingsHours = openingsHoursService.getOpeningsHours(NONEXISTING_KEY);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertNull(openingsHours);
		assertEquals("Opening Hours not found", error);
	}
	
	@Test
	public void testGetOpeningsHoursNameDoesNotExist() {
		DayOfWeek day = DayOfWeek.Sunday;

		String error = null;
		OpeningsHours openingsHours = null;
		try {
			openingsHours = openingsHoursService.getOpeningsHours(day);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertNull(openingsHours);
		// check error
		assertEquals("Opening Hours not found", error);
	}
	
	@Test
	public void testUpdateOpeningsHoursNameDoesNotExist() {
		DayOfWeek day = DayOfWeek.Sunday;
		LocalTime newStartTime = LocalTime.parse("16:00");
		LocalTime newEndTime = LocalTime.parse("19:00");

		String error = null;
		OpeningsHours openingsHours = null;
		try {
			openingsHours = openingsHoursService.updateOpeningsHours(day, Time.valueOf(newStartTime), Time.valueOf(newEndTime));
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertNull(openingsHours);
		// check error
		assertEquals("Openings hours not found", error);
	}
	@Test
	public void testUpdateOpeningsHours() {

		LocalTime newStartTime = LocalTime.parse("16:00");
		LocalTime newEndTime = LocalTime.parse("19:00");
		
		OpeningsHours openingsHours = null;
		try {
			openingsHours = openingsHoursService.updateOpeningsHours(OPENINGSHOURS_KEY, Time.valueOf(newStartTime), Time.valueOf(newEndTime));
		} catch (IllegalArgumentException e) {
			fail();
		}
		assertNotNull(openingsHours);
		assertEquals(OPENINGSHOURS_KEY,openingsHours.getOpeningDay());
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
		assertEquals(newStartTime.format(formatter).toString(), openingsHours.getStartTime().toString());
		assertEquals(newEndTime.format(formatter).toString(), openingsHours.getEndTime().toString());
	}
	@Test
	public void testUpdateOpeningsHoursEndTimeBeforeStarttime() {
		String error = null;
		LocalTime newStartTime = LocalTime.parse("19:00");
		LocalTime newEndTime = LocalTime.parse("16:00");
		
		OpeningsHours openingsHours = null;
		try {
			openingsHours = openingsHoursService.updateOpeningsHours(OPENINGSHOURS_KEY, Time.valueOf(newStartTime), Time.valueOf(newEndTime));
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertNull(openingsHours);
		assertEquals("start time can't be after end time", error);
	}
	
	@Test
	public void testDeleteOpeningsHoursNameDoesNotExist() {
		DayOfWeek day = DayOfWeek.Sunday;

		String error = null;
		OpeningsHours openingsHours = null;
		try {
			openingsHours = openingsHoursService.deleteOpeningsHours(day);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertNull(openingsHours);
		// check error
		assertEquals("Opening Hours not found", error);
	}
	
	@Test
	public void testDeleteOpeningsHours() {

		OpeningsHours openingsHours = null;
		try {
			openingsHours = openingsHoursService.deleteOpeningsHours(OPENINGSHOURS_KEY);
		} catch (IllegalArgumentException e) {
			fail();
		}
		assertNotNull(openingsHours);
		assertEquals(OPENINGSHOURS_KEY,openingsHours.getOpeningDay());
	}
}

