package ca.mcgill.ecse321.librarysystem.dto;


import java.sql.Date;

public class HolidayDto {

	private String name;
	private Date startDate;
	private Date endDate;

	public HolidayDto() {
	}

	public HolidayDto(String name) {
		this(name, Date.valueOf("2022-01-01"), Date.valueOf("2022-01-09"));
	}

	public HolidayDto(String name, Date startDate, Date endDate) {
		this.name = name;
		this.startDate = startDate;
		this.endDate = endDate;
	}

	public String getName() {
		return name;
	}

	public Date getStartDate() {
		return startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

}
