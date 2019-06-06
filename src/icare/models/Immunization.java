package icare.models;

import java.time.LocalDate;

/**
 *
 * @author David Ortiz
 */
public class Immunization {
    
    private String immunization;
    private LocalDate dateAdministered;
    private boolean isFollowUpRequired;
    private String immunizationAbbreviation;

    /**
    * Default constructor for this class
     * @param immunization Sets this Immunization's name
     * @param immunizationAbbreviation Sets this Immunization's abbreviation
    */
    public Immunization(String immunization, String immunizationAbbreviation) {
        this.immunization = immunization;
        this.immunizationAbbreviation = immunizationAbbreviation;
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

    
    
}
