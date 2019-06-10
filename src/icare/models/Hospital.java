package icare.models;

import java.util.ArrayList;

/**
 *
 * @author jakebenedick
 */
public class Hospital {
    Address address;
    ArrayList<Staff> staffList;
    
    /**
    * Default constructor for this class
    */
    public Hospital(){
        
    }
    
    /**
    * Hospital constructor with address and staff list parameters
     * @param Address Stores the address of the hospital
     * @param ArrayList Stores an arraylist of hospital staff members
    */
    public Hospital(Address address, ArrayList<Staff> staffList) {
        this.address = address;
        this.staffList = staffList;
    }
    
    /**
    * Hospital constructor with address parameter
     * @param Address Stores the address of the hospital
     * Creates an empty arrayList to store staff members;
    */
    public Hospital(Address address){
        this.address = address;
        staffList = new ArrayList<>();
    }

    /**
    * Returns this Appointment's address as an Address object
     * @return An Address object representing the location of this Appointment
    */
    public Address getAddress() {
        return address;
    }

    /**
    * Sets this Appointment's address using Strings for street, city, state, and an int for zip.
     *  @param String streetAddress
     *  @param String city
     *  @param String state
     *  @param int zipCode
    */
    public void setAddress(String streetAddress, String city, String state, int zipCode) {
        this.address = new Address(streetAddress, city, state, zipCode);
    }

    /**
    * Returns an arrayList of staff members at the hospital 
     * @return ArrayList of staff members
    */
    public ArrayList<Staff> getStaffList() {
        return staffList;
    }

    /**
    * Sets the staff list for the hospital
     *  @param ArrayList streetAddress
    */
    public void setStaffList(ArrayList<Staff> staffList) {
        this.staffList = staffList;
    }
    
    /**
    * Adds a staff member to the staff list for the hospital
     *  @param Staff staffMember
    */
    public void addStaff(Staff staffMember) {
        staffList.add(staffMember);
    }
    
    /**
    * Removes a staff member to the staff list for the hospital
     *  @param Staff staffMember
    */
    public void removeStaff(Staff staffMember) {
        staffList.remove(staffMember);
    }
}
