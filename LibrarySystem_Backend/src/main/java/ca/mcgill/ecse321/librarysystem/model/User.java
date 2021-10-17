package ca.mcgill.ecse321.librarysystem.model;

import java.util.*;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class User extends Account {
  private String email;

  public String getEmail() {
    return this.email;
  }

  public void setEmail(String newEmail) {
    this.email = newEmail;
  }

  private String address;

  public String getAddress() {
    return this.address;
  }

  public void setAddress(String userAddress) {
    this.address = userAddress;
  }

  private int accountBalance;

  public int getAccountBalance() {
    return this.accountBalance;
  }

  public void setAccountBalance(int newAccountBalance) {
    this.accountBalance = newAccountBalance;
  }

  private boolean isVerified;

  public boolean getIsVerified() {
    return this.isVerified;
  }

  public void setIsVerified(boolean newVerificationStatus) {
    this.isVerified = newVerificationStatus;
  }

  private boolean isLocal;

  public boolean getIsLocal() {
    return this.isLocal;
  }

  public void setIsLocal(boolean newLocalStatus) {
    this.isLocal = newLocalStatus;
  }

}