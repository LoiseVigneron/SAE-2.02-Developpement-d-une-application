package IHM;

import java.util.ArrayList;

import SAE.Country;
import SAE.Gestion;
import SAE.Groupe;
import SAE.Student;
import SAE.Voyage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;

public class IncompatibilitesController {

    @FXML
    private ListView<Student> listViewEleves;

    @FXML
    private ListView<Student> listViewIncompatibles;

    private Voyage voyageActuel;

    @FXML
    private Label labelNomSelectionne;


    public void initialize() {
    ArrayList<String> dataImport = Gestion.importData();
    ArrayList<Student> allStudents = Gestion.createStudents(dataImport);

    // Créer tous les groupes
    Groupe france = Gestion.createGroup(allStudents, Country.FRA);
    Groupe allemagne = Gestion.createGroup(allStudents, Country.ALL);
    Groupe espagne = Gestion.createGroup(allStudents, Country.ESP);
    Groupe italie = Gestion.createGroup(allStudents, Country.ITA);

    // Fusionner toutes les listes d'élèves
    ArrayList<Student> tous = new ArrayList<>();
    tous.addAll(france.getStudentsList());
    tous.addAll(allemagne.getStudentsList());
    tous.addAll(espagne.getStudentsList());
    tous.addAll(italie.getStudentsList());

    ObservableList<Student> listeFinale = FXCollections.observableArrayList(tous);
    listViewEleves.setItems(listeFinale);

    // Affichage nom + prénom dans la ListView
    listViewEleves.setCellFactory(lv -> new ListCell<>() {
        @Override
        protected void updateItem(Student student, boolean empty) {
            super.updateItem(student, empty);
            setText(empty || student == null ? null : student.getForename() + " " + student.getName());
        }
    });

    // Mise à jour du Label quand on clique sur un élève
    listViewEleves.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
        if (newVal != null) {
            labelNomSelectionne.setText(newVal.getForename() + " " + newVal.getName());
        }
    });
}

    public void setVoyage(Voyage voyage) {
        this.voyageActuel = voyage;

        ObservableList<Student> tousLesEleves = FXCollections.observableArrayList();
        tousLesEleves.addAll(voyage.getPaysHost().getStudentsList());
        tousLesEleves.addAll(voyage.getPaysGuest().getStudentsList());

        listViewEleves.setItems(tousLesEleves);
    }

    @FXML
    private void handleClickEleve(MouseEvent event) {
    Student selection = listViewEleves.getSelectionModel().getSelectedItem();
    if (selection == null || voyageActuel == null) return;

    // Afficher le nom dans le label
    labelNomSelectionne.setText(selection.getForename() + " " + selection.getName());

    ObservableList<Student> incompatibles = FXCollections.observableArrayList();

    for (Student other : voyageActuel.getPaysHost().getStudentsList()) {
        if (!other.equals(selection)) {
            int score = voyageActuel.criteresCompatibility(selection, other);
            if (score > 20) {
                incompatibles.add(other);
            }
        }
    }

    for (Student other : voyageActuel.getPaysGuest().getStudentsList()) {
        if (!other.equals(selection)) {
            int score = voyageActuel.criteresCompatibility(selection, other);
            if (score > 20) {
                incompatibles.add(other);
            }
        }
    }

    listViewIncompatibles.setItems(incompatibles);
}

}
