package ca.mcgill.ecse321.librarysystem.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import ca.mcgill.ecse321.librarysystem.model.Library;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TestLibraryPersistence {
	@Autowired
	private LibraryRepository libraryRepository;

	@AfterEach
	public void clearDatabase() {
		
		libraryRepository.deleteAll();
	}

	@Test
	void testPersistLibraray() {
		
		
        String name = "Abdul international library";
		String address ="21312 rue lol";
		String email ="abdul@adbul.com";
		String phone = "911";

		if(!libraryRepository.existsByName(name)){
			libraryRepository.save(new Library(name, address, phone, email));
		}
		Library lib = libraryRepository.findByName(name);
		assertEquals(lib.getName(), name);
		assertEquals(lib.getEmailAddress(), email);
		assertEquals(lib.getPhoneNumber(), phone);
		assertEquals(lib.getAddress(), address);
	}
}
