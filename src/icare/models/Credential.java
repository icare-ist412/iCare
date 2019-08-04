package icare.models;

import java.io.Serializable;

/**
 *
 * @author David Ortiz
 */
public class Credential implements Serializable{
    
    private String userID;
    private String password;

    /**
     * Default constructor for class.
     */
    public Credential(String firstName, String lastName, String newPassword) 
    {
        userID = lastName +  firstName.substring(0, 2);
        password = newPassword;
    }

    /**
     * Verifies that User ID and password match for login.
     * @return A boolean determining if login is valid
     * @param _userID Used to compare with the Credential's ID
     * @param _password Used to compare with the Credential's password
     */
    public boolean verifyLogin(String _userID, String _password) 
    {
        return this.userID.equals(_userID) && this.password.equals(_password);      
    }
    
    /**
     * Verifies that User ID and password match for login.
     * @return A boolean determining if login is valid
     */
    public String getUserID(){
        return this.userID;
    }

    /**
     * Returns this Credential's password.
     * @return A String representing the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password for this Credential.
     * @param password Sets the Credential's password
     */
    public void setPassword(String password) {
        this.password = password;
    }
   
    @Override
    public String toString(){
        return this.userID + " - " + this.password;
    }
}
