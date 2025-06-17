package IHM;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import IHM.HomeController;
import SAE.*;

public class choixSejourController implements Initializable {
    @FXML
    private DatePicker datePicker;

    @FXML
    private Button validerButton;

    @FXML
    private Button annulerButton;

    private HomeController homeController; // Référence au contrôleur principal

    @FXML
    private ListView<Voyage> liste; 

    private Historique historique; 

// Setter appelé par HomeController pour transmettre sa propre instance
public void setHomeController(HomeController controller) {
    this.homeController = controller;
}

@Override
public void initialize(URL location, ResourceBundle resources) {
    afficherSejour();
}

@FXML
    private void handleValider(ActionEvent event) {
        if (datePicker.getValue() != null && homeController != null) {
            String dateStr = datePicker.getValue().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            System.out.println("→ Date choisie dans ChoixSejour : " + dateStr);
            homeController.setDate(dateStr);
        } else {
            System.out.println("homeController ou datePicker est null");
        }
        System.out.println("→ homeController = " + homeController);

        // Fermer la fenêtre
        Stage stage = (Stage) validerButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void afficherSejour(){
        historique=Gestion.importHistorique(); 
        ArrayList<Voyage> voyages =historique.getAllVoyages(); 
        liste.setItems(FXCollections.observableArrayList(voyages));

    }

    @FXML
    private void handleAnnuler(ActionEvent event) {
        ((Stage) annulerButton.getScene().getWindow()).close();
    }
}