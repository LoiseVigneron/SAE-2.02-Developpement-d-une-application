package IHM;

import java.io.IOException;
import java.util.ArrayList;

import SAE.Groupe;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class NouveauSejour extends Application {

        public void start(Stage stage) throws IOException {
                FXMLLoader loader = new FXMLLoader();
                java.net.URL fxmlFileUrl = getClass().getResource("NouveauSejour.fxml");
                if (fxmlFileUrl == null) {
                        System.out.println("Impossible de charger le fichier fxml");
                        System.exit(-1);
                }
                loader.setLocation(fxmlFileUrl);
                Parent root = loader.load();

                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.setTitle("Nouveau Sejour");
                stage.show();
        }

        public static void main(String[] args) {
                Application.launch(args);
        }

        public static void fenetre(ArrayList<Groupe> groupes) throws IOException {
                FXMLLoader loader = new FXMLLoader(NouveauSejour.class.getResource("NouveauSejour.fxml"));
                Parent root = loader.load();

                NouveauSejourController controller = loader.getController();
                controller.initData(groupes);

                Stage stage = new Stage();
                stage.setTitle("Nouveau Séjour");
                stage.setScene(new Scene(root));
                stage.show();
        }
}

