/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.1.4607.2d2b84eb8 modeling language!*/

package ca\mcgill\ecse321\model;
import java.util.*;
import java.sql.Date;
import java.sql.Time;

// line 21 "../model.ump"
public class User extends Account
{

  //------------------------
  // STATIC VARIABLES
  //------------------------

  private static Map<String, User> usersByEmail = new HashMap<String, User>();

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //User Attributes
  private String email;
  private boolean verified;
  private boolean local;
  private String address;
  private int balance;

  //User Associations
  private Library library;
  private List<Reservation> userReservation;
  private List<libraryBooking> libraryBooking;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public User(String aFirstName, String aLastName, int aAccountId, String aPassword, String aEmail, boolean aVerified, boolean aLocal, String aAddress, int aBalance, Library aLibrary)
  {
    super(aFirstName, aLastName, aAccountId, aPassword);
    verified = aVerified;
    local = aLocal;
    address = aAddress;
    balance = aBalance;
    if (!setEmail(aEmail))
    {
      throw new RuntimeException("Cannot create due to duplicate email. See http://manual.umple.org?RE003ViolationofUniqueness.html");
    }
    boolean didAddLibrary = setLibrary(aLibrary);
    if (!didAddLibrary)
    {
      throw new RuntimeException("Unable to create citizen due to library. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    userReservation = new ArrayList<Reservation>();
    libraryBooking = new ArrayList<libraryBooking>();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setEmail(String aEmail)
  {
    boolean wasSet = false;
    String anOldEmail = getEmail();
    if (anOldEmail != null && anOldEmail.equals(aEmail)) {
      return true;
    }
    if (hasWithEmail(aEmail)) {
      return wasSet;
    }
    email = aEmail;
    wasSet = true;
    if (anOldEmail != null) {
      usersByEmail.remove(anOldEmail);
    }
    usersByEmail.put(aEmail, this);
    return wasSet;
  }

  public boolean setVerified(boolean aVerified)
  {
    boolean wasSet = false;
    verified = aVerified;
    wasSet = true;
    return wasSet;
  }

  public boolean setLocal(boolean aLocal)
  {
    boolean wasSet = false;
    local = aLocal;
    wasSet = true;
    return wasSet;
  }

  public boolean setAddress(String aAddress)
  {
    boolean wasSet = false;
    address = aAddress;
    wasSet = true;
    return wasSet;
  }

  public boolean setBalance(int aBalance)
  {
    boolean wasSet = false;
    balance = aBalance;
    wasSet = true;
    return wasSet;
  }

  public String getEmail()
  {
    return email;
  }
  /* Code from template attribute_GetUnique */
  public static User getWithEmail(String aEmail)
  {
    return usersByEmail.get(aEmail);
  }
  /* Code from template attribute_HasUnique */
  public static boolean hasWithEmail(String aEmail)
  {
    return getWithEmail(aEmail) != null;
  }

  public boolean getVerified()
  {
    return verified;
  }

  public boolean getLocal()
  {
    return local;
  }

  public String getAddress()
  {
    return address;
  }

  public int getBalance()
  {
    return balance;
  }
  /* Code from template association_GetOne */
  public Library getLibrary()
  {
    return library;
  }
  /* Code from template association_GetMany */
  public Reservation getUserReservation(int index)
  {
    Reservation aUserReservation = userReservation.get(index);
    return aUserReservation;
  }

  public List<Reservation> getUserReservation()
  {
    List<Reservation> newUserReservation = Collections.unmodifiableList(userReservation);
    return newUserReservation;
  }

  public int numberOfUserReservation()
  {
    int number = userReservation.size();
    return number;
  }

  public boolean hasUserReservation()
  {
    boolean has = userReservation.size() > 0;
    return has;
  }

  public int indexOfUserReservation(Reservation aUserReservation)
  {
    int index = userReservation.indexOf(aUserReservation);
    return index;
  }
  /* Code from template association_GetMany */
  public libraryBooking getLibraryBooking(int index)
  {
    libraryBooking aLibraryBooking = libraryBooking.get(index);
    return aLibraryBooking;
  }

  public List<libraryBooking> getLibraryBooking()
  {
    List<libraryBooking> newLibraryBooking = Collections.unmodifiableList(libraryBooking);
    return newLibraryBooking;
  }

  public int numberOfLibraryBooking()
  {
    int number = libraryBooking.size();
    return number;
  }

  public boolean hasLibraryBooking()
  {
    boolean has = libraryBooking.size() > 0;
    return has;
  }

  public int indexOfLibraryBooking(libraryBooking aLibraryBooking)
  {
    int index = libraryBooking.indexOf(aLibraryBooking);
    return index;
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
      existingLibrary.removeCitizen(this);
    }
    library.addCitizen(this);
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfUserReservation()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Reservation addUserReservation(Date aReservationStartDate, Date aReservationEndDate, boolean aCheckedOut, Item aReservedItems)
  {
    return new Reservation(aReservationStartDate, aReservationEndDate, aCheckedOut, this, aReservedItems);
  }

  public boolean addUserReservation(Reservation aUserReservation)
  {
    boolean wasAdded = false;
    if (userReservation.contains(aUserReservation)) { return false; }
    User existingBorrower = aUserReservation.getBorrower();
    boolean isNewBorrower = existingBorrower != null && !this.equals(existingBorrower);
    if (isNewBorrower)
    {
      aUserReservation.setBorrower(this);
    }
    else
    {
      userReservation.add(aUserReservation);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeUserReservation(Reservation aUserReservation)
  {
    boolean wasRemoved = false;
    //Unable to remove aUserReservation, as it must always have a borrower
    if (!this.equals(aUserReservation.getBorrower()))
    {
      userReservation.remove(aUserReservation);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addUserReservationAt(Reservation aUserReservation, int index)
  {  
    boolean wasAdded = false;
    if(addUserReservation(aUserReservation))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfUserReservation()) { index = numberOfUserReservation() - 1; }
      userReservation.remove(aUserReservation);
      userReservation.add(index, aUserReservation);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveUserReservationAt(Reservation aUserReservation, int index)
  {
    boolean wasAdded = false;
    if(userReservation.contains(aUserReservation))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfUserReservation()) { index = numberOfUserReservation() - 1; }
      userReservation.remove(aUserReservation);
      userReservation.add(index, aUserReservation);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addUserReservationAt(aUserReservation, index);
    }
    return wasAdded;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfLibraryBooking()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public libraryBooking addLibraryBooking(Date aStartDate, Date aEndDate, Time aStartTime, Time aEndTime, Library aLibrary)
  {
    return new libraryBooking(aStartDate, aEndDate, aStartTime, aEndTime, this, aLibrary);
  }

  public boolean addLibraryBooking(libraryBooking aLibraryBooking)
  {
    boolean wasAdded = false;
    if (libraryBooking.contains(aLibraryBooking)) { return false; }
    User existingUser = aLibraryBooking.getUser();
    boolean isNewUser = existingUser != null && !this.equals(existingUser);
    if (isNewUser)
    {
      aLibraryBooking.setUser(this);
    }
    else
    {
      libraryBooking.add(aLibraryBooking);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeLibraryBooking(libraryBooking aLibraryBooking)
  {
    boolean wasRemoved = false;
    //Unable to remove aLibraryBooking, as it must always have a user
    if (!this.equals(aLibraryBooking.getUser()))
    {
      libraryBooking.remove(aLibraryBooking);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addLibraryBookingAt(libraryBooking aLibraryBooking, int index)
  {  
    boolean wasAdded = false;
    if(addLibraryBooking(aLibraryBooking))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfLibraryBooking()) { index = numberOfLibraryBooking() - 1; }
      libraryBooking.remove(aLibraryBooking);
      libraryBooking.add(index, aLibraryBooking);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveLibraryBookingAt(libraryBooking aLibraryBooking, int index)
  {
    boolean wasAdded = false;
    if(libraryBooking.contains(aLibraryBooking))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfLibraryBooking()) { index = numberOfLibraryBooking() - 1; }
      libraryBooking.remove(aLibraryBooking);
      libraryBooking.add(index, aLibraryBooking);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addLibraryBookingAt(aLibraryBooking, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    usersByEmail.remove(getEmail());
    Library placeholderLibrary = library;
    this.library = null;
    if(placeholderLibrary != null)
    {
      placeholderLibrary.removeCitizen(this);
    }
    for(int i=userReservation.size(); i > 0; i--)
    {
      Reservation aUserReservation = userReservation.get(i - 1);
      aUserReservation.delete();
    }
    for(int i=libraryBooking.size(); i > 0; i--)
    {
      libraryBooking aLibraryBooking = libraryBooking.get(i - 1);
      aLibraryBooking.delete();
    }
    super.delete();
  }


  public String toString()
  {
    return super.toString() + "["+
            "email" + ":" + getEmail()+ "," +
            "verified" + ":" + getVerified()+ "," +
            "local" + ":" + getLocal()+ "," +
            "address" + ":" + getAddress()+ "," +
            "balance" + ":" + getBalance()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "library = "+(getLibrary()!=null?Integer.toHexString(System.identityHashCode(getLibrary())):"null");
  }
}