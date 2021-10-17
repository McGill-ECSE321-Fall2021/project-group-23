package ca.mcgill.ecse321.librarysystem.dao;

import java.util.Set;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.librarysystem.model.WeeklySchedule;
import ca.mcgill.ecse321.librarysystem.model.Shift;

@Repository
public class LibrarianRepository {
	@Autowired
	EntityManager entityManager;
	
	@Transactional
	public WeeklySchedule createSchedule(int scheduleId, Set<Shift> shiftsToAdd) {
		WeeklySchedule newSchedule = new WeeklySchedule();
		newSchedule.setWeeklyScheduleId(scheduleId);
		newSchedule.setLibrarianShifts(shiftsToAdd);
		entityManager.persist(newSchedule);
		return newSchedule;
	}
	
	@Transactional
	public WeeklySchedule getSchedule(int scheduleId) {
		WeeklySchedule schedule = entityManager.find(WeeklySchedule.class, scheduleId);
		return schedule;
	}
}
