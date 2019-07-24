package icare.models;

import java.io.Serializable;
import java.time.LocalDate;

/**
 *
 * @author David Ortiz
 */
public class Immunization implements Serializable {
    
    private String immunization;
    private LocalDate dateAdministered;
    private boolean isFollowUpRequired;
    private String immunizationAbbreviation;

    /**
    * Overloaded constructor for this class
     * @param immunization Sets this Immunization's name
     * @param immunizationAbbreviation Sets this Immunization's abbreviation
     * @param dateAdministered Sets this Immunization's date
    */
    public Immunization(String immunization, String immunizationAbbreviation, LocalDate dateAdministered) {
        this.immunization = immunization;
        this.immunizationAbbreviation = immunizationAbbreviation;
        this.dateAdministered = dateAdministered;
    }
    
    public Immunization(String immunization, LocalDate dateAdministered, boolean isFollowUpRequired) {
        this.immunization = immunization;
        this.dateAdministered = dateAdministered;
        this.isFollowUpRequired = isFollowUpRequired;
        
        //immunizationAbbreviation = createAbbreviation();
    }

    /**
    * Gets this Immunization's name
     * @return A String representing the name of this Immunization
    */
    public String getImmunization() {
        return immunization;
    }

    /**
    * Gets this Immunization's administered date
     * @return A LocalDate representing the date of this Immunization
    */
    public LocalDate getDateAdministered() {
        return dateAdministered;
    }

    /**
    * Sets this Immunization's administered date
     * @param dateAdministered Sets this Immunization's administered date
    */
    public void setDateAdministered(LocalDate dateAdministered) {
        this.dateAdministered = dateAdministered;
    }

    /**
    * Gets a boolean determining if a follow up is required for this Immunization
     * @return A boolean determining if a follow up is required
    */
    public boolean isIsFollowUpRequired() {
        return isFollowUpRequired;
    }

    /**
    * Sets boolean determining if a follow up is required for this Immunization
     * @param isFollowUpRequired Sets the follow up requirement
    */
    public void setIsFollowUpRequired(boolean isFollowUpRequired) {
        this.isFollowUpRequired = isFollowUpRequired;
    }

    /**
    * Gets this Immunization's abbreviation 
     * @return A String representing this Immunization's abbreviation
    */
    public String getImmunizationAbbreviation() {
        return immunizationAbbreviation;
    }

    /*
    public String createAbbreviation() {
        String abbreviation = immunization.substring(0, 1);
        
        if(immunization.contains(" ")){
            abbreviation += immunization.substring(immunization.indexOf(" ") + 1, immunization.indexOf(" ") + 2);
        }else{
            abbreviation += immunization.substring(0, 2);
        }
        
        return abbreviation;
    }*/
    
    
}
