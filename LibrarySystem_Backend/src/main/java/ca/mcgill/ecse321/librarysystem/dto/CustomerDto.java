package ca.mcgill.ecse321.librarysystem.dto;

public class CustomerDto {
  private String firstName;
  private String lastName;
  private int customerId;
  private String password;
  private String email;
  private boolean isVerified;
  private boolean isLocal;
  private int accountBalance;

  public customerDto() {

  }

  @SuppressWarnings("unchecked")
  public customerDto(int customerId) {
    this.firstName = null;
    this.lastName = null;
    this.customerId = customerId;
    this.password = null;
    this.email = null;
    this.isVerified = false;
    this.isLocal = false;
    this.accountBalance = 0;
  }

  public customerDto(String firstName, String lastName, int customerId, String password, String email, boolean isVerified, boolean isLocal, int accountBalance) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.customerId = customerId;
    this.password = password;
    this.email = email;
    this.isVerified = isVerified;
    this.isLocal = isLocal;
    this.accountBalance = accountBalance;
  }

  // ------------------ Getters and Setters ------------------------
	public String getFirstName() {
		return this.firstName;
	}
	
	public String getLastName() {
		return this.lastName;
	}
	
  public int getCustomerId() {
    return this.customerId;
  }

	public String getPassword() {
		return this.password;
	}

  public String getEmail() {
    return this.email;
  }

  public boolean getIsVerified() {
    return this.isVerified;
  }

  public boolean getIsLocal() {
    return this.isLocal;
  }

  public int getAccountBalance() {
    return this.accountBalance;
  }

  public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
  public void setCustomerId(int customerId) {
    this.customerId = customerId;
  }

	public void setPassword(String password) {
		this.password = password;
	}

  public void setEmail(String email) {
    this.email = email;
  }

  public void setIsVerified(boolean isVerified) {
    this.isVerified = isVerified;
  }

  public void setIsLocal(boolean isLocal) {
    this.isLocal = isLocal;
  }

  public void setAccountBalance(int accountBalance) {
    this.accountBalance = accountBalance;
  }
}
