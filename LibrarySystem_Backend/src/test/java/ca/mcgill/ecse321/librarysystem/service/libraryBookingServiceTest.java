package ca.mcgill.ecse321.librarysystem.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.lenient;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;

import ca.mcgill.ecse321.librarysystem.dao.CustomerRepository;
import ca.mcgill.ecse321.librarysystem.dao.LibraryBookingRepository;
import ca.mcgill.ecse321.librarysystem.model.Customer;
import ca.mcgill.ecse321.librarysystem.model.LibraryBooking;

@ExtendWith(MockitoExtension.class)
public class libraryBookingServiceTest {

    @Mock
    private LibraryBookingRepository libraryBookingRepository;

    @Mock
    private CustomerRepository customerRepository;
    
    @InjectMocks
    private CustomerService customerService;

    @InjectMocks
    private LibraryBookingService libraryBookingService;

    private static final int CUSTOMER_ID = 123;
    private static final int CUSTOMER_ID2 = 23;
    private static final int LIBRARYBOOKING_ID = 456; 
    private static final int LIBRARYBOOKING_ID2 = 789;

    private static final int NONEXISTANT_LIBRARYBOOKING_ID = 654;
    private static final int NONEXISTANT_CUSTOMER_ID = 567;


    @BeforeEach
    public void setMockOutput() {

        lenient().when(libraryBookingRepository.findByCustomer(any(Customer.class))).thenAnswer( (InvocationOnMock invocation) -> {
            List<LibraryBooking> libraryBookings = new ArrayList<>();
            Customer customer = new Customer();
            customer.setAccountId(CUSTOMER_ID);
            customer.setAccountBalance(0);
            customer.setAddress("testAddress");
            customer.setEmail("testEmail");
            customer.setFirstName("testFirstname");
            customer.setLastName("testLastName");
            customer.setIsLocal(true);
            customer.setIsVerified(true);
            customer.setPassword("testPassword");
            LibraryBooking libraryBooking = new LibraryBooking();
            Calendar C = Calendar.getInstance();
            C.set(2021, Calendar.JANUARY, 01, 10, 0, 0);
            Date startDate = new Date(C.getTimeInMillis());
            Date endDate = new Date(C.getTimeInMillis());
            LocalTime startTime = LocalTime.parse("10:00");
            LocalTime endTime = LocalTime.parse("12:00");
            libraryBooking.setCustomer(customer);
            libraryBooking.setId(LIBRARYBOOKING_ID);
            libraryBooking.setStartDate(startDate);
            libraryBooking.setEndDate(endDate);
            libraryBooking.setStartTime(Time.valueOf(startTime));
            libraryBooking.setEndTime(Time.valueOf(endTime));
            libraryBookings.add(libraryBooking);
            return libraryBookings;

        });

        lenient().when(libraryBookingRepository.findAll()).thenAnswer((InvocationOnMock invocation) -> {
            List<LibraryBooking> libraryBookings = new ArrayList<LibraryBooking>();
            Customer customer = new Customer();
            customer.setAccountId(CUSTOMER_ID);
            customer.setAccountBalance(0);
            customer.setAddress("testAddress");
            customer.setEmail("testEmail");
            customer.setFirstName("testFirstname");
            customer.setLastName("testLastName");
            customer.setIsLocal(true);
            customer.setIsVerified(true);
            customer.setPassword("testPassword");
            LibraryBooking libraryBooking = new LibraryBooking();
            Calendar C = Calendar.getInstance();
            C.set(2021, Calendar.JANUARY, 01, 10, 0, 0);
            Date startDate = new Date(C.getTimeInMillis());
            Date endDate = new Date(C.getTimeInMillis());
            LocalTime startTime = LocalTime.parse("10:00");
            LocalTime endTime = LocalTime.parse("12:00");
            libraryBooking.setCustomer(customer);
            libraryBooking.setId(LIBRARYBOOKING_ID);
            libraryBooking.setStartDate(startDate);
            libraryBooking.setEndDate(endDate);
            libraryBooking.setStartTime(Time.valueOf(startTime));
            libraryBooking.setEndTime(Time.valueOf(endTime));

            Customer customer2 = new Customer();
            customer2.setAccountId(CUSTOMER_ID2);
            customer2.setAccountBalance(0);
            customer2.setAddress("testAddress2");
            customer2.setEmail("testEmail2");
            customer2.setFirstName("testFirstname2");
            customer2.setLastName("testLastName2");
            customer2.setIsLocal(true);
            customer2.setIsVerified(true);
            customer2.setPassword("testPassword2");
            Calendar C2 = Calendar.getInstance();
            C2.set(2021, Calendar.FEBRUARY, 01, 10, 0, 0);
            Date startDate2 = new Date(C2.getTimeInMillis());
            Date endDate2 = new Date(C2.getTimeInMillis());
            LocalTime startTime2 = LocalTime.parse("14:00");
            LocalTime endTime2 = LocalTime.parse("16:00");
            LibraryBooking libraryBooking2 = new LibraryBooking();
            libraryBooking2.setCustomer(customer2);
            libraryBooking2.setId(LIBRARYBOOKING_ID2);
            libraryBooking2.setStartDate(startDate2);
            libraryBooking2.setEndDate(endDate2);
            libraryBooking2.setStartTime(Time.valueOf(startTime2));
            libraryBooking2.setEndTime(Time.valueOf(endTime2));

            libraryBookings.add(libraryBooking);
            libraryBookings.add(libraryBooking2);

            return libraryBookings;
            

        });

        lenient().when(libraryBookingRepository.findById(anyInt())).thenAnswer((InvocationOnMock invocation) -> {
            if(invocation.getArgument(0).equals(LIBRARYBOOKING_ID)) {
                Customer customer = new Customer();
                customer.setAccountId(CUSTOMER_ID);
                customer.setAccountBalance(0);
                customer.setAddress("testAddress");
                customer.setEmail("testEmail");
                customer.setFirstName("testFirstname");
                customer.setLastName("testLastName");
                customer.setIsLocal(true);
                customer.setIsVerified(true);
                customer.setPassword("testPassword");
                LibraryBooking libraryBooking = new LibraryBooking();
                Calendar C = Calendar.getInstance();
                C.set(2021, Calendar.JANUARY, 01, 10, 0, 0);
                Date startDate = new Date(C.getTimeInMillis());
                Date endDate = new Date(C.getTimeInMillis());
                LocalTime startTime = LocalTime.parse("10:00");
                LocalTime endTime = LocalTime.parse("12:00");
                libraryBooking.setCustomer(customer);
                libraryBooking.setId(LIBRARYBOOKING_ID);
                libraryBooking.setStartDate(startDate);
                libraryBooking.setEndDate(endDate);
                libraryBooking.setStartTime(Time.valueOf(startTime));
                libraryBooking.setEndTime(Time.valueOf(endTime));
                return libraryBooking;
            } else {
                return null;
            }

        });

        lenient().when(customerRepository.findCustomerByAccountId(anyInt())).thenAnswer((InvocationOnMock invocation) -> {
            if(invocation.getArgument(0).equals(CUSTOMER_ID)) {
            Customer customer = new Customer();
            customer.setAccountId(CUSTOMER_ID);
            customer.setAccountBalance(0);
            customer.setAddress("testAddress");
            customer.setEmail("testEmail");
            customer.setFirstName("testFirstname");
            customer.setLastName("testLastName");
            customer.setIsLocal(true);
            customer.setIsVerified(true);
            customer.setPassword("testPassword");
            return customer;
            } else if(invocation.getArgument(0).equals(CUSTOMER_ID2)) {

            Customer customer2 = new Customer();
            customer2.setAccountId(CUSTOMER_ID2);
            customer2.setAccountBalance(0);
            customer2.setAddress("testAddress2");
            customer2.setEmail("testEmail2");
            customer2.setFirstName("testFirstname2");
            customer2.setLastName("testLastName2");
            customer2.setIsLocal(true);
            customer2.setIsVerified(true);
            customer2.setPassword("testPassword2");
            return customer2;


            }
            return null;
        });

        lenient().when(customerRepository.existsByAccountId(anyInt())).thenAnswer((InvocationOnMock invocation) -> {
            if(invocation.getArgument(0).equals(CUSTOMER_ID)) {
                Customer customer = new Customer();
                customer.setAccountId(CUSTOMER_ID);
                customer.setAccountBalance(0);
                customer.setAddress("testAddress");
                customer.setEmail("testEmail");
                customer.setFirstName("testFirstname");
                customer.setLastName("testLastName");
                customer.setIsLocal(true);
                customer.setIsVerified(true);
                customer.setPassword("testPassword");
                return true;
                } else if(invocation.getArgument(0).equals(CUSTOMER_ID2)) {
    
                Customer customer2 = new Customer();
                customer2.setAccountId(CUSTOMER_ID2);
                customer2.setAccountBalance(0);
                customer2.setAddress("testAddress2");
                customer2.setEmail("testEmail2");
                customer2.setFirstName("testFirstname2");
                customer2.setLastName("testLastName2");
                customer2.setIsLocal(true);
                customer2.setIsVerified(true);
                customer2.setPassword("testPassword2");
                return true;
    
    
                }
                return false;
        });


       Answer<?> returnParameterAsAnswer = (InvocationOnMock invocation) -> invocation.getArgument(0);
       lenient().when(libraryBookingRepository.save(any(LibraryBooking.class))).thenAnswer(returnParameterAsAnswer);
       lenient().when(customerRepository.save(any(Customer.class))).thenAnswer(returnParameterAsAnswer);

    }

