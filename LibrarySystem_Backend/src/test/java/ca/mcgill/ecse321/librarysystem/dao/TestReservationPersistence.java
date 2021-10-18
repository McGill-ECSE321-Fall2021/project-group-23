package ca.mcgill.ecse321.librarysystem.dao;

import java.util.ArrayList;
import java.util.List;

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

import ca.mcgill.ecse321.librarysystem.model.Book;
import ca.mcgill.ecse321.librarysystem.model.Customer;
import ca.mcgill.ecse321.librarysystem.model.Item;
import ca.mcgill.ecse321.librarysystem.model.Reservation;
import ca.mcgill.ecse321.librarysystem.model.Item.Status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TestReservationPersistence {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private ItemRepository itemRepository;

    @AfterEach
    public void clearDatabase() {
        //delete reservation first because of dependencies
        reservationRepository.deleteAll();
        customerRepository.deleteAll();
        itemRepository.deleteAll();

    }


    @Test
    public void testPersistAndLoadReservationViaCustomer() {

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
        
        Item newItem1 = new Book();
        Status status1 = Item.Status.BORROWED;
        int itemId1 = 2;
        String title1 = "Harry Potter";
        newItem1.setItemId(itemId1);
        newItem1.setStatus(status1);
        newItem1.setTitle(title1);

        Item newItem2 = new Book();
        Status status2 = Item.Status.BORROWED;
        int itemId2 = 2;
        String title2 = "Harry Potter 2";
        newItem2.setItemId(itemId2);
        newItem2.setStatus(status2);
        newItem2.setTitle(title2);
        
        Reservation reservation1 = new Reservation();
        int reservation1Id = 123;
        Date reservation1StartDate = java.sql.Date.valueOf(LocalDate.of(2021, Month.DECEMBER, 1));
		Date reservation1EndDate = java.sql.Date.valueOf(LocalDate.of(2021, Month.DECEMBER, 14));
        boolean isCheckedOut1 = true;
        reservation1.setId(reservation1Id);
        reservation1.setReservationStartDate(reservation1StartDate);
        reservation1.setReservationEndDate(reservation1EndDate);
        reservation1.setCustomer(newCustomer);
        reservation1.setIsCheckedOut(isCheckedOut1);
        reservation1.setItem(newItem1);

        Reservation reservation2 = new Reservation();
        int reservation2Id = 456;
        Date reservation2StartDate = java.sql.Date.valueOf(LocalDate.of(2021, Month.DECEMBER, 15));
		Date reservation2EndDate = java.sql.Date.valueOf(LocalDate.of(2021, Month.DECEMBER, 28));
        boolean isCheckedOut2 = true;
        reservation2.setId(reservation2Id);
        reservation2.setReservationStartDate(reservation2StartDate);
        reservation2.setReservationEndDate(reservation2EndDate);
        reservation2.setCustomer(newCustomer);
        reservation2.setIsCheckedOut(isCheckedOut2);
        reservation2.setItem(newItem2);

        customerRepository.save(newCustomer);
        reservationRepository.save(reservation1);
        reservationRepository.save(reservation2);
        itemRepository.save(newItem1);
        itemRepository.save(newItem2);


        List<Reservation> reservations = new ArrayList<Reservation>();

        reservation1 = null;
        reservation2 = null;

        reservations = reservationRepository.findByCustomer(newCustomer);

        reservation1 = reservations.get(0);
        reservation2 = reservations.get(1);

        assertNotNull(reservation1);
		assertNotNull(reservation2);
        assertEquals(reservation1Id, reservation1.getid());
        assertEquals(reservation1StartDate, reservation1.getReservationStartDate());
        assertEquals(reservation1EndDate, reservation1.getReservationEndDate());
        assertEquals(reservation2Id, reservation2.getid());
        assertEquals(reservation2StartDate, reservation2.getReservationStartDate());
        assertEquals(reservation2EndDate, reservation2.getReservationEndDate());

    }

    @Test
    public void testPersistAndLoadReservationViaItem() {
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
        
        Item newItem1 = new Book();
        Status status1 = Item.Status.BORROWED;
        int itemId1 = 2;
        String title1 = "Harry Potter";
        newItem1.setItemId(itemId1);
        newItem1.setStatus(status1);
        newItem1.setTitle(title1);

        Reservation reservation1 = new Reservation();
        int reservation1Id = 123;
        Date reservation1StartDate = java.sql.Date.valueOf(LocalDate.of(2021, Month.DECEMBER, 1));
		Date reservation1EndDate = java.sql.Date.valueOf(LocalDate.of(2021, Month.DECEMBER, 14));
        boolean isCheckedOut1 = true;
        reservation1.setId(reservation1Id);
        reservation1.setReservationStartDate(reservation1StartDate);
        reservation1.setReservationEndDate(reservation1EndDate);
        reservation1.setCustomer(newCustomer);
        reservation1.setIsCheckedOut(isCheckedOut1);
        reservation1.setItem(newItem1);

        customerRepository.save(newCustomer);
        itemRepository.save(newItem1);
        reservationRepository.save(reservation1);

        reservation1 = null;

        Reservation testReservation = new Reservation();

        testReservation = reservationRepository.findByItem(newItem1);

        assertNotNull(testReservation);
        assertEquals(reservation1Id, testReservation.getid());
        assertEquals(reservation1StartDate, testReservation.getReservationStartDate());
        assertEquals(reservation1EndDate, testReservation.getReservationEndDate());


    }

    @Test
    public void testPersistAndLoadReservationViaId() {

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
        
        Item newItem1 = new Book();
        Status status1 = Item.Status.BORROWED;
        int itemId1 = 2;
        String title1 = "Harry Potter";
        newItem1.setItemId(itemId1);
        newItem1.setStatus(status1);
        newItem1.setTitle(title1);

        Reservation reservation1 = new Reservation();
        int reservation1Id = 123;
        Date reservation1StartDate = java.sql.Date.valueOf(LocalDate.of(2021, Month.DECEMBER, 1));
		Date reservation1EndDate = java.sql.Date.valueOf(LocalDate.of(2021, Month.DECEMBER, 14));
        boolean isCheckedOut1 = true;
        reservation1.setId(reservation1Id);
        reservation1.setReservationStartDate(reservation1StartDate);
        reservation1.setReservationEndDate(reservation1EndDate);
        reservation1.setCustomer(newCustomer);
        reservation1.setIsCheckedOut(isCheckedOut1);
        reservation1.setItem(newItem1);

        customerRepository.save(newCustomer);
        itemRepository.save(newItem1);
        reservationRepository.save(reservation1);

        reservation1 = null;

        Reservation testReservation = new Reservation();

        testReservation = reservationRepository.findByReservationId(reservation1Id);

        assertNotNull(testReservation);
        assertEquals(reservation1Id, testReservation.getid());
        assertEquals(reservation1StartDate, testReservation.getReservationStartDate());
        assertEquals(reservation1EndDate, testReservation.getReservationEndDate());

    }

    
    
}
