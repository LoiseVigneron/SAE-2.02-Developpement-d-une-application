package IHM;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalDate;
import java.util.ArrayList;

import SAE.*;

public class NouveauSejourController {

    @FXML
    private ListView<Groupe> listViewHost;

    @FXML
    private ListView<Groupe> listViewGuest;

    @FXML
    private Button validerButton;

    @FXML
    private DatePicker datePicker;

    private Historique nouveauH; 

    /**
     * Méthode appelée par le HomeController pour initialiser la vue
     */
    public void initData(ArrayList<Groupe> groupes) {
        ObservableList<Groupe> observableGroupes = FXCollections.observableArrayList(groupes);

        listViewHost.setItems(observableGroupes);
        listViewGuest.setItems(observableGroupes);
    }

    @FXML
    private void handleValider() {
        Groupe groupeHost = listViewHost.getSelectionModel().getSelectedItem();
        Groupe groupeGuest = listViewGuest.getSelectionModel().getSelectedItem();
        LocalDate date = datePicker.getValue();

        if (groupeHost == null || groupeGuest == null || date == null) {
            System.out.println("Merci de sélectionner un groupe hôte, un groupe invité et une date.");
            return;
        }

        Voyage nouveauSejour = new Voyage(date, groupeHost, groupeGuest);
        nouveauSejour.affectationCalcul();
        nouveauH = Gestion.importHistorique();
        nouveauH.ajouterVoyage(nouveauSejour);
        Gestion.exportHistorique(nouveauH);

        Stage stage = (Stage) validerButton.getScene().getWindow();
        stage.close();
    }
}

