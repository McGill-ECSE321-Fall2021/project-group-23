/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.1.4607.2d2b84eb8 modeling language!*/

package ca\mcgill\ecse321\model;
import java.util.*;
import java.sql.Date;
import java.sql.Time;

// line 32 "../model.ump"
public class Library
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Library Attributes
  private String name;
  private String address;
  private String phoneNumber;
  private String email;

  //Library Associations
  private HeadLibrarian headLibrarian;
  private List<User> citizens;
  private List<Librarian> librarians;
  private List<Item> libraryItems;
  private List<Holiday> holidayDays;
  private List<OpeningsHours> businessHours;
  private List<libraryBooking> libraryBooking;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Library(String aName, String aAddress, String aPhoneNumber, String aEmail)
  {
    name = aName;
    address = aAddress;
    phoneNumber = aPhoneNumber;
    email = aEmail;
    citizens = new ArrayList<User>();
    librarians = new ArrayList<Librarian>();
    libraryItems = new ArrayList<Item>();
    holidayDays = new ArrayList<Holiday>();
    businessHours = new ArrayList<OpeningsHours>();
    libraryBooking = new ArrayList<libraryBooking>();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setName(String aName)
  {
    boolean wasSet = false;
    name = aName;
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

  public boolean setPhoneNumber(String aPhoneNumber)
  {
    boolean wasSet = false;
    phoneNumber = aPhoneNumber;
    wasSet = true;
    return wasSet;
  }

  public boolean setEmail(String aEmail)
  {
    boolean wasSet = false;
    email = aEmail;
    wasSet = true;
    return wasSet;
  }

  public String getName()
  {
    return name;
  }

  public String getAddress()
  {
    return address;
  }

  public String getPhoneNumber()
  {
    return phoneNumber;
  }

  public String getEmail()
  {
    return email;
  }
  /* Code from template association_GetOne */
  public HeadLibrarian getHeadLibrarian()
  {
    return headLibrarian;
  }

  public boolean hasHeadLibrarian()
  {
    boolean has = headLibrarian != null;
    return has;
  }
  /* Code from template association_GetMany */
  public User getCitizen(int index)
  {
    User aCitizen = citizens.get(index);
    return aCitizen;
  }

  public List<User> getCitizens()
  {
    List<User> newCitizens = Collections.unmodifiableList(citizens);
    return newCitizens;
  }

  public int numberOfCitizens()
  {
    int number = citizens.size();
    return number;
  }

  public boolean hasCitizens()
  {
    boolean has = citizens.size() > 0;
    return has;
  }

  public int indexOfCitizen(User aCitizen)
  {
    int index = citizens.indexOf(aCitizen);
    return index;
  }
  /* Code from template association_GetMany */
  public Librarian getLibrarian(int index)
  {
    Librarian aLibrarian = librarians.get(index);
    return aLibrarian;
  }

  public List<Librarian> getLibrarians()
  {
    List<Librarian> newLibrarians = Collections.unmodifiableList(librarians);
    return newLibrarians;
  }

  public int numberOfLibrarians()
  {
    int number = librarians.size();
    return number;
  }

  public boolean hasLibrarians()
  {
    boolean has = librarians.size() > 0;
    return has;
  }

  public int indexOfLibrarian(Librarian aLibrarian)
  {
    int index = librarians.indexOf(aLibrarian);
    return index;
  }
  /* Code from template association_GetMany */
  public Item getLibraryItem(int index)
  {
    Item aLibraryItem = libraryItems.get(index);
    return aLibraryItem;
  }

  public List<Item> getLibraryItems()
  {
    List<Item> newLibraryItems = Collections.unmodifiableList(libraryItems);
    return newLibraryItems;
  }

  public int numberOfLibraryItems()
  {
    int number = libraryItems.size();
    return number;
  }

  public boolean hasLibraryItems()
  {
    boolean has = libraryItems.size() > 0;
    return has;
  }

  public int indexOfLibraryItem(Item aLibraryItem)
  {
    int index = libraryItems.indexOf(aLibraryItem);
    return index;
  }
  /* Code from template association_GetMany */
  public Holiday getHolidayDay(int index)
  {
    Holiday aHolidayDay = holidayDays.get(index);
    return aHolidayDay;
  }

  public List<Holiday> getHolidayDays()
  {
    List<Holiday> newHolidayDays = Collections.unmodifiableList(holidayDays);
    return newHolidayDays;
  }

  public int numberOfHolidayDays()
  {
    int number = holidayDays.size();
    return number;
  }

  public boolean hasHolidayDays()
  {
    boolean has = holidayDays.size() > 0;
    return has;
  }

  public int indexOfHolidayDay(Holiday aHolidayDay)
  {
    int index = holidayDays.indexOf(aHolidayDay);
    return index;
  }
  /* Code from template association_GetMany */
  public OpeningsHours getBusinessHour(int index)
  {
    OpeningsHours aBusinessHour = businessHours.get(index);
    return aBusinessHour;
  }

  public List<OpeningsHours> getBusinessHours()
  {
    List<OpeningsHours> newBusinessHours = Collections.unmodifiableList(businessHours);
    return newBusinessHours;
  }

  public int numberOfBusinessHours()
  {
    int number = businessHours.size();
    return number;
  }

  public boolean hasBusinessHours()
  {
    boolean has = businessHours.size() > 0;
    return has;
  }

  public int indexOfBusinessHour(OpeningsHours aBusinessHour)
  {
    int index = businessHours.indexOf(aBusinessHour);
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
  /* Code from template association_SetOptionalOneToOne */
  public boolean setHeadLibrarian(HeadLibrarian aNewHeadLibrarian)
  {
    boolean wasSet = false;
    if (headLibrarian != null && !headLibrarian.equals(aNewHeadLibrarian) && equals(headLibrarian.getLibrary()))
    {
      //Unable to setHeadLibrarian, as existing headLibrarian would become an orphan
      return wasSet;
    }

    headLibrarian = aNewHeadLibrarian;
    Library anOldLibrary = aNewHeadLibrarian != null ? aNewHeadLibrarian.getLibrary() : null;

    if (!this.equals(anOldLibrary))
    {
      if (anOldLibrary != null)
      {
        anOldLibrary.headLibrarian = null;
      }
      if (headLibrarian != null)
      {
        headLibrarian.setLibrary(this);
      }
    }
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfCitizens()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public User addCitizen(String aFirstName, String aLastName, int aAccountId, String aPassword, String aEmail, boolean aVerified, boolean aLocal, String aAddress, int aBalance)
  {
    return new User(aFirstName, aLastName, aAccountId, aPassword, aEmail, aVerified, aLocal, aAddress, aBalance, this);
  }

  public boolean addCitizen(User aCitizen)
  {
    boolean wasAdded = false;
    if (citizens.contains(aCitizen)) { return false; }
    Library existingLibrary = aCitizen.getLibrary();
    boolean isNewLibrary = existingLibrary != null && !this.equals(existingLibrary);
    if (isNewLibrary)
    {
      aCitizen.setLibrary(this);
    }
    else
    {
      citizens.add(aCitizen);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeCitizen(User aCitizen)
  {
    boolean wasRemoved = false;
    //Unable to remove aCitizen, as it must always have a library
    if (!this.equals(aCitizen.getLibrary()))
    {
      citizens.remove(aCitizen);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addCitizenAt(User aCitizen, int index)
  {  
    boolean wasAdded = false;
    if(addCitizen(aCitizen))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfCitizens()) { index = numberOfCitizens() - 1; }
      citizens.remove(aCitizen);
      citizens.add(index, aCitizen);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveCitizenAt(User aCitizen, int index)
  {
    boolean wasAdded = false;
    if(citizens.contains(aCitizen))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfCitizens()) { index = numberOfCitizens() - 1; }
      citizens.remove(aCitizen);
      citizens.add(index, aCitizen);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addCitizenAt(aCitizen, index);
    }
    return wasAdded;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfLibrarians()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Librarian addLibrarian(String aFirstName, String aLastName, int aAccountId, String aPassword, WeeklySchedule aLibrarianSchedule)
  {
    return new Librarian(aFirstName, aLastName, aAccountId, aPassword, aLibrarianSchedule, this);
  }

  public boolean addLibrarian(Librarian aLibrarian)
  {
    boolean wasAdded = false;
    if (librarians.contains(aLibrarian)) { return false; }
    Library existingLibrary = aLibrarian.getLibrary();
    boolean isNewLibrary = existingLibrary != null && !this.equals(existingLibrary);
    if (isNewLibrary)
    {
      aLibrarian.setLibrary(this);
    }
    else
    {
      librarians.add(aLibrarian);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeLibrarian(Librarian aLibrarian)
  {
    boolean wasRemoved = false;
    //Unable to remove aLibrarian, as it must always have a library
    if (!this.equals(aLibrarian.getLibrary()))
    {
      librarians.remove(aLibrarian);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addLibrarianAt(Librarian aLibrarian, int index)
  {  
    boolean wasAdded = false;
    if(addLibrarian(aLibrarian))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfLibrarians()) { index = numberOfLibrarians() - 1; }
      librarians.remove(aLibrarian);
      librarians.add(index, aLibrarian);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveLibrarianAt(Librarian aLibrarian, int index)
  {
    boolean wasAdded = false;
    if(librarians.contains(aLibrarian))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfLibrarians()) { index = numberOfLibrarians() - 1; }
      librarians.remove(aLibrarian);
      librarians.add(index, aLibrarian);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addLibrarianAt(aLibrarian, index);
    }
    return wasAdded;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfLibraryItems()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */


  public boolean addLibraryItem(Item aLibraryItem)
  {
    boolean wasAdded = false;
    if (libraryItems.contains(aLibraryItem)) { return false; }
    Library existingLibrary = aLibraryItem.getLibrary();
    boolean isNewLibrary = existingLibrary != null && !this.equals(existingLibrary);
    if (isNewLibrary)
    {
      aLibraryItem.setLibrary(this);
    }
    else
    {
      libraryItems.add(aLibraryItem);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeLibraryItem(Item aLibraryItem)
  {
    boolean wasRemoved = false;
    //Unable to remove aLibraryItem, as it must always have a library
    if (!this.equals(aLibraryItem.getLibrary()))
    {
      libraryItems.remove(aLibraryItem);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addLibraryItemAt(Item aLibraryItem, int index)
  {  
    boolean wasAdded = false;
    if(addLibraryItem(aLibraryItem))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfLibraryItems()) { index = numberOfLibraryItems() - 1; }
      libraryItems.remove(aLibraryItem);
      libraryItems.add(index, aLibraryItem);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveLibraryItemAt(Item aLibraryItem, int index)
  {
    boolean wasAdded = false;
    if(libraryItems.contains(aLibraryItem))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfLibraryItems()) { index = numberOfLibraryItems() - 1; }
      libraryItems.remove(aLibraryItem);
      libraryItems.add(index, aLibraryItem);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addLibraryItemAt(aLibraryItem, index);
    }
    return wasAdded;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfHolidayDays()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Holiday addHolidayDay(Date aStartDate, Date aEndDate)
  {
    return new Holiday(aStartDate, aEndDate, this);
  }

  public boolean addHolidayDay(Holiday aHolidayDay)
  {
    boolean wasAdded = false;
    if (holidayDays.contains(aHolidayDay)) { return false; }
    Library existingLibrary = aHolidayDay.getLibrary();
    boolean isNewLibrary = existingLibrary != null && !this.equals(existingLibrary);
    if (isNewLibrary)
    {
      aHolidayDay.setLibrary(this);
    }
    else
    {
      holidayDays.add(aHolidayDay);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeHolidayDay(Holiday aHolidayDay)
  {
    boolean wasRemoved = false;
    //Unable to remove aHolidayDay, as it must always have a library
    if (!this.equals(aHolidayDay.getLibrary()))
    {
      holidayDays.remove(aHolidayDay);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addHolidayDayAt(Holiday aHolidayDay, int index)
  {  
    boolean wasAdded = false;
    if(addHolidayDay(aHolidayDay))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfHolidayDays()) { index = numberOfHolidayDays() - 1; }
      holidayDays.remove(aHolidayDay);
      holidayDays.add(index, aHolidayDay);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveHolidayDayAt(Holiday aHolidayDay, int index)
  {
    boolean wasAdded = false;
    if(holidayDays.contains(aHolidayDay))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfHolidayDays()) { index = numberOfHolidayDays() - 1; }
      holidayDays.remove(aHolidayDay);
      holidayDays.add(index, aHolidayDay);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addHolidayDayAt(aHolidayDay, index);
    }
    return wasAdded;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfBusinessHours()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public OpeningsHours addBusinessHour(DayOfWeek aOpeningDate, Time aStartTime, Time aEndTime)
  {
    return new OpeningsHours(aOpeningDate, aStartTime, aEndTime, this);
  }

  public boolean addBusinessHour(OpeningsHours aBusinessHour)
  {
    boolean wasAdded = false;
    if (businessHours.contains(aBusinessHour)) { return false; }
    Library existingLibrary = aBusinessHour.getLibrary();
    boolean isNewLibrary = existingLibrary != null && !this.equals(existingLibrary);
    if (isNewLibrary)
    {
      aBusinessHour.setLibrary(this);
    }
    else
    {
      businessHours.add(aBusinessHour);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeBusinessHour(OpeningsHours aBusinessHour)
  {
    boolean wasRemoved = false;
    //Unable to remove aBusinessHour, as it must always have a library
    if (!this.equals(aBusinessHour.getLibrary()))
    {
      businessHours.remove(aBusinessHour);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addBusinessHourAt(OpeningsHours aBusinessHour, int index)
  {  
    boolean wasAdded = false;
    if(addBusinessHour(aBusinessHour))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfBusinessHours()) { index = numberOfBusinessHours() - 1; }
      businessHours.remove(aBusinessHour);
      businessHours.add(index, aBusinessHour);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveBusinessHourAt(OpeningsHours aBusinessHour, int index)
  {
    boolean wasAdded = false;
    if(businessHours.contains(aBusinessHour))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfBusinessHours()) { index = numberOfBusinessHours() - 1; }
      businessHours.remove(aBusinessHour);
      businessHours.add(index, aBusinessHour);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addBusinessHourAt(aBusinessHour, index);
    }
    return wasAdded;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfLibraryBooking()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public libraryBooking addLibraryBooking(Date aStartDate, Date aEndDate, Time aStartTime, Time aEndTime, User aUser)
  {
    return new libraryBooking(aStartDate, aEndDate, aStartTime, aEndTime, aUser, this);
  }

  public boolean addLibraryBooking(libraryBooking aLibraryBooking)
  {
    boolean wasAdded = false;
    if (libraryBooking.contains(aLibraryBooking)) { return false; }
    Library existingLibrary = aLibraryBooking.getLibrary();
    boolean isNewLibrary = existingLibrary != null && !this.equals(existingLibrary);
    if (isNewLibrary)
    {
      aLibraryBooking.setLibrary(this);
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
    //Unable to remove aLibraryBooking, as it must always have a library
    if (!this.equals(aLibraryBooking.getLibrary()))
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
    HeadLibrarian existingHeadLibrarian = headLibrarian;
    headLibrarian = null;
    if (existingHeadLibrarian != null)
    {
      existingHeadLibrarian.delete();
    }
    for(int i=citizens.size(); i > 0; i--)
    {
      User aCitizen = citizens.get(i - 1);
      aCitizen.delete();
    }
    for(int i=librarians.size(); i > 0; i--)
    {
      Librarian aLibrarian = librarians.get(i - 1);
      aLibrarian.delete();
    }
    for(int i=libraryItems.size(); i > 0; i--)
    {
      Item aLibraryItem = libraryItems.get(i - 1);
      aLibraryItem.delete();
    }
    for(int i=holidayDays.size(); i > 0; i--)
    {
      Holiday aHolidayDay = holidayDays.get(i - 1);
      aHolidayDay.delete();
    }
    for(int i=businessHours.size(); i > 0; i--)
    {
      OpeningsHours aBusinessHour = businessHours.get(i - 1);
      aBusinessHour.delete();
    }
    for(int i=libraryBooking.size(); i > 0; i--)
    {
      libraryBooking aLibraryBooking = libraryBooking.get(i - 1);
      aLibraryBooking.delete();
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "name" + ":" + getName()+ "," +
            "address" + ":" + getAddress()+ "," +
            "phoneNumber" + ":" + getPhoneNumber()+ "," +
            "email" + ":" + getEmail()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "headLibrarian = "+(getHeadLibrarian()!=null?Integer.toHexString(System.identityHashCode(getHeadLibrarian())):"null");
  }
}