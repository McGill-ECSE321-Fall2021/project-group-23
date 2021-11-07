package ca.mcgill.ecse321.librarysystem.service;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.librarysystem.model.LibraryBooking;
import ca.mcgill.ecse321.librarysystem.dao.LibraryBookingRepository;
import ca.mcgill.ecse321.librarysystem.model.Customer;



public class LibraryBookingService {

    @Autowired
    private LibraryBookingRepository libraryBookingRepository;

    @Transactional
    public LibraryBooking createLibraryBooking(Date startDate, Date endDate, Time startTime, Time endTime, Customer customer) {
        String error = "";
        if (startTime == null) {
            error = "A start time is needed to create a LibraryBooking";
        }

        if (endTime == null) {
            error = "An end time is needed to create a libraryBooking";
        }

        if (customer == null) {
            error = error + "A customer is needed to create a libraryBooking";
        } 

        if (startDate == null) {
            error = error + "A start date is needed to create a libraryBooking";
        }

        if (endDate == null) {
            error = error + "An end date is needed to create a libraryBooking";
        }

        if (startDate.before(endDate)) {
            error = error + "The end date cannot be before the startdate";
        }

        if (startDate.equals(endDate) && endTime.before(startTime)) {
            error = error +"The end time of a libraryBooking cannot be before the start time";
        }
        error = error.trim();
        if (error.length() >0) {
            throw new InvalidInputException(error);
        }
        LibraryBooking libraryBooking = new LibraryBooking();
        libraryBooking.setStartDate(startDate);
        libraryBooking.setEndDate(endDate);
        libraryBooking.setEndTime(endTime);
        libraryBooking.setStartTime(startTime);
        libraryBooking.setCustomer(customer);
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
        return toList(libraryBookings);
    }

    @Transactional
    public LibraryBooking updateLibraryBookingDateAndTime(int id, Date startDate, Date endDate, Time startTime, Time endTime) {
        String error = "";
        if (libraryBookingRepository.findById(id) == null) {
            error = "librayBooking does not exist";
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
        if (endDate.equals(startDate) && startTime.before(endTime)) {
            error = error + "The end time of a libraryBooking cannot be before its start time";
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
    public LibraryBooking updateBookingCustomer(int id, Customer customer) {

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
