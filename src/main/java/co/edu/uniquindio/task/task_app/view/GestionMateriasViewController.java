package co.edu.uniquindio.task.task_app.view;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class GestionMateriasViewController {

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
        assert btnAgregarMateria != null : "fx:id=\"btnAgregarMateria\" was not injected: check your FXML file 'gestion-materias-view.fxml'.";
        assert columnaColor != null : "fx:id=\"columnaColor\" was not injected: check your FXML file 'gestion-materias-view.fxml'.";
        assert columnaHorario != null : "fx:id=\"columnaHorario\" was not injected: check your FXML file 'gestion-materias-view.fxml'.";
        assert columnaNombre != null : "fx:id=\"columnaNombre\" was not injected: check your FXML file 'gestion-materias-view.fxml'.";
        assert columnaProfesor != null : "fx:id=\"columnaProfesor\" was not injected: check your FXML file 'gestion-materias-view.fxml'.";
        assert columnaTareas != null : "fx:id=\"columnaTareas\" was not injected: check your FXML file 'gestion-materias-view.fxml'.";
        assert tablaMaterias != null : "fx:id=\"tablaMaterias\" was not injected: check your FXML file 'gestion-materias-view.fxml'.";

    }

}
