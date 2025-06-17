package IHM;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Home extends Application {

        public void start(Stage stage) throws IOException {
                FXMLLoader loader = new FXMLLoader();
                java.net.URL fxmlFileUrl = getClass().getResource("Home.fxml");
                if (fxmlFileUrl == null) {
                        System.out.println("Impossible de charger le fichier fxml");
                }
                loader.setLocation(fxmlFileUrl);
                Parent root = loader.load();

                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.setTitle("Home");
                stage.show();
        }

        public static void main(String[] args) {
                Application.launch(args);
        }
}
