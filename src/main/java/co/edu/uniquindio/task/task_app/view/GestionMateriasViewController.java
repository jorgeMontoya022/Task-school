package co.edu.uniquindio.task.task_app.view;

import java.net.URL;
import java.util.ResourceBundle;

import co.edu.uniquindio.task.task_app.Session.Sesion;
import co.edu.uniquindio.task.task_app.model.Usuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class GestionMateriasViewController {

    Usuario usuario;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnAgregarMateria;

    @FXML
    private TableColumn<?, ?> columnaColor;

    @FXML
    private TableColumn<?, ?> columnaHorario;

    @FXML
    private TableColumn<?, ?> columnaNombre;

    @FXML
    private TableColumn<?, ?> columnaProfesor;

    @FXML
    private TableColumn<?, ?> columnaTareas;

    @FXML
    private TableView<?> tablaMaterias;

    @FXML
    void onAgregarMateria(ActionEvent event) {

    }

    @FXML
    void initialize() {
      usuario = (Usuario) Sesion.getInstance().getUsuario();
      System.out.println("Usuario en la gestión de materias "+usuario.getNombreCompleto());

    }

}
