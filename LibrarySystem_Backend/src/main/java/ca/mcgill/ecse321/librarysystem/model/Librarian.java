package ca.mcgill.ecse321.librarysystem.model;
import java.util.*;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.OneToOne;
import javax.persistence.InheritanceType;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "LIBRARIAN_TYPE")
public class Librarian extends Account
{
	private WeeklySchedule librarianSchedule;
	@OneToOne(optional=true)
	public WeeklySchedule getLibrarianSchedule() {
		return this.librarianSchedule;
	}
	public void setLibrarianSchedule(WeeklySchedule newSchedule) {
		this.librarianSchedule = newSchedule;
	}
	
}