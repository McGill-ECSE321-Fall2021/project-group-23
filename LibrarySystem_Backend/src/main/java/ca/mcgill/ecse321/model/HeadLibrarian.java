/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.1.4607.2d2b84eb8 modeling language!*/

package ca\mcgill\ecse321\model;
import java.util.*;

// line 16 "../model.ump"
public class HeadLibrarian extends Librarian
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //HeadLibrarian Associations
  private Library library;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public HeadLibrarian(String aFirstName, String aLastName, int aAccountId, String aPassword, WeeklySchedule aLibrarianSchedule, Library aLibrary, Library aLibrary)
  {
    super(aFirstName, aLastName, aAccountId, aPassword, aLibrarianSchedule, aLibrary);
    boolean didAddLibrary = setLibrary(aLibrary);
    if (!didAddLibrary)
    {
      throw new RuntimeException("Unable to create headLibrarian due to library. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------
  /* Code from template association_GetOne */
  public Library getLibrary()
  {
    return library;
  }
  /* Code from template association_SetOneToOptionalOne */
  public boolean setLibrary(Library aNewLibrary)
  {
    boolean wasSet = false;
    if (aNewLibrary == null)
    {
      //Unable to setLibrary to null, as headLibrarian must always be associated to a library
      return wasSet;
    }
    
    HeadLibrarian existingHeadLibrarian = aNewLibrary.getHeadLibrarian();
    if (existingHeadLibrarian != null && !equals(existingHeadLibrarian))
    {
      //Unable to setLibrary, the current library already has a headLibrarian, which would be orphaned if it were re-assigned
      return wasSet;
    }
    
    Library anOldLibrary = library;
    library = aNewLibrary;
    library.setHeadLibrarian(this);

    if (anOldLibrary != null)
    {
      anOldLibrary.setHeadLibrarian(null);
    }
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    Library existingLibrary = library;
    library = null;
    if (existingLibrary != null)
    {
      existingLibrary.setHeadLibrarian(null);
    }
    super.delete();
  }

}