/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package icare.controllers;

import icare.models.Patient;
import icare.models.User;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

/**
 * FXML Controller class
 *
 * @author David Ortiz
 */
public class ViewEditController implements Initializable {

    private User currentUser;
    private Patient selectedUser;
    protected ListProperty<String> listProperty = new SimpleListProperty<>();
    @FXML
    private Label userTitleLbl;
   
    @FXML
    private ListView listView;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    
    public void initData(User currentUser, Patient selectedUser){
        this.currentUser = currentUser;
        this.selectedUser = selectedUser;
        listView.itemsProperty().bind(listProperty);
        if(this.selectedUser.getDiseases() != null){
            
            listProperty.set(FXCollections.observableArrayList(this.selectedUser.getDiseases()));
        }
            
        this.userTitleLbl.setText(this.selectedUser.getFirstName());
       
    }
    
    public void addDiseaseClicked(ActionEvent event){
        //TODO: make popup alert to type in disease name
        this.selectedUser.addDisease("illness");
        saveDisease();
        
        //printDiseases();
        
        
    }
    
    private void saveDisease(){
        listProperty.set(FXCollections.observableArrayList(this.selectedUser.getDiseases()));

    }
    
    private void printDiseases(){
        for(String d : this.selectedUser.getDiseases()){
            System.out.println(d);
        }
            
    }
    
    
}
