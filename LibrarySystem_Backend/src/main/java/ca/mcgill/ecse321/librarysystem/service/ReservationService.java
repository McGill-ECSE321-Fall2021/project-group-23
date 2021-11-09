package ca.mcgill.ecse321.librarysystem.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;

import ca.mcgill.ecse321.librarysystem.dao.ReservationRepository;
import ca.mcgill.ecse321.librarysystem.dao.ItemRepository;
import ca.mcgill.ecse321.librarysystem.dao.CustomerRepository;
import ca.mcgill.ecse321.librarysystem.model.Customer;
import ca.mcgill.ecse321.librarysystem.model.Item;
import ca.mcgill.ecse321.librarysystem.model.Reservation;
import org.springframework.stereotype.Service;

@Service
public class ReservationService {

    /**
     * Author: Abdouallah Tahdi
     */
    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private CustomerRepository customerRepository;
    

    @Transactional
    public Reservation createReservation(int itemId, int customerId, Date startDate, Date endDate, boolean isCheckedout){

        String error = "";


        if (startDate == null) {
            error = error + "A start date is needed to create a reservation";
        }

        if (endDate == null) {
            error = error + "An end date is needed to create a reservation";
        }
        //Check if they are no reservation for this item
        for (Reservation reserv : reservationRepository.findAll()) {
            if (reserv.getItem().getItemId() == itemId) {
                error = error + "Cannot have two reservations for the same item";
            }
        }
        //Check if the item is available
        if(itemRepository.findById(itemId).get().getStatus().compareTo(Item.Status.AVAILABLE)  != 0) {
            error = error + "The book is not available";
        }

        //Check if the item can be reserved
        if(!itemRepository.findById(itemId).get().canBeBorrowed) {
            error = error + "This item cannot be borrowed";
        }

        //Check that the customer does not go beyond the maximum number of reservation
        if(reservationRepository.findByCustomer(customerRepository.findCustomerByAccountId(customerId)).size() == 10) {
            error = error + "One customer cannot have more than 10 reservation at the same time";
        }
        error = error.trim();
        if (error.length() >0) {
            throw new InvalidInputException(error);
        }
        Reservation reservation = new Reservation();
        itemRepository.findItemByItemId(itemId).setStatus(Item.Status.RESERVED);
        reservation.setItem(itemRepository.findItemByItemId(itemId));
        reservation.setCustomer(customerRepository.findCustomerByAccountId(customerId));
        reservation.setIsCheckedOut(isCheckedout);
        reservation.setReservationStartDate(startDate);
        reservation.setReservationEndDate(endDate);

        reservationRepository.save(reservation);
        return reservation;
        
    }

    @Transactional
    public Reservation getReservationById(int id) {

        String error = "";
        if (reservationRepository.findById(id) == null) {
            error = "reservation does not exist";
        }
        error = error.trim();
        if (error.length() >0) {
            throw new InvalidInputException(error);
        }
        return reservationRepository.findById(id);
    }

    @Transactional
    public List<Reservation> getReservationByCustomer(Customer customer){
        String error = "";
        if (reservationRepository.findByCustomer(customer) == null) {
            error = "reservation does not exist";
        }
        error = error.trim();
        if (error.length() >0) {
            throw new InvalidInputException(error);
        }
        return reservationRepository.findByCustomer(customer);
    }

    @Transactional
    public Reservation getReservationByItem(Item item) {
        String error = "";
        if (reservationRepository.findByItem(item) == null) {
            error = "reservation does not exist";
        }
        error = error.trim();
        if (error.length() >0) {
            throw new InvalidInputException(error);
        }
        return reservationRepository.findByItem(item);
    }

    @Transactional
    public List<Reservation> getAllReservations() {
        return toList(reservationRepository.findAll());
    }

    @Transactional
    public Reservation deleteReservation(int id) {
        String error = "";
        if (reservationRepository.findById(id) == null) {
            error = "reservation does not exist";
        }
        error = error.trim();
        if (error.length() >0) {
            throw new InvalidInputException(error);
        }
        Reservation reservation = reservationRepository.findById(id);
        //Make the item available again
        itemRepository.findItemByItemId(reservationRepository.findById(id).getItem().getItemId()).setStatus(Item.Status.AVAILABLE);
        reservationRepository.delete(reservation);
        return reservation;
    }

    @Transactional
    public List<Reservation> deleteAllReservation(){
        Iterable<Reservation> reservations = reservationRepository.findAll();
        for(Reservation reserv : reservationRepository.findAll()){
            itemRepository.findItemByItemId(reserv.getItem().getItemId()).setStatus(Item.Status.AVAILABLE); //make all the items available again
        }
        reservationRepository.deleteAll();
        return toList(reservations);

    }

    @Transactional
    public Reservation updateReservationDate(int id, Date startDate, Date endDate) {
        String error = "";
        if (reservationRepository.findById(id) == null) {
            error = "reservation does not exist";
        }
        if (startDate == null ) {
            error = error + "StartDate cannot be empty";
        }
        if (endDate == null ) {
            error = error + "EndDate cannot be empty";
        }
        if (endDate.before(startDate) ) {
            error = error + "EndDate cannot be before startDate";
        }
        error = error.trim();
        if (error.length() >0) {
            throw new InvalidInputException(error);
        }
        Reservation reservation = reservationRepository.findById(id);
        reservation.setReservationEndDate(endDate);
        reservation.setReservationStartDate(startDate);
        reservationRepository.save(reservation);
        return reservation;
    }

    @Transactional
    public Reservation updateReservationItem(int id, Item item) {
        String error = "";
        if (reservationRepository.findById(id) == null) {
            error = "reservation does not exist";
        }
        if (reservationRepository.findByItem(item) == null) {
            error = error + "reservation does not exist";
        }
        error = error.trim();
        if (error.length() >0) {
            throw new InvalidInputException(error);   
        }
        Reservation reservation = reservationRepository.findById(id);
        reservation.setItem(item);
        reservationRepository.save(reservation);
        return reservation;

    }

    @Transactional
    public Reservation updateReservationCustomer(int id, Customer customer) {
        String error = "";
        if (reservationRepository.findById(id) == null) {
            error = "reservation does not exist";
        }
        if (reservationRepository.findByCustomer(customer) == null) {
            error = error + "reservation does not exist";
        }
        error = error.trim();
        if (error.length() >0) {
            throw new InvalidInputException(error);   
        }
        Reservation reservation = reservationRepository.findById(id);
        reservation.setCustomer(customer);
        reservationRepository.save(reservation);
        return reservation;
    }


    private <T> List<T> toList(Iterable<T> iterable) {
        List<T> resultList = new ArrayList<T>();
        for (T t : iterable) {
            resultList.add(t);
        }
        return resultList;
    }

}
