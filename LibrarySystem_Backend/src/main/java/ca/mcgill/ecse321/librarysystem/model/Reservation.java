package ca.mcgill.ecse321.librarysystem.model;
import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Reservation
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
	private Date reservationStartDate;
	
	public Date getReservationStartDate() {
		return this.reservationStartDate;
	}
	public void setReservationStartDate(Date startDate) {
		this.reservationStartDate = startDate;
	}
	
	private Date reservationEndDate;
	
	public Date getReservationEndDate() {
		return this.reservationEndDate;
	}
	public void setReservationEndDate(Date endDate) {
		this.reservationEndDate = endDate;
	}
	private boolean isCheckedOut;
	
	public boolean getIsCheckedOut() {
		return this.isCheckedOut;
	}
	
	public void setIsCheckedOut(boolean checkout) {
		this.isCheckedOut = checkout;
	}

	private Item item;
	@OneToOne(optional = true)
	public Item getItem() {
		return this.item;
	}
	public void setItem(Item item) {
		this.item = item;
	}
	
	private Customer customer;
	
	@ManyToOne(optional = false)
	public Customer getCustomer() {
		return this.customer;
	}
	
	public void setCustomer(Customer Customer) {
		this.customer = Customer;
	}
	
}