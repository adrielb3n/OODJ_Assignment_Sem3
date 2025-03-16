/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package customerZD;

import Classes.IOMethods;
import Classes.User;
import Classes.validation;
import adminAustin.Admin;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;

/**
 *
 * @author ZD
 */
public class Customer extends User implements validation
{
    //automatically load all the user details
    private ArrayList<ArrayList<String>> allUser = IOMethods.readFile(IOMethods.USERTEXT);
    
    
    Customer()
    {
        allUser = IOMethods.readFile(IOMethods.USERTEXT);
    }

    
    
    public ArrayList<String> loadProfile(String userID){
        ArrayList<String> userDetails = new ArrayList<>();
        for (ArrayList<String> user: allUser)
        {
            if (userID.equals(user.get(0)))
            {
                userDetails = user;
                break;
            }
        }
        
        
//        for(int i=0; i<allUser.size();i++){
//            //if user id matchs
//            if(allUser.get(i).get(0).equals("user111")){
//                //loop to load all targets user details
//                for(int x=0;x<=4;x++){
//                    userDetails.add(allUser.get(i).get(x));
//                }break;
//            }
//            }
        return userDetails;
    } 
    
    @Override
    public boolean validateEmail(String email) {
    String emailRegex = "^[\\w-\\.]+@[\\w-]+\\.com.*$";
    Pattern pattern = Pattern.compile(emailRegex);
    Matcher matcher = pattern.matcher(email);
    return matcher.matches();
}
    @Override
    public boolean validatePhoneNumber(String phone) {
        String phoneRegex = "^[0-9]+$";
        Pattern pattern = Pattern.compile(phoneRegex);
        Matcher matcher = pattern.matcher(phone);
        return matcher.matches();
    }  
    @Override
        public boolean repeatedEmail(String email)
    {
        Admin admin = new Admin();
      
        //
        boolean repeatMail = false;
        ArrayList<ArrayList<String>> filteredList = admin.getUserList();
        //No validation Austin
        for (ArrayList user: filteredList)
        {
            if (email.equals(user.get(3)))
            {
                repeatMail = true;
                break;
            }
        }
        return repeatMail;
    }

    
    public void updateProfile(String userID,String name, String email, String phone, String pass){
        ArrayList<ArrayList<String>> update = allUser;
        
        for (ArrayList<String> currentUser: update)
        {
            if (currentUser.get(0).equals(userID))
            {
                currentUser.set(1,name);
                currentUser.set(2,phone);
                currentUser.set(3,email);
                currentUser.set(4,pass);
               

            }
           
        }
        IOMethods.writeArrayListOfArrayList(IOMethods.USERTEXT, update);
        
        
//        for (int i = 0; i < update.size(); i++) {
//        ArrayList<String> currentUser = update.get(i);
//                if(currentUser.get(0).equals("user111")){
//                        currentUser.set(1,name);
//                        currentUser.set(2,email);
//                        currentUser.set(3,phone);
//                        currentUser.set(4,pass);
//                    }break;
//                }
    }
}
