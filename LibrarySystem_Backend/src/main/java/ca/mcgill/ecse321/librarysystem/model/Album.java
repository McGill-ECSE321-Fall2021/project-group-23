package ca.mcgill.ecse321.librarysystem.model;
import java.util.*;

public class Album extends Item
{
    @Override
    public boolean canBeBorrowed(){
        return true;
    }
}