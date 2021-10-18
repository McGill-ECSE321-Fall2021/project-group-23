package ca.mcgill.ecse321.librarysystem.model;
import java.util.*;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.CascadeType;

@Entity
public class WeeklySchedule
{

  private int weeklyScheduleId;

  @Id
  @GeneratedValue(generator = "idGenerator")
  public int getWeeklyScheduleId() {
    return this.weeklyScheduleId;
  }
  public void setWeeklyScheduleId(int id) {
    this.weeklyScheduleId = id;
  }
  
	private Set<Shift> librarianShifts;
	
	@OneToMany(cascade={CascadeType.ALL})
	public Set<Shift> getLibrarianShifts() {
		return this.librarianShifts;
	}
	
	public void setLibrarianShifts(Set<Shift> newShifts) {
		this.librarianShifts = newShifts;
	}
	
	/*private Librarian employee;
	@Id
	public Librarian getEmployee() {
		return this.employee;
	}
	public void setEmployee(Librarian newEmployee) {
		this.employee = newEmployee;
	}*/
}