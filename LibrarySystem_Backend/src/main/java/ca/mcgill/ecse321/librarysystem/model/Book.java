package ca.mcgill.ecse321.librarysystem.model;

import javax.persistence.Entity;

@Entity
public class Book extends Item
{
    /*@Override
    public boolean canBeBorrowed(){
        return true;
    }*/

    public Book() {
        super.canBeBorrowed = true;
    }
}