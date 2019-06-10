package icare.models;

/**
 *
 * @author David Ortiz
 */
class Credential {
    
    private String userID;
    private String password;

    /**
     * Default constructor for class.
     */
    public Credential(String firstName, String lastName, String newPassword) {
        String temp = lastName +  firstName.substring(0, 2);
        
                
                
        userID = lastName +  firstName.substring(0, 2);
        password = newPassword;
    }

    /**
     * Verifies that User ID and password match for login.
     * @return A boolean determining if login is valid
     * @param _userID Used to compare with the Credential's ID
     * @param _password Used to compare with the Credential's password
     */
    public boolean verifyLogin(String _userID, String _password) {
        if(this.userID.toLowerCase().equals(_userID) && this.password.equals(_password)){
            return true;
        }
        
        return false;      
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
    
}
