package ca.mcgill.ecse321.librarysystem.dto;

public class LibrarianDto {
	private String firstName;
	private String lastName;
	private String password;
	private int librarianId;
	private WeeklyScheduleDto librarianSchedule;
	
	public LibrarianDto() {
		
	}
	
	public LibrarianDto(int librarianId) {
		this.librarianId = librarianId;
		this.firstName = null;
		this.lastName = null;
		this.password = null;
		this.librarianSchedule = null;
	}
	
	public LibrarianDto(int librarianId, String firstName, String lastName, String password) {
		this.librarianId = librarianId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
		this.librarianSchedule = null;
	}
	
	public LibrarianDto(int librarianId, String firstName, String lastName, String password, WeeklyScheduleDto librarianSchedule) {
		this.librarianId = librarianId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
		this.librarianSchedule = librarianSchedule;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public String getPassword() {
		return password;
	}
	
	public WeeklyScheduleDto getWeeklySchedule() {
		return librarianSchedule;
	}
	
	public int getLibrarianId() {
		return librarianId;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public void setWeeklySchedule(WeeklyScheduleDto librarianSchedule) {
		this.librarianSchedule = librarianSchedule;
	}
}
