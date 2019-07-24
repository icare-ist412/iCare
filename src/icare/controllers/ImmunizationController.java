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
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.shape.Rectangle;
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
    private String firstName;
    
    @FXML
    private TableView<Immunization> immunizationTable;
    
    @FXML
    private TableColumn immunizationName;
    
    @FXML
    private TableColumn date;
    
    @FXML
    private TableColumn follow;
    
    @FXML
    private Button addButton;
    
    @FXML
    private Button deleteButton;
    
    @FXML
    private Button createButton;
    
    @FXML
    private Button cancelButton;
    
    @FXML
    private Button backButton;
    
    @FXML
    private Label userNameLbl;
    
    @FXML
    private Label immunizationNameLabel;
    
    @FXML
    private Label dateLabel;
        
    @FXML
    private TextField immunizationNameField;
    
    @FXML
    private Rectangle background;
    
    @FXML
    private CheckBox followUpCheckbox;
    
    @FXML
    private DatePicker datePicker;
    
    @FXML
    private Label warningLbl;
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
        
        firstName = this.selectedPatient.getFirstName().substring(0, 1).toUpperCase() + this.selectedPatient.getFirstName().substring(1);

        userNameLbl.setText(firstName+"'s Immunizations");
        
        immunizationName.setCellValueFactory(new PropertyValueFactory<Immunization, String>("immunization"));
        date.setCellValueFactory(new PropertyValueFactory<Immunization, Date>("dateAdministered"));
        follow.setCellValueFactory(new PropertyValueFactory<Immunization, String>("isFollowUpRequired"));
       
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
        
        Alert alert = new Alert(AlertType.CONFIRMATION, "Are you sure you want to delete " + selected.getImmunization() + " ?", ButtonType.YES, ButtonType.NO);
        alert.setHeaderText("Confirm deletion");
        alert.setGraphic(null);
        alert.showAndWait();

        if (alert.getResult() == ButtonType.YES) {
            this.selectedPatient.removeImmunization(selected);
            immunizationTable.getItems().setAll(selectedPatient.getImmunizations());
            storage.writeUserListFile();
        }
        
    }
    
    public void addButtonPressed(ActionEvent event) throws IOException{
        immunizationNameField.setText("");
        datePicker.setValue(null);
        backButton.setVisible(false);
        addButton.setVisible(false);
        deleteButton.setVisible(false);
        userNameLbl.setText("Add Immunization Record");
        immunizationTable.setVisible(false);
    }
    
    public void cancelButtonPressed(ActionEvent event) throws IOException{
        refresh();
    }
    
    public void createButtonPressed(ActionEvent event) throws IOException{
        boolean followUp = false;
        
        if(immunizationNameField.getText() != null 
                && !immunizationNameField.getText().equals("")
                && datePicker.getValue() != null)
        {
            
            if(followUpCheckbox.isSelected()){
                followUp = true;
            }
            
            warningLbl.setText("");
            selectedPatient.addImmunization(new Immunization(immunizationNameField.getText(), datePicker.getValue(), followUp));
            
            storage.writeUserListFile();
            
            refresh();
        } else {
            warningLbl.setText("Please fill out all fields.");
        }
        
    }
    
    public void refresh(){
        userNameLbl.setText(firstName+"'s Immunizations");
        backButton.setVisible(true);
        addButton.setVisible(true);
        deleteButton.setVisible(true);
        immunizationTable.setVisible(true);
        immunizationTable.getItems().setAll(selectedPatient.getImmunizations());
    }

}
