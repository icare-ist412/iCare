/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package icare.controllers;

import icare.models.Patient;
import icare.models.Staff;
import icare.models.Storage;
import icare.models.User;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author David Ortiz
 */
public class AddUserViewController implements Initializable {

    private Storage storage;
    private User currentUser;
    //private Patient newPatient;
    private String selectedUserType;
    
    @FXML
    private TextField fnameLbl;
    
    @FXML
    private TextField lnameLbl;
    
    @FXML
    private TextField userIDLbl;
    
    @FXML
    private TextField passwordLbl;
    
    @FXML
    private DatePicker dobPicker;
    
    @FXML
    private TextField insuranceLbl;
    
    @FXML 
    private TextField departmentLbl;
    
    @FXML 
    private Label warningLbl;
    
    @FXML
    private ToggleGroup userType;
    
    @FXML
    private HBox insuranceBox;
    
    @FXML
    private HBox departmentBox;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public void initData(Storage storage, User currentUser){
        this.storage = storage;
        this.currentUser = currentUser;
        this.warningLbl.setText("");
        this.departmentBox.setVisible(false);
        this.selectedUserType = ((RadioButton)userType.getSelectedToggle()).getText();
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
    
    public void userTypeSelected(ActionEvent event){
        
        this.selectedUserType = ((RadioButton)userType.getSelectedToggle()).getText();
        switch (this.selectedUserType) {
            case "Patient":
                this.insuranceBox.setVisible(true);
                this.departmentBox.setVisible(false);
                break;
            case "Staff":
                this.insuranceBox.setVisible(false);
                this.departmentBox.setVisible(true);
                break;
            default:
                break;
        }
        
    }
    
    
    public void fnameEntered(KeyEvent keyEvent){
        generateUserID();
    }
    
    public void lnameEntered(KeyEvent keyEvent){
        generateUserID();
    }
    
    private void generateUserID(){
        
        String lastNameTwoChars = "";
        if(this.fnameLbl.getText().length()>=2){
            lastNameTwoChars = this.fnameLbl.getText().substring(0, 2);
        }
        
        this.userIDLbl.setText((this.lnameLbl.getText() + lastNameTwoChars).toLowerCase());
        
    }
    
    public void generatePassword(ActionEvent event){
        String randomChars = "";
        Random random = new Random();
        int wordSize = 8;
        
        char[] word = new char[wordSize]; 
        for(int i = 0; i < word.length; i++)
        {
            word[i] = (char)('a' + random.nextInt(26));
        }
        randomChars = new String(word);
        
        passwordLbl.setText(randomChars);
    }
    
    public void saveBtnClicked(ActionEvent event) throws IOException{
        

        if(fnameLbl.getText().isEmpty() || lnameLbl.getText().isEmpty() ||  dobPicker.getValue() == null || passwordLbl.getText().isEmpty()){
            this.warningLbl.setText("Please fill out all fields.");
        } else {
            //selectedUserType
            if(dobPicker.getValue().isBefore(LocalDate.now())){
                
                
                switch (this.selectedUserType) {
                    case "Staff":
                        
                        if(!departmentLbl.getText().isEmpty()){
                            //**** all entered data is valid *****
                            saveNewUser();
                            goToMainMenu(event);
                        } else {
                            this.warningLbl.setText("Please fill out all fields.");
                        }
                        
                        
                        
                        break;
                    case "Patient":
                        
                        if(!insuranceLbl.getText().isEmpty() && insuranceLbl.getText().matches("\\d*")){
                    
                            //**** all entered data is valid *****
                            saveNewUser();
                            goToMainMenu(event);

                        } else {
                            this.warningLbl.setText("Insurance IDs should only include integers.");
                        }
                        
                        
                        
                        break;
                    default:
                        break;
                }
                
                
                
                
                
                
            } else {
                this.warningLbl.setText("Please select a date in the past.");
            }
            
        }
       
    }
    
    private void saveNewUser(){
        
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        switch (this.selectedUserType) {
            case "Staff":

                this.warningLbl.setText("");
                Staff newStaff = new Staff(fnameLbl.getText().toLowerCase(), lnameLbl.getText().toLowerCase(), departmentLbl.getText(), dateTimeFormatter.format(dobPicker.getValue()));
                newStaff.updateCredential(passwordLbl.getText());

                this.storage.addToUserList(newStaff);



                break;
            case "Patient":

                this.warningLbl.setText("");
                Patient newPatient = new Patient(fnameLbl.getText().toLowerCase(), lnameLbl.getText().toLowerCase(), Long.parseLong(insuranceLbl.getText()), dateTimeFormatter.format(dobPicker.getValue()));
                newPatient.updateCredential(passwordLbl.getText());

                this.storage.addToUserList(newPatient);

                break;
            default:
                break;
        }
        
       
    }
    
}
