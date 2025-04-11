package co.edu.uniquindio.task.task_app.view;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class CreacionTareasViewController extends CoreViewController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnAgregar;

    @FXML
    private Button btnCompletada;

    @FXML
    private Button btnEditar;

    
 

    @FXML
    private ComboBox<?> cbMateria;

    @FXML
    private DatePicker datePickerFechaLimite;

    @FXML
    private Label lblEstado;

    @FXML
    private Label lblFechaCreada;

    @FXML
    private Label lblPrioridad;

    @FXML
    private Label lblProfesor;

    @FXML
    private Label lblTiempoRestante;

    @FXML
    private TextArea txtDescripcion;

    @FXML
    private TextField txtNombreTarea;

    @FXML
    void onAgregarTarea(ActionEvent event) {

    }

    @FXML
    void onEditarTarea(ActionEvent event) {

    }

    @FXML
    void onMarcarCompletada(ActionEvent event) {

    }


    @FXML
    void initialize() {
        

    }

}
