package ca.mcgill.ecse321.librarysystem.dto;

import java.util.Set;

public class WeeklyScheduleDto {
	private int weeklyScheduleId;
	private Set<ShiftDto> shifts;
	
	public WeeklyScheduleDto() {
		
	}
	
	public WeeklyScheduleDto(int weeklyScheduleId) {
		this.weeklyScheduleId = weeklyScheduleId;
		this.shifts = null;
	}
	
	public WeeklyScheduleDto(int weeklyScheduleId, Set<ShiftDto> shifts) {
		this.shifts = shifts;
		this.weeklyScheduleId = weeklyScheduleId;
	}
	
	public int getWeeklyScheduleId() {
		return weeklyScheduleId;
	}
	
	public Set<ShiftDto> getShifts() {
		return shifts;
	}
	
	public void setShifts(Set<ShiftDto> shifts) {
		this.shifts = shifts;
	}
}
