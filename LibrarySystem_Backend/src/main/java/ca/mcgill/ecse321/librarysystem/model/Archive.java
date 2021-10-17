package ca.mcgill.ecse321.librarysystem.model;
import java.util.*;

public class Archive extends Item
{
    /*@Override
    public boolean canBeBorrowed(){
        return false;
    }*/
    public Archive(){
        super.canBeBorrowed = false;
    }
}