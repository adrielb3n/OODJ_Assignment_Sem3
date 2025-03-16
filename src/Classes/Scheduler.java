/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Classes;


import java.io.*;
import java.io.FileWriter;
import java.io.IOException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.*;

/**
 *
 * @author Hg
 */
public class Scheduler extends User
{
    private String userID;
    private ArrayList<String> scheduler_edit;
    private static ArrayList<ArrayList<String>> schedule;

    
    public Scheduler()
    {
        
    }
    
    public Scheduler(String userID, String name, String phoneNumber, String email, String password, String role)
    {
        super(userID, name,phoneNumber, email, password, role);
    }
    
    
    public Scheduler(String userID)
    {
        //for loop to look for userID in users.txt
        this.userID = userID;
        
        
    }
    
    
    public void addHall(ArrayList<String>hallNewLine)
    {
        
        ArrayList<ArrayList<String>> newHallList = IOMethods.readFile(IOMethods.HALLTEXT);
        newHallList.add(hallNewLine);
        //rewrite hall.txt with new ArrayList
        IOMethods.writeArrayListOfArrayList(IOMethods.HALLTEXT, newHallList,6);
    }
    
    public void editHall(String hallID, String hallName, String hallType, String price,String capacity ,String description)
    {
        //read halls
       Hall edithall = new Hall();
       ArrayList<ArrayList<String>> newHallList = edithall.getHallList();
       
       //change elements in array (searched by Hall ID)
        for (ArrayList<String> row: newHallList)
        {
            if (row.get(0).equals(hallID))
            {
                row.set(1, hallName);
                row.set(2, hallType);
                row.set(3, price);
                row.set(4, capacity);
                row.set(5, description);
                break;
            }
        }
        // rewrite the hall 
       IOMethods.writeArrayListOfArrayList(IOMethods.HALLTEXT, newHallList,6);
    }
    
    public void editHall(ArrayList<String> hall)
    {
        //read halls
       Hall edithall = new Hall();
       ArrayList<ArrayList<String>> newHallList = edithall.getHallList();
       
       //change elements in array (searched by Hall ID)
        for (ArrayList<String> row: newHallList)
        {
            if (row.get(0).equals(hall.get(0)))
            {
                row.set(1, hall.get(1));
                row.set(2, hall.get(2));
                row.set(3, hall.get(3));
                row.set(4, hall.get(4));
                row.set(5, hall.get(5));
//                row.set(2, hallType);
//                row.set(3, price);
//                row.set(4, capacity);
//                row.set(5, description);
                break;
            }
        }
        // rewrite the hall 
       IOMethods.writeArrayListOfArrayList(IOMethods.HALLTEXT, newHallList,6);
    }
    
    //Delete hall
    public void deleteHall(String hallID)
    {
        //create new hall instance
        ArrayList<ArrayList<String>> newHallList = IOMethods.readFile(IOMethods.HALLTEXT);
        //delete by hallID
        newHallList.removeIf(row -> row.get(0).equals(hallID));     
        IOMethods.writeArrayListOfArrayList(IOMethods.HALLTEXT, newHallList,6);
    }
    
    //create a new booking
    public void addEvent(ArrayList<String> newBooking)
    {
       ArrayList<ArrayList<String>> bookingList = IOMethods.readFile(IOMethods.BOOKINGTEXT);
       bookingList.add(newBooking);
       IOMethods.writeArrayListOfArrayList(IOMethods.BOOKINGTEXT,bookingList,11);
    }
    
    
    //Change status for assigned task by manager
    public void changeMaintenanceStatus(String reportID, String status)
    {
        //read reports list
        ArrayList<ArrayList<String>> reportList = IOMethods.readFile(IOMethods.REPORTTEXT);
        
        
        for (ArrayList<String> row : reportList)
        {
            if (row.get(0).equals(reportID))
            {
                row.set(3, status);
                break;
            } 
        }
        
        //rewrite text file
        IOMethods.writeArrayListOfArrayList(IOMethods.REPORTTEXT, reportList,6);
    }
    
    //change schedule startDate to today's date and endDate to three weeks from 
    //today;
    public static void pushSchedule()
    {
        //export as ArrayList<ArrayList<String>> although its just one entry
        schedule = IOMethods.readFile(IOMethods.SCHEDULETEXT);
        
        // get today's date 
        Calendar now = Calendar.getInstance();
        LocalDate today = LocalDate.now();
//        int numberOfDays = 21;
        //check if the date today is a Monday
        ArrayList newSchedule = new ArrayList<String>();
        if (now.get(Calendar.DAY_OF_WEEK) == (Calendar.MONDAY))
        {
            //change first column (startDate) to today's date
            newSchedule.add(today.toString());
            LocalDate next3Weeks = today.plusWeeks(3);
            
            //change second column (endDate) to the Monday after 3 weeks
            newSchedule.add(next3Weeks.toString());
            
            //clear previous record before overwriting the ArrayList<ArrayList<String>>schedule

        }
        //system may not be initiated on a Monday
        else
            
        {
            LocalDate thisWeekMonday = today.with(DayOfWeek.MONDAY);
            newSchedule.add(thisWeekMonday.toString());
            
            LocalDate next3Weeks = thisWeekMonday.plusWeeks(3);
            // Set endDate to the Monday after 3 weeks
            newSchedule.add(next3Weeks.toString());
            

        }
        schedule.clear();
        schedule.add(newSchedule);
            
        
        IOMethods.writeArrayListOfArrayList(IOMethods.SCHEDULETEXT, schedule);
    }
}

