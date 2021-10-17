package ca.mcgill.ecse321.librarysystem.model;
import java.util.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Librarian extends Account
{
	private WeeklySchedule librarianSchedule;
	@OneToOne
	public WeeklySchedule getLibrarianSchedule() {
		return this.librarianSchedule;
	}
	public void setLibrarianSchedule(WeeklySchedule newSchedule) {
		this.librarianSchedule = newSchedule;
	}
	
}