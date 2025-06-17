package IHM;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Incompatibilites extends Application {

        public void start(Stage stage) throws IOException {
                FXMLLoader loader = new FXMLLoader();
                java.net.URL fxmlFileUrl = getClass().getResource("incompatibilites.fxml");
                if (fxmlFileUrl == null) {
                        System.out.println("Impossible de charger le fichier fxml");
                        System.exit(-1);
                }
                loader.setLocation(fxmlFileUrl);
                Parent root = loader.load();

                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.setTitle("Incompatibilités");
                stage.show();
        }

        public static void main(String[] args) {
                Application.launch(args);
        }

        public static void fenetre() throws IOException {
                Stage stage = new Stage();
                FXMLLoader loader = new FXMLLoader();
                java.net.URL fxmlFileUrl = ChoixSejour.class.getResource("incompatibilites.fxml");
                if (fxmlFileUrl == null) {
                        System.out.println("Impossible de charger le fichier fxml");
                        System.exit(-1);
                }
                loader.setLocation(fxmlFileUrl);
                Parent root = loader.load();

                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.setTitle("Incompatibilités");
                stage.show();
        }
}
