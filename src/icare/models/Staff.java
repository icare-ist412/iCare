package icare.models;

/**
 *
 * @author David Ortiz
 */
public class Staff extends User {
    

    private String firstName;
    private String lastName;
    private Credential credential;

    /**
     *
     * @param firstName
     * @param lastName
     * @param credential
     */
    public Staff(String firstName, String lastName) {
        super(firstName, lastName);
    }

}
