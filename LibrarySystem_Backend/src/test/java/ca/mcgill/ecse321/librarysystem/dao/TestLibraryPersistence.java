package ca.mcgill.ecse321.librarysystem.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import ca.mcgill.ecse321.librarysystem.model.Library;

@SpringBootTest
public class TestLibraryPersistence {
	@Autowired
	private LibraryRepository libraryRepository;

	@Test
	void testPersistLibraray() {
		libraryRepository.save(new Library("Abdul international library", "21312 rue lol", "911", "abdul@adbul.com"));
        assertEquals("abdul", "abdul");
	}
}
