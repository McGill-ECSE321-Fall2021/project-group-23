package ca.mcgill.ecse321.librarysystem.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.lenient;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;

import ca.mcgill.ecse321.librarysystem.dao.OpeningsHoursRepository;
import ca.mcgill.ecse321.librarysystem.dao.ShiftRepository;
import ca.mcgill.ecse321.librarysystem.model.OpeningsHours;
import ca.mcgill.ecse321.librarysystem.model.Shift;
import ca.mcgill.ecse321.librarysystem.model.Shift.DayOfWeek;

@ExtendWith(MockitoExtension.class)
public class ShiftServiceTest {
	@Mock
	private ShiftRepository shiftRepository;
	@Mock
	private OpeningsHoursRepository openingsHoursRepository;
	
	@InjectMocks
	private ShiftService shiftService;
	
	private static final int SHIFTID = 7;
	private static final String DAYOFWEEK = "Monday";
	private static final String STARTTIME = "10:00:00";
	private static final String ENDTIME = "15:00:00";
	
	@BeforeEach
	public void setMockOutput() {
		lenient().when(shiftRepository.findByShiftId(anyInt())).thenAnswer((InvocationOnMock invocation) -> {
			if (invocation.getArgument(0).equals(SHIFTID)) {
				Shift shift = new Shift();
				shift.setShiftId(SHIFTID);
				shift.setWorkingDay(DayOfWeek.valueOf(DAYOFWEEK));
				shift.setStartTime(Time.valueOf(STARTTIME));
				shift.setEndTime(Time.valueOf(ENDTIME));
				return shift;
			} else {
				return null;
			}
		});
		
		lenient().when(shiftRepository.findByWorkingDayAndStartTimeAndEndTime(any(DayOfWeek.class), any(Time.class), any(Time.class))).thenAnswer((InvocationOnMock invocation) -> {	
			if (invocation.getArgument(0).equals(DayOfWeek.valueOf(DAYOFWEEK)) && 
					invocation.getArgument(1).equals(Time.valueOf(STARTTIME)) && invocation.getArgument(2).equals(Time.valueOf(ENDTIME))) {
				Shift shift = new Shift();
				shift.setShiftId(SHIFTID);
				shift.setWorkingDay(DayOfWeek.valueOf(DAYOFWEEK));
				shift.setStartTime(Time.valueOf(STARTTIME));
				shift.setEndTime(Time.valueOf(ENDTIME));
				return shift;
			} else {
				return null;
			}
		});
		
		lenient().when(shiftRepository.findAll()).thenAnswer((InvocationOnMock invocation) -> {
			Shift shift = new Shift();
			shift.setShiftId(SHIFTID);
			shift.setWorkingDay(DayOfWeek.valueOf(DAYOFWEEK));
			shift.setStartTime(Time.valueOf(STARTTIME));
			shift.setEndTime(Time.valueOf(ENDTIME));
			ArrayList<Shift> shifts = new ArrayList<>();
			shifts.add(shift);
			return shifts;
		});
		
		// When service methods need to check openings hours for conflict, create a set to avoid error
		lenient().when(openingsHoursRepository.findAll()).thenAnswer((InvocationOnMock invocation) -> {
			ArrayList<OpeningsHours> ohs = new ArrayList<OpeningsHours>();
			OpeningsHours oh1 = new OpeningsHours();
			oh1.setOpeningDay(OpeningsHours.DayOfWeek.valueOf(DAYOFWEEK));
			oh1.setStartTime(Time.valueOf(STARTTIME));
			oh1.setEndTime(Time.valueOf(ENDTIME));
			ohs.add(oh1);
			
			OpeningsHours oh2 = new OpeningsHours();
			oh2.setOpeningDay(OpeningsHours.DayOfWeek.valueOf("Tuesday"));
			oh2.setStartTime(Time.valueOf("12:00:00"));
			oh2.setEndTime(Time.valueOf("16:00:00"));
			ohs.add(oh2);
			return ohs;
		});
		
		// Whenever anything is saved, just return the parameter object
		Answer<?> returnParameterAsAnswer = (InvocationOnMock invocation) -> {
			return invocation.getArgument(0);
		};
		lenient().when(shiftRepository.save(any(Shift.class))).thenAnswer(returnParameterAsAnswer);
		lenient().when(openingsHoursRepository.save(any(OpeningsHours.class))).thenAnswer(returnParameterAsAnswer);
	}
	
	@Test
	public void testCreateShift() {
		Shift shift = null;
	
		try {
			shift = shiftService.createShift(DayOfWeek.valueOf(DAYOFWEEK), Time.valueOf(STARTTIME), Time.valueOf(ENDTIME));
		} catch (IllegalArgumentException e) {
			fail();
		}
		
		// verify the shift was created (by being returned)
		assertNotNull(shift);
		assertEquals(shift.getWorkingDay(), DayOfWeek.valueOf(DAYOFWEEK));
		assertEquals(shift.getStartTime(), Time.valueOf(STARTTIME));
		assertEquals(shift.getEndTime(), Time.valueOf(ENDTIME));
	}
	
