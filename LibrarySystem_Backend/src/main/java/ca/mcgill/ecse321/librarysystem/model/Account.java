
package ca.mcgill.ecse321.librarysystem.model;
import java.util.*;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public abstract class Account
{
  private int accountId;

  @Id
  public int getAccountId() {
    return this.accountId;
  }
  public void setAccountId(int id) {
    this.accountId = id;
  }

  private String firstName;
  public String getFirstName() {
    return this.firstName;
  }
  public void setFirstName(String fName) {
    this.firstName = fName;
  }

  private String lastName;
  public String getLastName() {
    return this.lastName;
  }
  public void setLastName(String lName) {
    this.lastName = lName;
  }

  private String password;
  public String getPassword() { // check if should remove getPassword() method
    return this.password;
  }
  public void setPassword(String newPassword) {
    this.password = newPassword;
  }
  
}