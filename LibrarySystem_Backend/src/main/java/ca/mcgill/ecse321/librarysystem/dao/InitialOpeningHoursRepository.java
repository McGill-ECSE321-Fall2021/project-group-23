package ca.mcgill.ecse321.librarysystem.dao;

import java.sql.Time;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.librarysystem.model.OpeningsHours;
import ca.mcgill.ecse321.librarysystem.model.OpeningsHours.DayOfWeek;

@Repository
public class InitialOpeningHoursRepository {
    
    @Autowired
    EntityManager entityManager;

    @Transactional
    public OpeningsHours createOpeningsHours(DayOfWeek openingDay, Time startTime, Time endTime){
        OpeningsHours oh = new OpeningsHours();
        oh.setOpeningDay(openingDay);
        oh.setStartTime(startTime);
        oh.setEndTime(endTime);
        entityManager.persist(oh);
        return oh;
    }

    @Transactional
    public OpeningsHours getOpeningsHours(DayOfWeek dayOfWeek) {
        OpeningsHours oh = entityManager.find(OpeningsHours.class, dayOfWeek);
        return oh;
    }
}
