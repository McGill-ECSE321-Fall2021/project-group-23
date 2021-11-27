package ca.mcgill.ecse321.librarysystem;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import ca.mcgill.ecse321.librarysystem.model.HeadLibrarian;
import ca.mcgill.ecse321.librarysystem.service.HeadLibrarianService;
import ca.mcgill.ecse321.librarysystem.service.ShiftService;
import ca.mcgill.ecse321.librarysystem.service.WeeklyScheduleService;
import ca.mcgill.ecse321.librarysystem.model.Shift;
import ca.mcgill.ecse321.librarysystem.model.WeeklySchedule;
import ca.mcgill.ecse321.librarysystem.model.Shift.DayOfWeek;

@RestController
@SpringBootApplication
public class LibrarySystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(LibrarySystemApplication.class, args);
		
		// Initialize the system with a Head Librarian and WeeklySchedule
		HeadLibrarianService headLibService = new HeadLibrarianService();
		ShiftService shiftService = new ShiftService();
		WeeklyScheduleService scheduleService = new WeeklyScheduleService();
		
		Shift shift1 = shiftService.createShift(DayOfWeek.Monday, Time.valueOf("08:00:00"), Time.valueOf("16:00:00"));
		Shift shift2 = shiftService.createShift(DayOfWeek.Wednesday, Time.valueOf("08:00:00"), Time.valueOf("16:00:00"));
		Shift shift3 = shiftService.createShift(DayOfWeek.Friday, Time.valueOf("08:00:00"), Time.valueOf("14:00:00"));
		
		ArrayList<Integer> shiftIds = new ArrayList<Integer>();
		shiftIds.add(shift1.getShiftId());
		shiftIds.add(shift2.getShiftId());
		shiftIds.add(shift3.getShiftId());
		
		WeeklySchedule schedule = scheduleService.createWeeklySchedule(shiftIds);
		headLibService.createHeadLibrarian("Abiola", "Olaniyan", "library123", schedule.getWeeklyScheduleId());
	}
	@RequestMapping("/")
  public String greeting(){
    return "Hello FROM THE OTHER SIIIIIIIIIDEEE!";
  }

}
