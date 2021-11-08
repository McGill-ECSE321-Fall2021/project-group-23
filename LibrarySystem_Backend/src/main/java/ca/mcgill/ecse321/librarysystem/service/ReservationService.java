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
        for (Reservation reserv : reservationRepository.findAll()) {
            if (reserv.getItem().getItemId() == itemId) {
                error = error + "Cannot have two reservations for the same item";
            }
        }
        error = error.trim();
        if (error.length() >0) {
            throw new InvalidInputException(error);
        }
        Reservation reservation = new Reservation();
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
        reservationRepository.delete(reservation);
        return reservation;
    }

    @Transactional
    public List<Reservation> deleteAllReservation(){
        Iterable<Reservation> reservations = reservationRepository.findAll();
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
