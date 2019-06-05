package icare.models;

/**
 *
 * @author David Ortiz
 */
public class User implements UserInterface {
    
    private String firstName;
    private String lastName;
    private Credential credential;

    /**
     * Default constructor for this class
     * @param firstName
     * @param lastName
     */
    public User(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
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
