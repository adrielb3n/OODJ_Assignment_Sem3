/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package customerZD;

import Classes.IOMethods;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 *
 * @author ZD
 */
public class Payment {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        
    public ArrayList<String> showReceipt(String ID){
         ArrayList<ArrayList<String>> allBooking = IOMethods.readFile(IOMethods.BOOKINGTEXT);
         ArrayList<String> receiptDetails = new ArrayList<>();
         for(int i=0; i<allBooking.size();i++){
             if(allBooking.get(i).get(0).equals(ID)){
                 for(int x=0;x<=9;x++){
                     receiptDetails.add(allBooking.get(i).get(x));
                 }break;
             }
         }
         return receiptDetails;
    }
    
    public ArrayList<ArrayList<String>> filterPast(String userID){
        
        //load all bookings
        ArrayList<ArrayList<String>> allBooking = IOMethods.readFile(IOMethods.BOOKINGTEXT);
        
        
        ArrayList<ArrayList<String>> past = new ArrayList<>();
        for (int i =0;i<allBooking.size();i++){
            LocalDate date = LocalDate.parse(allBooking.get(i).get(3));
            LocalTime outTime = LocalTime.parse(allBooking.get(i).get(5));
            if(allBooking.get(i).get(2).equals(userID)){
                if (date.isBefore(LocalDate.now())){
                past.add(allBooking.get(i));
                }
                else if(date.isEqual(LocalDate.now())&& outTime.isBefore(LocalTime.now())){
                    past.add(allBooking.get(i));
                }
            }
        }System.out.println(past);
        return past;
    }
    
    public ArrayList<ArrayList<String>> filterComing(String userID){
        ArrayList<ArrayList<String>> allBooking = IOMethods.readFile(IOMethods.BOOKINGTEXT);
        ArrayList<ArrayList<String>> coming = new ArrayList<>();
        for (int i =0;i<allBooking.size();i++){
            LocalDate date = LocalDate.parse(allBooking.get(i).get(3));
            LocalTime inTime = LocalTime.parse(allBooking.get(i).get(4));
            if(allBooking.get(i).get(2).equals(userID)){
                if (date.isAfter(LocalDate.now())){
                coming.add(allBooking.get(i));
                }
                else if(date.isEqual(LocalDate.now())&& inTime.isAfter(LocalTime.now())){
                    coming.add(allBooking.get(i));
                }
            }
        }System.out.println(coming);
        return coming;
    }
    
    public double calPrice(LocalTime checkin,LocalTime checkout, double hPrice){
        Duration duration = Duration.between(checkin,checkout);
        double hour = Math.ceil(duration.toMinutes()/60.0);
        return hour*hPrice;
    }
    
    public String getHallName(String hallid){
          ArrayList<ArrayList<String>> allHall = IOMethods.readFile(IOMethods.HALLTEXT);
          for(ArrayList<String> item:allHall){
              if (item.get(0).equals(hallid)){
                  return item.get(1);
                }
          }return null;
        }
}
