package icare.models;

/**
 *
 * @author jakebenedick
 */
public class Treatment {
    String instructions;
    String medication;
    String prescribingDoctor;
    int numberOfWeeks;

    /**
    * Treatment constructor with instructions, medication, and numberOfWeeks parameters
     * @param String Treatment Instructions
     * @param String Prescribing Doctor
     * @param String Medication Name
     * @param int Treatment duration (in weeks)
    */
    public Treatment(String instructions, String medication, int numberOfWeeks, String prescribingDoctor) {
        this.instructions = instructions;
        this.medication = medication;
        this.numberOfWeeks = numberOfWeeks;
        this.prescribingDoctor = prescribingDoctor;
    }

    /**
    * Returns this Treatment's instructions as a string
     * @return String of treatment instructions
    */
    public String getInstructions() {
        return instructions;
    }

    /**
    * Sets this treatment's instructions as a String
     *  @param String instructions
    */
    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    /**
    * Returns this treatment's medication name as a string
     * @return String storing Medication Name
    */
    public String getMedication() {
        return medication;
    }
    
    /**
    * Sets this treatment's medication name as a string.
     *  @param String medication
    */
    public void setMedication(String medication) {
        this.medication = medication;
    }
    
    /**
    * Returns this treatment's prescribing doctor as a string
     * @return String storing prescribing doctor's name
    */
    public String getPrescribingDoctor() {
        return prescribingDoctor;
    }
    
    /**
    * Sets this treatment's medication name as a string.
     *  @param String medication
    */
    public void setPrescribingDoctor(String prescribingDoctor) {
        this.prescribingDoctor = prescribingDoctor;
    }

    /**
    * Returns this treatment's duration in weeks as an int
     * @return int duration(weeks)
    */
    public int getNumberOfWeeks() {
        return numberOfWeeks;
    }

    /**
    * Sets this treatment's duration in weeks as an int.
     *  @param int numberOfWeeks
    */
    public void setNumberOfWeeks(int numberOfWeeks) {
        this.numberOfWeeks = numberOfWeeks;
    }
    
    
}
