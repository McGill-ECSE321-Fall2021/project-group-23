package ca.mcgill.ecse321.librarysystem.dto;

import java.sql.Date;

public class ReservationDto {  

    /**
     * Author: Abdouallah Tahdi
     */

    private Date startDate;
    private Date endDate;
    private ItemDto itemDto;
    private CustomerDto customerDto;
    private int id;
    private boolean isCheckedout;
    
    public ReservationDto() {

    }

    public ReservationDto(CustomerDto customerDto, ItemDto itemDto, Date startDate, Date endDate, int id, boolean isCheckedout) {
        this.customerDto = customerDto;
        this.startDate = startDate;
        this.endDate = endDate;
        this.itemDto = itemDto;
        this.id = id;
    }

    public Date getDtartDate() {
        return startDate;
    }

    public Date getEnddate() {
        return endDate;
    }
    public CustomerDto getCustomer() {
        return customerDto;
    }
    public ItemDto getItem() {
        return itemDto;
    }
    public int getId() {
        return id;
    }
    public boolean getIsCheckedOut() {
        return isCheckedout;
    }

    
}
