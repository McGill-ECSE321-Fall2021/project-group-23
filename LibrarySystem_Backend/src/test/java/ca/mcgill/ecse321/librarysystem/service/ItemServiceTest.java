package ca.mcgill.ecse321.librarysystem.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.lenient;
import static org.mockito.ArgumentMatchers.anyString;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;

import java.util.ArrayList;
import java.util.List;

import ca.mcgill.ecse321.librarysystem.dao.ItemRepository;
import ca.mcgill.ecse321.librarysystem.model.Book;
import ca.mcgill.ecse321.librarysystem.model.Item;

@ExtendWith(MockitoExtension.class)
public class ItemServiceTest {

    @Mock
    private ItemRepository itemRepository;

    @InjectMocks
    private ItemService itemService;

    private static final int ITEM_ID = 42;
    private static final String ITEM_TITLE = "TestItem";

    private static final int NON_EXISTING_ITEM_ID = 24;
    private static final String NON_EXISTING_ITEM_TITLE = "NotAnItem";

    @BeforeEach
    public void setMockOutput() {
        lenient().when(itemRepository.findItemByItemId(anyInt())).thenAnswer((InvocationOnMock invocation) -> {
            if (invocation.getArgument(0).equals(ITEM_ID)) {
                Book item = new Book();
                item.setItemId(ITEM_ID);
                item.setTitle(ITEM_TITLE);
                return item;
            } else {
                return null;
            }
        });
        lenient().when(itemRepository.findItemByTitle(anyString())).thenAnswer((InvocationOnMock invocation) -> {
            if (invocation.getArgument(0).equals(ITEM_TITLE)) {
                Book item = new Book();
                item.setItemId(ITEM_ID);
                item.setTitle(ITEM_TITLE);
                ArrayList<Item> list = new ArrayList<Item>();
                list.add(item);
                return list;
            } else {
                return null;
            }
        });
        lenient().when(itemRepository.findAll()).thenAnswer((InvocationOnMock invocation) -> {
            Book item = new Book();
            item.setItemId(ITEM_ID);
            item.setTitle(ITEM_TITLE);
            ArrayList<Item> list = new ArrayList<Item>();
            list.add(item);
            return list;
        });
        // Whenever anything is saved, just return the parameter object
        Answer<?> returnParameterAsAnswer = (InvocationOnMock invocation) -> {
            return invocation.getArgument(0);
        };
        lenient().when(itemRepository.save(any(Item.class))).thenAnswer(returnParameterAsAnswer);

    }

    @Test
    public void testCreateBook() {
        Item item = null;
        String type = "BOOK";
        try {
            item = itemService.createItem(ITEM_TITLE, type);
        } catch (InvalidInputException e) {
            fail();
        }
        assertNotNull(item);
        assertEquals(type.toUpperCase(), item.getClass().getSimpleName().toUpperCase());
        assertEquals(ITEM_TITLE, item.getTitle());
    }

    @Test
    public void testCreateMovie() {
        Item item = null;
        String type = "mOviE";
        try {
            item = itemService.createItem(ITEM_TITLE, type);
        } catch (InvalidInputException e) {
            fail();
        }
        assertNotNull(item);
        assertEquals(type.toUpperCase(), item.getClass().getSimpleName().toUpperCase());
        assertEquals(ITEM_TITLE, item.getTitle());
    }

    @Test
    public void testCreateAlbum() {
        Item item = null;
        String type = "album";
        try {
            item = itemService.createItem(ITEM_TITLE, type);
        } catch (InvalidInputException e) {
            fail();
        }
        assertNotNull(item);
        assertEquals(type.toUpperCase(), item.getClass().getSimpleName().toUpperCase());
        assertEquals(ITEM_TITLE, item.getTitle());
    }

    @Test
    public void testCreateNewspapers() {
        Item item = null;
        String type = "Newspapers";
        try {
            item = itemService.createItem(ITEM_TITLE, type);
        } catch (InvalidInputException e) {
            fail();
        }
        assertNotNull(item);
        assertEquals(type.toUpperCase(), item.getClass().getSimpleName().toUpperCase());
        assertEquals(ITEM_TITLE, item.getTitle());
    }