    @Test
    public void createLibraryBooking(){
        LibraryBooking libraryBooking = null;
        Calendar C = Calendar.getInstance();
        C.set(2021, Calendar.MARCH, 01, 10, 0, 0);
        Date startDate = new Date(C.getTimeInMillis());
        Date endDate = new Date(C.getTimeInMillis());
        LocalTime startTime = LocalTime.parse("10:00");
        LocalTime endTime = LocalTime.parse("12:00");

        try {
            libraryBooking = libraryBookingService.createLibraryBooking(startDate, endDate, Time.valueOf(startTime), Time.valueOf(endTime), CUSTOMER_ID);
        } 
        catch(InvalidInputException e) {
            fail();
        }
        assertNotNull(libraryBooking);
        assertEquals(startDate, libraryBooking.getStartDate());
        assertEquals(endDate, libraryBooking.getEndDate());
        assertEquals(Time.valueOf(startTime), libraryBooking.getStartTime());
        assertEquals(Time.valueOf(endTime), libraryBooking.getEndTime());
        assertEquals(CUSTOMER_ID, libraryBooking.getCustomer().getAccountId());
        
    }

    @Test
    public void createLibraryBookingNullStartDate(){
        String error = "";
        LibraryBooking libraryBooking = null;
        Calendar C = Calendar.getInstance();
        C.set(2021, Calendar.JANUARY, 01, 10, 0, 0);
        Date startDate = null;
        Date endDate = new Date(C.getTimeInMillis());
        LocalTime startTime = LocalTime.parse("10:00");
        LocalTime endTime = LocalTime.parse("12:00");

        try {
            libraryBooking = libraryBookingService.createLibraryBooking(startDate, endDate, Time.valueOf(startTime), Time.valueOf(endTime), CUSTOMER_ID);
        } 
        catch(InvalidInputException e) {
            error = e.getMessage();
        }
        assertNull(libraryBooking);
        assertEquals(error, "A start date is needed to create a libraryBooking");
        
    }

