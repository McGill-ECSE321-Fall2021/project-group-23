package ca.mcgill.ecse321.librarysystem.model;
import java.util.*;

import javax.persistence.Entity;

@Entity
public class Album extends Item
{
    /*@Override
    public boolean canBeBorrowed(){
        return true;
    }*/

    public Album() {
        super.canBeBorrowed = true;
    }
}