    @Test
    public void testCreateArchive() {
        Item item = null;
        String type = "ArChIve";
        try {
            item = itemService.createItem(ITEM_TITLE, type);
        } catch (InvalidInputException e) {
            fail();
        }
        assertNotNull(item);
        assertEquals(type.toUpperCase(), item.getClass().getSimpleName().toUpperCase());
        assertEquals(ITEM_TITLE, item.getTitle());
    }

    @Test
    public void testCreateBookNullTitle() {
        Item item = null;
        String error = null;
        String type = "Book";
        String title = null;
        try {
            item = itemService.createItem(title, type);
        } catch (InvalidInputException e) {
            error = e.getMessage();
        }
        assertNull(item);
        assertEquals("Invalid title", error);

    }

    @Test
    public void testCreateBookNullType() {
        Item item = null;
        String error = null;
        String type = null;
        String title = "title";
        try {
            item = itemService.createItem(title, type);
        } catch (InvalidInputException e) {
            error = e.getMessage();
        }
        assertNull(item);
        assertEquals("Invalid item type", error);

    }

    @Test
    public void testCreateBookInvalidType() {
        Item item = null;
        String error = null;
        String type = "Comic Book";
        String title = "title";
        try {
            item = itemService.createItem(title, type);
        } catch (InvalidInputException e) {
            error = e.getMessage();
        }
        assertNull(item);
        assertEquals("Invalid item type", error);

    }

    @Test
    public void testGetExistingItemById() {
        assertEquals(ITEM_ID, itemService.getItem(ITEM_ID).getItemId());
    }

    @Test
    public void testGetExistingItemByTitle() {
        assertEquals(ITEM_ID, itemService.getItem(ITEM_TITLE).get(0).getItemId());
    }

    @Test
    public void testGetNonExistingItemById() {
        assertNull(itemService.getItem(NON_EXISTING_ITEM_ID));
    }

    @Test
    public void testGetNonExistingItemByTitle() {
        assertNull(itemService.getItem(NON_EXISTING_ITEM_TITLE));
    }

    @Test
    public void testDeleteNonExistingItem() {
        String error = null;
        Item item = null;
        try {
            item = itemService.deleteItem(NON_EXISTING_ITEM_ID);
        } catch (InvalidInputException e) {
            error = e.getMessage();
        }
        assertNull(item);
        assertEquals("Can't find item with ID = " + NON_EXISTING_ITEM_ID, error);
    }

    @Test
    public void testDeleteItem() {

        Item item = null;
        try {
            item = itemService.deleteItem(ITEM_ID);
        } catch (InvalidInputException e) {
            fail();
        }
        assertEquals(ITEM_ID, item.getItemId());
    }

    @Test
    public void testModifyItem() {
        Item item = null;
        try {
            item = itemService.modifyItem(ITEM_ID, "newTitle");
        } catch (InvalidInputException e) {
            fail();
        }
        assertNotNull(item);
        assertEquals(ITEM_ID, item.getItemId());
        assertEquals("newTitle", item.getTitle());
    }

    @Test
    public void testModifyNonExistingItem() {
        Item item = null;
        String error = null;
        try {
            item = itemService.modifyItem(NON_EXISTING_ITEM_ID, "newTitle");
        } catch (InvalidInputException e) {
            error = e.getMessage();
        }
        assertNull(item);
        assertEquals("Can't find item with ID = " + NON_EXISTING_ITEM_ID, error);
    }

    @Test
    public void testModifyItemWithInvalidTitle() {
        Item item = null;
        String error = null;
        try {
            item = itemService.modifyItem(ITEM_ID, " ");
        } catch (InvalidInputException e) {
            error = e.getMessage();
        }
        assertNull(item);
        assertEquals("Invalid new Title", error);
    }

    @Test
    public void testGetAllItems() {
        List<Item> items = new ArrayList<Item>();
        items = itemService.getAllItems();
        Item item = items.get(0);
        assertNotNull(item);
    }

}
