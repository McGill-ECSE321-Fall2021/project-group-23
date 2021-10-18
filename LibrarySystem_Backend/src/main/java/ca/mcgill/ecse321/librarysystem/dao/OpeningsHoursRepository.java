package ca.mcgill.ecse321.librarysystem.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.librarysystem.model.OpeningsHours;
import ca.mcgill.ecse321.librarysystem.model.OpeningsHours.DayOfWeek;

public interface OpeningsHoursRepository extends CrudRepository<OpeningsHours, DayOfWeek> {
	OpeningsHours findOpeningsHoursByOpeningDay(DayOfWeek openingDay);
}
