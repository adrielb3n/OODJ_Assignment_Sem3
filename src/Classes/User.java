/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Classes;


import Classes.IOMethods;
import java.util.ArrayList;
//import adminAustin.IOMethods;
//import java.io.BufferedReader;
//import java.io.FileReader;
//import java.io.FileWriter;
//import java.io.IOException;
//import javax.swing.JOptionPane;

/**
 *
 * @author User
 */
public class User {
    private String userID;//
    private String Username;//
    private String phonenumber;//
    private String email;//
    private String password;//
    private String role;
    
    
    public User()
    {
        

    }
    
    public User(String userID, String username, String phonenumber, String email, String password, String role)
    {
        this.userID = userID;
        this.Username = username;
        this.phonenumber = phonenumber;
        this.email = email;
        this.password = password;
        this.role = role;
    }
    public String getUsername(){
        return Username;
    }
    
    public void setUsername(String Username){
        this.Username = Username;
    }
    
    public String getuserID(){
        return userID;
    }
    
    public void setuserID(String userID){
        this.userID = userID;
    }
    
    public String getpassword(){
        return password;
    }
    
    public void setpassword(String password){
        this.password = password;
    }
    
    public String getemail(){
        return email;
    }
    
    public void setemail(String email){
        this.email = email;
    }
    
    public String getphonenumber(){
        return phonenumber;
    }
    
    public void setphonenumber(String phonenumber){
        this.phonenumber = phonenumber;
    } 
    
    public String getrole(){
        return role;
    }
    
    public void setrole(String role){
        this.role = role;
    }
    
    
    public ArrayList<String> login(String emailInput, String password){
        ArrayList<String> returnList = new ArrayList<String>();
        boolean status = false;
        
        try 
        {
            // TODO add your handling code here:
            ArrayList<ArrayList<String>> userList = IOMethods.readFile(IOMethods.USERTEXT);

            for (ArrayList<String> user: userList)
            {
          
                if (emailInput.equals(user.get(3)) && password.equals(user.get(4)))
                {
                    status = true;
                    this.userID = user.get(0);
                    this.role = user.get(5);
                    break;

                }
            }
            
            if (status)
            {
                returnList.add(String.valueOf(status));
                returnList.add(this.userID); //userID
                returnList.add(this.role); //user Role
            }
            else
            {
                returnList.add(String.valueOf(status));
                returnList.add(null); //null if not successful login
                returnList.add(null); //null if not successful login
            }
                   
            return returnList;
            
        }
        
        
        catch (Exception e) {
            System.out.println("Exception Error:"+e.getMessage());
        }        
          return returnList;
    }
    
    
    
    public void logout()
    {
        System.exit(0);
    }
    
    
    

    
    
    
}

