package ca.mcgill.ecse321.librarysystem.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import ca.mcgill.ecse321.librarysystem.dto.ItemDto;
import ca.mcgill.ecse321.librarysystem.model.Item;
import ca.mcgill.ecse321.librarysystem.service.ItemService;

@CrossOrigin(origins = "*")
@RestController
public class ItemController {
    @Autowired
    private ItemService itemService;


    /**
     * Return a list of every item Dtos
     * 
     * @return list of item Dtos
     */
    @GetMapping(value = { "/getAllItems", "/getAllItems/" })
    public List<ItemDto> getAllItems() {
        return itemService.getAllItems().stream().map(u -> convertToDto(u)).collect(Collectors.toList());
    }

    /**
     * Return a list of every item Dtos
     * 
     * @return list of item Dtos
     */
    @GetMapping(value = { "/getItemById/{stringId}", "/getItemById/{stringId}/" })
    public ItemDto getItemById(@PathVariable("stringId") String stringId) {

        return convertToDto(itemService.getItem(Integer.valueOf(stringId)));
    }

    /**
     * Return a list of item Dtos with the input title
     * 
     * @return list of item Dtos
     */
    @GetMapping(value = { "/getItemsByTitle/{title}", "/getItemsByTitle/{title}/" })
    public List<ItemDto> getItemsByTitle(@PathVariable("title") String title) {
        return itemService.getItem(title).stream().map(u -> convertToDto(u)).collect(Collectors.toList());
    }

    /**
     * creates an item given a title and type (book, movie, album, newspapers,
     * archive)
     * 
     * @param title
     * @param type
     * @return
     */
    @PostMapping(value = { "/createItem/{title}/{type}", "/createItem/{title}/{type}/" })
    public ItemDto createItem(@PathVariable("title") String title, @PathVariable("type") String type) {
        Item item = itemService.createItem(title, type);
        return convertToDto(item);
    }

    /**
     * deletes an item given the id
     * 
     * @param stringId
     */
    @DeleteMapping(value = { "/deleteItem/{stringId}", "/deleteItem/{stringId}/" })
    public void deleteItem(@PathVariable("stringId") String stringId) {
        itemService.deleteItem(Integer.valueOf(stringId));
        ;
        return;
    }

    /**
     * Modifies an item's title, given a valid ID
     * 
     * @param stringId
     * @param newTitle
     * @return
     */
    @PutMapping(value = { "/modifyItem/{stringId}/{newTitle}", "/deleteItem/{stringId}/{newTitle}/" })
    public ItemDto modifyItem(@PathVariable("stringId") String stringId, @PathVariable("newTitle") String newTitle) {
        itemService.modifyItem(Integer.valueOf(stringId), newTitle);
        ;
        return convertToDto(itemService.getItem(Integer.valueOf(stringId)));
    }

    /**
     * Converts an item object into an itemDto
     * 
     * @param item
     * @return
     */
    public ItemDto convertToDto(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("There is no such item");
        }
        String type = item.getClass().getSimpleName();
        ItemDto itemDto = new ItemDto(item.getItemId(), item.getTitle(), item.getStatus().toString(), type);
        return itemDto;
    }

    /**
     * converts an itemDto to an Item object
     * 
     * @param itemDto
     * @return item
     */
    public Item convertToDomainObject(ItemDto itemDto) {
        List<Item> items = itemService.getAllItems();
        for (Item item : items) {
            if (item.getItemId() == itemDto.getId()) {
                return item;
            }
        }
        return null;
    }
}
