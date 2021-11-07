package ca.mcgill.ecse321.librarysystem.model;
import java.util.*;

import javax.persistence.Entity;
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
  
  private Set<Shift> shifts;
	
  @OneToMany(cascade={CascadeType.ALL})
  public Set<Shift> getShifts() {
	  return this.shifts;
  }
	
  public void setShifts(Set<Shift> newShifts) {
	  this.shifts = newShifts;
  }
}