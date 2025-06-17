package IHM;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import SAE.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;

public class FormationBinomeController {

    @FXML
    private ListView<Student> hostListView;

    @FXML
    private ListView<Student> guestListView;

    @FXML
    private Label hostInfoLabel;

    @FXML
    private Label guestInfoLabel;

    @FXML
    private Label pointsCommunsLabel;

    @FXML
    private Label pointsIncompatiblesLabel;


    private Student selectedHost;
    private Student selectedGuest;

    @FXML
    private Button validateButton;

    @FXML
    private Button cancelButton;

    @FXML
    public void initialize() {
    validateButton.setOnAction(event -> handleValidate());
    cancelButton.setOnAction(event -> handleCancel());
}



    public void setLists(List<Student> hosts, List<Student> guests) {
        ObservableList<Student> hostList = FXCollections.observableArrayList(hosts);
        ObservableList<Student> guestList = FXCollections.observableArrayList(guests);

        hostListView.setItems(hostList);
        guestListView.setItems(guestList);

        hostListView.setCellFactory(e -> new ListCell<>() {
            @Override
            protected void updateItem(Student student, boolean empty) {
                super.updateItem(student, empty);
                setText((student == null || empty) ? null : student.getForename() + " " + student.getName());
            }
        });

        guestListView.setCellFactory(e -> new ListCell<>() {
            @Override
            protected void updateItem(Student student, boolean empty) {
                super.updateItem(student, empty);
                setText((student == null || empty) ? null : student.getForename() + " " + student.getName());
            }
        });
    }

    @FXML
    private void handleHostClick(MouseEvent event) {
        selectedHost = hostListView.getSelectionModel().getSelectedItem();
        if (selectedHost != null) {
            hostInfoLabel.setText(selectedHost.getName() + " " + selectedHost.getForename());
            updateLabelsIfReady();
        }
    }

    @FXML
    private void handleGuestClick(MouseEvent event) {
        selectedGuest = guestListView.getSelectionModel().getSelectedItem();
        if (selectedGuest != null) {
            guestInfoLabel.setText(selectedGuest.getName() + " " + selectedGuest.getForename());
            updateLabelsIfReady();
        }
    }


private void updateLabelsIfReady() {
    if (selectedHost != null && selectedGuest != null) {
        var communs = Voyage.getPointsCommuns(selectedHost, selectedGuest);
        var incompatibles = Voyage.getPointsIncompatibles(selectedHost, selectedGuest);
        pointsCommunsLabel.setText(String.join(", ", communs));
        pointsIncompatiblesLabel.setText(String.join(", ", incompatibles));
        }
    }

    @FXML
    private void handleValidate() {
    Student host = hostListView.getSelectionModel().getSelectedItem();
    Student guest = guestListView.getSelectionModel().getSelectedItem();

    if (host != null && guest != null) {
        try {
            File exportFile = new File("R2.01-03_Dév/res/export.csv");
            FileWriter fw = new FileWriter(exportFile, true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(host.getName() + " " + host.getForename() + ";" + guest.getName() + " " + guest.getForename() + ";\n");
            bw.close();
            fw.close();
            System.out.println("Ajout effectué dans export.csv !");
        } catch (IOException e) {
            System.err.println("Erreur lors de l'écriture dans le fichier export.csv : " + e.getMessage());
        }
    } else {
        System.out.println("Aucun binôme sélectionné.");
    }
    validateButton.getScene().getWindow().hide();
}


    @FXML
    private void handleCancel() {
        cancelButton.getScene().getWindow().hide();
    }


}
