package ca.mcgill.ecse321.librarysystem.service;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.lenient;

import java.util.ArrayList;
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

import ca.mcgill.ecse321.librarysystem.dao.LibrarianRepository;
import ca.mcgill.ecse321.librarysystem.dao.WeeklyScheduleRepository;
import ca.mcgill.ecse321.librarysystem.model.HeadLibrarian;
import ca.mcgill.ecse321.librarysystem.model.Librarian;
import ca.mcgill.ecse321.librarysystem.model.WeeklySchedule;

@ExtendWith(MockitoExtension.class)
public class HeadLibrarianServiceTest {
	@Mock
	private LibrarianRepository librarianRepository;
	@Mock
	private WeeklyScheduleRepository weeklyScheduleRepository;
	
	@InjectMocks
	private HeadLibrarianService headLibrarianService;
	
	// HeadLibrarian constants
	private static final int ACCOUNTID = 77;
	private static final int NONACCOUNTID = 135;
	
	private static final String FIRSTNAME = "Abiola";
	private static final String LASTNAME = "Olaniyan";
	private static final String PASSWORD = "bestpassword123";
	
	private static final String NEWFNAME = "Aloiba";
	private static final String NEWLNAME = "Nayinalo";
	private static final String NEWPWORD = "worstpassword456";
	
	// Schedule constants
	private static final int SCHEDULEID = 12;
	private static final int NONSCHEDULEID = 1;

	
	@BeforeEach
	public void setMockOutput() {
		lenient().when(librarianRepository.findByAccountId(anyInt())).thenAnswer((InvocationOnMock invocation) -> {
			HeadLibrarian hl = new HeadLibrarian();
			hl.setAccountId(ACCOUNTID);
			hl.setFirstName(FIRSTNAME);
			hl.setLastName(LASTNAME);
			hl.setPassword(PASSWORD);
			WeeklySchedule ws = new WeeklySchedule();
			ws.setWeeklyScheduleId(SCHEDULEID);
			hl.setLibrarianSchedule(ws);
			return hl;
		});
		
		lenient().when(librarianRepository.findByFirstNameAndLastName(anyString(), anyString())).thenAnswer((InvocationOnMock invocation) -> {
			Librarian l = new Librarian();
			l.setAccountId(NONACCOUNTID);
			l.setFirstName(invocation.getArgument(0));
			l.setLastName(invocation.getArgument(1));
			l.setPassword(NEWPWORD);
			WeeklySchedule ws = new WeeklySchedule();
			ws.setWeeklyScheduleId(NONSCHEDULEID);
			l.setLibrarianSchedule(ws);
			return l;
		});
		
		lenient().when(librarianRepository.findByLibrarianSchedule(any(WeeklySchedule.class))).thenAnswer((InvocationOnMock invocation) -> {
			Librarian l = new Librarian();
			l.setAccountId(NONACCOUNTID);
			l.setFirstName(NEWFNAME);
			l.setLastName(NEWLNAME);
			l.setPassword(NEWPWORD);
			WeeklySchedule ws = invocation.getArgument(0);
			l.setLibrarianSchedule(ws);
			return l;
		});
		
		lenient().when(weeklyScheduleRepository.findByWeeklyScheduleId(anyInt())).thenAnswer((InvocationOnMock invocation) -> {
			WeeklySchedule ws = new WeeklySchedule();
			ws.setWeeklyScheduleId(invocation.getArgument(0));
			return ws;
		});
		
		lenient().when(librarianRepository.findAll()).thenAnswer((InvocationOnMock invocation) -> {
			Librarian l1 = new Librarian();
			Librarian l2 = new Librarian();
			ArrayList<Librarian> librarians = new ArrayList<Librarian>();
			librarians.add(l1);
			librarians.add(l2);
			return librarians;
		});
		
		// Whenever anything is saved, just return the parameter object
		Answer<?> returnParameterAsAnswer = (InvocationOnMock invocation) -> {
			return invocation.getArgument(0);
		};
		lenient().when(librarianRepository.save(any(Librarian.class))).thenAnswer(returnParameterAsAnswer);
		lenient().when(weeklyScheduleRepository.save(any(WeeklySchedule.class))).thenAnswer(returnParameterAsAnswer);
	}
	
