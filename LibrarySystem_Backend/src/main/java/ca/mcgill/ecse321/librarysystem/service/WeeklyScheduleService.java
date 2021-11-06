package ca.mcgill.ecse321.librarysystem.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.librarysystem.dao.ShiftRepository;
import ca.mcgill.ecse321.librarysystem.dao.WeeklyScheduleRepository;
import ca.mcgill.ecse321.librarysystem.model.Shift;
import ca.mcgill.ecse321.librarysystem.model.WeeklySchedule;


@Service
public class WeeklyScheduleService {
	@Autowired
	WeeklyScheduleRepository weeklyScheduleRepository;
	@Autowired
	ShiftRepository shiftRepository;
	
	@Transactional
	public WeeklySchedule createWeeklySchedule() {
		WeeklySchedule ws = new WeeklySchedule();
		weeklyScheduleRepository.save(ws);
		return ws;
	}
	
	@Transactional
	public WeeklySchedule updateWeeklyScheduleShifts(int wsId, Set<Shift> shiftsToSet) {
		for (Shift shift : shiftsToSet) {
			if (!shiftRepository.existsByShiftId(shift.getShiftId())) {
				throw new IllegalArgumentException("Shift(s) could not be found");
			}
		}
		
		if (!weeklyScheduleRepository.existsByWeeklyScheduleId(wsId)) {
			throw new IllegalArgumentException("Weekly schedule with provided id does not exist");
		}
		WeeklySchedule wsToUpdate = weeklyScheduleRepository.findByWeeklyScheduleId(wsId);
		wsToUpdate.setShifts(shiftsToSet);
		weeklyScheduleRepository.save(wsToUpdate);
		return wsToUpdate;	
	}
	
	@Transactional
	public WeeklySchedule deleteWeeklySchedule(int wsId) {
		if (!weeklyScheduleRepository.existsByWeeklyScheduleId(wsId)) {
			throw new IllegalArgumentException("Weekly schedule with provided id does not exist");
		}
		WeeklySchedule wsToDelete = weeklyScheduleRepository.findByWeeklyScheduleId(wsId);
		weeklyScheduleRepository.delete(wsToDelete);
		return wsToDelete;
	}
	
	@Transactional
	public List<WeeklySchedule> deleteAllWeeklySchedules() {
		List<WeeklySchedule> schedules = toList(weeklyScheduleRepository.findAll());
		weeklyScheduleRepository.deleteAll();
		return schedules;
	}
	
	@Transactional
	public WeeklySchedule getWeeklySchedule(int id) {
		if (!weeklyScheduleRepository.existsByWeeklyScheduleId(id)) {
			throw new IllegalArgumentException("Weekly schedule with provided id does not exist");
		}
		WeeklySchedule ws = weeklyScheduleRepository.findByWeeklyScheduleId(id);
		return ws;
	}
	
	@Transactional
	public List<WeeklySchedule> getAllWeeklySchedules() {
		return toList(weeklyScheduleRepository.findAll());
	}
	
	private <T> List<T> toList(Iterable<T> iterable){
		List<T> resultList = new ArrayList<T>();
		for (T t : iterable) {
			resultList.add(t);
		}
		return resultList;
	}
}
