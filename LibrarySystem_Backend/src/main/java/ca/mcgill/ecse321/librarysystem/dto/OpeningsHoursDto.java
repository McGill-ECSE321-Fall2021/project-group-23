package ca.mcgill.ecse321.librarysystem.dto;

import java.sql.Time;

import ca.mcgill.ecse321.librarysystem.model.OpeningsHours.DayOfWeek;

public class OpeningsHoursDto {

	private DayOfWeek openingDay;
	private Time startTime;
	private Time endTime;

	public OpeningsHoursDto() {
	}

	public OpeningsHoursDto(DayOfWeek openingDay) {
		this(openingDay, Time.valueOf("13:59:59"), Time.valueOf("23:59:59"));
	}

	public OpeningsHoursDto(DayOfWeek openingDay, Time startTime, Time endTime) {
		this.openingDay = openingDay;
		this.startTime = startTime;
		this.endTime = endTime;
	}

	public DayOfWeek getOpeningDay() {
		return openingDay;
	}

	public Time getStartTime() {
		return startTime;
	}

	public Time getEndTime() {
		return endTime;
	}

}
