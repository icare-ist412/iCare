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
class Credential {
    
    private String userID;
    private String password;

    public Credential(String firstName, String lastName, String newPassword) {

        userID = lastName +  firstName.substring(0, 2);
        password = newPassword;
    }

    
    public boolean verifyLogin(String _userID, String _password) {
        if(this.userID.toLowerCase().equals(_userID) && this.password.equals(_password)){
            return true;
        }
        
        return false;      
    }
    
    public String getUserID(){
        return this.userID;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
}
