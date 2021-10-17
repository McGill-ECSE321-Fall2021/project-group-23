package ca.mcgill.ecse321.librarysystem.model;
import java.sql.Time;

@Entity
public class OpeningsHours
{
	public enum DayOfWeek { Monday, Tuesday, Wednesday, Thursday, Friday, Saturday, Sunday }
	
	private DayOfWeek openingDate;
	public void setOpeningDate(DayOfWeek value){
		this.openingDate = value;
	}
@Id
	public DayOfWeek getOpeningDate{
	return this.openingDate;
	}

	private Time startTime;
	public void setStartTime(Time value) {
	this.startTime = value;
	    }
	public Time getStartTime() {
	return this.startTime;
	    }
	
	private Time endTime;
	public void setEndTime(Time value) {
	this.endTime = value;
	    }
	public Time getEndTime() {
	return this.endTime;
	       }
}