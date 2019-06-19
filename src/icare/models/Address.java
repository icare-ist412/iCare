package icare.models;

/**
 *
 * @author jakebenedick
 */
public class Address {
    String streetAddress;
    String city;
    String state;
    int zipCode;

    /**
    * Address constructor with streetAddress, city, state, and zipCode parameters
<<<<<<< HEAD
     * @param streetAddress sets the street
     * @param city sets the city
     * @param state sets the state
     * @param zipCode sets the Zip code
=======
     * @param String streetAddress
     * @param String city
     * @param String state
     * @param int zipCode
>>>>>>> master
    */
    public Address(String streetAddress, String city, String state, int zipCode) {
        this.streetAddress = streetAddress;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
    }
    
    /**
    * Returns a String for the streetAddress
     * @return String streetAddress
    */
    public String getStreetAddress() {
        return streetAddress;
    }

    /**
    * Sets the streetAddress for the address
<<<<<<< HEAD
     *  @param streetAddress sets the street;
=======
     *  @param String streetAddress;
>>>>>>> master
    */
    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    /**
    * Returns a String for the city
<<<<<<< HEAD
     * @return A String representing the city
=======
     * @return String city
>>>>>>> master
    */
    public String getCity() {
        return city;
    }

    /**
    * Sets the city for the address
<<<<<<< HEAD
     *  @param city sets the city
=======
     *  @param String city
>>>>>>> master
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
<<<<<<< HEAD
     *  @param state sets the state
=======
     *  @param String state
>>>>>>> master
    */
    public void setState(String state) {
        this.state = state;
    }

    /**
    * Returns an int for the zipCode
<<<<<<< HEAD
     * @return An int representing the zipCode 
=======
     * @return String zipCode
>>>>>>> master
    */
    public int getZipCode() {
        return zipCode;
    }

    /**
    * Sets the zipCode for the address
<<<<<<< HEAD
     *  @param zipCode sets the zip code
=======
     *  @param int zipCode
>>>>>>> master
    */
    public void setZipCode(int zipCode) {
        this.zipCode = zipCode;
    }
    
    @Override
    public String toString(){
        return getStreetAddress() + " " + getCity() + " "+ getState() + " " + getZipCode();
    }
    
    
}
