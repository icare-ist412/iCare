/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package icare.controllers;

import icare.models.Patient;
import icare.models.RoleEnum;
import icare.models.Storage;
import icare.models.User;
import java.io.IOException;
import java.net.URL;
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
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author David Ortiz
 */
public class MainMenuViewController implements Initializable {

    @FXML 
    private Label fnameLabel;
    
    @FXML 
    private Button addPatientBtn;
    
    @FXML
    private Button viewPatientsBtn;
    
    @FXML
    private Button medRecordBtn;
    @FXML
    private Button myAppointmentsBtn;
    
    
    @FXML
    private Pane staffPane;
    @FXML
    private Pane patientPane;
    
    private Storage storage;
    private User currentUser;
    
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        staffPane.setVisible(false);
        patientPane.setVisible(false);
        
        
    }    
    
    /**
     * Passes data when a new instance of class is created.
     */
    public void initData(Storage storage, User currentUser){
        this.storage = storage;
        this.currentUser = currentUser;
        
        if(this.currentUser.getRoleType() == RoleEnum.Staff){
            this.addPatientBtn.setDisable(false);
            this.viewPatientsBtn.setDisable(false);
            staffPane.setVisible(true);
        } else {
            this.addPatientBtn.setDisable(true);
            this.viewPatientsBtn.setDisable(true);
            patientPane.setVisible(true);
        }
        
        String fname = this.currentUser.getFirstName().substring(0, 1).toUpperCase() + this.currentUser.getFirstName().substring(1);
        this.fnameLabel.setText("Welcome, " + fname);
        
    }
   
    /**
     * Handles the Quit button onclick event.
     */
    public void quitButtonClicked(ActionEvent event){
        System.exit(0);
    }
    
    public void myRecordBtnClicked(ActionEvent event) throws IOException{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/icare/views/ViewEditView.fxml"));
        Parent root = loader.load();
        
        Scene scene = new Scene(root);

        ViewEditController controller = loader.getController();        
        controller.initData(this.currentUser, (Patient)this.currentUser, this.storage);
        
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(scene);
        window.show();
    }
    
    public void myAppointmentsBtnClicked(ActionEvent event) throws IOException{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/icare/views/AppointmentsView.fxml"));
        Parent root = loader.load();
        
        Scene scene = new Scene(root);

        AppointmentsViewController controller = loader.getController();        
        controller.initData(this.storage, this.currentUser, (Patient)this.currentUser);
        
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(scene);
        window.show();
    }
            
    public void myImmBtnClicked(ActionEvent event) throws IOException{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/icare/views/ImmunizationView.fxml"));
        Parent root = loader.load();
        
        Scene scene = new Scene(root);

        ImmunizationController controller = loader.getController();        
        controller.initData(this.storage, this.currentUser, (Patient)this.currentUser);
        
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(scene);
        window.show();
    }
    
    /**
     * Handles the Logout button onclick event.
     */
    public void logoutButtonClicked(ActionEvent event) throws IOException{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/icare/views/LoginView.fxml"));
        Parent root = loader.load();
        
        Scene scene = new Scene(root);
        
        //access the controller and call a method
        LoginViewController controller = loader.getController();        
        controller.initData(this.storage);
        
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(scene);
        window.show();
    }
    
    public void addUserBtnClicked(ActionEvent event) throws IOException{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/icare/views/AddUserView.fxml"));
        Parent root = loader.load();
        
        Scene scene = new Scene(root);
        
        //access the controller and call a method
        AddUserViewController controller = loader.getController();        
        controller.initData(this.storage, this.currentUser);
        
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(scene);
        window.show();
        
    }
    
    public void viewPatientsBtnClicked(ActionEvent event) throws IOException{
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
    
    
    
}
