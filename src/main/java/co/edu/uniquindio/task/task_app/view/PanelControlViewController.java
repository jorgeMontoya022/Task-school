package co.edu.uniquindio.task.task_app.view;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

public class PanelControlViewController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnAgregarTarea;

    @FXML
    private Button btnMisTareas;

    @FXML
    private Button btnPanelPrincipal;

    @FXML
    private Button btnTareasPendiente;

    @FXML
    private Text label;

    @FXML
    private Text textCarrera;

    @FXML
    private Text textNombre;

    @FXML
    void onAgregarTarea(ActionEvent event) {

    }

    @FXML
    void onMisTareas(ActionEvent event) {

    }

    @FXML
    void ontareasPendientes(ActionEvent event) {

    }

    @FXML
    void initialize() {
        assert btnAgregarTarea != null : "fx:id=\"btnAgregarTarea\" was not injected: check your FXML file 'panel-control-view.fxml'.";
        assert btnMisTareas != null : "fx:id=\"btnMisTareas\" was not injected: check your FXML file 'panel-control-view.fxml'.";
        assert btnPanelPrincipal != null : "fx:id=\"btnPanelPrincipal\" was not injected: check your FXML file 'panel-control-view.fxml'.";
        assert btnTareasPendiente != null : "fx:id=\"btnTareasPendiente\" was not injected: check your FXML file 'panel-control-view.fxml'.";
        assert label != null : "fx:id=\"label\" was not injected: check your FXML file 'panel-control-view.fxml'.";
        assert textCarrera != null : "fx:id=\"textCarrera\" was not injected: check your FXML file 'panel-control-view.fxml'.";
        assert textNombre != null : "fx:id=\"textNombre\" was not injected: check your FXML file 'panel-control-view.fxml'.";

    }

}
