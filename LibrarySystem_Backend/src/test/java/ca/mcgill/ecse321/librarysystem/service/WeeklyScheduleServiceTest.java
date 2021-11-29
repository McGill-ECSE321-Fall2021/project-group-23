package ca.mcgill.ecse321.librarysystem.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.lenient;

import java.sql.Time;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;

import ca.mcgill.ecse321.librarysystem.dao.ShiftRepository;
import ca.mcgill.ecse321.librarysystem.dao.WeeklyScheduleRepository;
import ca.mcgill.ecse321.librarysystem.model.Shift;
import ca.mcgill.ecse321.librarysystem.model.Shift.DayOfWeek;
import ca.mcgill.ecse321.librarysystem.model.WeeklySchedule;

@ExtendWith(MockitoExtension.class)
public class WeeklyScheduleServiceTest {
	@Mock
	private WeeklyScheduleRepository weeklyScheduleRepository;
	@Mock
	private ShiftRepository shiftRepository;
	
	@InjectMocks
	private WeeklyScheduleService weeklyScheduleService;
	
	// Schedule constants
	private static final int SCHEDULEID = 12;
	private static final int NONSCHEDULEID = 1;
	
	// Shift constants
	private static final int SHIFTID1 = 7;
	private static final String DAYOFWEEK1 = "Monday";
	private static final String STARTTIME1 = "10:00:00";
	private static final String ENDTIME1 = "15:00:00";
	
	private static final int SHIFTID2 = 24;
	private static final String DAYOFWEEK2 = "Tuesday";
	private static final String STARTTIME2 = "12:00:00";
	private static final String ENDTIME2 = "16:00:00";
	
	private static final int NONSHIFTID = 30;
	
	@BeforeEach
	public void setMockOutput() {
		// When the repository looks through the list of IDs provided, it will go through here and create shifts that match
		lenient().when(shiftRepository.findByShiftId(anyInt())).thenAnswer((InvocationOnMock invocation) -> {
			if (invocation.getArgument(0).equals(SHIFTID1)) {
				Shift shift = new Shift();
				shift.setShiftId(SHIFTID1);
				shift.setWorkingDay(DayOfWeek.valueOf(DAYOFWEEK1));
				shift.setStartTime(Time.valueOf(STARTTIME1));
				shift.setEndTime(Time.valueOf(ENDTIME1));
				return shift;
			}
			else if (invocation.getArgument(0).equals(SHIFTID2)) {
				Shift shift = new Shift();
				shift.setShiftId(SHIFTID2);
				shift.setWorkingDay(DayOfWeek.valueOf(DAYOFWEEK2));
				shift.setStartTime(Time.valueOf(STARTTIME2));
				shift.setEndTime(Time.valueOf(ENDTIME2));
				return shift;
			} else {
				return null;
			}
		});
		
		lenient().when(weeklyScheduleRepository.findByWeeklyScheduleId(anyInt())).thenAnswer((InvocationOnMock invocation) -> {
			WeeklySchedule ws = new WeeklySchedule();
			HashSet<Shift> shifts = new HashSet<Shift>();
			Shift shift = new Shift();
			shift.setShiftId(SHIFTID1);
			shift.setWorkingDay(DayOfWeek.valueOf(DAYOFWEEK1));
			shift.setStartTime(Time.valueOf(STARTTIME1));
			shift.setEndTime(Time.valueOf(ENDTIME1));
			
			ws.setWeeklyScheduleId(SCHEDULEID);
			ws.setShifts(shifts);
			return ws;
		});
		
		lenient().when(weeklyScheduleRepository.findAll()).thenAnswer((InvocationOnMock invocation) -> {
			WeeklySchedule ws = new WeeklySchedule();
			ws.setWeeklyScheduleId(NONSHIFTID);
			HashSet<Shift> shifts = new HashSet<Shift>();
			Shift shift = new Shift();
			shift.setShiftId(SHIFTID1);
			shift.setWorkingDay(DayOfWeek.valueOf(DAYOFWEEK1));
			shift.setStartTime(Time.valueOf(STARTTIME1));
			shift.setEndTime(Time.valueOf(ENDTIME1));
			
			ws.setWeeklyScheduleId(SCHEDULEID);
			ws.setShifts(shifts);
			
			ArrayList<WeeklySchedule> schedules = new ArrayList<WeeklySchedule>();
			schedules.add(ws);
			return schedules;
		});
		
		// Whenever anything is saved, just return the parameter object
		Answer<?> returnParameterAsAnswer = (InvocationOnMock invocation) -> {
			return invocation.getArgument(0);
		};
		lenient().when(weeklyScheduleRepository.save(any(WeeklySchedule.class))).thenAnswer(returnParameterAsAnswer);
		lenient().when(shiftRepository.save(any(Shift.class))).thenAnswer(returnParameterAsAnswer);
	}
	
