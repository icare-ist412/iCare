package icare.controllers;

import icare.models.Patient;
import icare.models.Storage;
import icare.models.User;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
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
    private List<User> users;

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
        this.users = storage.getUserList();
        Stream<List> reset = Stream.of(Arrays.asList("(reset)"));

//        List<User> users = storage.getUserList();
//        for(User u: users){
//            System.out.println("User name " + u.getFirstName() + " " + u.getLastName());
//        }
        tableView.getItems().setAll(users);

        byFnameCB.getItems().setAll(users.stream()
                .map(User::getFirstName)
                .collect(Collectors.toSet())
                .stream()
                .sorted()
                .collect(Collectors.toList())
        );

        byLnameCB.getItems().setAll(users.stream()
                .map(User::getLastName)
                .collect(Collectors.toSet())
                .stream()
                .sorted()
                .collect(Collectors.toList())
        );
        byDobCB.getItems().setAll(users.stream()
                .map(User::getDob)
                .collect(Collectors.toSet())
                .stream()
                .sorted()
                .collect(Collectors.toList())
        );
        byGenderCB.getItems().setAll(users.stream()
                .map(User::getGender)
                .collect(Collectors.toSet())
                .stream()
                .sorted()
                .collect(Collectors.toList())
        );
        byLastVisitCB.getItems().setAll(users.stream()
                .map(User::getLastVisit)
                .collect(Collectors.toSet())
                .stream()
                .sorted()
                .collect(Collectors.toList())
        );
        byNextVisitCB.getItems().setAll(users.stream()
                .map(User::getNextVisit)
                .collect(Collectors.toSet())
                .stream()
                .sorted()
                .collect(Collectors.toList()));

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
        String s = searchField.getText();
        
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
                    || (u.getDob().contains(s))
                    || (u.getGender().contains(s))
                    || (u.getLastVisit().contains(s))
                    || (u.getNextVisit().contains(s))))
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

}
