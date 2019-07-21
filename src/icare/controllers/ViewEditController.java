/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package icare.controllers;

import icare.models.Patient;
import icare.models.Treatment;
import icare.models.User;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
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

/**
 * FXML Controller class
 *
 * @author David Ortiz
 */
public class ViewEditController implements Initializable {

    private User currentUser;
    private Patient selectedUser;
    private String selectedDisease;
    
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
    
    public void initData(User currentUser, Patient selectedUser){
        this.currentUser = currentUser;
        this.selectedUser = selectedUser;
        listView.itemsProperty().bind(listProperty);
        
        if(this.selectedUser.getTreatments() != null){
            tableView.getItems().setAll(this.selectedUser.getTreatments());
        }
        
        if(this.selectedUser.getDiseases() != null){
            listProperty.set(FXCollections.observableArrayList(this.selectedUser.getDiseases()));
        }
            
        String fname = this.selectedUser.getFirstName().substring(0, 1).toUpperCase() + this.selectedUser.getFirstName().substring(1);
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
    
    public void deleteDiseaseBtnClicked(ActionEvent event){
        this.selectedUser.removeDisease(this.selectedDisease);
        updateDiseaseList();
        if(this.selectedUser.getDiseases().isEmpty()){
            deleteDiseaseBtn.setDisable(true);
        }
        
    }
    
    public void deleteTreatmentBtnClicked(ActionEvent event){
//        this.selectedUser.removeTreatment(this.selectedDisease);
//        updateDiseaseList();
//        if(this.selectedUser.getDiseases().isEmpty()){
//            deleteDiseaseBtn.setDisable(true);
//        } 
    }
    
    public void addTreatmentClicked(ActionEvent event) {
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
                
        dialogPane.setContent(new VBox(8, instrField, medField, weeksField));
        
        Platform.runLater(instrField::requestFocus);
        
        Treatment treatment;
        dialog.setResultConverter((ButtonType button) -> {
            if (button == ButtonType.OK) {
                if( !instrField.getText().equals("") &&  !medField.getText().equals("") && !weeksField.getText().equals("") ){
                    if(weeksField.getText().matches("\\d*")){
                        return new Treatment( instrField.getText(), medField.getText(), Integer.parseInt(weeksField.getText()) );
                    }
                }
            }
            return null;
        });
        Optional<Treatment> optionalResult = dialog.showAndWait();
        
        
        //TODO: add error message or alert if invalid data entered
        optionalResult.ifPresent(
            (Treatment treatmnt) -> {
                System.out.println(treatmnt.getInstructions() + " " + treatmnt.getMedication() + " " + treatmnt.getNumberOfWeeks());
                this.selectedUser.addTreatment(treatmnt);
                updateTreatmentList();
            }
        );
        
//        if (optionalResult.isPresent() && !optionalResult.get().equals("")) {
//            
//            this.selectedUser.addTreatment(optionalResult.get());
//            updateDiseaseList();
//        } 
        
//             this.warningLbl.setText("");
//        } else {
//            this.warningLbl.setText("Error adding treatment: Enter valid data.");
//            
//        } 
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
    
    
}
