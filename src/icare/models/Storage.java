package icare.models;

import icare.models.persistence.HospitalDao;
import icare.models.persistence.RequiredVaccinesDao;
import icare.models.persistence.UserDao;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author David Ortiz
 */
public class Storage implements Serializable {

    private Properties applicationProperties;


    private UserDao userDao;
    private HospitalDao hospitalDao;
    private RequiredVaccinesDao requiredVaccinesDao;

    //private String hospitalFile = "Hospitals.ser";
    /**
     * Default constructor for this class.
     *
     * @throws java.io.FileNotFoundException Thrown when the user's text file is
     * not found
     */
    public Storage() throws FileNotFoundException {

        applicationProperties = new Properties();
        try {
            applicationProperties.load(new FileInputStream("application.properties"));

        } catch (IOException ex) {
            Logger.getLogger(Storage.class.getName()).log(Level.SEVERE, null, ex);
        }

        userDao = new UserDao(applicationProperties.getProperty("UsersFile"),
                applicationProperties.getProperty("UsersListFile"));
        hospitalDao = new HospitalDao(applicationProperties.getProperty("HospitalsFile"));
        requiredVaccinesDao = new RequiredVaccinesDao(applicationProperties.getProperty("VaccinesFile"));

    }

    /**
     * @return the applicationProperties
     */
    public Properties getApplicationProperties() {
        return applicationProperties;
    }


    /**
     * @return the userDao
     */
    public UserDao getUserDao() {
        return userDao;
    }

    /**
     * @return the hospitalDao
     */
    public HospitalDao getHospitalDao() {
        return hospitalDao;
    }

    /**
     * @return the requiredVaccinesDao
     */
    public RequiredVaccinesDao getRequiredVaccinesDao() {
        return requiredVaccinesDao;
    }
}
