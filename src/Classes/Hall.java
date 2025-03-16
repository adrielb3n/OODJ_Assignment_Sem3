/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Classes;

import java.io.File;
import java.time.LocalDate;
import java.time.temporal.ChronoField;
import java.util.*;

/**
 *
 * @author Hg
 */
public class Hall 
{
    private String hallID;
    private String hallName;
    private double pricePerHour;
    private char hallType;
    private int capacity;
    private String description;
//    private String imagePath;
    private ArrayList<ArrayList<String>> hallList;
    private ArrayList<ArrayList<String>> booking;
    
    //constant for defaultPricePerHour
    public static final double AUDIPRICE = 300.00;
    public static final double BANQUETPRICE = 100.00;
    public static final double CONFROOMPRICE = 50.00;
    
    
    
    //constructor
    public Hall()
    {
       hallList = IOMethods.readFile(IOMethods.HALLTEXT);
    }
    
    public Hall(String hallID, String hallName, double pricePerHour,char hallType,int capacity, String description)
    {
        this.hallID = hallID;
        this.hallName = hallName;
        this.pricePerHour = pricePerHour;
        this.hallType = hallType;
        this.capacity = capacity;
        this.description = description;
        this.hallList = IOMethods.readFile(IOMethods.HALLTEXT);
    }
   
    
    //accessor method (get & set)
    
    public String getHallID()
    {
        return this.hallID;
    }
    public void setHallID(String hallID)
    {
        this.hallID = hallID;
    }
    public void setHallName(String newHallName)
    {
        this.hallName = newHallName;
    }
    
    public String getHallName()
    {
        return this.hallName;
    }
    
    public void setPricePerHour(double pricePerHour)
    {
        this.pricePerHour = pricePerHour;
    }
    
    public double getPricePerHour()
    {
        return this.pricePerHour;
    }
    
    public void setHallType(char hallType)
    {
        this.hallType = hallType;
    }
    
    public char getHallType()
    {
        return this.hallType;
    }
    
    public void setDescription(String description)
    {
        this.description = description;
        
    }
    public String getDescription()
    {
        return this.description;
    }
    public void setCapacity(int capacity)
    {
        this.capacity = capacity;
        
    }
    public int getcapacity()
    {
        return this.capacity;
    }

    public void setHallList(ArrayList<ArrayList<String>> hallList)
    {
        this.hallList = hallList;
    }
    
    public ArrayList<ArrayList<String>> getHallList()
    {
        return this.hallList;
    }
    
    
    //convert object to ArrayList<String>
    public ArrayList<String> toStringArrayList()
    {
        ArrayList<String> hallArray = new ArrayList<String>();
       hallArray.add(hallID);
       hallArray.add(hallName);
       hallArray.add(Character.toString(hallType));
       hallArray.add(""+pricePerHour);
       hallArray.add(""+capacity);
       hallArray.add(description);
       
       return hallArray;
       
        
    }
    
    
    //methods 
    public void readHall()
    {
   
        hallList = IOMethods.readFile(IOMethods.HALLTEXT);
        
    }
    
    public void saveHall()
            
    {
        IOMethods.writeArrayListOfArrayList(IOMethods.HALLTEXT, hallList);
    }
    

    

    
}
