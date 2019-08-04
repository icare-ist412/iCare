package icare.controllers;

import icare.models.Patient;
import icare.models.Storage;
import icare.models.User;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 *
 * @author d.mikhaylov
 */
public class ViewPatientsController implements Initializable {

    private Storage storage;
    private User currentUser;
    private List<Patient> users;
    private Patient patientSelectedFromTable;

    @FXML
    private TextField searchField;

    @FXML
    private ComboBox byFnameCB;
    @FXML
    private ComboBox byLnameCB;
    @FXML
    private ComboBox byDobCB;
    @FXML
    private ComboBox byGenderCB;
    @FXML
    private ComboBox byLastVisitCB;
    @FXML
    private ComboBox byNextVisitCB;
    
    @FXML
    private Button viewMedicalBtn;
    @FXML
    private Button immunizationsBtn;
    @FXML
    private Button appointmentsBtn;
    
    @FXML
    private TableView tableView;
    @FXML
    private TableColumn<Patient, String> firstNameCol;
    @FXML
    private TableColumn<Patient, String> lastNameCol;
    @FXML
    private TableColumn<Patient, String> dobCol;
    @FXML
    private TableColumn<Patient, String> genderCol;
    @FXML
    private TableColumn<Patient, String> lastVisitCol;
    @FXML
    private TableColumn<Patient, String> nextVisitCol;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        viewMedicalBtn.setDisable(true);
        immunizationsBtn.setDisable(true);
        appointmentsBtn.setDisable(true);
        
        firstNameCol.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        lastNameCol.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        dobCol.setCellValueFactory(new PropertyValueFactory<>("dob"));
        genderCol.setCellValueFactory(new PropertyValueFactory<>("gender"));
        lastVisitCol.setCellValueFactory(new PropertyValueFactory<>("lastVisit"));
        nextVisitCol.setCellValueFactory(new PropertyValueFactory<>("nextVisit"));

    }

    public void initData(Storage storage, User currentUser) {
        this.storage = storage;
        this.currentUser = currentUser;
        this.users = storage.getUserDao().getAllPatients();
        
        this.storage.getUserDao().updateVisits();
        
        Stream<List> reset = Stream.of(Arrays.asList("(reset)"));

        tableView.getItems().setAll(users);
        byFnameCB.getItems().setAll(toSortedList(users.stream()
                .map(Patient::getFirstName)));
        byLnameCB.getItems().setAll(toSortedList(users.stream()
                .map(Patient::getLastName)));
        byDobCB.getItems().setAll(toSortedList(users.stream()
                .map(Patient::getDob)));
        byGenderCB.getItems().setAll(toSortedList(users.stream()
                .map(Patient::getGender)));
        byLastVisitCB.getItems().setAll(toSortedList(users.stream()
                .map(Patient::getLastVisit)));
        byNextVisitCB.getItems().setAll(toSortedList(users.stream()
                .map(Patient::getNextVisit)));

    }

    private List<String> toSortedList(Stream<String> input){ 
        return input.collect(Collectors.toSet())
                .stream()
                .sorted()
                .collect(Collectors.toList());
    }
    
    public void resetBtnClicked(ActionEvent event){
        selectFirstInChoiceBox(byFnameCB);
        selectFirstInChoiceBox(byLnameCB);
        selectFirstInChoiceBox(byDobCB);
        selectFirstInChoiceBox(byGenderCB);
        selectFirstInChoiceBox(byLastVisitCB);
        selectFirstInChoiceBox(byNextVisitCB);
        selectFirstInChoiceBox(byFnameCB);
        
        tableView.getItems().setAll(users);
    }
    
    public void selectFirstInChoiceBox(ComboBox cb){
        cb.valueProperty().set(null);
    }
    
    public void goToMainMenu(ActionEvent event) throws IOException {

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

    public void performSearch(ActionEvent event) {
        String s = searchField.getText().toLowerCase();
        
        if (s.isEmpty()){
            byFnameCB.setValue(null);
            byLnameCB.setValue(null);
            byDobCB.setValue(null);
            byGenderCB.setValue(null);
            byLastVisitCB.setValue(null);
            byNextVisitCB.setValue(null);
        }
        
        if (s != null) {
            tableView.getItems().setAll(users
                    .stream()
                    .filter(Objects::nonNull)
                    .filter(u -> ((u.getFullName().contains(s))
                    || (u.getDob().toLowerCase().contains(s))
                    || (u.getGender().toLowerCase().contains(s))
                    || (u.getLastVisit().toLowerCase().contains(s))
                    || (u.getNextVisit().toLowerCase().contains(s))))
                    .collect(Collectors.toList())
            );
        }
    }

    public void applyFilter(ActionEvent event) {

        if (byFnameCB.getValue() != null && byFnameCB.getValue().equals("(reset)")) {
            byFnameCB.setValue(null);
        }

        tableView.getItems().setAll(users
                .stream()
                .filter(Objects::nonNull)
                .filter(u -> (byFnameCB.getValue() == null || u.getFirstName().equalsIgnoreCase(byFnameCB.getValue().toString()))
                && (byLnameCB.getValue() == null || u.getLastName().equalsIgnoreCase(byLnameCB.getValue().toString()))
                && (byDobCB.getValue() == null || u.getDob().equalsIgnoreCase(byDobCB.getValue().toString()))
                && (byGenderCB.getValue() == null || u.getGender().equalsIgnoreCase(byGenderCB.getValue().toString()))
                && (byLastVisitCB.getValue() == null || u.getLastVisit().equalsIgnoreCase(byLastVisitCB.getValue().toString()))
                && (byNextVisitCB.getValue() == null || u.getNextVisit().equalsIgnoreCase(byNextVisitCB.getValue().toString())))
                .collect(Collectors.toList()));

    }
        
    public void immunizationsBtnClicked(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/icare/views/ImmunizationView.fxml"));
        Parent root = loader.load();
        
        Scene scene = new Scene(root);

        ImmunizationController controller = loader.getController();        
        controller.initData(this.storage, this.currentUser, this.patientSelectedFromTable);
        
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(scene);
        window.show();
    }
    
    public void medicalBtnClicked(ActionEvent event) throws IOException {
        
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/icare/views/ViewEditView.fxml"));
        Parent root = loader.load();
        
        Scene scene = new Scene(root);

        ViewEditController controller = loader.getController();        
        controller.initData(this.currentUser, this.patientSelectedFromTable, this.storage);
        
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(scene);
        window.show();
        
    }
    
    public void appointmentsBtnClicked(ActionEvent event) throws IOException {
        
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/icare/views/AppointmentsView.fxml"));
        Parent root = loader.load();
        
        Scene scene = new Scene(root);

        AppointmentsViewController controller = loader.getController();        
        controller.initData(this.storage, this.currentUser, this.patientSelectedFromTable);
        
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(scene);
        window.show();
        
    }
    
    public void userClickedTable(){
        viewMedicalBtn.setDisable(false); 
        appointmentsBtn.setDisable(false); 
        immunizationsBtn.setDisable(false);
        
        try{
            this.patientSelectedFromTable = (Patient)tableView.getSelectionModel().getSelectedItem();
            
        } catch(NullPointerException e){
            System.out.println("No patient selected!");
        }
        
    }

}
