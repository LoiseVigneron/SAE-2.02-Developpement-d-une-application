package IHM;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class AjouterEleveController {

    @FXML private TextField nameField;
    @FXML private TextField forenameField;
    @FXML private DatePicker birthDatePicker;
    @FXML private TextField nationalityField;
    @FXML private TextField hobbyField;

    @FXML private CheckBox checkMale;
    @FXML private CheckBox checkFemale;
    @FXML private CheckBox checkOther;

    @FXML private CheckBox checkAllergyAnimal;
    @FXML private CheckBox checkPet;
    @FXML private CheckBox checkVegetarian;
    @FXML private CheckBox checkNut;

    @FXML private CheckBox preferredMale;
    @FXML private CheckBox preferredFemale;
    @FXML private CheckBox preferredOther;

    @FXML private Button annuler; 

    // Le chemin vers le fichier import.csv, adapte-le à ta config
    private static final String PATH = System.getProperty("user.dir") + "/R2.01-03_Dév/res/import.csv";

    @FXML
    private void handleValider() {
        String name = nameField.getText().trim();
        String forename = forenameField.getText().trim();
        LocalDate birthDate = birthDatePicker.getValue();
        String nationality = nationalityField.getText().trim().toUpperCase();  // Exemple: FRANCE, ITALY, ...
        String hobbies = hobbyField.getText().trim();

        String gender = checkMale.isSelected() ? "M" :
                        checkFemale.isSelected() ? "F" :
                        checkOther.isSelected() ? "O" : "";

        String allergyAnimal = Boolean.toString(checkAllergyAnimal.isSelected());
        String hasPet = Boolean.toString(checkPet.isSelected());
        String vegetarian = checkVegetarian.isSelected() ? "vegetarian" : "normal";
        String nutAllergy = checkNut.isSelected() ? "no nuts" : "normal";

        String preferredGender = preferredMale.isSelected() ? "M" :
                                 preferredFemale.isSelected() ? "F" :
                                 preferredOther.isSelected() ? "O" : "";

        String history = "false"; // Par défaut (tu peux modifier si tu as ce champ)

        // Formatage de la date en dd/MM/yyyy
        String birthDateStr = birthDate != null ? birthDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) : "";

        // Construction de la ligne CSV (ordre selon ton fichier)
        // FORENAME;NAME;COUNTRY;BIRTH_DATE;GUEST_ANIMAL_ALLERGY;HOST_HAS_ANIMAL;GUEST_FOOD_CONSTRAINT;HOST_FOOD;HOBBIES;GENDER;PAIR_GENDER;HISTORY
        String ligne = String.join(";",
            forename,
            name,
            nationality,
            birthDateStr,
            allergyAnimal,
            hasPet,
            nutAllergy,
            vegetarian,
            hobbies,
            gender,
            preferredGender,
            history
        );

        // Ajout à la fin du fichier sans supprimer les données existantes
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(PATH, true))) {  // true = append mode
            writer.newLine(); // Nouvelle ligne avant d’écrire
            writer.write(ligne);
            System.out.println("Nouvel élève ajouté : " + ligne);
        } catch (IOException e) {
            e.printStackTrace();
        }
        annuler.getScene().getWindow().hide();
    }


    @FXML
    private void handleAnnuler(){
        annuler.getScene().getWindow().hide(); 
    }
}
