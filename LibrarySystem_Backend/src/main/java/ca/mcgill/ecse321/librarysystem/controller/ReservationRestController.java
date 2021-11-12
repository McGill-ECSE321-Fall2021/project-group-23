package ca.mcgill.ecse321.librarysystem.controller;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;

import ca.mcgill.ecse321.librarysystem.dto.ReservationDto;
import ca.mcgill.ecse321.librarysystem.dto.ItemDto;
import ca.mcgill.ecse321.librarysystem.dto.CustomerDto;
import ca.mcgill.ecse321.librarysystem.model.Customer;
import ca.mcgill.ecse321.librarysystem.model.Item;
import ca.mcgill.ecse321.librarysystem.model.Reservation;
import ca.mcgill.ecse321.librarysystem.service.ReservationService;
import ca.mcgill.ecse321.librarysystem.service.CustomerService;
import ca.mcgill.ecse321.librarysystem.service.ItemService;

@CrossOrigin(origins = "*")
@RestController
public class ReservationRestController {

    @Autowired
    private ReservationService reservationService;

    @Autowired
    private ItemService itemService;

    @Autowired
    private CustomerService customerService;

    /**
     * creates a reservation
     * 
     */
    @PostMapping(value = { "/createReservation/{customerId}/{itemId}/{isCheckedOut}/{date}",
            "/createReservation/{customerId}/{itemId}/{isCheckedOut}/{date}/" })
    public ReservationDto createReservation(@PathVariable("customerId") int customerId,
            @PathVariable("itemId") int itemId,
            @PathVariable("date") String date,
            @PathVariable("isCheckedOut") boolean isCheckedOut) {
        ReservationDto reservationDto = convertToDto(
                reservationService.createReservation(itemId, customerId, Date.valueOf(date), isCheckedOut));
        return reservationDto;
    }

    /**
     * gets all the reservations
     * 
     */
    @GetMapping(value = { "/getAllReservation", "/getAllReservation/" })
    public List<ReservationDto> getAllReservation() {
        return reservationService.getAllReservations().stream().map(u -> convertToDto(u)).collect(Collectors.toList());
    }

    /**
     * gets the reservation with a unique id
     * 
     */
    @GetMapping(value = { "/getReservationById/{reservationId}", "/getReservationById/{reservationId}/" })
    public ReservationDto getReservationById(@PathVariable("reservationId") int reservationId) {
        return convertToDto(reservationService.getReservationById(reservationId));
    }

    /**
     * gets all the reservations made by a customer
     * 
     */
    @GetMapping(value = { "/getReservationByCustomer/{customerId}", "/getReservationByCustomer/{customerId}/" })
    public List<ReservationDto> getReservationByCustomer(@PathVariable("customerId") int customerId) {
        List<ReservationDto> reservationDtos = new ArrayList<>();
        for (Reservation reservation : reservationService
                .getReservationByCustomer(customerService.getCustomerByAccountId(customerId))) {
            reservationDtos.add(convertToDto(reservation));
        }
        return reservationDtos;
    }

    /**
     * gets the reservation made on an item
     * 
     */
    @GetMapping(value = { "/getReservationByItem/{itemId}", "/getReservationByitem/{itemId}/" })
    public ReservationDto getReservationByItem(@PathVariable("itemId") int itemId) {
        return convertToDto(reservationService.getReservationByItem(itemService.getItem(itemId)));

    }

    /**
     * deletes a reservation with a specific id
     * 
     */
    @DeleteMapping(value = { "/deleteReservation/{reservationId}", "/deleteReservation/{reservationId}/" })
    public ReservationDto deleteReservation(@PathVariable("reservationId") int reservationId) {
        ReservationDto reservation = convertToDto(reservationService.deleteReservation(reservationId));
        return reservation;
    }

    /**
     * deletes all reservations
     * 
     */
    @DeleteMapping(value = { "/deleteAllReservation", "/deleteAllReservation/" })
    public List<ReservationDto> deleteAllReservation() {
        List<ReservationDto> reservationDtos = new ArrayList<>();
        for (Reservation reservation : reservationService.getAllReservations()) {
            reservationDtos.add(convertToDto(reservation));
        }
        reservationService.deleteAllReservation();
        return reservationDtos;
    }

    /**
     * update a reservation start and end date
     * 
     */
    @PutMapping(value = { "/updateReservationDate/{reservationId}", "/updateReservationDate/{reservationId}/" })
    public ReservationDto updateReservationDate(@PathVariable("reservationId") int reservationId,
            @RequestParam String startDate, @RequestParam String endDate) {
        ReservationDto reservation = convertToDto(
                reservationService.updateReservationDate(reservationId, Date.valueOf(startDate), Date.valueOf(endDate)));
        return reservation;

    }

    /**
     * updates the reservation customer
     * 
     */
    /*@PutMapping(value = { "updateReservationCustomer/{reservationId}/{customerId}",
            "updateReservationCustomer/{reservationId}/{customerId}" })
    public ReservationDto updateReservationCustomer(@PathVariable("reservationId") int reservationId,
            @PathVariable("customerId") int customerId) {
        ReservationDto reservation = convertToDto(reservationService.updateReservationCustomer(reservationId,
                customerService.getCustomerByAccountId(customerId)));
        return reservation;
    }*/

    /**
     * updates the reservation item
     * 
     */
    /*@PutMapping(value = { "updateReservationItem/{reservationId}/{itemId}",
            "updateReservationItem/{reservationId}/{itemId}" })
    public ReservationDto updateReservationItem(@PathVariable("reservationId") int reservationId,
            @PathVariable("itemId") int itemId) {
        ReservationDto reservation = convertToDto(
                reservationService.updateReservationItem(reservationId, itemService.getItem(itemId)));
        return reservation;
    }*/

    /**
     * converts a reservation to a reservation Dto
     * 
     */

    private ReservationDto convertToDto(Reservation reservation) {
        if (reservation == null) {
            throw new InvalidInputException("There is no such reservation!");
        }
        ReservationDto reservationDto = new ReservationDto(convertToDto(reservation.getCustomer()),
                convertToDto(reservation.getItem()), reservation.getReservationStartDate(),
                reservation.getReservationEndDate(), reservation.getId(), reservation.getIsCheckedOut());
        return reservationDto;
    }

    /**
     * converts an item to and item Dto
     * 
     */
    public ItemDto convertToDto(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("There is no such item");
        }
        String type = item.getClass().getSimpleName();
        ItemDto itemDto = new ItemDto(item.getItemId(), item.getTitle(), item.getStatus().toString(), type);
        return itemDto;
    }

    /**
     * converts a customer to a customer Dto
     * 
     */
    private CustomerDto convertToDto(Customer customer) {
        if (customer == null) {
            throw new InvalidInputException("There is no such customer!");
        }
        CustomerDto customerDto = new CustomerDto(customer.getFirstName(), customer.getLastName(),
                customer.getAccountId(), customer.getPassword(), customer.getEmail(), customer.getIsVerified(),
                customer.getIsLocal(), customer.getAccountBalance());
        return customerDto;
    }

}
