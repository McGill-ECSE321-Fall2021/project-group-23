/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.1.4607.2d2b84eb8 modeling language!*/

package ca\mcgill\ecse321\model;
import java.util.*;
import java.sql.Time;

// line 102 "../model.ump"
public class WeeklySchedule
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //WeeklySchedule Associations
  private List<Shift> librarianShifts;
  private Librarian employee;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public WeeklySchedule(Librarian aEmployee)
  {
    librarianShifts = new ArrayList<Shift>();
    if (aEmployee == null || aEmployee.getLibrarianSchedule() != null)
    {
      throw new RuntimeException("Unable to create WeeklySchedule due to aEmployee. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    employee = aEmployee;
  }

  public WeeklySchedule(String aFirstNameForEmployee, String aLastNameForEmployee, int aAccountIdForEmployee, String aPasswordForEmployee, Library aLibraryForEmployee)
  {
    librarianShifts = new ArrayList<Shift>();
    employee = new Librarian(aFirstNameForEmployee, aLastNameForEmployee, aAccountIdForEmployee, aPasswordForEmployee, this, aLibraryForEmployee);
  }

  //------------------------
  // INTERFACE
  //------------------------
  /* Code from template association_GetMany */
  public Shift getLibrarianShift(int index)
  {
    Shift aLibrarianShift = librarianShifts.get(index);
    return aLibrarianShift;
  }

  public List<Shift> getLibrarianShifts()
  {
    List<Shift> newLibrarianShifts = Collections.unmodifiableList(librarianShifts);
    return newLibrarianShifts;
  }

  public int numberOfLibrarianShifts()
  {
    int number = librarianShifts.size();
    return number;
  }

  public boolean hasLibrarianShifts()
  {
    boolean has = librarianShifts.size() > 0;
    return has;
  }

  public int indexOfLibrarianShift(Shift aLibrarianShift)
  {
    int index = librarianShifts.indexOf(aLibrarianShift);
    return index;
  }
  /* Code from template association_GetOne */
  public Librarian getEmployee()
  {
    return employee;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfLibrarianShifts()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Shift addLibrarianShift(DayOfWeek aWorkingDay, Time aStartTime, Time aEndTime)
  {
    return new Shift(aWorkingDay, aStartTime, aEndTime, this);
  }

  public boolean addLibrarianShift(Shift aLibrarianShift)
  {
    boolean wasAdded = false;
    if (librarianShifts.contains(aLibrarianShift)) { return false; }
    WeeklySchedule existingLibrarianSchedule = aLibrarianShift.getLibrarianSchedule();
    boolean isNewLibrarianSchedule = existingLibrarianSchedule != null && !this.equals(existingLibrarianSchedule);
    if (isNewLibrarianSchedule)
    {
      aLibrarianShift.setLibrarianSchedule(this);
    }
    else
    {
      librarianShifts.add(aLibrarianShift);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeLibrarianShift(Shift aLibrarianShift)
  {
    boolean wasRemoved = false;
    //Unable to remove aLibrarianShift, as it must always have a librarianSchedule
    if (!this.equals(aLibrarianShift.getLibrarianSchedule()))
    {
      librarianShifts.remove(aLibrarianShift);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addLibrarianShiftAt(Shift aLibrarianShift, int index)
  {  
    boolean wasAdded = false;
    if(addLibrarianShift(aLibrarianShift))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfLibrarianShifts()) { index = numberOfLibrarianShifts() - 1; }
      librarianShifts.remove(aLibrarianShift);
      librarianShifts.add(index, aLibrarianShift);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveLibrarianShiftAt(Shift aLibrarianShift, int index)
  {
    boolean wasAdded = false;
    if(librarianShifts.contains(aLibrarianShift))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfLibrarianShifts()) { index = numberOfLibrarianShifts() - 1; }
      librarianShifts.remove(aLibrarianShift);
      librarianShifts.add(index, aLibrarianShift);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addLibrarianShiftAt(aLibrarianShift, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    for(int i=librarianShifts.size(); i > 0; i--)
    {
      Shift aLibrarianShift = librarianShifts.get(i - 1);
      aLibrarianShift.delete();
    }
    Librarian existingEmployee = employee;
    employee = null;
    if (existingEmployee != null)
    {
      existingEmployee.delete();
    }
  }

}