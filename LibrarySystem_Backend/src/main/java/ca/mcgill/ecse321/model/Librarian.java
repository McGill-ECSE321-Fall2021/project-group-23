/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.1.4607.2d2b84eb8 modeling language!*/

package ca\mcgill\ecse321\model;
import java.util.*;

// line 11 "../model.ump"
public class Librarian extends Account
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Librarian Associations
  private WeeklySchedule librarianSchedule;
  private Library library;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Librarian(String aFirstName, String aLastName, int aAccountId, String aPassword, WeeklySchedule aLibrarianSchedule, Library aLibrary)
  {
    super(aFirstName, aLastName, aAccountId, aPassword);
    if (aLibrarianSchedule == null || aLibrarianSchedule.getEmployee() != null)
    {
      throw new RuntimeException("Unable to create Librarian due to aLibrarianSchedule. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    librarianSchedule = aLibrarianSchedule;
    boolean didAddLibrary = setLibrary(aLibrary);
    if (!didAddLibrary)
    {
      throw new RuntimeException("Unable to create librarian due to library. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }

  public Librarian(String aFirstName, String aLastName, int aAccountId, String aPassword, Library aLibrary)
  {
    super(aFirstName, aLastName, aAccountId, aPassword);
    librarianSchedule = new WeeklySchedule(this);
    boolean didAddLibrary = setLibrary(aLibrary);
    if (!didAddLibrary)
    {
      throw new RuntimeException("Unable to create librarian due to library. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------
  /* Code from template association_GetOne */
  public WeeklySchedule getLibrarianSchedule()
  {
    return librarianSchedule;
  }
  /* Code from template association_GetOne */
  public Library getLibrary()
  {
    return library;
  }
  /* Code from template association_SetOneToMany */
  public boolean setLibrary(Library aLibrary)
  {
    boolean wasSet = false;
    if (aLibrary == null)
    {
      return wasSet;
    }

    Library existingLibrary = library;
    library = aLibrary;
    if (existingLibrary != null && !existingLibrary.equals(aLibrary))
    {
      existingLibrary.removeLibrarian(this);
    }
    library.addLibrarian(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    WeeklySchedule existingLibrarianSchedule = librarianSchedule;
    librarianSchedule = null;
    if (existingLibrarianSchedule != null)
    {
      existingLibrarianSchedule.delete();
    }
    Library placeholderLibrary = library;
    this.library = null;
    if(placeholderLibrary != null)
    {
      placeholderLibrary.removeLibrarian(this);
    }
    super.delete();
  }

}