	// Test for successfully creating a schedule
	@Test
	public void testCreateWeeklySchedule() {
		WeeklySchedule ws = null;
		// Pass through without getting an error
		lenient().when(shiftRepository.existsByShiftId(anyInt())).thenReturn(true);
		
		// Create a list of shift IDs that match the mock output
		List<Integer> shiftIds = new ArrayList<Integer>();
		shiftIds.add(SHIFTID1);
		shiftIds.add(SHIFTID2);
		
		try {
			ws = weeklyScheduleService.createWeeklySchedule(shiftIds);
		} catch (InvalidInputException e) {
			fail();
		}
		assertNotNull(ws);
		assertEquals(ws.getShifts().size(), 2);
	}
	
	// Tests for failing to create a schedule
	// List provided is null
	@Test
	public void testCreateWeeklyScheduleNullIds() {
		WeeklySchedule ws = null;
		String error = null;
		try {
			ws = weeklyScheduleService.createWeeklySchedule(null);
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		assertNull(ws);
		assertEquals("List of shifts cannot be null", error);
	}
	
	// Shifts provided do not exist
	@Test
	public void testCreateWeeklyScheduleIdsNotFound() {
		WeeklySchedule ws = null;
		String error = null;
		
		// Adds shift that does not exist for test
		List<Integer> shiftIds = new ArrayList<Integer>();
		shiftIds.add(NONSHIFTID);
		try {
			ws = weeklyScheduleService.createWeeklySchedule(shiftIds);
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		assertNull(ws);
		assertEquals("Shift(s) could not be found", error);
	}
	
	// Test for updating a schedule
	@Test
	public void testUpdateWeeklySchedule() {
		// Mock that it found the id
		lenient().when(shiftRepository.existsByShiftId(anyInt())).thenReturn(true);
		lenient().when(weeklyScheduleRepository.existsByWeeklyScheduleId(anyInt())).thenReturn(true);
		WeeklySchedule ws = null;
		
		// Create a list of shift IDs that match the mock output
		List<Integer> shiftIds = new ArrayList<Integer>();
		shiftIds.add(SHIFTID1);
		shiftIds.add(SHIFTID2);
		try {
			ws = weeklyScheduleService.updateWeeklyScheduleShifts(SCHEDULEID, shiftIds);
		} catch (InvalidInputException e) {
			fail();
		}
		assertNotNull(ws);
		assertEquals(ws.getShifts().size(), 2);
	}
	
	// Test for updating a schedule but id is not found
	@Test
	public void testUpdateWeeklyScheduleIdNotFound() {
		// Mock that it found the shift id but not the schedule
		lenient().when(shiftRepository.existsByShiftId(anyInt())).thenReturn(true);
		WeeklySchedule ws = null;
		String error = null;
		
		// Create a list of shift IDs that match the mock output
		List<Integer> shiftIds = new ArrayList<Integer>();
		shiftIds.add(SHIFTID1);
		shiftIds.add(SHIFTID2);
		try {
			ws = weeklyScheduleService.updateWeeklyScheduleShifts(SCHEDULEID, shiftIds);
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		assertNull(ws);
		assertEquals("Weekly schedule with provided id does not exist", error);
	}
	
	// Test for deleting a schedule
	@Test
	public void testDeleteWeeklySchedule() {
		WeeklySchedule ws = null;
		lenient().when(weeklyScheduleRepository.existsByWeeklyScheduleId(anyInt())).thenReturn(true);
		try {
			ws = weeklyScheduleService.deleteWeeklySchedule(SCHEDULEID);
		} catch (InvalidInputException e) {
			fail();
		}
		assertNotNull(ws);
	}
	
	// Test for deleting a schedule but it's not found
	@Test
	public void testDeleteWeeklyScheduleNotFound() {
		WeeklySchedule ws = null;
		String error = null;
		try {
			ws = weeklyScheduleService.deleteWeeklySchedule(NONSCHEDULEID);
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		assertNull(ws);
		assertEquals("Weekly schedule with provided id does not exist", error);
	}
	
	// Test for deleting all schedules
	@Test
	public void testDeleteAllWeeklySchedules() {
		List<WeeklySchedule> schedules = null;
		schedules = weeklyScheduleService.deleteAllWeeklySchedules();
		assertNotNull(schedules);
	}
	
	// Test for getting a schedule by id
	@Test
	public void testGetWeeklySchedule() {
		lenient().when(weeklyScheduleRepository.existsByWeeklyScheduleId(anyInt())).thenReturn(true);
		WeeklySchedule ws = null;
		ws = weeklyScheduleService.getWeeklySchedule(SCHEDULEID);
		assertNotNull(ws);
	}
	
	// Test for getting a schedule but not found
	@Test
	public void testGetWeeklyScheduleNotFound() {
		WeeklySchedule ws = null;
		String error = null;
		try {
			ws = weeklyScheduleService.getWeeklySchedule(SCHEDULEID);
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		assertNull(ws);
		assertEquals("Weekly schedule with provided id does not exist", error);
	}
	
	// Test for getting all schedules
	@Test
	public void testGetAllWeeklySchedules() {
		List<WeeklySchedule> schedules = null;
		schedules = weeklyScheduleService.getAllWeeklySchedules();
		assertNotNull(schedules);
	}
	
}
