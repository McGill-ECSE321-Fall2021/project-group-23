/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.1.4607.2d2b84eb8 modeling language!*/

package ca\mcgill\ecse321\model;
import java.sql.Date;

// line 91 "../model.ump"
public class Reservation
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Reservation Attributes
  private Date reservationStartDate;
  private Date reservationEndDate;
  private boolean checkedOut;

  //Reservation Associations
  private User borrower;
  private Item reservedItems;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Reservation(Date aReservationStartDate, Date aReservationEndDate, boolean aCheckedOut, User aBorrower, Item aReservedItems)
  {
    reservationStartDate = aReservationStartDate;
    reservationEndDate = aReservationEndDate;
    checkedOut = aCheckedOut;
    boolean didAddBorrower = setBorrower(aBorrower);
    if (!didAddBorrower)
    {
      throw new RuntimeException("Unable to create userReservation due to borrower. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    boolean didAddReservedItems = setReservedItems(aReservedItems);
    if (!didAddReservedItems)
    {
      throw new RuntimeException("Unable to create reservation due to reservedItems. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setReservationStartDate(Date aReservationStartDate)
  {
    boolean wasSet = false;
    reservationStartDate = aReservationStartDate;
    wasSet = true;
    return wasSet;
  }

  public boolean setReservationEndDate(Date aReservationEndDate)
  {
    boolean wasSet = false;
    reservationEndDate = aReservationEndDate;
    wasSet = true;
    return wasSet;
  }

  public boolean setCheckedOut(boolean aCheckedOut)
  {
    boolean wasSet = false;
    checkedOut = aCheckedOut;
    wasSet = true;
    return wasSet;
  }

  public Date getReservationStartDate()
  {
    return reservationStartDate;
  }

  public Date getReservationEndDate()
  {
    return reservationEndDate;
  }

  public boolean getCheckedOut()
  {
    return checkedOut;
  }
  /* Code from template association_GetOne */
  public User getBorrower()
  {
    return borrower;
  }
  /* Code from template association_GetOne */
  public Item getReservedItems()
  {
    return reservedItems;
  }
  /* Code from template association_SetOneToMany */
  public boolean setBorrower(User aBorrower)
  {
    boolean wasSet = false;
    if (aBorrower == null)
    {
      return wasSet;
    }

    User existingBorrower = borrower;
    borrower = aBorrower;
    if (existingBorrower != null && !existingBorrower.equals(aBorrower))
    {
      existingBorrower.removeUserReservation(this);
    }
    borrower.addUserReservation(this);
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_SetOneToOptionalOne */
  public boolean setReservedItems(Item aNewReservedItems)
  {
    boolean wasSet = false;
    if (aNewReservedItems == null)
    {
      //Unable to setReservedItems to null, as reservation must always be associated to a reservedItems
      return wasSet;
    }
    
    Reservation existingReservation = aNewReservedItems.getReservation();
    if (existingReservation != null && !equals(existingReservation))
    {
      //Unable to setReservedItems, the current reservedItems already has a reservation, which would be orphaned if it were re-assigned
      return wasSet;
    }
    
    Item anOldReservedItems = reservedItems;
    reservedItems = aNewReservedItems;
    reservedItems.setReservation(this);

    if (anOldReservedItems != null)
    {
      anOldReservedItems.setReservation(null);
    }
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    User placeholderBorrower = borrower;
    this.borrower = null;
    if(placeholderBorrower != null)
    {
      placeholderBorrower.removeUserReservation(this);
    }
    Item existingReservedItems = reservedItems;
    reservedItems = null;
    if (existingReservedItems != null)
    {
      existingReservedItems.setReservation(null);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "checkedOut" + ":" + getCheckedOut()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "reservationStartDate" + "=" + (getReservationStartDate() != null ? !getReservationStartDate().equals(this)  ? getReservationStartDate().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "reservationEndDate" + "=" + (getReservationEndDate() != null ? !getReservationEndDate().equals(this)  ? getReservationEndDate().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "borrower = "+(getBorrower()!=null?Integer.toHexString(System.identityHashCode(getBorrower())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "reservedItems = "+(getReservedItems()!=null?Integer.toHexString(System.identityHashCode(getReservedItems())):"null");
  }
}