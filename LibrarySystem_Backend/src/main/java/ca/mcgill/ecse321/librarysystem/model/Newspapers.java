package ca.mcgill.ecse321.librarysystem.model;

import javax.persistence.Entity;

@Entity
public class Newspapers extends Item
{
    /*@Override
    public boolean canBeBorrowed(){
        return false;
    }*/

    public Newspapers() {
        super.canBeBorrowed = false;
    }
}