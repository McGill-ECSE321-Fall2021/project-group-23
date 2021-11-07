package ca.mcgill.ecse321.librarysystem.dto;

import java.sql.Date;
import java.sql.Time;

import ca.mcgill.ecse321.librarysystem.model.Customer;

public class LibraryBookingDto {   

    /**
     * Author: Abdouallah Tahdi
     */

    private Date startDate;
    private Date endDate;
    private Time startTime;
    private Time endTime;
    private int id;
    private Customer customer;
    
    public LibraryBookingDto() {

    }

    public LibraryBookingDto(int id, Customer customer, Date startDate, Date endDate, Time startTime, Time endTime) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.id = id;
        this.customer = customer;
    }

    public int getId() {
        return id;
    }
    public Customer getCustomer() {
        return customer;
    }
    public Date getStartDate() {
        return startDate;
    }
    public Date getEndDate() {
        return endDate;
    }
    public Time getStartTime() {
        return startTime;
    }
    public Time getEndTime() {
        return endTime;
    }
    
}
