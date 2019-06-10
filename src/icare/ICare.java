/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package icare;

import icare.controllers.LoginViewController;
import icare.models.Storage;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author David Ortiz
 */
public class ICare extends Application {
    
    private Storage storage;
    
    @Override
    public void start(Stage stage) throws Exception {
        this.storage = new Storage();
        
        
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/icare/views/LoginView.fxml"));
        Parent root = loader.load();
        
        
        Scene scene = new Scene(root);
        
        //access the controller and call a method
        LoginViewController controller = loader.getController();        
        controller.initData(this.storage);
        
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
