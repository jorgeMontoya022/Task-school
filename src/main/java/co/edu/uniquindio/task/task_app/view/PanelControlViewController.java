package co.edu.uniquindio.task.task_app.view;

import java.net.URL;
import java.util.ResourceBundle;

import co.edu.uniquindio.task.task_app.Session.Sesion;
import co.edu.uniquindio.task.task_app.model.Usuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

public class PanelControlViewController extends CoreViewController{

    Usuario usuario;

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
    private TableColumn<?, ?> colEstado;

    @FXML
    private TableColumn<?, ?> colFecha;

    @FXML
    private TableColumn<?, ?> colMateria;

    @FXML
    private TableColumn<?, ?> colTarea;

    @FXML
    private Text label;

    @FXML
    private AnchorPane materiasView;

    @FXML
    private ScrollPane panelPrincipalView;

    @FXML
    private TableView<?> tablaTareas;

    @FXML
    private Text textCarrera;

    @FXML
    private Text textNombre;

    @FXML
    void onAgregarTarea(ActionEvent event) {

    }

    @FXML
    public void onPanelPrincipal(ActionEvent event) {
        // Mostrar panel principal, ocultar el resto
        panelPrincipalView.setVisible(true);
        materiasView.setVisible(false);

        
        // Cambiar estilos de los botones
        actualizarEstilosBotones(btnPanelPrincipal);
    }
    
    @FXML
    public void onMisTareas(ActionEvent event) {
        // Mostrar vista de materias, ocultar el resto
        panelPrincipalView.setVisible(false);
        materiasView.setVisible(true);
    
        
        // Cambiar estilos de los botones
        actualizarEstilosBotones(btnMisTareas);
    }

    private void actualizarEstilosBotones(Button botonActivo) {
        // Restaurar el estilo por defecto para todos los botones
        String estiloInactivo = "-fx-background-color: white; -fx-text-fill: #718096; -fx-alignment: center-left; -fx-padding: 0 0 0 35; -fx-background-radius: 5;";
        btnPanelPrincipal.setStyle(estiloInactivo);
        btnMisTareas.setStyle(estiloInactivo);
        btnTareasPendiente.setStyle(estiloInactivo);
        
        // Aplicar estilo activo al botón seleccionado
        String estiloActivo = "-fx-background-color: #edf2f7; -fx-text-fill: #2d3748; -fx-alignment: center-left; -fx-padding: 0 0 0 35; -fx-background-radius: 5;";
        botonActivo.setStyle(estiloActivo);
    }

    @FXML
    void ontareasPendientes(ActionEvent event) {

    }

    @FXML
    void initialize() {
       usuario = (Usuario) Sesion.getInstance().getUsuario();
       System.out.println("Usuario en PanelControl: " + usuario);
       initView();

    }

    private void initView() {
        mostrarInformacion();
    }

    private void mostrarInformacion() {
        textCarrera.setText(usuario.getCarrera());
        textNombre.setText(usuario.getNombreCompleto());
    
    }

}