    @Test
    public void createLibraryBookingNullEndDate(){
        String error = "";
        LibraryBooking libraryBooking = null;
        Calendar C = Calendar.getInstance();
        C.set(2021, Calendar.JANUARY, 01, 10, 0, 0);
        Date startDate = new Date(C.getTimeInMillis());
        Date endDate = null;
        LocalTime startTime = LocalTime.parse("10:00");
        LocalTime endTime = LocalTime.parse("12:00");

        try {
            libraryBooking = libraryBookingService.createLibraryBooking(startDate, endDate, Time.valueOf(startTime), Time.valueOf(endTime), CUSTOMER_ID);
        } 
        catch(InvalidInputException e) {
            error = e.getMessage();
        }
        assertNull(libraryBooking);
        assertEquals(error, "An end date is needed to create a libraryBooking");
        
    }

    @Test
    public void createLibraryBookingNullStartTime(){
        String error = "";
        LibraryBooking libraryBooking = null;
        Calendar C = Calendar.getInstance();
        C.set(2021, Calendar.JANUARY, 01, 10, 0, 0);
        Date startDate = new Date(C.getTimeInMillis());
        Date endDate = new Date(C.getTimeInMillis());
        LocalTime endTime = LocalTime.parse("12:00");

        try {
            libraryBooking = libraryBookingService.createLibraryBooking(startDate, endDate, null, Time.valueOf(endTime), CUSTOMER_ID);
        } 
        catch(InvalidInputException e) {
            error = e.getMessage();
        }
        assertNull(libraryBooking);
        assertEquals(error, "A start time is needed to create a LibraryBooking");
        
    }

