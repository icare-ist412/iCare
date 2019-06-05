package icare.models;

/**
 *
 * @author David Ortiz
 */
  
public interface UserInterface {
    String getFirstName();
    String getLastName();
    String getFullName();
    String getUserID();
    void setCredential(String password);
    boolean authenticate(String userID, String password);
    
    
}
 
    
    
   


