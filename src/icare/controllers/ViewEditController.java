/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package icare.controllers;

import icare.models.Patient;
import icare.models.RoleEnum;
import icare.models.Storage;
import icare.models.Treatment;
import icare.models.User;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author David Ortiz
 */
public class ViewEditController implements Initializable {

    private User currentUser;
    private Patient selectedUser;
    private String selectedDisease;
    private Storage storage;
    private RoleEnum userType;
    
    private Treatment selectedTreatment;
    @FXML
    private Button deleteTreatmentBtn;
    
    protected ListProperty<String> listProperty = new SimpleListProperty<>();
    @FXML
    private Label userTitleLbl;
    
    @FXML
    private Label warningLbl;
   
    @FXML
    private ListView listView;
    
    @FXML
    private TableView tableView;
    
    @FXML
    private TableColumn<Treatment, String> instructionsCol;
    @FXML
    private TableColumn<Treatment, String> medicationCol;
    @FXML
    private TableColumn<Treatment, String> weeksCol;
    
    @FXML
    private TextField fnameLbl;
    
    @FXML
    private TextField lnameLbl;
            
    @FXML
    private Label genderLbl;
    
    @FXML
    private TextField insuranceLbl;
    
    @FXML
    private DatePicker dobPicker;
    
    @FXML
    private Button deleteDiseaseBtn;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        listView.setPlaceholder(new Label("No records to display"));
        
