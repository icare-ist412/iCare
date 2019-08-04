/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package icare.controllers;

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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

/**
 *
 * @author David Ortiz
 */
public class LoginViewController implements Initializable {
    
    @FXML
    private TextField userIdField;
    @FXML
    private Button loginButton;
    
    @FXML
    private PasswordField passwordField;
    
    @FXML
    private Label statusLabel;
    
    private String userID;
    private String password;
    
    private User currentUser;
    private Storage storage;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loginButton.setDisable(true);
    }    
    
    /**
     * Passes data when a new instance of class is created.
     */
    public void initData(Storage storage){
        this.storage = storage;
    }
    
    /**
     * Handles the Logout button onclick event.
     */
    public void loginBtnClicked(ActionEvent event) throws IOException {

        userID = userIdField.getText();
        password = passwordField.getText();

        if (userID.equals("") || password.equals("")) {
            statusLabel.setText("One of the fields is empty");
            passwordField.setText("");
        } else {
            
            //authentication
            String authResult = performAuth(userID, password);
            if(authResult.equals("valid")){
                resetScreen();
                goToMainMenu(event);
            } else {
                statusLabel.setText(authResult);
                passwordField.setText("");
            }

        }

    }
    
    private String performAuth(String userID, String password){
        String result;
        userID = userID.toLowerCase();
        
        if(storage.getUserDao().doesUserExist(userID)){
                
            this.currentUser = storage.getUserDao().findById(userID);

            if (this.currentUser.authenticate(userID, password)) {

                result = "valid";

            } else {
                result = "Login failed, incorrect password.";
            }

        } else {
            result = "Login failed, user does not exist";
        }
        return result;
        
    }
    
    private void goToMainMenu(ActionEvent event) throws IOException{
        
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
    
    private void resetScreen() {
        statusLabel.setText("");
        userIdField.setText("");
        passwordField.setText("");

    }
    
    /**
     * Handles the Quit button onclick event.
     */
    public void quitBtnClicked(ActionEvent event) throws IOException {
        System.exit(0);
    }
    
    /**
     * Handles the KeyTyped event for the username and password fields.
     * @param event
     */
    public void keyTyped(KeyEvent event) {
        if(!this.userIdField.getText().equals("") && !this.passwordField.getText().equals("")){
            this.loginButton.setDisable(false);
        }else {
            this.loginButton.setDisable(true);
        }
    }
    
}


