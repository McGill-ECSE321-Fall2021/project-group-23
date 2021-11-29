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
  private String address;

  public CustomerDto() {

  }

  public CustomerDto(int customerId) {
    this.firstName = null;
    this.lastName = null;
    this.customerId = customerId;
    this.password = null;
    this.email = null;
    this.isVerified = false;
    this.isLocal = false;
    this.address = null;
    this.accountBalance = 0;
  }

  public CustomerDto(String firstName, String lastName, int customerId, String password, String email, boolean isVerified, boolean isLocal, int accountBalance, String address) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.customerId = customerId;
    this.password = password;
    this.email = email;
    this.isVerified = isVerified;
    this.isLocal = isLocal;
    this.address = address;
    this.accountBalance = accountBalance;
  }

  // ------------------ Getters and Setters ------------------------
	public String getFirstName() {
		return this.firstName;
	}
	
	public String getLastName() {
		return this.lastName;
	}

  public String getAddress() {
		return this.address;
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

  public void setAddress(String address) {
		this.address = address;
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
