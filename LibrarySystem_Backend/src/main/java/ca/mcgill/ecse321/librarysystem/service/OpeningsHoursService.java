package ca.mcgill.ecse321.librarysystem.service;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.librarysystem.dao.OpeningsHoursRepository;
import ca.mcgill.ecse321.librarysystem.model.OpeningsHours;
import ca.mcgill.ecse321.librarysystem.model.OpeningsHours.DayOfWeek;

@Service
public class OpeningsHoursService {
	
	@Autowired
	OpeningsHoursRepository openingsHoursRepository;
	
	@Transactional
	public OpeningsHours createOpeningsHours(DayOfWeek day, Time startTime, Time endTime) {
		String error = "";
		if (day == null) {
			error = error + "Day of week name cannot be empty! ";
		}
		if (startTime == null) {
			error = error + "Start Time cannot be empty! ";
		}
		if (endTime == null) {
			error = error + "End Time cannot be empty! ";
		}
		if (endTime != null && startTime != null &&startTime.after(endTime)){
			error = error + "Start time can't be after end time";
		}
		error = error.trim();
		if (error.length() > 0) {
			throw new IllegalArgumentException(error);
		}
		for (OpeningsHours ohs : toList(openingsHoursRepository.findAll())) {
			if (ohs.getOpeningDay().equals(day)) {
				throw new IllegalArgumentException("There are already opening hours for this day (Try updating).");
			}
		}
		OpeningsHours oh = new OpeningsHours();
		oh.setOpeningDay(day);
		oh.setStartTime(startTime);
		oh.setEndTime(endTime);
		openingsHoursRepository.save(oh);
		return oh;
	}
	@Transactional
	public OpeningsHours getOpeningsHours(DayOfWeek day) {
		String error = "";
		if (openingsHoursRepository.findOpeningsHoursByOpeningDay(day)==null) {
			error = error + "Opening Hours not found";
		}
		error = error.trim();
		if (error.length() > 0) {
			throw new IllegalArgumentException(error);
		}
		OpeningsHours oh = openingsHoursRepository.findOpeningsHoursByOpeningDay(day);
		return oh;
	}
	
	@Transactional
	public List<OpeningsHours> getAllOpeningsHours() {
		return toList(openingsHoursRepository.findAll());
	}
	
	@Transactional
	public OpeningsHours updateOpeningsHours(DayOfWeek day, Time newStartTime, Time newEndTime) {
		String error = "";
		if (openingsHoursRepository.findOpeningsHoursByOpeningDay(day)==null) {
			error = error + "Openings hours not found";
		}
		if (newEndTime != null && newStartTime != null &&newStartTime.after(newEndTime)){
			error = error + "start time can't be after end time";
		}
		error = error.trim();
		if (error.length() > 0) {
			throw new IllegalArgumentException(error);
		}
		OpeningsHours oh = openingsHoursRepository.findOpeningsHoursByOpeningDay(day);
		oh.setStartTime(newStartTime);
		oh.setEndTime(newEndTime);
		openingsHoursRepository.save(oh);
		return oh;
	}
	
	@Transactional
	public OpeningsHours deleteOpeningsHours(DayOfWeek day) { 
		String error = "";
		if (openingsHoursRepository.findOpeningsHoursByOpeningDay(day)==null) {
			error = error + "Opening Hours not found";
		}
		error = error.trim();
		if (error.length() > 0) {
			throw new IllegalArgumentException(error);
		}
		OpeningsHours oh = openingsHoursRepository.findOpeningsHoursByOpeningDay(day);
		openingsHoursRepository.delete(oh);
		return oh;
	}
	
	@Transactional
	public List<OpeningsHours> deleteAllOpeningsHours(){
		Iterable<OpeningsHours> ohs = openingsHoursRepository.findAll();
		openingsHoursRepository.deleteAll();
		return toList(ohs);
	}

	private <T> List<T> toList(Iterable<T> iterable){
		List<T> resultList = new ArrayList<T>();
		for (T t : iterable) {
			resultList.add(t);
		}
		return resultList;
	}
}
