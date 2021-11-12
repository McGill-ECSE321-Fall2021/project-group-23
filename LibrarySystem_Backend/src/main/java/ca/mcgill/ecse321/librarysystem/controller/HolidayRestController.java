package ca.mcgill.ecse321.librarysystem.controller;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ca.mcgill.ecse321.librarysystem.dto.HolidayDto;
import ca.mcgill.ecse321.librarysystem.model.Holiday;
import ca.mcgill.ecse321.librarysystem.service.HolidayService;

@CrossOrigin(origins = "*")
@RestController
public class HolidayRestController {

	@Autowired
	private HolidayService service;
	

	@PostMapping(value = { "/createHoliday/{name}", "/createHoliday/{name}/" })
	public HolidayDto createHoliday(@PathVariable("name") String name, @RequestParam String startDate,@RequestParam String endDate)
	throws IllegalArgumentException {
		Holiday holiday = service.createHoliday(name, Date.valueOf(startDate), Date.valueOf(endDate));
		return convertToDto(holiday);
	}

	@GetMapping(value = { "/getAllHolidays", "/getAllHolidays/" })
	public List<HolidayDto> getAllHolidays() {
		List<HolidayDto> holidayDtos = new ArrayList<>();
		for (Holiday holiday : service.getAllHolidays()) {
			holidayDtos.add(convertToDto(holiday));
		}
		return holidayDtos;
	}

	@GetMapping(value = { "/getHolidayByName/{name}", "/getHolidayByName/{name}/" })
	public HolidayDto getHolidayByName(@PathVariable("name") String name) throws IllegalArgumentException {
		return convertToDto(service.getHoliday(name));
	}
	
	@PutMapping(value = { "/updateHolidayDates/{name}", "/updateHolidayDate/{name}/" })
	public HolidayDto updateHolidayDates(@PathVariable("name") String name, @RequestParam String newStartDate,@RequestParam String newEndDate)
	throws IllegalArgumentException {
		Holiday holiday = service.updateHolidayDates(name, Date.valueOf(newStartDate), Date.valueOf(newEndDate));
		return convertToDto(holiday);
	}
	
	@DeleteMapping(value = {"/deleteHoliday/{name}","/deleteHoliday/{name}/"})
	public HolidayDto deleteHoliday(@PathVariable("name") String name) {
		
		Holiday holiday = service.deleteHoliday(name);
		return convertToDto(holiday);
	}
	
	@DeleteMapping(value = {"/deleteAllHolidays", "/deleteAllHolidays/"})
	public List<HolidayDto> deleteAllHolidays() {
		List<Holiday> holidays = service.deleteAllHolidays();
		List<HolidayDto> holidayDtos = new ArrayList<>();
		for (Holiday holiday : holidays) {
			holidayDtos.add(convertToDto(holiday));
		}
		return holidayDtos;
	}


	private HolidayDto convertToDto(Holiday h) {
		if (h == null) {
			throw new IllegalArgumentException("There is no such Holiday!");
		}
		HolidayDto holidayDto = new HolidayDto(h.getName(),h.getStartDate(),h.getEndDate());
		return holidayDto;
	}

}
