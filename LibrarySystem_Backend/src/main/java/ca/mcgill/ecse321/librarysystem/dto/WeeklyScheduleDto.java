package ca.mcgill.ecse321.librarysystem.dto;

import java.util.Set;

import ca.mcgill.ecse321.librarysystem.model.Shift;

public class WeeklyScheduleDto {
	private int weeklyScheduleId;
	private Set<Shift> shifts;
	
	public WeeklyScheduleDto() {
		
	}
	
	public WeeklyScheduleDto(int weeklyScheduleId) {
		this.weeklyScheduleId = weeklyScheduleId;
		this.shifts = null;
	}
	
	public WeeklyScheduleDto(int weeklyScheduleId, Set<Shift> shifts) {
		this.shifts = shifts;
		this.weeklyScheduleId = weeklyScheduleId;
	}
	
	public int getWeeklyScheduleId() {
		return weeklyScheduleId;
	}
	
	public Set<Shift> getShifts() {
		return shifts;
	}
	
	public void setShifts(Set<Shift> shifts) {
		this.shifts = shifts;
	}
}
