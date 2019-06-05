/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package icare.models;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author David Ortiz
 */
public class Storage {
    
    private ArrayList<User> userList;
    
    public Storage() throws FileNotFoundException{
        
        this.userList = fetchUsersFromFile();
        
        
        displayLoginsForTesting();
        
    }
    
    private void displayLoginsForTesting(){
        System.out.println("---------- Logins for testing ----------");
        for(User u : this.userList){
            System.out.println("Role: "+ u.getRoleType());
            System.out.println("User ID: "+ u.getUserID());
            System.out.println("Password: "+ u.getCredential().getPassword());
            System.out.println();
        }
        System.out.println("----------------------------------------");
        
    }

    public ArrayList<User> getUserList() {
        return userList;
    }
    
    public boolean doesUserExist(String id){
        for(User u : this.userList){
            if(u.getUserID().equals(id)){
                return true;
            }
            
        } 
        return false;
        
    }
    
    public User getUser(String id){
        
        User foundUser = null;
        for(User u : this.userList){
            if(u.getUserID().equals(id)){
                foundUser = u;
                
            }
            
        } 
        return foundUser;
        
    }
    
    public void addToUserList(User u){
        this.userList.add(u);
    }
    
    
    private static ArrayList<User> fetchUsersFromFile() throws FileNotFoundException{
        String fileName = "users.txt";
        
        ArrayList<User> users = new ArrayList<>();
         
        String line = null;
         
        int index = 0;
        
        try {
            FileReader fileReader = new FileReader(fileName);

            BufferedReader bufferedReader = new BufferedReader(fileReader);
            
            while((line = bufferedReader.readLine()) != null) {
                
                if(line != ""){
                    String[] type = line.split("~");
                    
                    if(type[0].equals("patient")){
                        
                        
                        String[] words = type[1].split(";");
                        String fname = words[0];
                        String lname = words[1];
                        String password = words[2];
                        
                        Patient tempPatient = new Patient(fname, lname);
                        tempPatient.setCredential(password);
                        users.add(tempPatient);
                        
                        
                    } else if(type[0].equals("staff")){
                        
                        String[] words = type[1].split(";");
                        String fname = words[0];
                        String lname = words[1];
                        String password = words[2];
                        
                        Staff tempStaff = new Staff(fname, lname);
                        tempStaff.setCredential(password);
                        
                        users.add(tempStaff);
                        
                        
                    } else {
                        
                        System.out.println("Invalid user");
                    }
                    
                    index++;
                } 
                
            }
            
            bufferedReader.close(); 
             
        } catch(FileNotFoundException ex) {
            System.out.println("Unable to open file '" + fileName + "'");                
        } catch(IOException ex) {
            System.out.println( "Error reading file '" + fileName + "'");   
        }
        return users;
         
    } // end fetchUsersFromFile()
    
    
}
