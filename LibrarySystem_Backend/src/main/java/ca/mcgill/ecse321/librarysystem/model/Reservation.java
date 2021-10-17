package ca.mcgill.ecse321.librarysystem.model;
import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Reservation
{
	private int id;
	
	@Id
	public int getid() {
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
	
	@OneToMany
	public Item getItem() {
		return this.item;
	}
	
	
	public void setItem(Item item) {
		this.item = item;
	}
	
	private User user;
	
	@ManyToOne(optional = false)
	public User getUser() {
		return this.user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
	
}