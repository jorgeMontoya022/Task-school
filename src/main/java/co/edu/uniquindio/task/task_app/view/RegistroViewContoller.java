package co.edu.uniquindio.task.task_app.view;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import co.edu.uniquindio.task.task_app.controller.RegistroController;
import co.edu.uniquindio.task.task_app.model.Usuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class RegistroViewContoller extends CoreViewController {
    RegistroController registroController;

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
        Usuario usuario = buildDataUsuario();
        if(usuario == null) {
            mostrarMensaje("Notificación", "Error en registro", "No pudiste registrar con éxito, vuelve a intentarlo mas tarde", Alert.AlertType.ERROR);
            return;
        }

        if(validarDatos(usuario)) {
            if(registroController.registrarUsuario(usuario)){
                mostrarMensaje("Notificación", "Usuario registrado", "Te has registrado correctamente", Alert.AlertType.INFORMATION);
                browseWindow("/co/edu/uniquindio/task/task_app/view/login-view.fxml", "Inicio de sesión", event);
                limpiarCampos();
            } else {
                mostrarMensaje("Error", "Usuario no registrado", "El usuario no puedo ser registrado", Alert.AlertType.ERROR);
            }
        }


    }

    private boolean validarDatos(Usuario usuario) {
        String mensaje = "";
        if (usuario.getNombreCompleto().isEmpty()) {
            mensaje += "El nombre es requerido.\n";
        }
        if (usuario.getInstitucion().isEmpty()) {
            mensaje += "La institución es requerida.\n";
        }
         if (usuario.getCorreo().isEmpty()) {
            mensaje += "El correo es requerido.\n";
         }else if(!validarCorreo(usuario.getCorreo())){
            mensaje += "El formato del correo es inválido.\n";
         }
         if (usuario.getContrasenia().isEmpty()) {
            mensaje += "Necesitas crear una contraseña.\n";
         }
         if (usuario.getCarrera().isEmpty()) {
            mensaje += "Queremos saber que estudias.\n";
         }
         if(registroController.verificarUsuarioExiste(usuario.getCorreo())){
            mensaje += "Ya hay una cuenta registrada con el mismo correo";

         }
         if (!mensaje.isEmpty()) {
            mostrarMensaje("Notificación de validación", "Datos no validos", mensaje, Alert.AlertType.WARNING);
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

    private void limpiarCampos() {
       nombreField.clear();
       carreraField.clear();
       emailField.clear();
       passwordField.clear();
       universidadField.clear();
    }

    private Usuario buildDataUsuario() {
        String nombre = nombreField.getText();
        String carrera = carreraField.getText();
        String correo = emailField.getText();
        String contraseña = passwordField.getText();
        String universidad = universidadField.getText();

        return new Usuario(nombre, correo, contraseña, universidad, carrera);
    }

    @FXML
    void initialize() {
      registroController = new RegistroController();

    }

}
