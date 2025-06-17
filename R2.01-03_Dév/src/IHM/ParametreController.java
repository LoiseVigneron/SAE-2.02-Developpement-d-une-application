package IHM;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class ParametreController {

    @FXML
    private Button validerButton;

    @FXML
    private Button cancelButton;

    @FXML
    private void handleCancel() {
        cancelButton.getScene().getWindow().hide();
    }
    @FXML
    private void handleValide() {
        validerButton.getScene().getWindow().hide();
    }
    
}