        instructionsCol.setCellValueFactory(new PropertyValueFactory<>("instructions"));
        medicationCol.setCellValueFactory(new PropertyValueFactory<>("medication"));
        weeksCol.setCellValueFactory(new PropertyValueFactory<>("numberOfWeeks"));
        
        
    }    
    
    public void initData(User currentUser, Patient selectedUser, Storage storage){
        this.currentUser = currentUser;
        this.selectedUser = selectedUser;
        this.storage = storage;
        this.userType = currentUser.getRoleType();
        
        String fname = this.selectedUser.getFirstName().substring(0, 1).toUpperCase() + this.selectedUser.getFirstName().substring(1);
        String lname = this.selectedUser.getLastName().substring(0, 1).toUpperCase() + this.selectedUser.getLastName().substring(1);

        fnameLbl.setText(fname);
        lnameLbl.setText(lname);
        genderLbl.setText(selectedUser.getGender());
        insuranceLbl.setText(String.valueOf(selectedUser.getInsuranceID()));
        dobPicker.setValue(selectedUser.getBirthdate());
        
        listView.itemsProperty().bind(listProperty);
        
        if(this.selectedUser.getTreatments() != null){
            tableView.getItems().setAll(this.selectedUser.getTreatments());
        }
        
        if(this.selectedUser.getDiseases() != null){
            listProperty.set(FXCollections.observableArrayList(this.selectedUser.getDiseases()));
        }
            
        this.userTitleLbl.setText(fname);
       
    }
       
    private void updateDiseaseList(){
        listProperty.set(FXCollections.observableArrayList(this.selectedUser.getDiseases()));
    }
    
    private void updateTreatmentList(){
        tableView.getItems().setAll(this.selectedUser.getTreatments());
    }
    
    //for testing
    private void printDiseases(){
        for(String d : this.selectedUser.getDiseases()){
            System.out.println(d);
        }    
    }
    
    public void saveBtnClicked(ActionEvent event) throws IOException{
        
        if(validateMedicalRecordInfo().equals("valid")){
            this.selectedUser.setFirstName(this.fnameLbl.getText());
            this.selectedUser.setLastName(this.lnameLbl.getText());
            this.selectedUser.setBirthdate(this.dobPicker.getValue());
            this.selectedUser.setInsuranceID(Long.valueOf(this.insuranceLbl.getText()));
            
            storage.getUserDao().writeUserListFile();
            returnToViewPatients(event); 
        } else {
            this.warningLbl.setText(validateMedicalRecordInfo());
        }
        
        
    }
    
    private String validateMedicalRecordInfo(){
        String result = "";
        
        if(!this.fnameLbl.getText().isEmpty()
                && !this.lnameLbl.getText().isEmpty() 
                && this.dobPicker.getValue() != null 
                && !this.insuranceLbl.getText().isEmpty() )
        {
            if(dobPicker.getValue().isBefore(LocalDate.now())){  
                if(insuranceLbl.getText().matches("\\d*")){
                    result = "valid";
                } else {
                    result = "Insurance IDs should only include integers.";

                }

            } else {
                result = "Birthdate must be in the past.";
            }
            
        } else {
            result = "All fields must be filled out.";
        }
        
        return result;
    }
    
    public void cancelBtnClicked(ActionEvent event) throws IOException{
        
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to cancel? No changes will be saved.", ButtonType.YES, ButtonType.NO);
        alert.setHeaderText("Confirm cancel");
        alert.setGraphic(null);
        alert.showAndWait();

        if (alert.getResult() == ButtonType.YES) {
            returnToViewPatients(event);
        }
    }
    
    
    public void returnToViewPatients(ActionEvent event) throws IOException{
        
        FXMLLoader loader = new FXMLLoader();
        Scene scene = null;
        
        if(userType==RoleEnum.Staff){
            
            loader.setLocation(getClass().getResource("/icare/views/ViewPatients.fxml"));
            Parent root = loader.load();

            scene = new Scene(root);

            //access the controller and call a method
            ViewPatientsController controller = loader.getController();        
            controller.initData(this.storage, this.currentUser);
        
        } else {
            
            loader.setLocation(getClass().getResource("/icare/views/MainMenuView.fxml"));
            Parent root = loader.load();

            scene = new Scene(root);

            //access the controller and call a method
            MainMenuViewController controller = loader.getController();
            controller.initData(this.storage, this.currentUser);
            
        }
        
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(scene);
        window.show();
        
    }
    
    
    public void deleteDiseaseBtnClicked(ActionEvent event){
        this.selectedUser.removeDisease(this.selectedDisease);
        updateDiseaseList();
        deleteDiseaseBtn.setDisable(true);
        
    }
    
    public void deleteTreatmentBtnClicked(ActionEvent event){
        this.selectedUser.removeTreatment(this.selectedTreatment);
        updateTreatmentList();
        this.deleteTreatmentBtn.setDisable(true);
    }
    
    public void addTreatmentClicked(ActionEvent event) {
        warningLbl.setText("");
        
        Dialog<Treatment> dialog = new Dialog<>();
        dialog.setTitle("New Treatment");
        dialog.setHeaderText("Please specify…");
        DialogPane dialogPane = dialog.getDialogPane();
        dialogPane.getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);
        TextField instrField = new TextField();
        TextField medField = new TextField();
        TextField weeksField = new TextField();
        
        instrField.setPromptText("Instructions");
        medField.setPromptText("Medication");
        weeksField.setPromptText("# of weeks");
                
        dialogPane.setContent(new VBox(8, medField, instrField, weeksField));
        
        Platform.runLater(medField::requestFocus);
        
        dialog.setResultConverter((ButtonType button) -> {
            if (button == ButtonType.OK) {
                if( !instrField.getText().equals("") &&  !medField.getText().equals("") && !weeksField.getText().equals("") ){
                    if(weeksField.getText().matches("\\d*")){
                        warningLbl.setText("");
                        return new Treatment( instrField.getText(), medField.getText(), Integer.parseInt(weeksField.getText()) );
                    } else {
                        warningLbl.setText("Error adding treatment: number of weeks can only be a number.");
                    }
                } else {
                    warningLbl.setText("Error adding treatment: please fill out all fields.");
                }
            }
            
            return null;
        });
        
        Optional<Treatment> optionalResult = dialog.showAndWait();
        optionalResult.ifPresent(
            (Treatment treatmnt) -> {
                this.selectedUser.addTreatment(treatmnt);
                updateTreatmentList();
            }
        );
        

    }
            
    public void addDiseaseClicked(ActionEvent event) {
        TextInputDialog dialog = new TextInputDialog();
        
        dialog.setTitle("New Disease Entry");
        dialog.setHeaderText("Enter the sickness/disease");
        dialog.setGraphic(null);

        Optional<String> result = dialog.showAndWait();
        if (result.isPresent() && !result.get().equals("")) {
            this.selectedUser.addDisease(result.get());
            updateDiseaseList();
        } 
    }
    
    public void userClickedListView(){
        
        try{
            this.selectedDisease = listView.getSelectionModel().getSelectedItem().toString();
            
            if(!selectedDisease.isEmpty()){
                deleteDiseaseBtn.setDisable(false);
            }
            
        } catch(NullPointerException e){
            System.out.println("No disease selected!");
        }
        
    }
    
    public void userClickedTableView(){
        
        try{
            this.selectedTreatment = (Treatment)tableView.getSelectionModel().getSelectedItem();
            
            if(selectedTreatment != null){
                this.deleteTreatmentBtn.setDisable(false);
            }
            
        } catch(NullPointerException e){
            System.out.println("No treatment selected!");
        }
        
    }
    
    
    
    
}
