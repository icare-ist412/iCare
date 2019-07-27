package icare.models;

import java.io.Serializable;

/**
 *
 * @author jakebenedick
 */
public class Hospital implements Serializable{
    private String name;
    private Address address;
    private String phone;
    
    /**
    * Hospital constructor with address parameter
     * @param address Stores the address of the hospital
    */
    public Hospital(String name, Address address, String phone){
        this.address = address;
        this.name = name;
        this.phone = phone;
    }
        
    /**
    * Returns this Appointment's address as an Address object
     * @return An Address object representing the location of this Appointment
    */
    public Address getAddress() {
        return address;
    }
    
    public String getPhone(){
        return this.phone;
    }
    
    public String getName(){
        return this.name;
    }
    
    
     
}
