package testharness;

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
        hospitalTest();
        storageTest();
        treatmentTest();
        userTest();
        staffTest();
        patientTest();
        addressTest();
        appointmentTest();
        billTest();
        insuranceTest();
        immunizationTest();
    }

    private static void print(Object text) {
        System.out.println(text);
    }

    private static void test(String testName, Object item, Object shouldEqual) {

        //uncomment for debugging:
        //print(item + " == " +shouldEqual);
        
        if (item.equals(shouldEqual)) {
            print(testName + ": passed");
        } else {
            print(testName + ": failed");
        }

    }

    private static void patientTest() {

        print(HEADER + "Patient");

        Patient patient = new Patient("David", "Ortiz", 123456789, "1995-08-03");
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

        Staff staff = new Staff("David", "Ortiz", "IT", "1995-08-03");
        print(staff.getDepartment());

        test("Staff.getRoleType test", staff.getRoleType(), "Staff");
        test("Staff.getDepartment test", staff.getDepartment(), "IT");

        print(FOOTER);
    }

    private static void userTest() {

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

        test("User.getRoleType test", user.getRoleType(), "User");
        test("User.getBirthdate test", user.getBirthdate(), LocalDate.parse("1995-08-03", DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        test("User.getFullName test", user.getFullName(), "David Ortiz");
        test("User.getUserID test", user.getUserID(), "OrtizDa");
        test("User.authenticate test", user.authenticate(user.getUserID(), "test"), true);

        print(FOOTER);

    }

    private static void addressTest() {
        print(HEADER + "Adress");
        Address address = new Address("767 5th Ave", "New York", "NY", 10153);
        print(address.getStreetAddress());
        print(address.getCity());
        print(address.getState());
        print(address.getZipCode());

        test("Address.getStreetAddress test", address.getStreetAddress(), "767 5th Ave");
        test("Address.getCity test", address.getCity(), "New York");
        test("Address.getState test", address.getState(), "NY");
        test("Address.getZipCode test", address.getZipCode(), 10153);

        print(FOOTER);
    }

    private static void appointmentTest() {
        print(HEADER + "Appointment");
        Appointment appointment = new Appointment();
        Patient aPatient = new Patient("David", "Ortiz", 123456789, "1995-08-03");
        Staff employee = new Staff("David", "Ortiz", "IT", "1995-08-03");
        LocalDateTime aDate = LocalDateTime.now().plusDays(3).withHour(15).withMinute(15);
        Address anAddress = new Address("767 5th Ave", "New York", "NY", 10153);
        appointment.setAddress(anAddress);
        appointment.setDate(aDate);
        appointment.setPatient(aPatient);
        appointment.setStaff(employee);
        print(appointment.getAddress().toString());
        print(appointment.getDateAsString());
        print(appointment.getPatient().toString());
        print(appointment.getStaff().toString());

        test("Appointment.getAddress() test", appointment.getAddress(), anAddress);
        test("Appointment.getDateAsString() test", appointment.getDate(), aDate);
        test("Appointment.getPatient() test", appointment.getPatient(), aPatient);
        test("Appointment.getStaff() test", appointment.getStaff(), employee);

        print(FOOTER);

    }

    private static void billTest() {
        print(HEADER + "Bill");
        Bill testBill = new Bill();
        testBill.setBillID(123456);
        testBill.postBillToPatient("12457-890", 250);
        LocalDate aDate = LocalDate.now();

        test("Bill.getBillID() test",testBill.getBillID(), 123456);
        test("Bill.getBillAmount()", testBill.getBillAmount(), new Double(250));
        test("Bill.getBillPatientID()",testBill.getBillPatientID(),"12457-890");
        test("BillPostedDate()", testBill.getBillPostedDate(), aDate);

        print(FOOTER);
    }

    private static void hospitalTest() {
        print(HEADER + "Hospital");

        Address address = new Address("500 University DR", "Hershey", "PA", 17033);
        Hospital hospital = new Hospital(address);
        
        ArrayList<Staff> staffList = new ArrayList<>();
        staffList.add(new Staff("Jake", "Benedick", "Oncology", "1900-01-01"));
        hospital.setStaffList(staffList);

        print(hospital.getAddress().toString());
        print("StaffList: " + hospital.getStaffList().toString());
        
        test("Hospital.getAddress() test", hospital.getAddress(), address);
        test("Hospital.getStaffList() test", hospital.getStaffList(), staffList);

        print(FOOTER);
    }

    private static void storageTest() {
        print(HEADER + "Storage");
        Storage storage = null;

        try {
            storage = new Storage();
        } catch (FileNotFoundException ex) {
            print("Failed to read from file");
        }

        test("Storage.getUserList() test", storage.getUserList(), storage.getUserList());
        print(FOOTER);
    }

    private static void treatmentTest() {
        print(HEADER + "Treatment");

        Treatment treatment = new Treatment("Instructions", "Medication", 6);
        print(treatment.getInstructions());
        print(treatment.getMedication());
        print(treatment.getNumberOfWeeks());
        
        test("Treatment.getInstructions() test", treatment.getInstructions(), "Instructions");
        test("Treatment.getMedication() test", treatment.getMedication(), "Medication");
        test("Treatment.getNumberOfWeeks() test", treatment.getNumberOfWeeks(), 6);

        print(FOOTER);
    }

    private static void insuranceTest() {
        print(HEADER + "Insurance");

        Insurance testInsurance = new Insurance();
        testInsurance.setCompanyName("Allsafe");
        testInsurance.setCompanyPhone("800-321-1234");
        testInsurance.setCompanyAddress("12 Healthy Way, HealthyCity, MD, 29001");
        testInsurance.setPolicyNumber("4569874-869");
        testInsurance.setDeductible(500);

        test("Insurance.getCompanyName()",testInsurance.getCompanyName(),"Allsafe");
        test("Insurance.getCompanyAddress()",testInsurance.getCompanyAddress(),"12 Healthy Way, HealthyCity, MD, 29001");
        test("Insurance.getCompanyPhone()",testInsurance.getCompanyPhone(),"800-321-1234");
        test("Insurance.getPolicyNumber()",testInsurance.getPolicyNumber(),"4569874-869");
        test("Insurance.getDeductible()",testInsurance.getDeductible(),500);
        print(FOOTER);
    }

    private static void immunizationTest() {
        print(HEADER + "Immunization");
        Immunization testImmunization = new Immunization("FLU SHOT", "FS");
        testImmunization.setDateAdministered(LocalDate.now());
        LocalDate aDate = LocalDate.now();
        testImmunization.setIsFollowUpRequired(false);

        test("Immunization.getDateAdministered()",testImmunization.getDateAdministered(),aDate);
        test("Immunization.getImmunization()",testImmunization.getImmunization(),"FLU SHOT");
        test("Immunization.getImmunizationAbbreviation()",testImmunization.getImmunizationAbbreviation(),"FS");
        test("Immunization.isIsFollowUpRequired()",testImmunization.isIsFollowUpRequired(),false);
        print(FOOTER);
    }
}
