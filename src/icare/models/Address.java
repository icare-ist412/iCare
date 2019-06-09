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
     * @param String streetAddress
     * @param String city
     * @param String state
     * @param int zipCode
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
     *  @param String streetAddress;
    */
    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    /**
    * Returns a String for the city
     * @return String city
    */
    public String getCity() {
        return city;
    }

    /**
    * Sets the city for the address
     *  @param String city
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
     *  @param String state
    */
    public void setState(String state) {
        this.state = state;
    }

    /**
    * Returns an int for the zipCode
     * @return String zipCode
    */
    public int getZipCode() {
        return zipCode;
    }

    /**
    * Sets the zipCode for the address
     *  @param int zipCode
    */
    public void setZipCode(int zipCode) {
        this.zipCode = zipCode;
    }
    
    
}
