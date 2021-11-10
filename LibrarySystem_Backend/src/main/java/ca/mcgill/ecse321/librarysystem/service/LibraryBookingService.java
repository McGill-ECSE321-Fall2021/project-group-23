package ca.mcgill.ecse321.librarysystem.service;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.librarysystem.model.LibraryBooking;
import ca.mcgill.ecse321.librarysystem.dao.LibraryBookingRepository;
import ca.mcgill.ecse321.librarysystem.dao.CustomerRepository;
import ca.mcgill.ecse321.librarysystem.model.Customer;
import org.springframework.stereotype.Service;

@Service
public class LibraryBookingService {

    /**
     * Author: Abdouallah Tahdi
     */


    @Autowired
    private LibraryBookingRepository libraryBookingRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Transactional
    public LibraryBooking createLibraryBooking(Date startDate, Date endDate, Time startTime, Time endTime, int customerId) {
        String error = "";
        if (startTime == null) {
            error = "A start time is needed to create a LibraryBooking";
        }

        if (endTime == null) {
            error = "An end time is needed to create a libraryBooking";
        }


        if (startDate == null) {
            error = error + "A start date is needed to create a libraryBooking";
        }

        if (endDate == null) {
            error = error + "An end date is needed to create a libraryBooking";
        }

        //Eachh libraryBooking must only be one day
        if(endDate!=null && startDate!=null) {
            if (!endDate.equals(startDate)) {
                error = error + "Each libraryBooking cannot be for multiple days";
            }
        }
        //Checks for overlaps with other libraryBookings
        if(error.length()==0) {
            for(LibraryBooking libBooking : libraryBookingRepository.findAll()) {
                if(startTime.before(libBooking.getEndTime()) && startTime.after(libBooking.getStartTime()) && startDate.equals(libBooking.getStartDate())) {
                    error = error +"This libraryBooking overlaps with an existing libraryBooking";
                }
                if(endTime.before(libBooking.getEndTime()) && endTime.after(libBooking.getStartTime()) && startDate.equals(libBooking.getStartDate())) {
                    error = error +"This libraryBooking overlaps with an existing libraryBooking";
                }
                if(startTime.before(libBooking.getStartTime()) && endTime.after(libBooking.getEndTime()) && startDate.equals(libBooking.getStartDate())) {
                    error = error +"This libraryBooking overlaps with an existing libraryBooking";
                }
                if(startTime.after(libBooking.getStartTime()) && endTime.before(libBooking.getEndTime()) && startDate.equals(libBooking.getStartDate())) {
                    error = error +"This libraryBooking overlaps with an existing libraryBooking";
                }
    
            }
            
        }
        
    

        // if (startDate.equals(endDate) && endTime.before(startTime)) {
        //     error = error +"The end time of a libraryBooking cannot be before the start time";
        // }

        // for(LibraryBooking libBooking : libraryBookingRepository.findAll()) {
        //     if(startDate.before(libBooking.getEndDate()) && startDate.after(libBooking.getEndDate())){
        //         error = error +"This libraryBooking overlaps with an existing libraryBooking";
        //     }
        //     if(endDate.before(libBooking.getEndDate()) && endDate.after(libBooking.getStartDate())) {
        //         error = error +"This libraryBooking overlaps with an existing libraryBooking";
        //     }
        //     if(startDate.before(libBooking.getStartDate()) && endDate.after(libBooking.getEndDate())){
        //         error = error +"This libraryBooking overlaps with an existing libraryBooking";
        //     }
        //     if(startDate.after(libBooking.getStartDate()) && endDate.before(libBooking.getEndDate())){
        //         error = error +"This libraryBooking overlaps with an existing libraryBooking";
        //     }
        //     if(startDate.compareTo(libBooking.getStartDate()) == 0 && endDate.after(libBooking.getEndDate())) {
        //         error = error +"This libraryBooking overlaps with an existing libraryBooking"; 
        //     }
        //     if(startDate.compareTo(libBooking.getStartDate()) == 0 && endDate.before(libBooking.getEndDate())) {
        //         error = error +"This libraryBooking overlaps with an existing libraryBooking"; 
        //     }
        //     if(endDate.compareTo(libBooking.getEndDate()) == 0 && startDate.before(libBooking.getStartDate())) {
        //         error = error +"This libraryBooking overlaps with an existing libraryBooking"; 
        //     }
        //     if(endDate.compareTo(libBooking.getEndDate()) == 0 && startDate.after(libBooking.getStartDate())) {
        //         error = error +"This libraryBooking overlaps with an existing libraryBooking"; 
        //     }
        //     if(startDate.compareTo(libBooking.getStartDate()) == 0 && endDate.compareTo(libBooking.getEndDate()) == 0 && startDate.compareTo(endDate) == 0) {
        //         if(endTime.after(libBooking.getStartTime()) && endTime.before(libBooking.getEndTime())) {
        //             error = error +"This libraryBooking overlaps with an existing libraryBooking"; 
        //         }
        //         if(startTime.after(libBooking.getStartTime()) && startTime.before(libBooking.getEndTime())){
        //             error = error +"This libraryBooking overlaps with an existing libraryBooking"; 
        //         }
        //         if(startTime.before(libBooking.getStartTime()) && endTime.after(libBooking.getEndTime())) {
        //             error = error +"This libraryBooking overlaps with an existing libraryBooking";
        //         }
        //         if(startTime.after(libBooking.getStartTime()) && endTime.before(libBooking.getEndTime())) {
        //             error = error +"This libraryBooking overlaps with an existing libraryBooking";
        //         }
        //     }

        //     if(startDate.compareTo(libBooking.getStartDate()) == 0 && endDate.compareTo(libBooking.getEndDate()) == 0 && startDate.compareTo(endDate) != 0) {
        //         error = error +"This libraryBooking overlaps with an existing libraryBooking";
        //     }

        //     if(startDate.compareTo(libBooking.getEndDate()) == 0) {
        //         if(startTime.before(libBooking.getEndTime())) {
        //             error = error +"This libraryBooking overlaps with an existing libraryBooking";
        //         }
        //     }

        //     if(endDate.compareTo(libBooking.getStartDate()) == 0) {
        //         if(endTime.before(libBooking.getStartTime())) {
        //             error = error +"This libraryBooking overlaps with an existing libraryBooking";
        //         }
        //     }


            
        //}
        error = error.trim();
        if (error.length() >0) {
            throw new InvalidInputException(error);
        }
        LibraryBooking libraryBooking = new LibraryBooking();
        libraryBooking.setStartDate(startDate);
        libraryBooking.setEndDate(endDate);
        libraryBooking.setEndTime(endTime);
        libraryBooking.setStartTime(startTime);
        libraryBooking.setCustomer(customerRepository.findCustomerByAccountId(customerId));
        libraryBookingRepository.save(libraryBooking);
        return libraryBooking;
    }

