package ca.mcgill.ecse321.librarysystem.model;

import java.sql.Time;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Shift {
	// list containing days of the week
	public enum DayOfWeek {
		Monday, Tuesday, Wednesday, Thursday, Friday, Saturday, Sunday
	}

	private int shiftId;
	@Id
	@GeneratedValue(generator = "idGenerator")
	public int getShiftId() {
		return this.shiftId;
	}
	public void setShiftId(int id) {
		this.shiftId = id;
	}

	// variable for working day of librarian
	private DayOfWeek workingDay;

	public DayOfWeek getWorkingDay() {
		return this.workingDay;
	}

	public void setWorkingDay(DayOfWeek dayOfWeek) {
		this.workingDay = dayOfWeek;
	}

	// variables for times shift begins and ends
	private Time startTime;

	public Time getStartTime() {
		return this.startTime;
	}

	public void setStartTime(Time startTime) {
		this.startTime = startTime;
	}

	private Time endTime;

	public Time getEndTime() {
		return this.endTime;
	}

	public void setEndTime(Time endTime) {
		this.startTime = endTime;
	}

}