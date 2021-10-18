package ca.mcgill.ecse321.librarysystem.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.librarysystem.model.Reservation;
import ca.mcgill.ecse321.librarysystem.model.Customer;
import ca.mcgill.ecse321.librarysystem.model.Item;

public interface ReservationRepository extends CrudRepository<Reservation, Integer> {
	
	List<Reservation> findByCustomer(Customer customer);

    Reservation findByItem(Item item);

    Reservation findByReservationId(int Id);


}
