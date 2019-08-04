package icare.models.persistence;

import icare.models.Address;
import icare.models.Hospital;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Dmitry Mikhailov
 */
public class HospitalDao implements CrudRepository<Hospital, String>{
   
    private String sourceFile;
    private ArrayList<Hospital> hospitalList = new ArrayList<>();

    public HospitalDao(String sourceFile) throws FileNotFoundException {
        
        this.sourceFile = sourceFile;
        hospitalList = this.fetchHospitalsFromFile();
    }

    
    @Override
    public ArrayList<Hospital> getAll() {
        return this.hospitalList;
    }
    
    
    

    @Override
    public Hospital findById(String name) {
        Hospital found = null;
        for (Hospital h : this.hospitalList) {
            if (h.getName().equals(name)) {
                found = h;
            }
        }
        return found;
    }

    private ArrayList<Hospital> fetchHospitalsFromFile() throws FileNotFoundException {
       
        ArrayList<Hospital> hospitals = new ArrayList<>();

        String line = null;

        int index = 0;

        try {
            FileReader fileReader = new FileReader(sourceFile);

            BufferedReader bufferedReader = new BufferedReader(fileReader);

            while ((line = bufferedReader.readLine()) != null) {

                if (line != "") {

                    String[] words = line.split(";");
                    String name = words[0];
                    String number = words[1];
                    String streetName = words[2];
                    String city = words[3];
                    String state = words[4];
                    String zip = words[5];
                    String phone = words[6];

                    Address address = new Address(number, streetName, city, state, zip);
                    Hospital hospital = new Hospital(name, address, phone);
                    hospitals.add(hospital);

                }

                index++;
            }

            bufferedReader.close();

        } catch (FileNotFoundException ex) {
            System.out.println("Unable to open file '" + sourceFile + "'");
        } catch (IOException ex) {
            System.out.println("Error reading file '" + sourceFile + "'");
        }
        return hospitals;

    } // end fetchHospitalsFromFile()

    private void printHospitalsForTesting() {
        for (Hospital h : this.hospitalList) {
            System.out.println(h.getName() + " = " + h.getAddress().toString() + " " + h.getPhone());
        }
    }

    @Override
    public void save(Hospital t) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
