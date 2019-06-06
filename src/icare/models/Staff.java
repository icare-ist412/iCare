package icare.models;

/**
 *
 * @author David Ortiz
 */
public class Staff extends User {
    
    private String firstName;
    private String lastName;
    private String department;

    /**
     * Default constructor for this class. 
     * @param firstName Sets the User's first name
     * @param lastName Sets the User's last name
     * @param department Sets the User's department
     */
    public Staff(String firstName, String lastName, String department, String dobString) {
        super(firstName, lastName, dobString);
        this.department = department;
        
        System.out.println(this.department);
    }

    /**
     * Gets this Staff's department
     * @return A string representing this Staff's department
     */
    public String getDepartment() {
        return department;
    }

    
}
