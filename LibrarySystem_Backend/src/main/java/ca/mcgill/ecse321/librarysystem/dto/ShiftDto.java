package ca.mcgill.ecse321.librarysystem.dto;

import java.sql.Time;

import ca.mcgill.ecse321.librarysystem.model.Shift.DayOfWeek;

public class ShiftDto {
	
	private DayOfWeek dayOfWeek;
	private Time startTime;
	private Time endTime;
	private int shiftId;
	
	public ShiftDto() {
		
	}
	
	public ShiftDto(int shiftId) {
		this.dayOfWeek = null;
		this.startTime = null;
		this.endTime = null;
		this.shiftId = shiftId;
	}
	
	public ShiftDto(DayOfWeek dayOfWeek, Time startTime, Time endTime, int shiftId) {
		this.dayOfWeek = dayOfWeek;
		this.startTime = startTime;
		this.endTime = endTime;
		this.shiftId = shiftId;
	}
	
	public DayOfWeek getDayOfWeek() {
		return dayOfWeek;
	}
	
	public Time getStartTime() {
		return startTime;
	}
	
	public Time getEndTime() {
		return endTime;
	}
	
	public int getShiftId() {
		return shiftId;
	}
	
	public void setDayOfWeek(DayOfWeek dayOfWeek) {
		this.dayOfWeek = dayOfWeek;
	}
	
	public void setStartTime(Time startTime) {
		this.startTime = startTime;
	}
	
	public void setEndTime(Time endTime) {
		this.endTime = endTime;
	}
}
