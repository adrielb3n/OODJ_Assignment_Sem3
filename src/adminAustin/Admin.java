
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package adminAustin;

import Classes.IOMethods;
import Classes.validation;
import adminAustin.Login;
import Classes.User;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;

/**
 *
 * @author User
 */
public class Admin extends User implements validation{

    
    public Admin()
    {
        super();
        
    }


    
    public ArrayList<String> userToString(User user)
    {
        ArrayList<String> userArray = new ArrayList<String>();
        
        userArray.add(user.getuserID());
        userArray.add(user.getUsername());
        userArray.add(user.getphonenumber());
        userArray.add(user.getemail());
        userArray.add(user.getpassword());
        userArray.add(user.getrole());
        
        return userArray;
    }
    
    public void registerUser(String role, int index, User newuser)
    {
        Admin register = new Admin();
        ArrayList<ArrayList<String>> userList = register.getFullList();
        
        String name = newuser.getUsername();
        String phone = newuser.getphonenumber();
        String email = newuser.getemail();
        String password = newuser.getpassword();
       

        String userid = IOMethods.updateIndex(role, index);
        ArrayList <String> user = new ArrayList<String>();
        user.add(userid);
        user.add(name);
        user.add(phone); 
        user.add(email);
        user.add(password);
        user.add(role);
        
        userList.add(user);
        IOMethods.writeArrayListOfArrayList(IOMethods.USERTEXT, userList);
            
    }
        
        

    
public void deleteUser(String userID) {
    
        Admin a = new Admin();
        
        
        ArrayList<ArrayList<String>> userList = a.getFullList();
        userList.removeIf(row -> row.get(0).equals(userID));


        IOMethods.writeArrayListOfArrayList(IOMethods.USERTEXT,userList ,6);
        
    
//    File inputFile = new File("user.txt");
//    ArrayList<ArrayList<String>> userList = new ArrayList<>();
//
//    // Read the file and store relevant data in ArrayList
//    try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
//        String currentLine;
//
//        while ((currentLine = reader.readLine()) != null){
//            String[] parts = currentLine.split(";");
//            ArrayList<String> user = new ArrayList<>();
//            for (String part : parts) {
//                user.add(part);
//            }
//            userList.add(user);
//        }
//    } catch (IOException ex) {
//        ex.printStackTrace();
//    }
//
//    // Find and remove the row with the specified username
//    for (int i = 0; i < userList.size(); i++) {
//        if (userList.get(i).get(0).equals(username)) {
//            userList.remove(i);
//            break; // Exit the loop after removing the row
//        }
//    }
//
//    // Write the updated data back to the file
//    try (BufferedWriter writer = new BufferedWriter(new FileWriter(inputFile))) {
//        for (ArrayList<String> user : userList) {
//            writer.write(String.join(";", user));
//            writer.newLine();
//        }
//    } catch (IOException ex) {
//        JOptionPane.showMessageDialog(null, "Error writing to file: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
//    }
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
            //if email exist and doesnt matches self previous email
            if (email.equals(user.get(3)))
            {
                repeatMail = true;
                break;
            }
        }
        return repeatMail;
    }
    
    public void saveUserInfo(User newInfo)
    {
        Admin a = new Admin();
        ArrayList<ArrayList<String>> userList = a.getFullList();
        
        for (ArrayList<String> row: userList)
        {
            if (newInfo.getuserID().equals(row.get(0)))
            {
                row.set(1, newInfo.getUsername());
                row.set(2, newInfo.getphonenumber());
                row.set(3, newInfo.getemail());
                row.set(4, newInfo.getpassword());
                break;
            }
        }
        IOMethods.writeArrayListOfArrayList(IOMethods.USERTEXT, userList);

        
//       ArrayList<String[]> userlist = new ArrayList<>();
//       String line;
//       try(BufferedReader br = new BufferedReader(new FileReader("user.txt"))){
//           //parse value into userlist
//           while((line = br.readLine()) != null){
//               String[] parts = line.trim().split(";");
//               userlist.add(parts);
//           }
//           //replace value
//           for (int i = 0; i < userlist.size(); i++) {
//                String[] row = userlist.get(i);
//                if (row[0].equals(olduserid)) {
//                    userlist.set(i, new String[] {newinfo.getuserID(),newinfo.getUsername(),newinfo.getphonenumber(),newinfo.getemail(),newinfo.getpassword(),newinfo.getrole()});
//                    break; //exit loop
//                }
//            }
//            
//           //rewrite file
//            try (FileWriter writer = new FileWriter("user.txt")) {
//            for (String[] row : userlist) {
//                writer.write(String.join(";", row));
//                writer.write(System.lineSeparator());
//            }
//        } catch (IOException e) {
//                System.out.println(e.getMessage());
//        }
//            
//       } catch (FileNotFoundException ex) {
//            Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (IOException ex) {
//            Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }
    
    //used for appending 
    public ArrayList<ArrayList<String>> getFullList()
    {
        ArrayList<ArrayList<String>> userList = IOMethods.readFile(IOMethods.USERTEXT);
        return userList;
        
    }
    
    //use to validate (hide master admin account)
    public ArrayList<ArrayList<String>> getUserList()
    {
        ArrayList<ArrayList<String>> userList = IOMethods.readFile(IOMethods.USERTEXT);
        ArrayList<ArrayList<String>> filteredList = new ArrayList<>(userList.subList(1, userList.size()));
        return filteredList;
        
    }
    

}
