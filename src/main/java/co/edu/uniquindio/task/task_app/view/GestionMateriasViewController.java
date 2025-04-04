package co.edu.uniquindio.task.task_app.view;

import java.net.URL;
import java.util.ResourceBundle;

import co.edu.uniquindio.task.task_app.controller.GestionMateriasController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class GestionMateriasViewController {

    GestionMateriasController gestionMateriasController;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnAgregarMateria;

    @FXML
    void onAgregarMateria(ActionEvent event) {

    }

    @FXML
    void initialize() {
        gestionMateriasController = new GestionMateriasController();
    }

}
