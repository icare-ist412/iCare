package icare.controllers;

import icare.models.Immunization;
import icare.models.Storage;
import icare.models.User;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Date;
import java.util.Iterator;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Immunization Controller class
 *
 * @author Jake Benedick
 */
public class ImmunizationController implements Initializable {

    private Storage storage;
    private User currentUser;
    //private Patient newPatient;
    private String selectedUserType;
    
    @FXML
    private TableView<Immunization> immunizationTable;
    
    @FXML
    private TableColumn firstName;
    
    @FXML
    private TableColumn lastName;
    
    @FXML
    private TableColumn date;
    
    @FXML
    private TableColumn details;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    
    public void initData(Storage storage, User currentUser){
        this.storage = storage;
        this.currentUser = currentUser;
        
        firstName.setCellValueFactory(new PropertyValueFactory<Immunization, String>("firstName"));
        lastName.setCellValueFactory(new PropertyValueFactory<Immunization, String>("lastName"));
        date.setCellValueFactory(new PropertyValueFactory<Immunization, Date>("dateAdministered"));
        details.setCellValueFactory(new PropertyValueFactory<Immunization, String>("immunization"));
        
        immunizationTable.getItems().setAll(storage.getImmunizationList());
    }
    
    public void goToMainMenu(ActionEvent event) throws IOException{
        
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/icare/views/MainMenuView.fxml"));
        Parent root = loader.load();
        
        Scene scene = new Scene(root);
        
        //access the controller and call a method
        MainMenuViewController controller = loader.getController();        
        controller.initData(this.storage, this.currentUser);
        
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(scene);
        window.show();
        
    }
    
    public void deleteButtonPressed(ActionEvent event) throws IOException{
        Immunization selected = immunizationTable.getSelectionModel().getSelectedItem();
        
        removeImmunizationRecord(selected.getImmunization(), selected.getDateAdministered(), selected.getFirstName(), selected.getLastName());
        
        immunizationTable.getItems().setAll(storage.getImmunizationList());
    }
    
    public void removeImmunizationRecord(String immunization, LocalDate dateAdministered, String firstName, String lastName){
        Iterator<Immunization> iterator = storage.getImmunizationList().iterator();
        Immunization temp;
        
        while(iterator.hasNext()){
            temp  = iterator.next();
            
            if(temp.getFirstName().equals(firstName) 
                && temp.getLastName().equals(lastName) 
                && temp.getDateAdministered().equals(dateAdministered) 
                && temp.getImmunization().equals(immunization)){
                
                iterator.remove();
                System.out.println("Removed: " + temp.toString());
            }
        }
    }
}
