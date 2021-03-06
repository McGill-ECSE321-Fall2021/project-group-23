package ca.mcgill.ecse321.librarysystem.model;
import java.sql.Date;
import java.sql.Time;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class LibraryBooking
{

    private int id;

    @Id
    @GeneratedValue(generator = "idGenerator")
    public int getId() {
        return this.id;
    }

    public void setId(int newId) {
        this.id = newId;
    }

    private Date startDate;

    public Date getStartDate() {
        return this.startDate;
    }

    public void setStartDate(Date newDate) {
        this.startDate = newDate;
    }
    private Date endDate;

    public Date getEndDate() {
        return this.endDate;
    }

    public void setEndDate(Date newDate) {
        this.endDate = newDate;
    }
    private Time startTime;

    public Time getStartTime() {
        return this.startTime;
    }

    public void setStartTime(Time newTime) {
        this.startTime = newTime;
    }
    private Time endTime;
    public Time getEndTime() {
        return this.endTime;
    }

    public void setEndTime(Time newTime) {
        this.endTime = newTime;
    }

    private Customer customer;

    @ManyToOne(optional = false)
    public Customer getCustomer() {
        return this.customer;
    }
    public void setCustomer(Customer customerr){
        this.customer = customerr;
    }
}