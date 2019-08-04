/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package icare.models.persistence;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Dmitry Mikhailov
 */
public class RequiredVaccinesDao implements CrudRepository<String, String>{

    private String sourceFile;
    private ArrayList<String> requiredVaccines = new ArrayList<>();

    public RequiredVaccinesDao(String sourceFile) {
        this.sourceFile = sourceFile;
        requiredVaccines = fetchVaccinesFromFile();

    }

    @Override
    public ArrayList<String> getAll() {
        return this.requiredVaccines;
    }
       

    private ArrayList<String> fetchVaccinesFromFile() {

        ArrayList<String> vaccines = new ArrayList<>();

        String line = null;

        int index = 0;

        try {
            FileReader fileReader = new FileReader(sourceFile);

            BufferedReader bufferedReader = new BufferedReader(fileReader);

            while ((line = bufferedReader.readLine()) != null) {

                if (!line.equals("")) {

                    vaccines.add(line);

                }

                index++;
            }

            bufferedReader.close();

        } catch (FileNotFoundException ex) {
            System.out.println("Unable to open file '" + sourceFile + "'");
        } catch (IOException ex) {
            System.out.println("Error reading file '" + sourceFile + "'");
        }
        return vaccines;

    } // end fetchVaccinesFromFile()

    @Override
    public void save(String t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String findById(String id) {
        String found = null;
        for (String h : this.requiredVaccines) {
             if (h.equalsIgnoreCase(id)) {
                found = h;
            }
        }
        return found;
    }

}
