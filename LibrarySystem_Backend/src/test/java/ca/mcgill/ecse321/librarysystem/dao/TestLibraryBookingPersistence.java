package ca.mcgill.ecse321.librarysystem.dao;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import ca.mcgill.ecse321.librarysystem.model.Customer;
import ca.mcgill.ecse321.librarysystem.model.LibraryBooking;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

public class TestLibraryBookingPersistence {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private LibraryBookingRepository libraryBookingRepository;


    @AfterEach
    public void clearDatabase() {
        customerRepository.deleteAll();
        libraryBookingRepository.deleteAll();
    }

    @Test
    public void testPersistAndLoadViaCustomer() {
        Customer newCustomer = new Customer();
        String firstName = "Abdouallah";
        String lastName = "Tahdi";
        int accountId = 1;
        String password = "password123";
        String email = "Abdouallah.Tahdi@gmail.com";
        boolean isVerified = true;
        boolean isLocal = true;
        String address = "1 Mcgill street";
        int balance = 0;
        newCustomer.setAccountBalance(balance);
        newCustomer.setAccountId(accountId);
        newCustomer.setAddress(address);
        newCustomer.setEmail(email);
        newCustomer.setFirstName(firstName);
        newCustomer.setIsLocal(isLocal);
        newCustomer.setIsVerified(isVerified);
        newCustomer.setLastName(lastName);
        newCustomer.setPassword(password);

        LibraryBooking libraryBooking1 = new LibraryBooking();
        Date startDate1 = java.sql.Date.valueOf(LocalDate.of(2021, Month.DECEMBER, 1));
		Date endDate1 = java.sql.Date.valueOf(LocalDate.of(2021, Month.DECEMBER, 1));
		Time startTime1 = java.sql.Time.valueOf(LocalTime.of(10, 00));
		Time endTime1 = java.sql.Time.valueOf(LocalTime.of(11, 00));
        int id1 = 1;
        libraryBooking1.setStartDate(startDate1);
        libraryBooking1.setStartTime(startTime1);
        libraryBooking1.setEndDate(endDate1);
        libraryBooking1.setEndTime(endTime1);
        libraryBooking1.setId(id1);
        libraryBooking1.setCustomer(newCustomer);

        LibraryBooking libraryBooking2 = new LibraryBooking();
        Date startDate2 = java.sql.Date.valueOf(LocalDate.of(2021, Month.DECEMBER, 2));
		Date endDate2 = java.sql.Date.valueOf(LocalDate.of(2021, Month.DECEMBER, 2));
		Time startTime2 = java.sql.Time.valueOf(LocalTime.of(11, 00));
		Time endTime2 = java.sql.Time.valueOf(LocalTime.of(12, 00));
        int id2 = 2;
        libraryBooking2.setStartDate(startDate2);
        libraryBooking2.setStartTime(startTime2);
        libraryBooking2.setEndDate(endDate2);
        libraryBooking2.setEndTime(endTime2);
        libraryBooking2.setId(id2);
        libraryBooking2.setCustomer(newCustomer);

        customerRepository.save(newCustomer);
        libraryBookingRepository.save(libraryBooking1);
        libraryBookingRepository.save(libraryBooking2);

        libraryBooking1 = null;
        libraryBooking2 = null;

        List<LibraryBooking> libraryBookings = new ArrayList<LibraryBooking>();

        libraryBookings = libraryBookingRepository.findByCustomer(newCustomer);

        libraryBooking1 = libraryBookings.get(0);
        libraryBooking2 = libraryBookings.get(1);

        assertNotNull(libraryBooking1);
        assertNotNull(libraryBooking2);
        assertEquals(startDate1, libraryBooking1.getStartDate());
        assertEquals(endDate1, libraryBooking1.getEndDate());
        assertEquals(startTime1, libraryBooking1.getStartTime());
        assertEquals(endTime1, libraryBooking1.getEndTime());
        assertEquals(id1, libraryBooking1.getId());
        assertEquals(newCustomer, libraryBooking1.getCustomer());

        assertEquals(startDate2, libraryBooking2.getStartDate());
        assertEquals(endDate2, libraryBooking2.getEndDate());
        assertEquals(startTime2, libraryBooking2.getStartTime());
        assertEquals(endTime2, libraryBooking2.getEndTime());
        assertEquals(id2, libraryBooking2.getId());
        assertEquals(newCustomer, libraryBooking2.getCustomer());



    }

    @Test
    public void testPersistAndLoadViaId() {

        Customer newCustomer = new Customer();
        String firstName = "Abdouallah";
        String lastName = "Tahdi";
        int accountId = 1;
        String password = "password123";
        String email = "Abdouallah.Tahdi@gmail.com";
        boolean isVerified = true;
        boolean isLocal = true;
        String address = "1 Mcgill street";
        int balance = 0;
        newCustomer.setAccountBalance(balance);
        newCustomer.setAccountId(accountId);
        newCustomer.setAddress(address);
        newCustomer.setEmail(email);
        newCustomer.setFirstName(firstName);
        newCustomer.setIsLocal(isLocal);
        newCustomer.setIsVerified(isVerified);
        newCustomer.setLastName(lastName);
        newCustomer.setPassword(password);

        LibraryBooking libraryBooking1 = new LibraryBooking();
        Date startDate1 = java.sql.Date.valueOf(LocalDate.of(2021, Month.DECEMBER, 1));
		Date endDate1 = java.sql.Date.valueOf(LocalDate.of(2021, Month.DECEMBER, 1));
		Time startTime1 = java.sql.Time.valueOf(LocalTime.of(10, 00));
		Time endTime1 = java.sql.Time.valueOf(LocalTime.of(11, 00));
        int id1 = 1;
        libraryBooking1.setStartDate(startDate1);
        libraryBooking1.setStartTime(startTime1);
        libraryBooking1.setEndDate(endDate1);
        libraryBooking1.setEndTime(endTime1);
        libraryBooking1.setId(id1);
        libraryBooking1.setCustomer(newCustomer);


        customerRepository.save(newCustomer);
        libraryBookingRepository.save(libraryBooking1);

        libraryBooking1 = null;
        

        LibraryBooking testLibraryBooking = new LibraryBooking();

        testLibraryBooking = libraryBookingRepository.findByLibraryBookingById(id1);

        libraryBooking1 = testLibraryBooking;

        assertNotNull(libraryBooking1);
        assertEquals(startDate1, libraryBooking1.getStartDate());
        assertEquals(endDate1, libraryBooking1.getEndDate());
        assertEquals(startTime1, libraryBooking1.getStartTime());
        assertEquals(endTime1, libraryBooking1.getEndTime());
        assertEquals(id1, libraryBooking1.getId());
        assertEquals(newCustomer, libraryBooking1.getCustomer());



    }



    
}
