/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.1.4607.2d2b84eb8 modeling language!*/

package ca\mcgill\ecse321\model;
import java.util.*;
import java.sql.Date;

// line 60 "../model.ump"
public abstract class Item
{

  //------------------------
  // STATIC VARIABLES
  //------------------------

  private static Map<Integer, Item> itemsByItemId = new HashMap<Integer, Item>();

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Item Attributes
  private Status status;
  private boolean canBeBorrowed;
  private int itemId;
  private String title;

  //Item Associations
  private Library library;
  private Reservation reservation;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Item(Status aStatus, boolean aCanBeBorrowed, int aItemId, String aTitle, Library aLibrary)
  {
    status = aStatus;
    canBeBorrowed = aCanBeBorrowed;
    title = aTitle;
    if (!setItemId(aItemId))
    {
      throw new RuntimeException("Cannot create due to duplicate itemId. See http://manual.umple.org?RE003ViolationofUniqueness.html");
    }
    boolean didAddLibrary = setLibrary(aLibrary);
    if (!didAddLibrary)
    {
      throw new RuntimeException("Unable to create libraryItem due to library. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setStatus(Status aStatus)
  {
    boolean wasSet = false;
    status = aStatus;
    wasSet = true;
    return wasSet;
  }

  public boolean setCanBeBorrowed(boolean aCanBeBorrowed)
  {
    boolean wasSet = false;
    canBeBorrowed = aCanBeBorrowed;
    wasSet = true;
    return wasSet;
  }

  public boolean setItemId(int aItemId)
  {
    boolean wasSet = false;
    Integer anOldItemId = getItemId();
    if (anOldItemId != null && anOldItemId.equals(aItemId)) {
      return true;
    }
    if (hasWithItemId(aItemId)) {
      return wasSet;
    }
    itemId = aItemId;
    wasSet = true;
    if (anOldItemId != null) {
      itemsByItemId.remove(anOldItemId);
    }
    itemsByItemId.put(aItemId, this);
    return wasSet;
  }

  public boolean setTitle(String aTitle)
  {
    boolean wasSet = false;
    title = aTitle;
    wasSet = true;
    return wasSet;
  }

  public Status getStatus()
  {
    return status;
  }

  public boolean getCanBeBorrowed()
  {
    return canBeBorrowed;
  }

  public int getItemId()
  {
    return itemId;
  }
  /* Code from template attribute_GetUnique */
  public static Item getWithItemId(int aItemId)
  {
    return itemsByItemId.get(aItemId);
  }
  /* Code from template attribute_HasUnique */
  public static boolean hasWithItemId(int aItemId)
  {
    return getWithItemId(aItemId) != null;
  }

  public String getTitle()
  {
    return title;
  }
  /* Code from template association_GetOne */
  public Library getLibrary()
  {
    return library;
  }
  /* Code from template association_GetOne */
  public Reservation getReservation()
  {
    return reservation;
  }

  public boolean hasReservation()
  {
    boolean has = reservation != null;
    return has;
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
      existingLibrary.removeLibraryItem(this);
    }
    library.addLibraryItem(this);
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_SetOptionalOneToOne */
  public boolean setReservation(Reservation aNewReservation)
  {
    boolean wasSet = false;
    if (reservation != null && !reservation.equals(aNewReservation) && equals(reservation.getReservedItems()))
    {
      //Unable to setReservation, as existing reservation would become an orphan
      return wasSet;
    }

    reservation = aNewReservation;
    Item anOldReservedItems = aNewReservation != null ? aNewReservation.getReservedItems() : null;

    if (!this.equals(anOldReservedItems))
    {
      if (anOldReservedItems != null)
      {
        anOldReservedItems.reservation = null;
      }
      if (reservation != null)
      {
        reservation.setReservedItems(this);
      }
    }
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    itemsByItemId.remove(getItemId());
    Library placeholderLibrary = library;
    this.library = null;
    if(placeholderLibrary != null)
    {
      placeholderLibrary.removeLibraryItem(this);
    }
    Reservation existingReservation = reservation;
    reservation = null;
    if (existingReservation != null)
    {
      existingReservation.delete();
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "canBeBorrowed" + ":" + getCanBeBorrowed()+ "," +
            "itemId" + ":" + getItemId()+ "," +
            "title" + ":" + getTitle()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "status" + "=" + (getStatus() != null ? !getStatus().equals(this)  ? getStatus().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "library = "+(getLibrary()!=null?Integer.toHexString(System.identityHashCode(getLibrary())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "reservation = "+(getReservation()!=null?Integer.toHexString(System.identityHashCode(getReservation())):"null");
  }  
  //------------------------
  // DEVELOPER CODE - PROVIDED AS-IS
  //------------------------
  
  // line 63 "../model.ump"
  public enum Status 
  {
    Borrowed, Damaged, Available, Incoming
  }

  
}