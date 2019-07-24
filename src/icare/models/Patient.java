package icare.models;

import java.util.ArrayList;

/**
 *
 * @author David Ortiz
 */
public class Patient extends User {
    
    private long insuranceID;
    private ArrayList<Treatment> treatments;
    private ArrayList<String> diseases;
    private ArrayList<Immunization> immunizations;

    /**
     * Default constructor for this class. 
     * @param firstName Sets the User's first name
     * @param lastName Sets the User's last name
     * @param insuranceID Sets the User's insurance ID
     * @param dobString Sets the User's birthdate; formatted yyyy-MM-dd
     */
    public Patient(String firstName, String lastName, long insuranceID, String dobString, String gender) {
        super(firstName, lastName, dobString, gender);
        diseases = new ArrayList();
        treatments = new ArrayList();
        immunizations = new ArrayList();
        this.insuranceID = insuranceID;
        
       
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
