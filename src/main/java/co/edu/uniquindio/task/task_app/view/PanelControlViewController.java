package co.edu.uniquindio.task.task_app.view;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import co.edu.uniquindio.task.task_app.Session.Sesion;
import co.edu.uniquindio.task.task_app.controller.PanelControlController;
import co.edu.uniquindio.task.task_app.model.EstadoTarea;
import co.edu.uniquindio.task.task_app.model.Materia;
import co.edu.uniquindio.task.task_app.model.Tarea;
import co.edu.uniquindio.task.task_app.model.Usuario;
import co.edu.uniquindio.task.task_app.view.observer.EventType;
import co.edu.uniquindio.task.task_app.view.observer.ObserverManagement;
import co.edu.uniquindio.task.task_app.view.observer.ObserverView;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

public class PanelControlViewController extends CoreViewController implements ObserverView {

    PanelControlController panelControlController;

    ObservableList listaTareas = FXCollections.observableArrayList();

    Usuario usuario;

    Tarea tareaSeleccionada;

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
    private ComboBox<EstadoTarea> cbEstado;

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
    private DatePicker datePickerFechaLimite;

    @FXML
    private Text labeBienvenida;

    @FXML
    private Text labelTareasCompletadas;

    @FXML
    private Text labelTareasPendientes;

