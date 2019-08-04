package icare.controllers;

import icare.models.Immunization;
import icare.models.Patient;
import icare.models.RoleEnum;
import icare.models.Storage;
import icare.models.User;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;
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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/**
 * FXML Immunization Controller class
 *
 * @author Jake Benedick, David Ortiz
 */
public class ImmunizationController implements Initializable {

    private Storage storage;
    private User currentUser;
    private Patient selectedPatient;
    private String firstName;
    private RoleEnum userType;
    private Immunization selectedImmunization;
    protected ListProperty<String> listProperty = new SimpleListProperty<>();
    
    @FXML
    private Pane warningsPane;
    
    @FXML
    private TableView<Immunization> immunizationTable;
    
    @FXML
    private TableColumn immunizationName;
    
    @FXML
    private TableColumn date;
    
    @FXML
    private TableColumn follow;
    
    @FXML
    private Button addButton;
    
    @FXML
    private Button deleteButton;
    
    @FXML
    private Button createButton;
    
    @FXML
    private Button cancelButton;
    
    @FXML
    private Button backButton;
    
    @FXML
    private Label userNameLbl;
    
    @FXML
    private Label immunizationNameLabel;
    
    @FXML
    private Label dateLabel;
        
    @FXML
    private TextField immunizationNameField;
    
    @FXML
    private Rectangle background;
    
    @FXML
    private CheckBox followUpCheckbox;
    
    @FXML
    private DatePicker datePicker;
    
    @FXML
    private Label warningLbl;
    
    @FXML
    private ListView warningsList;
    
    @FXML
    private Button schedAppBtn;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        immunizationName.setCellValueFactory(new PropertyValueFactory<Immunization, String>("immunization"));
        date.setCellValueFactory(new PropertyValueFactory<Immunization, Date>("dateAdministered"));
        follow.setCellValueFactory(new PropertyValueFactory<Immunization, String>("isFollowUpRequired"));
        
        this.warningsList.itemsProperty().bind(listProperty);
        
    }    
    
    public void initData(Storage storage, User currentUser, Patient selectedPatient){
        this.storage = storage;
        this.currentUser = currentUser;
        this.selectedPatient = selectedPatient;
        this.deleteButton.setDisable(true);
        
        this.userType = currentUser.getRoleType();
        
        if(this.userType == RoleEnum.Patient){
            this.deleteButton.setVisible(false);
            this.addButton.setVisible(false);
        } else {
            this.schedAppBtn.setVisible(false);
        }
        
        warningsList.setPlaceholder(new Label("All caught up!"));
        firstName = this.selectedPatient.getFirstName().substring(0, 1).toUpperCase() + this.selectedPatient.getFirstName().substring(1);

        userNameLbl.setText(firstName+"'s Immunizations");
        
        immunizationTable.getItems().setAll(selectedPatient.getImmunizations());
        
        updateWarningsList();
        
    }
    
    private void updateWarningsList(){
        listProperty.set(FXCollections.observableArrayList(getMissingVaccines()));
    }
    
    private ArrayList<String> getMissingVaccines(){
        ArrayList<String> list = new ArrayList<>();
        
        for(String s : this.storage.getRequiredVaccinesDao().getAll()){
            if( !this.selectedPatient.getImmunizationNames().contains(s.toLowerCase()) ){
                list.add(s);
            }
        }
   
        return list;
    }
    
    public void backBtnClicked(ActionEvent event) throws IOException{
        FXMLLoader loader = new FXMLLoader();
        Scene scene = null;
        
        if(this.userType == RoleEnum.Staff){
            loader.setLocation(getClass().getResource("/icare/views/ViewPatients.fxml"));
            Parent root = loader.load();
        
            scene = new Scene(root);

            //access the controller and call a method
            ViewPatientsController controller = loader.getController();        
            controller.initData(this.storage, this.currentUser);
        } else if(userType == RoleEnum.Patient){
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
    
    public void schedAppClicked(ActionEvent event) throws IOException{
        
        
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
    
    public void deleteButtonPressed(ActionEvent event) throws IOException{
        selectedImmunization = immunizationTable.getSelectionModel().getSelectedItem();
        
        Alert alert = new Alert(AlertType.CONFIRMATION, "Are you sure you want to delete " + selectedImmunization.getImmunization() + "?", ButtonType.YES, ButtonType.NO);
        alert.setHeaderText("Confirm deletion");
        alert.setGraphic(null);
        alert.showAndWait();

        if (alert.getResult() == ButtonType.YES) {
            this.selectedPatient.removeImmunization(selectedImmunization);
            immunizationTable.getItems().setAll(selectedPatient.getImmunizations());
            storage.getUserDao().writeUserListFile();
        }
        
    }
    
    public void addButtonPressed(ActionEvent event) throws IOException{
        immunizationNameField.setText("");
        datePicker.setValue(null);
        backButton.setVisible(false);
        addButton.setVisible(false);
        deleteButton.setVisible(false);
        userNameLbl.setText("Add Immunization Record");
        immunizationTable.setVisible(false);
        warningsPane.setVisible(false);
    }
    
    public void cancelButtonPressed(ActionEvent event) throws IOException{
        refresh();
    }
    
    public void userClickedTableView(){
        
        if(this.userType == RoleEnum.Staff){
            
            try{
                this.selectedImmunization = (Immunization)immunizationTable.getSelectionModel().getSelectedItem();

                if(selectedImmunization != null){
                    this.deleteButton.setDisable(false);
                }

            } catch(NullPointerException e){
                System.out.println("No immunization selected!");
            }
        }
        
    }
    
    public void createButtonPressed(ActionEvent event) throws IOException{
        boolean followUp = false;
        
        if(immunizationNameField.getText() != null 
                && !immunizationNameField.getText().equals("")
                && datePicker.getValue() != null)
        {
            
            if(followUpCheckbox.isSelected()){
                followUp = true;
            }
            
            warningLbl.setText("");
            selectedPatient.addImmunization(new Immunization(immunizationNameField.getText(), datePicker.getValue(), followUp));
            updateWarningsList();
            storage.getUserDao().writeUserListFile();
            
            refresh();
        } else {
            warningLbl.setText("Please fill out all fields.");
        }
        
    }
    
    public void refresh(){
        userNameLbl.setText(firstName+"'s Immunizations");
        backButton.setVisible(true);
        addButton.setVisible(true);
        deleteButton.setVisible(true);
        warningsPane.setVisible(true);
        immunizationTable.setVisible(true);
        immunizationTable.getItems().setAll(selectedPatient.getImmunizations());
    }

}
