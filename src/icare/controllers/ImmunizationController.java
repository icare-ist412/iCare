package icare.controllers;

import icare.models.Immunization;
import icare.models.Patient;
import icare.models.Storage;
import icare.models.User;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
    private Patient selectedPatient;
    
    @FXML
    private TableView<Immunization> immunizationTable;
    
    @FXML
    private TableColumn immunization;
    
    @FXML
    private TableColumn date;
    
    @FXML
    private Button addButton;
    
    @FXML
    private Button deleteButton;
    
    @FXML
    private Label userNameLbl;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    
    public void initData(Storage storage, User currentUser, Patient selectedPatient){
        this.storage = storage;
        this.currentUser = currentUser;
        this.selectedPatient = selectedPatient;
        
        String fname = this.selectedPatient.getFirstName().substring(0, 1).toUpperCase() + this.selectedPatient.getFirstName().substring(1);

        userNameLbl.setText(fname+"'s Immunizations");
        date.setCellValueFactory(new PropertyValueFactory<Immunization, Date>("dateAdministered"));
        immunization.setCellValueFactory(new PropertyValueFactory<Immunization, String>("immunization"));
       
        immunizationTable.getItems().setAll(selectedPatient.getImmunizations());
    }
    
    public void backBtnClicked(ActionEvent event) throws IOException{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/icare/views/ViewPatients.fxml"));
        Parent root = loader.load();
        
        Scene scene = new Scene(root);
        
        //access the controller and call a method
        ViewPatientsController controller = loader.getController();        
        controller.initData(this.storage, this.currentUser);
        
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(scene);
        window.show();
        
    }
    
    public void deleteButtonPressed(ActionEvent event) throws IOException{
        Immunization selected = immunizationTable.getSelectionModel().getSelectedItem();
        this.selectedPatient.removeImmunization(selected);
        immunizationTable.getItems().setAll(selectedPatient.getImmunizations());
    }
    
    public void addButtonPressed(ActionEvent event) throws IOException{
        System.out.println("Add feature under development");
    }
    

}
