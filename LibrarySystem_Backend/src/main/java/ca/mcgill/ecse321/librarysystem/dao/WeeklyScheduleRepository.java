package ca.mcgill.ecse321.librarysystem.dao;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Time;

import ca.mcgill.ecse321.librarysystem.model.Shift;
import ca.mcgill.ecse321.librarysystem.model.Shift.DayOfWeek;

@Repository
public class WeeklyScheduleRepository {
	@Autowired
	EntityManager entityManager;
	
	@Transactional
	public Shift createShift(int shiftId, DayOfWeek dayOfWeek, Time startTime, Time endTime) {
		Shift newShift = new Shift();
		newShift.setShiftId(shiftId);
		newShift.setWorkingDay(dayOfWeek);
		newShift.setStartTime(startTime);
		newShift.setEndTime(endTime);
		entityManager.persist(newShift);
		return newShift;
	}
	
	@Transactional
	public Shift getShift(int shiftId) {
		Shift shift = entityManager.find(Shift.class, shiftId);
		return shift;
	}
}
