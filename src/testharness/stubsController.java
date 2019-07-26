package testharness;

import icare.controllers.MainMenuViewController;
import icare.models.Address;
import icare.models.Appointment;
import icare.models.Hospital;
import icare.models.Patient;
import icare.models.Staff;
import icare.models.Storage;
import icare.models.Treatment;
import icare.models.User;
import icare.models.Bill;
import icare.models.Insurance;
import icare.models.Immunization;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;


/**
 *
 * @author Group One
 */
public class stubsController {

    private static final String HEADER = "------------------------------\nClass stub: ";
    private static final String FOOTER = "------------------------------\n";
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        //Stubs calls
        mainMenuViewControllerTest();
        hospitalTest();
        storageTest();
        treatmentTest();
        userTest();
        staffTest();
        patientTest();
        addressTest();
        appointmentTest();
        billTest();
        InsuranceTest();
        immunizationTest();
    }

    private static void print(Object text) {
        System.out.println(text);
    }
    
    private static void test(String testName, Object item, Object shouldEqual){
        
        //uncomment for debugging:
        //print(item + " == " +shouldEqual);
        
        if(item.equals(shouldEqual)){
            print(testName + ": passed");
        } else {
            print(testName + ": failed");
        }
        
    }

    private static void patientTest() {

        print(HEADER + "Patient");

        Patient patient = new Patient("David", "Ortiz", 123456789, "1995-08-03", "M");
        print(patient.getInsuranceID());
        patient.setInsuranceID(987654321);
        print(patient.getInsuranceID());
        
        test("Patient.getRoleType test", patient.getRoleType(), "Patient");
        test("Patient.getInsurance test", patient.getInsuranceID(), new Long(987654321));
        test("Patient.getFullName test", patient.getFullName(), "David Ortiz");


        print(FOOTER);
    }

    private static void staffTest() {

        print(HEADER + "Staff");

        Staff staff = new Staff("David", "Ortiz", "IT", "1995-08-03", "M");
        print(staff.getDepartment());

        test("Staff.getRoleType test", staff.getRoleType(), "Staff");
        test("Staff.getDepartment test", staff.getDepartment(), "IT");
            
        print(FOOTER);
    }

    private static void userTest() {

        print(HEADER + "User");

        User user = new User("David", "Ortiz", "1995-08-03", "M");

        print(user.getRoleType());
        print(user.getBirthdate());

        print(user.getFirstName());
        print(user.getLastName());
        print(user.getFullName());

        user.updateCredential("test");

        print(user.getCredential());
        print(user.getUserID());

        print(user.authenticate(user.getUserID(), "test"));
        
        test("User.getRoleType test", user.getRoleType(), "User");
        test("User.getBirthdate test", user.getBirthdate(), LocalDate.parse("1995-08-03", DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        test("User.getFullName test", user.getFullName(), "David Ortiz");
        test("User.getUserID test", user.getUserID(), "OrtizDa");
        test("User.authenticate test", user.authenticate(user.getUserID(), "test"), true);

        print(FOOTER);

    }

    private static void addressTest() {
        print(HEADER + "Adress");
        Address address = new Address("767","5th Ave","New York", "NY", "10153");  
        print(address.getStreet());
        print(address.getCity());
        print(address.getState());
        print(address.getZipCode());
        print(FOOTER);
    }

    private static void appointmentTest() {
        print(HEADER + "Appointment");
        Appointment appointment = new Appointment();
        Patient patient = new Patient("David", "Ortiz", 123456789, "1995-08-03", "M");
        Staff staff = new Staff("David", "Ortiz", "IT", "1995-08-03", "M");
        LocalDateTime aDate = LocalDateTime.now().plusDays(3).withHour(15).withMinute(15);
        appointment.setDate(aDate);
        appointment.setPatient(patient);
        appointment.setStaff(staff);           
        print(appointment.getDateAsString());
        print(appointment.getPatient().toString());
        print(appointment.getStaff().toString());
        print(FOOTER);
        
    }

    private static void billTest(){
        print(HEADER + "Bill");
        Bill testBill = new Bill();
        testBill.setBillID(123456);
        testBill.postBillToPatient("12457-890", 250);
        print(testBill.getBillID());
        print(testBill.getBillAmount());
        print(testBill.getBillPatientID());
        print(testBill.getBillPostedDate());
        
        print("Second test");
        Bill testBill2 = new Bill();
        testBill2.setBillPostedDate(LocalDate.now());
        testBill2.setBillpatientID("123457-774");
        testBill2.setBillAmount(150);
        testBill2.setBillID(85412575);
        
        print(testBill.getBillID());
        print(testBill.getBillAmount());
        print(testBill.getBillPatientID());
        print(testBill.getBillPostedDate());
        print(FOOTER);
    }

    private static void hospitalTest() {
        print(HEADER + "Hospital");
        /*
        Hospital hospital = new Hospital();
        hospital.setAddress("500 University DR", "Hershey", "PA", 17033);
        ArrayList<Staff> staffList = new ArrayList<>();
        staffList.add(new Staff("Jake", "Benedick", "Oncology", "1900-01-01", "M"));
        hospital.setStaffList(staffList);
        
        print(hospital.getAddress().toString());
        print("StaffList: " + hospital.getStaffList().toString());
        */
        print(FOOTER);
    }

    private static void storageTest() {
        print(HEADER + "Storage");
        
        try {
            Storage storage = new Storage();
        } catch (FileNotFoundException ex) {
            print("Failed to read from file");
        }
        
        print(FOOTER);
    }

    private static void treatmentTest() {
        print(HEADER + "Treatment");
        
        Treatment treatment = new Treatment("Instructions", "Medication", 6);
        print(treatment.getInstructions());
        print(treatment.getMedication());
        print(treatment.getNumberOfWeeks());
        
        print(FOOTER);
    }

    private static void mainMenuViewControllerTest() {
       print(HEADER + "Main Menu View Controller");
       
       MainMenuViewController mainMenuViewController = new MainMenuViewController();
       User user = new User("David", "Ortiz", "1995-08-03", "M");       
       
       // the other methods of MainMenuViewController pertain to GUI and can't be test using this approach for testing
       
       print("Successfully initialized mainMenuViewController with user information");
       print(FOOTER);
    }

    private static void InsuranceTest(){
        print(HEADER + "Insurance");
        
        Insurance testInsurance = new Insurance();
        testInsurance.setCompanyName("Allsafe");
        testInsurance.setCompanyPhone("800-321-1234");
        testInsurance.setCompanyAddress("12 Healthy Way, HealthyCity, MD, 29001");
        testInsurance.setPolicyNumber("4569874-869");
        testInsurance.setDeductible(500);
        
        print(testInsurance.getCompanyName());
        print(testInsurance.getCompanyAddress());
        print(testInsurance.getCompanyPhone());
        print(testInsurance.getPolicyNumber());
        print(testInsurance.getDeductible());
        print(FOOTER);
    }
    
    private static void immunizationTest(){
        print(HEADER + "Immunization");
        Immunization testImmunization = new Immunization("FLU SHOT","FS", LocalDate.now());
        testImmunization.setDateAdministered(LocalDate.now());
        testImmunization.setIsFollowUpRequired(false);
        
        print(testImmunization.getDateAdministered());
        print(testImmunization.getImmunization());
        print(testImmunization.getImmunizationAbbreviation());
        print(testImmunization.isIsFollowUpRequired());
        print(FOOTER);
    }
}