    @Test
    public void createLibraryBookingNullEndTime(){
        String error = "";
        LibraryBooking libraryBooking = null;
        Calendar C = Calendar.getInstance();
        C.set(2021, Calendar.JANUARY, 01, 10, 0, 0);
        Date startDate = new Date(C.getTimeInMillis());
        Date endDate = new Date(C.getTimeInMillis());
        LocalTime startTime = LocalTime.parse("12:00");

        try {
            libraryBooking = libraryBookingService.createLibraryBooking(startDate, endDate, Time.valueOf(startTime), null, CUSTOMER_ID);
        } 
        catch(InvalidInputException e) {
            error = e.getMessage();
        }
        assertNull(libraryBooking);
        assertEquals(error, "An end time is needed to create a libraryBooking");
        
    }

    @Test
    public void createLibraryBookingMultipleDays(){
        String error = "";
        LibraryBooking libraryBooking = null;
        Calendar C = Calendar.getInstance();
        C.set(2021, Calendar.JANUARY, 01, 10, 0, 0);
        Calendar C2 = Calendar.getInstance();
        C2.set(2021, Calendar.JANUARY, 02, 10, 0, 0);
        Date startDate = new Date(C.getTimeInMillis());
        Date endDate = new Date(C2.getTimeInMillis());
        LocalTime startTime = LocalTime.parse("12:00");
        LocalTime endTime = LocalTime.parse("12:00");

        try {
            libraryBooking = libraryBookingService.createLibraryBooking(startDate, endDate, Time.valueOf(startTime), Time.valueOf(endTime), CUSTOMER_ID);
        } 
        catch(InvalidInputException e) {
            error = e.getMessage();
        }
        assertNull(libraryBooking);
        assertEquals(error, "Each libraryBooking cannot be for multiple days");
    }

