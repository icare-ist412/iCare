package testharness;

import icare.controllers.LoginViewController;
import icare.models.Address;
import icare.models.Appointment;
import icare.models.Patient;
import icare.models.Staff;
import icare.models.User;
import java.time.LocalDateTime;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

/**
 *
 * @author David Ortiz
 */
public class stubsController {

    private static final String HEADER = "------------------------------\nClass stub: ";
    private static final String FOOTER = "------------------------------\n";
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        //Stubs calls
        userStub();
        staffStub();
        patientStub();
        addressTest();
        appointmentTest();
        loginViewControllerTest();

    }

    private static void print(Object text) {
        System.out.println(text);
    }

    private static void patientStub() {

        print(HEADER + "Patient");

        Patient patient = new Patient("David", "Ortiz", 123456789, "1995-08-03");
        print(patient.getInsuranceID());
        patient.setInsuranceID(987654321);
        print(patient.getInsuranceID());

        print(FOOTER);
    }

    private static void staffStub() {

        print(HEADER + "Staff");

        Staff staff = new Staff("David", "Ortiz", "IT", "1995-08-03");
        print(staff.getDepartment());

        print(FOOTER);
    }

    private static void userStub() {

        print(HEADER + "User");

        User user = new User("David", "Ortiz", "1995-08-03");

        print(user.getRoleType());
        print(user.getBirthdate());

        print(user.getFirstName());
        print(user.getLastName());
        print(user.getFullName());

        user.updateCredential("test");

        print(user.getCredential());
        print(user.getUserID());

        print(user.authenticate(user.getUserID(), "test"));

        print(FOOTER);

    }

    private static void addressTest() {
        print(HEADER + "Adress");
        Address address = new Address("767 5th Ave","New York", "NY", 10153);  
        print(address.getStreetAddress());
        print(address.getCity());
        print(address.getState());
        print(address.getZipCode());
        print(FOOTER);
    }

    private static void appointmentTest() {
        print(HEADER + "Appointment");
        Appointment appointment = new Appointment();
        Patient patient = new Patient("David", "Ortiz", 123456789, "1995-08-03");
        Staff staff = new Staff("David", "Ortiz", "IT", "1995-08-03");
        LocalDateTime aDate = LocalDateTime.now().plusDays(3).withHour(15).withMinute(15);
        appointment.setAddress(new Address("767 5th Ave","New York", "NY", 10153));
        appointment.setDate(aDate);
        appointment.setPatient(patient);
        appointment.setStaff(staff);           
        print(appointment.getAddress().toString());
        print(appointment.getDateAsString());
        print(appointment.getPatient().toString());
        print(appointment.getStaff().toString());
        print(FOOTER);
        
    }

    private static void loginViewControllerTest() {
       print(HEADER + "LoginViewController");
       
       LoginViewController loginViewController = new LoginViewController();
       KeyEvent key = new KeyEvent(KeyEvent.KEY_TYPED, "Z", "Z", KeyCode.Z, false, false, false, false);
       loginViewController.keyTyped(key);
       
       // the other methods of LoginViewController pertain to GUI and can't be test using this approach for testing
       
       print(FOOTER);
    }

}
