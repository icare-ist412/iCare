/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package icare.models.persistence;

import icare.models.Patient;
import icare.models.RoleEnum;
import icare.models.Staff;
import icare.models.User;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 *
 * @author Dmitry Mikhailov
 */
public class UserDao implements CrudRepository<User, String> {

    private String sourceFile;
    private String serFile; //location of the file with serialized object
    private ArrayList<User> userList = new ArrayList<>();

    public UserDao(String sourceFile, String serFile) {

        this.sourceFile = sourceFile;
        this.serFile = serFile;
       
        readUserListFile();
        if(userList.isEmpty() || userList == null)
        {
            this.userList = fetchUsersFromFile();
            this.writeUserListFile();
            this.readUserListFile();
        }
        
        
        
        displayLoginsForTesting();

    }

    public void readUserListFile() {
        FileInputStream fis = null;
        ObjectInputStream in = null;
        try {
            fis = new FileInputStream(serFile);
            in = new ObjectInputStream(fis);
            userList = (ArrayList) in.readObject();
            in.close();
        } catch (IOException ex) {
            System.out.println(serFile + " not found, creating.");
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }

    }

    public void writeUserListFile() {
        FileOutputStream fos = null;
        ObjectOutputStream out = null;
        try {
            fos = new FileOutputStream(serFile);
            out = new ObjectOutputStream(fos);
            out.writeObject(userList);
            out.close();
        } catch (IOException ex) {
            //System.out.println("Error serializing");
            ex.printStackTrace();
        }
    }

    /**
     * Returns the User List
     *
     * @return An ArrayList of User types
     */
    public ArrayList<User> getAll() {
        return userList;
    }

    public ArrayList<Patient> getAllPatients() {
        ArrayList<Patient> patientList = new ArrayList();

        this.userList.stream().filter((u) -> (u.getRoleType()==RoleEnum.Patient)).forEachOrdered((u) -> {
            patientList.add((Patient) u);
        });

        return patientList;
    }

    /**
     * Determines if a User exists using the ID parameter.
     *
     * @param id Used to compare to User's ID to determine if User exists.
     * @return A boolean determining if User exists in UserList
     */
    public boolean doesUserExist(String id) {
        return this.userList.stream().anyMatch((u) -> (u.getUserID().equals(id)));
    }

    /**
     * Gets the User whose ID matches the ID passed in.
     *
     * @param id Used to identify User to fetch.
     * @return The User matching the ID passed
     */
    public User findById(String id) {

        User foundUser = null;
        for (User u : this.userList) {
            if (u.getUserID().equals(id)) {
                foundUser = u;

            }

        }
        return foundUser;

    }

    /**
     * Adds the User to the UserList.
     *
     * @param newUser User object to be added to the UserList
     */
    public void save(User newUser) {
        this.userList.add(newUser);
        this.writeUserListFile(); //update serialized file
    }

    public void updateVisits() {

        this.userList.stream().filter((u) -> (u.getRoleType()== RoleEnum.Patient)).forEachOrdered((u) -> {
            ((Patient) u).updateVisits();
        });

    }

    private ArrayList<User> fetchUsersFromFile() {

        ArrayList<User> users = new ArrayList<>();

        String line = null;

        int index = 0;

        try {
            System.out.println(sourceFile);
            FileReader fileReader = new FileReader(sourceFile);

            BufferedReader bufferedReader = new BufferedReader(fileReader);

            while ((line = bufferedReader.readLine()) != null) {

                if (line != "") {
                    String[] type = line.split("~");

                    if (type[0].equalsIgnoreCase(RoleEnum.Patient.toString())){
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
                    }
                    else if (type[0].equalsIgnoreCase(RoleEnum.Staff.toString())) {
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
                    }
                    else {
                        System.out.println("Invalid user");
                    }
                    

                    index++;
                }

            }

            bufferedReader.close();

        } catch (FileNotFoundException ex) {
            System.out.println("Unable to open file '" + sourceFile + "'");
        } catch (IOException ex) {
            System.out.println("Error reading file '" + sourceFile + "'");
        }
        return users;

    } // end fetchUsersFromFile()
    
        private void displayLoginsForTesting() {
        System.out.println("---------- Logins for testing ----------");
        for (User u : this.userList) {
            System.out.println("Role: " + u.getRoleType().toString());
            System.out.println("User ID: " + u.getUserID());
            System.out.println("Password: " + u.getCredential().getPassword());
            System.out.println("Birthdate: " + u.getBirthdate());
            System.out.println();
        }
        System.out.println("----------------------------------------");
    }


}
