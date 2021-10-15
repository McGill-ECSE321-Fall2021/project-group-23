/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.1.4607.2d2b84eb8 modeling language!*/

package ca\mcgill\ecse321\model;
import java.util.*;

// line 78 "../model.ump"
public class Album extends Item
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Album(Status aStatus, boolean aCanBeBorrowed, int aItemId, String aTitle, Library aLibrary)
  {
    super(aStatus, aCanBeBorrowed, aItemId, aTitle, aLibrary);
  }

  //------------------------
  // INTERFACE
  //------------------------

  public void delete()
  {
    super.delete();
  }

}