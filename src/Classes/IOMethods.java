/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Classes;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author Hg
 */
public class IOMethods 
{
    //HallText = 
    public static final String HALLTEXT = "hall.txt";
    public static final String SCHEDULETEXT = "schedule.txt";
    public static final String INDEXTEXT = "index.txt";
    public static final String BOOKINGTEXT = "booking.txt";
    public static final String REPORTTEXT = "report.txt";
    public static final String USERTEXT = "user.txt";
    
    
   
    //create a method to read rows and convert from String to ArrayList<String>
    //@param text = text file name
    //@return ArrayList<ArrayList<String>> array list of rows in the text file
    
    public static ArrayList<ArrayList<String>> readFile(String text)
    {
        var array1 = new ArrayList<ArrayList<String>>();
        
        try
        {
            File f = new File(text);
            if (!f.exists())
            {
                f.createNewFile();
            }
            else
            {
                FileReader fr = new FileReader(text);
                BufferedReader br = new BufferedReader(fr);
                String read = br.readLine();

                //iterate till the first empty column (null)
                while(read != null)
                {
                        //split string into array (String type) using ";" as separator
                        String[] arraysplit = read.split(";");
                        //convert array into arraylist "alist"
                        ArrayList<String> alist = new ArrayList<>(Arrays.asList(arraysplit));
                        //add ArrayList "alist" to ArrayList"array1" 
                        array1.add(alist);

                    read = br.readLine();
                }
                //close buffered reader
                br.close();
                
            }
            //Use fileReader to read files from text (textfile parameter)
            
        }
        catch (FileNotFoundException e)
        {
            System.out.println("No file found");
        }
        catch (IOException f)
        {
            System.out.println("error reading file: " + text);
        }
        return array1;
    }
    
    //create a static method to convert ArrayList of arraylist into a string 
    //with ";" as seperator and store it in text file
    
    //@param file file name of the output file
    //@param input_array the arraylist of arraylist which is overwritting the text file
    
    
    //Polymorphism
    
    
    //with int columns (use to validate column numbers before saving (normal saving txt))
    public static void writeArrayListOfArrayList(String file, ArrayList<ArrayList<String>> input_array, int columns)
    {
        try
        {
            File f = new File(file);
            if (!f.exists())
            {
                f.createNewFile();
            }
            FileWriter fw = new FileWriter(file);
           
            for(ArrayList<String> row : input_array)
            {
                if (row.size()==columns)
                {
                    for(int i = 0; i < row.size();i++)
                    {
                       fw.write(row.get(i));
                       if (i < row.size() - 1)
                       {
                           fw.write(";");
                       }
                       else
                       {
                           fw.write("\n");
                       }
                    
                }
            }
        }
            //close filewriter
            fw.close();
    }
        catch (IOException e)
        {
            System.out.println("Cannot find file to write!");
        }
    }
    
    //without int columns (use to update index number)
    public static void writeArrayListOfArrayList(String file, ArrayList<ArrayList<String>> input_array)
    {
        try
        {
            File f = new File(file);
            if (!f.exists())
            {
                f.createNewFile();
            }
            FileWriter fw = new FileWriter(file);
           
            for(ArrayList<String> row : input_array)
            {

                for(int i = 0; i < row.size();i++)
                {
                   fw.write(row.get(i));
                   if (i < row.size() - 1)
                   {
                       fw.write(";");
                   }
                   else
                   {
                       fw.write("\n");
                   }

                
            }
        }
            //close filewriter
            fw.close();
    }
        catch (IOException e)
        {
            System.out.println("Cannot find file to write!");
        }
    }
    
   /*returns a string and auto index increment
   
    @param Initial = initial of the index (1-2 characters) (eg: HallID = H)
    @param index = index of number accessed in index.txt (written in index.txt)
    @Return a string that can be placed in the first column as unique identifier
    */
    public static String updateIndex(String Initial, int index)
    {
        
        String returnID;
        //get index from index.txt
        ArrayList<ArrayList<String>> indexList = IOMethods.readFile(IOMethods.INDEXTEXT);
        String currentIndex = indexList.get(2).get(index);

        //change format to 4-digit numbers, add leading zero if number.length less than 4
        DecimalFormat df = new DecimalFormat("0000000");
        returnID = Initial + df.format(Integer.parseInt(currentIndex));
        
        // increment  index  number by one  in index.txt
        currentIndex = "" + (Integer.parseInt(currentIndex)+ 1);
        //set new index number in column 6
        //please refer to documentation in index.txt. TQ :)
        indexList.get(2).set(index,currentIndex);
        IOMethods.writeArrayListOfArrayList(IOMethods.INDEXTEXT, indexList);
        return returnID;
    }
    
    public static void initiateIndex()
    {
        try
        {
            File f = new File(IOMethods.INDEXTEXT);
                     
            f.createNewFile();
            
            FileWriter fw = new FileWriter(IOMethods.INDEXTEXT);
            fw.write("This is a txt file to store all indexes\n");
            fw.write("0 UserID -> 1 AdminID -> 2 CustomerID -> 3 ManagerID -> 4 SchedulerID -> 5 HallID -> 6 BookingID -> 7 ReportID -> 8 MaintenanceID \n");
            
            for(int i = 0; i < 9;i++)
            {
               fw.write("1");
               if (i < 8)
               {
                   fw.write(";");
               }
               else
               {
                   fw.write("\n");
               }
            }
            fw.close();
            
            f = new File(IOMethods.USERTEXT);
            
            f.createNewFile();
            
            fw = new FileWriter(IOMethods.USERTEXT);
            fw.write("000;admin;0;admin;admin;A\n");
            
            //close filewriter
            fw.close();
        }
        catch (IOException e)
        {
            System.out.println("Cannot find file to write!");
        }
    }
    

}
