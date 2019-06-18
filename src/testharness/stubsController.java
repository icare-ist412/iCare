package testharness;

import icare.models.Patient;
import icare.models.Staff;
import icare.models.User;


/**
 *
 * @author David Ortiz
 */
public class stubsController {
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        //Stubs calls
        userStub();
        staffStub();
        patientStub();
        
    }
    
    private static void print(Object text){
        System.out.println(text);
    }
    
    private static void patientStub(){
        
        print("------------------------------"); 
        print("class stub: Patient");
        
        Patient patient = new Patient("David", "Ortiz", 123456789, "1995-08-03");
        print(patient.getInsuranceID());
        patient.setInsuranceID(987654321);
        print(patient.getInsuranceID());
        
        print("------------------------------");        
        print("");    
    }
    
    private static void staffStub(){
        
        print("------------------------------"); 
        print("class stub: Staff");
        
        Staff staff = new Staff("David", "Ortiz", "IT", "1995-08-03");
        print(staff.getDepartment());
        
        print("------------------------------");        
        print("");    
    }
    
    private static void userStub(){
        
        print("------------------------------");        
        print("class stub: User");
        
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
        
        print("------------------------------");        
        print("");        

        
    }
    
}
