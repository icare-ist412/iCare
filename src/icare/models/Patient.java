package icare.models;

import java.util.ArrayList;

/**
 *
 * @author David Ortiz
 */
public class Patient extends User {
    
    private String firstName;
    private String lastName;
    private ArrayList<Appointment> appointments;

    /**
     * Default constructor for this class. 
     * @param firstName Sets the User's first name
     * @param lastName Sets the User's last name
     */
    public Patient(String firstName, String lastName) {
        super(firstName, lastName);
        appointments = new ArrayList<Appointment>();
    }

    /**
    * Returns this Patients's list of appointments as an ArrayList
     * @return ArrayList of Appointment objects
    */
    public ArrayList<Appointment> getAppointments() {
        return appointments;
    }

    /**
    * Sets this Patient's appointments with an ArrayList of Appointment objects
     *  @param ArrayList of Appointments
    */
    public void setAppointments(ArrayList<Appointment> appointments) {
        this.appointments = appointments;
    }
    
    /**
    * Adds an appointment to this Patient's appointment list
     *  @param Appointment ap
    */
    public void addAppointment(Appointment ap){
        appointments.add(ap);
    }

}
