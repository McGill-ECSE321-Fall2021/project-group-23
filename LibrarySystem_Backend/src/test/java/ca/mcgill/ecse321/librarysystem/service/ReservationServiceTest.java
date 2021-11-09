package ca.mcgill.ecse321.librarysystem.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.lenient;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;

import ca.mcgill.ecse321.librarysystem.dao.CustomerRepository;
import ca.mcgill.ecse321.librarysystem.dao.ItemRepository;
import ca.mcgill.ecse321.librarysystem.dao.ReservationRepository;
import ca.mcgill.ecse321.librarysystem.model.Book;
import ca.mcgill.ecse321.librarysystem.model.Customer;
import ca.mcgill.ecse321.librarysystem.model.Item;
import ca.mcgill.ecse321.librarysystem.model.Movie;
import ca.mcgill.ecse321.librarysystem.model.Reservation;

@ExtendWith(MockitoExtension.class)
public class ReservationServiceTest {

    @Mock
    private ReservationRepository reservationRepository;
    @Mock
    private ItemRepository itemRepository;
    @Mock
    private CustomerRepository customerRepository;
    @InjectMocks
    private ReservationService reservationService;

    //new reservation
    private static final int new_RESERVATION_ID = 100;
    private static final int ITEM_ID_exists = 10; //movie
    private static final int CUSTOMER_ID_exists = 69;
    private static final Date new_STARTDATE = parseDate(2021,11,01);
    private static final Date new_ENDDATE = parseDate(2021,11,21);
    private static final boolean new_ISNOTCHECKEDOUT = false;
    //private static final boolean new_ISCHECKEDOUT = true;

    //already present reservation
    private static final int RESERVATION_ID_exists = 50;
    private static final int ITEM_ID_exists_reserved = 15; //book
    private static final Date STARTDATE_exists = parseDate(2021,11,03);
    private static final Date ENDDATE_exists = parseDate(2021,11,23);
    private static final boolean ISCHECKEDOUT_exists = true;
    //already present reservation item that is already borrowed (or checked out)
    private static final String ITEM_Title_exists = "lol book"; 


   //private static final int NON_EXISTING_ITEM_ID = 24;
    //private static final String NON_EXISTING_ITEM_TITLE = "NotAnItem";

    @BeforeEach
    public void setMockOutput() {

        //Mock database
        Customer cust = new Customer();
        cust.setAccountId(CUSTOMER_ID_exists);

        //movie not reserved
        Movie movie = new Movie();
        movie.setItemId(ITEM_ID_exists);
        movie.setTitle("zombies");
        movie.setStatus(Item.Status.AVAILABLE);

        //reserved book
        Book book = new Book();
        book.setItemId(ITEM_ID_exists_reserved);
        book.setTitle(ITEM_Title_exists);
        book.setStatus(Item.Status.BORROWED);

        Reservation reservation = new Reservation();
        reservation.setId(RESERVATION_ID_exists);
        reservation.setCustomer(cust);
        reservation.setItem(book);
        reservation.setReservationStartDate(STARTDATE_exists);
        reservation.setReservationEndDate(ENDDATE_exists);
        reservation.setIsCheckedOut(ISCHECKEDOUT_exists);
        ArrayList<Reservation> DBReservationList = new ArrayList<Reservation>();
        DBReservationList.add(reservation);


        lenient().when(reservationRepository.findAll()).thenAnswer((InvocationOnMock invocation) -> {
            return DBReservationList;
        });
        lenient().when(reservationRepository.findById(anyInt())).thenAnswer((InvocationOnMock invocation) -> {
            if (invocation.getArgument(0).equals(RESERVATION_ID_exists)){
                return reservation;
            }
            return null;
        });
        lenient().when(itemRepository.findById(anyInt())).thenAnswer((InvocationOnMock invocation) -> {
            if (invocation.getArgument(0).equals(ITEM_ID_exists_reserved)) {
                return book;
            }
            else if (invocation.getArgument(0).equals(ITEM_ID_exists)) {
                return movie;
            }
            else {
                return null;
            }
        });
        lenient().when(customerRepository.findCustomerByAccountId(anyInt())).thenAnswer((InvocationOnMock invocation) -> {
            if (invocation.getArgument(0).equals(CUSTOMER_ID_exists)) {
                return cust;
            } else {
                return null;
            }
        });
        lenient().when(reservationRepository.findByCustomer(any())).thenAnswer((InvocationOnMock invocation) -> {
            return DBReservationList;
        });
        // Whenever anything is saved, just return the parameter object
        Answer<?> returnParameterAsAnswer = (InvocationOnMock invocation) -> {
            return invocation.getArgument(0);
        };
        lenient().when(itemRepository.save(any(Item.class))).thenAnswer(returnParameterAsAnswer);
    }

    @Test
    public void testCreateReservationSUCCESS() {
        Reservation res = null;
        try {
            res = reservationService.createReservation(ITEM_ID_exists, CUSTOMER_ID_exists, new_STARTDATE, new_ISNOTCHECKEDOUT);
            System.out.println(res.getId());
        } catch (Exception e) {
            fail(e.getMessage());
        }
        assertNotNull(res);
        assertEquals(CUSTOMER_ID_exists, res.getCustomer().getAccountId());
        assertEquals(ITEM_ID_exists, res.getItem().getItemId());
    }
    @Test
    public void testGetReservationByIdSUCCESS() {
        Reservation res = null;
        try {
            res = reservationService.getReservationById(RESERVATION_ID_exists);
        } catch (Exception e) {
            fail(e.getMessage());
        }
        assertNotNull(res);
        assertEquals(CUSTOMER_ID_exists, res.getCustomer().getAccountId());
        assertEquals(ITEM_ID_exists_reserved, res.getItem().getItemId());
    }

    
    public static Date parseDate(int year, int month, int day) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month - 1); // <-- months start
        cal.set(Calendar.DAY_OF_MONTH, day);

        return new java.sql.Date(cal.getTimeInMillis());
    }

}
