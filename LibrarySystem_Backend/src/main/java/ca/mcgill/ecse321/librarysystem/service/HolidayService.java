package ca.mcgill.ecse321.librarysystem.service;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.librarysystem.dao.HolidayRepository;
import ca.mcgill.ecse321.librarysystem.model.Holiday;


@Service
public class HolidayService {
	
	@Autowired
	HolidayRepository holidayRepository;
	
	@Transactional
	public Holiday createHoliday(String name, Date startDate, Date endDate) {
		String error = "";
		if (name == null) {
			error = error + "holiday name cannot be empty! ";
		}
		if (startDate == null) {
			error = error + "start date cannot be empty! ";
		}
		if (endDate == null) {
			error = error + "end date cannot be empty! ";
		}
		if (endDate != null && startDate != null &&startDate.after(endDate)){
			error = error + "start date can't be after end date";
		}
		
		error = error.trim();
		if (error.length() > 0) {
			throw new InvalidInputException(error);
		}
		for (Holiday h: toList(holidayRepository.findAll())) {
			if (h.getName().equals(name)) {
				throw new InvalidInputException("There are already holiday for this day (Try updating).");
			}
		}
		Holiday holiday = new Holiday();
		holiday.setName(name);
		holiday.setStartDate(startDate);
		holiday.setEndDate(endDate);
		holidayRepository.save(holiday);
		return holiday;
	}
	@Transactional
	public Holiday getHoliday(String name) {
		String error = "";
		if (holidayRepository.findHolidayByName(name)==null) {
			error = error + "holiday not found";
		}
		
		error = error.trim();
		if (error.length() > 0) {
			throw new InvalidInputException(error);
		}
		Holiday holiday = holidayRepository.findHolidayByName(name);
		return holiday;
	}
	
	@Transactional
	public List<Holiday> getAllHolidays() {
		return toList(holidayRepository.findAll());
	}
	
	@Transactional
	public Holiday updateHolidayDates(String name, Date newStartDate, Date newEndDate) {
		String error = "";
		if (holidayRepository.findHolidayByName(name)==null) {
			error = error + "holiday not found";
		}
		if (newEndDate != null && newStartDate != null &&newStartDate.after(newEndDate)){
			error = error + "start date can't be after end date";
		}
		error = error.trim();
		if (error.length() > 0) {
			throw new InvalidInputException(error);
		}
		Holiday holiday = holidayRepository.findHolidayByName(name);
		holiday.setStartDate(newStartDate);
		holiday.setEndDate(newEndDate);
		holidayRepository.save(holiday);
		return holiday;
	}

	
	@Transactional
	public Holiday deleteHoliday(String name) { 
		String error = "";
		if (holidayRepository.findHolidayByName(name)==null) {
			error = error + "holiday not found";
		}
		error = error.trim();
		if (error.length() > 0) {
			throw new InvalidInputException(error);
		}
		Holiday holiday = holidayRepository.findHolidayByName(name);
		holidayRepository.delete(holiday);;
		return holiday;
	}
	
	@Transactional
	public List<Holiday> deleteAllHolidays(){
		Iterable<Holiday> holidays = holidayRepository.findAll();
		holidayRepository.deleteAll();
		return toList(holidays);
	}


	private <T> List<T> toList(Iterable<T> iterable){
		List<T> resultList = new ArrayList<T>();
		for (T t : iterable) {
			resultList.add(t);
		}
		return resultList;
	}
}
