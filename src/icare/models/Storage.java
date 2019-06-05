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
    
    /**
     * Default constructor for this class. 
     * @throws java.io.FileNotFoundException
     */
    public Storage() throws FileNotFoundException{
        
        this.userList = fetchUsersFromFile();
        
        
        displayLoginsForTesting();
        
    }
    
    /**
     * Returns the User List 
     * @return An ArrayList of User types
     */
    public ArrayList<User> getUserList() {
        return userList;
    }
    
    /**
     * Determines if a User exists using the ID parameter. 
     * @param id Used to compare to User's ID to determine if User exists.
     * @return A boolean determining if User exists in UserList
     */
    public boolean doesUserExist(String id){
        for(User u : this.userList){
            if(u.getUserID().equals(id)){
                return true;
            }
            
        } 
        return false;
        
    }
    
    /**
     * Gets the User whose ID matches the ID passed in. 
     * @param id Used to identify User to fetch.
     * @return The User matching the ID passed
     */
    public User getUser(String id){
        
        User foundUser = null;
        for(User u : this.userList){
            if(u.getUserID().equals(id)){
                foundUser = u;
                
            }
            
        } 
        return foundUser;
        
    }
    
    /**
     * Adds the User to the UserList. 
     * @param newUser User object to be added to the UserList
     */
    public void addToUserList(User newUser){
        this.userList.add(newUser);
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
