package ca.mcgill.ecse321.librarysystem.dto;

import java.sql.Date;
import java.sql.Time;

public class LibraryBookingDto {   

    /**
     * Author: Abdouallah Tahdi
     */

    private Date startDate;
    private Date endDate;
    private Time startTime;
    private Time endTime;
    private int id;
    private CustomerDto customerDto;
    
    public LibraryBookingDto() {

    }

    public LibraryBookingDto(int id, CustomerDto customerDto, Date startDate, Date endDate, Time startTime, Time endTime) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.id = id;
        this.customerDto = customerDto;
    }

    public int getId() {
        return id;
    }
    public CustomerDto getCustomer() {
        return customerDto;
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