    @Test
    public void createLibraryBookingOverlap1(){
        String error = "";
        Customer customer = new Customer();
        customer.setAccountId(CUSTOMER_ID);
        customer.setAccountBalance(0);
        customer.setAddress("testAddress");
        customer.setEmail("testEmail");
        customer.setFirstName("testFirstname");
        customer.setLastName("testLastName");
        customer.setIsLocal(true);
        customer.setIsVerified(true);
        customer.setPassword("testPassword");
        LibraryBooking libraryBooking = null;
        Calendar C = Calendar.getInstance();
        C.set(2021, Calendar.JANUARY, 01, 10, 0, 0);
        Date startDate = new Date(C.getTimeInMillis());
        Date endDate = new Date(C.getTimeInMillis());
        LocalTime startTime = LocalTime.parse("10:00");
        LocalTime endTime = LocalTime.parse("11:30");

            try {
                libraryBooking = libraryBookingService.createLibraryBooking(startDate, endDate, Time.valueOf(startTime), Time.valueOf(endTime), CUSTOMER_ID);
            } 
            catch(InvalidInputException e) {
                error = e.getMessage();
            }
            assertNull(libraryBooking);
            assertEquals(error, "This libraryBooking overlaps with an existing libraryBooking");  
    }
    @Test
    public void createLibraryBookingOverlap2(){
        String error = "";
        Customer customer = new Customer();
        customer.setAccountId(CUSTOMER_ID);
        customer.setAccountBalance(0);
        customer.setAddress("testAddress");
        customer.setEmail("testEmail");
        customer.setFirstName("testFirstname");
        customer.setLastName("testLastName");
        customer.setIsLocal(true);
        customer.setIsVerified(true);
        customer.setPassword("testPassword");
        LibraryBooking libraryBooking = null;
        Calendar C = Calendar.getInstance();
        C.set(2021, Calendar.JANUARY, 01, 10, 0, 0);
        Date startDate = new Date(C.getTimeInMillis());
        Date endDate = new Date(C.getTimeInMillis());
        LocalTime startTime = LocalTime.parse("09:00");
        LocalTime endTime = LocalTime.parse("11:30");

            try {
                libraryBooking = libraryBookingService.createLibraryBooking(startDate, endDate, Time.valueOf(startTime), Time.valueOf(endTime), CUSTOMER_ID);
            } 
            catch(InvalidInputException e) {
                error = e.getMessage();
            }
            assertNull(libraryBooking);
            assertEquals(error, "This libraryBooking overlaps with an existing libraryBooking");  
    }
    @Test
    public void createLibraryBookingOverlap3(){
        String error = "";
        Customer customer = new Customer();
        customer.setAccountId(CUSTOMER_ID);
        customer.setAccountBalance(0);
        customer.setAddress("testAddress");
        customer.setEmail("testEmail");
        customer.setFirstName("testFirstname");
        customer.setLastName("testLastName");
        customer.setIsLocal(true);
        customer.setIsVerified(true);
        customer.setPassword("testPassword");
        LibraryBooking libraryBooking = null;
        Calendar C = Calendar.getInstance();
        C.set(2021, Calendar.JANUARY, 01, 10, 0, 0);
        Date startDate = new Date(C.getTimeInMillis());
        Date endDate = new Date(C.getTimeInMillis());
        LocalTime startTime = LocalTime.parse("08:00");
        LocalTime endTime = LocalTime.parse("14:30");

            try {
                libraryBooking = libraryBookingService.createLibraryBooking(startDate, endDate, Time.valueOf(startTime), Time.valueOf(endTime), CUSTOMER_ID);
            } 
            catch(InvalidInputException e) {
                error = e.getMessage();
            }
            assertNull(libraryBooking);
            assertEquals(error, "This libraryBooking overlaps with an existing libraryBooking");  
    }
    @Test
    public void createLibraryBookingOverlap4(){
        String error = "";
        Customer customer = new Customer();
        customer.setAccountId(CUSTOMER_ID);
        customer.setAccountBalance(0);
        customer.setAddress("testAddress");
        customer.setEmail("testEmail");
        customer.setFirstName("testFirstname");
        customer.setLastName("testLastName");
        customer.setIsLocal(true);
        customer.setIsVerified(true);
        customer.setPassword("testPassword");
        LibraryBooking libraryBooking = null;
        Calendar C = Calendar.getInstance();
        C.set(2021, Calendar.JANUARY, 01, 10, 0, 0);
        Date startDate = new Date(C.getTimeInMillis());
        Date endDate = new Date(C.getTimeInMillis());
        LocalTime startTime = LocalTime.parse("11:00");
        LocalTime endTime = LocalTime.parse("11:30");

            try {
                libraryBooking = libraryBookingService.createLibraryBooking(startDate, endDate, Time.valueOf(startTime), Time.valueOf(endTime), CUSTOMER_ID);
            } 
            catch(InvalidInputException e) {
                error = e.getMessage();
            }
            assertNull(libraryBooking);
            assertEquals(error, "This libraryBooking overlaps with an existing libraryBooking");  
    }

    @Test
    public void createLibraryBookingOverlap5(){
        String error = "";
        Customer customer = new Customer();
        customer.setAccountId(CUSTOMER_ID);
        customer.setAccountBalance(0);
        customer.setAddress("testAddress");
        customer.setEmail("testEmail");
        customer.setFirstName("testFirstname");
        customer.setLastName("testLastName");
        customer.setIsLocal(true);
        customer.setIsVerified(true);
        customer.setPassword("testPassword");
        LibraryBooking libraryBooking = null;
        Calendar C = Calendar.getInstance();
        C.set(2021, Calendar.JANUARY, 01, 10, 0, 0);
        Date startDate = new Date(C.getTimeInMillis());
        Date endDate = new Date(C.getTimeInMillis());
        LocalTime startTime = LocalTime.parse("10:00");
        LocalTime endTime = LocalTime.parse("12:00");

            try {
                libraryBooking = libraryBookingService.createLibraryBooking(startDate, endDate, Time.valueOf(startTime), Time.valueOf(endTime), CUSTOMER_ID);
            } 
            catch(InvalidInputException e) {
                error = e.getMessage();
            }
            assertNull(libraryBooking);
            assertEquals(error, "This libraryBooking overlaps with an existing libraryBooking");  
    }

