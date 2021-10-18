package ca.mcgill.ecse321.librarysystem.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

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
    @AfterEach
	public void clearrDatabase() {
		
		itemRepository.deleteAll();
		
	}


    @Test
    public void testPersistAndLoadBook() {
        
        String name = "Lord Of The Rings 2";
        Status status = Book.Status.AVAILABLE;
        Book book = new Book();
        book.setTitle(name);
        book.setStatus(status);
        int id = itemRepository.save(book).getItemId();

        book = null;

        book = (Book) itemRepository.findItemByItemId(id);
        assertNotNull(book);
        assertEquals(id, book.getItemId());
        

    }

    @Test
    public void testPersistAndLoadMovie() {
        
        String name = "Lord Of The Rings";
        Status status = Movie.Status.AVAILABLE;
        Movie movie = new Movie();
        movie.setTitle(name);
        movie.setStatus(status);
        int id = itemRepository.save(movie).getItemId();

        movie = null;

        movie = (Movie) itemRepository.findItemByItemId(id);
        assertNotNull(movie);
        assertEquals(id, movie.getItemId());
        

    }

    @Test
    public void testPersistAndLoadAlbum() {
        
        String name = "OK Computer";
        Status status = Album.Status.AVAILABLE;
        Album album = new Album();
        album.setTitle(name);
        album.setStatus(status);
        Album album2 = itemRepository.save(album);

        album = null;

        album = (Album) itemRepository.findItemByItemId(album2.getItemId());
        assertNotNull(album);
        assertEquals(album2.getItemId(), album.getItemId());
        

    }

    @Test
    public void testPersistAndLoadNewspaper() {
        
        String name = "La Presse";
        Status status = Newspapers.Status.AVAILABLE;
        Newspapers newspapers = new Newspapers();
        newspapers.setTitle(name);
        newspapers.setStatus(status);
        int id = itemRepository.save(newspapers).getItemId();

        newspapers = null;

        newspapers = (Newspapers) itemRepository.findItemByItemId(id);
        assertNotNull(newspapers);
        assertEquals(id, newspapers.getItemId());
        

    }

    @Test
    public void testPersistAndLoadArchive() {
        
        String name = "Encyclopedia Vol.3";
        Status status = Archive.Status.AVAILABLE;
        Archive archive = new Archive();
        archive.setTitle(name);
        archive.setStatus(status);
        int id = itemRepository.save(archive).getItemId();

        archive = null;

        archive = (Archive) itemRepository.findItemByItemId(id);
        assertNotNull(archive);
        assertEquals(id, archive.getItemId());
        

    }

    @Test
    public void testSearchItemByTitle() {
        
        String name = "Encyclopedia Vol.3";
        Status status = Archive.Status.AVAILABLE;
        Archive archive = new Archive();
        archive.setTitle(name);
        archive.setStatus(status);
        int id = itemRepository.save(archive).getItemId();

        archive = null;

        archive = (Archive) itemRepository.findItemByTitle(name).get(0);
        assertNotNull(archive);
        assertEquals(id, archive.getItemId());
        

    }

    @Test
    public void deleteItemById() {
        
        String name = "Encyclopedia Vol.3";
        Status status = Archive.Status.AVAILABLE;
        Archive archive = new Archive();
        archive.setTitle(name);
        archive.setStatus(status);
        int id = itemRepository.save(archive).getItemId();

        itemRepository.deleteById(id);
        assertNull(itemRepository.findItemByItemId(id));
        
        

    }


}