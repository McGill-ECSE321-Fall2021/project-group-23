package ca.mcgill.ecse321.librarysystem.dto;

public class ItemDto {
    private String title;
    private int itemId;
    private String status;
    private String type;

    public ItemDto() {
    }

    public ItemDto(int id, String title, String status, String type ) {
        this.itemId = id;
        this.title = title;
        this.status = status;
        this.type = type;
    }

    public int getId() {
        return itemId;
    }

    public String getTitle() {
        return title;
    }

    public String getStatus() {
        return status;
    }

    public String getType() {
        return title;
    }

    

}
