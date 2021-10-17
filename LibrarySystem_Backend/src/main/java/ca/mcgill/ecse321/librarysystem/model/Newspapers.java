package ca.mcgill.ecse321.librarysystem.model;
import java.util.*;

public class Newspapers extends Item
{
    @Override
    public boolean canBeBorrowed(){
        return false;
    }
}