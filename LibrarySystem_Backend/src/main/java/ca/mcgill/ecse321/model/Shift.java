/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.1.4607.2d2b84eb8 modeling language!*/

package ca\mcgill\ecse321\model;
import java.sql.Time;

// line 106 "../model.ump"
public class Shift
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Shift Attributes
  private DayOfWeek workingDay;
  private Time startTime;
  private Time endTime;

  //Shift Associations
  private WeeklySchedule librarianSchedule;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Shift(DayOfWeek aWorkingDay, Time aStartTime, Time aEndTime, WeeklySchedule aLibrarianSchedule)
  {
    workingDay = aWorkingDay;
    startTime = aStartTime;
    endTime = aEndTime;
    boolean didAddLibrarianSchedule = setLibrarianSchedule(aLibrarianSchedule);
    if (!didAddLibrarianSchedule)
    {
      throw new RuntimeException("Unable to create librarianShift due to librarianSchedule. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setWorkingDay(DayOfWeek aWorkingDay)
  {
    boolean wasSet = false;
    workingDay = aWorkingDay;
    wasSet = true;
    return wasSet;
  }

  public boolean setStartTime(Time aStartTime)
  {
    boolean wasSet = false;
    startTime = aStartTime;
    wasSet = true;
    return wasSet;
  }

  public boolean setEndTime(Time aEndTime)
  {
    boolean wasSet = false;
    endTime = aEndTime;
    wasSet = true;
    return wasSet;
  }

  public DayOfWeek getWorkingDay()
  {
    return workingDay;
  }

  public Time getStartTime()
  {
    return startTime;
  }

  public Time getEndTime()
  {
    return endTime;
  }
  /* Code from template association_GetOne */
  public WeeklySchedule getLibrarianSchedule()
  {
    return librarianSchedule;
  }
  /* Code from template association_SetOneToMany */
  public boolean setLibrarianSchedule(WeeklySchedule aLibrarianSchedule)
  {
    boolean wasSet = false;
    if (aLibrarianSchedule == null)
    {
      return wasSet;
    }

    WeeklySchedule existingLibrarianSchedule = librarianSchedule;
    librarianSchedule = aLibrarianSchedule;
    if (existingLibrarianSchedule != null && !existingLibrarianSchedule.equals(aLibrarianSchedule))
    {
      existingLibrarianSchedule.removeLibrarianShift(this);
    }
    librarianSchedule.addLibrarianShift(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    WeeklySchedule placeholderLibrarianSchedule = librarianSchedule;
    this.librarianSchedule = null;
    if(placeholderLibrarianSchedule != null)
    {
      placeholderLibrarianSchedule.removeLibrarianShift(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "workingDay" + "=" + (getWorkingDay() != null ? !getWorkingDay().equals(this)  ? getWorkingDay().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "startTime" + "=" + (getStartTime() != null ? !getStartTime().equals(this)  ? getStartTime().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "endTime" + "=" + (getEndTime() != null ? !getEndTime().equals(this)  ? getEndTime().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "librarianSchedule = "+(getLibrarianSchedule()!=null?Integer.toHexString(System.identityHashCode(getLibrarianSchedule())):"null");
  }  
  //------------------------
  // DEVELOPER CODE - PROVIDED AS-IS
  //------------------------
  
  // line 108 "../model.ump"
  public enum DayOfWeek 
  {
    Monday, Tuesday, Wednesday, Thursday, Friday, Saturday, Sunday
  }

  
}