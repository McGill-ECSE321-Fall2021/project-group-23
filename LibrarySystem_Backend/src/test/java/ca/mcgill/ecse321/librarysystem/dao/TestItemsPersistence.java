package ca.mcgill.ecse321.librarysystem.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import ca.mcgill.ecse321.librarysystem.model.Album;
import ca.mcgill.ecse321.librarysystem.model.Archive;
import ca.mcgill.ecse321.librarysystem.model.Book;
import ca.mcgill.ecse321.librarysystem.model.Movie;
import ca.mcgill.ecse321.librarysystem.model.Newspapers;
import ca.mcgill.ecse321.librarysystem.model.Item.Status;

import org.springframework.beans.factory.annotation.Autowired;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TestItemsPersistence {

    @Autowired
    private ItemRepository itemRepository;

    @BeforeEach
	public void clearDatabase() {
		
		itemRepository.deleteAll();
		
	}


    @Test
    public void testPersistAndLoadBook() {
        
        int id = 000000000;
        String name = "Lord Of The Rings";
        Status status = Book.Status.AVAILABLE;
        Book book = new Book();
        book.setTitle(name);
        book.setStatus(status);
        book.setItemId(id);
        itemRepository.save(book);

        book = null;

        book = (Book) itemRepository.findItemByItemId(id);
        assertNotNull(book);
        assertEquals(id, book.getItemId());
        

    }

    @Test
    public void testPersistAndLoadMovie() {
        
        int id = 000000000;
        String name = "Lord Of The Rings";
        Status status = Movie.Status.AVAILABLE;
        Movie movie = new Movie();
        movie.setTitle(name);
        movie.setStatus(status);
        movie.setItemId(id);
        itemRepository.save(movie);

        movie = null;

        movie = (Movie) itemRepository.findItemByItemId(id);
        assertNotNull(movie);
        assertEquals(id, movie.getItemId());
        

    }

    @Test
    public void testPersistAndLoadAlbum() {
        
        int id = 000000000;
        String name = "OK Computer";
        Status status = Album.Status.AVAILABLE;
        Album album = new Album();
        album.setTitle(name);
        album.setStatus(status);
        album.setItemId(id);
        itemRepository.save(album);

        album = null;

        album = (Album) itemRepository.findItemByItemId(id);
        assertNotNull(album);
        assertEquals(id, album.getItemId());
        

    }

    @Test
    public void testPersistAndLoadNewspaper() {
        
        int id = 000000000;
        String name = "La Presse";
        Status status = Newspapers.Status.AVAILABLE;
        Newspapers newspapers = new Newspapers();
        newspapers.setTitle(name);
        newspapers.setStatus(status);
        newspapers.setItemId(id);
        itemRepository.save(newspapers);

        newspapers = null;

        newspapers = (Newspapers) itemRepository.findItemByItemId(id);
        assertNotNull(newspapers);
        assertEquals(id, newspapers.getItemId());
        

    }

    @Test
    public void testPersistAndLoadArchive() {
        
        int id = 000000000;
        String name = "Encyclopedia Vol.3";
        Status status = Archive.Status.AVAILABLE;
        Archive archive = new Archive();
        archive.setTitle(name);
        archive.setStatus(status);
        archive.setItemId(id);
        itemRepository.save(archive);

        archive = null;

        archive = (Archive) itemRepository.findItemByItemId(id);
        assertNotNull(archive);
        assertEquals(id, archive.getItemId());
        

    }


}