    @Test
    public void testDeleteLibraryBooking() {
        
        LibraryBooking libraryBooking = null;
        try {
            libraryBooking = libraryBookingService.deleteLibraryBooking(LIBRARYBOOKING_ID);
        } 
        catch(InvalidInputException e) {
            fail();
        }
        assertNotNull(libraryBooking);
        assertEquals(LIBRARYBOOKING_ID, libraryBooking.getId());
    }

    @Test 
    public void testDeleteNonExistantLibraryBooking() {
        String error = "";
        try {
            libraryBookingService.deleteLibraryBooking(NONEXISTANT_LIBRARYBOOKING_ID);
        } 
        catch(InvalidInputException e) {
            error = e.getMessage();
        }

        assertEquals(error, "libraryBooking does not exist");


    }

    @Test
    public void testDeleteAllLibraryBooking(){

        List<LibraryBooking> libraryBookings = new ArrayList<LibraryBooking>();
        libraryBookings = libraryBookingService.deleteAllLibraryBooking();
        LibraryBooking libraryBooking1 = libraryBookings.get(0);
        LibraryBooking libraryBooking2 = libraryBookings.get(1);
        assertNotNull(libraryBooking1);
        assertNotNull(libraryBooking2);
        assertEquals(LIBRARYBOOKING_ID, libraryBooking1.getId());
        assertEquals(LIBRARYBOOKING_ID2, libraryBooking2.getId());
        
    }

    @Test
    public void testGetAllLibraryBooking() {

        List<LibraryBooking> libraryBookings = new ArrayList<LibraryBooking>();
        libraryBookings = libraryBookingService.getAllLibraryBooking();
        LibraryBooking libraryBooking1 = libraryBookings.get(0);
        LibraryBooking libraryBooking2 = libraryBookings.get(1);
        assertNotNull(libraryBooking1);
        assertNotNull(libraryBooking2);
        assertEquals(LIBRARYBOOKING_ID, libraryBooking1.getId());
        assertEquals(LIBRARYBOOKING_ID2, libraryBooking2.getId());

    }

    @Test
    public void testGetLibraryBookingById() {
        LibraryBooking libraryBooking = libraryBookingService.getLibraryBookingbyId(LIBRARYBOOKING_ID);
        assertEquals(LIBRARYBOOKING_ID, libraryBooking.getId());
    }

    @Test
    public void testGetLibraryBookingByInexistantId() {
        String error = "";
        LibraryBooking libraryBooking = null;
        try {
            libraryBooking = libraryBookingService.getLibraryBookingbyId(NONEXISTANT_LIBRARYBOOKING_ID);
        } catch(InvalidInputException e) {
            error = e.getMessage();
        }
        assertNull(libraryBooking);
        assertEquals(error, "libraryBooking does not exist");
    }
    

    @Test
    public void testGetLibraryBookingByCustomer() {
        Customer customer = new Customer();
            customer.setAccountId(CUSTOMER_ID);
            customer.setAccountBalance(0);
            customer.setAddress("testAddress");
            customer.setEmail("testEmail");
            customer.setFirstName("testFirstname");
            customer.setLastName("testLastName");
            customer.setIsLocal(true);
            customer.setIsVerified(true);
            customer.setPassword("testPassword");
        LibraryBooking libraryBooking = libraryBookingService.getLibraryBookingByCustomer(customer).get(0);
        assertEquals(LIBRARYBOOKING_ID, libraryBooking.getId());

    }

    @Test
    public void testGetLibraryBookingByInexistantCustomer() {
        String error = "";
        List<LibraryBooking> libraryBooking = null;
        Customer customer = new Customer();
            customer.setAccountId(NONEXISTANT_CUSTOMER_ID);
            customer.setAccountBalance(0);
            customer.setAddress("testAddress");
            customer.setEmail("testEmail");
            customer.setFirstName("testFirstname");
            customer.setLastName("testLastName");
            customer.setIsLocal(true);
            customer.setIsVerified(true);
            customer.setPassword("testPassword");
        try {
            libraryBooking = libraryBookingService.getLibraryBookingByCustomer(customer);
        } catch(InvalidInputException e) {
            error = e.getMessage();
        }
        assertNull(libraryBooking);
        assertEquals(error, "Customer does not exist");
    }

