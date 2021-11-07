package ca.mcgill.ecse321.librarysystem.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import ca.mcgill.ecse321.librarysystem.dto.WeeklyScheduleDto;
import ca.mcgill.ecse321.librarysystem.model.WeeklySchedule;
import ca.mcgill.ecse321.librarysystem.service.ShiftService;
import ca.mcgill.ecse321.librarysystem.service.WeeklyScheduleService;

@CrossOrigin(origins = "*")
@RestController
public class WeeklyScheduleRestController {
	@Autowired 
	WeeklyScheduleService weeklyScheduleService;
	@Autowired
	ShiftService shiftService;
	
	// Get all schedules in database
	@GetMapping(value = { "/getAllWeeklySchedules", "/getAllWeeklySchedules/" })
	public List<WeeklyScheduleDto> getAllWeeklySchedules() {
		List<WeeklyScheduleDto> weeklyScheduleDtos = new ArrayList<>();
		for (WeeklySchedule weeklySchedule : weeklyScheduleService.getAllWeeklySchedules()) {
			weeklyScheduleDtos.add(convertToDto(weeklySchedule));
		}
		return weeklyScheduleDtos;
	}
	
	// Get a schedule by id
	@GetMapping(value = { "/getWeeklyScheduleById/{weeklyScheduleId}", "/getWeeklyScheduleById/{weeklyScheduleId}/" })
	public WeeklyScheduleDto getWeeklyScheduleById(@PathVariable("weeklyScheduleId") int weeklyScheduleId)
	throws IllegalArgumentException{
		return convertToDto(weeklyScheduleService.getWeeklySchedule(weeklyScheduleId));
	}
	
	// Create a new weekly schedule
	@PostMapping(value = { "/createWeeklySchedule", "/createWeeklySchedule/" })
	public WeeklyScheduleDto createWeeklySchedule() {
		return convertToDto(weeklyScheduleService.createWeeklySchedule());
	}
	
	// Update a schedule with new shifts
	@PutMapping(value = { "/updateWeeklyScheduleShifts/{scheduleId}/{shiftsToSet}", "/updateWeeklyScheduleShifts/{scheduleId}/{shiftsToSet}/" })
	public WeeklyScheduleDto updateScheduleShifts(@PathVariable("scheduleId") int scheduleId, @PathVariable("shiftsToSet") List<Integer> shiftsToSet)
	throws IllegalArgumentException{
		return convertToDto(weeklyScheduleService.updateWeeklyScheduleShifts(scheduleId, shiftsToSet));
	}
	
	// Delete a schedule by id
	@DeleteMapping(value = { "/deleteWeeklySchedule/{weeklyScheduleId}", "/deleteWeeklySchedule/{weeklyScheduleId}/" })
	public WeeklyScheduleDto deleteWeeklyScheduleById(@PathVariable("weeklyScheduleId") int weeklyScheduleId)
	throws IllegalArgumentException{
		return convertToDto(weeklyScheduleService.deleteWeeklySchedule(weeklyScheduleId));
	}
	
	// Delete all schedules
	@DeleteMapping(value = { "/deleteAllWeeklySchedules", "/deleteAllWeeklySchedules/" })
	public List<WeeklyScheduleDto> deleteAllWeeklySchedules() {
		List<WeeklyScheduleDto> weeklyScheduleDtos = new ArrayList<>();
		for (WeeklySchedule weeklySchedule : weeklyScheduleService.deleteAllWeeklySchedules()) {
			weeklyScheduleDtos.add(convertToDto(weeklySchedule));
		}
		return weeklyScheduleDtos;
	}
	
	// Convert a weekly schedule to DTO
	private WeeklyScheduleDto convertToDto(WeeklySchedule ws) {
		if (ws == null) {
			throw new IllegalArgumentException("There is no such Weekly Schedule!");
		}
		WeeklyScheduleDto weeklyScheduleDto = new WeeklyScheduleDto(ws.getWeeklyScheduleId(), ws.getShifts());
		return weeklyScheduleDto;
	}
	
}
