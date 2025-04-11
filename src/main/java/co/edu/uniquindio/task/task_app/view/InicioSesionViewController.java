package co.edu.uniquindio.task.task_app.view;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import co.edu.uniquindio.task.task_app.controller.InicioSesionController;
import co.edu.uniquindio.task.task_app.model.Usuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class InicioSesionViewController extends CoreViewController {

    InicioSesionController inicioSesionController;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField emailField;

    @FXML
    private Button loginButton;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Hyperlink registerLink;

    @FXML
    void handleLogin(ActionEvent event) {
        if (!validarCamposRequeridos()) {
            return;
        }

        try {
            String correo = emailField.getText().trim();
            String contrasenia = passwordField.getText().trim();

            if (!validarCorreo(correo)) {
                mostrarMensaje("Formato inválido", "Correo incorrecto", "El correo ingresado no es válido.", Alert.AlertType.WARNING);
                emailField.requestFocus();
                return;
            }

            Usuario usuarioValidado = inicioSesionController.validarAcceso(correo, contrasenia);

            if (usuarioValidado == null) {
                mostrarMensaje("Acceso denegado", "Credenciales inválidas", "Correo o contraseña incorrectos.", Alert.AlertType.ERROR);
                return;
            }

            inicioSesionController.guardarSesion(usuarioValidado);
    
            navegarAlInicio(usuarioValidado, event);

        } catch (Exception e) {
            mostrarMensaje("Error inesperado", "No se pudo iniciar sesión", e.getMessage(), Alert.AlertType.ERROR);
            e.printStackTrace();
        }
    }

  

    private void navegarAlInicio(Usuario usuarioValidado, ActionEvent event) {
        try {
            if(usuarioValidado!=null) {
                browseWindow("/co/edu/uniquindio/task/task_app/view/panel-control-view.fxml", "Panel de control", event);

            }
        } catch (Exception e) {
            mostrarMensaje(
                    "Error de redirección",
                    "No se pudo cargar la siguiente ventana",
                    "Ocurrió un error al intentar cargar la ventana de usuario. Por favor intente nuevamente.",
                    Alert.AlertType.ERROR
            );
        }
    }



    @FXML
    void handleRegister(ActionEvent event) {
        if(mostrarMensajeConfirmacion("¿Esto te lleva al registro... ¿le damos?")){
            browseWindow("/co/edu/uniquindio/task/task_app/view/registro-view.fxml", "Registro", event);

        }

    }

    @FXML
    void initialize() {
        inicioSesionController = new InicioSesionController();
        emailField.setText("jorgew.montoyat@uqvirtual.edu.co");
        passwordField.setText("maloh");
        

    }

    private boolean validarCamposRequeridos() {
        if (emailField.getText().trim().isEmpty() || passwordField.getText().trim().isEmpty()) {
            mostrarMensaje("Campos vacíos", "Faltan datos", "Por favor llene todos los datos", Alert.AlertType.WARNING);
            return false;

        }
        return true;
    }

    private boolean validarCorreo(String correo) {
         Pattern patron = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
        Matcher matcher = patron.matcher(correo);
        return matcher.find();
    }

}
