package ca.mcgill.ecse321.librarysystem.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.librarysystem.model.Library;

public interface LibraryRepository extends CrudRepository<Library, Integer> {
    Library findByName(String name);
    boolean existsByName(String name);
}
