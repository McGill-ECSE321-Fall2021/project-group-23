package ca.mcgill.ecse321.librarysystem.model;

import java.sql.Time;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class OpeningsHours {
	public enum DayOfWeek {
		Monday, Tuesday, Wednesday, Thursday, Friday, Saturday, Sunday
	}

	private DayOfWeek openingDay;

	public void setOpeningDay(DayOfWeek value) {
		this.openingDay = value;
	}

	@Id
	public DayOfWeek getOpeningDay() {
		return this.openingDay;
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