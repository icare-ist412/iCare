package icare.models;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 *
 * @author David Ortiz
 */
public class Patient extends User {
    
    private long insuranceID;
    private ArrayList<Treatment> treatments;
    private ArrayList<String> diseases;
    private ArrayList<Immunization> immunizations;
    private ArrayList<Appointment> appointments;
    
    private LocalDateTime now;
    private ArrayList<Appointment> upcomingAppointments;
    private ArrayList<Appointment> pastAppointments;
    private ArrayList<Appointment> requestedAppointments;
    
    private String lastVisit;
    private String nextVisit;

    /**
     * Default constructor for this class. 
     * @param firstName Sets the User's first name
     * @param lastName Sets the User's last name
     * @param insuranceID Sets the User's insurance ID
     * @param dobString Sets the User's birthdate; formatted yyyy-MM-dd
     */
    public Patient(String firstName, String lastName, long insuranceID, String dobString, String gender) {
        super(firstName, lastName, dobString, gender);
        role = RoleEnum.Patient;
        diseases = new ArrayList();
        treatments = new ArrayList();
        immunizations = new ArrayList();
        appointments = new ArrayList();
        requestedAppointments = new ArrayList();
        
        upcomingAppointments = new ArrayList();
        pastAppointments = new ArrayList();
        
        this.insuranceID = insuranceID;
        
        this.now = LocalDateTime.now();
        
        this.updateVisits();
        
    }
    
    public ArrayList<String> getImmunizationNames(){
        ArrayList<String> list = new ArrayList<>();
        
        this.immunizations.stream().forEach((i) -> {
            list.add(i.getImmunization().toLowerCase());
        });
        
        return list;
    }
    
    public void updateVisits(){
        
        if(this.pastAppointments.isEmpty()){
            this.lastVisit = "None";
        } else {
            pastAppointments.sort(Comparator.comparing(Appointment::getDate));
            this.lastVisit = this.pastAppointments.get(0).getDay();
        }
        
        if(this.upcomingAppointments.isEmpty()){
            this.nextVisit = "None";
        } else {
            upcomingAppointments.sort(Comparator.comparing(Appointment::getDate));
            this.nextVisit = this.upcomingAppointments.get(0).getDay();
        }
    }
    
    private List<String> toSortedList(Stream<String> input){ 
        return input.collect(Collectors.toSet())
                .stream()
                .sorted()
                .collect(Collectors.toList());
    }
    
    public void setLastVisit(LocalDate date) { 
        this.lastVisit = date.toString();
    }
    
    public void setNextVisit(LocalDate date) { 
        this.nextVisit = date.toString();
    }
    
    public String getLastVisit() { 
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return lastVisit;
    }
    
    public String getNextVisit() { 
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return nextVisit;
    }
    
    public ArrayList<Appointment> getPastAppointments(){
        
        return pastAppointments;
    }
    
    public ArrayList<Appointment> getUpcomingAppointments(){
        
        return upcomingAppointments;
    }
    
    public void addAppointment(Appointment appointment){
        if(this.appointments == null){
            appointments = new ArrayList();
        }
        
        if(this.upcomingAppointments == null){
            upcomingAppointments = new ArrayList();
        }
        if(this.pastAppointments == null){
            pastAppointments = new ArrayList();
        }
        
        if(appointment.getDate().isAfter(this.now)){
            upcomingAppointments.add(appointment);
        }
        if(appointment.getDate().isBefore(this.now)){
            pastAppointments.add(appointment);
        }
        
        this.appointments.add(appointment);
    }
    public ArrayList<Appointment> getAppointments(){
        return this.appointments;
    }
    public void removeAppointment(Appointment appointment){
        this.appointments.remove(appointment);
        refreshAppointments();
    }
    public void refreshAppointments(){
        this.now = LocalDateTime.now();
        this.upcomingAppointments.clear();
        this.pastAppointments.clear();
        
        for(Appointment a : this.appointments){
            if(a.getDate().isAfter(now) || a.getDate().isEqual(now)){
                this.upcomingAppointments.add(a);
            }
            if(a.getDate().isBefore(now)){
                this.pastAppointments.add(a);
            }
        }
    }
    
    public void addRequestedAppointment(Appointment a){
        if(this.requestedAppointments == null){
            requestedAppointments = new ArrayList();
        }
        this.requestedAppointments.add(a);
    }
    public void removeRequestedAppointment(Appointment a){
        this.requestedAppointments.remove(a);
    }
    public ArrayList<Appointment> getRequestedAppointments(){
        return this.requestedAppointments;
    }
    public void approveRequestedAppointment(Appointment a){
        this.addAppointment(a);
        this.removeRequestedAppointment(a);
        this.refreshAppointments();
    }
    public void denyRequestedAppointment(Appointment a){
        this.removeRequestedAppointment(a);
    }
    

    public void addTreatment(Treatment treatment){
        this.treatments.add(treatment);
    }
    public void addTreatment(String instructions, String medication, int numOfWeeks){
        this.treatments.add(new Treatment(instructions, medication, numOfWeeks));
    }
    public ArrayList<Treatment> getTreatments(){
        return this.treatments;
    }
    public void removeTreatment(Treatment treatment){
        this.treatments.remove(treatment);
    }
    
    
    public void addImmunization(Immunization immunization){
        if(this.immunizations == null){
            immunizations = new ArrayList();
        }
        this.immunizations.add(immunization);
    }
    public ArrayList<Immunization> getImmunizations(){
        return this.immunizations;
    }
    public void removeImmunization(Immunization immunization){
        this.immunizations.remove(immunization);
    }
    
    
    
    public void addDisease(String disease){
        if(this.diseases == null){
            diseases = new ArrayList();
        }
        this.diseases.add(disease);
    }
    public ArrayList<String> getDiseases(){
        return this.diseases;
    }
    public void removeDisease(String disease){
        this.diseases.remove(disease);
    }
    
    /**
     * Sets the User's insurance ID 
     * @return A long representing the User's insurance ID
     */
    public long getInsuranceID() {
        return insuranceID;
    }

    /**
     * Sets the User's insurance ID 
     * @param insuranceID Sets the User's insurance ID
     */
    public void setInsuranceID(long insuranceID) {
        this.insuranceID = insuranceID;
    }
    
    @Override
    public String toString(){
        return "Name: " + getFullName() + ", dob: " + getBirthdate() + ",role: " + getRoleType() + ", InsuranceID: " + getInsuranceID();
    }
    

}
