package icare.models;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author David Ortiz
 */
public class Appointment implements Serializable {
    
    private LocalDateTime date;
    private Hospital hospital;
    private Patient patient;
    private Staff staff;
    private String reason;
    
    private String day;
    private String time;
    private String location;
    
    /**
    * Default constructor for this class
    */
    public Appointment(){
    }

    public Appointment(LocalDateTime date, Hospital hospital, Patient patient, String reason) {
        this.date = date;
        this.hospital = hospital;
        this.location = this.hospital.getName();
        this.day = this.date.getMonthValue() + "/" + this.date.getDayOfMonth() + "/"+ this.date.getYear();
        
        String min = String.valueOf(this.date.getMinute());
        if(this.date.getMinute() < 10){
            min = 0+""+this.date.getMinute();
        }
        
        this.time = this.date.getHour() + ":" + min;
        
        this.patient = patient;
        this.reason = reason;
    }

    public String getTime() {
        return this.time;
    }
    public String getDay() {
        return this.day;
    }
    
    public String getLocation(){
        return this.location;
    }
    
    public String getReason(){
        return this.reason;
    }
    public void setReason(String reason){
        this.reason = reason;
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
    
    public String getDayTime(){
        return this.day +", " + this.time;
    }
  
    /**
    * Gets this Appointment's day of the week
     * @return A String object representing this Appointment's day of the week
    */
    
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
    * Returns this Appointment's hospital
     * @return An Hospital object representing the location of this Appointment
    */
    public Hospital getHospital() {
        return hospital;
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
     * @param hospital the hospital to set
     */
    public void setHospital(Hospital hospital) {
        this.hospital = hospital;
    }
}
