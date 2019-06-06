package icare.models;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author David Ortiz
 */
public class User implements UserInterface {
    
    private String firstName;
    private String lastName;
    private Credential credential;
    private LocalDate birthdate;

    /**
     * Default constructor for this class
     */
    public User(String firstName, String lastName, String dobString) {
        this.firstName = firstName;
        this.lastName = lastName;
        
        DateTimeFormatter formatter =  DateTimeFormatter.ofPattern("yyyy-MM-dd");
        birthdate = LocalDate.parse(dobString, formatter);
        
    }

    /**
     * Gets the role of the current User
     * @return A String representing the User's role
     */
    public String getRoleType(){
        String className = this.getClass().getSimpleName();
        return className;
    }
    
    /**
     * Gets the Credential of the current User
     * @return This User's Credential object
     */
    public Credential getCredential(){
        return this.credential;
    }

    /**
     * Gets the User's birthdate
     * @return A LocalDate representing this User's birthdate
     */
    public LocalDate getBirthdate() {
        return birthdate;
    }
    
    /**
     * Sets the Credential for the current User
     * @param credential Credential to be set to this User's Credential object.
     */
    public void setCredential(Credential credential) {
        this.credential = credential;
    }

    @Override
    public String getFirstName() {
        return this.firstName;
    }

    @Override
    public String getLastName() {
        return this.lastName;   
    }

    @Override
    public String getFullName() {
        return this.firstName +" "+ this.lastName;
    }

    @Override
    public void setCredential(String password) {
        credential = new Credential(this.firstName, this.lastName, password); 
    }

    @Override
    public boolean authenticate(String userID, String password) {
       return getCredential().verifyLogin(userID, password);  
    }

    @Override
    public String getUserID() {
        return this.getCredential().getUserID();
    }
    
}
