package co.edu.uniquindio.task.task_app.view;

import java.net.URL;
import java.util.ResourceBundle;

import co.edu.uniquindio.task.task_app.Session.Sesion;
import co.edu.uniquindio.task.task_app.controller.PanelControlController;
import co.edu.uniquindio.task.task_app.model.Materia;
import co.edu.uniquindio.task.task_app.model.Tarea;
import co.edu.uniquindio.task.task_app.model.Usuario;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

public class PanelControlViewController extends CoreViewController {

    PanelControlController panelControlController;

    ObservableList listaTareas = FXCollections.observableArrayList();

    Usuario usuario;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnActualizar;

    @FXML
    private Button btnAgregarTarea;

    @FXML
    private Button btnEliminarTarea;

    @FXML
    private Button btnMisMaterias;

    @FXML
    private Button btnPanelPrincipal;

    @FXML
    private Button btnVolverInicio;

    @FXML
    private ComboBox<?> cbEstado;

    @FXML
    private ComboBox<Materia> cbMateria;

    @FXML
    private Label labelNombreProfesor;

    @FXML
    private Label labelPrioridad;

    @FXML
    private TableView<Tarea> tablaTareas;


    @FXML
    private TableColumn<Tarea, String> colEstado;

    @FXML
    private TableColumn<Tarea, String> colFecha;

    @FXML
    private TableColumn<Tarea, String> colMateria;

    @FXML
    private TableColumn<Tarea, String> colTarea;

    @FXML
    private TextArea txtDescripcion;

    @FXML
    private TextField txtNombreTarea;

    @FXML
    private TextField txtfiltrarTarea;

    @FXML
    private Text labeBienvenida;

    @FXML
    private AnchorPane materiasView;

    @FXML
    private ScrollPane panelPrincipalView;

    
    @FXML
    void onVolverInicio(ActionEvent event) {
        if (mostrarMensajeConfirmacion("¿Deseas volver al inicio?")) {
            Sesion.getInstance().closeSesion();
            browseWindow("/co/edu/uniquindio/task/task_app/view/login-view.fxml", "Inicio de sesión", event);
        }

    }

    @FXML
    void oEliminarTarea(ActionEvent event) {

    }

    @FXML
    void onActualizarTarea(ActionEvent event) {

    }


    @FXML
    private Text textCarrera;

    @FXML
    private Text textNombre;

    @FXML
    public void onPanelPrincipal(ActionEvent event) {
        // Mostrar panel principal, ocultar el resto
        panelPrincipalView.setVisible(true);
        materiasView.setVisible(false);

        // Cambiar estilos de los botones
        actualizarEstilosBotones(btnPanelPrincipal);
    }

    @FXML
    void onMisMaterias(ActionEvent event) {
        panelPrincipalView.setVisible(false);
        materiasView.setVisible(true);

        actualizarEstilosBotones(btnMisMaterias);

    }

    private void actualizarEstilosBotones(Button botonActivo) {
        // Restaurar el estilo por defecto para todos los botones
        String estiloInactivo = "-fx-background-color: white; -fx-text-fill: #718096; -fx-alignment: center-left; -fx-padding: 0 0 0 35; -fx-background-radius: 5;";
        btnPanelPrincipal.setStyle(estiloInactivo);
        btnMisMaterias.setStyle(estiloInactivo);

        // Aplicar estilo activo al botón seleccionado
        String estiloActivo = "-fx-background-color: #edf2f7; -fx-text-fill: #2d3748; -fx-alignment: center-left; -fx-padding: 0 0 0 35; -fx-background-radius: 5;";
        botonActivo.setStyle(estiloActivo);
    }

    @FXML
    void onLimpiarFormulario(ActionEvent event) {
        

    }

    @FXML
    void onAgregarTarea(ActionEvent event) {

    }

    @FXML
    void initialize() {
        panelControlController = new PanelControlController();
        usuario = (Usuario) Sesion.getInstance().getUsuario();
        System.out.println("Usuario en PanelControl: " + usuario);
        initView();

    }

    private void initView() {
        initDataBinding();
        getTareas();
        initializeDataComboBox();
        tablaTareas.getItems().clear();
        tablaTareas.setItems(listaTareas);
        listenerSelection();
        mostrarInformacion();
        
        
    }

     
    private void listenerSelection() {
      
    }

    private void initializeDataComboBox() {
     
    }

    private void initDataBinding() {
        colTarea.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTitulo()));
        colFecha.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getFechaEntrega())));
        colMateria.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getMateria())));
        colEstado.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getEstadoTarea())));
    }

    private void mostrarInformacion() {
        textCarrera.setText(usuario.getCarrera());
        textNombre.setText(usuario.getNombreCompleto());
        labeBienvenida.setText("Bienvenido de vuelta, " + usuario.getNombreCompleto());

    }

    private void getTareas() {
        String correo = usuario.getCorreo();
        listaTareas.clear();
       
    }

}