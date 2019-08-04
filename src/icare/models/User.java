package icare.models;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author David Ortiz
 */
public class User implements Serializable {

    private String firstName;
    private String lastName;
    private Credential credential;
    private LocalDate birthdate;
    private String gender;
    protected RoleEnum role;
    
    /**
     * Default constructor for this class
     *
     * @param firstName Sets the User's first name
     * @param lastName Sets the User's last name
     * @param dobString Sets the User's date of birth
     * @param gender Sets the User's gender
     */
    public User(String firstName, String lastName, String dobString, String gender) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.role = RoleEnum.Unknown;

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        birthdate = LocalDate.parse(dobString, dateTimeFormatter);
        

    }

    public void setFirstName(String firstName) {
        this.firstName = firstName.toLowerCase();
    }

    public void setLastName(String lastName) {
        this.lastName = lastName.toLowerCase();
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }
    
    

    /**
     * Gets the role of the current User
     *
     * @return A String representing the User's role
     */
    public RoleEnum getRoleType() {
        //String className = this.getClass().getSimpleName();
        return role;
    }

    /**
     * Gets the Credential of the current User
     *
     * @return This User's Credential object
     */
    public Credential getCredential() {
        return this.credential;
    }

    /**
     * Gets the User's birthdate
     *
     * @return A LocalDate representing this User's birthdate
     */
    public LocalDate getBirthdate() {
        return birthdate;
    }

    public String getDob() { 
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return dateTimeFormatter.format(birthdate);
    }
    
    public String getGender() { 
        return gender;
    }
    

    /**
     * Method gets the User's first name.
     *
     * @return Should be configured to return a String representing the first
     * name.
     */
    public String getFirstName() {
        return this.firstName;
    }

    /**
     * Method gets the User's last name.
     *
     * @return A String representing the last name.
     */
    public String getLastName() {
        return this.lastName;
    }

    /**
     * Method gets the User's full name.
     *
     * @return A String representing the full name.
     */
    public String getFullName() {
        return this.firstName + " " + this.lastName;
    }

    /**
     * Method updates the User's Credential with a new password
     *
     * @param password Will be used to set the Credential's password.
     */
    public void updateCredential(String password) {
        credential = new Credential(this.firstName, this.lastName, password);
    }

    /**
     * Method authenticates the User's credentials. .
     *
     * @param userID Used to compare to the User's password.
     * @param password Used to compare to the User's ID.
     * @return A boolean representing success or failure.
     */
    public boolean authenticate(String userID, String password) {
        return getCredential().verifyLogin(userID, password);
    }

    /**
     * Method gets the User's User ID.
     *
     * @return A String representing the user ID.
     */
    public String getUserID() {
        return this.getCredential().getUserID();
    }

    @Override
    public String toString() {
        return "Name: " + getFullName() + ", dob: " + getBirthdate() + ", role: " + getRoleType();
    }

}
