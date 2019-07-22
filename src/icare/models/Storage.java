package icare.models;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author David Ortiz
 */
public class Storage implements Serializable {
    
    private ArrayList<User> userList = new ArrayList<>();
    private String userFile = "Users.ser";
    
    /**
     * Default constructor for this class. 
     * @throws java.io.FileNotFoundException Thrown when the user's text file is not found
     */
    public Storage() throws FileNotFoundException{
        
        this.readUserListFile();
        if(userList.isEmpty() || userList == null){
            this.userList = fetchUsersFromFile();
            
            this.writeUserListFile();
            this.readUserListFile();
        }
        
        displayLoginsForTesting();
        
    }
    
    public void readUserListFile(){
        FileInputStream fis = null;
        ObjectInputStream in = null;
        try {
            fis = new FileInputStream(userFile);
            in = new ObjectInputStream(fis);
            userList = (ArrayList)in.readObject();
            in.close();
        }
        catch(IOException ex){
            System.out.println(userFile + " not found, creating.");
        }
        catch(ClassNotFoundException ex){
            ex.printStackTrace();
        }
        
    }
    
    public void writeUserListFile(){
        FileOutputStream fos = null;
        ObjectOutputStream out = null;
        try {
            fos = new FileOutputStream(userFile);
            out = new ObjectOutputStream(fos);
            out.writeObject(userList);
            out.close();
        }
        catch(IOException ex){
            //System.out.println("Error serializing");
            ex.printStackTrace();
        }
    }
    
    /**
     * Returns the User List 
     * @return An ArrayList of User types
     */
    public ArrayList<User> getUserList() {
        return userList;
    }
    
    public ArrayList<User> getPatients(){
        ArrayList<User> tempList = new ArrayList();
        
        for(User u : this.userList){
            if(u.getRoleType().equals("Patient")){
                tempList.add(u);
            }
        }
        
        return tempList;
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
        this.writeUserListFile(); //update serialized file
    }
    
    private void displayLoginsForTesting(){
        System.out.println("---------- Logins for testing ----------");
        for(User u : this.userList){
            System.out.println("Role: "+ u.getRoleType());
            System.out.println("User ID: "+ u.getUserID());
            System.out.println("Password: "+ u.getCredential().getPassword());
            System.out.println("Birthdate: "+ u.getBirthdate());
            System.out.println();
        }
        System.out.println("----------------------------------------");
    }
    
    private ArrayList<User> fetchUsersFromFile() throws FileNotFoundException{
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
                    
                    switch (type[0]) {
                        case "patient":
                            {
                                String[] words = type[1].split(";");
                                String fname = words[0];
                                String lname = words[1];
                                String password = words[2];
                                long insuranceID = Long.parseLong(words[3]);
                                String dob = words[4];
                                String gender = words[5];
                                Patient tempPatient = new Patient(fname, lname, insuranceID, dob, gender);
                                tempPatient.updateCredential(password);
                                users.add(tempPatient);
                                break;
                            }
                        case "staff":
                            {
                                String[] words = type[1].split(";");
                                String fname = words[0];
                                String lname = words[1];
                                String password = words[2];
                                String dept = words[3];
                                String dob = words[4];
                                String gender = words[5];
                                Staff tempStaff = new Staff(fname, lname, dept, dob, gender);
                                tempStaff.updateCredential(password);
                                users.add(tempStaff);
                                break;
                            }
                        default:
                            System.out.println("Invalid user");
                            break;
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
