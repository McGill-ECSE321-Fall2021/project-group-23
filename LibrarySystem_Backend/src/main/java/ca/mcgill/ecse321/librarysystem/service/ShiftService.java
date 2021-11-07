package ca.mcgill.ecse321.librarysystem.service;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.librarysystem.dao.OpeningsHoursRepository;
import ca.mcgill.ecse321.librarysystem.dao.ShiftRepository;
import ca.mcgill.ecse321.librarysystem.model.OpeningsHours;
import ca.mcgill.ecse321.librarysystem.model.Shift;
import ca.mcgill.ecse321.librarysystem.model.Shift.DayOfWeek;


@Service
public class ShiftService {
	@Autowired
	ShiftRepository shiftRepository;
	@Autowired
	OpeningsHoursRepository openingsHoursRepository;
	
	@Transactional
	public Shift createShift(DayOfWeek dayOfWeek, Time startTime, Time endTime) {
		// error checks for null inputs
		if (dayOfWeek == null) {
			throw new IllegalArgumentException("No day of week provided. Please select a day of week.");
		}
		if (startTime == null) {
			throw new IllegalArgumentException("No start time provided. Please select a start time.");
		}
		if (endTime == null) {
			throw new IllegalArgumentException("No end time provided. Please select an end time.");
		}
		
		// error check for start time after end time
		if (startTime.after(endTime)) {
			throw new IllegalArgumentException("Start time cannot be after end time.");
		}
		
		// error check for times outside of opening hours
		for (OpeningsHours oh : toList(openingsHoursRepository.findAll())) {
			if (startTime.before(oh.getStartTime()) || startTime.after(oh.getEndTime())) {
				throw new IllegalArgumentException("Start time cannot be outside of the library's opening hours.");
			}
			if (endTime.before(oh.getStartTime()) || endTime.after(oh.getEndTime())) {
				throw new IllegalArgumentException("End time cannot be outside of the library's opening hours.");
			}
			
		}
		
		Shift shift = new Shift();
		shift.setWorkingDay(dayOfWeek);
		shift.setStartTime(startTime);
		shift.setEndTime(endTime);
		shiftRepository.save(shift);
		return shift;
	}
	
	@Transactional
	public Shift updateShift(int id, DayOfWeek newDOW, Time newStartTime, Time newEndTime) {
		// check that shift exists first
		if (!shiftRepository.existsByShiftId(id)) {
			throw new IllegalArgumentException("Shift to update could not be found.");
		}
		
		// error checks for null inputs
		if (newDOW == null) {
			throw new IllegalArgumentException("No day of week provided. Please select a day of week.");
		}
		if (newStartTime == null) {
			throw new IllegalArgumentException("No start time provided. Please select a start time.");
		}
		if (newEndTime == null) {
			throw new IllegalArgumentException("No end time provided. Please select an end time.");
		}
		
		// error check for start time after end time
		if (newStartTime.after(newEndTime)) {
			throw new IllegalArgumentException("Start time cannot be after end time.");
		}
		
		// error check for times outside of opening hours
		for (OpeningsHours oh : toList(openingsHoursRepository.findAll())) {
			if (newStartTime.before(oh.getStartTime()) || newStartTime.after(oh.getEndTime())) {
				throw new IllegalArgumentException("Start time cannot be outside of the library's opening hours.");
			}
			if (newEndTime.before(oh.getStartTime()) || newEndTime.after(oh.getEndTime())) {
				throw new IllegalArgumentException("End time cannot be outside of the library's opening hours.");
			}
			
		}
		
		// Update shift's variables and save to repo.
		Shift shiftToUpdate = shiftRepository.findByShiftId(id);
		shiftToUpdate.setStartTime(newStartTime);
		shiftToUpdate.setEndTime(newEndTime);
		shiftToUpdate.setWorkingDay(newDOW);
		shiftRepository.save(shiftToUpdate);
		return shiftToUpdate;
	}
	
	@Transactional
	public Shift deleteShift(int shiftId) {
		if (!shiftRepository.existsByShiftId(shiftId)) {
			throw new IllegalArgumentException("Shift to delete could not be found.");
		}
		Shift shiftToDelete = shiftRepository.findByShiftId(shiftId);
		shiftRepository.delete(shiftToDelete);
		return shiftToDelete;
	}
	
	@Transactional
	public List<Shift> deleteAllShifts() {
		Iterable<Shift> allShifts = shiftRepository.findAll();
		shiftRepository.deleteAll();
		return toList(allShifts);
	}
	
	@Transactional
	public Shift getShift(int shiftId) {
		// check if the shift with that id exists
		if (!shiftRepository.existsByShiftId(shiftId)) {
			throw new IllegalArgumentException("Shift could not be found with provided id");
		}
		Shift s = shiftRepository.findByShiftId(shiftId);
		return s;
	}
	
	@Transactional
	public Shift getShift(DayOfWeek dow, Time startTime, Time endTime) {
		// error checks for null inputs
		if (dow == null) {
			throw new IllegalArgumentException("No day of week provided. Please select a day of week.");
		}
		if (startTime == null) {
			throw new IllegalArgumentException("No start time provided. Please select a start time.");
		}
		if (endTime == null) {
			throw new IllegalArgumentException("No end time provided. Please select an end time.");
		}
		
		// check if the shift with that day of week and times exists
		if (!shiftRepository.existsByWorkingDayAndStartTimeAndEndTime(dow, startTime, endTime)) {
			throw new IllegalArgumentException("Shift could not be found with provided inputs");
		}
		Shift s = shiftRepository.findByWorkingDayAndStartTimeAndEndTime(dow, startTime, endTime);
		return s;
	}
	
	@Transactional
	public List<Shift> getAllShifts() {
		return toList(shiftRepository.findAll());
	}
	
	private <T> List<T> toList(Iterable<T> iterable){
		List<T> resultList = new ArrayList<T>();
		for (T t : iterable) {
			resultList.add(t);
		}
		return resultList;
	}
}