    @Test
    public void testUpdateLibraryBookingDateAndTimeSuccess() {
        
        LibraryBooking libraryBooking = null;
        Calendar C = Calendar.getInstance();
        C.set(2021, Calendar.JANUARY, 01, 10, 0, 0);
        Date startDate = new Date(C.getTimeInMillis());
        Date endDate = new Date(C.getTimeInMillis());
        LocalTime startTime = LocalTime.parse("10:00");
        LocalTime endTime = LocalTime.parse("12:00");
        try {
            libraryBooking = libraryBookingService.updateLibraryBookingDateAndTime(LIBRARYBOOKING_ID, startDate, endDate, Time.valueOf(startTime), Time.valueOf(endTime));
        } catch(InvalidInputException e) {
            fail();
        }
        assertNotNull(libraryBooking);
        assertEquals(startDate, libraryBooking.getStartDate());
        assertEquals(endDate, libraryBooking.getEndDate());
        assertEquals(Time.valueOf(startTime), libraryBooking.getStartTime());
        assertEquals(Time.valueOf(endTime), libraryBooking.getEndTime());

    }

    @Test 
    public void testUpdateLibraryBookingDateAndTimeNullStartDate() {
        String error = "";
        LibraryBooking libraryBooking = null;
        Calendar C = Calendar.getInstance();
        C.set(2021, Calendar.JANUARY, 01, 10, 0, 0);
        Date startDate = null;
        Date endDate = new Date(C.getTimeInMillis());
        LocalTime startTime = LocalTime.parse("10:00");
        LocalTime endTime = LocalTime.parse("12:00");
        try {
            libraryBooking = libraryBookingService.updateLibraryBookingDateAndTime(LIBRARYBOOKING_ID, startDate, endDate, Time.valueOf(startTime), Time.valueOf(endTime));
        } catch(InvalidInputException e) {
            error = e.getMessage();
        }
        assertNull(libraryBooking);
        assertEquals(error, "StartDate cannot be empty");

    }

    @Test 
    public void testUpdateLibraryBookingDateAndTimeNullEndDate() {
        String error = "";
        LibraryBooking libraryBooking = null;
        Calendar C = Calendar.getInstance();
        C.set(2021, Calendar.JANUARY, 01, 10, 0, 0);
        Date startDate = new Date(C.getTimeInMillis());
        Date endDate = null;
        LocalTime startTime = LocalTime.parse("10:00");
        LocalTime endTime = LocalTime.parse("12:00");
        try {
            libraryBooking = libraryBookingService.updateLibraryBookingDateAndTime(LIBRARYBOOKING_ID, startDate, endDate, Time.valueOf(startTime), Time.valueOf(endTime));
        } catch(InvalidInputException e) {
            error = e.getMessage();
        }
        assertNull(libraryBooking);
        assertEquals(error, "EndDate cannot be empty");
        
    }

    @Test 
    public void testUpdateLibraryBookingDateAndTimeNullStartTime() {
        String error = "";
        LibraryBooking libraryBooking = null;
        Calendar C = Calendar.getInstance();
        C.set(2021, Calendar.JANUARY, 01, 10, 0, 0);
        Date startDate = new Date(C.getTimeInMillis());
        Date endDate = new Date(C.getTimeInMillis());
        LocalTime endTime = LocalTime.parse("12:00");
        try {
            libraryBooking = libraryBookingService.updateLibraryBookingDateAndTime(LIBRARYBOOKING_ID, startDate, endDate, null, Time.valueOf(endTime));
        } catch(InvalidInputException e) {
            error = e.getMessage();
        }
        assertNull(libraryBooking);
        assertEquals(error, "StartTime cannot be empty");
        
        
    }

    @Test 
    public void testUpdateLibraryBookingDateAndTimeNullEndTime() {
        String error = "";
        LibraryBooking libraryBooking = null;
        Calendar C = Calendar.getInstance();
        C.set(2021, Calendar.JANUARY, 01, 10, 0, 0);
        Date startDate = new Date(C.getTimeInMillis());
        Date endDate = new Date(C.getTimeInMillis());
        LocalTime startTime = LocalTime.parse("10:00");
        try {
            libraryBooking = libraryBookingService.updateLibraryBookingDateAndTime(LIBRARYBOOKING_ID, startDate, endDate, Time.valueOf(startTime), null);
        } catch(InvalidInputException e) {
            error = e.getMessage();
        }
        assertNull(libraryBooking);
        assertEquals(error, "EndTime cannot be empty");
        
    }

