package icare.controllers;

import icare.models.Patient;
import icare.models.Storage;
import icare.models.User;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 *
 * @author d.mikhaylov
 */
public class ViewPatientsController implements Initializable {

    private Storage storage;
    private User currentUser;
    private List<User> users;

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

        firstNameCol.setCellValueFactory(new PropertyValueFactory<Patient, String>("firstName"));
        lastNameCol.setCellValueFactory(new PropertyValueFactory<Patient, String>("lastName"));
        dobCol.setCellValueFactory(new PropertyValueFactory<Patient, String>("dob"));
        genderCol.setCellValueFactory(new PropertyValueFactory<Patient, String>("gender"));
        lastVisitCol.setCellValueFactory(new PropertyValueFactory<Patient, String>("lastVisit"));
        nextVisitCol.setCellValueFactory(new PropertyValueFactory<Patient, String>("nextVisit"));

    }

    public void initData(Storage storage, User currentUser) {
        this.storage = storage;
        this.currentUser = currentUser;
        this.users = storage.getUserList();

//        List<User> users = storage.getUserList();
//        for(User u: users){
//            System.out.println("User name " + u.getFirstName() + " " + u.getLastName());
//        }
        tableView.getItems().setAll(users);

        byFnameCB.getItems().setAll(users.stream()
                .map(User::getFirstName)
                .sorted()
                .collect(Collectors.toSet()));
        byLnameCB.getItems().setAll(users.stream()
                .map(User::getLastName)
                .sorted()
                .collect(Collectors.toSet()));
        byDobCB.getItems().setAll(users.stream()
                .map(User::getDob)
                .sorted()
                .collect(Collectors.toSet()));
        byGenderCB.getItems().setAll(users.stream()
                .map(User::getGender)
                .sorted()
                .collect(Collectors.toSet()));
        byLastVisitCB.getItems().setAll(users.stream()
                .map(User::getLastVisit)
                .sorted()
                .collect(Collectors.toSet()));
        byNextVisitCB.getItems().setAll(users.stream()
                .map(User::getNextVisit)
                .sorted()
                .collect(Collectors.toSet()));

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

        //TODO search patients
    }

    public void applyFilter(ActionEvent event) {

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

}
