package icare.models;

/**
 *
 * @author David Ortiz
 */
  
public interface UserInterface {
    /**
     * Method gets the User's first name.
     * @return Should be configured to return a String representing the first name.
     */
    String getFirstName();
    
    /**
     * Method should be configured to get the User's last name.
     * @return Should be configured to return a String representing the last name.
     */
    String getLastName();
    
    /**
     * Method should be configured to get the User's full name.
     * @return Should be configured to return a String representing the full name.
     */
    String getFullName();
    
    /**
     * Method should be configured to get the User's User ID.
     * @return Should be configured to return a String representing the user ID.
     */
    String getUserID();
    
    /**
     * Method should be configured to set the User's Credential.
     * @param password Will be used to set the Credential's password.
     */
    void setCredential(String password);
    
    /**
     * Method should be configured to authenticate the User's credentials. .
     * @param userID Used to compare to the User's password.
     * @param password Used to compare to the User's ID.
     * @return Should be configured to return a boolean representing success or failure.
     */
    boolean authenticate(String userID, String password);
    
    
}
 
    
    
   