    @Test 
    public void testUpdateLibraryBookingDateAndTimeEndDateNotEqualStartDate() {
        String error = "";
        LibraryBooking libraryBooking = null;
        Calendar C = Calendar.getInstance();
        C.set(2021, Calendar.JANUARY, 01, 10, 0, 0);
        Calendar C1 = Calendar.getInstance();
        C1.set(2021, Calendar.JANUARY, 02, 10, 0, 0);
        Date startDate = new Date(C.getTimeInMillis());
        Date endDate = new Date(C1.getTimeInMillis());
        LocalTime startTime = LocalTime.parse("10:00");
        LocalTime endTime = LocalTime.parse("12:00");
        try {
            libraryBooking = libraryBookingService.updateLibraryBookingDateAndTime(LIBRARYBOOKING_ID, startDate, endDate, Time.valueOf(startTime), Time.valueOf(endTime));
        } catch(InvalidInputException e) {
            error = e.getMessage();
        }
        assertNull(libraryBooking);
        assertEquals(error, "StartDate and endDate must be equal");
        
    }

    @Test 
    public void testUpdateLibraryBookingDateAndTimeendTimeBeforeStartTime() {
        String error = "";
        LibraryBooking libraryBooking = null;
        Calendar C = Calendar.getInstance();
        C.set(2021, Calendar.JANUARY, 01, 10, 0, 0);
        Date startDate = new Date(C.getTimeInMillis());
        Date endDate = new Date(C.getTimeInMillis());
        LocalTime startTime = LocalTime.parse("10:00");
        LocalTime endTime = LocalTime.parse("09:00");
        try {
            libraryBooking = libraryBookingService.updateLibraryBookingDateAndTime(LIBRARYBOOKING_ID, startDate, endDate, Time.valueOf(startTime), Time.valueOf(endTime));
        } catch(InvalidInputException e) {
            error = e.getMessage();
        }
        assertNull(libraryBooking);
        assertEquals(error, "The end time of a libraryBooking cannot be before its start time");
        
    }

    @Test 
    public void testUpdateLibraryBookingCustomer() {

        String error = "";
        LibraryBooking libraryBooking = null;
        Customer customer = new Customer();
            customer.setAccountId(CUSTOMER_ID);
            customer.setAccountBalance(0);
            customer.setAddress("testAddress");
            customer.setEmail("testEmail");
            customer.setFirstName("testFirstname");
            customer.setLastName("testLastName");
            customer.setIsLocal(true);
            customer.setIsVerified(true);
            customer.setPassword("testPassword");
        try {
            libraryBooking = libraryBookingService.updateLibraryBookingCustomer(NONEXISTANT_LIBRARYBOOKING_ID,  customer);
        } catch(InvalidInputException e) {
            error = e.getMessage();
        }
        assertNull(libraryBooking);
        assertEquals(error, "librayBooking does not exist");
        
    }

    @Test 
    public void testUpdateLibraryBookingCustomerNonExistantLibraryBooking() {

        String error = "";
        LibraryBooking libraryBooking = null;
        Customer customer = new Customer();
            customer.setAccountId(CUSTOMER_ID);
            customer.setAccountBalance(0);
            customer.setAddress("testAddress");
            customer.setEmail("testEmail");
            customer.setFirstName("testFirstname");
            customer.setLastName("testLastName");
            customer.setIsLocal(true);
            customer.setIsVerified(true);
            customer.setPassword("testPassword");
        try {
            libraryBooking = libraryBookingService.updateLibraryBookingCustomer(NONEXISTANT_LIBRARYBOOKING_ID,  customer);
        } catch(InvalidInputException e) {
            error = e.getMessage();
        }
        assertNull(libraryBooking);
        assertEquals(error, "librayBooking does not exist");
        
    }

    @Test 
    public void testUpdateLibraryBookingCustomerNullCustomer() {
        String error = "";
        LibraryBooking libraryBooking = null;
        try {
            libraryBooking = libraryBookingService.updateLibraryBookingCustomer(LIBRARYBOOKING_ID,  null);
        } catch(InvalidInputException e) {
            error = e.getMessage();
        }
        assertNull(libraryBooking);
        assertEquals(error, "Customer cannot be empty");
        
    }
    
}
