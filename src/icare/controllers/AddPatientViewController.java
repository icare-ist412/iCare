/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package icare.controllers;

import icare.models.Patient;
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
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author David Ortiz
 */
public class AddPatientViewController implements Initializable {

    private Storage storage;
    private User currentUser;
    private Patient newPatient;
    
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
    private Label warningLbl;
    
    
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
        
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        if(fnameLbl.getText().isEmpty() || lnameLbl.getText().isEmpty() || insuranceLbl.getText().isEmpty() || dobPicker.getValue() == null || passwordLbl.getText().isEmpty()){
            this.warningLbl.setText("Please fill out all fields.");
        } else {
            
            if(insuranceLbl.getText().matches("\\d*")){
                
                if(dobPicker.getValue().isBefore(LocalDate.now())){
                    
                    //**** all entered data is valid *****
                    this.warningLbl.setText("");
                    newPatient = new Patient(fnameLbl.getText(), lnameLbl.getText(), Long.parseLong(insuranceLbl.getText()), dateTimeFormatter.format(dobPicker.getValue()));
                    newPatient.updateCredential(passwordLbl.getText());
                    
                    this.storage.addToUserList(newPatient);
                    
                    
                    goToMainMenu(event);

                } else {
                    this.warningLbl.setText("Please select a date in the past.");
                }
            } else {
                this.warningLbl.setText("Insurance IDs should only include integers.");
            }
            
        }
       
    }
    
}
