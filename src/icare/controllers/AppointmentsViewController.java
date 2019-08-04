/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package icare.controllers;

import icare.models.Appointment;
import icare.models.Hospital;
import icare.models.Patient;
import icare.models.RoleEnum;
import icare.models.User;
import icare.models.Storage;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
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
    private RoleEnum userType;
    private Appointment selectedAppointment;

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
    private TableView requestedAppTable;

    @FXML
    private Button cancelBtn;

    @FXML
    private Button saveBtn;

    @FXML
    private Button approveBtn;

    @FXML
    private Button staffCancelAppBtn;

    @FXML
    private Button denyBtn;

    @FXML
    private TableColumn<Appointment, String> reqDateCol;

    @FXML
    private TableColumn<Appointment, String> reqPlaceCol;

    @FXML
    private TableColumn<Appointment, String> reqTimeCol;

    @FXML
    private TableColumn<Appointment, String> reqReasonCol;

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
    private Pane staffApproveDenyPane;

    @FXML
    private Label warningLbl;

    @FXML
    private Label hospitalLbl;
    @FXML
    private Label addressLbl;
    @FXML
    private Label phoneLbl;
    @FXML
    private Label dateTimeLbl;
    @FXML
    private Label reasonLbl;

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

        reqDateCol.setCellValueFactory(new PropertyValueFactory<>("day"));
        reqTimeCol.setCellValueFactory(new PropertyValueFactory<>("time"));
        reqPlaceCol.setCellValueFactory(new PropertyValueFactory<>("location"));
        reqReasonCol.setCellValueFactory(new PropertyValueFactory<>("reason"));

        this.staffPane.setVisible(false);
        this.patientPane.setVisible(false);
        this.staffApproveDenyPane.setVisible(false);
        this.approveBtn.setDisable(true);
        this.denyBtn.setDisable(true);
        this.staffCancelAppBtn.setDisable(true);
        this.staffCancelAppBtn.setVisible(true);
    }

    public void initData(Storage storage, User currentUser, Patient selectedPatient) {
        this.storage = storage;
        this.currentUser = currentUser;
        this.selectedPatient = selectedPatient;

        this.userType = currentUser.getRoleType();

        if (this.userType == RoleEnum.Staff) {
            this.staffPane.setVisible(true);
            this.staffApproveDenyPane.setVisible(true);
            this.staffCancelAppBtn.setVisible(true);

        }
        if (this.userType == RoleEnum.Patient) {
            this.patientPane.setVisible(true);
            this.cancelBtn.setVisible(false);
            this.saveBtn.setText("Return");
        }

        String fname = this.selectedPatient.getFirstName().substring(0, 1).toUpperCase() + this.selectedPatient.getFirstName().substring(1);
        nameLbl.setText(fname + "'s Appointments");

        upcomingTableView.setPlaceholder(new Label("No upcoming appointments"));
        pastTable.setPlaceholder(new Label("No past appointments"));
        requestedAppTable.setPlaceholder(new Label("No appointments requested"));

        this.selectedPatient.refreshAppointments();

        if (this.selectedPatient.getUpcomingAppointments() != null) {
            this.upcomingTableView.getItems().setAll(this.selectedPatient.getUpcomingAppointments());
        }

        if (this.selectedPatient.getPastAppointments() != null) {
            this.pastTable.getItems().setAll(this.selectedPatient.getPastAppointments());
        }

        if (this.selectedPatient.getRequestedAppointments() != null) {
            this.requestedAppTable.getItems().setAll(this.selectedPatient.getRequestedAppointments());
        }
    }

    public void newAppointmentClicked(ActionEvent event) {
        triggerNewAppointmentDialog("");
    }

    public void cancelAppClicked(ActionEvent event) {
        this.selectedPatient.removeAppointment(this.selectedAppointment);
        this.updateAppointmentsList();
    }

    public void triggerNewAppointmentDialog(String type) {

        warningLbl.setText("");

        Dialog<Appointment> dialog = new Dialog<>();
        dialog.setTitle("New Appointment");
        dialog.setHeaderText("Please specify…");
        DialogPane dialogPane = dialog.getDialogPane();
        dialogPane.getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

        DatePicker datePicker = new DatePicker();

        ChoiceBox hospitalDropdown = new ChoiceBox();
        hospitalDropdown.getItems().setAll(toSortedList(this.storage.getHospitalDao().getAll().stream().map(Hospital::getName)));

        ChoiceBox hourDropdown = new ChoiceBox<>(FXCollections.observableList(this.generateTimes("hours")));
        ChoiceBox minuteDropdown = new ChoiceBox<>(FXCollections.observableList(this.generateTimes("mins")));

        TextField reasonField = new TextField();

        reasonField.setPromptText("Reason");

        dialogPane.setContent(
                new VBox(8,
                        new VBox(new Label("Select day"), datePicker),
                        new VBox(new Label("Select time"),
                                new HBox(hourDropdown, minuteDropdown)),
                        new VBox(new Label("Select location"), hospitalDropdown),
                        new VBox(new Label("Reason for visit"), reasonField)
                )
        );

        Platform.runLater(datePicker::requestFocus);

        dialog.setResultConverter((ButtonType button) -> {

            if (button == ButtonType.OK) {

                if (datePicker.getValue() != null
                        && hospitalDropdown.getValue() != null
                        && !reasonField.getText().equals("")
                        && hourDropdown.getValue() != null
                        && minuteDropdown.getValue() != null) {

                    String time = datePicker.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) + " " + hourDropdown.getValue() + ":" + minuteDropdown.getValue();
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                    LocalDateTime dateTime = LocalDateTime.parse(time, formatter);

                    if (dateTime.isAfter(LocalDateTime.now())) {

                        warningLbl.setText("");
                        return new Appointment(dateTime, storage.getHospitalDao().findById(hospitalDropdown.getValue().toString()), this.selectedPatient, reasonField.getText());
                    } else {
                        warningLbl.setText("Error adding appointment: please enter a future date.");
                    }

                } else {
                    warningLbl.setText("Error adding appointment: please fill out all fields.");
                }
            }

            return null;
        });

        Optional<Appointment> optionalResult = dialog.showAndWait();

        optionalResult.ifPresent(
                (Appointment apt) -> {
                    if (type.equals("request")) {
                        this.selectedPatient.addRequestedAppointment(apt);
                        updateRequestedAppointmentsList();

                    } else {
                        this.selectedPatient.addAppointment(apt);
                        updateAppointmentsList();
                    }
                }
        );
    }

    private void parseAppointmentDetails() {
        hospitalLbl.setText(this.selectedAppointment.getHospital().getName());
        addressLbl.setText(this.selectedAppointment.getHospital().getAddress().toString());
        phoneLbl.setText(this.selectedAppointment.getHospital().getPhone());
        dateTimeLbl.setText(this.selectedAppointment.getDayTime());
        reasonLbl.setText("Reason: " + this.selectedAppointment.getReason());
    }

    public void pastAppointmentTableClicked() {

        try {
            this.selectedAppointment = (Appointment) this.pastTable.getSelectionModel().getSelectedItem();
            if (this.selectedAppointment != null) {
                this.approveBtn.setDisable(true);
                this.denyBtn.setDisable(true);
                this.staffCancelAppBtn.setDisable(true);
                parseAppointmentDetails();
            }
        } catch (NullPointerException e) {
            System.out.println("No appointment selected!");
        }

    }

    public void upcomingAppointmentTableClicked() {

        try {
            this.selectedAppointment = (Appointment) this.upcomingTableView.getSelectionModel().getSelectedItem();
            if (this.selectedAppointment != null) {
                this.approveBtn.setDisable(true);
                this.denyBtn.setDisable(true);
                this.staffCancelAppBtn.setDisable(false);
                parseAppointmentDetails();
            }
        } catch (NullPointerException e) {
            System.out.println("No appointment selected!");
        }

    }

    public void reqAppointmentTableClicked() {

        try {
            this.selectedAppointment = (Appointment) this.requestedAppTable.getSelectionModel().getSelectedItem();
            if (this.selectedAppointment != null) {
                this.approveBtn.setDisable(false);
                this.denyBtn.setDisable(false);
                this.staffCancelAppBtn.setDisable(true);
                parseAppointmentDetails();
            }
        } catch (NullPointerException e) {
            System.out.println("No appointment selected!");
        }

    }

    private ArrayList<String> generateTimes(String type) {
        ArrayList<String> temp = new ArrayList();
        if (type.equals("hours")) {
            for (int i = 1; i <= 23; i++) {
                temp.add(String.format("%02d", i));
            }
        }
        if (type.equals("mins")) {
            for (int i = 0; i < 60; i += 5) {
                temp.add(String.format("%02d", i));
            }
        }
        if (type.equals("time")) {
            temp.add("AM");
            temp.add("PM");
        }
        return temp;
    }

    private void updateAppointmentsList() {
        this.upcomingTableView.getItems().setAll(this.selectedPatient.getUpcomingAppointments());
    }

    private void updateRequestedAppointmentsList() {
        this.requestedAppTable.getItems().setAll(this.selectedPatient.getRequestedAppointments());
    }

    private List<String> toSortedList(Stream<String> input) {
        return input.collect(Collectors.toSet())
                .stream()
                .sorted()
                .collect(Collectors.toList());
    }

    public void requestAppClicked(ActionEvent event) {
        triggerNewAppointmentDialog("request");
    }

    public void denyBtnClicked(ActionEvent event) {

        this.selectedPatient.denyRequestedAppointment(this.selectedAppointment);
        this.updateRequestedAppointmentsList();

    }

    public void approveBtnClicked(ActionEvent event) {

        this.selectedPatient.approveRequestedAppointment(this.selectedAppointment);
        this.updateRequestedAppointmentsList();
        this.updateAppointmentsList();

    }

    public void saveButtonClicked(ActionEvent event) throws IOException {
        storage.getUserDao().writeUserListFile();
        returnToViewPatients(event);

    }

    public void cancelBtnClicked(ActionEvent event) throws IOException {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to cancel? No changes will be saved.", ButtonType.YES, ButtonType.NO);
        alert.setHeaderText("Confirm cancel");
        alert.setGraphic(null);
        alert.showAndWait();

        if (alert.getResult() == ButtonType.YES) {
            returnToViewPatients(event);
        }

    }

    public void returnToViewPatients(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader();
        Scene scene = null;

        if (userType.equals("Staff")) {

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
