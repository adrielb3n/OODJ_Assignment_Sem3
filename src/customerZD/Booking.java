/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package customerZD;

import Classes.IOMethods;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JOptionPane;

/**
 *
 * @author ZD
 */
public class Booking {
    private ArrayList<ArrayList<String>> hall = new ArrayList<ArrayList<String>>();
    private ArrayList<ArrayList<String>> banquet =new ArrayList<ArrayList<String>>();
    private ArrayList<ArrayList<String>> meeting = new ArrayList<ArrayList<String>>();;
    
    public void CreateBooking(String HallId,String UserId,String date,String checkin,String checkout, String pax, String bookingType, String request, String price){
        try{
            ArrayList<String> book = new ArrayList<>();
            String bookingID = IOMethods.updateIndex("BK", 6);
            book.add(bookingID);
            book.add(HallId);
            book.add(UserId);
            book.add(date);
            book.add(checkin);
            book.add(checkout);
            book.add(pax);
            book.add(bookingType);
            book.add(request);
            book.add(price);
            LocalDateTime currentDateTime = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String formattedDateTime = currentDateTime.format(formatter);

            FileWriter fw = new FileWriter("booking.txt",true);
            fw.write(book.get(0)+";"+book.get(1)+";"+book.get(2)+";"+book.get(3)+";"+book.get(4)+";"+book.get(5)+";"+book.get(6)+";"+book.get(7)+";"+book.get(8)+";"+book.get(9)+";"+formattedDateTime+"\n");
            fw.close();
            JOptionPane.showMessageDialog(null, "booked");
        }catch(IOException e){
            System.out.println("Error"+e.getMessage());
        }
    }
    
    public void bookingCancel(String id){
        ArrayList<ArrayList<String>> allBooking=IOMethods.readFile(IOMethods.BOOKINGTEXT);
        ArrayList<ArrayList<String>> updated = new ArrayList<ArrayList<String>>();
        for(ArrayList<String> item : allBooking){
            if (!item.get(0).equals(id)){
                updated.add(item);
            }
        }
        IOMethods.writeArrayListOfArrayList(IOMethods.BOOKINGTEXT, updated);
    }
    
    public void SetHallList(){
        ArrayList<ArrayList<String>> loadedData=IOMethods.readFile(IOMethods.HALLTEXT);
        for (int i=0;i<loadedData.size();i++){
            switch(loadedData.get(i).get(2).charAt(0)){
                case 'A':
                    hall.add(loadedData.get(i));
                    break;
                case 'B':
                    banquet.add(loadedData.get(i));
                    break;
                case 'C':
                    meeting.add(loadedData.get(i));
                    break;
            }
        }
    }
    
    public ArrayList<String> checkAvailability(String hallid,LocalDate date,LocalTime intime,LocalTime outtime){
        ArrayList<ArrayList<String>> allBooking=IOMethods.readFile(IOMethods.BOOKINGTEXT);
        for(ArrayList<String> item:allBooking){
            //look for related hallid
            if (hallid.equals(item.get(1))){
                //check is there booking at the same day
                if (date.equals(LocalDate.parse(item.get(3)))){
                    if (intime.isBefore(LocalTime.parse(item.get(5)))&&outtime.isAfter(LocalTime.parse(item.get(4)))){
                        return item;
                    }
                }
            }
        }return null;
   }
    
    
    public ArrayList<ArrayList<String>> getAudi(){
        return hall;
    }
    
     public ArrayList<ArrayList<String>> getHall(){
        return banquet;
    }
     
      public ArrayList<ArrayList<String>> getMeeting(){
        return meeting;
    }
      
}
    

