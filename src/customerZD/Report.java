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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JOptionPane;

/**
 *
 * @author ZD
 */
public class Report {
    private String GenerateID(){
        try{
            //create booking file if not exist
            File file = new File("report.txt");
            if(!file.exists()){
                file.createNewFile();
                System.out.println("report.txt created");
            }
            
            FileReader fr = new FileReader("report.txt");
            BufferedReader br = new BufferedReader(fr);
            ArrayList<String> reportId = new ArrayList<>();
            String read;
            //load all created ID in array
            while ((read = br.readLine())!= null){
                reportId.add(read.split(";")[0]);
            }
            br.close();
            
            //generate random ID and check with the array
            Random rand = new Random();
            String newID;
            do{
                newID = "RPT"+rand.nextInt(100000);
            }while (reportId.contains(newID)); 
            return newID;
                
    }catch(IOException e){
            System.out.println("Exception Error:"+e.getMessage());
    } 
        return null;
 }    
    public void addReport(String bookingID,String des){
        try{    
            ArrayList<String> report = new ArrayList<>();
            report.add(IOMethods.updateIndex("RP", 7));
            report.add(bookingID);
            report.add(des);
            LocalDateTime currentDateTime = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            String formattedDateTime = currentDateTime.format(formatter);

            FileWriter fw = new FileWriter("report.txt",true);
            fw.write(report.get(0)+";"+report.get(1)+";"+report.get(2)+";"+"unassigned"+";"+""+";"+formattedDateTime+"\n");
            fw.close();
            JOptionPane.showMessageDialog(null, "Submitted Report");
        }catch (IOException e){
            System.out.println("Error"+e.getMessage());
        }
    }
}
