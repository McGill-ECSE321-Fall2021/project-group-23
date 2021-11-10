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

    //Creates a LibraryBooking
    @Transactional
    public LibraryBooking createLibraryBooking(Date startDate, Date endDate, Time startTime, Time endTime, int customerId) {
        String error = "";
        //Check for null startTime
        if (startTime == null) {
            error = "A start time is needed to create a LibraryBooking";
        }
        //Check for null endTime
        if (endTime == null) {
            error = "An end time is needed to create a libraryBooking";
        }
        //Check for null startDate
        if (startDate == null) {
            error = error + "A start date is needed to create a libraryBooking";
        }
        //Check for null endDate
        if (endDate == null) {
            error = error + "An end date is needed to create a libraryBooking";
        }

        //Each libraryBooking must only be one day

        if(endDate!=null && startDate!=null) {
            if (!endDate.equals(startDate)) {
                error = error + "Each libraryBooking cannot be for multiple days";
            }

        }

        //Check for endTime before startTime
        if(error.length()==0) {
            if(endTime.before(startTime)) {
                error = error + "The end time of a libraryBooking cannot be before its start time";
            }

        }

        //Checks for overlaps with other libraryBookings
        if(error.length()==0) {
            for(LibraryBooking libBooking : libraryBookingRepository.findAll()) {
                if(startTime.before(libBooking.getEndTime()) && startTime.after(libBooking.getStartTime()) && String.valueOf(startDate).equals(String.valueOf(libBooking.getStartDate()))|| startTime.equals(libBooking.getStartTime())&& String.valueOf(startDate).equals(String.valueOf(libBooking.getStartDate()))) {
                    error = error +"This libraryBooking overlaps with an existing libraryBooking";
                }
            }
        }
        if(error.length()==0) {
            for(LibraryBooking libBooking : libraryBookingRepository.findAll()) {
                if(endTime.before(libBooking.getEndTime()) && endTime.after(libBooking.getStartTime()) && String.valueOf(startDate).equals(String.valueOf(libBooking.getStartDate()))|| endTime.equals(libBooking.getEndTime())&& String.valueOf(startDate).equals(String.valueOf(libBooking.getStartDate()))) {
                    error = error +"This libraryBooking overlaps with an existing libraryBooking";
                }
            }
        }
        if(error.length()==0) {
            for(LibraryBooking libBooking : libraryBookingRepository.findAll()) {
                if(startTime.before(libBooking.getStartTime()) && endTime.after(libBooking.getEndTime())&& String.valueOf(startDate).equals(String.valueOf(libBooking.getStartDate()))) {
                    error = error +"This libraryBooking overlaps with an existing libraryBooking";
                }
            }
        }
        if(error.length()==0) {
            for(LibraryBooking libBooking : libraryBookingRepository.findAll()) {
                if(startTime.after(libBooking.getStartTime()) && endTime.before(libBooking.getEndTime()) && String.valueOf(startDate).equals(String.valueOf(libBooking.getStartDate()))) {
                    error = error +"This libraryBooking overlaps with an existing libraryBooking";
                }
            }
        }

        //Check for identical libraryBookoing date and time

        if(error.length()==0) {
            for(LibraryBooking libBooking : libraryBookingRepository.findAll()) {
                if(startTime.equals(libBooking.getStartTime()) && endTime.equals(libBooking.getEndTime()) && String.valueOf(startDate).equals(String.valueOf(libBooking.getStartDate()))) {
                    error = error +"This libraryBooking overlaps with an existing libraryBooking";
                }

            }
        }

        //Throw the erro messge if any

        error = error.trim();
        if (error.length() > 0) {
            throw new InvalidInputException(error);
        }
        //Create libraryBooking and save in the repository
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
        //Check if LibraryBooking exist
        if (libraryBookingRepository.findById(id) == null) {
            error = "libraryBooking does not exist";
        }
        error = error.trim();
        if (error.length() >0) {
            throw new InvalidInputException(error);
        }
        return libraryBookingRepository.findById(id);
    }
    //return all the libraryBooking made by a customer
    @Transactional
    public List<LibraryBooking> getLibraryBookingByCustomer(Customer customer) {
        String error = "";

        //Check if customer exists

        if (!customerRepository.existsByAccountId(customer.getAccountId())) {
            error = "Customer does not exist";
        }
        error = error.trim();
        if (error.length() >0) {
            throw new InvalidInputException(error);
        }
        return libraryBookingRepository.findByCustomer(customer);
    }
    // return all the libraryBooking
    @Transactional
    public List<LibraryBooking> getAllLibraryBooking() {
        return toList(libraryBookingRepository.findAll());
    }
    //Delete a libraryBooking with a specific Id
    @Transactional
    public LibraryBooking deleteLibraryBooking(int id) {
        String error = "";
        //CHeck if LibraryBooking exist
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
    //Delete all libraryBooking
    @Transactional
    public List<LibraryBooking> deleteAllLibraryBooking() {
        Iterable<LibraryBooking> libraryBookings = libraryBookingRepository.findAll();
        libraryBookingRepository.deleteAll();
        return toList(libraryBookings);
    }

    @Transactional
    public LibraryBooking updateLibraryBookingDateAndTime(int id, Date startDate, Date endDate, Time startTime, Time endTime) {
        String error = "";

        //Check if startDate is null

        if (startDate == null ) {
            error = error + "StartDate cannot be empty";
        }
        //Check if endDate is null
        if (endDate == null ) {
            error = error + "EndDate cannot be empty";
        }

        //Check if startTime is null
        if (startTime == null ) {
            error = error + "StartTime cannot be empty";
        }
        //CHeck if endTime is null
        if (endTime == null ) {
            error = error + "EndTime cannot be empty";
        }
        //Each libraryBooking must only be one day
        if(endDate!=null && startDate!=null) {
            if (!endDate.equals(startDate)) {
                error = error + "Each libraryBooking cannot be for multiple days";
            }
        }
        //Check for endTime before startTime
        if(error.length()==0) {
            if(endTime.before(startTime)) {
                error = error + "The end time of a libraryBooking cannot be before its start time";
            }
        }

        //Checks for overlaps with other libraryBookings
        if(error.length()==0) {
            for(LibraryBooking libBooking : libraryBookingRepository.findAll()) {
                if(startTime.before(libBooking.getEndTime()) && startTime.after(libBooking.getStartTime()) && String.valueOf(startDate).equals(String.valueOf(libBooking.getStartDate()))|| startTime.equals(libBooking.getStartTime())&& String.valueOf(startDate).equals(String.valueOf(libBooking.getStartDate()))) {
                    error = error +"This libraryBooking overlaps with an existing libraryBooking";
                }
            }
        }
        if(error.length()==0) {
            for(LibraryBooking libBooking : libraryBookingRepository.findAll()) {
                if(endTime.before(libBooking.getEndTime()) && endTime.after(libBooking.getStartTime()) && String.valueOf(startDate).equals(String.valueOf(libBooking.getStartDate()))|| endTime.equals(libBooking.getEndTime())&& String.valueOf(startDate).equals(String.valueOf(libBooking.getStartDate()))) {
                    error = error +"This libraryBooking overlaps with an existing libraryBooking";
                }
            }
        }
        if(error.length()==0) {
            for(LibraryBooking libBooking : libraryBookingRepository.findAll()) {
                if(startTime.before(libBooking.getStartTime()) && endTime.after(libBooking.getEndTime())&& String.valueOf(startDate).equals(String.valueOf(libBooking.getStartDate()))) {
                    error = error +"This libraryBooking overlaps with an existing libraryBooking";
                }
            }
        }
        if(error.length()==0) {
            for(LibraryBooking libBooking : libraryBookingRepository.findAll()) {
                if(startTime.after(libBooking.getStartTime()) && endTime.before(libBooking.getEndTime()) && String.valueOf(startDate).equals(String.valueOf(libBooking.getStartDate()))) {
                    error = error +"This libraryBooking overlaps with an existing libraryBooking";
                }
            }
        }
        //Check if LibraryBooking with same date and time
        if(error.length()==0) {
            for(LibraryBooking libBooking : libraryBookingRepository.findAll()) {
                if(startTime.equals(libBooking.getStartTime()) && endTime.equals(libBooking.getEndTime()) && String.valueOf(startDate).equals(String.valueOf(libBooking.getStartDate()))) {
                    error = error +"This libraryBooking overlaps with an existing libraryBooking";
                }

            }
        }
        //throw an error if any
        error = error.trim();
        if (error.length() >0) {
            throw new InvalidInputException(error);
        }
        //retrieves the existing libraryBooking and mmodifies the dates and times
        LibraryBooking libraryBooking = libraryBookingRepository.findById(id);
        libraryBooking.setEndDate(endDate);
        libraryBooking.setStartDate(startDate);
        libraryBooking.setStartTime(startTime);
        libraryBooking.setEndTime(endTime);
        libraryBookingRepository.save(libraryBooking);
        return libraryBooking;
    }
    //Update a libraryBooking customer
    @Transactional
    public LibraryBooking updateLibraryBookingCustomer(int id, Customer customer) {

        String error = "";
        //Check if libraryBooking exists
        if (libraryBookingRepository.findById(id) == null) {
            error = "librayBooking does not exist";
        }
        //check if customer is null
        if (customer == null ) {
            error = error + "Customer cannot be empty";
        }
        //throw an error if any
        error = error.trim();
        if (error.length() >0) {
            throw new InvalidInputException(error);
        }
        //retrives the libraryBooking and modifies the customer
        LibraryBooking libraryBooking = libraryBookingRepository.findById(id);
        libraryBooking.setCustomer(customer);
        libraryBookingRepository.save(libraryBooking);
        return libraryBooking;
    }

    //Helper method to convert iterable to an arraylist
    private <T> List<T> toList(Iterable<T> iterable) {
        List<T> resultList = new ArrayList<T>();
        for (T t : iterable) {
            resultList.add(t);
        }
        return resultList;
    }
    
}
