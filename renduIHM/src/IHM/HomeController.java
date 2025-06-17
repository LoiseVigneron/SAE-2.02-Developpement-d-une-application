package IHM;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

import SAE.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;



public class HomeController {
    @FXML
    Button choixSejour;
    @FXML
    Button parametre;
    @FXML
    Button incompatibilite;
    @FXML
    Button formerBinome;
    @FXML
    Button creerSejour;
    @FXML
    Button ajouterEleve;

@FXML
 ListView<Affectation> hostListView;

@FXML
 Label hostLabel;

@FXML
 Label guestLabel;
 // On garde la vraie liste des étudiants ici

 @FXML
 Label pointsCommuns;

 @FXML
 Label pointsIncompatibles;

 @FXML
ImageView hostFlagImageView;

@FXML
ImageView guestFlagImageView;

@FXML
private Label dateLabel;

private ArrayList<Student> hostList; 
private ArrayList<Student> guestList; 
private ArrayList<Groupe> groupList = new ArrayList<>();


@FXML
public void initialize() {

    ArrayList<String> tableImport = Gestion.importData(); 
    ArrayList<Student> studentsList= Gestion.createStudents(tableImport); 
    Gestion.displayArrayList(studentsList);
    Groupe france = Gestion.createGroup(studentsList, Country.FRA); 
    Groupe italie = Gestion.createGroup(studentsList, Country.ITA);
    Groupe allemagne = Gestion.createGroup(studentsList, Country.ALL);
    Groupe espagne = Gestion.createGroup(studentsList, Country.ESP);
    groupList.add(france);
    groupList.add(espagne);
    groupList.add(italie);
    groupList.add(allemagne); 

    Voyage fraIta = new Voyage(LocalDate.now(), france, italie);
    fraIta.affectationCalcul();
    Voyage fraAll = new Voyage(LocalDate.of(2015,6,25), france, allemagne);
    fraAll.affectationCalcul();
    Voyage itaEsp = new Voyage(LocalDate.of(2017,9,05), italie, espagne);
    itaEsp.affectationCalcul();
    Voyage allEsp = new Voyage(LocalDate.of(2018,10,04), allemagne, espagne); 
    allEsp.affectationCalcul();

    ObservableList<Affectation> affectations = FXCollections.observableArrayList(fraIta.getAffectations());
    //ObservableList<Affectation> affectations2 = FXCollections.observableArrayList(itaEsp.getAffectations());
    //ObservableList<Affectation> affectations3 = FXCollections.observableArrayList(allEsp.getAffectations());

    hostList = fraIta.getPaysHost().getStudentsList();
    guestList = fraIta.getPaysGuest().getStudentsList(); 

    hostListView.setItems(affectations);

    // Afficher uniquement le nom/prénom du host dans la ListView
    hostListView.setCellFactory(lv -> new ListCell<>() {
        @Override
        protected void updateItem(Affectation item, boolean empty) {
            super.updateItem(item, empty);
            if (empty || item == null) {
                setText(null);
            } else {
                setText(item.getHost().getForename() + " " + item.getHost().getName() + " / " + item.getGuest().getForename() + " " + item.getGuest().getName());
            }
        }
    });

    // Listener sur la sélection dans la ListView
    hostListView.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
        if (newVal != null) {
            hostLabel.setText(newVal.getHost().getForename() + " " + newVal.getHost().getName());
            guestLabel.setText(newVal.getGuest().getForename() + " " + newVal.getGuest().getName());
        }
    });
}

public void setDate(String date) {
System.out.println("🏷 Date reçue dans Home : " + date); // test console
dateLabel.setText("Date : " + date);
}

@FXML
public void accessChoixSejour(MouseEvent event) throws IOException {
    FXMLLoader loader = new FXMLLoader(getClass().getResource("choixSejour.fxml"));
    Parent root = loader.load();

    choixSejourController controller = loader.getController();
    controller.setHomeController(this); // ← Ceci est essentiel

    Stage stage = new Stage();
    stage.setScene(new Scene(root));
    stage.setTitle("Choix du séjour");
    stage.show();
}



private void setFlagImage(Country country, ImageView imageView) {
    try {
        String imagePath = "R2.01-03_Dév/res/img/" + country.toString() + ".png";
        File imageFile = new File(imagePath);
        if (imageFile.exists()) {
            imageView.setImage(new javafx.scene.image.Image(imageFile.toURI().toString()));
        } else {
            System.err.println("Fichier image introuvable : " + imagePath);
        }
    } catch (Exception e) {
        System.err.println("Erreur lors du chargement de l'image pour " + country + " : " + e.getMessage());
    }
}



    @FXML
private void handleListClick(MouseEvent event) {
    Affectation selected = hostListView.getSelectionModel().getSelectedItem();
    if (selected != null) {
        Student host = selected.getHost();
        Student guest = selected.getGuest();

        hostLabel.setText(host.getForename() + " " + host.getName());
        guestLabel.setText(guest.getForename() + " " + guest.getName());

        pointsCommuns.setText(Voyage.getPointsCommuns(host, guest).toString());
        pointsIncompatibles.setText(Voyage.getPointsIncompatibles(host, guest).toString());

        // Mise à jour des drapeaux
        setFlagImage(host.getCountry(), hostFlagImageView);
        setFlagImage(guest.getCountry(), guestFlagImageView);
    }
}


    public void accessChoixSejourf(MouseEvent event) throws IOException {
        ChoixSejour.fenetre(this);
    }
    public void accessParametre(MouseEvent event) throws IOException {
        Parametre.fenetre();
    }
    public void accessIncompatibilite(MouseEvent event) throws IOException {
        System.out.println("Méthode incompatibilité appelée");
        Incompatibilites.fenetre();
    }
    public void accessFormerBinome(MouseEvent event) throws IOException {
        FormationBinome.fenetre(hostList, guestList);
    }
    public void accessCreerSejour(MouseEvent event) throws IOException {
        NouveauSejour.fenetre(groupList);
    }
    public void accessAjouterEleve(MouseEvent event) throws IOException {
        AjouterEleve.fenetre();
    }

}

