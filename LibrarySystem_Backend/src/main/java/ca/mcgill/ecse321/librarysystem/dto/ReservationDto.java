package ca.mcgill.ecse321.librarysystem.dto;

import java.sql.Date;

import ca.mcgill.ecse321.librarysystem.model.Customer;
import ca.mcgill.ecse321.librarysystem.model.Item;

public class ReservationDto {


    private Date startDate;
    private Date endDate;
    private Item item;
    private Customer customer;
    private int id;
    private boolean isCheckedout;
    
    public ReservationDto() {

    }

    public ReservationDto(Customer customer, Item item, Date startDate, Date endDate, int id, boolean isCheckedout) {
        this.customer = customer;
        this.startDate = startDate;
        this.endDate = endDate;
        this.item = item;
        this.id = id;
    }

    public Date getDtartDate() {
        return startDate;
    }

    public Date getEnddate() {
        return endDate;
    }
    public Customer getCustomer() {
        return customer;
    }
    public Item getItem() {
        return item;
    }
    public int getId() {
        return id;
    }
    public boolean getIsCheckedOut() {
        return isCheckedout;
    }

    
}
