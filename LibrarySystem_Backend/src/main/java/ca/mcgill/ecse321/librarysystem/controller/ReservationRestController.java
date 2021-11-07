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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ca.mcgill.ecse321.librarysystem.dto.ReservationDto;
import ca.mcgill.ecse321.librarysystem.model.Customer;
import ca.mcgill.ecse321.librarysystem.model.Item;
import ca.mcgill.ecse321.librarysystem.model.Reservation;
import ca.mcgill.ecse321.librarysystem.service.ReservationService;

@CrossOrigin(origins = "*")
@RestController
public class ReservationRestController {

@Autowired
private ReservationService reservationService;

// @Autowired
// private ItemService itemService;

// @Autowired
// private CustomerService customerService;

// @PostMapping(value = {"/createReservation/{customer}/{item}/{startDate}/{endDate}/{isCHeckedOut}", "/createReservation/{customer}/{item}/{startDate}/{endDate}/{isCheckedOut}/"})
// public ReservationDto createReservation(
//     @PathVariable("customer")int customerId,
//     @PathVariable("item")int itemId,
//     @RequestParam("startDate")Date startDate,
//     @RequestParam("endDate")Date endDate,
//     @PathVariable("isCheckedOut")Boolean isCheckedout
// ) {

//     Item itemSelected = itemService.getItemById(itemId);
//     Customer customerSelected = customerService.getCustomerById(customerId);
//     Reservation reservation = reservationService.createReservation(itemSelected, customerSelected, startDate, endDate, isCheckedout);
// }

// private ReservationDto convertToDto(Reservation reservation) {
//     if (reservation == null) {
//         throw new InvalidInputException("There is no such reservation!");
//     }
//     ReservationDto reservationDto = new ReservationDto(reservation.getCustomer(), reservation.getItem(), reservation.getReservationStartDate(), reservation.getReservationEndDate(),reservation.getId(), reservation.getIsCheckedOut());
//     return reservationDto;
// }

// private ItemDto convertToDto(Item item) {
//     if (item == null) {
//         throw new InvalidInputException("There is no such item!");
//     }
//     ItemDto itemDto = new ItemDto();
//     return itemDto; 

// }

// private CustomerDto convertToDto(Customer customer) {
//     if (customer == null) {
//         throw new InvalidInputException("There is no such customer!");
//     }
//     CustomerDto customerDto = new CustomerDto();
//     return customerDto;
// }

@GetMapping(value = {"/getAllReservation", "/getAllReservation/" })
public List<ReservationDto> getAllReservation() {
    List<ReservationDto> reservationDtos = new ArrayList<>();
    for (Reservation reservation : reservationService.getAllReservations()) {
        //reservationDtos.add(convertToDto(reservation));
    }
    return reservationDtos;
}

// @GetMapping(value = {"/getReservationById/{reservationId}", "/getReservationById/{reservationId}/" })
// public ReservationDto getReservationById(@PathVariable("reservationId") int reservationId) {
//     return convertToDto(reservationService.getReservationById(reservationId));
// }

@GetMapping(value = {"/getReservationByCustomer/{customerId}", "/getReservationByCustomer/{customerId}/" })
public List<ReservationDto> getReservationByCustomer(@PathVariable("customerId") int customerId) {
    List<ReservationDto> reservationDtos = new ArrayList<>();
    //for (Reservation reservation : reservationService.getReservationByCustomer(customerService.getCustomerbyId(customerId))) {
       // reservationDtos.add(convertToDto(reservation));
    //}
    return reservationDtos;
}


// @GetMapping(value = {"/getReservationByItem/{itemId}", "/getReservationByitem/{itemId}/" })
// public ReservationDto getReservationByItem(@PathVariable("itemId") int itemId) {
//     //return convertToDto(reservationService.getReservationByItem(itemService.getItemById(itemId)));
    
// }



    
}
