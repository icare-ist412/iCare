package icare.models;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author David Ortiz
 */
public class Appointment {
    
    private LocalDateTime date;
    private Address address;
    private Patient patient;
    private Staff staff;
    
    /**
    * Default constructor for this class
    */
    public Appointment(){
    }
    
    
    /**
    * Sets this Appointment's date and time
     * @param formattedDateString Date String formatted as yyyy-MM-dd
     * @param formattedTimeString Time String formatted as HH:mm
    */
    public void setDateFromString(String formattedDateString, String formattedTimeString){
        
        String stringToParse = formattedDateString + " " + formattedTimeString;
        
        LocalDateTime date1;  
        try {
            
            DateTimeFormatter formatter =  DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            date1 = LocalDateTime.parse(stringToParse, formatter);
            
            this.setDate(date1);
            
        } catch (Exception ex) {
            System.out.println("Invalid date format: " + ex);
        }
        
    }
    
    /**
    * Gets this Appointment's in a formatted string
     * @return A String object representing this Appointment's date
    */
    public String getDateAsString(){
        //"Tuesday December 21st, 2019"
        return this.getDate().getDayOfWeek() + " " + this.getDate().getMonth() + " "+this.getDate().getDayOfMonth() + ", "+ this.getDate().getYear();
    }
  
    /**
    * Gets this Appointment's day of the week
     * @return A String object representing this Appointment's day of the week
    */
    public String getDay(){
        return this.getDate().getDayOfWeek().toString();
    }
    
    /**
    * Gets this Appointment's day of the month
     * @return A String object representing this Appointment's day of the month
    */
    public String getDayOfMonth(){
        return Integer.toString(this.getDate().getDayOfMonth());
    }
    
    /**
    * Gets this Appointment's month
     * @return A String object representing this Appointment's month
    */
    public String getMonth(){
        return this.getDate().getMonth().toString();
    }
    
    /**
    * Gets this Appointment's year
     * @return A String object representing this Appointment's year
    */
    public String getYear(){
        return Integer.toString(this.getDate().getYear());
    }
    
    /**
    * Returns this Appointment's address
     * @return An Address object representing the location of this Appointment
    */
    public Address getAddress() {
        return address;
    }

    /**
    * Sets this Appointment's address using Strings for street, city, state, and an int for zip.
<<<<<<< HEAD
     *  @param streetAddress sets the city
     *  @param city sets the city
     *  @param state sets the state
     *  @param zipCode sets the zip code
=======
     *  @param String streetAddress
     *  @param String city
     *  @param String state
     *  @param int zipCode
>>>>>>> master
    */
    public void setAddress(String streetAddress, String city, String state, int zipCode) {
        setAddress(new Address(streetAddress, city, state, zipCode));
    }

    /**
     * @return the patient
     */
    public Patient getPatient() {
        return patient;
    }

    /**
     * @param patient the patient to set
     */
    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    /**
     * @return the staff
     */
    public Staff getStaff() {
        return staff;
    }

    /**
     * @param staff the staff to set
     */
    public void setStaff(Staff staff) {
        this.staff = staff;
    }

    /**
     * @return the date
     */
    public LocalDateTime getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(Address address) {
        this.address = address;
    }
}
