package ca.mcgill.ecse321.librarysystem.model;
import javax.persistence.Entity;
import javax.persistence.Id;


@Entity
public class Library
{
    public Library(){
        
    }
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

    private int id;
    public void setId(int value) {
        this.id = value;
    }
    @Id
    public int getId() {
        return this.id;
    }


}