	// Test for creating the head librarian
	@Test
	public void testCreateHeadLibrarian() {
		HeadLibrarian hl = null;
		lenient().when(weeklyScheduleRepository.existsByWeeklyScheduleId(anyInt())).thenReturn(true);
		try {
			hl = headLibrarianService.createHeadLibrarian(FIRSTNAME, LASTNAME, PASSWORD, SCHEDULEID);
		} catch (InvalidInputException e) {
			fail();
		}
		assertNotNull(hl);
		assertEquals(hl.getFirstName(), FIRSTNAME);
		assertEquals(hl.getLastName(), LASTNAME);
		assertEquals(hl.getPassword(), PASSWORD);
		assertEquals(hl.getLibrarianSchedule().getWeeklyScheduleId(), SCHEDULEID);
	}
	
	// Test for creating head but first name is null or empty
	@Test
	public void testCreateHeadLibrarianButFirstNameNull() {
		HeadLibrarian hl = null;
		String error = null;
		try {
			hl = headLibrarianService.createHeadLibrarian(null, LASTNAME, PASSWORD, SCHEDULEID);
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		assertNull(hl);
		assertEquals("First name cannot be empty.", error);
	}
	
	// Test for creating head but last name is null or empty
	@Test
	public void testCreateHeadLibrarianButLastNameNull() {
		HeadLibrarian hl = null;
		String error = null;
		try {
			hl = headLibrarianService.createHeadLibrarian(FIRSTNAME, null, PASSWORD, SCHEDULEID);
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		assertNull(hl);
		assertEquals("Last name cannot be empty.", error);
	}
	
	// Test for creating head but password is null or empty
	@Test
	public void testCreateHeadLibrarianButPasswordNull() {
		HeadLibrarian hl = null;
		String error = null;
		try {
			hl = headLibrarianService.createHeadLibrarian(FIRSTNAME, LASTNAME, null, SCHEDULEID);
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		assertNull(hl);
		assertEquals("Password cannot be empty.", error);
	}
	
	// Test for creating head but schedule id is not found
	@Test
	public void testCreateHeadLibrarianButScheduleNotFound() {
		HeadLibrarian hl = null;
		String error = null;
		try {
			hl = headLibrarianService.createHeadLibrarian(FIRSTNAME, LASTNAME, PASSWORD, NONSCHEDULEID);
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		assertNull(hl);
		assertEquals("Weekly schedule with provided id could not be found in the database", error);
	}
	
	// Test for updating head
	@Test
	public void testUpdateHeadLibrarian() {
		HeadLibrarian hl = null;
		// Pass through checks verifying if librarian and schedule exist
		lenient().when(weeklyScheduleRepository.existsByWeeklyScheduleId(anyInt())).thenReturn(true);
		lenient().when(librarianRepository.existsById(anyInt())).thenReturn(true);
		try {
			hl = headLibrarianService.updateHeadLibrarian(ACCOUNTID, NEWFNAME, NEWLNAME, NEWPWORD, NONSCHEDULEID);
		} catch (InvalidInputException e) {
			fail();
		}
		assertNotNull(hl);
		assertEquals(hl.getFirstName(), NEWFNAME);
		assertEquals(hl.getLastName(), NEWLNAME);
		assertEquals(hl.getPassword(), NEWPWORD);
		assertEquals(hl.getLibrarianSchedule().getWeeklyScheduleId(), NONSCHEDULEID);
	}
	
	// Test for updating head but does not exist yet
	@Test
	public void testUpdateHeadLibrarianButNotExist() {
		HeadLibrarian hl = null;
		String error = null;
		lenient().when(weeklyScheduleRepository.existsByWeeklyScheduleId(anyInt())).thenReturn(true);
		try {
			hl = headLibrarianService.updateHeadLibrarian(ACCOUNTID, NEWFNAME, NEWLNAME, NEWPWORD, NONSCHEDULEID);
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		assertNull(hl);
		assertEquals("Head librarian with provided id could not be found in the database", error);
	}
	
	// Test for creating librarian
	@Test
	public void testCreateLibrarian() {
		Librarian l = null;
		lenient().when(weeklyScheduleRepository.existsByWeeklyScheduleId(anyInt())).thenReturn(true);
		try {
			l = headLibrarianService.createLibrarian(FIRSTNAME, LASTNAME, PASSWORD, SCHEDULEID);
		} catch (InvalidInputException e) {
			fail();
		}
		assertNotNull(l);
		assertEquals(l.getFirstName(), FIRSTNAME);
		assertEquals(l.getLastName(), LASTNAME);
		assertEquals(l.getPassword(), PASSWORD);
		assertEquals(l.getLibrarianSchedule().getWeeklyScheduleId(), SCHEDULEID);
	}
	
	// Test for changing librarian schedule
	@Test
	public void testAssignWeeklySchedule() {
		Librarian l = null;
		// Both schedule and librarian were found
		lenient().when(weeklyScheduleRepository.existsByWeeklyScheduleId(anyInt())).thenReturn(true);
		lenient().when(librarianRepository.existsByAccountId(anyInt())).thenReturn(true);
		try {
			l = headLibrarianService.assignWeeklySchedule(ACCOUNTID, NONSCHEDULEID);
		} catch (InvalidInputException e) {
			fail();
		}
		assertNotNull(l);
		assertEquals(l.getLibrarianSchedule().getWeeklyScheduleId(), NONSCHEDULEID);
	}
	
	// Test for changing librarian schedule but librarian is not found
	@Test
	public void testAssignWeeklyScheduleLibrarianNotFound() {
		Librarian l = null;
		String error = null;
		lenient().when(weeklyScheduleRepository.existsByWeeklyScheduleId(anyInt())).thenReturn(true);
		try {
			l = headLibrarianService.assignWeeklySchedule(NONACCOUNTID, NONSCHEDULEID);
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		assertNull(l);
		assertEquals("Librarian with provided account id could not found.", error);
	}
	
	// Test for deleting librarian
	@Test
	public void testDeleteLibrarian() {
		Librarian l = null;
		lenient().when(librarianRepository.existsByAccountId(anyInt())).thenReturn(true);
		try {
			l = headLibrarianService.deleteLibrarian(ACCOUNTID);
		} catch (InvalidInputException e) {
			fail();
		}
		assertNotNull(l);
		assertEquals(l.getAccountId(), ACCOUNTID);
	}
	
	// Test for deleting librarian but account not found
	@Test
	public void testDeleteLibrarianIdNotFound() {
		Librarian l = null;
		String error = null;
		try {
			l = headLibrarianService.deleteLibrarian(NONACCOUNTID);
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		assertNull(l);
		assertEquals("Librarian account to delete could not be found with provided id", error);
	}
	
	// Test for deleting all librarians
	@Test
	public void testDeleteAllLibrarians() {
		List<Librarian> libs = headLibrarianService.deleteAllLibrarians();
		assertEquals(libs.size(), 2);
	}
	
	// Test for getting librarian by id
	@Test
	public void testGetLibrarianByAccountId() {
		Librarian l = null;
		lenient().when(librarianRepository.existsByAccountId(anyInt())).thenReturn(true);
		l = headLibrarianService.getLibrarianByAccountId(ACCOUNTID);
		assertNotNull(l);
		assertEquals(l.getAccountId(), ACCOUNTID);
	}
	
	// Test for getting librarian by names
	@Test
	public void testGetLibrarianByNames() {
		Librarian l = null;
		lenient().when(librarianRepository.existsByFirstNameAndLastName(anyString(), anyString())).thenReturn(true);
		l = headLibrarianService.getLibrarianByNames(NEWFNAME, NEWLNAME);
		assertNotNull(l);
		assertEquals(l.getFirstName(), NEWFNAME);
		assertEquals(l.getLastName(), NEWLNAME);
	}
	
	// Test for getting librarian by names but names not found
	@Test
	public void testGetLibrarianByNamesButNotFound() {
		Librarian l = null;
		String error = null;
		try {
			l = headLibrarianService.getLibrarianByNames(FIRSTNAME, LASTNAME);
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		assertNull(l);
		assertEquals("Librarian with provided first name and last name could not be found.", error);
	}
	
	// Test for getting librarian by schedule
	@Test
	public void testGetLibrarianByScheduleId() {
		Librarian l = null;
		lenient().when(weeklyScheduleRepository.existsByWeeklyScheduleId(anyInt())).thenReturn(true);
		lenient().when(librarianRepository.existsByLibrarianSchedule(any(WeeklySchedule.class))).thenReturn(true);
		l = headLibrarianService.getLibrarianByScheduleId(NONSCHEDULEID);
		assertNotNull(l);
		assertEquals(l.getLibrarianSchedule().getWeeklyScheduleId(), NONSCHEDULEID);
	}
	
	// Test for getting librarian by schedule but not found
	@Test
	public void testGetLibrarianByScheduleIdButNotFound() {
		Librarian l = null;
		String error = null;
		lenient().when(weeklyScheduleRepository.existsByWeeklyScheduleId(anyInt())).thenReturn(true);
		try {
			l = headLibrarianService.getLibrarianByScheduleId(NONSCHEDULEID);
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		assertNull(l);
		assertEquals("Librarian with provided weekly schedule could not be found.", error);
	}
	
	// Test for getting all schedules
	@Test
	public void testGetAllLibrarians() {
		List<Librarian> librarians = null;
		librarians = headLibrarianService.getAllLibrarians();
		assertNotNull(librarians);
	}
}
