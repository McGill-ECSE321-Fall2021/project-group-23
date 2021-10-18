package ca.mcgill.ecse321.librarysystem.model;
import java.util.*;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Library
{
    public Library(String name, String address, String phoneNum, String emailAddress){
        this.name= name;
        this.address = address;
        this.phoneNumber=phoneNum;
        this.emailAddress= emailAddress;
    }

    private String name;
    
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }

    private String address;
    public String getAddress() {
        return this.address;
    }
    public void setAddress(String address) {
        this.address = address;
    }

    private String phoneNumber;
    public String getPhoneNumber() {
        return this.phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    private String emailAddress;
    public String getEmailAddress() {
        return this.emailAddress;
    }
    public void setEmailAddress(String address) {
        this.emailAddress = address;
    }

    private Set<Reservation> reservations;
    @OneToMany(cascade = {CascadeType.ALL})
    public Set<Reservation> getReservations() {
        return this.reservations;

    }
    public void setReservations(Set<Reservation> reservationss) {
        this.reservations = reservationss;
    }

    private Set<Item> items;
    @OneToMany(cascade = {CascadeType.ALL})
    public Set<Item> getItems() {
        return this.items;

    }
    public void setItems(Set<Item> itemss) {
        this.items = itemss;
    }

    private Set<Customer> customers;
    @OneToMany(cascade = {CascadeType.ALL})
    public Set<Customer> getCustomers() {
        return this.customers;

    }
    public void setCustomers(Set<Customer> customerss) {
        this.customers = customerss;
    }

    private Set<Librarian> librarians;
    @OneToMany(cascade = {CascadeType.ALL})
    public Set<Librarian> getLibrarians() {
        return this.librarians;

    }
    public void setLibrarians(Set<Librarian> librarianss) {
        this.librarians = librarianss;
    }

    private HeadLibrarian headLibrarian;
    @OneToOne(cascade = {CascadeType.ALL})
    public HeadLibrarian getHeadLibrarian() {
        return this.headLibrarian;

    }
    public void setHeadLibrarian(HeadLibrarian headLibrarian) {
        this.headLibrarian = headLibrarian;
    }

    private Set<LibraryBooking> libraryBookings;
    @OneToMany(cascade = {CascadeType.ALL})
    public Set<LibraryBooking> getLibraryBookings() {
        return this.libraryBookings;

    }
    public void setLibraryBookings(Set<LibraryBooking> libraryBookingss) {
        this.libraryBookings = libraryBookingss;
    }

    private Set<Holiday> holidays;
    @OneToMany(cascade = {CascadeType.ALL})
    public Set<Holiday> getHolidays() {
        return this.holidays;

    }
    public void setHolidays(Set<Holiday> holidayss) {
        this.holidays = holidayss;
    }

    private Set<OpeningsHours> openingsHours;
    @OneToMany(cascade = {CascadeType.ALL})
    public Set<OpeningsHours> getOpeningsHours() {
        return this.openingsHours;

    }
    public void setOpeningsHours(Set<OpeningsHours> openingsHourss) {
        this.openingsHours = openingsHourss;
    }

    private int id;
    public void setId(int value) {
        this.id = value;
    }
    @Id
    public int getId() {
        return this.id;
    }


}