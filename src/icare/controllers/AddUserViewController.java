/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package icare.controllers;

import icare.models.RoleEnum;
import icare.models.Storage;
import icare.models.User;
import icare.models.UserFactory;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author David Ortiz
 */
public class AddUserViewController implements Initializable {

    private Storage storage;
    private User currentUser;
    private UserFactory userFactory;
    //private Patient newPatient;
    private RoleEnum selectedUserType;

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
    private ChoiceBox departmentLbl;

    @FXML
    private ToggleGroup gender;

    @FXML
    private Label warningLbl;

    @FXML
    private ToggleGroup userType;

    @FXML
    private HBox insuranceBox;

    @FXML
    private HBox departmentBox;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    public void initData(Storage storage, User currentUser) {
        this.storage = storage;
        this.currentUser = currentUser;
        this.userFactory = new UserFactory();
        this.warningLbl.setText("");
        this.departmentBox.setVisible(false);

        this.selectedUserType = getRole(((RadioButton) userType.getSelectedToggle()).getText());

        this.departmentLbl.setItems(FXCollections.observableArrayList(
                "Radiology",
                "Nursing",
                "Patient Care",
                "Customer Service",
                "Billing",
                "IT",
                "Emergency")
        );
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

    public void userTypeSelected(ActionEvent event) {

        this.selectedUserType = getRole(((RadioButton) userType.getSelectedToggle()).getText());

        if (this.selectedUserType ==   RoleEnum.Patient) {
            this.insuranceBox.setVisible(true);
            this.departmentBox.setVisible(false);
        }

        if (this.selectedUserType ==   RoleEnum.Staff) {
            this.insuranceBox.setVisible(false);
            this.departmentBox.setVisible(true);
        }

    }

    public void fnameEntered(KeyEvent keyEvent) {
        generateUserID();
    }

    public void lnameEntered(KeyEvent keyEvent) {
        generateUserID();
    }

    private void generateUserID() {

        String lastNameTwoChars = "";
        if (this.fnameLbl.getText().length() >= 2) {
            lastNameTwoChars = this.fnameLbl.getText().substring(0, 2);
        }

        this.userIDLbl.setText((this.lnameLbl.getText() + lastNameTwoChars).toLowerCase());

    }

    public void generatePassword(ActionEvent event) {
        String randomChars = "";
        Random random = new Random();
        int wordSize = 8;

        char[] word = new char[wordSize];
        for (int i = 0; i < word.length; i++) {
            word[i] = (char) ('a' + random.nextInt(26));
        }
        randomChars = new String(word);

        passwordLbl.setText(randomChars);
    }

    public void saveBtnClicked(ActionEvent event) throws IOException {

        String validateResult = validateUserInput();

        if (validateResult.equals("valid")) {
            saveNewUser();
            goToMainMenu(event);
        } else {
            this.warningLbl.setText(validateResult);
        }

    }

    private String validateUserInput() {
        String result = "";

        if (fnameLbl.getText().isEmpty()
                || lnameLbl.getText().isEmpty()
                || dobPicker.getValue() == null
                || passwordLbl.getText().isEmpty()) {
            result = "Please fill out all fields.";
        } else {
            if (!dobPicker.getValue().isBefore(LocalDate.now())) {
                result = "Please select a date in the past.";
            } else {

                if (this.selectedUserType == RoleEnum.Staff) {
                    result = (departmentLbl.getValue() != (null)) ? "valid" : "Please fill out all fields.";
                }

                else if (this.selectedUserType == RoleEnum.Patient) {
                    result = (!insuranceLbl.getText().isEmpty() && insuranceLbl.getText().matches("\\d*"))
                            ? "valid" : "Insurance IDs should only include integers.";
                }

            } // end nested ifelse 
        } // end ifelse

        return result;
    }

    private void saveNewUser() {

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        User newUser = userFactory.createNewUser(
                this.selectedUserType,
                fnameLbl.getText().toLowerCase(),
                lnameLbl.getText().toLowerCase(),
                dateTimeFormatter.format(dobPicker.getValue()),
                passwordLbl.getText(),
                (String) departmentLbl.getValue(),
                Long.parseLong(insuranceLbl.getText()),
                ((RadioButton) gender.getSelectedToggle()).getText());

        this.storage.getUserDao().save(newUser);

    }

    private RoleEnum getRole(String role) {

        //determine role the the user
        if (((RadioButton) userType.getSelectedToggle()).getText().equalsIgnoreCase(RoleEnum.Patient.toString())) {
            return RoleEnum.Patient;
        } else if (((RadioButton) userType.getSelectedToggle()).getText().equalsIgnoreCase(RoleEnum.Staff.toString())) {
            return RoleEnum.Staff;
        } else {
            return RoleEnum.Unknown;
        }
    }

}
