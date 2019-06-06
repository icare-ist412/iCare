package icare.models;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author David Ortiz
 */
public class Appointment {
    
    LocalDateTime date;
    
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
    public void setDate(String formattedDateString, String formattedTimeString){
        
        String stringToParse = formattedDateString + " " + formattedTimeString;
        
        LocalDateTime date1;  
        try {
            
            DateTimeFormatter formatter =  DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            date1 = LocalDateTime.parse(stringToParse, formatter);
            
            this.date = date1;
            
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
        return this.date.getDayOfWeek() + " " + this.date.getMonth() + " "+this.date.getDayOfMonth() + ", "+ this.date.getYear();
    }
  
    /**
    * Gets this Appointment's day of the week
     * @return A String object representing this Appointment's day of the week
    */
    public String getDay(){
        return this.date.getDayOfWeek().toString();
    }
    
    /**
    * Gets this Appointment's day of the month
     * @return A String object representing this Appointment's day of the month
    */
    public String getDayOfMonth(){
        return Integer.toString(this.date.getDayOfMonth());
    }
    
    /**
    * Gets this Appointment's month
     * @return A String object representing this Appointment's month
    */
    public String getMonth(){
        return this.date.getMonth().toString();
    }
    
    /**
    * Gets this Appointment's year
     * @return A String object representing this Appointment's year
    */
    public String getYear(){
        return Integer.toString(this.date.getYear());
    }
    
    
}
