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
    private int customerId;
    
    public LibraryBookingDto() {

    }

    public LibraryBookingDto(int id, int customerId, Date startDate, Date endDate, Time startTime, Time endTime) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.id = id;
        this.customerId = customerId;
    }

    public int getId() {
        return id;
    }
    public int getCustomerId() {
        return customerId;
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