    @FXML
    private Text labelVencimientoPróximo;

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
        eliminarTarea();

    }

    private void eliminarTarea() {
        mostrarMensaje("Funcionalidad no disponible", "Aviso",
                "¡Ups! Esta funcionalidad todavía no está lista. Pronto estará disponible.",
                Alert.AlertType.INFORMATION);
    }

    @FXML
    void onActualizarTarea(ActionEvent event) {
        actualizarTarea();

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
        limpiarCampos();

    }

    @FXML
    void onAgregarTarea(ActionEvent event) {
        agregarTarea();

    }

    @FXML
    void initialize() {
        panelControlController = new PanelControlController();
        usuario = (Usuario) Sesion.getInstance().getUsuario();
        System.out.println("Usuario en PanelControl: " + usuario);
        initView();
        setupFilter();
        ObserverManagement.getInstance().addObserver(EventType.MATERIA, this);

    }

    private void initView() {
        initDataBinding();
        getTareas();
        initializeDataComboBox();
        initDatePickerListener();
        tablaTareas.getItems().clear();
        tablaTareas.setItems(listaTareas);
        listenerSelection();
        mostrarInformacion();
        mostrarEntregas();

    }

    private void mostrarEntregas() {
        mostrarCantTareasPendientes();
        mostrarCantTareasProximas();
        mostrarCantTareasCompletadas();
    }

    private void mostrarCantTareasCompletadas() {
        int tareasCompletadas = panelControlController.contarTareasCompletadas(usuario.getCorreo());
        labelTareasCompletadas.setText(String.valueOf(tareasCompletadas));
    }

    private void mostrarCantTareasProximas() {
        int tareasProximas = panelControlController.contarTareasProximas(usuario.getCorreo());
        labelVencimientoPróximo.setText(String.valueOf(tareasProximas));
    }

    private void mostrarCantTareasPendientes() {
        int tareasPendientes = panelControlController.contarTareasPendientes(usuario.getCorreo());
        labelTareasPendientes.setText(String.valueOf(tareasPendientes));
    }

    private void listenerSelection() {
        tablaTareas.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            tareaSeleccionada = newSelection;
            mostrarInformacionTarea(tareaSeleccionada);
        });

    }

    private void mostrarInformacionTarea(Tarea tareaSeleccionada) {
        if (tareaSeleccionada != null) {
            cbMateria.setValue(tareaSeleccionada.getMateria());
            txtDescripcion.setText(tareaSeleccionada.getDescripción());
            txtNombreTarea.setText(tareaSeleccionada.getTitulo());
            datePickerFechaLimite.setValue(tareaSeleccionada.getFechaEntrega());
            cbEstado.setValue(tareaSeleccionada.getEstadoTarea());
        }
    }

    private void setupFilter() {
        txtfiltrarTarea.textProperty().addListener((observable, oldValue, newValue) -> {
            List<Tarea> originalList = panelControlController.getTareas(usuario.getCorreo());
            ObservableList<Tarea> filteredLis = filterList(originalList, newValue);
            tablaTareas.setItems(filteredLis);
        });
    }

    private ObservableList<Tarea> filterList(List<Tarea> originalList, String newValue) {
        List<Tarea> filteredList = new ArrayList<>();
        for (Tarea tarea : originalList) {
            if(encontrarTarea(tarea, newValue)) filteredList.add(tarea);

        }
        return FXCollections.observableList(filteredList);
    }

    private boolean encontrarTarea(Tarea tarea, String newValue) {
        return(tarea.getMateria().getNombreMateria().toLowerCase().contains(newValue.toLowerCase()) || 
        tarea.getTitulo().toLowerCase().contains(newValue.toLowerCase()));
    }

    private void initializeDataComboBox() {
        ObservableList<Materia> materias = FXCollections.observableArrayList(usuario.getListaMaterias());

        initializeComboBox(cbMateria, materias, item -> item.getNombreMateria());

        cbMateria.getSelectionModel().selectedItemProperty().addListener((obs, oldValue, newValue) -> {
            if (newValue != null) {
                labelNombreProfesor.setText(newValue.getProfesor());
            } else {
                labelNombreProfesor.setText("profesor");
            }
        });

        cbEstado.setItems(FXCollections.observableArrayList(EstadoTarea.values()));

    }

    private void initDatePickerListener() {
        datePickerFechaLimite.valueProperty().addListener((obs, oldDate, newDate) -> {
            if (newDate != null) {
                long diasRestantes = java.time.temporal.ChronoUnit.DAYS.between(java.time.LocalDate.now(), newDate);
                String prioridad;

                if (diasRestantes <= 2) {
                    prioridad = "Alta";
                } else if (diasRestantes <= 5) {
                    prioridad = "Media";
                } else {
                    prioridad = "Baja";
                }

                labelPrioridad.setText(prioridad);
            } else {
                labelPrioridad.setText("prioridad");
            }
        });
    }

    private void initDataBinding() {
        colTarea.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTitulo()));
        colFecha.setCellValueFactory(
                cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getFechaEntrega())));
        colMateria.setCellValueFactory(
                cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getMateria())));
        colEstado.setCellValueFactory(
                cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getEstadoTarea())));
    }

    private void mostrarInformacion() {
        textCarrera.setText(usuario.getCarrera());
        textNombre.setText(usuario.getNombreCompleto());
        labeBienvenida.setText("Bienvenido de vuelta, " + usuario.getNombreCompleto());

    }

    private void getTareas() {
        String correo = usuario.getCorreo();
        listaTareas.clear();
        listaTareas.addAll(panelControlController.getTareas(correo));

    }

    private void agregarTarea() {
        Tarea tarea = buildDataTarea();
        if (tarea == null) {
            mostrarMensaje("Error", "Datos inválidos", "No se pudo crear la tarea", Alert.AlertType.ERROR);
            return;
        }

        if (validarFormulario(tarea)) {
            if (panelControlController.agregarTarea(tarea, usuario.getCorreo())) {
                listaTareas.add(tarea);
                mostrarEntregas();
                ObserverManagement.getInstance().notifyObservers(EventType.TAREA);
                mostrarMensaje("Notificación", "Tarea agregada", "La tarea fue creada con éxito",
                        Alert.AlertType.INFORMATION);
                limpiarCampos();
                getTareas();
            } else {
                mostrarMensaje("Error", "Tarea no agregada", "La tarea no pudo ser agregada", Alert.AlertType.ERROR);
            }
        }
    }

    private void actualizarTarea() {
        Tarea tarea = buildDataTarea();
        if (tareaSeleccionada != null) {
            if (validarFormulario(tarea)) {
                if (!verificarCambios(tareaSeleccionada, tarea)) {
                    mostrarMensaje("Error", "Sin cambios",
                            "No se puede actualizar sin cambios", Alert.AlertType.WARNING);
                }

                int selectedIndex = tablaTareas.getSelectionModel().getSelectedIndex();
                if (panelControlController.actualizarTarea(tareaSeleccionada, tarea, usuario.getCorreo())) {
                    ObserverManagement.getInstance().notifyObservers(EventType.TAREA);
                    mostrarEntregas();
                    tablaTareas.refresh();
                    listaTareas.set(selectedIndex, tarea);
                    tablaTareas.refresh();
                    tablaTareas.getSelectionModel().select(tarea);
                    mostrarMensaje("Notificación", "Tarea Actualizada",
                            "La tarea ha sido actualizada con éxito", Alert.AlertType.INFORMATION);
                    getTareas();
                    limpiarCampos();
                }
            } else {
                mostrarMensaje("Error", "La tarea no ctualizada",
                        "La tarea no pudo ser actualizada", Alert.AlertType.ERROR);
            }

        } else {
            mostrarMensaje("Advertencia", "Tarea no seleccionada",
                    "Por favor selecciones una tarea que desea actualizar", Alert.AlertType.WARNING);
        }
    }

    private boolean verificarCambios(Tarea tareaSeleccionada, Tarea tarea) {
        return !tareaSeleccionada.getTitulo().equals(tarea.getTitulo()) ||
                !tareaSeleccionada.getFechaEntrega().equals(tarea.getFechaEntrega()) ||
                !tareaSeleccionada.getEstadoTarea().equals(tarea.getEstadoTarea()) ||
                !tareaSeleccionada.getMateria().equals(tarea.getMateria()) ||
                !tareaSeleccionada.getDescripción().equals(tarea.getDescripción());

    }

    private void limpiarCampos() {
        txtDescripcion.clear();
        txtNombreTarea.clear();
        datePickerFechaLimite.setValue(null);
        cbEstado.getSelectionModel().clearSelection();
        cbMateria.getSelectionModel().clearSelection();

    }

    private Tarea buildDataTarea() {
        return new Tarea(cbMateria.getValue(), txtDescripcion.getText(), txtNombreTarea.getText(),
                datePickerFechaLimite.getValue(), cbEstado.getValue());
    }

    private boolean validarFormulario(Tarea tarea) {
        String mensaje = "";

        if (tarea.getMateria() == null) {
            mensaje += "La materia es requerida.\n";
        }

        if (tarea.getTitulo() == null || tarea.getTitulo().trim().isEmpty()) {
            mensaje += "El nombre de la tarea es requerido.\n";
        }

        if (tarea.getFechaEntrega() == null) {
            mensaje += "La fecha de entrega es requerida.\n";
        } else if (tarea.getFechaEntrega().isBefore(LocalDate.now())) {
            mensaje += "La fecha de entrega no puede ser anterior al día actual.\n";
        }

        if (tarea.getEstadoTarea() == null) {
            mensaje += "El estado es requerido.\n";
        }

        if (!mensaje.isEmpty()) {
            mostrarMensaje("Notificación de validación", "Datos no válidos", mensaje, Alert.AlertType.WARNING);
            return false;
        }

        return true;
    }

    @Override
    public void updateView(EventType eventType) {
        if (eventType == EventType.MATERIA) {
            initializeDataComboBox();
            getTareas();
            mostrarEntregas();
        }
    }

}