/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.1.4607.2d2b84eb8 modeling language!*/

package ca\mcgill\ecse321\model;
import java.util.*;

// line 3 "../model.ump"
public abstract class Account
{

  //------------------------
  // STATIC VARIABLES
  //------------------------

  private static Map<Integer, Account> accountsByAccountId = new HashMap<Integer, Account>();

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Account Attributes
  private String firstName;
  private String lastName;
  private int accountId;
  private String password;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Account(String aFirstName, String aLastName, int aAccountId, String aPassword)
  {
    firstName = aFirstName;
    lastName = aLastName;
    password = aPassword;
    if (!setAccountId(aAccountId))
    {
      throw new RuntimeException("Cannot create due to duplicate accountId. See http://manual.umple.org?RE003ViolationofUniqueness.html");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setFirstName(String aFirstName)
  {
    boolean wasSet = false;
    firstName = aFirstName;
    wasSet = true;
    return wasSet;
  }

  public boolean setLastName(String aLastName)
  {
    boolean wasSet = false;
    lastName = aLastName;
    wasSet = true;
    return wasSet;
  }

  public boolean setAccountId(int aAccountId)
  {
    boolean wasSet = false;
    Integer anOldAccountId = getAccountId();
    if (anOldAccountId != null && anOldAccountId.equals(aAccountId)) {
      return true;
    }
    if (hasWithAccountId(aAccountId)) {
      return wasSet;
    }
    accountId = aAccountId;
    wasSet = true;
    if (anOldAccountId != null) {
      accountsByAccountId.remove(anOldAccountId);
    }
    accountsByAccountId.put(aAccountId, this);
    return wasSet;
  }

  public boolean setPassword(String aPassword)
  {
    boolean wasSet = false;
    password = aPassword;
    wasSet = true;
    return wasSet;
  }

  public String getFirstName()
  {
    return firstName;
  }

  public String getLastName()
  {
    return lastName;
  }

  public int getAccountId()
  {
    return accountId;
  }
  /* Code from template attribute_GetUnique */
  public static Account getWithAccountId(int aAccountId)
  {
    return accountsByAccountId.get(aAccountId);
  }
  /* Code from template attribute_HasUnique */
  public static boolean hasWithAccountId(int aAccountId)
  {
    return getWithAccountId(aAccountId) != null;
  }

  public String getPassword()
  {
    return password;
  }

  public void delete()
  {
    accountsByAccountId.remove(getAccountId());
  }


  public String toString()
  {
    return super.toString() + "["+
            "firstName" + ":" + getFirstName()+ "," +
            "lastName" + ":" + getLastName()+ "," +
            "accountId" + ":" + getAccountId()+ "," +
            "password" + ":" + getPassword()+ "]";
  }
}