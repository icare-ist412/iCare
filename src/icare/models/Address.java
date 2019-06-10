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
     * @param streetAddress sets the street
     * @param city sets the city
     * @param state sets the state
     * @param zipCode sets the Zip code
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
     *  @param streetAddress sets the street;
    */
    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
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
    public int getZipCode() {
        return zipCode;
    }

    /**
    * Sets the zipCode for the address
     *  @param zipCode sets the zip code
    */
    public void setZipCode(int zipCode) {
        this.zipCode = zipCode;
    }
    
    
}
