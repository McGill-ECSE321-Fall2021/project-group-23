package ca.mcgill.ecse321.librarysystem.dao;

import java.sql.Date;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.librarysystem.model.Holiday;

@Repository
public class InitialHolidayRepository {
    
    @Autowired
    EntityManager entityManager;

    @Transactional
    public Holiday createHoliday(String name, Date startDate, Date endDate){
        Holiday h = new Holiday();
        h.setName(name);
        h.setStartDate(startDate);
        h.setEndDate(endDate);
        entityManager.persist(h);
        return h;
    }

    @Transactional
    public Holiday getHoliday(String name) {
        Holiday h = entityManager.find(Holiday.class, name);
        return h;
    }
}
