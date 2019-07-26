/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package icare.controllers;

import icare.models.Appointment;
import icare.models.Hospital;
import icare.models.Patient;
import icare.models.User;
import icare.models.Storage;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author David Ortiz
 */
public class AppointmentsViewController implements Initializable {
    
    private Storage storage;
    private User currentUser;
    private Patient selectedPatient;
    private String userType;
    
    @FXML
    private Label nameLbl;
    
    @FXML
    private TableView upcomingTableView;
    
    @FXML
    private TableColumn<Appointment, String> dateCol;
    
    @FXML
    private TableColumn<Appointment, String> placeCol;
    
    @FXML
    private TableColumn<Appointment, String> timeCol;
    
    @FXML
    private TableColumn<Appointment, String> reasonCol;
    
    @FXML
    private TableView pastTable;
    
    @FXML
    private TableColumn<Appointment, String> pastDateCol;
    
    @FXML
    private TableColumn<Appointment, String> pastPlaceCol;
    
    @FXML
    private TableColumn<Appointment, String> pastTimeCol;
    
    @FXML
    private TableColumn<Appointment, String> pastReasonCol;
    
    @FXML
    private VBox staffPane;
    
    @FXML
    private VBox patientPane;
    
    @FXML
    private Label warningLbl;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        dateCol.setCellValueFactory(new PropertyValueFactory<>("day"));
        timeCol.setCellValueFactory(new PropertyValueFactory<>("time"));
        placeCol.setCellValueFactory(new PropertyValueFactory<>("location"));
        reasonCol.setCellValueFactory(new PropertyValueFactory<>("reason"));
        
        pastDateCol.setCellValueFactory(new PropertyValueFactory<>("day"));
        pastTimeCol.setCellValueFactory(new PropertyValueFactory<>("time"));
        pastPlaceCol.setCellValueFactory(new PropertyValueFactory<>("location"));
        pastReasonCol.setCellValueFactory(new PropertyValueFactory<>("reason"));
    }    
    
    public void initData(Storage storage, User currentUser, Patient selectedPatient){
        this.storage = storage;
        this.currentUser = currentUser;
        this.selectedPatient = selectedPatient;
        
        this.userType = currentUser.getRoleType();
        String fname = this.selectedPatient.getFirstName().substring(0, 1).toUpperCase() + this.selectedPatient.getFirstName().substring(1);
        nameLbl.setText(fname + "'s Appointments");
        
        upcomingTableView.setPlaceholder(new Label("No upcoming appointments"));
        pastTable.setPlaceholder(new Label("No past appointments"));
        
        //this.selectedPatient.addAppointment(new Appointment(LocalDateTime.now().minusDays(3), this.storage.getHospital("ATZ Health General"), this.selectedPatient ,"testing"));
        //this.selectedPatient.addAppointment(new Appointment(LocalDateTime.now().plusDays(3), this.storage.getHospital("ATZ Health General"), this.selectedPatient ,"testing"));
        
        if(this.selectedPatient.getUpcomingAppointments() != null){
            this.upcomingTableView.getItems().setAll(this.selectedPatient.getUpcomingAppointments());
        }
        
        if(this.selectedPatient.getPastAppointments()!= null){
            this.pastTable.getItems().setAll(this.selectedPatient.getPastAppointments());
        }
    }
    
    public void newAppointmentClicked(ActionEvent event) throws IOException{
        warningLbl.setText("");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        
        Dialog<Appointment> dialog = new Dialog<>();
        dialog.setTitle("New Appointment");
        dialog.setHeaderText("Please specify…");
        DialogPane dialogPane = dialog.getDialogPane();
        dialogPane.getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);
        
        //LocalDateTime date, Hospital hospital, Patient patient, String reason
        
        //TODO replace this with datepicker
        TextField dateField = new TextField("2019-07-07");
        String str = dateField.getText() + " 12:30";
        
        boolean validDate = true;
        
        
        ChoiceBox hospitalDropdown = new ChoiceBox();
        hospitalDropdown.getItems().setAll(toSortedList(this.storage.getHospitals().stream().map(Hospital::getName)));
        
        TextField reasonField = new TextField();
        
        dateField.setPromptText("Date");
        //hospitalDropdown.("Hospital");
        reasonField.setPromptText("Reason");
                
        dialogPane.setContent(new VBox(8, dateField, hospitalDropdown, reasonField));
        
        Platform.runLater(dateField::requestFocus);
        
        dialog.setResultConverter((ButtonType button) -> {
            LocalDateTime dateTime = null;
            try{
                dateTime = LocalDateTime.parse(str, formatter);

            }catch (Exception e) {
                
                System.out.println("invalid date");
            }
            if (button == ButtonType.OK) {
                
                if( !dateField.getText().equals("") &&  
                        hospitalDropdown.getValue() != null &&
                        !reasonField.getText().equals("") ){
                    
                    warningLbl.setText("here");
                    //todo make this work
                    return new Appointment( dateTime, storage.getHospital(hospitalDropdown.getValue().toString()),this.selectedPatient, reasonField.getText() );
                    /*
                    if(weeksField.getText().matches("\\d*")){
                        warningLbl.setText("");
                        return new Treatment( instrField.getText(), medField.getText(), Integer.parseInt(weeksField.getText()) );
                    } else {
                        warningLbl.setText("Error adding treatment: number of weeks can only be a number.");
                    }
                    */
                } else {
                    warningLbl.setText("Error adding appointment: please fill out all fields.");
                }
            }
            
            return null;
        });
        
        Optional<Appointment> optionalResult = dialog.showAndWait();
        optionalResult.ifPresent(
                
            (Appointment apt) -> {
                this.selectedPatient.addAppointment(apt);
                updateAppointmentsList();
            }
                
        );
        
    }
    
    private void updateAppointmentsList(){
        this.upcomingTableView.getItems().setAll(this.selectedPatient.getUpcomingAppointments());
    }
    
    private List<String> toSortedList(Stream<String> input){ 
        return input.collect(Collectors.toSet())
                .stream()
                .sorted()
                .collect(Collectors.toList());
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
        
        if(userType.equals("Staff")){
            
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
    
}
