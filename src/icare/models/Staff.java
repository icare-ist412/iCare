package icare.models;

/**
 *
 * @author David Ortiz
 */
public class Staff extends User {
    
    private String firstName;
    private String lastName;

    /**
     * Default constructor for this class. 
     * @param firstName Sets the User's first name
     * @param lastName Sets the User's last name
     */
    public Staff(String firstName, String lastName) {
        super(firstName, lastName);
    }

}
