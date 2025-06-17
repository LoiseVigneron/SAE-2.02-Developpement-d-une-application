package IHM;

import java.io.IOException;
import java.util.List;

import SAE.Student;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class FormationBinome extends Application {

        public void start(Stage stage) throws IOException {
                FXMLLoader loader = new FXMLLoader();
                java.net.URL fxmlFileUrl = getClass().getResource("Formation Binôme.fxml");
                if (fxmlFileUrl == null) {
                        System.out.println("Impossible de charger le fichier fxml");
                        System.exit(-1);
                }
                loader.setLocation(fxmlFileUrl);
                Parent root = loader.load();

                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.setTitle("Formation Binôme");
                stage.show();
        }

        public static void main(String[] args) {
                Application.launch(args);
        }

       public static void fenetre(List<Student> hosts, List<Student> guests) throws IOException {
                Stage stage = new Stage();
                FXMLLoader loader = new FXMLLoader();
                java.net.URL fxmlFileUrl = FormationBinome.class.getResource("Formation Binôme.fxml");

                if (fxmlFileUrl == null) {
                        System.out.println("Impossible de charger le fichier fxml");
                }

                loader.setLocation(fxmlFileUrl);
                Parent root = loader.load();

                // Injecte les deux listes dans le contrôleur
                FormationBinomeController controller = loader.getController();
                controller.setLists(hosts, guests);

                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.setTitle("Formation Binôme");
                stage.show();
        }

}
