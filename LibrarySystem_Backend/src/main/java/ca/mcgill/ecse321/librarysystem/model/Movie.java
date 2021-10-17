package ca.mcgill.ecse321.librarysystem.model;
import java.util.*;

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