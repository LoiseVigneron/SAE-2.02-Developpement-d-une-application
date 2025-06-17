package IHM;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ChoixSejour extends Application {

        public void start(Stage stage) throws IOException {
                FXMLLoader loader = new FXMLLoader();
                java.net.URL fxmlFileUrl = getClass().getResource("ChoixSejour.fxml");
                if (fxmlFileUrl == null) {
                        System.out.println("Impossible de charger le fichier fxml");
                }
                loader.setLocation(fxmlFileUrl);
                Parent root = loader.load();

                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.setTitle("Choix Sejour");
                stage.show();
        }

        


        public static void main(String[] args) {
                Application.launch(args);
        }

        public static void fenetre(HomeController homeController) throws IOException {
                FXMLLoader loader = new FXMLLoader(ChoixSejour.class.getResource("ChoixSejour.fxml"));
                Parent root = loader.load();

                choixSejourController controller = loader.getController();
                controller.setHomeController(homeController); // 👈 Passage du contrôleur Home

                Stage stage = new Stage();
                stage.setTitle("Choix du séjour");
                stage.setScene(new Scene(root));
                stage.show();
        }

}
