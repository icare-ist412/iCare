package icare.models;

import java.io.Serializable;

/**
 *
 * @author jakebenedick
 */
public class Address implements Serializable{
    private String number;
    private String street;
    private String city;
    private String state;
    private String zipCode;

    /**
    * Address constructor with street, city, state, and zipCode parameters
     * @param streetAddress sets the street
     * @param city sets the city
     * @param state sets the state
     * @param zipCode sets the Zip code
    */
    public Address(String number, String streetAddress, String city, String state, String zipCode) {
        this.number = number;
        this.street = streetAddress;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
    }
    
    /**
    * Returns a String for the street
     * @return String street
    */
    public String getStreet() {
        return street;
    }

    /**
    * Sets the street for the address
     *  @param street sets the street;
    */
    public void setStreet(String street) {
        this.street = street;
    }

    /**
    * Returns a String for the city
     * @return A String representing the city
    */
    public String getCity() {
        return city;
    }

    /**
    * Sets the city for the address
     *  @param city sets the city
    */
    public void setCity(String city) {
        this.city = city;
    }

    /**
    * Returns a String for the state
     * @return String state
    */
    public String getState() {
        return state;
    }

    /**
    * Sets the state for the address
     *  @param state sets the state
    */
    public void setState(String state) {
        this.state = state;
    }

    /**
    * Returns an int for the zipCode
     * @return An int representing the zipCode 
    */
    public String getZipCode() {
        return zipCode;
    }

    /**
    * Sets the zipCode for the address
     *  @param zipCode sets the zip code
    */
    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }
    
    @Override
    public String toString(){
        return this.number + " "+ this.street + ", " + this.city + ", "+ this.state + " " + this.zipCode+". ";
    }
    
    
}