    @Transactional
    public LibraryBooking getLibraryBookingbyId(int id) {
        String error = "";
        if (libraryBookingRepository.findById(id) == null) {
            error = "libraryBooking does not exist";
        }
        error = error.trim();
        if (error.length() >0) {
            throw new InvalidInputException(error);
        }
        return libraryBookingRepository.findById(id);
    }

    @Transactional
    public List<LibraryBooking> getLibraryBookingByCustomer(Customer customer) {
        String error = "";
        if (libraryBookingRepository.findByCustomer(customer) == null) {
            error = "libraryBooking does not exist";
        }
        error = error.trim();
        if (error.length() >0) {
            throw new InvalidInputException(error);
        }
        return libraryBookingRepository.findByCustomer(customer);
    }

    @Transactional
    public List<LibraryBooking> getAllLibraryBooking() {
        return toList(libraryBookingRepository.findAll());
    }

    @Transactional
    public LibraryBooking deleteLibraryBooking(int id) {
        String error = "";
        if (libraryBookingRepository.findById(id) == null) {
            error = "libraryBooking does not exist";
        }
        error = error.trim();
        if (error.length() >0) {
            throw new InvalidInputException(error);
        }
        LibraryBooking libraryBooking = libraryBookingRepository.findById(id);
        libraryBookingRepository.delete(libraryBooking);
        return libraryBooking;
    }

    @Transactional
    public List<LibraryBooking> deleteAllLibraryBooking() {
        Iterable<LibraryBooking> libraryBookings = libraryBookingRepository.findAll();
        libraryBookingRepository.deleteAll();
        return toList(libraryBookings);
    }

    @Transactional
    public LibraryBooking updateLibraryBookingDateAndTime(int id, Date startDate, Date endDate, Time startTime, Time endTime) {
        String error = "";
        if (startDate == null ) {
            error = error + "StartDate cannot be empty";
        }
        if (endDate == null ) {
            error = error + "EndDate cannot be empty";
        }
        if (startTime == null ) {
            error = error + "StartTime cannot be empty";
        }
        if (endTime == null ) {
            error = error + "EndTime cannot be empty";
        }
        if(error.length()==0) {
            if (!endDate.equals(startDate)) {
                error = error + "StartDate and endDate must be equal";
            }
            if (endDate.equals(startDate) && startTime.after(endTime)) {
                error = error + "The end time of a libraryBooking cannot be before its start time";
            }  
        }
        
        error = error.trim();
        if (error.length() >0) {
            throw new InvalidInputException(error);
        }

        LibraryBooking libraryBooking = libraryBookingRepository.findById(id);
        libraryBooking.setEndDate(endDate);
        libraryBooking.setStartDate(startDate);
        libraryBooking.setStartTime(startTime);
        libraryBooking.setEndTime(endTime);
        libraryBookingRepository.save(libraryBooking);
        return libraryBooking;
    }

    @Transactional
    public LibraryBooking updateLibraryBookingCustomer(int id, Customer customer) {

        String error = "";
        if (libraryBookingRepository.findById(id) == null) {
            error = "librayBooking does not exist";
        }
        if (customer == null ) {
            error = error + "Customer cannot be empty";
        }
        error = error.trim();
        if (error.length() >0) {
            throw new InvalidInputException(error);
        }

        LibraryBooking libraryBooking = libraryBookingRepository.findById(id);
        libraryBooking.setCustomer(customer);
        libraryBookingRepository.save(libraryBooking);
        return libraryBooking;
    }

    private <T> List<T> toList(Iterable<T> iterable) {
        List<T> resultList = new ArrayList<T>();
        for (T t : iterable) {
            resultList.add(t);
        }
        return resultList;
    }
    
}
