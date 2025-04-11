package co.edu.uniquindio.task.task_app.view;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class RegistroViewContoller extends CoreViewController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField carreraField;

    @FXML
    private TextField emailField;

    @FXML
    private Hyperlink loginLink;

    @FXML
    private TextField nombreField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button registerButton;

    @FXML
    private TextField universidadField;

    @FXML
    void handleLogin(ActionEvent event) {
        if (mostrarMensajeConfirmacion("¿Volvemos al inicio de sesión?")) {
            browseWindow("/co/edu/uniquindio/task/task_app/view/login-view.fxml", "Inicio de sesión", event);
        }

    }

    @FXML
    void handleRegister(ActionEvent event) {

    }

    @FXML
    void initialize() {
      

    }

}
