package ca.mcgill.ecse321.librarysystem.model;

import javax.persistence.Entity;

@Entity
public class Movie extends Item
{
    /*@Override
    public boolean canBeBorrowed(){
        return true;
    }*/

    public Movie() {
        super.canBeBorrowed = true;
    }
}