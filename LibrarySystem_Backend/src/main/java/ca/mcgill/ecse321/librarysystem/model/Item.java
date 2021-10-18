package ca.mcgill.ecse321.librarysystem.model;

import java.util.*;

import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "ITEM_TYPE")
public abstract class Item {
    private int itemId;

 
    @Id
    @GeneratedValue(generator = "idGenerator")
    public int getItemId() {
        return this.itemId;
    }

    public void setItemId(int id) {
        this.itemId = id;
    }

    /*
     * private Library library;
     * 
     * @ManyToOne(optional=false) public Library getLibrary() { return this.library;
     * }
     * 
     * public void setLibrary(Library newLibrary) { this.library = newLibrary; }
     */

    private String title;

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String newTitle) {
        this.title = newTitle;
    }

    public enum Status {
        AVAILABLE, BORROWED, RESERVED, DAMAGED, INCOMING
    }

    private Status status;

    public Status getStatus() {
        return this.status;
    }

    /**
     * Sets status using the Status enum (AVAILABLE, BORROWED, RESERVED, DAMAGED,
     * INCOMING)
     * 
     * @param newStatus
     */
    public void setStatus(Status newStatus) {
        this.status = newStatus;
    }

    /**
     * Sets status using a string (AVAILABLE, BORROWED, RESERVED, DAMAGED,
     * INCOMING). Not case sensitive
     * 
     * @param newStatus
     */
    public void setStatus(String newStatus) {
        newStatus = newStatus.toUpperCase();
        this.status = Status.valueOf(newStatus);
    }

    /**
     * Returns whether or not an item can be borrowed
     * 
     * @return
     */
    // abstract boolean canBeBorrowed();
    public boolean canBeBorrowed;

    /*public boolean getCanBeBorrowed() {
        return this.canBeBorrowed;
    }

    public void setCanBeBorrowed(String newTitle) {
        this.title = newTitle;
    }*/
}