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

import ca.mcgill.ecse321.librarysystem.dto.OpeningsHoursDto;
import ca.mcgill.ecse321.librarysystem.model.OpeningsHours;
import ca.mcgill.ecse321.librarysystem.model.OpeningsHours.DayOfWeek;
import ca.mcgill.ecse321.librarysystem.service.OpeningsHoursService;


@CrossOrigin(origins = "*")
@RestController
public class OpeningsHoursRestController {

	@Autowired
	private OpeningsHoursService service;
	
	@PostMapping(value = { "/openingsHours/{openingDay}", "/openingsHours/{openingDay}/" })
	public OpeningsHoursDto createOpeningsHours(@PathVariable("openingDay") DayOfWeek openingDay,@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.TIME, pattern = "HH:mm") LocalTime startTime,
			@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.TIME, pattern = "HH:mm") LocalTime endTime)
	throws IllegalArgumentException {
		OpeningsHours oh = service.createOpeningsHours(openingDay, Time.valueOf(startTime), Time.valueOf(endTime));
		return convertToDto(oh);
	}

	@GetMapping(value = { "/openingsHours", "/openingsHours/" })
	public List<OpeningsHoursDto> getAllOpeningsHours() {
		List<OpeningsHoursDto> openingsHoursDtos = new ArrayList<>();
		for (OpeningsHours openingsHours : service.getAllOpeningsHours()) {
			openingsHoursDtos.add(convertToDto(openingsHours));
		}
		return openingsHoursDtos;
	}

	@GetMapping(value = { "/openingsHours/{openingDay}", "/openingsHourss/{openingDay}/" })
	public OpeningsHoursDto getOpeningsHoursByName(@PathVariable("openingDay") DayOfWeek openingDay) throws IllegalArgumentException {
		return convertToDto(service.getOpeningsHours(openingDay));
	}
	
	@PutMapping(value = { "/updateOpeningsHoursTimes/{openingDay}", "/updateOpeningsHoursTimes/{openingDay}/" })
	public OpeningsHoursDto updateOpeningsHoursTimes(@PathVariable("openingDay") DayOfWeek openingDay,@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.TIME, pattern = "HH:mm") LocalTime newStartTime,
			@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.TIME, pattern = "HH:mm") LocalTime newEndTime)
	throws IllegalArgumentException {
		OpeningsHours oh = service.updateOpeningsHours(openingDay, Time.valueOf(newStartTime), Time.valueOf(newEndTime));
		return convertToDto(oh);
	}
	
	@DeleteMapping(value = {"/deleteOpeningsHours/{openingDay}","/deleteOpeningsHours/{openingDay}/"})
	public OpeningsHoursDto deleteOpeningsHours(@PathVariable("openingDay") DayOfWeek openingDay) {
		
		OpeningsHours oh = service.deleteOpeningsHours(openingDay);
		return convertToDto(oh);
	}
	
	@DeleteMapping(value = {"/deleteAllOpeningsHours", "/deleteAllOpeningsHours/"})
	public List<OpeningsHoursDto> deleteAllOpeningsHours() {
		List<OpeningsHours> openingsHours = service.deleteAllOpeningsHours();
		List<OpeningsHoursDto> openingsHoursDtos = new ArrayList<>();
		for (OpeningsHours oh : openingsHours) {
			openingsHoursDtos.add(convertToDto(oh));
		}
		return openingsHoursDtos;
	}


	private OpeningsHoursDto convertToDto(OpeningsHours oh) {
		if (oh == null) {
			throw new IllegalArgumentException("There is no such opening Hours!");
		}
		OpeningsHoursDto openingsHoursDto = new OpeningsHoursDto(oh.getOpeningDay(),oh.getStartTime(),oh.getEndTime());
		return openingsHoursDto;
	}

}