	// Test for create shift null input; returns an error message if working
	@Test
	public void testCreateShiftNullDay() {
		Shift shift = null;
		String error = null;
		try {
			shift = shiftService.createShift(null, Time.valueOf(STARTTIME), Time.valueOf(ENDTIME));
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertNull(shift);
		assertEquals("No day of week provided. Please select a day of week.", error);
	}
	
	@Test
	public void testCreateShiftNullStartT() {
		Shift shift = null;
		String error = null;
		try {
			shift = shiftService.createShift(DayOfWeek.valueOf(DAYOFWEEK), null, Time.valueOf(ENDTIME));
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertNull(shift);
		assertEquals("No start time provided. Please select a start time.", error);
	}
	
	@Test
	public void testCreateShiftNullEndT() {
		Shift shift = null;
		String error = null;
		try {
			shift = shiftService.createShift(DayOfWeek.valueOf(DAYOFWEEK), Time.valueOf(STARTTIME), null);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertNull(shift);
		assertEquals("No end time provided. Please select an end time.", error);
	}
	
	// Test for checking if the start time is after end time
	@Test
	public void testCreateShiftStartAfterEnd() {
		Shift shift = null;
		String error = null;
		try {
			shift = shiftService.createShift(DayOfWeek.valueOf(DAYOFWEEK), Time.valueOf("10:00:00"), Time.valueOf("09:00:00"));
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertNull(shift);
		assertEquals("Start time cannot be after end time.", error);
	}
	
	// Tests for checking if start times and end times input are outside of the opening hours
	@Test
	public void testCreateShiftStartOutsideHours() {
		Shift shift = null;
		String error = null;
		
		try {
			shift = shiftService.createShift(DayOfWeek.valueOf(DAYOFWEEK), Time.valueOf("06:00:00"), Time.valueOf(ENDTIME));
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertNull(shift);
		assertEquals("Start time cannot be outside of the library's opening hours.", error);
	}
	
	@Test
	public void testCreateShiftEndOutsideHours() {
		Shift shift = null;
		String error = null;
		
		try {
			shift = shiftService.createShift(DayOfWeek.valueOf(DAYOFWEEK), Time.valueOf(STARTTIME), Time.valueOf("16:00:00"));
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertNull(shift);
		assertEquals("End time cannot be outside of the library's opening hours.", error);
	}
	
	// Test for checking if updating the shift is successful
	@Test
	public void testUpdateShift() {
		Shift shiftU = null;
		lenient().when(shiftRepository.existsByShiftId(anyInt())).thenReturn(true);
		try {
			shiftU = shiftService.updateShift(SHIFTID, DayOfWeek.valueOf("Tuesday"), Time.valueOf("12:00:00"), Time.valueOf("16:00:00"));
		} catch (IllegalArgumentException e) {
			fail();
		}
		assertNotNull(shiftU);
		assertEquals(shiftU.getWorkingDay(), DayOfWeek.valueOf("Tuesday"));
		assertEquals(shiftU.getStartTime(), Time.valueOf("12:00:00"));
		assertEquals(shiftU.getEndTime(), Time.valueOf("16:00:00"));
	}
	
	@Test
	public void testUpdateShiftNotFound() {
		Shift shiftU = null;
		String error = null;
		try {
			shiftU = shiftService.updateShift(SHIFTID, DayOfWeek.valueOf("Tuesday"), Time.valueOf("12:00:00"), Time.valueOf("16:00:00"));
		} catch (IllegalArgumentException e) {
			error = e.getMessage();	
		}
		assertNull(shiftU);
		assertEquals("Shift to update could not be found.", error);
	}
	
	// Test to check if shift is successfully deleted (returns it if no errors)
	@Test
	public void testDeleteShift() {
		Shift shiftD = null;
		lenient().when(shiftRepository.existsByShiftId(anyInt())).thenReturn(true);
		try {
			shiftD = shiftService.deleteShift(SHIFTID);
		} catch (IllegalArgumentException e) {
			fail();
		}
		assertNotNull(shiftD);
		assertEquals(shiftD.getShiftId(), SHIFTID);
		assertEquals(shiftD.getWorkingDay(), DayOfWeek.valueOf(DAYOFWEEK));
		assertEquals(shiftD.getStartTime(), Time.valueOf(STARTTIME));
		assertEquals(shiftD.getEndTime(), Time.valueOf(ENDTIME));
	}
	
	@Test
	public void testDeleteShiftNotFound() {
		Shift shiftD = null;
		String error = null;
		try {
			shiftD = shiftService.deleteShift(SHIFTID);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();	
		}
		assertNull(shiftD);
		assertEquals("Shift to delete could not be found.", error);
	}
	
	@Test
	public void testGetShiftDayAndTimes() {
		// Shift created so that the find method returns it 
		Shift s = null;
		
		// added to pass existence check
		lenient().when(shiftRepository.existsByWorkingDayAndStartTimeAndEndTime(any(DayOfWeek.class), 
				any(Time.class), 
				any(Time.class))).thenReturn(true);
		try {
			s = shiftService.getShift(DayOfWeek.valueOf(DAYOFWEEK), Time.valueOf(STARTTIME), Time.valueOf(ENDTIME));
		} catch (IllegalArgumentException e) {
			fail();
		}
		assertNotNull(s);
		assertEquals(s.getShiftId(), SHIFTID);
		assertEquals(s.getWorkingDay(), DayOfWeek.valueOf(DAYOFWEEK));
		assertEquals(s.getStartTime(), Time.valueOf(STARTTIME));
		assertEquals(s.getEndTime(), Time.valueOf(ENDTIME));
	}
	
	@Test
	public void testGetShiftDayAndTimesNotFound() {
		Shift s = null;
		String error = null;
		try {
			s = shiftService.getShift(DayOfWeek.valueOf(DAYOFWEEK), Time.valueOf(STARTTIME), Time.valueOf(ENDTIME));
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		
		// Check that no shift was found/created and the error is correct
		assertNull(s);
		assertEquals("Shift could not be found with provided inputs", error);
	}
	
	// Test for get a list of all shifts in repository
	@Test
	public void testGetAllShifts() {
		List<Shift> shifts = shiftService.getAllShifts();
		assertEquals(shifts.get(0).getShiftId(), SHIFTID);
	}
	
}
