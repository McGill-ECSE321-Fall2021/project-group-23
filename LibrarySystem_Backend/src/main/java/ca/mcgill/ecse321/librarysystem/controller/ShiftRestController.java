package ca.mcgill.ecse321.librarysystem.controller;

import java.sql.Time;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ca.mcgill.ecse321.librarysystem.dto.ShiftDto;
import ca.mcgill.ecse321.librarysystem.model.Shift;
import ca.mcgill.ecse321.librarysystem.model.Shift.DayOfWeek;
import ca.mcgill.ecse321.librarysystem.service.ShiftService;

@CrossOrigin(origins = "*")
@RestController
public class ShiftRestController {
	@Autowired
	private ShiftService shiftService;
	
	@GetMapping(value = {"/shifts", "/shifts/"})
	public List<ShiftDto> getAllShifts() {
		List<ShiftDto> shiftDtos = new ArrayList<>();
		for (Shift shift : shiftService.getAllShifts()) {
			shiftDtos.add(convertToDto(shift));
		}
		return shiftDtos;
	}
	
	@GetMapping(value = { "/shift/{shiftId}", "/shift/{shiftId}/" })
	public ShiftDto getShiftById(@PathVariable("shiftId") int shiftId) throws IllegalArgumentException{
		return convertToDto(shiftService.getShift(shiftId));
	}
	
	@GetMapping(value = { "/shift/{dayOfWeek}/{startTime}/{endTime}", "/shift/{dayOfWeek}/{startTime}/{endTime}/" })
	public ShiftDto getShiftByDayAndTimes(@PathVariable("dayOfWeek") DayOfWeek dayOfWeek, @PathVariable("startTime") Time startTime, @PathVariable("endTime") Time endTime) {
		return convertToDto(shiftService.getShift(dayOfWeek, startTime, endTime));
	}
	
	@PostMapping(value = { "/createShift/{dayOfWeek}/{startTime}/{endTime}", "/createShift/{dayOfWeek}/{startTime}/{endTime}/"})
	public ShiftDto createShift(@PathVariable("dayOfWeek") DayOfWeek dayOfWeek, 
			@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.TIME, pattern = "HH:mm") LocalTime startTime,
			@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.TIME, pattern = "HH:mm") LocalTime endTime)
			throws IllegalArgumentException{
		Shift shift = shiftService.createShift(dayOfWeek, Time.valueOf(startTime), Time.valueOf(endTime));
		return convertToDto(shift);
	}
	
	@PutMapping(value = { "/updateShift/{shiftId}/{dayOfWeek}/{startTime}/{endTime}", "/updateShift/{shiftId}/{dayOfWeek}/{startTime}/{endTime}/" })
	public ShiftDto updateShift(@PathVariable("shiftId") int shiftId,
			@PathVariable("dayOfWeek") DayOfWeek dayOfWeek, 
			@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.TIME, pattern = "HH:mm") LocalTime startTime,
			@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.TIME, pattern = "HH:mm") LocalTime endTime)
			throws IllegalArgumentException{
		Shift shift = shiftService.updateShift(shiftId, dayOfWeek, Time.valueOf(startTime), Time.valueOf(endTime));
		return convertToDto(shift);
	}
	
	@DeleteMapping(value = { "/deleteShift/{shiftId}", "/deleteShift/{shiftId}/" })
	public ShiftDto deleteShiftById(@PathVariable("shiftId") int shiftId) throws IllegalArgumentException {
		Shift shift = shiftService.deleteShift(shiftId);
		return convertToDto(shift);
	}
	
	@DeleteMapping(value = { "/deleteAllShifts", "/deleteAllShifts/" })
	public List<ShiftDto> deleteAllShifts() {
		List<ShiftDto> shiftDtos = new ArrayList<>();
		for (Shift s : shiftService.deleteAllShifts()) {
			shiftDtos.add(convertToDto(s));
		}
		return shiftDtos;
	}
	
	private ShiftDto convertToDto(Shift s) {
		if (s == null) {
			throw new IllegalArgumentException("There is no such Shift!");
		}
		ShiftDto shiftDto = new ShiftDto(s.getWorkingDay(), s.getStartTime(), s.getEndTime(), s.getShiftId());
		return shiftDto;
	}
	
}
