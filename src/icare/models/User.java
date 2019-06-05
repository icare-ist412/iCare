/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
     *
     * @param firstName
     * @param lastName
     * @param credential
     */
    public User(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getRoleType(){
        String className = this.getClass().getSimpleName();
        return className;
    }
    
    public Credential getCredential(){
        return this.credential;
    }
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
